
package locked_inside;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Inimigo {
    private int x;//posição vertical
    private int y;//posição horizontal
    //estas variaveis serão usadas para definir a forma do meu jogador (no caso o tamanho do quadrado)
    private int altura;
    private int largura;
    //cor do meu jogador
    private Color cor;
    // usarei esta variavel para verificar posicoes na matriz
    private int matriz[][];
    
    // variaveis para a posicao do jogador
    private int playerx;// posicao x do jogador
    private int playery;// posicao y do jogador
    
    //instanciando a classe A_Star para o monstro
    private A_Star estrela;
    
    private ArrayList<No> caminho = new ArrayList();
    
    private int indice = 0;
    
    private Imagem enemyart;
    
    public Inimigo(Labirinto lab, int x, int y) throws IOException{
        // importando a matriz da classe Labirinto
        this.matriz = lab.getMatriz();
        // posicao inicial do jogador no labirinto
        this.x = x;
        this.y = y;
        // definindo o tamanho do jogador
        this.altura = 20;
        this.largura = this.altura;
        // definindo a cor do jogador
        this.cor = Color.RED;
        // instanciando o objeto da ia com meu labirinto como parametro
        this.estrela = new A_Star(matriz);
        estrela.defineGrafo();
        
        this.enemyart = new Imagem(0,0,"tile_monstro.png");
    }
    
    public void desenha(Graphics g) {
        g.setColor(cor);
        //g.fillRect(this.x*this.altura, this.y*this.largura, this.largura, this.altura);
        enemyart.desenha(g, this.x*20, this.y*20);
    }
    
    // FUNCOES QUE USAREI PARA O PATHFINDER A*
    // esta função sempre sera executada no gameloop para assim manter a posicao do jogador atualizada nesta classe
    public void posicaoJogador(Jogador j){
        this.playerx = j.getX();
        this.playery = j.getY();
    }
    
    public void achaCaminho(){
        caminho = estrela.pathfinder(this.x, this.y, this.playerx, this.playery);
        indice = caminho.size()-1;
    }
    
    public void moveMonstro(Inimigo A, Inimigo B) throws InterruptedException{
        // importando as posições dos monstros a serem comparados
        int x1 = A.getX();
        int y1 = A.getY();
        int x2 = B.getX();
        int y2 = B.getY();
        
        int auxX;
        int auxY;
        
        System.out.println(caminho.size());
        if(caminho.size() == 0){
            return;
        }
        if(indice < 0){
            return;
        }
        
        auxX = this.x;
        auxY = this.y;
        this.x = caminho.get(indice).getX();
        this.y = caminho.get(indice).getY();
        
        // caso a posicao do monstro seja igual a dos outros ele volta para sua anterior
        if((this.x == x1 && this.y == y1) ||  (this.x == x2 && this.y == y2)){
            this.x = auxX;
            this.y = auxY;
        }
        
        indice--;
    }
    
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    
    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }
    
}
