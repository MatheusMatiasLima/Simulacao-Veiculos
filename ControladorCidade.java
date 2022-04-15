package simulacao;

import java.util.LinkedList;
import java.util.List;

/**
 * Controla os multiplos elementos da cidada que não são imagens redesenhaveis
 * @author Grupo
 */
public class ControladorCidade implements Controlador{
    private static List<Rua> ruas; 
    private static List<PontoEspera> pontos;
    //Criar obras
    //private static List<Obra> pbras;
    private Mapa mapa;

    public ControladorCidade (Mapa mapa) {
        try{
            if(mapa == null) throw new NullPointerException("Mapa Atual null");
            ruas = new LinkedList<>();
            pontos = new LinkedList<>();
            this.mapa = mapa;
            criarRuas();
            criarPontos();
        } catch(Exception e){
            System.out.println(e.getMessage()+" em "+this);
        }
    }
    
    public static List<PontoEspera> getPontos(){
        return pontos;
    }
    
    public static List<Rua> getRuas(){
        return ruas;
    }
    
    public void criarPontos(){
        PontoEspera ponto = new PontoEspera(new Localizacao(45,2));
        pontos.add(ponto);
        PontoEspera ponto2 = new PontoEspera(new Localizacao(2,45));
        pontos.add(ponto2);
        PontoEspera ponto3 = new PontoEspera(new Localizacao(48,33));
        pontos.add(ponto3);
    }
    
    public void criarRuas(){
        
        for(int i = 3; i <=49; i++){
            Rua rua = new Rua(new Localizacao(i,3));
            ruas.add(rua);
        }
        
        for(int i = 3; i <=49; i++){
            Rua rua = new Rua(new Localizacao(49,i));
            ruas.add(rua);
        }
        
        for(int i = 3; i <= 49; i++){
            Rua rua = new Rua(new Localizacao(3,i));
            ruas.add(rua);
        }
        
        for(int i = 3; i <= 49; i++){
            Rua rua = new Rua(new Localizacao(i,49));
            ruas.add(rua);
        }
    }

    @Override
    public void executarAcao() {
        
    }
}
