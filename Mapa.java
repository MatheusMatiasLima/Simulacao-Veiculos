package simulacao;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Representa um mapa com todos os itens que participam da simulacao
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann and Grupo
 */
public class Mapa {
    //modificado para lista ao inves de matriz
    private List<Item> itens;
    private int largura;
    private int altura;
    
    private static final int LARGURA_PADRAO = 50;
    private static final int ALTURA_PADRAO = 50;
    
    /**
     * Cria mapa para alocar itens da simulacao.
     * @param largura: largura da área de simulacao.
     * @param altura: altura da área de simulação.
     */
    public Mapa(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        //itens = new Item[altura][largura];
        itens = new LinkedList<>();
    }
    /**
     * Cria mapa com tamanho padrao.
     */
    public Mapa(){
        this(LARGURA_PADRAO,ALTURA_PADRAO);
    }
    
    public Iterator<Item> getItem()
    {
        return itens.iterator();
    }
    
    public void adicionarItem(Item item)
    {
        if(itens.contains(item)) {
            throw new IllegalArgumentException(
                item + " ja existe no mapa.");
        }
        itens.add(item);
    }

    public void removerItem(Item item)
    {
        itens.remove(item);
    }
    public void atualizarMapa(Item v){
        removerItem(v);
        adicionarItem(v);
    }
    
    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

}
