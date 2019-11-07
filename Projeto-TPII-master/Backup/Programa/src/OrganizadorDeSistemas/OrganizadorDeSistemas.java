package organizadorDeSistemas;

import java.util.*;

/**
 * A classe OrganizadorDeSistemas representa um organizador de equações lidas de um arquivo texto, tendo
 * como base uma matriz que armazena os coeficientes das equações anteriormente armazenados pela classe LeitorArquivoSistemaEquacao.java no vetorEquacoes e um número inteiro de quantidade de equações presentes.
 * Instâncias desta classe permitem que valores lidos de um arquivo texto sejam armazenados e organizados em uma Matriz.
 * Nela encontramos, por exemplo, um método para montar a Matriz, tirar os zeros da diagonal principal, um construtor, equals etc.
 * @author Nícolas Maisonnette Duarte e Guilherme Augusto Felisberto Teixeira.
 * @since 2019.
 */

public class OrganizadorDeSistemas implements Cloneable
{
    /** Matriz de double onde os coeficientes das equações serão armazanados.*/
    protected double[][] matrizEquacoes;

    /** Vetor de String onde as equações não formatadas são armazenadas.*/
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

        this.qtdEquacoes = qtdEquacoes;

        this.vetorEquacoes = new String[this.qtdEquacoes];
        for(int i = 0; i < this.qtdEquacoes; i++) //Clona o vetor
        {
            this.vetorEquacoes[i] = new String(vetorEquacoes[i]);
        }

        this.matrizEquacoes = new double[this.qtdEquacoes][this.getQtdColunas()];
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

        this.vetorEquacoes = new String[this.qtdEquacoes];
        for(int i = 0; i < this.qtdEquacoes; i++) //Clona o vetor
        {
            this.vetorEquacoes[i] = new String(modelo.vetorEquacoes[i]);
        }

        this.matrizEquacoes = new double[this.qtdEquacoes][this.getQtdColunas()];
        for(int i = 0; i < this.qtdEquacoes; i++) //Clona a matriz
        {
            for(int j = 0; j < this.getQtdColunas(); j++)
            {
                this.matrizEquacoes[i][j] = modelo.matrizEquacoes[i][j];
            }
        }
    }

    /**
     * Monta a matriz com os coeficientes das equações.
     * Cria uma instância da matriz de equações e, a cada linha dessa matriz, preenche suas colunas com os valores dos coeficientes;
     * utiliza a classe StringTokenizer para auxiliar "quebrando" a string em caracteres.
     * @throws Exception se ocorrer algum erro na montagem da matriz.
     * @return a matriz montada.
     */
    public double[][] montarMatriz() throws Exception
    {
        try
        {
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

        return this.matrizEquacoes;
    }

    /**
     * Tira os zeros da diagonal principal da matriz.
     * Cria as instâncias de duas matrizes auxiliares e um boolean, que auxiliarão no decorrer da função;
     * primeiramente, o método verifica se a matriz já está sem zeros na diagonal principal;
     * se possui zeros na diagonal principal, o método desloca cada linha da matriz para a linha de baixo,
     * visando eliminar esses zeros da diagonal principal.
     * @throws Exception se o método não conseguir tirar os zeros da diagonal principal.
     * @return a matriz sem os zeros na diagonal principal.
     */
    public double[][] tirarZerosDaDiagonalPrincipal() throws Exception
    {
        double[] auxPrim = new double[this.getQtdColunas()];
        double[] auxSeg = new double[this.getQtdColunas()];
        double[] auxTrans = new double[this.getQtdColunas()];
        boolean ok = true;

        for (int i = 0; i < this.qtdEquacoes; i++) //Quantas tentativas
        {
            for(int k = 0; k < this.qtdEquacoes; k++) //Teste para ver se há 0 na diagonal principal
            {
                if(this.matrizEquacoes[k][k] == 0)
                {
                    ok = false;
                    break;
                }
                ok = true;
            }
            if(!ok) //Se houver 0 na diagonal principal
            {
                for (int w = 0; w < this.qtdEquacoes - 1; w++) //Linhas
                {
                    if (w == 0) //Primeira linha
                    {
                        for (int j = 0; j < this.getQtdColunas(); j++)
                        {
                            auxPrim[j] = this.matrizEquacoes[this.qtdEquacoes - 1][j]; //Pega o último
                            auxSeg[j] = this.matrizEquacoes[w][j];

                            this.matrizEquacoes[w][j] = auxPrim[j];

                            auxPrim[j] = this.matrizEquacoes[w + 1][j]; //Guarda os valores que serão apagados por auxSeg

                            this.matrizEquacoes[w + 1][j] = auxSeg[j]; //Valor da próxima linha
                        }
                    }
                    else
                    {
                        if (w != this.qtdEquacoes - 1) //Se não for o último
                        {
                            for (int j = 0; j < this.getQtdColunas(); j++)
                            {
                                this.matrizEquacoes[w][j] = auxSeg[j]; //Valor da antiga próxima linha, que agora é esta

                                auxSeg[j] = auxPrim[j]; //Atualiza o secundário
                                auxPrim[j] = this.matrizEquacoes[w + 1][j]; //Atualiza o primário para guardar os valores que serão apagados por auxSeg

                                this.matrizEquacoes[w + 1][j] = auxSeg[j]; //Valor da próxima linha
                            }
                        }
                        else
                        {
                            for (int j = 0; j < this.getQtdColunas(); j++)
                            {
                                auxTrans[j] = auxSeg[j]; //Permite que o primário receba o secundário não alterado

                                auxSeg[j] = auxPrim[j];
                                auxPrim[j] = auxTrans[j];

                                this.matrizEquacoes[w][j] = auxPrim[j];
                                this.matrizEquacoes[w + 1][j] = auxSeg[j];
                            }
                        }
                    }
                }
            }
            else
                break; //Para o loop principal
        }
        if (!ok)
            throw new Exception("Sistema impossível de se resolver"); //Se terminar o for e ainda não tiver tirado os zeros da diagonal principal

        return this.matrizEquacoes; //Retorna a matriz pronta
    }

    /**
     * Cria e retorna a quantidade de colunas.
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
            ret = ret * 13 + this.vetorEquacoes[i].hashCode();

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
            for(int j = 0; j < this.getQtdColunas(); j++)
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
     * Gera uma representação textual de todo conteúdo do organizador de sistemas.
     * Produz e resulta um String representando o objeto chamante da classe.
     * @return um String contendo representando o objeto chamante da classe.
     */
    public String toString ()
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
        ret = ret + "\n" + "Vetor de Equações:" + "\n";

        for(int i = 0; i < this.qtdEquacoes - 1; i++) //Percorre o vetor, adicionando cada valor de dentro dele à String de retorno
            ret = ret + this.vetorEquacoes[i] + "\n";

        ret = ret + this.vetorEquacoes[this.qtdEquacoes - 1]; //Adiciona o último sem "\n"



		return ret;
    }

    /**
     * Constroi uma cópia deste OrganizadorDeSistemas.
     * Utiliza o construtor de cópia para gerar uma cópia de this e a retorna.
     * @return a cópia deste OrganizadorDeSistemas como Object.
     */
    public Object clone()
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
