package printador;

/**
 * A classe Printador representa uma classe singleton, que servirá apenas para interagir com o usuário,
 * printando os resultados e pedindo o diretório do arquivo de equações.
 * Nela encontramos os métodos printarResultado e printarPerguntarLocalDoArquivo.
 * @author Nícolas Maisonnette Duarte e Guilherme Augusto Felisberto Teixeira.
 * @since 2019.
 */
public class Printador //Classe singleton
{
    /**
     * Escreve o resultado do sistema de equações.
     * Ao receber seus parâmetros, o método gera uma string que contém, de uma forma autoexplicativa para o usuário,
     * os resultados do sistema de equações.
     * @param matriz matriz que o método printará na tela.
     * @param qtdEquacoes quantidade de equações presentes no sistema.
     */
    public static void printarResultado(double[][] matriz, int qtdEquacoes)
    {
        System.out.println("Os resultados valem, respectivamente:");

        double[] resultados = new double[qtdEquacoes];
        for(int i = 0; i < qtdEquacoes; i++)
        {
            resultados[i] = matriz[i][qtdEquacoes];

            if(i != qtdEquacoes - 1)
                System.out.printf("%.2f" + ";" + "\n", resultados[i]);
            else
                System.out.printf("%.2f" + "." + "\n\n", resultados[qtdEquacoes - 1]);
        }

        System.out.print("Portanto, S = (");
        for(int i = 0; i < qtdEquacoes; i++)
        {
            if(i != qtdEquacoes - 1)
                System.out.printf("%.2f" + "; ", resultados[i]);
            else
                System.out.printf("%.2f" + ")", resultados[qtdEquacoes - 1]);
        }
    }

    /**
     * Pergunta para o usuário o diretório do arquivo.
     * Pede para o usuário digitar o diretório do arquivo, que contém o sistema de equações que ele deseja resolver.
     */
    public static void printarPerguntarLocalDoArquivo()
    {
        System.out.println("Digite o local do arquivo do sistema para resolvê-lo ou digite SAIR para finalizar o programa: ");
    }
}
