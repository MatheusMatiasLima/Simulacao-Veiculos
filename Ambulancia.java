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
    
    public void verPessoasNaAmbulancia () {
        System.out.println("***Pessoas na ambulancia***");
        for (Pessoa p : pessoas) {
            System.out.println("Nome: " + p.getNome());
        }
    }

}
