
/**Classe que representa uma Ambulancia, herda de veiculos.
 *
 * @author Grupo17
 */
public class Ambulancia extends Veiculo {

    /**Cria uma ambulância em dada localizacao.
     *
     * @param localizacao localização que a ambulancia e instanciada.
     */
    public Ambulancia(Localizacao localizacao) {

        super(localizacao,"Imagens/ambulancia.png");
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
