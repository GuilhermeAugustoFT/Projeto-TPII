package OrganizadorDeSistemas;

import java.util.*;

/**
 * A classe OrganizadorDeSistemas representa um organizador de equações lidas em um arquivo texto, tendo
 * como base uma matriz que armazena os coeficientes das equações anteriormente armazenados pela classe LeitorArquivoSistemaEquação.
 * Instâncias desta classe permitem que valores lidos de um arquivo texto sejam armazenados e organizados em uma Matriz.
 * Nela encontramos, por exemplo, um método para Montar a Matriz, Tirar os zeros da diagonal principal, um construtor, equals etc.
 * @author Nícolas Maisonnette Duarte e Guilherme Augusto Felisberto Teixeira.
 * @since 2019.
 */

public class OrganizadorDeSistemas implements Cloneable
{
    /** Matriz de Double onde os coeficientes das equações serão armazanados.*/
    protected double[][] matrizEquacoes;

    /** Vetor de String onde os coeficientes das equações são armazenados.*/
    protected String[] vetorEquacoes;

    /** Variável inteira representando a quantidade de equações do sistema lido. */
    protected int qtdEquacoes;

    /**
     * Constroi uma nova instância da classe OrganizadorDeSistemas.
     * @param vetorEquacoes vetor que possui os coeficientes lidos de um arquivo texto.
     * @param qtdEquacoes inteiro cujo valor representa a quantidade de equações do sistema.
     * @throws Exception se o vetor de equações for nulo, ou se a quantidade de equações for inválida.
     */
    public OrganizadorDeSistemas(String[] vetorEquacoes, int qtdEquacoes) throws Exception
    {
        if(vetorEquacoes == null)
            throw new Exception("vetorEquacoes era null");
        if(qtdEquacoes < 2)
            throw new Exception("A quantidade de equações era inválida");

        this.vetorEquacoes = vetorEquacoes.clone();
        this.qtdEquacoes = qtdEquacoes;
    }

    /**
     * Constroi uma cópia da instância da classe OrganizadorDeSistemas dada.
     * Para tanto, deve ser fornecida uma instancia da classe OrganizadorDeSistemas para ser
     * utilizada como modelo para a construção da nova instância criada.
     * @param modelo a instância da classe OrganizadorDeSistemas a ser usada como modelo.
     * @throws Exception se o modelo for null.
     */
    public OrganizadorDeSistemas(OrganizadorDeSistemas modelo) throws Exception
    {
        if(modelo == null)
            throw new Exception("O modelo era null");

        this.qtdEquacoes = modelo.qtdEquacoes;
        this.vetorEquacoes = modelo.vetorEquacoes.clone();
        this.matrizEquacoes = modelo.matrizEquacoes.clone();
    }

    /**
     * Monta a matriz com os coeficientes das equações.
     * Faz a instância da matriz de equações e a cada linha dessa matriz, preenche suas colunas com os valores dos coeficientes.
     * Utiliza a classe StringTokenizer para auxiliar "quebrando" a string em caracteres.
     * @throws Exception se ocorrer algum erro na montagem da matriz.
     */
    public void montarMatriz() throws Exception
    {
        try
        {
            this.matrizEquacoes = new double[this.qtdEquacoes][this.getQtdColunas()]; // instância da matriz com o tamanho necessário

            for (int i=0; i < this.qtdEquacoes; i++)
            {
                StringTokenizer quebrador = new StringTokenizer(this.vetorEquacoes[i]); // instância do StringTokenizer que irá auxiliar na montagem da matriz

                int j = 0;
                while(quebrador.hasMoreTokens())
                {
                    this.matrizEquacoes[i][j] = Double.parseDouble(quebrador.nextToken()); // armazena em determinada linha e coluna um valor de um coeficente
                    j++;
                }
            }
        }
        catch(Exception ex)
        {
            throw new Exception("Erro ao montar a matriz");
        }
    }

    /**
     * Tira os zeros da diagonal principal da matriz.
     * Faz a instância duas matrizes auxiliares e um boolean, que auxiliarão no decorrer da função.
     * Primeiramente, o método verifica se a matriz já está sem zeros na diagonal principal.
     * Se possui zeros na diagonal principal, o método desloca cada linha da matriz para a linha de baixo,
     * visando eliminar esses zeros da diagonal principal.
     * @throws Exception se o método não conseguir tirar os zeros da diagonal principal.
     */
    public void tirarZerosDaDiagonalPrincipal() throws Exception
    {
        double[] auxPrim = new double[this.getQtdColunas()];
        double[] auxSeg = new double[this.getQtdColunas()];
        boolean ok = true;
        for(int j = 0; j < this.getQtdColunas(); j++)
        {
            if (j < this.qtdEquacoes)
            {
                if (this.matrizEquacoes[j][j] == 0.0)
                {
                    ok = false;
                }
            }


            auxPrim[j] = this.matrizEquacoes[0][j];
            auxSeg[j] = this.matrizEquacoes[1][j];
        }

        if(!ok)
        {
            ok = true;

            for (int i = 0; i < this.qtdEquacoes; i++)
            {
                for (int j = 0; j < this.getQtdColunas(); j++)
                {
                    if (i != this.qtdEquacoes - 1)
                        this.matrizEquacoes[i + 1][j] = auxPrim[j];
                    else
                        this.matrizEquacoes[0][j] = auxPrim[j];
                }

                for (int j = 0; j < this.getQtdColunas(); j++)
                {
                    auxPrim[j] = auxSeg[j];

                    if (i < this.qtdEquacoes - 2)
                        auxSeg[j] = this.matrizEquacoes[i + 2][j];
                }

                for(int j = 0; j < this.qtdEquacoes; j++)
                {
                    if (this.matrizEquacoes[j][j] == 0.0)
                    {
                        ok = false;
                        break;
                    }
                    else
                        ok = true;
                }
                if(ok)
                    break;
            }
            if (!ok)
                throw new Exception("Sistema impossível de se resolver");
        }
    }

