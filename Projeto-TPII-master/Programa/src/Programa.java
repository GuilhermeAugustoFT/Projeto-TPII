//Importa todas as classes que serão utilizadas no programa
import leitorArquivoSistemaEquacao.LeitorArquivoSistemaEquacao;
import organizadorDeSistemas.OrganizadorDeSistemas;
import resolvedorDeSistemas.ResolvedorDeSistemas;
import teclado.Teclado;
import verificadorDeSistemas.VerificadorDeSistemas;
import printador.Printador;

public class Programa
{
    public static void main(String[] args)
    {
        programa:for(;;) //Sempre
        {
            try
            {
                //Cria variáveis a partir das classes que resolverão o sistema
                LeitorArquivoSistemaEquacao leitor;
                OrganizadorDeSistemas org;
                ResolvedorDeSistemas res;
                String localDoArquivo;

                for(;;) //Sempre
                {
                    Printador.printarPerguntarLocalDoArquivo(); //Pergunta o local onde o arquivo do sistema a ser resolvido está
                    localDoArquivo = Teclado.getUmString(); //Lê o local
                    if(localDoArquivo.toUpperCase().equals("SAIR")) //Verifica se, na realidade, o usuário queria parar o programa
                        break programa; //Para o primeiro loop

                    try
                    {
                        leitor = new LeitorArquivoSistemaEquacao(); //Instancia a variável do leitor de arquivos
                        leitor.ler(localDoArquivo); //Lê o arquivo do local passado
                        System.out.print("\n"); //Cria um espaçamento
                        break; //Para o último loop
                    }
                    catch (Exception ex) //Se der erro
                    {
                        System.err.println("Digite o local corretamente!" + "\n"); //Pede para o usuário digitar corretamente e volta para o começo do loop
                    }
                }

                org = new OrganizadorDeSistemas(leitor.getLinhas(), leitor.getQtdEquacoes()); //Instancia a variável do organizador de sistemas
                if(!VerificadorDeSistemas.haPossibilidadeDeResolucao(org.montarMatriz(), org.getQtdEquacoes())) //Verifica se o sistema pode, de fato, ser resolvido
                    throw new Exception("Sistema impossível de resolver");

                res = new ResolvedorDeSistemas(org.tirarZerosDaDiagonalPrincipal()/*Procura remover todos os zeros da diagonal principal da matriz, trocando a ordem das equações nela*/, org.getQtdEquacoes()); //Instancia a variável do resolvedor de sistemas

                Printador.printarResultado(res.resolverSistema()/*Resolve o sistema de equações localizado na matriz*/, res.getQtdEquacoes()); //Printa, por meio de uma classe singleton, os resultados do sistema de duas formas diferentes
                System.out.println("\n"); //Cria um espaçamento
            }
            catch (Exception ex) //Se der erro
            {
                System.err.println("Sistema impossível de se resolver! Tente novamente com outro!" + "\n"); //Avisa o usuário que o sistema passado é impossível de se resolver e pede para que ele tente novamente
            }
        }
    }
}
