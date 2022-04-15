package simulacao;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe que representa uma van, herda de veiculo.
 * @author Grupo
 */
public class Van extends Veiculo{
    
    private List<Passageiro> passageiros;
    private List<Localizacao> destinos;
    private List<PontoEspera> rota;
    private int cont=0;
    
    private static final int ALOCACAO_MAXIMA = 15;
    
    public Van(Localizacao localizacao, ControladorVan cv) {
        super(localizacao,cv);
        passageiros = new LinkedList<>();
        destinos = new LinkedList<>();
        rota = new LinkedList<>();
    }
    
    //TENTAR PASSAR METODO PARA CONTROLADOR
    public void setRota(){
        System.out.println(cont);
        if(rota.size()==0) rota.addAll(ControladorCidade.getPontos());
        setLocalizacaoDeColeta(rota.get(cont).localizacao);
        cont+=1;
        if(cont == 3) cont = 0;
    }
    
    //TENTAR PASSAR METODO PARA CONTROLADOR
    public void setLocalizacaoDeColeta(Localizacao localizacao){
        this.setLocalizacaoDestino(localizacao);
    }
    
    public boolean estaLivre(){
        return this.getLocalizacaoDestino() == null && passageiros.isEmpty();
    }
    
    public boolean estaComPassageiro(){
        return passageiros.isEmpty();
    }
    
    public boolean estaCheio(){
        return passageiros.size() == 15;
    }
    
    @Override
    public void pegarPassageiro(Passageiro passageiro){
        if(!this.estaCheio()){
            passageiros.add(passageiro);
            destinos.add(passageiro.getLocalizacaoDestinoViagem());
            this.limparLocalizacaoDestino();
        }
    }
    
    public void deixarPassageiro(){
        for(Passageiro passageiro: passageiros){
            if(passageiro.getLocalizacaoDestinoViagem().getX() == this.getLocalizacaoAtual().getX() &&
               passageiro.getLocalizacaoDestinoViagem().getY() == this.getLocalizacaoAtual().getY()     ){
                passageiros.remove(passageiro);
                passageiro = null;
            }
        }
    }
    
    @Override
    public void executarAcao(){
        if(estaLivre()){
            setRota();
        }
        Localizacao destino = getLocalizacaoDestino();
        if(getLocalizacaoAtual() == destino){
            this.deixarPassageiro();
            for(PontoEspera ponto: rota){
                if(ponto.localizacao == destino){
                    while(!estaCheio()&& ponto.getPassageiros().size()!=0){
                        for(Passageiro passageiro: ponto.getPassageiros()){
                            pegarPassageiro(passageiro);
                            ponto.removerPassageiroEmEspera(passageiro);
                        }
                    }
                }
            }
            setRota();
        }
        if(destino != null){
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(destino);
            setLocalizacaoAtual(proximaLocalizacao);
        }
    }
}
