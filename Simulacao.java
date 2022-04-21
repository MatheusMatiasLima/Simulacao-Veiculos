import java.util.Random;
import java.util.Stack;
/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Simulacao {
   
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;

    private Hospital hospital;
    private Veiculo veiculo;
    private Parque parque;
    private Ambulancia ambulancia;
    
    public Simulacao() {
        Random rand = new Random(12345);
        mapa = new Mapa();
        int largura = mapa.getLargura();
        int altura = mapa.getAltura();
        
        veiculo = new Veiculo(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)), "Imagens/veiculo.jpg");//Cria um veiculo em uma posicao aleatoria

        hospital = new Hospital(new Localizacao(5, 5));

        parque = new Parque(new Localizacao(27, 10));

        ambulancia = new Ambulancia(hospital.getEstacionamento());

        //adicionando pessoa matheus doente ao parque
        parque.adicionarPessoaAoAmbiente(new Pessoa("Matheus"));
        
        veiculo.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));//Define a posicao destino aleatoriamente
        ambulancia.setLocalizacaoDestino(parque.getEstacionamento());


        mapa.adicionarItem(veiculo); 
        mapa.adicionarItem(hospital);
        mapa.adicionarItem(ambulancia);
        mapa.adicionarItem(parque);

        
        janelaSimulacao = new JanelaSimulacao(mapa);
    }
    





    public void executarSimulacao(int numPassos){
        janelaSimulacao.executarAcao();
        for (int i = 0; i < numPassos; i++) {
            executarUmPasso();
            esperar(100);
        }        
    }

    private void executarUmPasso() {
        mapa.removerItem(veiculo);
        veiculo.executarAcao();
        mapa.adicionarItem(veiculo);
        ambulancia.executarAcao();

        if (ambulancia.getLocalizacaoAtual().equals(ambulancia.getLocalizacaoDestino())) {
            ambulancia.setLocalizacaoDestino(hospital.getEstacionamento());
        }



        janelaSimulacao.executarAcao();
    }
    
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    
}
