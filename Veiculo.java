
/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Veiculo extends Item {
    private Localizacao localizacaoDestino;

    //esse construtor vai ser descontinuado, está aqui apenas para tampar buraco
    //public Veiculo (Localizacao localizacao) {
    //    super(localizacao, "Imagens/veiculo.jpg");
    //    localizacaoDestino = null;
    //}

    public Veiculo(Localizacao localizacao, String connectionStringImagem) {
        super(localizacao, connectionStringImagem);
        localizacaoDestino = null;
    }

    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }
    
    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }
    
    public void executarAcao(){
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null){
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(localizacaoDestino);
            setLocalizacaoAtual(proximaLocalizacao);
        }
    }

    public boolean estaEmMovimento () {
        if (getLocalizacaoAtual() != getLocalizacaoDestino()) {
            return true;
        }
        else {
            return false;
        }
    }
}
