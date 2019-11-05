package verificadorDeSistemas;

/**
 * A classe VerificadorDeSistemas é a classe que verificará se é possível resolver o sistema de equações fornecido pelo usuário.
 * Instâncias desta classe verificam se o sistema definido pela matriz é possível de ser resolvido.
 * Nela encontramos, por exemplo, um método para verificar se é possível resolver o sistema de equações, um construtor, equals etc.
 * @author Nícolas Maisonnette Duarte e Guilherme Augusto Felisberto Teixeira.
 * @since 2019.
 */
public class VerificadorDeSistemas implements Cloneable
{
    /** Matriz de double onde os coeficientes das equações serão armazanados. */
    protected double[][] matrizEquacoes;

    /** Variável inteira representando a quantidade de equações do sistema lido. */
    protected int qtdEquacoes;

    /**
     * Constroi uma nova instância da classe VerificadorDeSistemas.
     * @param matrizEquacoes matriz que possui os valores dos coeficientes do sistema.
     * @param qtdEquacoes inteiro cujo valor representa a quantidade de equações do sistema.
     * @throws Exception se a matriz de equações for null, ou se a quantidade de equações for inválida.
     */
    public VerificadorDeSistemas(double[][] matrizEquacoes, int qtdEquacoes) throws Exception
    {
        if(matrizEquacoes == null)
            throw new Exception("A matriz era null");
        if(qtdEquacoes < 2)
            throw new Exception("A quantidade de equações é inválida");

        this.matrizEquacoes = matrizEquacoes.clone();
        this.qtdEquacoes = qtdEquacoes;
    }

    /**
     * Constroi uma cópia da instância da classe VerificadorDeSistemas dada.
     * Para tanto, deve ser fornecida uma instancia da classe VerificadorDeSistemas para ser
     * utilizada como modelo para a construção da nova instância criada.
     * @param modelo a instância da classe VerificadorDeSistemas a ser usada como modelo.
     * @throws Exception se o modelo for null.
     */
    public VerificadorDeSistemas(VerificadorDeSistemas modelo) throws Exception
    {
        if(modelo == null)
            throw new Exception("O modelo era null");

        this.matrizEquacoes = modelo.matrizEquacoes.clone();
        this.qtdEquacoes = modelo.qtdEquacoes;
    }

    /**
     * Coleta a quantidade de equações.
     * Retorna a quantidade de equações presentes na matriz da classe.
     * @return a quantidade de equações, que é um inteiro.
     */
    public int getQtdEquacoes()
    {
        return this.qtdEquacoes;
    }

    /**
     * Coleta a matriz.
     * Retorna a matriz da classe.
     * @return a matriz da classe, que é do tipo double.
     */
    public double[][] getMatriz()
    {
        return this.matrizEquacoes;
    }

    /**
     * Verifica se é possível resolver o sistema de equações.
     *
     * @throws Exception se o sistema de equações for impossível de ser resolvido.
     */
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

    /**
     * Verifica a igualdade entre dois VerificadorDeSistemas.
     * Verifica se o Object fornecido como parâmetro representa um
     * VerificadorDeSistemas igual àquele representado pela instância à qual este
     * método for aplicado, resultando true em caso afirmativo,
     * ou false, caso contrário.
     * @param  obj o objeto a ser comparado com a instância à qual esse método
     * for aplicado.
     * @return true, caso o Object fornecido ao método e a instância chamante do
     * método representarem VerificadorDeSistemas iguais, ou false, caso contrário.
     */
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(this.getClass() != obj.getClass())
            return false;

        VerificadorDeSistemas outro = (VerificadorDeSistemas) obj;

        if(this.qtdEquacoes != outro.qtdEquacoes)
            return false;

        for(int i = 0; i < this.qtdEquacoes; i++)
            for(int j = 0; i < this.qtdEquacoes + 1; j++)
                if(this.matrizEquacoes[i][j] != outro.matrizEquacoes[i][j])
                    return false;

        return true;
    }

    /**
     * Calcula o código de espalhamento (ou código de hash) de um Verificador de sistemas de equação.
     * Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
     * hashcode) do Verificador de sistemas de equação representado pela instância à qual o método for aplicado.
     * @return o código de espalhamento do leitor de arquivos de sistemas de equação chamante do método.
     */
    public int hashcode()
    {
        int ret = 2;

        ret = ret * 13 + new Integer(this.qtdEquacoes).hashCode();

        for(int i = 0; i < this.qtdEquacoes; i ++)
            for (int j = 0; j < this.qtdEquacoes + 1; j++)
                ret = ret * 13 + new Double(this.matrizEquacoes[i][j]).hashCode();

        if(ret < 0)
            return -ret;
        return ret;
    }

    /**
     * Gera uma representação textual de todo conteúdo do verificador de sistemas.
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

            ret = ret + "}";
        }

        return ret;
    }

    /**
     * Constroi uma cópia deste VerificadorDeSistemas.
     * Utiliza o construtor de cópia para gerar uma cópia de this e a retorna.
     * @return a cópia deste LeitorArquivoSistemaEquacao como Object.
     */
    public Object clone()
    {
        VerificadorDeSistemas ret = null;
        try
        {
            ret = new VerificadorDeSistemas(this);
        }
        catch(Exception ex)
        {}

        return ret;
    }


}