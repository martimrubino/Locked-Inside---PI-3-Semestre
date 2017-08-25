
package locked_inside;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

public class Vida {
    
    private int matriz[][];
    private int a;
    private int b;
    private int altura;
    private int largura;
    
    private Imagem vidaart;
    
    public Vida(Labirinto lab) throws IOException{
        this.matriz = lab.getMatriz();
        this.altura = 20;
        this.largura = this.altura;
        
        this.a = ((int)(Math.random()*100))%28 + 1;
        this.b = ((int)(Math.random()*100))%28 + 1;
        
        this.vidaart = new Imagem(0, 0, "tile_vida.png");
    }
    
    // metodo gera um ponto em uma posição aleatoria do mapa que não esteja bloqueada
    public void geraVida(){
        this.a = ((int)(Math.random()*100))%28 + 1;
        this.b = ((int)(Math.random()*100))%28 + 1;
        if(this.matriz[b][a] == 1){
            geraVida();
        }
    }
    
    public int getA(){
        return this.a;
    }
    
    public int getB(){
        return this.b;
    }
    
     public void desenha(Graphics g) {
        g.setColor(Color.yellow);
        //g.fillRect(this.a*this.altura, this.b*this.largura, this.largura, this.altura);
        vidaart.desenha(g, this.a*20, this.b*20);
    }
     
    public void mudaPosicao(){
        this.a = 30;
        this.b = 30;
    }
    
    
}
