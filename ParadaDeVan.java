import java.util.Random;
import java.util.Stack;

public class ParadaDeVan extends Item implements Ambiente{
    private Stack<Pessoa> pessoas;
    private Localizacao estacionamento;

    public ParadaDeVan (Localizacao localizacao, Localizacao estacionamento) {
        super(localizacao, "Imagens/pontoDeVan.jpg");
        this.estacionamento = estacionamento;
        pessoas = new Stack<>();
    }

    @Override
    public void adicionarPessoaAoAmbiente(Pessoa p) {
        Random rand = new Random();
        if (rand.nextInt(20) > 18) {
            pessoas.add(p);
        }
        
    }

    @Override
    public Stack<Pessoa> removerPessoaDoAmbiente() {
        Stack<Pessoa> pessoasSaindoDoPonto = new Stack<>();
        while (!pessoas.empty()) {
            pessoasSaindoDoPonto.add(pessoas.pop());
        }
        return pessoasSaindoDoPonto;
    }

    @Override
    public Localizacao getEstacionamento() {
        return estacionamento;
    }
    
}
