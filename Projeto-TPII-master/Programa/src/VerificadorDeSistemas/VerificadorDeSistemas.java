package VerificadorDeSistemas;

public class VerificadorDeSistemas
{
    protected double[][] matrizEquacoes;
    protected int qtdEquacoes;

    public VerificadorDeSistemas(double[][] matrizEquacoes, int qtdEquacoes) throws Exception
    {
        if(matrizEquacoes == null)
            throw new Exception("A matriz era null");
        if(qtdEquacoes < 2)
            throw new Exception("A quantidade de equações é inválida");

        this.matrizEquacoes = matrizEquacoes.clone();
        this.qtdEquacoes = qtdEquacoes;
    }

    public VerificadorDeSistemas(VerificadorDeSistemas modelo) throws Exception
    {
        if(modelo == null)
            throw new Exception("O modelo era null");

        this.matrizEquacoes = modelo.matrizEquacoes.clone();
        this.qtdEquacoes = modelo.qtdEquacoes;
    }

    protected int getQtdColunas()
    {
        return this.qtdEquacoes + 1;
    }

    public int getQtdEquacoes()
    {
        return this.qtdEquacoes;
    }

    public double[][] getMatriz()
    {
        return this.matrizEquacoes;
    }

    public void verificarPossibilidadeDeResolucao() throws Exception
    {
        double[][] matrizResultados = new double[this.qtdEquacoes - 1][this.qtdEquacoes];
        boolean ok = true;

        for(int i = 0; i < this.qtdEquacoes - 1; i++)
        {
            for(int j = 0; j < this.qtdEquacoes; j++)
            {
                matrizResultados[i][j] = this.matrizEquacoes[i][j] / this.matrizEquacoes[i + 1][j];
            }
        }

        for(int i = 0; i < this.qtdEquacoes - 2; i++)
        {
            for(int j = 0; j < this.qtdEquacoes; j++)
            {
                if(matrizResultados[i][j] != matrizResultados[i + 1][j])
                {
                    ok = true;
                    break;
                }
                else
                    ok = false;
            }
        }
        if(!ok)
            throw new Exception("Sistema de equações impossível de resolver");
    }
}