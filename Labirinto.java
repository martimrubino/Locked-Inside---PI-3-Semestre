
package locked_inside;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Labirinto {
    private int lin;
    private int col;
    private int matriz[][];
    //variaveis da minha parede;
    private int height;
    private int width;
    private int tam;
    private int cont1;
    private int cont2;
    
    private Imagem wallart;
   
    
    public Labirinto(String nomeArquivo) throws FileNotFoundException, IOException{
        BufferedReader entrada = new BufferedReader( new FileReader(nomeArquivo));
        String linha;
        linha = entrada.readLine();
        
        //definindo o tamanho da minha parede
        this.height = 20;
        this.width = this.height;

  
        
        // criando minha matriz a partir de duas variaveis, numero de linhas e colunas,
        // definidas na primeira linha do arquivo lido.
        String tamMat[] = linha.split(" ");
        this.lin = Integer.parseInt(tamMat[0]);
        this.col = Integer.parseInt(tamMat[1]);
        this.matriz = new int[lin][col];
        
        // lendo todas as linhas do meu arquivo importado,
        // e assim inserindo os valores em suas respectivas casas
        int i = 0;
        while((linha = entrada.readLine())!=null){
            String vetor[] = linha.split(" ");
            for(int j=0; j<this.lin; j++){
                int a = Integer.parseInt(vetor[j]);
                this. matriz[i][j] = a;
            }
            i++;
        }
        
        this.wallart = new Imagem(0,0,"tile_paredes.png");
    }
    
    //DESENHA MEU LABIRINTO
    public void desenhaLabirinto(Graphics g){
        for(int i=0; i<matriz.length; i++){
            for(int j=0;j<matriz[0].length;j++){
                if(matriz[j][i] == 1){
                    // aqui desenho minha parede
                    g.setColor(Color.white);
                    g.fillRect(i*this.width, j*this.width, height, width);
                    wallart.desenha(g, i*20, j*20);
                }
            }
        }
    }

    public int[][] getMatriz() {
        return matriz;
    }
}
