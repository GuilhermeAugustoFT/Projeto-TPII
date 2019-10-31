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

    public void tirarZerosDaDiagonalPrincipal() throws Exception
    {
        double[] auxPrim = new double[this.qtdEquacoes + 1];
        double[] auxSeg = new double[this.qtdEquacoes + 1];
        boolean ok = true;
        for(int j = 0; j < this.qtdEquacoes + 1; j++)
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
                for (int j = 0; j < this.qtdEquacoes + 1; j++)
                {
                    if (i != this.qtdEquacoes - 1)
                        this.matrizEquacoes[i + 1][j] = auxPrim[j];
                    else
                        this.matrizEquacoes[0][j] = auxPrim[j];
                }

                for (int j = 0; j < this.qtdEquacoes + 1; j++)
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

    public void setMatriz(double[][] matriz, int qtdEquacoes) throws Exception
    {
        if(matriz == null)
            throw new Exception("Matriz inválida");
        if(qtdEquacoes < 2)
            throw new Exception("Quantidade de equações inválida");

        double[][] novaMatriz = new double[qtdEquacoes][qtdEquacoes + 1];
        for(int i = 0; i < qtdEquacoes; i++)
        {
            for(int j = 0; j < qtdEquacoes + 1; j++)
            {
                novaMatriz[i][j] = matriz[i][j];
            }
        }

        this.matrizEquacoes = novaMatriz;
        this.qtdEquacoes = qtdEquacoes;
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

    public int hashCode()
    {
        int ret = 2;

        ret += ret * 13 + new Integer(this.qtdEquacoes).hashCode();

        for(int i = 0; i < qtdEquacoes; i++)
            for(int j = 0; j < qtdEquacoes + 1; j++)
            {
                ret += ret * 13 + new Double(this.matrizEquacoes[i][j]).hashCode();
            }

        for(int i = 0; i < vetorEquacoes.length; i++)
            ret += ret * 13 + new String(this.vetorEquacoes[i]).hashCode();

        return ret;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(this.getClass() != obj.getClass())
            return false;

        OrganizadorDeSistemas outro = (OrganizadorDeSistemas) obj;

        if(outro.qtdEquacoes + 1 != this.qtdEquacoes + 1 || outro.qtdEquacoes != this.qtdEquacoes ||
           this.vetorEquacoes.length != outro.vetorEquacoes.length)
            return false;


        for(int i = 0; i < qtdEquacoes; i++)
            for(int j = 0; j < qtdEquacoes + 1; j++)
            {
               if(this.matrizEquacoes[i][j] != outro.matrizEquacoes[i][j]);
                return false;
            }

        for(int i = 0; i < this.vetorEquacoes.length; i++)
            if(this.vetorEquacoes[i] != outro.vetorEquacoes[i])
                return false;

        return true;
    }

    public String toString ()
    {
        String ret = "";

        for(int i = 0; i < this.qtdEquacoes; i++)
        {
            ret += "| ";
            for (int j = 0; j < this.qtdEquacoes + 1; j++)
                ret += this.matrizEquacoes[i][j] + " ";
            ret+= "|\n";
        }

    return ret;

    }
}
