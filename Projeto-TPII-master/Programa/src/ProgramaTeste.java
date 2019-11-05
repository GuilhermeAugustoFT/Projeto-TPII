import resolvedorDeSistemas.ResolvedorDeSistemas;
import leitorArquivoSistemaEquacao.LeitorArquivoSistemaEquacao;
import organizadorDeSistemas.OrganizadorDeSistemas;
import verificadorDeSistemas.VerificadorDeSistemas;

public class ProgramaTeste
{
    public static void main(String[] args)
    {
        try
        {
            //Teste de LeitorArquivoSistemaEquacao:
                LeitorArquivoSistemaEquacao leitor = new LeitorArquivoSistemaEquacao();

                leitor.ler("C:\\Users\\nicol\\OneDrive\\Documentos\\GitHub\\Projeto-TPII\\Projeto-TPII-master\\Programa\\src\\gauss4.txt");
                //leitor.ler("C:\\Users\\u19192\\Documents\\GitHub\\Projeto-TPII\\Projeto-TPII-master\\Programa\\src\\gauss.txt");
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

        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}
