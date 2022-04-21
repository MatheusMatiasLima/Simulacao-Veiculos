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
    private Van vanDoHospital;
    
    public Simulacao() {
        Random rand = new Random(12345);
        mapa = new Mapa();
        int largura = mapa.getLargura();
        int altura = mapa.getAltura();
        
        //veiculo = new Veiculo(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)), "Imagens/veiculo.jpg");//Cria um veiculo em uma posicao aleatoria

        hospital = new Hospital(new Localizacao(5, 5));

        //veiculo = new Veiculo(hospital.getEstacionamento(), "Imagens/veiculo.jpg");//Cria um veiculo em uma posicao aleatoria

        parque = new Parque(new Localizacao(27, 10));

        ambulancia = new Ambulancia(hospital.getEstacionamento());
        vanDoHospital = new Van(hospital.getEstacionamento());

       
        parque.adicionarPessoaAoAmbiente(new Pessoa("Matheus"));
        parque.adicionarPessoaAoAmbiente(new Pessoa("Jao"));
        parque.adicionarPessoaAoAmbiente(new Pessoa("Edu"));
        parque.adicionarPessoaAoAmbiente(new Pessoa("Dudu"));
        
        //veiculo.setLocalizacaoDestino(new Localizacao(rand.nextInt(largura),rand.nextInt(altura)));//Define a posicao destino aleatoriamente
        ambulancia.setLocalizacaoDestino(hospital.getEstacionamento());
        vanDoHospital.setLocalizacaoDestino(hospital.getEstacionamento());

        //mapa.adicionarItem(veiculo); 
        mapa.adicionarItem(hospital);
        mapa.adicionarItem(ambulancia);
        mapa.adicionarItem(vanDoHospital);
        mapa.adicionarItem(parque);

        
        janelaSimulacao = new JanelaSimulacao(mapa);
    }
    





    public void executarSimulacao(int numPassos){
        janelaSimulacao.executarAcao();
        while (true) {
            executarUmPasso();
            esperar(200);
        }        
    }

    private void executarUmPasso() {

        mapa.removerItem(ambulancia);
        ambulancia.executarAcao();
        mapa.adicionarItem(ambulancia);

        mapa.removerItem(vanDoHospital);
        vanDoHospital.executarAcao();
        mapa.adicionarItem(vanDoHospital);

        realizarPassoDoParque();
        realizarPassoDoHospital();

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
