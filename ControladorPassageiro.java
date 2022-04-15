package simulacao;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Controla os multiplos passageiros
 * @author Grupo
 */
public class ControladorPassageiro implements Controlador{
    private static List<Passageiro> passageiros;
    private Mapa mapa;
    private ControladorVan cv;
    
    private Random rand;
    private static final double PROBABILIDADE_DE_CRIACAO = 0.05;
    
    public ControladorPassageiro(Mapa mapa, ControladorVan cv) {
        try{
            if(mapa == null) throw new NullPointerException("Mapa Atual null");
            passageiros = new LinkedList<>();
            this.mapa = mapa;
            this.cv = cv;
            
            //TESTE COM 1 PASSAGEIRO
            Passageiro passageiro = new Passageiro(new Localizacao(40,1));
            passageiros.add(passageiro);
            mapa.adicionarItem(passageiro);
                
            //criarPassageiros(); <-------------------------TESTE COM 1 PASSAGEIRO
            //setPontoDeEsperaDestino();
        } catch(Exception e){
            System.out.println(e.getMessage()+" em "+this);
        }
    }
    
    public static List<Passageiro> getPassageiros(){
        return passageiros;
    }
    
    public void setPontoDeEsperaDestino(){
        for(Passageiro passageiro: passageiros){
            passageiro.setLocalizacaoDestino(passageiro.getPontoMaisProximo());
        }
    }
    
    public Passageiro emPontoEspera(){
        Passageiro atual;
        for(Passageiro passageiro: passageiros){
            if(passageiro.getLocalizacaoAtual().equals(passageiro.getLocalizacaoDestino())){
                List<PontoEspera> pontos = new LinkedList<>();
                pontos.addAll(ControladorCidade.getPontos());
                for(PontoEspera ponto: pontos){
                    if(ponto.getX() == passageiro.getLocalizacaoAtual().getX() &&ponto.getY() == passageiro.getLocalizacaoAtual().getY()){
                        atual = passageiro;
                        return atual;
                    }
                }
            }
        }
        atual = null;
        return atual;
    }
        
   
    
    private void criarPassageiros() {
        /* TESTE COM 1 PASSAGEIRO
        Random rand = new Random();
        
        if(rand.nextDouble() <= PROBABILIDADE_DE_CRIACAO){
            int a = rand.nextInt(99);
            if(a < 33){
                Passageiro passageiro = new Passageiro(new Localizacao(40,1));
                passageiros.add(passageiro);
                mapa.adicionarItem(passageiro);
                
            }else if(a < 66){
                Passageiro passageiro = new Passageiro(new Localizacao(1,40));
                passageiros.add(passageiro);
                mapa.adicionarItem(passageiro);
                
            }else{
                Passageiro passageiro = new Passageiro(new Localizacao(46,28));
                passageiros.add(passageiro);
                mapa.adicionarItem(passageiro);
            }
            
        }
        */
        //TESTE COM 1 PASSAGEIRO
        Passageiro passageiro = new Passageiro(new Localizacao(40,1));
        passageiros.add(passageiro);
        mapa.adicionarItem(passageiro);
    }

    @Override
    public void executarAcao() {
        
        //criarPassageiros(); <-------------TESTE COM 1 PASSAGEIRO
        setPontoDeEsperaDestino();
        
        Passageiro passageiro = emPontoEspera();
        if (passageiro != null){
            List<PontoEspera> pontos = new LinkedList<>();
            pontos.addAll(ControladorCidade.getPontos());
            for(PontoEspera ponto: pontos){
                if(ponto.getLocal().getX() == passageiro.getLocalizacaoAtual().getX() && ponto.getLocal().getY() == passageiro.getLocalizacaoAtual().getY()){
                    passageiro.setLocalizacaoDestinoViagem(new Localizacao(2,45));
                    ponto.adicionarPassageiroEmEspera(passageiro);
                }
            }
            passageiros.remove(passageiro);
            mapa.removerItem(passageiro);
        }
    }
}
