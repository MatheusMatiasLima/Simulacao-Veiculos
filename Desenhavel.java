package simulacao;

import java.awt.Image;

/**
 * Interface Itens que possuem imagem.
 * @author Grupo
 */
public interface Desenhavel extends Item{
    
    public Image getImagem();

    public void executarAcao();    
}
