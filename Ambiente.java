import java.util.Stack;

/**
 * @author :Grupo17
 */
public interface Ambiente {
    public void adicionarPessoaAoAmbiente(Pessoa p);
    public Stack<Pessoa> removerPessoaDoAmbiente();
    public Localizacao getEstacionamento();

}