    /**
     * Armazena valores na matriz.
     * Armazena valores na matriz a partir de uma matriz já existente.
     * @param matriz a matriz que passará oa valores para matriz de equações da classe.
     * @param qtdEquacoes passa para a classe a quantidade de equações fornecidas pela matriz.
     * @throws Exception se a matriz passada por parâmetro for nula ou se a quantidade de equações for inválida.
     */
    public void setMatriz(double[][] matriz, int qtdEquacoes) throws Exception
    {
        if(matriz == null)
            throw new Exception("Matriz inválida");
        if(qtdEquacoes < 2)
            throw new Exception("Quantidade de equações inválida");

        this.matrizEquacoes = matriz.clone();
        this.qtdEquacoes = qtdEquacoes;
    }

    /**
     * Coleta a matriz.
     * Retorna a matriz da classe.
     * @return a matriz da classe, que é do tipo Double.
     */
    public double[][] getMatriz()
    {
        return this.matrizEquacoes;
    }

    /**
     * Coleta a quantidade de colunas.
     * Retorna a quantidade de colunas presentes na matriz da classe.
     * @return a quantidade de colunas, que é um inteiro.
     */
    protected int getQtdColunas()
    {
        return this.qtdEquacoes + 1;
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
     * Calcula o código de espalhamento (ou código de hash).
     * Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
     * hashcode) da classe OrganizadorDeSistemas representada pela instância à qual o método for aplicado.
     * @return o código de espalhamento do objeto chamante da classe OrganizadorDeSistemas.
     */
    public int hashCode()
    {
        int ret = 2;

        ret = ret * 13 + new Integer(this.qtdEquacoes).hashCode();

        for(int i = 0; i < qtdEquacoes; i++) // percorre a matriz, coletando o valor do hashcode de cada posição
            for (int j = 0; j < this.getQtdColunas(); j++)
                ret = ret * 13 + new Double(this.matrizEquacoes[i][j]).hashCode();
        for(int i = 0; i < vetorEquacoes.length; i++) // percorre o vetor, coletado o valor do hashcode de cada posição
            ret = ret * 13 + new String(this.vetorEquacoes[i]).hashCode();

        if(ret < 0)
            return -ret;
        return ret;
    }

    /**
     * Verifica a igualdade entre dois OrganizadorDeSistemas.
     * Verifica se o Object fornecido como parâmetro representa um
     * OrganizadorDeSistemas igual àquele representado pela instância à qual este
     * método for aplicado, resultando true em caso afirmativo,
     * ou false, caso contrário.
     * @param  obj o objeto a ser comparado com a instância à qual esse método
     * for aplicado.
     * @return true, caso o Object fornecido ao método e a instância chamante do
     * método representarem OrganizadorDeSistemas iguais, ou false, caso contrário.
     */
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(this.getClass() != obj.getClass())
            return false;

        OrganizadorDeSistemas outro = (OrganizadorDeSistemas) obj;

        if(outro.qtdEquacoes != this.qtdEquacoes)
            return false;

        for(int i = 0; i < qtdEquacoes; i++)//Verifica se todos os valores das matrizes são iguais
        {
            for(int j = 0; j < qtdEquacoes + 1; j++)
            {
                if(this.matrizEquacoes[i][j] != outro.matrizEquacoes[i][j])
                    return false;
            }
        }

        for(int i = 0; i < this.vetorEquacoes.length; i++) //Verifica se todos os valores dos vetores são iguais
            if(!this.vetorEquacoes[i].equals(outro.vetorEquacoes[i]))
                return false;

        return true;
    }

    /**
     * Gera uma representação textual de todo conteúdo do Organizador de sistemas.
     * Produz e resulta um String representando a matriz de equações do objeto chamante da classe.
     * @return um String contendo representando a matriz de equações.
     */
    public String toString ()
    {
        String ret = "";

        for(int i = 0; i < this.qtdEquacoes; i++)
        {
            ret = ret + "| ";

            for (int j = 0; j < this.getQtdColunas(); j++)
                ret = ret + this.matrizEquacoes[i][j] + " ";

            ret+= "|";
        }

		return ret;
    }

    /**
     * Constroi uma cópia deste OrganizadorDeSistemas.
     * Utiliza o construtor de cópia para gerar uma cópia de this e a retorna.
     * @return a cópia deste OrganizadorDeSistemas como Object.
     */
    public Object clone ()
    {
        OrganizadorDeSistemas ret = null;
        try
        {
            ret = new OrganizadorDeSistemas(this);
        }
        catch (Exception ex)
        {} // sei que não vai dar erro porque this nunca é null

        return ret;
    }

}
