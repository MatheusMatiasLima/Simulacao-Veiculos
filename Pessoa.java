import java.util.Random;

public class Pessoa{
    private String nome;
    private boolean doente = false;
    private boolean idoso = false;


    public Pessoa(String nome) {
        this.nome = nome;
    }

    public void viver () {
        if (!doente) {
            Random rand = new Random();
            int sorte = rand.nextInt(100);
            if (sorte > 98 ) {
                System.out.println(nome + " ficou doente, numero: " + sorte);
                ficarDoente();
            }
        }
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
