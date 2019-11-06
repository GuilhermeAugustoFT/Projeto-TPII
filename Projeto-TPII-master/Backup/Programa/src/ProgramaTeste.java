import leitorArquivoSistemaEquacao.LeitorArquivoSistemaEquacao;
import printador.Printador;
import resolvedorDeSistemas.ResolvedorDeSistemas;
import organizadorDeSistemas.OrganizadorDeSistemas;
import verificadorDeSistemas.VerificadorDeSistemas;

public class ProgramaTeste
{
    public static void main(String[] args)
    {
        try
        {
            //Teste de LeitorArquivoSistemaEquacao:
            System.out.println("Teste de LeitorArquivoSistemaEquacao: \n\n\n");

                LeitorArquivoSistemaEquacao leitor = new LeitorArquivoSistemaEquacao();

                leitor.ler("F:\\COTUCA\\Curso Técnico de Informática\\I Ano\\2 Sem\\Técnicas de Programação II\\2Java\\1Projetos\\Projeto1\\1Proposta e Observações\\gauss.txt");
                System.out.println(leitor + "\n");

                LeitorArquivoSistemaEquacao leitor2 = new LeitorArquivoSistemaEquacao(leitor);
                System.out.println(leitor2 + "\n");

                leitor2 = (LeitorArquivoSistemaEquacao)leitor.clone();
                System.out.println(leitor2 + "\n");

                System.out.println(leitor.getQtdEquacoes());
                System.out.println("\n");
                for(int i = 0; i < leitor.getLinhas().length; i++)
                {
                    System.out.println(leitor.getLinhas()[i]);
                }

                System.out.println(leitor.hashCode());

                System.out.println(leitor.equals(leitor2));
                System.out.println(leitor.equals(new LeitorArquivoSistemaEquacao()));

                try
                {
                    leitor.ler("jasjdij");
                }
                catch(Exception ex)
                {
                    System.err.println(ex.getMessage());
                }

            //---------------------------------------------------------------------------------------//
            //Teste de OrganizadorDeSistemas:
            System.out.println("\n\n\n" + "Teste de OrganizadorDeSistemas: \n\n\n");

                OrganizadorDeSistemas org = new OrganizadorDeSistemas(leitor.getLinhas(), leitor.getQtdEquacoes());
                OrganizadorDeSistemas org2 = new OrganizadorDeSistemas(org);
                org.montarMatriz();
                System.out.println(org + "\n\n");
                OrganizadorDeSistemas org3 = (OrganizadorDeSistemas)org.clone();
                System.out.println(org3 + "\n\n");
                org2.montarMatriz();
                org2.tirarZerosDaDiagonalPrincipal();
                org3 = new OrganizadorDeSistemas(org2);
                System.out.println(org + "\n\n");
                System.out.println(org2 + "\n\n");
                System.out.println(org3 + "\n\n");

                System.out.println(org.hashCode() + "\n");
                System.out.println(org2.hashCode() + "\n");
                System.out.println(org3.hashCode() + "\n\n");

                System.out.println(org.equals(org2) + "\n");
                System.out.println(org.equals(org3) + "\n");
                System.out.println(org2.equals(org3) + "\n\n");

                try
                {
                    org = new OrganizadorDeSistemas(null, 0);
                }
                catch(Exception ex)
                {
                    System.err.println(ex.getMessage());
                }

                try
                {
                    org = new OrganizadorDeSistemas(null, 1);
                }
                catch(Exception ex)
                {
                    System.err.println(ex.getMessage());
                }

                try
                {
                    String[] vetor = new String[3];
                    vetor[0] = "0 0 0 0";
                    vetor[1] = "0 0 0 0";
                    vetor[2] = "0 0 0 0";
                    org = new OrganizadorDeSistemas(vetor, 1);
                }
                catch(Exception ex)
                {
                    System.err.println(ex.getMessage());
                }

                try
                {
                    String[] vetor = new String[3];
                    vetor[0] = "0 0 0 0";
                    vetor[1] = "0 0 0 0";
                    vetor[2] = "0 0 0 0";
                    org = new OrganizadorDeSistemas(vetor, 3);
                    org.tirarZerosDaDiagonalPrincipal();
                }
                catch(Exception ex)
                {
                    System.err.println(ex.getMessage());
                }

            //---------------------------------------------------------------------------------------//
            //Teste de Printador:
            System.out.println("\n\n\n" + "Teste de Printador: \n\n\n");

                Printador.printarPerguntarLocalDoArquivo();
                System.out.println("\n");
                Printador.printarResultado(org2.montarMatriz(), org2.getQtdEquacoes());

            //---------------------------------------------------------------------------------------//
            //Teste de ResolvedorDeSistemas:
            System.out.println("\n\n\n" + "Teste de ResolvedorDeSistemas: \n\n\n");

                ResolvedorDeSistemas res = new ResolvedorDeSistemas(org2.tirarZerosDaDiagonalPrincipal(), org2.getQtdEquacoes());
                ResolvedorDeSistemas res2 = new ResolvedorDeSistemas(res);

                res.resolverSistema();
                System.out.println(res + "\n");
                ResolvedorDeSistemas res3 = (ResolvedorDeSistemas)res.clone();
                System.out.println(res2 + "\n");
                System.out.println(res3 + "\n\n");

                System.out.println(res.hashCode() + "\n");
                System.out.println(res2.hashCode() + "\n");
                System.out.println(res3.hashCode() + "\n\n");

                System.out.println(res.equals(res2) + "\n");
                System.out.println(res2.equals(res3) + "\n");
                System.out.println(res.equals(res3) + "\n\n");

                try
                {
                    res = new ResolvedorDeSistemas(null, 0);
                }
                catch(Exception ex)
                {
                    System.err.println(ex.getMessage());
                }

                try
                {
                    res = new ResolvedorDeSistemas(null, 1);
                }
                catch(Exception ex)
                {
                    System.err.println(ex.getMessage());
                }

                try
                {
                    res = new ResolvedorDeSistemas(null, 2);
                }
                catch(Exception ex)
                {
                    System.err.println(ex.getMessage());
                }

                try
                {
                    res = new ResolvedorDeSistemas(org.montarMatriz(), 3);
                    res.resolverSistema();
                }
                catch(Exception ex)
                {
                    System.err.println(ex.getMessage());
                }

            //---------------------------------------------------------------------------------------//
            //Teste de VerificadorDeSistemas:
            System.out.println("\n\n\n" + "Teste de VerificadorDeSistemas: \n\n\n");

                System.out.println(VerificadorDeSistemas.haPossibilidadeDeResolucao(org.montarMatriz(), org.getQtdEquacoes())); //Por enquanto, pode dizer que é true, pois será checado se é possível remover os zeros da diagonal principal posteriormente no programa
                System.out.println(VerificadorDeSistemas.haPossibilidadeDeResolucao(org2.montarMatriz(), org2.getQtdEquacoes()));
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}
