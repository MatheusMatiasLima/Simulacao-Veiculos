import java.util.ArrayList;
import java.util.Stack;

/**Representa os veiculos da simulacao, herda de Item.
 *
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann and Grupo17
 */
public abstract class Veiculo extends Item {
    private Localizacao localizacaoDestino;
    private Stack<Pessoa> pessoas;


    //esse construtor vai ser descontinuado, está aqui apenas para tampar buraco
    //public Veiculo (Localizacao localizacao) {
    //    super(localizacao, "Imagens/veiculo.jpg");
    //    localizacaoDestino = null;
    //}
    /**Cria veiculo para ser utilizado na animacao
     *
     * @param localizacao localizacao que o veiculo e criada
     * @param connectionStringImagem imagem do veiculo
     */
    public Veiculo(Localizacao localizacao, String connectionStringImagem) {
        super(localizacao, connectionStringImagem);
        pessoas = new Stack<>();
        localizacaoDestino = null;
    }
    /**Retorna o nome do veiculo
     *
     * @return String que representa o nome da classe do veiculo
     */
    protected abstract String retornarNomeDaSubclasse ();

    public void inserirPessoaStack(Pessoa p){
        pessoas.add(p);
    }

    /**Adiciona pessoa no veiculo
     *
     * @param p pessoa que esta entrando no veiculo
     */
    public void adicionarPessoa (Pessoa p) {
        pessoas.add(p);
    }

    /**Retira a pessoa de dentro do veiculo na simulacao.
     *
     * @return Pessoa que esta saindo do veiculo para seu destino
     */
    public Pessoa tirarPessoa () {
        return pessoas.pop();
    }

    /**Check para ver se o veiculo possui pessoas nele
     *
     * @return booelan Verdadeira se possuir pessoas, False se nao
     */
    public boolean temPessoasNoVeiculo () {
        if (pessoas.isEmpty()) {
            return false;
        }
        return true;
    }

    /**Imprime no terminal as pessoas que estão dentro do veiculo
     *
     */
    public void verPessoasNoVeiculo () {
        System.out.println("***Pessoas na " + retornarNomeDaSubclasse() + "***" );
        for (Pessoa p : pessoas) {
            System.out.println("Nome: " + p.getNome());
        }
    }

    /**Retorna a localizacao destino do veiculo
     *
     * @return Localizacao destino do veiculo
     */
    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }

    /**Passa uma localizacao para ser a localizacao de destino do veiculo
     *
     * @param localizacaoDestino Localizacao que sera o destino do veiculo
     */
    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }

    /**Executa a acao do veiculo, levando em conta os obstaculos que existe no mapa
     *
     * @param obstaculos Array de obstaculos da simulacao
     */
    public void executarAcao(ArrayList<Item> obstaculos){
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null){
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(localizacaoDestino,obstaculos);
            setLocalizacaoAtual(proximaLocalizacao);
        }
    }

    /**Retorna se o veiculo esta se movendo
     *
     * @return booelean Verdadeiro que representa que o veiculo esta se movimentando e Falso se esta parado
     */
    public boolean estaEmMovimento () {
        if (getLocalizacaoAtual() != getLocalizacaoDestino()) {
            return true;
        }
        else {
            return false;
        }
    }
}
