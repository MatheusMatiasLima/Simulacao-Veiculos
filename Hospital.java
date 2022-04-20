/*
*  hospital é um lugar estatico
*  na localizacao do hospital podem nascer ambulancias
*  @author Matheus Matias Lima, integrante do grupo 17
*/

import java.awt.Image;
import javax.swing.ImageIcon;

public class Hospital extends Item {
    private Localizacao localizacaoAtual;
    private Image imagem;

    public Hospital (Localizacao localizacao) {
        super(localizacao, "Imagens/hospital.png");
    }

}
