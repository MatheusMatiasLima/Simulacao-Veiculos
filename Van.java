import java.util.Stack;

/*
* A van é o nosso veiculo principal,
* é ela que vai levar as pessoas para algum lugar
* @author Matheus Matias Lima: integrante do grupo 17
*/

public class Van extends Veiculo {
    private Stack<Pessoa> pessoas;


    public Van(Localizacao localizacao) {
        super(localizacao,"Imagens/van.png");
        pessoas = new Stack<>();
        //TODO Auto-generated constructor stub
    }

    public void adicionarPessoa (Pessoa p) {
        pessoas.add(p);
    }
    public Pessoa tirarPessoa () {
        return pessoas.pop();
    }

    public boolean temPessoasNoVeiculo () {
        if (pessoas.isEmpty()) {
            return false;
        }
        return true;
    }
    

}
