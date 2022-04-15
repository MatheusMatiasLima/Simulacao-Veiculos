package simulacao;

import java.awt.Image;
import javax.swing.ImageIcon;
import simulacao.Desenhavel;
import simulacao.Localizacao;

/**
 * Classe que representa um veiculo.
 * @author Grupo
 */
public abstract class Veiculo implements Desenhavel{
    private ControladorVan controladorVan;
    private Localizacao localizacaoAtual;
    private Localizacao localizacaoDestino;
    private Image imagem;

    public Veiculo(Localizacao localizacao, ControladorVan cv) {
        try{
            if(localizacao == null) throw new NullPointerException("Localização Atual null");
            this.localizacaoAtual = localizacao;
            localizacaoDestino = null;
            imagem = new ImageIcon(getClass().getResource("Imagens/veiculo.png")).getImage();
            controladorVan = cv;
        } catch(NullPointerException e){
            System.out.println(e.getMessage()+" em "+this);
        }
    }

    @Override
    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }
    
    @Override
    public Image getImagem(){
        return imagem;
    }

    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        try{
            if(localizacaoDestino == null) {
                throw new NullPointerException();
            }
            this.localizacaoDestino = localizacaoDestino;
        }catch(Exception e){
              System.out.println(e.getMessage()+" em "+this); 
        }
    }
    
    public abstract void pegarPassageiro(Passageiro passageiro);
    
    public void chegouNoDestino(Veiculo veiculo,Passageiro passageiro){
        System.out.println(veiculo + " offloads " + passageiro);
    }
    
    public void limparLocalizacaoDestino(){
        this.localizacaoDestino = null;
    }
    
    @Override
    public void executarAcao(){
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null){
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(localizacaoDestino);
            setLocalizacaoAtual(proximaLocalizacao);
        }
    }
}