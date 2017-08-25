
package locked_inside;

import java.awt.Color;
import java.awt.Graphics;

public class Parede {
    private int x;
    private int y;
    private int altura;
    private int largura;
    private Color cor;
    //CONSTRUTOR
    public Parede(int x,int y,int altura,int largura, Color cor){
        //tamanho da minha parede (a mesma tera um tamanho fixo)
        this.x = x;
        this.y = y;
        this.altura = 1;
        this.largura = 1;
        this.cor = cor;
    }
    
    public void desenha(Graphics g) {
        g.setColor(cor);
        g.fillRect(x,y, largura, altura);
    }
}
