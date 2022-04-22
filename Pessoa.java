import java.util.Random;
/**Representa as pessoas da simulacao.
 *
 * @author Grupo17
 */
public class Pessoa{
    private String nome;
    private boolean doente = false;
    private int idade;
    private String cpf;
    private int peso; //Em Kg


    /**Cria uma pessoa com um determinado nome.
     *
     * @param nome: nome da pessoa.
     * @param idade: idade da pessoa.
     * @param cpf: cpf da pessoa.
     * @param peso: peso da pessoa.
     */
    
    public Pessoa(String nome, int idade, String cpf, int peso) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.peso = peso;
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

    public int getIdade(){return idade;}

    public int getPeso(){return peso;}
    
    public String getCpf(){return cpf;}

    /**Verifica se a pessoa e idosa.
     *
     * @return Boolean Verdadeiro se a pessoa for idosa, Falso se não for.
     */
    public boolean isIdoso() {
        if(getIdade()>=65){
            return true;
        }
        return false;
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
