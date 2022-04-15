package simulacao;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Representa uma localização no mapa
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann and Grupo and Sven Woltmann
 */
public class Localizacao {
    private int x;
    private int y;
    private static Random rand = new Random();
    
    
    /**
     * Representa uma localização na cidade
     * @param x Coordenada x: deve ser maior ou igual a 0.
     * @param y Coordenada y: deve ser maior ou igual a 0.
     */
    public Localizacao(int x, int y) {
        try{
            if(x < 0){
                throw new IllegalArgumentException("X possui valor negativo: " + x);
            }
            this.x = x;
            if(y < 0){
                throw new IllegalArgumentException("Y possui valor negativo: " + y);
            }
            this.y = y;
        } catch(Exception e){
            System.out.println(e.getMessage()+"here");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    /**
     * GRUPO:
     * Gera a localizacao para se mover visando alcançar o destino levando em consideração as ruas da cidade.
     * Gera movimento para os veiculos nas ruas e somente nas ruas.
     * @param localizacaoDestino: localizacao que se deseja alcancar.
     * @return Localizacao para onde se deve ir
     * 
     * chama método encontrarMenorCaminho()**
     */
    public Localizacao proximaLocalizacao(Localizacao localizacaoDestino){
        
        //Inicializa e pega as ruas da cidade fornecidas
        List<Rua> ruas = new LinkedList<>();
        ruas.addAll(ControladorCidade.getRuas()); // inicializado em Simulacao()
        Localizacao novaLocalizacao = null;
        
        if(localizacaoDestino.equals(this)){//Verifica se já alcancou o destino
            return localizacaoDestino;
        }else{
            Direcao direcao = encontrarMenorCaminho(localizacaoDestino);
            novaLocalizacao = new Localizacao(x+direcao.getDx(), y + direcao.getDy());
            return novaLocalizacao;
        }
    }
    
    /**
     * GRUPO:
     * Baseado no Algoritimo de Lee. Algoritimo procura primeiro em largura e retorna a melhor
     * proxima direcao a seguir no menor caminho encontrado.
     * Adaptação do algoritimo de Sven Woltmann
     */
    private Direcao encontrarMenorCaminho(Localizacao localizacaoDestino) {
        
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] descoberto = new boolean[51][51];
        boolean[][] matriz = new boolean[51][51];
        
        List<Rua> ruas = new LinkedList<>();
        ruas.addAll(ControladorCidade.getRuas());
        
        List<PontoEspera> pontos = new LinkedList<>();
        pontos.addAll(ControladorCidade.getPontos());
        
        for(Rua rua: ruas){
            matriz[rua.getX()][rua.getY()] = true;
        }
        
        for(PontoEspera ponto: pontos){
            matriz[ponto.getX()][ponto.getY()] = true;
        }
        
        descoberto[y][x] = true;
        queue.add(new Node(x, y, null));
        
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (Direcao dir : Direcao.values()) {
                int novoX = node.x + dir.getDx();
                int novoY = node.y + dir.getDy();
                Direcao novaDirecao = node.direcaoInicial == null ? dir : node.direcaoInicial;
                
                if (novoX == localizacaoDestino.getX() && novoY == localizacaoDestino.getY()) {
                    return novaDirecao;// alcançou o destino
                }
                
                if (matriz[novoY][novoX] && !descoberto[novoY][novoX]) {
                    // Descobre novo caminho possivel e coloca na queue
                    descoberto[novoY][novoX] = true;
                    queue.add(new Node(novoX, novoY, novaDirecao));
                }
            }
        }
        throw new IllegalStateException("No path found");
    }
    
    
    public Localizacao proximaLocalizacaoPessoa(Localizacao localizacaoDestino){
        if(localizacaoDestino.equals(this)){//Verifica se já alcancou o destino
            return localizacaoDestino;
        }else{
            int destX = localizacaoDestino.getX();
            int destY = localizacaoDestino.getY();
            int deslocX = x < destX ? 1 : x > destX ? -1 : 0;//Deslocamento de 1 ou 0 ou -1 posição em x
            int deslocY = y < destY ? 1 : y > destY ? -1 : 0;//Deslocamento de 1 ou 0 ou -1 posição em y
            Localizacao novaLocalizacao;
            if(deslocX != 0 && deslocY != 0){//Se nenhuma coordenada coincide com a localizacao destino
                if(rand.nextInt(2) == 0){//Atualizar x
                    novaLocalizacao = new Localizacao(x + deslocX, y);
                }else{//Atualizar y
                    novaLocalizacao = new Localizacao(x, y + deslocY);
                }
            }else{
                if(deslocX != 0) novaLocalizacao = new Localizacao(x + deslocX, y);
                else novaLocalizacao = new Localizacao(x, y + deslocY);
            }
            return novaLocalizacao;
        }
    }
    
    /**
     * Verificacao de igualdade de conteudo de objetos do tipo Localizacao.
     * @return true: se a localizacao é igual.
     *         false: caso contrario.
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }else if(!(obj instanceof Localizacao)){
            return false;
        }else{
            Localizacao outro = (Localizacao) obj;
            return x == outro.x && y == outro.y;
        }
    }
    
    /**
     * @return A representacao da localizacao.
     */
    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
    
    private static class Node {
        final int x;
        final int y;
        final Direcao direcaoInicial;

        public Node(int x, int y, Direcao direcaoInicial) {
          this.x = x;
          this.y = y;
          this.direcaoInicial = direcaoInicial;
        }
    }
    
    public enum Direcao {
        UP(0, -1),
        RIGHT(1, 0),
        DOWN(0, 1),
        LEFT(-1, 0);

        private final int dx;
        private final int dy;

        Direcao(int dx, int dy) {
          this.dx = dx;
          this.dy = dy;
        }

        public int getDx() {
          return dx;
        }

        public int getDy() {
          return dy;
        }
    }
}
