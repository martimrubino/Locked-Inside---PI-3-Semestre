
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

public class LevelSelect extends JPanel  implements KeyListener{
    private boolean menuAtivo;
    private int status;
    private KeyEvent e;
    private int jogoAtivo;
    
    private String labirinto;
    
    JFrame frame;
    
    //CONSTRUTOR
    public LevelSelect() throws IOException{
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
        g.setColor(Color.white);
        g.setFont(new Font("Arial Bold", Font.PLAIN, 30));
        g.drawString("ESCOLHA UMA AREA", 220, 100);
        
        g.setFont(new Font("Arial Bold", Font.PLAIN, 15));
        g.drawString("1 - Saguao", 300, 200);
        
        g.setFont(new Font("Arial Bold", Font.PLAIN, 15));
        g.drawString("2 - Cozinha", 300, 250);
        
        g.setFont(new Font("Arial Bold", Font.PLAIN, 15));
        g.drawString("3 - CorredoresA", 300, 300);
        
        g.setFont(new Font("Arial Bold", Font.PLAIN, 15));
        g.drawString("4 - CorredoresB", 300, 350);
        
        g.setFont(new Font("Arial Bold", Font.PLAIN, 15));
        g.drawString("5 - QuartoPrincipal", 300, 400);
        
        g.setFont(new Font("Arial Bold", Font.PLAIN, 15));
        g.drawString("6 - Porao", 300, 450);
        
        g.setFont(new Font("Arial Bold", Font.PLAIN, 15));
        g.drawString("7 - Sotao", 300, 500);
    }
    
    public String run() throws InterruptedException, IOException {
        this.menuAtivo = true;
        while(menuAtivo) {
            // desenha a tela
            repaint();
            
            Thread.sleep(10);
     
        }
        return labirinto;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
        
    }

    @Override
    public void keyPressed(KeyEvent e){
  	if (e.getKeyCode() == KeyEvent.VK_1){
                labirinto = "Saguao.txt";
                this.menuAtivo = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_2){
                labirinto = "Cozinha.txt";
                this.menuAtivo = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_3){
                labirinto = "CorredoresA.txt";
                this.menuAtivo = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_4){
                labirinto = "CorredoresB.txt";
                this.menuAtivo = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_5){
                labirinto = "QuartoPrincipal.txt";
                this.menuAtivo = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_6){
                labirinto = "Porao.txt";
                this.menuAtivo = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_7){
                labirinto = "Sotao.txt";
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