import java.util.Stack;

public interface Ambiente {
    public void adicionarPessoaAoAmbiente(Pessoa p);
    public Stack<Pessoa> removerPessoaDoAmbiente();
    public Localizacao getEstacionamento();

}
