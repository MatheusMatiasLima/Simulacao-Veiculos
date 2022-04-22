

/**Representa um mapa com todos os itens que participam da simulacao.
 *
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann and Grupo17
 */
public class Mapa {
    private Item[][] itens;
    private int largura;
    private int altura;
    
    private static final int LARGURA_PADRAO = 35;
    private static final int ALTURA_PADRAO = 35;
    
    /**Cria mapa para alocar itens da simulacao.
     *
     * @param largura: largura da área de simulacao.
     * @param altura: altura da área de simulação.
     */
    public Mapa(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        itens = new Item[altura][largura];
    }
    /**Cria mapa com tamanho padrao.
     */
    public Mapa(){
        this(LARGURA_PADRAO,ALTURA_PADRAO);
    }
    /**Adiciona item na matriz itens de Mapa.
     *
     * @param v: item a ser adicionado.
     */
    public void adicionarItem(Item v){
        itens[v.getLocalizacaoAtual().getX()][v.getLocalizacaoAtual().getY()] = v;
    }
    /**Remove item na matriz itens de Mapa.
     *
     * @param v: item a ser removido.
     */
    public void removerItem(Item v){
        itens[v.getLocalizacaoAtual().getX()][v.getLocalizacaoAtual().getY()] = null;
    }
    /**Atualiza o item passado como referencia no Mapa da simulacao.
     *
     * @param v: item a ser atualizado.
     */
    public void atualizarMapa(Item v){
        removerItem(v);
        adicionarItem(v);
    }
    /**Fornece um dos intes do Mapa segundo a posicao desejada.
     *
     * @param x: posicao x desejada.
     * @param y: posicao y desejada.
     * @return Item que esta na posicao (x, y) buscada.
     */
    public Item getItem(int x, int y){
        return itens[x][y];
    }
    /**Fornece a largura do Mapa.
     *
     * @return Int que representa largura do Mapa.
     */
    public int getLargura() {
        return largura;
    }
    /**Fornece a altura do Mapa.
     *
     * @return Int que representa altura do Mapa.
     */
    public int getAltura() {
        return altura;
    }
    
}
