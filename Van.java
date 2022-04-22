import java.util.Stack;

/**Representa as vans da simulacao, herda de Veiculo.
 *
 * @author Grupo17
 */

public class Van extends Veiculo {
    private int limitePeso;
    private int pesoAtual;


    /**Cria uma van em dada localizacao.
     *
     * @param localizacao localização que a van e instanciada.
     */
    public Van(Localizacao localizacao) {
        super(localizacao,"Imagens/van.png");
        limitePeso = 1200;
        pesoAtual = 0;
    }

    public int getLimitePeso(){return limitePeso;}
    public int getPesoAtual(){return pesoAtual;}

    @Override
    public void adicionarPessoa(Pessoa p){
        if(getPesoAtual() + p.getPeso() > getLimitePeso()){
            System.out.println("Não pode ser adicionada, limite de peso excedido.");
        }else{
            super.inserirPessoaStack(p);
        }
    }
    /**Fornece o nome da subclasse desse objeto.
     *
     * @return String que representa o nome da classe do objeto.
     */
    @Override
    protected String retornarNomeDaSubclasse () {
        return this.getClass().getName();
    }
    

}
