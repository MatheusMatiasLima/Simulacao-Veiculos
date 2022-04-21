import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;
/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public abstract class Veiculo extends Item {
    private Localizacao localizacaoDestino;
    private Stack<Pessoa> pessoas;
    private int espera = 15;
    private boolean noSemafaro = false;


    //esse construtor vai ser descontinuado, está aqui apenas para tampar buraco
    //public Veiculo (Localizacao localizacao) {
    //    super(localizacao, "Imagens/veiculo.jpg");
    //    localizacaoDestino = null;
    //}

    public Veiculo(Localizacao localizacao, String connectionStringImagem) {
        super(localizacao, connectionStringImagem);
        pessoas = new Stack<>();
        localizacaoDestino = null;
    }

    protected abstract String retornarNomeDaSubclasse ();

    public void adicionarPessoa (Pessoa p) {
        pessoas.add(p);
    }
    public Pessoa tirarPessoa () {
        return pessoas.pop();
    }

    public boolean temPessoasNoVeiculo () {
        if (pessoas.isEmpty()) {
            return false;
        }
        return true;
    }

    public void verPessoasNoVeiculo () {
        System.out.println("***Pessoas na " + retornarNomeDaSubclasse() + "***" );
        for (Pessoa p : pessoas) {
            System.out.println("Nome: " + p.getNome());
        }
    }

    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }
    
    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }
    

    public void executarAcao() {
        if (!noSemafaro && estaEmMovimento()) {
            Random rand = new Random();
            int sorte = rand.nextInt(100);

            if (sorte > 98) {
                noSemafaro = true;
                //System.out.println("semafaro");
            }
            else {
                Localizacao destino = getLocalizacaoDestino();
                if (destino != null) {
                    Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(localizacaoDestino);
                    setLocalizacaoAtual(proximaLocalizacao);
                }
            }
        }
        
        else if (noSemafaro){
            //System.out.println("espera" + espera);
            espera -= 1;
            if(espera == 0){
                espera = 15;
                noSemafaro = false;
            }
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
