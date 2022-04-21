import java.util.ArrayList;
import java.util.Stack;

/*
O parque é o destino de todas as pessoas
as pessoas no parque podem passar mal e ter que ir 
no hospital. Uma ambulancia deve ir buscar a pessoa e lavar ao hospital.
*/

public class Parque extends Item implements Ambiente {

    private ArrayList<Pessoa> pessoasNoParque;
    private boolean socorroACaminho;
    private Localizacao estacionamento;

    public Parque(Localizacao localizacao) {
        super(localizacao, "Imagens/parque.jpg");
        socorroACaminho = false;
        pessoasNoParque = new ArrayList<>();
        estacionamento = new Localizacao(localizacao.getX() - 1, localizacao.getY());
        
    }
    
    @Override
    public Localizacao getEstacionamento() {
        return estacionamento;
    }

    @Override
    public void adicionarPessoaAoAmbiente(Pessoa p) {
        pessoasNoParque.add(p);
    }

    @Override
    public Stack<Pessoa> removerPessoaDoAmbiente() {
        Stack<Pessoa> pr = new Stack<>();

        for (int i=0; i < pessoasNoParque.size() ; i++) {
            if (pessoasNoParque.get(i).isDoente()) {
                pr.add(pessoasNoParque.remove(i));
            }
        }
        return pr ;
    }

    public void passarTempo () {
        for (Pessoa p : pessoasNoParque) {
            p.viver();
        }
    }


    public boolean precisaDeSocorro() {

        if (socorroACaminho == true) {
            return true;
        }
        else {
            for (Pessoa p : pessoasNoParque) {
                if (p.isDoente()) {
                    System.out.println("Alguem precisa de socorro! Enviando sinal de socorro");
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean socorroACaminho () {
        return socorroACaminho;
    }

    public void setTrueSocorroACaminho () {
        socorroACaminho = true;
    }

    public void setFalseSocorroACaminho () {
        socorroACaminho = false;
    }

}
