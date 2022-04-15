package simulacao;
import java.awt.*;
import java.util.Iterator;
import javax.swing.*;

/**
 * Fornece a visualizacao da simulacao
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann and Grupo
 */
public class JanelaSimulacao extends JFrame{
    private Mapa mapa;
    private VisaoMapa visaoMapa;
    private Image doubleBuffer;
    
    public JanelaSimulacao(Mapa mapa){
        this.mapa = mapa;
        visaoMapa = new VisaoMapa(mapa.getLargura(),mapa.getAltura());
        getContentPane().add(visaoMapa);
        setTitle("Simulator");
        setSize(1000,1000);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Mostra o estado atual do mapa.
     */
    public void executarAcao(){
        visaoMapa.preparePaint();
        Iterator<Item> items = mapa.getItem();
        while(items.hasNext()) {//Se existir algum objeto na posicao (i,j)
            Item item = items.next();
            if(item instanceof Desenhavel){
                Desenhavel desenhavel = (Desenhavel) item;
                Localizacao localizacao = desenhavel.getLocalizacaoAtual();
                visaoMapa.desenharImagem(localizacao.getX(), localizacao.getY(), desenhavel.getImagem());
            }
        }
        visaoMapa.repaint();
    }
    
    /**
     * Fornece uma visualizacao grafica do mapa. Esta eh 
     * uma classe interna que define os componentes da GUI.
     * Ela contém alguns detalhes mais avancados sobre GUI 
     * que voce pode ignorar para realizacao do seu trabalho.
     */    
    private class VisaoMapa extends JPanel{

        private final int VIEW_SCALING_FACTOR = 6;

        private int larguraMapa, alturaMapa;
        private int xScale, yScale;
        private Dimension tamanho;
        private Graphics g;
        private Image imagemMapa;

        /**
         * Cria um novo componente VisaoMapa.
         */
        public VisaoMapa(int largura, int altura)
        {
            larguraMapa = largura;
            alturaMapa = altura;
            setBackground(Color.white);
            tamanho = new Dimension(0, 0);
        }

        /**
         * Informa para o gerenciador GUI o tamanho.
         */
        public Dimension getPreferredSize()
        {
            return new Dimension(larguraMapa * VIEW_SCALING_FACTOR,
                                 alturaMapa * VIEW_SCALING_FACTOR);
        }
        
        /**
         * Prepara para um novo ciclo de exibicao. Uma vez que o componente
         * pode ser redimensionado, calcula o "fator de escala" novamente.
         */
        public void preparePaint()
        {
            if(!tamanho.equals(getSize())) {  // se o tamanho mudou...
                tamanho = getSize();
                //teste
                
                
                imagemMapa = visaoMapa.createImage(tamanho.width, tamanho.height);
                g = imagemMapa.getGraphics();

                xScale = tamanho.width / larguraMapa;
                if(xScale < 1) {
                    xScale = VIEW_SCALING_FACTOR;
                }
                yScale = tamanho.height / alturaMapa;
                if(yScale < 1) {
                    yScale = VIEW_SCALING_FACTOR;
                }
            }
            g.setColor(Color.white);
            g.fillRect(0, 0, tamanho.width, tamanho.height);
            g.setColor(Color.gray);
            for(int i = 0, x = 0; x < tamanho.width; i++, x = i * xScale) {
                g.drawLine(x, 0, x, tamanho.height - 1);
            }
            for(int i = 0, y = 0; y < tamanho.height; i++, y = i * yScale) {
                g.drawLine(0, y, tamanho.width - 1, y);
            }
            //Desenha a cidade
            visaoMapa.desenharCidade();
        }
        
        /**
         * Desenha a imagem para um determinado item.
         */
        public void desenharImagem(int x, int y, Image image)
        {
            
            g.drawImage(image, x * xScale + 1, y * yScale + 1,
                        xScale - 1, yScale - 1, this);
        }
        
        /**
         * O componente VisaoMapa precisa ser reexibido. Copia a
         * imagem interna para a tela.
         */
        public void paintComponent(Graphics g)
        {
            if(imagemMapa != null) {
                g.drawImage(imagemMapa, 0, 0, null);
            }
        }
        
        /**
         * GRUPO:
         * O componente visaoMapa precisa desenhar as ruas. Desenha
         * de acordo com o que foi estabelecido ser cada parte da cidade.
         * Desenha porém não tem uma "conecção" com a classe que determina as ruas.
         */
        public void desenharCidade(){
            if(!tamanho.equals(getSize())) {  // se o tamanho mudou...
                tamanho = getSize();
                imagemMapa = visaoMapa.createImage(tamanho.width, tamanho.height);
                g = imagemMapa.getGraphics();

                xScale = tamanho.width / larguraMapa;
                if(xScale < 1) {
                    xScale = VIEW_SCALING_FACTOR;
                }
                yScale = tamanho.height / alturaMapa;
                if(yScale < 1) {
                    yScale = VIEW_SCALING_FACTOR;
                }
            }
            //"RUAS"
            g.setColor(Color.green);
            g.fillRect(3*xScale, 3*yScale, 46*xScale, yScale);
            g.fillRect(3*xScale, 4*yScale, xScale, 46*yScale);
            g.fillRect(3*xScale, 49*yScale, 46*xScale, yScale);
            g.fillRect(49*xScale, 3*yScale, xScale, 47*yScale);
            //"PONTO DE VAN"
            g.setColor(Color.blue);
            g.fillRect(45*xScale, 2*yScale, xScale, yScale);
            g.fillRect(2*xScale, 45*yScale, xScale, yScale);
            g.fillRect(48*xScale, 33*yScale, xScale, yScale);
        }
    }
}
