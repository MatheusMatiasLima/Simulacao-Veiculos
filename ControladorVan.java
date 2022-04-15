package simulacao;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Responsavel por controlar multiplas vans.
 * @author Grupo
 */
public class ControladorVan implements Controlador{
    private List<Veiculo> veiculos;
    private Mapa mapa;
    
    private Map<Veiculo, Passageiro> atribuicoes;

    public ControladorVan(Mapa mapa) {
        try{
            if(mapa == null) throw new NullPointerException("Mapa Atual null");
            veiculos = new LinkedList<>();
            this.mapa = mapa;
            criarVeiculos();
        }catch(NullPointerException e){
            System.out.println(e.getMessage()+" em "+this);
        }
    }
    
    public List<Veiculo> getVeiculos(){
        return veiculos;
    }
    
    private void criarVeiculos(){
        int altura = mapa.getAltura();
        int largura = mapa.getLargura();
        
        for(int i = 0; i < 1; i++){
            Veiculo veiculo = new Van(new Localizacao(3,3),this);
            //veiculo.setLocalizacaoDestino(new Localizacao(49,36));
            veiculos.add(veiculo);
            mapa.adicionarItem(veiculo);
        }
    }
    
    
    /*
    public boolean requisitarColetaParticular(Passageiro passageiro, int numPassageiros){
        
        if(numPassageiros <= 15) {
            Veiculo veiculo = new Van(new Localizacao(3,3),this);
            atribuicoes.put(veiculo, passageiro);
            veiculo.setLocalizacaoDestino(passageiro.getLocalizacaoAtual());
            return true;
        }
        else {
            return false;
        }
    }
    
    public void chegouEmPontoColeta(Veiculo veiculo){
        Passageiro passageiro = atribuicoes.remove(veiculo);
        if(passageiro == null) {
            System.out.println("Passageiro inexistente");
        }
        mapa.removerItem(passageiro);
        veiculo.pegarPassageiro(passageiro);
    }
    */
    
    
    @Override
    public void executarAcao() {
        
    }
}
