import java.util.Random;

public class Pessoa{
    private String nome;
    private boolean doente = false;
    private int idade;
    private String cpf;
    private int peso; //Em Kg


    public Pessoa(String nome, int idade, String cpf, int peso) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.peso = peso;
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

    public int getIdade(){return idade;}

    public int getPeso(){return peso;}
    
    public String getCpf(){return cpf;}

    public boolean isIdoso() {
        if(getIdade()>=65){
            return true;
        }
        return false;
    }

    public void ficarDoente () {
        doente = true;
    }

    public void curar () {
        doente = false;
    }

}
