import java.util.ArrayList;
import java.util.Stack;

/*
O parque é o destino de todas as pessoas
as pessoas no parque podem passar mal e ter que ir 
no hospital. Uma ambulancia deve ir buscar a pessoa no hospital.
*/

public class Parque extends Item implements Ambiente {

    private ArrayList<Pessoa> pessoasNoParque;
    private boolean socorroACaminho;
    private Localizacao estacionamento;

    public Parque(Localizacao localizacao) {
        super(localizacao, "Imagens/parque.jpg");

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

    public boolean precisaDeSocorro() {

        for (Pessoa p : pessoasNoParque) {
            if (p.isDoente()) {
                System.out.println(p.getNome() + " precisa de socorro! Enviando sinal de socorro");
                socorroACaminho = true;
                return true;
            }
        }
        return false;
    }
    
    public boolean socorroACaminho () {
        return socorroACaminho;
    }

}
