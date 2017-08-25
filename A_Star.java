
package locked_inside;

import java.util.ArrayList;
import java.util.List;

public class A_Star {
    private int matriz[][];
    private No[][] nos; // aqui estou criando uma lista de nós
    
    // preciso importar a matriz representante do mapa do jogo para poder transforma-la em um grafo
    public A_Star(int lab[][]){
        this.matriz = lab;
    }
    
    // primeiramente preciso transformar todas as posições da matriz em nós
    // também preciso definir a heuristica, o custo g, se é possivel, etc
    public void defineGrafo(){
        this.nos = new No[matriz.length][matriz[0].length];
        for(int x=0; x<matriz.length; x++){
            for(int y=0; y<matriz[0].length; y++){
               this.nos[x][y] = new No(x, y, matriz[y][x] == 0); // defino um objeto No com a posição da matriz e ja verificando se é possivel (posição = 0)
            }
        }
    }
    
    // algoritmo pathfinder A*
    // meu metodo recebe como parametros as posicoes do monstro e jogador
    public ArrayList<No> pathfinder(int monstroX, int monstroY, int jogadorX, int jogadorY){
        // verificando se meu monstro encontrou o jogador antes de come;ar o pathfinder, caso sim, retorna uma lista vazia
        if(monstroX == jogadorX && monstroY == jogadorY){
            return new ArrayList<No>();
        }
        // criando as listas open e close
        ArrayList<No> open = new ArrayList<>();
        ArrayList<No> closed = new ArrayList<>();
        
        // adicionando minha posicao inicial na lista aberta
        System.out.println(monstroX+"  ,  "+monstroY);
        
        System.out.println("lista open : "+nos);
        
        open.add(nos[monstroX][monstroY]);
        
        
        
        // inicio meu loop para calcular o pathfinder
        while(true){
            // verifico qual no da lista aberta possui o menor custo f e retorno o mesmo
            No atual = menorF(open);
            // removo o no "atual" da lista aberta
            open.remove(atual);
            // adiciono o no "atual" na lista fechada
            closed.add(atual);
            
            // se minha posicao atual eh igual a posicao alvo, fecho o loop e o metodo
            if ((atual.getX() == jogadorX) && (atual.getY() == jogadorY)){
                // Retorno uma lista contendo todos os meus nos visitados.
                return verificaCaminho(nos[monstroX][monstroY], atual);
            }
            ArrayList<No> adjacentes = retornaAdjacente(atual, closed);
            for (No adjacente : adjacentes){
		// se meu no nao estiver na lista aberta
		if (!open.contains(adjacente)){
                    // defino meu no "atual" como pai deste no
                    adjacente.definePai(atual);
                    // defino a heuristica deste no 
                    adjacente.heuristica(nos[jogadorX][jogadorY]);
                    // defino o custo g deste no
                    adjacente.defineG(atual);
                    // adiciono o no para a lista aberta
                    open.add(adjacente);
		}
		// caso o no esteja na lista aberta e o custo g do mesmo =eh menor que anteriores
		else if (adjacente.getG() > adjacente.defineG(atual)){
                    // defino o no "atual" como pai deste no
                    adjacente.definePai(atual);
                    // defino o custo g deste no
                    adjacente.defineG(atual);
		}
            }

            // se nao existe um caminho
            if (open.isEmpty()){
		// retorno uma lista vazia
		return new ArrayList<No>();
            }
            // se ao contrario existir, continuo o loop
        }
        
    }
    
    // este metodo verifica qual no da lista possui o menor custo f e retorna o mesmo
    public No menorF(List<No> list){
        No menor = list.get(0);
        for (int i = 0; i < list.size(); i++){
            if(list.get(i).getF() < menor.getF()){
                menor = list.get(i);
            }
	}
    return menor;
    }
    
    // verificando qual é o menor caminho
    public ArrayList<No> verificaCaminho(No inicio, No alvo){
	ArrayList<No> caminho = new ArrayList<>();
	No node = alvo;
	boolean done = false;
	while (!done){	
            caminho.add(node);
            node = node.retornaPai();
            if (node.equals(inicio)){
		done = true;
            }
        }	
	//imprime o caminho 
	for(No step: caminho){
	System.out.println("no: X "+ step.getX()+", Y"+step.getY());
	} 
	return caminho;	
    }
    
    // este metodo recebe como parametro uma coordenada e retorna o no da lista nesta coordenada
    public No getNo(int x, int y){
	if (x >= 0 && x < matriz.length && y >= 0 && y < matriz[0].length){
            return nos[x][y]; // retorno no na posicao x e y da minha matriz de nos
	}
	else{
            return null;
	}
    }
    
    // metodo verifica os vizinhos
    public ArrayList<No> retornaAdjacente(No no, List<No> closed){
	ArrayList<No> adjacentes = new ArrayList<No>();
	int x = no.getX();
	int y = no.getY();
	No adjacente;
	// verifica o vizinho da esquerda
	if (x > 0){
            adjacente = getNo(x - 1, y);
            if (adjacente != null && adjacente.sePossivel() && !closed.contains(adjacente)){
                adjacentes.add(adjacente);
            }
        }
	// verifica o vizinho da direita
	if (x < matriz[0].length){
		adjacente = getNo(x + 1, y);
                    if (adjacente != null && adjacente.sePossivel() && !closed.contains(adjacente)){
			adjacentes.add(adjacente);
                    }
	}
        // verifica o vizinho de cima
	if (y > 0){
            adjacente = this.getNo(x, y - 1);
            if (adjacente != null && adjacente.sePossivel() && !closed.contains(adjacente)){
                adjacentes.add(adjacente);}
            }
            // verifica o vizinho de baixo
            if (y < matriz.length){
		adjacente = this.getNo(x, y + 1);
                if (adjacente != null && adjacente.sePossivel() && !closed.contains(adjacente)){
                    adjacentes.add(adjacente);
		}
            }
    return adjacentes;
    }
}
