package tp.pr2.logica;

public class GameStateStack {
	private GameState [] array;
	private int contador;
	
	private static final int CAPACITY = 20;
	
	public GameStateStack() {
		array = new GameState[CAPACITY];
		this.contador=0;
	}
	
	
	
	/**Almacena un nuevo estado*/
	public void push(GameState state){
		this.array[contador]=state;
		this.contador++;
	}
		
	public void almacenar(GameState gameState) {
		if(this.contador<CAPACITY) {
			push(gameState);
		}
		else {
			pop();
			push(gameState);
			
		}
	}	
	/**Devuelve el último estado almacenado**/
	public GameState pop() {
		this.array[0]=null;//elimino el ultimo elemento
		for(int i=0;i<this.contador;i++) {
			if(i+1<contador) {
				array[i]=array[i+1];
			}
		}
		this.contador--;
		return this.array[contador-1];
	}
	/**Devuelve si la estructura de datos está vacía*/
	public boolean isEmpty() {
		return false;
	}
	public int getContador() {
		return this.contador;
	}
	public GameState [] getArray() {
		return this.array;
	}

}
