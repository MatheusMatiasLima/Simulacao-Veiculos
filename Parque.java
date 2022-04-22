import java.util.ArrayList;
import java.util.Stack;
/**O parque e um destinos das pessoas. As pessoas que estão no parque podem passar mal
 * e precisar ser transportadas para o hospital. Uma ambulancia busca a pessoa e leva para
 * o hospital, herda de Item e usa a interface Ambiente.
 *
 * @author Grupo17
 */
/*
O parque é o destino de todas as pessoas
as pessoas no parque podem passar mal e ter que ir
no hospital. Uma ambulancia deve ir buscar a pessoa e lavar ao hospital.
*/

public class Parque extends Item implements Ambiente {

    private ArrayList<Pessoa> pessoasNoParque;
    private boolean socorroACaminho;
    private Localizacao estacionamento;
    /**Cria uma pessoa em uma determinada localizaco.
     *
     * @param localizacao: localizacao do parque.
     */
    public Parque(Localizacao localizacao) {
        super(localizacao, "Imagens/parque.jpg");
        socorroACaminho = false;
        pessoasNoParque = new ArrayList<>();
        estacionamento = new Localizacao(localizacao.getX() - 1, localizacao.getY());
        
    }
    /**Fornece a localizacao do estacionamento do parque.
     *
     * @return Localizacao do estacionamento do parque.
     */
    @Override
    public Localizacao getEstacionamento() {
        return estacionamento;
    }
    /**Adiciona uma pessoa ao parque. "Pessoa entrando no parque".
     *
     * @param p: pessoa que entra no parque.
     */
    @Override
    public void adicionarPessoaAoAmbiente(Pessoa p) {
        pessoasNoParque.add(p);
    }

    /**Tira as pessoas doentes do parque para serem transportadas.
     *
     * @return Stack<Pessoa> que representa as pessoas que serão transportadas para o hospital.
     */
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
    /**Modo como a Pessoa age no parque, "passeia" e pode ficar doente.
     *
     */
    public void passarTempo () {
        for (Pessoa p : pessoasNoParque) {
            p.viver();
        }
    }

    /**Informa se tem alguma pessoa precisando de socorro no parque.
     *
     * @return Boolean Verdadeiro caso exista alguma pessoa doente no parque e Falso caso nao.
     */
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
    /**Informa se o socorro do hospital esta a caminho do parque.
     *
     * @return Boolean Verdadeiro caso o socorro esteja a caminho e Falso caso nao.
     */
    public boolean socorroACaminho () {
        return socorroACaminho;
    }
    /**Modifica o estado do parque se o socorro estiver a caminho.
     *
     */
    public void setTrueSocorroACaminho () {
        socorroACaminho = true;
    }
    /**Modifica o estado do parque se o socorro não estiver a caminho.
     *
     */
    public void setFalseSocorroACaminho () {
        socorroACaminho = false;
    }

}
