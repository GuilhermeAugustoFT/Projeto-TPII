package OrganizadorDeSistemas;

import java.util.*;

public class OrganizadorDeSistemas
{
    protected double[][] matrizEquacoes;
    protected String[] vetorEquacoes;
    protected int qtdEquacoes;

    public OrganizadorDeSistemas(String[] vetorEquacoes, int qtdEquacoes) throws Exception
    {
        if(vetorEquacoes == null)
            throw new Exception("vetorEquacoes era null");
        if(qtdEquacoes < 2)
            throw new Exception("A quantidade de equações era inválida");

        this.vetorEquacoes = vetorEquacoes.clone();
        this.qtdEquacoes = qtdEquacoes;
    }

    public OrganizadorDeSistemas(OrganizadorDeSistemas modelo) throws Exception
    {
        if(modelo == null)
            throw new Exception("O modelo era null");

        this.qtdEquacoes = modelo.qtdEquacoes;

        String[] vetorEqNovo = new String[this.qtdEquacoes];
        for(int i = 0; i < this.qtdEquacoes; i++)
        {
            vetorEqNovo[i] = new String(modelo.vetorEquacoes[i]);
        }
        this.vetorEquacoes = vetorEqNovo;

        double[][] matrizEqNova = new double[this.qtdEquacoes + 1][this.qtdEquacoes];
        for(int i = 0; i < this.qtdEquacoes + 1; i++)
        {
            for(int j = 0; j < this.qtdEquacoes; j++)
            {
                matrizEqNova[i][j] = modelo.matrizEquacoes[i][j];
            }
        }
        this.matrizEquacoes = matrizEqNova;
    }

    public void montarMatriz() throws Exception
    {
        try
        {
            this.matrizEquacoes = new double[this.qtdEquacoes][this.qtdEquacoes + 1];

            for (int i=0; i < this.qtdEquacoes; i++)
            {
                StringTokenizer quebrador = new StringTokenizer(this.vetorEquacoes[i]);

                int j = 0;
                while(quebrador.hasMoreTokens())
                {
                    this.matrizEquacoes[i][j] = Double.parseDouble(quebrador.nextToken());
                    j++;
                }
            }
        }
        catch(Exception ex)
        {
            throw new Exception("Erro ao montar a matriz");
        }
    }

    public double[][] getMatriz()
    {
        return this.matrizEquacoes;
    }

    public int getQtdColunas()
    {
        return this.qtdEquacoes + 1;
    }

    public int getQtdEquacoes()
    {
        return this.qtdEquacoes;
    }
}
