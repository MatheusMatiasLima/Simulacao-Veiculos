import java.util.Stack;

/*
* A van é o nosso veiculo principal,
* é ela que vai levar as pessoas para algum lugar
* @author Matheus Matias Lima: integrante do grupo 17
*/

public class Van extends Veiculo {
    private int limitePeso;
    private int pesoAtual;

    public Van(Localizacao localizacao) {
        super(localizacao,"Imagens/van.png");
        //TODO Auto-generated constructor stub
        limitePeso = 1200;
        pesoAtual = 0;
    }

    public int getLimitePeso(){return limitePeso;}
    public int getPesoAtual(){return pesoAtual;}

    @Override
    public void adicionarPessoa(Pessoa p){
        if(getPesoAtual() + p.getPeso() > getLimitePeso()){
            System.out.println("Não pode ser adicionada, limite de peso excedido.");
        }else{
            super.inserirPessoaStack(p);
        }
    }

    @Override
    protected String retornarNomeDaSubclasse () {
        return this.getClass().getName();
    }
    

}
