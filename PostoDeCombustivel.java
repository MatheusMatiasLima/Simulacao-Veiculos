import java.util.Stack;

public class PostoDeCombustivel extends Item{
    private Localizacao estacionamento;

    public PostoDeCombustivel(Localizacao localizacao, Localizacao estacionamento) {
        super(localizacao, "Imagens/postoDeCombustivel.jpg");
        this.estacionamento = estacionamento;

    }

    public Localizacao getEstacionamento() {
        return estacionamento;
    }
    
}
