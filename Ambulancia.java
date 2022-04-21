public class Ambulancia extends Veiculo {

    public Ambulancia(Localizacao localizacao) {

        super(localizacao,"Imagens/ambulancia.png");
    }

    @Override
    protected String retornarNomeDaSubclasse () {
        return this.getClass().getName();
    }

    @Override
    public void adicionarPessoa(Pessoa p){
        super.inserirPessoaStack(p);
    }
}
