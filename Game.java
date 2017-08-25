
package locked_inside;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.IOException;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.swing.JOptionPane;

public class Game extends JPanel  implements KeyListener{
    private boolean jogoAtivo;
    
    private Labirinto lab;
    
    private Jogador player;
    
    private Inimigo monstro;
    private Inimigo monstro2;
    private Inimigo monstro3;
    
    private double cont = 0;
    private double mov = 0;
    private double time;
    private Vida pontovida;
    
    private double vida;
    private int timervida;
    private int podepintar;
    
    private int opc;
    
    private int novomonstro;
    
    private Imagem groundart, screenframeart, hudart;
    
    public Clip clip;
    
    JFrame frame;
    
    public Game(String labirinto) throws IOException{
        frame = new JFrame("Jogo Labirinto!");//criação e nomeamento da janela
        frame.setSize(810, 620);//redimensionamento da janela
        frame.setLocationRelativeTo(null);//posicionamento da janela        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//encerramento da janela
        frame.setResizable(true);//desabilita maximizar janela
        
        frame.add(this); // adiciona o JPanel na janela
        
        this.setBackground( Color.black ); // muda cor de fundo do JPanel
        this.addKeyListener(this); // registra o JPanel na lista de eventos a serem repassados
        setFocusable(true); // permite o JPanel receber o os eventos
        
        jogoAtivo = true;
        //cria meu objeto labirinto
        lab = new Labirinto(labirinto);
        player = new Jogador(lab);
        
        monstro = new Inimigo(lab, 27, 27);
        monstro2 = new Inimigo(lab, 30, 30);
        monstro3 = new Inimigo(lab, 30, 30);
        
        pontovida = new Vida(lab);
        
        this.vida = 25;
        
        this.time = 0;
        
        podepintar = 1;
        
        opc = 0;
        
        novomonstro = 0;
        
        this.groundart = new Imagem(0,0,"tile_chao.png");
        this.screenframeart = new Imagem(0,0,"tile_screenframe.png");
        this.hudart = new Imagem(0, 0, "tile_hud.png");
        
        frame.setVisible(true);
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
        groundart.desenha(g, -2, -8);
        hudart.desenha(g, 576, 100);
        
        lab.desenhaLabirinto(g);//desenhando minhas paredes
        player.desenha(g);
        monstro.desenha(g);
        monstro2.desenha(g);
        monstro3.desenha(g);
        
        if(podepintar == 2){
            pontovida.desenha(g);
        }
        
        // desenhando as informações escritas
        // print vida
        g.setColor(Color.green);
        g.setFont(new Font("Arial Bold", Font.PLAIN, 20));
        g.drawString("VIDA: ", 610, 195);
        g.drawString( Math.round(vida)+"", 680, 195);
        // print pontos
        g.setColor(Color.blue);
        g.setFont(new Font("Arial Bold", Font.PLAIN, 20));
        g.drawString("PONTOS: ", 610, 325);
        g.drawString( Math.round(time)+" ", 710, 325);
        
        screenframeart.desenha(g, 0, 0);
    }
    
    //GAME LOOP
    public int run() throws InterruptedException {
        tocaMusica();
        clip.loop(0);
        while(jogoAtivo) {
            if(cont > 2){
                cont = 0;
            }
            if(mov > 10){
                mov = 0;
            }
            // estou chamando esta função a cada rodada do gameloop, com o parametro jogador,
            // para assim manter a posição do jogador atualizada na classe inimigo para comparações
            //monstro.posicaoJogador(player);
            if(cont == 2){
                monstro.posicaoJogador(player);
                monstro.achaCaminho();
                if(novomonstro == 1 || novomonstro == 2){
                    monstro2.posicaoJogador(player);
                    monstro2.achaCaminho();
                }
                if(novomonstro == 2){
                    monstro3.posicaoJogador(player);
                    monstro3.achaCaminho();
                }
            }
            
            if(mov == 10){
                monstro.moveMonstro(monstro2, monstro3);
                if(novomonstro == 1 || novomonstro == 2){
                    monstro2.moveMonstro(monstro, monstro3);
                }
                if(novomonstro == 2){
                    monstro3.moveMonstro(monstro, monstro2);
                }
            }
            
            // redesenha a tela
            repaint();
            
            // delay de 10 milisegundos
            Thread.sleep(10);
           
            
            // chamo o metodo que randomiza uma posicao para meu ponto na matriz;
            if(vida <= 20 && podepintar == 1){
                pontovida.geraVida();
                podepintar = 2;
            }
            
            if(player.getX() == pontovida.getA() && player.getY() == pontovida.getB()){
                pontovida.mudaPosicao();
                vida = 25;
                podepintar = 1;
                
            }
            
            verificaColisao();
            verificaVida();
            inimigoAdicional();
            
            cont = cont + 0.5;
            mov = mov + 0.5;
            time += 0.01;
            vida -= 0.01;
        }
        clip.stop();
        return opc;
        
    }
    
    public void keyPressed(KeyEvent e) {
        System.out.println("Pressionei a tecla:"+e.getKeyChar());
        player.moveJogador(e);
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                opc = 1;
                this.jogoAtivo = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Pressionei a tecla:"+e.getKeyChar());
        player.moveJogador(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    public void pegaVida(){
        if(player.getX() == pontovida.getA() && player.getY() == pontovida.getB()){
            podepintar = 0;
            pontovida.mudaPosicao();
        }
    }
    
    // metodo verifica a colisao do jogador com o monstro;
    public void verificaColisao(){
        if(player.getX() == monstro.getX() && player.getY() == monstro.getY()){
            opc = 3;
            jogoAtivo = false;
        }
        else if(player.getX() == monstro2.getX() && player.getY() == monstro2.getY()){
            opc = 3;
            jogoAtivo = false;
        }
        else if(player.getX() == monstro3.getX() && player.getY() == monstro3.getY()){
            opc = 3;
            jogoAtivo = false;
        }
    }
    
    public void inimigoAdicional(){
        if(time > 5 && time < 6){
            monstro2.setXY(27, 27);
            novomonstro = 1;
        }
        if(time > 10 && time < 11){
            monstro3.setXY(27, 27);
            novomonstro = 2;
        }
    }
    
    // metodo verifica se a vida do personagem esta em 0 para abrir o gme over
    public void verificaVida(){
        if(vida < 0){
            opc = 3;
            jogoAtivo = false;
        }
    }
    
    public void tocaMusica(){
        try {
            File soundFile = new File("gameplay.wav");
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
            DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(sound);
            } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
            }

    }
    
}
