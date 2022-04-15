package simulacao;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann and Grupo
 */
public class Simulacao {
    //Simulação agora tem uma Lista de itens
    private static List<Item> itens;
    private List<Controlador> controladores;
    private JanelaSimulacao janelaSimulacao;
    private Mapa mapa;
    private ControladorPassageiro controladorPassageiro;
    private ControladorVan controladorVan;
    
    public Simulacao() {
        /**
         * Random rand = new Random(12345);
         * mapa = new Mapa();
         * int largura = mapa.getLargura();
         * int altura = mapa.getAltura();
         * veiculo = new Veiculo(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));//Cria um veiculo em uma posicao aleatoria
         * veiculo.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));//Define a posicao destino aleatoriamente
         * mapa.adicionarItem(veiculo);//Inicializando o mapa com o veículo
         * janelaSimulacao = new JanelaSimulacao(mapa);
         */
        
        //COMENTARIO mudanças 
        Random rand = new Random(12345);
        mapa = new Mapa();
        int largura = mapa.getLargura();
        int altura = mapa.getAltura();
        itens = new LinkedList<>();
        controladores = new LinkedList<>();
        
        controladorVan = new ControladorVan(mapa);
        controladores.add(controladorVan);
        controladorPassageiro = new ControladorPassageiro(mapa,controladorVan);
        controladores.add(controladorPassageiro);
        ControladorCidade controladorCidade = new ControladorCidade(mapa);
        controladores.add(controladorCidade);
        
        //PARA TESTE 1 VAN
        itens.addAll(controladorVan.getVeiculos());
        
        janelaSimulacao = new JanelaSimulacao(mapa);
        
    }
    
    public void removeItem(Item v){
        itens.remove(v);
    }
    
    public void executarSimulacao(int numPassos){
        janelaSimulacao.executarAcao();
        for (int i = 0; i < numPassos; i++) {
            executarUmPasso();
            esperar(60);
        }        
    }

    private void executarUmPasso() {
        for (Controlador controlador:controladores){
            controlador.executarAcao();
        }
        itens.addAll(controladorPassageiro.getPassageiros());
        
        for(Item item:itens){
            if(item instanceof Desenhavel){
                Desenhavel desenhavel = (Desenhavel)item;
                desenhavel.executarAcao();
            }
            janelaSimulacao.executarAcao();
        }
    }
    
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }   
}
