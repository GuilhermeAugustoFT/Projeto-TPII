package DivisorDeLinhas;

public class DivisorDeLinhas
{
    protected double[][] matrizEquacoes;
    protected int qtdEquacoes;

    public DivisorDeLinhas(double[][] matrizEquacoes, int qtdEquacoes) throws Exception
    {
        if(matrizEquacoes == null)
            throw new Exception("A matriz era null");
        if(qtdEquacoes < 2)
            throw new Exception("A quantidade de equações é inválida");

        this.matrizEquacoes = matrizEquacoes.clone();
        this.qtdEquacoes = qtdEquacoes;
    }

    public DivisorDeLinhas(DivisorDeLinhas modelo) throws Exception
    {
        if(modelo == null)
            throw new Exception("O modelo era null");

        this.matrizEquacoes = modelo.matrizEquacoes.clone();
        this.qtdEquacoes = modelo.qtdEquacoes;
    }

    public int getQtdEquacoes()
    {
        return qtdEquacoes;
    }

    private int getQtdColunas()
    {
        return qtdEquacoes + 1;
    }

    public double[][] getMatriz()
    {
        return this.matrizEquacoes;
    }

    public void resolverSistema() throws Exception
    {
        double divisor;
        double resultado;

        for(int i = 0; i < this.qtdEquacoes; i++)
        {
            divisor = this.matrizEquacoes[i][i];
            for(int j = 0; j < this.getQtdColunas(); j++)
            {
                resultado = this.matrizEquacoes[i][j] / divisor;

                if(resultado != Double.POSITIVE_INFINITY && resultado != Double.NEGATIVE_INFINITY && !Double.isNaN(resultado))
                    this.matrizEquacoes[i][j] = resultado;
                else
                    throw new Exception("Proibido divisão por zero");
            }
            tornarDemaisElementosZero(i);
        }
    }

    private void tornarDemaisElementosZero(int coluna)
    {
        double[] aSerSomado = new double[this.getQtdColunas()];
        double numeroTrocado;

        for(int i = 0; i < this.qtdEquacoes; i++)
        {
            if(i != coluna)
            {
                numeroTrocado = -this.matrizEquacoes[i][coluna];
                if(numeroTrocado != 0)
                {
                    for(int j = 0; j < this.getQtdColunas(); j++)
                    {
                        aSerSomado[j] = this.matrizEquacoes[i][j] * numeroTrocado;

                        this.matrizEquacoes[coluna][j] = aSerSomado[j];
                    }
                }
            }
        }
    }
}
