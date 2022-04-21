import java.util.Stack;

public class Ambulancia extends Veiculo {
    private Stack<Pessoa> pessoas;

    public Ambulancia(Localizacao localizacao) {

        super(localizacao,"Imagens/ambulancia.png");
    }

    public void adicionarPessoa (Pessoa p) {
        System.out.println("Adicionando " + p + " a ambulancia");
        pessoas.add(p);
        
    }
    

}
