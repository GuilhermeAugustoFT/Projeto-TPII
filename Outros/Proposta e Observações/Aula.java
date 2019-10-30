import java.util.StringTokenizer;
import java.io.*;

public class Aula
{
    public static void main (String[] args)
    {
        double dividendo = 0;
        double divisor   = 0;
        double resultado = dividendo / divisor;

        System.out.println (resultado);

        try
        {
            BufferedReader arquivo =
                           new BufferedReader (
                           new FileReader (
                           "gauss.txt"));

            int qtdEquacoes = Integer.parseInt (arquivo.readLine());

            for (int i=0; i<qtdEquacoes; i++)
            {
                StringTokenizer quebrador = new StringTokenizer (arquivo.readLine());

                while (quebrador.hasMoreTokens())
                    System.out.println (quebrador.nextToken());
            }
        }
        catch (Exception erro)
        {
		//...
	}
    }
}