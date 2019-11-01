package LeitorArquivoSistemaEquacao;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * A classe LeitorArquivoSistemaEquacao representa um simples leitor de arquivos texto de sistemas de equação implemementada tendo
 * como base um vetor que armazena as equações não formatadas e um inteiro que armazena a quantidade de equações no vetor.
 * Instâncias desta classe permitem a relização de leituras de arquivos texto de sistemas de equações.
 * Nela encontramos, por exemplo, um método para ler o arquivo, um construtor, equals etc.
 * @author Nícolas Maisonnette Duarte e Guilherme Augusto Felisberto Teixeira.
 * @since 2019.
 */
public class LeitorArquivoSistemaEquacao implements Cloneable //Implementa o clone()
{
    /** Variável inteira representando a quantidade de equações do sistema lido. */
    protected int qtdEquacoes;

    /** Variável de cadeia de caracteres representando as equações não formatadas do sistema lido. */
    protected String[] vetorEquacoes;

    /**
     * Constroi uma nova instância da classe LeitorArquivoSistemaEquacao.
     * Para tanto, não será fornecida a fonte do arquivo, que será passada posteriormente, deixando-nos adotar a quantidade de equações como 0 e resetando o vetor de equações.
     */
    public LeitorArquivoSistemaEquacao()
    {
        qtdEquacoes = 0;
        vetorEquacoes = null;
    }

    /**
     * Constroi uma cópia da instância da classe LeitorArquivoSistemaEquacao dada.
     * Para tanto, deve ser fornecida uma instancia da classe LeitorArquivoSistemaEquacao para ser
     * utilizada como modelo para a construção da nova instância criada.
     * @param modelo a instância da classe LeitorArquivoSistemaEquacao a ser usada como modelo.
     * @throws Exception se o modelo for null.
     */
    public LeitorArquivoSistemaEquacao(LeitorArquivoSistemaEquacao modelo) throws Exception
    {
        if(modelo == null)
            throw new Exception("O modelo era null");

        this.qtdEquacoes = modelo.qtdEquacoes;
        this.vetorEquacoes = modelo.vetorEquacoes.clone();
    }

    /**
     * Lê um arquivo texto, dada a sua fonte.
     * Coloca nas variáveis qtdEquacoes e vetorEquacoes as informações lidas do arquivo texto.
     * @param src a fonte do arquivo a ser lido.
     * @throws Exception se não for fornecida uma fonte ou se o arquivo estiver mal formatado.
     */
    public void ler(String src) throws Exception
    {
        if(src == null || src.equals(""))
            throw new Exception("A fonte do arquivo é inválida");

        try
        {
            BufferedReader arquivo = new BufferedReader(new FileReader(src)); //Cria um leitor de arquivo

            qtdEquacoes = Integer.parseInt(arquivo.readLine()); //Coloca na variável de quantidade a primeira linha convertida para int do arquivo
            if(qtdEquacoes < 2)
                throw new Exception("Número insuficiente de equações");

            vetorEquacoes = new String[qtdEquacoes]; //Instancia o vetor de equações com base na quantidade delas

            for (int i = 0; i < qtdEquacoes; i++)
            {
                vetorEquacoes[i] = arquivo.readLine(); //Percorre o vetor, colocando as linhas dentro dele
            }
        }
        catch(Exception ex) //Se falhar...
        {
            throw new Exception("Erro ao coletar os dados do arquivo");
        }
    }

    /**
     * Coleta as equações não formatadas.
     * Retorna o vetor de equações com as linhas não formatadas.
     * @return o vetor de equações, que é um vetor de String.
     */
    public String[] getLinhas()
    {
        return vetorEquacoes;
    }

    /**
     * Coleta a quantidade equações.
     * Retorna a quantidade de equações lidas do arquivo texto.
     * @return a quantidade de equações, que é um inteiro.
     */
    public int getQtdEquacoes()
    {
        return qtdEquacoes;
    }

    /**
     * Gera uma representação textual de todo conteúdo do leitor de arquivos de sistemas de equação.
     * Produz e resulta um String com todas as equações e a quantidade delas contidas do leitor.
     * @return um String contendo todo conteúdo do leitor de arquivos de sistemas de equação.
     */
    public String toString()
    {
        String ret = "Quantidade: " + this.qtdEquacoes + "\n\n" + "Equações: " + "\n";

        for(int i = 0; i < this.qtdEquacoes - 1; i++) //Percorre o vetor, adicionando cada valor de dentro dele à String de retorno
            ret = ret + this.vetorEquacoes[i] + "\n";

        ret = ret + this.vetorEquacoes[this.qtdEquacoes - 1]; //Adiciona o último sem "\n"

        return ret;
    }

    /**
     * Calcula o código de espalhamento (ou código de hash) de um leitor de arquivos de sistemas de equação.
     * Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
     * hashcode) do leitor de arquivos de sistemas de equação representado pela instância à qual o método for aplicado.
     * @return o código de espalhamento do leitor de arquivos de sistemas de equação chamante do método.
     */
    public int hashCode()
    {
        int ret = 17;

        ret = ret * 17 + new Integer(this.qtdEquacoes).hashCode();
        for(int i = 0; i < this.qtdEquacoes; i++) //Percorre o vetor, coletando o hashCode de cada posição
            ret = ret * 17 + this.vetorEquacoes[i].hashCode();

        if(ret < 0)
            ret = -ret;

        return ret;
    }

    /**
     * Constroi uma cópia deste LeitorArquivoSistemaEquacao.
     * Utiliza o construtor de cópia para gerar uma de this e a retorna.
     * @return a cópia deste LeitorArquivoSistemaEquacao como Object.
     */
    public Object clone()
    {
        LeitorArquivoSistemaEquacao ret = null;
        try
        {
            ret = new LeitorArquivoSistemaEquacao(this);
        }
        catch(Exception ex)
        {}

        return ret;
    }

    /**
     * Verifica a igualdade entre dois LeitorArquivoSistemaEquacao.
     * Verifica se o Object fornecido como parâmetro representa um
     * LeitorArquivoSistemaEquacao igual àquele representado pela instância à qual este
     * método for aplicado, resultando true em caso afirmativo,
     * ou false, caso contrário.
     * @param  obj o objeto a ser comparado com a instância à qual esse método
     * for aplicado.
     * @return true, caso o Object fornecido ao método e a instância chamante do
     * método representarem LeitorArquivoSistemaEquacao iguais, ou false, caso contrário.
     */
    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;

        if(obj.getClass() != this.getClass())
            return false;

        if(this == obj)
            return true;

        LeitorArquivoSistemaEquacao modelo = (LeitorArquivoSistemaEquacao)obj;
        if(this.qtdEquacoes != modelo.qtdEquacoes)
            return false;

        for(int i = 0; i < this.qtdEquacoes; i++) //Verifica se todos os valores são iguais, levando em conta a posição de cada equação não formatada
        {
            if(!this.vetorEquacoes[i].equals(modelo.vetorEquacoes[i]))
                return false;
        }

        return true; //Último caso
    }
}
