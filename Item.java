
import java.awt.Image;
import javax.swing.ImageIcon;
/**Um item é um objeto instanciado na simulacao. Ele possui uma localizacao e uma imagem.
 * Um item pode ser uma pessoas, um veiculo, um ambiente, etc.
 *
 * @author Grupo17
 */
public class Item {

    private Localizacao localizacaoAtual;
    private Image imagem;
    /**Cria Item para ser utilizado na na simulacao.
     *
     * @param localizacao localizacao que o veiculo e criada.
     * @param connectionStringImagem imagem do veiculo.
     */
    public Item (Localizacao localizacao, String connectionStringImagem) {

        this.localizacaoAtual = localizacao;
        try {
            imagem = new ImageIcon(getClass().getResource(connectionStringImagem)).getImage();
        }
        catch (Exception e) {
            System.out.println("Erro ao tentar encontra o arquivo: " + connectionStringImagem);
            System.exit(1);
        }

    }
    /**Fornece a localizacao atual do item.
     *
     * @return Localizacao onde o item se encontra.
     */
    public Localizacao getLocalizacaoAtual() {
         return localizacaoAtual;
    }
    /**Atualiza a localizacao do item
     *
     * @param localizacaoAtual localizacao que esta o item.
     */
    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
         this.localizacaoAtual = localizacaoAtual;
    }
    /**Fornece a imagem do item.
     *
     * @return Image imagem do item.
     */
    public Image getImagem(){
        return imagem;
    }
    
}
