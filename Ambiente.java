import java.util.Stack;

/** Interface para os Ambientes (Hospital, Parque e Parada de Van).
 *
 * @author Grupo17
 */
public interface Ambiente {
    /**Adiciona pessoa ao ambiente.
     *
     * @param p: pessoa a ser adicionada no ambiente
     */
    public void adicionarPessoaAoAmbiente(Pessoa p);
    /**Remove as pessoas do ambiente que serao transportadas.
     *
     * @return Stack<Pessoa> que representa as pessoas que precisam de transporte.
     */
    public Stack<Pessoa> removerPessoaDoAmbiente();
    /**Fornece a localizacao do estacionamento do ambiente.
     *
     * @return Localizacao que representa o estacionamento de veiculos do ambiente.
     */
    public Localizacao getEstacionamento();

}
