import java.util.ArrayList;
import java.util.Random;
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
    private Buraco buraco;
    private ArrayList<Item> obstaculos;
    
    
    public Simulacao() {
        Random rand = new Random(12345);
        mapa = new Mapa();
        int largura = mapa.getLargura();
        int altura = mapa.getAltura();
        obstaculos = new ArrayList();
        
        veiculo = new Veiculo(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)), "Imagens/veiculo.jpg");//Cria um veiculo em uma posicao aleatoria

        hospital = new Hospital(new Localizacao(5, 5));

        parque = new Parque(new Localizacao(27, 10));

        ambulancia = new Ambulancia(hospital.getEstacionamento());

        //adiciona buracos no mapa
        for(int i = 0; i < 4; i++){
            buraco = new Buraco(new Localizacao(13, 10+i));
            obstaculos.add(buraco);
            mapa.adicionarItem(buraco);
            buraco = new Buraco(new Localizacao(14, 10+i));
            obstaculos.add(buraco);
            mapa.adicionarItem(buraco);
        }
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
        veiculo.executarAcao(obstaculos);
        mapa.adicionarItem(veiculo);
        ambulancia.executarAcao(obstaculos);

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
