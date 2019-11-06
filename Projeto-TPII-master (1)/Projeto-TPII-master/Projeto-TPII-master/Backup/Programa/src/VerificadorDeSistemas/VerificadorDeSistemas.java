package verificadorDeSistemas;

/**
 * A classe VerificadorDeSistemas é a classe singleton que verificará se é possível resolver o sistema de equações fornecido pelo usuário.
 * Esta classe verifica se o sistema definido pela matriz é possível de ser resolvido.
 * Nela encontramos um método para verificar se é possível resolver o sistema de equações.
 * @author Nícolas Maisonnette Duarte e Guilherme Augusto Felisberto Teixeira.
 * @since 2019.
 */
public class VerificadorDeSistemas //Classe singleton
{
    /**
     * Verifica se é possível resolver o sistema de equações.
     * Divide todas as linhas i por todas as outras linhas j e verifica se o resultado é o mesmo.
     * @return false, se o sistema de equações for impossível de ser resolvido; ou true, caso contrário.
     * @param matrizEquacoes matriz a ser verificada.
     * @param qtdEquacoes quantidade de equações na matriz.
     * @throws Exception se os parâmetros forem inválidos.
     */
    public static boolean haPossibilidadeDeResolucao(double[][] matrizEquacoes, int qtdEquacoes) throws Exception
    {
        if(matrizEquacoes == null || qtdEquacoes < 2)
            throw new Exception("Erro na passagem de parâmetros");

        double[][] matrizResultados = new double[qtdEquacoes][qtdEquacoes];
        boolean ok = true;

        for(int i = 0; i < qtdEquacoes; i++)
        {
            for(int k = i + 1; k < qtdEquacoes; k++)
            {
                for (int j = 0; j < qtdEquacoes; j++)
                {
                    matrizResultados[i][j] = matrizEquacoes[i][j] / matrizEquacoes[k][j]; //Divide uma pelas outras
                }
            }
        }

        for(int i = 0; i < qtdEquacoes; i++)
        {
            for(int k = i + 1; k < qtdEquacoes - i; k++)
            {
                for (int j = 0; j < qtdEquacoes; j++)
                {
                    if (matrizResultados[i][j] != matrizResultados[k][j]) //Verifica se o resultado é diferente
                    {
                        ok = true;
                        break;
                    }
                    else
                        ok = false;
                }
            }
        }

        return ok; //Retorna se a matriz está ok (Válida)
    }
}