import LeitorArquivoSistemaEquacao.LeitorArquivoSistemaEquacao;
import OrganizadorDeSistemas.OrganizadorDeSistemas;
import ResolvedorDeSistemas.ResolvedorDeSistemas;
import VerificadorDeSistemas.VerificadorDeSistemas;
import PrintadorDeResultadoSistema.PrintadorDeResultadoSistema;

public class Programa
{
    public static void main(String[] args)
    {
        try
        {
            //FAZER COMENTÁRIOS, JAVADOCS ETC.
            //PEDIR PARA O USUÁRIO DIGITAR A FONTE DO ARQUIVO!!!

            LeitorArquivoSistemaEquacao leitor;
            OrganizadorDeSistemas org;
            VerificadorDeSistemas ver;
            ResolvedorDeSistemas res;

            String localDoArquivo = "C:\\Users\\nicol\\OneDrive\\Documentos\\GitHub\\Projeto-TPII\\Projeto-TPII-master\\Programa\\src\\gauss3.txt";

            leitor = new LeitorArquivoSistemaEquacao();
            leitor.ler(localDoArquivo);

            org = new OrganizadorDeSistemas(leitor.getLinhas(), leitor.getQtdEquacoes());
            org.montarMatriz();

            ver = new VerificadorDeSistemas(org.getMatriz(), org.getQtdEquacoes());
            ver.verificarPossibilidadeDeResolucao();

            org.setMatriz(ver.getMatriz(), ver.getQtdEquacoes());
            org.tirarZerosDaDiagonalPrincipal();

            res = new ResolvedorDeSistemas(org.getMatriz(), org.getQtdEquacoes());
            res.resolverSistema();

            PrintadorDeResultadoSistema.printar(res.getMatriz(), res.getQtdEquacoes());
        }
        catch(Exception ex) //TRATAR MELHOR A EXCEÇÃO, com System.err.println() etc. Sem lançar a exceção.
        {
            System.err.println(ex.getMessage());
        }
    }
}
