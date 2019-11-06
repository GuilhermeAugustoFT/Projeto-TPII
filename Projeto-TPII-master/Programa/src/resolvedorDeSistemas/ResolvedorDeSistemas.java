package resolvedorDeSistemas;

/**
 * A classe ResolvedorDeSistemas é a classe que resolverá o sistema de equações, tendo
 * como base uma matriz que armazena os coeficientes das equações anteriormente armazenados pela classe LeitorArquivoSistemaEquação,
 * e organizados pela classe OrganizadorDeSistemas, e tendo também uma variável inteira que armazena a quantidade de equações no sistema.
 * Instâncias desta classe possuem a capacidade de resolver sistemas de equações anteriormente organizados.
 * Nela encontramos, por exemplo, um método para resolver o sistema, um construtor, equals etc.
 * @author Nícolas Maisonnette Duarte e Guilherme Augusto Felisberto Teixeira.
 * @since 2019.
 */
public class ResolvedorDeSistemas implements Cloneable
{
    /** Matriz de double onde os coeficientes das equações serão armazanados. */
    protected double[][] matrizEquacoes;

    /** Variável inteira representando a quantidade de equações do sistema lido. */
    protected int qtdEquacoes;

    /**
     * Constroi uma nova instância da classe ResolvedorDeSistemas.
     * @param matrizEquacoes matriz que possui os valores dos coeficientes do sistema.
     * @param qtdEquacoes inteiro cujo valor representa a quantidade de equações do sistema.
     * @throws Exception se a matriz de equações for nulo, ou se a quantidade de equações for inválida.
     */
    public ResolvedorDeSistemas(double[][] matrizEquacoes, int qtdEquacoes) throws Exception
    {
        if(matrizEquacoes == null)
            throw new Exception("A matriz era null");
        if(qtdEquacoes < 2)
            throw new Exception("A quantidade de equações é inválida");

        this.qtdEquacoes = qtdEquacoes;

        this.matrizEquacoes = new double[this.qtdEquacoes][this.getQtdColunas()];
        for(int i = 0; i < this.qtdEquacoes; i++) //Clona a matriz
        {
            for(int j = 0; j < this.getQtdColunas(); j++)
            {
                this.matrizEquacoes[i][j] = matrizEquacoes[i][j];
            }
        }
    }

    /**
     * Constroi uma cópia da instância da classe ResolvedorDeSistemas dada.
     * Para tanto, deve ser fornecida uma instancia da classe ResolvedorDeSistemas para ser
     * utilizada como modelo para a construção da nova instância criada.
     * @param modelo a instância da classe ResolvedorDeSistemas a ser usada como modelo.
     * @throws Exception se o modelo for null.
     */
    public ResolvedorDeSistemas(ResolvedorDeSistemas modelo) throws Exception
    {
        if(modelo == null)
            throw new Exception("O modelo era null");

        this.qtdEquacoes = modelo.qtdEquacoes;

        this.matrizEquacoes = new double[this.qtdEquacoes][this.getQtdColunas()];
        for(int i = 0; i < this.qtdEquacoes; i++) //Clona a matriz
        {
            for(int j = 0; j < this.getQtdColunas(); j++)
            {
                this.matrizEquacoes[i][j] = modelo.matrizEquacoes[i][j];
            }
        }
    }


    public int getQtdEquacoes()
    {
        return qtdEquacoes;
    }

    /**
     * Cria e retorna a quantidade de colunas.
     * Retorna a quantidade de colunas presentes na matriz da classe.
     * @return a quantidade de colunas, que é um inteiro.
     */
    protected int getQtdColunas()
    {
        return qtdEquacoes + 1;
    }

    /**
     * Resolve o sistema de equações.
     * Primeiramente, a diagonal principal será tornada um, e depois todo o resto será tornado zero por meio do método protected tornarDemaisElementosZero.
     * @throws Exception se algo for dividido por zero.
     * @return a matriz resolvida, com o número 1 na diagonal principal e o resto 0.
     */
    public double[][] resolverSistema() throws Exception
    {
        double divisor;
        double resultado;

        for(int i = 0; i < this.qtdEquacoes; i++)
        {
            divisor = this.matrizEquacoes[i][i];
            for(int j = 0; j < this.getQtdColunas(); j++)
            {
                resultado = this.matrizEquacoes[i][j] / divisor;

                if(resultado != Double.POSITIVE_INFINITY && resultado != Double.NEGATIVE_INFINITY && !Double.isNaN(resultado)) //Divisão por zero
                    this.matrizEquacoes[i][j] = resultado;
                else
                    throw new Exception("Proibido divisão por zero");
            }
            tornarDemaisElementosZero(i);
        }

        return this.matrizEquacoes;
    }

