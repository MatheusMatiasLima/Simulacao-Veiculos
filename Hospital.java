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
    private Stack<Pessoa> pessoasQueIraoSair;
    private Localizacao estacionamento;

    public Hospital (Localizacao localizacao) {
        super(localizacao, "Imagens/hospital.png");
        pessoasInternadas = new ArrayList<>();
        estacionamento = new Localizacao(localizacao.getX() + 1, localizacao.getY());
        pessoasQueIraoSair = new Stack<>();
    }

    @Override
    public Localizacao getEstacionamento() {
        return estacionamento;
    }

    @Override
    public void adicionarPessoaAoAmbiente(Pessoa p) {
        pessoasInternadas.add(new PessoaInternada(p));
        System.out.println(p.getNome() + " esta no hospital");
    }

    @Override
    public Stack<Pessoa> removerPessoaDoAmbiente() {
        return pessoasQueIraoSair;
    }

    public boolean temPessoasQuerendoSair () {
        if (!pessoasQueIraoSair.empty()) {
            return true;
        }
        return false;
    }

    

    public void verTodosOsPacientes () {
        System.out.println("****Pessoas que podem sair*****");
        for (Pessoa pi : pessoasQueIraoSair) {
            System.out.println(pi.getNome());
        }
    }

    public void realizarConsultasEmPacientes () {
        if (pessoasInternadas.size() > 0) {
            // for (PessoaInternada pi : pessoasInternadas) {
            //     verificarEstadoDaPessoa(pi);
            // }
            for (int i=0; i < pessoasInternadas.size(); i++) {
                verificarEstadoDaPessoa(pessoasInternadas.get(i));
            }
        }
    }

    private Pessoa removerPaciente (PessoaInternada paciente) {

        for (int i=0; i < pessoasInternadas.size() ; i++) { // remover das pessoas internadas
            if (pessoasInternadas.get(i).getPessoa().getNome().equals(paciente.getPessoa().getNome())) {
                PessoaInternada pi = pessoasInternadas.remove(i);
                return pi.getPessoa();
            }
        }

        return null;

    }

    private void verificarEstadoDaPessoa (PessoaInternada paciente) {
        Random roletaDaVida = new Random();

        if (paciente.getDiasInternados() > 3) { //morreu

            Pessoa p = removerPaciente(paciente);
            System.out.println(p.getNome() + " morreu");
            return;
        }

        if (paciente.getPessoa().isIdoso()) {

            if (roletaDaVida.nextInt(10) >= 7) { //se ele tirar 7, 8 ou 9 ele esta curado
                paciente.getPessoa().curar();
                Pessoa p = removerPaciente(paciente);
                System.out.println(p.getNome() + " curou");
                pessoasQueIraoSair.add(p);
                
            }
            else {
                paciente.passarDiaInternado();
                System.out.println(paciente.getPessoa().getNome() + " passara mais um dia internado");

            }
        }
        else { //não é idoso
            if (roletaDaVida.nextInt(10) >= 4) { //se ele tirar 4 ou mais ele esta curado
                paciente.getPessoa().curar();
                Pessoa p = removerPaciente(paciente);
                System.out.println(p.getNome() + " curou");
                pessoasQueIraoSair.add(p);
        

            }
            else {
                paciente.passarDiaInternado();
                System.out.println(paciente.getPessoa().getNome() + " passara mais um dia internado");

            }
        }


    }





    



}
