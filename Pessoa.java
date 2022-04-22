import java.util.Random;
/**Representa as pessoas da simulacao.
 *
 * @author Grupo17
 */
public class Pessoa{
    private String nome;
    private boolean doente = false;
    private boolean idoso = false;

    /**Cria uma pessoa com um determinado nome.
     *
     * @param nome: nome da pessoa.
     */
    public Pessoa(String nome) {
        this.nome = nome;
    }

    /**Calcula a chance de a pessoa ficar doente aleatoriamente, se checar essa probabilidade a pessoa fica doente.
     */
    public void viver () {
        if (!doente) {
            Random rand = new Random();
            int sorte = rand.nextInt(100);
            if (sorte > 98 ) {
                System.out.println(nome + " ficou doente, numero: " + sorte);
                ficarDoente();
            }
        }
    }
    /**Retorna o nome da pessoa.
     *
     * @return String que representa o nome da pessoa.
     */
    public String getNome() {
        return nome;
    }
    /**Verifica se a pessoa esta doente.
     *
     * @return Boolean Verdadeiro se a pessoa estiver doente, Falso se não estiver doente.
     */
    public boolean isDoente() {
        return doente;
    }
    /**Verifica se a pessoa e idosa.
     *
     * @return Boolean Verdadeiro se a pessoa for idosa, Falso se não for.
     */
    public boolean isIdoso() {
        return idoso;
    }
    /**Modifica a pessoa para o estado "doente" controlado por um boolean.
     */
    public void ficarDoente () {
        doente = true;
    }
    /**Modifica a pessoa para o estado "não doente" controlando por um boolean.
     */
    public void curar () {
        doente = false;
    }

}
