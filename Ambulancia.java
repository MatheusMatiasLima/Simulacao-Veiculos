import java.util.Stack;

public class Ambulancia extends Veiculo {
    private Stack<Pessoa> pessoas;

    public Ambulancia(Localizacao localizacao) {

        super(localizacao,"Imagens/ambulancia.png");
        pessoas = new Stack<>();
    }

    public void adicionarPessoa (Pessoa p) {
        pessoas.add(p);
    }
    public Pessoa tirarPessoa () {
        return pessoas.pop();
    }

    public boolean temPessoasNaAmbulancia () {
        if (pessoas.isEmpty()) {
            return false;
        }
        return true;
    }
    
    public void verPessoasNoVeiculo () {
        System.out.println("***Pessoas na ambulancia***");
        for (Pessoa p : pessoas) {
            System.out.println("Nome: " + p.getNome());
        }
    }

}
