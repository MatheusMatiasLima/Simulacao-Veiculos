import java.util.Stack;

/**Representa as vans da simulacao, herda de Veiculo.
 *
 * @author Grupo17
 */

public class Van extends Veiculo {

    /**Cria uma van em dada localizacao.
     *
     * @param localizacao localização que a van e instanciada.
     */
    public Van(Localizacao localizacao) {
        super(localizacao,"Imagens/van.png");
        //TODO Auto-generated constructor stub
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
