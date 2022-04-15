package simulacao;

/**
 * Classe abstrata com a Localização dos pontos de interesse da simulação.
 * Ruas, Pontos de Espera, etc.
 * @author Grupo
 */
public abstract class PontosDeInteresse {
    public Localizacao localizacao;
    
    public PontosDeInteresse(Localizacao localizacao){
       this.localizacao = localizacao; 
    }
    
    public Localizacao getLocal(){
        return this.localizacao;
    }
    
    public int getX(){
        return this.localizacao.getX();
    }
    
    public int getY(){
        return this.localizacao.getY();
    }
}
