package PrintadorDeResultadoSistema;

public class PrintadorDeResultadoSistema
{
    public static void printar(double[][] matriz, int qtdEquacoes)
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
}
