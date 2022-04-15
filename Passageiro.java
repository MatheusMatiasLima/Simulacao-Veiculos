package simulacao;

import java.awt.Image;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * Classe que representa um passageiro 
 * @author Grupo
 */
public class Passageiro implements Desenhavel{
    
    private Localizacao localizacaoAtual;
    private Localizacao localizacaoDestino;
    private Localizacao localizacaoDestinoViagem;
    private Image imagem;
    private int espera;

    public Passageiro(Localizacao localizacao) {
        try{
            if(localizacao == null) {
                throw new NullPointerException("Localização Atual null");
            }
            this.localizacaoAtual = localizacao;
            this.localizacaoDestino = null;
            localizacaoDestinoViagem = null;
            imagem = new ImageIcon(getClass().getResource("Imagens/passageiro.jpg")).getImage();
        } catch(Exception e){
            System.out.println(e.getMessage()+"here");
        }
    }
    
    public Localizacao getPontoMaisProximo(){
        List<PontoEspera> pontos = new LinkedList<>();
        pontos.addAll(ControladorCidade.getPontos());
        Localizacao novaLocalizacao = null;
        double distancia = 9999;
        
        for(PontoEspera ponto: pontos){
            double a;
            double b = Math.abs(ponto.getX()-localizacaoAtual.getX());
            double c = Math.abs(ponto.getY()-localizacaoAtual.getY());
            
            a = Math.sqrt(Math.pow(b, 2)+Math.pow(c, 2));
            
            if(a<distancia){
                distancia = a;
                novaLocalizacao = new Localizacao(ponto.getX(),ponto.getY());
                
            }
        }
        return novaLocalizacao;
    }
    @Override
    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }
    
    public Localizacao getLocalizacaoDestinoViagem() {
        return localizacaoDestinoViagem;
    }
    
    @Override
    public Image getImagem(){
        return imagem;
    }

    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;  
    }
    
    public void setLocalizacaoDestinoViagem(Localizacao localizacaoDestino) {
        this.localizacaoDestinoViagem = localizacaoDestino;  
    }
    
    @Override
    public void executarAcao(){
        if(espera == 20){
            Localizacao destino = getLocalizacaoDestino();
            if(destino != null && getLocalizacaoDestino()!= getLocalizacaoAtual()){
                Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacaoPessoa(destino);
                setLocalizacaoAtual(proximaLocalizacao);   
            }
            espera = 0;    
        }
        espera++;
    }
    
    @Override
    public String toString(){
        return "Passageiro em " +localizacaoAtual + " vai para " + localizacaoDestino + "e viaja para: " + localizacaoDestinoViagem;
    }
    
}
