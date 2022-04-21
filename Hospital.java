import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/*
*  hospital é um lugar estatico
*  na localizacao do hospital podem nascer ambulancias
*  @author Matheus Matias Lima, integrante do grupo 17
*/

public class Hospital extends Item implements Ambiente {

    private ArrayList<PessoaInternada> pessoasInternadas;
    private Localizacao estacionamento;

    public Hospital (Localizacao localizacao) {
        super(localizacao, "Imagens/hospital.png");
        pessoasInternadas = new ArrayList<>();
        estacionamento = new Localizacao(localizacao.getX() + 1, localizacao.getY());
    }

    @Override
    public Localizacao getEstacionamento() {
        return estacionamento;
    }

    @Override
    public void adicionarPessoaAoAmbiente(Pessoa p) {
        pessoasInternadas.add(new PessoaInternada(p));
        
    }

    @Override
    public Stack<Pessoa> removerPessoaDoAmbiente() {
        Stack<Pessoa> pessoasQueIraoEmbora = new Stack<>();

        for (int i=0; i < pessoasInternadas.size(); i++) {
            if ( !(pessoasInternadas.get(i).getPessoa().isDoente()) ) { //se não está doente
                PessoaInternada pi = pessoasInternadas.remove(i);
                pessoasQueIraoEmbora.add(pi.getPessoa());
            }
        }
        return pessoasQueIraoEmbora;
    }

    public void realizarConsultasEmPacientes () {
        for (PessoaInternada pi : pessoasInternadas) {
            verificarEstadoDaPessoa(pi);
        }
    }

    public void verificarEstadoDaPessoa (PessoaInternada paciente) {
        Random roletaDaVida = new Random();

        if (paciente.getDiasInternados() > 3) { //morreu

            for (int i=0; i < pessoasInternadas.size() ; i++) { // remover das pessoas internadas
                if (pessoasInternadas.get(i).getPessoa().getNome().equals(paciente.getPessoa().getNome())) {
                    pessoasInternadas.remove(i);
                }
            }
        }
        else if (paciente.getPessoa().isIdoso()) {

            if (roletaDaVida.nextInt(10) >= 7) { //se ele tirar 7, 8 ou 9 ele esta curado
                paciente.getPessoa().curar();
            }
            else {
                paciente.passarDiaInternado();
            }
        }
        else { //não é idoso
            if (roletaDaVida.nextInt(10) >= 4) { //se ele tirar 4 ou mais ele esta curado
                paciente.getPessoa().curar();
            }
            else {
                paciente.passarDiaInternado();
            }
        }
    }





    



}
