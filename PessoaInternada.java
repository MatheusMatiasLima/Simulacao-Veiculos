public class PessoaInternada {
    private Pessoa pessoa;
    private int diasInternados;

    public PessoaInternada (Pessoa p) {
        pessoa = p;
        diasInternados = 0;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public int getDiasInternados() {
        return diasInternados;
    }

    public void passarDiaInternado() {
        diasInternados++;
    }

}