    /**
     * Torna os demais elemetos zero.
     * Soma a linha do elemento a ser tornado um multiplicada pelo oposto do elemento a ser tornado zero à linha do elemento a ser tornado zero.
     * @param coluna coluna a ser tornada zero, exceto pela diagonal principal da matriz.
     */
    protected void tornarDemaisElementosZero(int coluna)
    {
        double[] aSerSomado = new double[this.getQtdColunas()];
        double numeroTrocado;

        for(int i = 0; i < this.qtdEquacoes; i++)
        {
            if(i != coluna)
            {
                numeroTrocado = -this.matrizEquacoes[i][coluna]; //Oposto do número coletado
                if(numeroTrocado != 0)
                {
                    for (int j = 0; j < this.getQtdColunas(); j++)
                    {
                        aSerSomado[j] = this.matrizEquacoes[coluna][j] * numeroTrocado; //Multiplica

                        this.matrizEquacoes[i][j] = this.matrizEquacoes[i][j] + aSerSomado[j]; //Soma
                    }
                }
            }
        }
    }

    /**
     * Calcula o código de espalhamento (ou código de hash).
     * Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
     * hashcode) da classe ResolvedorDeSistemas representada pela instância à qual o método for aplicado.
     * @return o código de espalhamento do objeto chamante da classe ResolvedorDeSistemas.
     */
    public int hashCode()
    {
        int ret  = 2;

        ret = ret * 13 + new Integer(this.qtdEquacoes).hashCode();

        for (int i = 0; i < this.qtdEquacoes; i++)
            for (int j = 0; j < this.getQtdColunas(); j++)
                ret = ret * 13 + new Double(this.matrizEquacoes[i][j]).hashCode();

        if(ret < 0)
            return -ret;

        return ret;
    }

    /**
     * Verifica a igualdade entre dois ResolvedorDeSistemas.
     * Verifica se o Object fornecido como parâmetro representa um
     * ResolvedorDeSistemas igual àquele representado pela instância à qual este
     * método for aplicado, resultando true em caso afirmativo,
     * ou false, caso contrário.
     * @param  obj o objeto a ser comparado com a instância à qual esse método
     * for aplicado.
     * @return true, caso o Object fornecido ao método e a instância chamante do
     * método representarem ResolvedorDeSistemas iguais, ou false, caso contrário.
     */
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(this.getClass() != obj.getClass())
            return false;

        ResolvedorDeSistemas outro = (ResolvedorDeSistemas) obj;

        if(this.qtdEquacoes != outro.qtdEquacoes)
            return false;

        for(int i = 0; i < this.qtdEquacoes; i++)
            for(int j = 0; j < this.getQtdColunas(); j++)
                if(this.matrizEquacoes[i][j] != outro.matrizEquacoes[i][j])
                    return false;

        return true;
    }

    /**
     * Gera uma representação textual de todo conteúdo do resolvedor de sistemas.
     * Produz e resulta um String representando a matriz de equações do objeto chamante da classe e a quantidade de equações.
     * @return um String contendo representando a matriz de equações e quantidade de equações.
     */
    public String toString()
    {
        String ret = "Quantidade de equações: " + this.qtdEquacoes + "\n" + "Matriz:" + "\n";

        for(int i = 0; i < this.qtdEquacoes; i++)
        {
            ret = ret + "{";

            for (int j = 0; j < this.qtdEquacoes; j++)
                ret = ret + this.matrizEquacoes[i][j] + "; ";

            ret = ret + this.matrizEquacoes[i][this.qtdEquacoes];

            ret = ret + "}" + "\n";
        }

        return ret;
    }

    /**
     * Constroi uma cópia deste ResolvedorDeSistemas.
     * Utiliza o construtor de cópia para gerar uma cópia de this e a retorna.
     * @return a cópia deste ResolvedorDeSistemas como Object.
     */
    public Object clone()
    {
        ResolvedorDeSistemas ret = null;

        try
        {
            ret = new ResolvedorDeSistemas(this);
        }
        catch(Exception ex)
        {} // sei que this nunca vai ser null;

        return ret;
    }
}
