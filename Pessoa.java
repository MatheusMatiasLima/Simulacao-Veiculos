public class Pessoa{
    private String nome;
    private boolean doente = true;
    private boolean idoso = false;


    public Pessoa(String nome) {
        this.nome = nome;
        
    }

    public String getNome() {
        return nome;
    }

    public boolean isDoente() {
        return doente;
    }

    public boolean isIdoso() {
        return idoso;
    }

    public void ficarDoente () {
        doente = true;
    }

    public void curar () {
        doente = false;
    }


    
}
