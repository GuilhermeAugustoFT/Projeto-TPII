import DivisorDeLinhas.DivisorDeLinhas;
import LeitorArquivoSistemaEquacao.LeitorArquivoSistemaEquacao;
import OrganizadorDeSistemas.OrganizadorDeSistemas;
import VerificadorDeSistemas.VerificadorDeSistemas;

public class ProgramaTeste
{
    public static void main(String[] args)
    {
        try
        {
            //Teste de LeitorArquivoSistemaEquacao:
                LeitorArquivoSistemaEquacao leitor = new LeitorArquivoSistemaEquacao();

                //leitor.ler("C:\\Users\\nicol\\OneDrive\\Documentos\\GitHub\\Projeto-TPII\\Projeto-TPII-master\\Programa\\src\\gauss.txt");
                leitor.ler("C:\\Users\\u19192\\Documents\\GitHub\\Projeto-TPII\\Projeto-TPII-master\\Programa\\src\\gauss.txt");
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
                    for(int j = 0; j < org.getQtdEquacoes() + 1; j++)
                    {
                        System.out.println(org.getMatriz()[i][j]);
                    }
                }

                System.out.println("\n\n");

                org.tirarZerosDaDiagonalPrincipal();
                for(int i = 0; i < org.getQtdEquacoes(); i++)
                {
                    for(int j = 0; j < org.getQtdEquacoes() + 1; j++)
                    {
                        System.out.println(org.getMatriz()[i][j]);
                    }
                }


            //Teste de VerificadorDeSistemas:
                VerificadorDeSistemas ver = new VerificadorDeSistemas(org.getMatriz(), org.getQtdEquacoes());
                ver.verificarPossibilidadeDeResolucao();

            //Teste de VerificadorDeSistemas:
                DivisorDeLinhas div = new DivisorDeLinhas(org.getMatriz(), org.getQtdEquacoes());
                div.resolverSistema();

                System.out.println("\n\n");

                for(int i = 0; i < div.getQtdEquacoes(); i++)
                {
                    for(int j = 0; j < div.getQtdEquacoes() + 1; j++)
                    {
                        System.out.println(div.getMatriz()[i][j]);
                    }
                }
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}
