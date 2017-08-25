
package locked_inside;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class TelaInicio extends JPanel  implements KeyListener{
    private boolean menuAtivo;
    private int status;
    private KeyEvent e;
    private int jogoAtivo;
    
    private int opc;
    
    JFrame frame;
    
    //CONSTRUTOR
    public TelaInicio() throws IOException{
        frame = new JFrame("LOCKED INSIDE!");//criação e nomeamento da janela
        frame.setSize(800, 600);//redimensionamento da janela
        frame.setLocationRelativeTo(null);//posicionamento da janela        
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//encerramento da janela
        frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        frame.setResizable(true);//desabilita maximizar janela
        frame.setVisible(true);
        frame.add(this); // adiciona o JPanel na janela
        
        this.setBackground(Color.black); // muda cor de fundo do JPanel
        this.addKeyListener(this); // registra o JPanel na lista de eventos a serem repassados
        setFocusable(true); // permite o JPanel receber o os eventos

        this.jogoAtivo = 0;
        opc = 1;
        //this.jogo = new Game();
        //this.status = 1;
        //frame.setVisible(true);
    }
   @Override
    public void setVisible(boolean visible){
        
        frame.setVisible(visible);
    }
        
    
    //AQUI MANDO DESENHAR NA TELA
    @Override
    public void paint(Graphics g) {
        // limpa a janela
        super.paint(g);
        g.setColor(Color.green);
        g.setFont(new Font("Arial Bold", Font.PLAIN, 80));
        g.drawString("LOCKED INSIDE!", 100, 150);
        g.setColor(Color.white);
        g.setFont(new Font("Arial Bold", Font.PLAIN, 30));
        g.drawString("start", 200, 250);
    }
    
    public int run() throws InterruptedException, IOException {
        this.menuAtivo = true;
        while(menuAtivo) {
            // desenha a tela
            repaint();
            
            // "inicia o jogo" caso  jogoAtivo seja = 1
            /*if(this.jogoAtivo == 1){
                this.jogo = new Game();
                this.jogo.run();
            }
            */
            // delay de 10 milisegundos
            Thread.sleep(10);
     
        }
        return opc;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
        
    }

    @Override
    public void keyPressed(KeyEvent e){
  	if (e.getKeyCode() == KeyEvent.VK_ENTER){
                opc = 1;
                this.menuAtivo = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                opc = -1;
                this.menuAtivo = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    public void fechar(){
        frame.dispose();
    }
}
