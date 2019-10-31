import LeitorArquivoSistemaEquacao.LeitorArquivoSistemaEquacao;
import OrganizadorDeSistemas.OrganizadorDeSistemas;

public class ProgramaTeste
{
    public static void main(String[] args)
    {
        try
        {
            //Teste de LeitorArquivoSistemaEquacao:
                LeitorArquivoSistemaEquacao leitor = new LeitorArquivoSistemaEquacao();

                leitor.ler("F:\\COTUCA\\Curso Técnico de Informática\\I Ano\\2 Sem\\Técnicas de Programação II\\2Java\\1Projetos\\Projeto1\\Programa\\src\\gauss.txt");
                System.out.println(leitor);

                LeitorArquivoSistemaEquacao leitor2 = new LeitorArquivoSistemaEquacao(leitor);
                System.out.println(leitor2);

                leitor2 = (LeitorArquivoSistemaEquacao)leitor.clone();
                System.out.println(leitor2);

                System.out.println(leitor.getQtdEquacoes());
                System.out.println("\n");
                for(int i = 0; i < leitor.getLinhas().length; i++)
                {
                    System.out.println(leitor.getLinhas()[i]);
                }

                System.out.println(leitor.hashCode());

                System.out.println(leitor.equals(leitor2));
                System.out.println(leitor.equals(new LeitorArquivoSistemaEquacao()));

            //Teste de OrganizadorDeSistemas:
                OrganizadorDeSistemas org = new OrganizadorDeSistemas(leitor.getLinhas(), leitor.getQtdEquacoes());

                org.montarMatriz();
                for(int i = 0; i < org.getQtdEquacoes(); i++)
                {
                    for(int j = 0; j < org.getQtdColunas(); j++)
                    {
                        System.out.println(org.getMatriz()[i][j]);
                    }
                }

                System.out.println("\n\n");

                org.tirarZerosDaDiagonalPrincipal();
                for(int i = 0; i < org.getQtdEquacoes(); i++)
                {
                    for(int j = 0; j < org.getQtdColunas(); j++)
                    {
                        System.out.println(org.getMatriz()[i][j]);
                    }
                }
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}
