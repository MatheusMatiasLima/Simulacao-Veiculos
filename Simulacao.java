import java.security.spec.MGF1ParameterSpec;
import java.util.ArrayList;
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
    private Parque parque;
    private Ambulancia ambulancia;
    private Buraco buraco;
    private ArrayList<Item> obstaculos;
    private Van vanDoHospital;
    private Van vanDoPonto;
    private PostoDeCombustivel postoDeCombustivel;

    private ArrayList<ParadaDeVan> paradasDeVan;


    public Simulacao() {
        Random rand = new Random(12345);
        mapa = new Mapa();

        obstaculos = new ArrayList();

        paradasDeVan = new ArrayList<>();

        paradasDeVan.add(new ParadaDeVan(new Localizacao(30, 15), new Localizacao(29, 15)));
        paradasDeVan.add(new ParadaDeVan(new Localizacao(23, 19), new Localizacao(24, 19)));
        
        postoDeCombustivel = new PostoDeCombustivel(new Localizacao(31, 30),new Localizacao(30, 30));

        hospital = new Hospital(new Localizacao(5, 5));
        parque = new Parque(new Localizacao(27, 10));

        ambulancia = new Ambulancia(hospital.getEstacionamento());
        vanDoHospital = new Van(hospital.getEstacionamento());

        vanDoPonto = new Van(new Localizacao(30, 30));
        vanDoPonto.setLocalizacaoDestino(new Localizacao(30, 30));
        

        //adiciona buracos no mapa
        for(int i = 0; i < 4; i++){
            buraco = new Buraco(new Localizacao(13, 10+i));
            obstaculos.add(buraco);
            mapa.adicionarItem(buraco);
            buraco = new Buraco(new Localizacao(14, 10+i));
            obstaculos.add(buraco);
            mapa.adicionarItem(buraco);
        }
 
        parque.adicionarPessoaAoAmbiente(new Pessoa("Matheus",20,"12345678912",70));
        parque.adicionarPessoaAoAmbiente(new Pessoa("Jao",20,"12345678912",70));
        parque.adicionarPessoaAoAmbiente(new Pessoa("Edu",20,"12345678912",70));
        parque.adicionarPessoaAoAmbiente(new Pessoa("Dudu",20,"12345678912",70));
        
        //veiculo.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));//Define a posicao destino aleatoriamente
        ambulancia.setLocalizacaoDestino(hospital.getEstacionamento());
        vanDoHospital.setLocalizacaoDestino(hospital.getEstacionamento());

        mapa.adicionarItem(postoDeCombustivel);
        mapa.adicionarItem(hospital);
        mapa.adicionarItem(ambulancia);
        mapa.adicionarItem(vanDoHospital);
        mapa.adicionarItem(parque);
        mapa.adicionarItem(vanDoPonto);
        mapa.adicionarItem(paradasDeVan.get(0));
        mapa.adicionarItem(paradasDeVan.get(1));

        
        janelaSimulacao = new JanelaSimulacao(mapa);
    }

    public void executarSimulacao(int numPassos){
        janelaSimulacao.executarAcao();
        while (true) {
            executarUmPasso();
            esperar(40);
        }        
    }

    private void executarUmPasso() {
        mapa.adicionarItem(hospital);
        mapa.adicionarItem(paradasDeVan.get(0));
        mapa.adicionarItem(paradasDeVan.get(1));
        mapa.adicionarItem(parque);

        mapa.removerItem(ambulancia);
        ambulancia.executarAcao(obstaculos);
        mapa.adicionarItem(ambulancia);

        mapa.removerItem(vanDoHospital);
        vanDoHospital.executarAcao(obstaculos);
        mapa.adicionarItem(vanDoHospital);

        mapa.removerItem(vanDoPonto);
        vanDoPonto.executarAcao(obstaculos);
        mapa.adicionarItem(vanDoPonto);




        realizarPassoDoParque();
        realizarPassoDoHospital();

        if (vanDoPonto.getLocalizacaoAtual().equals(postoDeCombustivel.getEstacionamento()) && !vanDoPonto.estaEmMovimento()) {
            vanDoPonto.setLocalizacaoDestino(paradasDeVan.get(0).getEstacionamento());
        }
        else if (vanDoPonto.getLocalizacaoAtual().equals(paradasDeVan.get(0).getEstacionamento()) && !vanDoPonto.estaEmMovimento()) {
            Stack<Pessoa> pessoasSaindoDoPonto = paradasDeVan.get(0).removerPessoaDoAmbiente();
            while (!pessoasSaindoDoPonto.empty()) {
                vanDoPonto.adicionarPessoa(pessoasSaindoDoPonto.pop());
            }
            vanDoPonto.setLocalizacaoDestino(paradasDeVan.get(1).getEstacionamento());
        }
        else if (vanDoPonto.getLocalizacaoAtual().equals(paradasDeVan.get(1).getEstacionamento()) && !vanDoPonto.estaEmMovimento()) {
            Stack<Pessoa> pessoasSaindoDoPonto = paradasDeVan.get(1).removerPessoaDoAmbiente();
            while (!pessoasSaindoDoPonto.empty()) {
                vanDoPonto.adicionarPessoa(pessoasSaindoDoPonto.pop());
            }
            vanDoPonto.setLocalizacaoDestino(parque.getEstacionamento());
        }
        else if (vanDoPonto.getLocalizacaoAtual().equals(parque.getEstacionamento()) && !vanDoPonto.estaEmMovimento()) {
            while(vanDoPonto.temPessoasNoVeiculo()) {
                parque.adicionarPessoaAoAmbiente(vanDoPonto.tirarPessoa());
            }
            vanDoPonto.setLocalizacaoDestino(new Localizacao(30, 30));
        }

       paradasDeVan.get(0).adicionarPessoaAoAmbiente(new Pessoa("NomeAleatorio",20,"12345678912",70));
       paradasDeVan.get(1).adicionarPessoaAoAmbiente(new Pessoa("NomeAleatorio",20,"12345678912",70));


        janelaSimulacao.executarAcao();
    }

    private void realizarPassoDoHospital() {

        if (ambulancia.getLocalizacaoAtual().equals(hospital.getEstacionamento())) {
            //System.out.println("Ambulancia chegou no hospital");
            while (ambulancia.temPessoasNoVeiculo()) { //esvaziar a ambulancia
                hospital.adicionarPessoaAoAmbiente(ambulancia.tirarPessoa());
            }

        }

        hospital.realizarConsultasEmPacientes ();

        if (hospital.temPessoasQuerendoSair()) {
            if (!vanDoHospital.estaEmMovimento() && vanDoHospital.getLocalizacaoAtual().equals(hospital.getEstacionamento())) {
                Stack<Pessoa> pessoasSaindoDoHospital = hospital.removerPessoaDoAmbiente();
                while (!pessoasSaindoDoHospital.empty()) {
                    vanDoHospital.adicionarPessoa(pessoasSaindoDoHospital.pop());
                }
                vanDoHospital.setLocalizacaoDestino(parque.getEstacionamento());
            }

        }

        if (vanDoHospital.getLocalizacaoAtual().equals(parque .getEstacionamento())) {
            if (vanDoHospital.temPessoasNoVeiculo()) {
                while (vanDoHospital.temPessoasNoVeiculo()) {
                    parque.adicionarPessoaAoAmbiente(vanDoHospital.tirarPessoa());
                }
                vanDoHospital.setLocalizacaoDestino(hospital.getEstacionamento());
            }
        }
        

    }

    private void realizarPassoDoParque () {

        if (parque.precisaDeSocorro()) {

            if (parque.socorroACaminho()) {
    

                if (ambulancia.getLocalizacaoDestino().equals(ambulancia.getLocalizacaoAtual())) {
                    System.out.println("Ambulancia chegou no parque, transferindo pessoas...");

                    Stack<Pessoa> pessoasSaindoDoParque = new Stack<>();
                    pessoasSaindoDoParque = parque.removerPessoaDoAmbiente();
                    while (!pessoasSaindoDoParque.empty()) {

                        ambulancia.adicionarPessoa(pessoasSaindoDoParque.pop());
                    }
                    ambulancia.verPessoasNoVeiculo();
                    ambulancia.setLocalizacaoDestino(hospital.getEstacionamento());
                    parque.setFalseSocorroACaminho();
                }
            }
            
            else {
                System.out.println("Chamando ambulancia");
                ambulancia.setLocalizacaoDestino(parque.getEstacionamento());
                parque.setTrueSocorroACaminho();
            }
        }

        parque.passarTempo();

    }
    
    private void esperar(int milisegundos){
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    
}
