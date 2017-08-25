
package locked_inside;

import java.awt.Graphics;
import java.io.IOException;

public class Locked_Inside {
    private static Graphics g;
    public static void main(String[] args)  throws InterruptedException, IOException{
        int opc  = 1;
        String labirinto = null;
        //Game jogo;
        //Menu menu = new Menu();
        //menu.setVisible(true);
        
        boolean gameover;
        
        while( opc != -1 ){
            
            // menu principal
            if(opc == 1){
                Menu menu = new Menu();
                menu.load();
                menu.setVisible(true);
                opc = menu.run();
                menu.setVisible(false);
                menu = null;
            }
            
            // level select
            if(opc == 4){
                LevelSelect screen = new LevelSelect();
                screen.setVisible(true);
                labirinto = screen.run();
                screen.setVisible(false);
                screen = null;
                opc = 2;
            }
            
            // game loop
            if( opc == 2 ){
                Game jogo;
                jogo = new Game(labirinto);
                jogo.setVisible(true);
                opc = jogo.run();
                jogo.setVisible(false);
                jogo = null;
            }
            
            // tela de game over
            if(opc == 3){
                GameOver fim = new GameOver();
                fim.setVisible(true);
                opc = fim.run();
                fim.setVisible(false);
                fim = null;
            }
            
        }
    
    }
    
}
