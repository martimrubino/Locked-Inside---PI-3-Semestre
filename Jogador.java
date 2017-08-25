
package locked_inside;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Jogador {
    //estas variaveis serão usadas para definir a posição do jogador
    private int x;//posição vertical
    private int y;//posição horizontal
    //estas variaveis serão usadas para definir a forma do meu jogador (no caso o tamanho do quadrado)
    private int altura;
    private int largura;
    //cor do meu jogador
    private Color cor;
    // usarei esta variavel para verificar posicoes na matriz
    private int matriz[][];
    private int linha;
    private int coluna;
    private int mov;
    
    private Imagem playerart;
    
    //CONSTRUTOR
    public Jogador(Labirinto lab) throws IOException{
        // posicao inicial do jogador no labirinto
        this.x = 1;
        this.y = 1;
        // definindo o tamanho do jogador
        this.altura = 20;
        this.largura = this.altura;
        // definindo a cor do jogador
        this.cor = Color.green;
        // importando a matriz da classe Labirinto
        this.matriz = lab.getMatriz();
        this.linha = matriz.length;
        this.coluna = matriz[0].length;
        
        this.playerart = new Imagem(0,0,"tile_player.png");
    }
    
    // funcao vai pegar a minha atriz labirinto e desenhar meu jogador nele
    // o mesmo ira receber as coordenadas da matriz definidas no metodo "moveJogador" como posicao
    public void desenha(Graphics g) {
        g.setColor(cor);
        //g.fillRect(this.x*this.altura, this.y*this.largura, this.largura, this.altura);
        playerart.desenha(g, this.x*20, this.y*20);
    }
    
    // verificação de teclas pressionadas e movimento do jogador
    public void moveJogador(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    // vai mover uma COLUNA para esquerda na matriz
                    if(this.x > 0 && this.x < this.matriz.length && this.matriz[y][x-1] == 0){
                        this.x = this.x-1;
                    }
                }
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    // vai mover uma COLUNA para direita na matriz
                    if(this.x>0 && this.x < this.matriz.length && this.matriz[y][x+1] == 0){
                        this.x = this.x+1;
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    // vai mover uma LINHA para cima na matriz
                    if(this.y > 0 && this.y < this.matriz.length && this.matriz[y-1][x]==0){
                        this.y = this.y-1;
                    }
                }
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    // vai mover uma LINHA para baixo na matriz
                    if(this.y > 0 && this.y < this.matriz.length && this.matriz[y+1][x]==0){
                        this.y = this.y+1;
                    }
                }
			
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
}
