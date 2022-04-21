import java.util.ArrayList;
import java.util.Stack;

/**
 * Representa os veiculos da simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public abstract class Veiculo extends Item {
    private Localizacao localizacaoDestino;
    private Stack<Pessoa> pessoas;


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

    public abstract void adicionarPessoa (Pessoa p);

    public void inserirPessoaStack(Pessoa p){
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
    
    public void executarAcao(ArrayList<Item> obstaculos){
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null){
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(localizacaoDestino,obstaculos);
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
