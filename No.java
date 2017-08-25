
package locked_inside;

public class No {
    private int x; // posicao x do meu no
    private int y; // posicao y do meu no
    private boolean possivel; // se a posição desejada não é uma parede
    private No pai; // de onde o no atual originou
    private int gn; // custo de movimentação entre nós
    private int heuristica; // distancia entre o no atual e o objetivo
    private int custo = 10;
          
    // constroi meu obeto
    public No(int x, int y, boolean possivel){
        this.x = x;
        this.y = y;
        this.possivel = possivel;
    }
    
    // calcula o custo g(n) entre os nos
    public int defineG(No pai){
        return (pai.getG() + custo);
    }
    
    public void heuristica(No alvo){ // no caso o no alvo é o jogador
        heuristica = (Math.abs(getX() - alvo.getX()) + Math.abs(getY() - alvo.getY())) * custo;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public void setX(int x){
	this.x = x;
    }
    
    public void setY(int y){
	this.y = y;
    }
    
    // verifica se a posicao é possivel
    public boolean sePossivel(){
	return possivel;
    }
    
    // retorna o no origem do atual
    public No retornaPai(){
		return pai;
    }
    
    // defina o no origem do atual
    public void definePai(No pai){
		this.pai = pai;
    }
    
    // calcula e retorna meu custo f(n)
    public int getF(){
        return this.gn + this.heuristica;
    }
    
    // retorna meu custo gn
    public int getG(){
	return this.gn;
    }
    
    // retorna minha heuristica
    public int getHeuristica(){
	return this.heuristica;
    }
}
