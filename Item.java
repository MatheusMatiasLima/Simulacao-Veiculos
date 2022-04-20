/*
* Itens pode ser qualquer coisa que tenha no mapa
* Um item tem a sua localização e a sua imagem 
* Um item pode ser uma pessoa, um veiculo, um lugar...
*
* @author Matheus Matias Lima, integrante do grupo 17
*/

import java.awt.Image;
import javax.swing.ImageIcon;

public class Item {

    private Localizacao localizacaoAtual;
    private Image imagem;

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

    public Localizacao getLocalizacaoAtual() {
         return localizacaoAtual;
    }

    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
         this.localizacaoAtual = localizacaoAtual;
    }

    public Image getImagem(){
        return imagem;
    }
    
}
