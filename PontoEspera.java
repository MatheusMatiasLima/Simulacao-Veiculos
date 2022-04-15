package simulacao;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Grupo
 */
public class PontoEspera extends PontosDeInteresse{
    private  List<Passageiro> passageiros;
    
    public PontoEspera(Localizacao localizacao) {
        super(localizacao);
        passageiros = new LinkedList<>();
    }
    
    public  List<Passageiro> getPassageiros(){
        return passageiros;
    }
    
    public  void adicionarPassageiroEmEspera(Passageiro passageiro){
        passageiros.add(passageiro);
    }
    
    public  void removerPassageiroEmEspera(Passageiro passageiro){
        passageiros.remove(passageiro);
    }
    
    @Override
    public String toString(){
        return "Ponto em " +getLocal() + " contem - " + getPassageiros().size()+ " - passageiros em espera" ;
    }
}