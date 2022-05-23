package tp.pr1;

import java.util.Random;

public class Board {
	private Cell[][] board; 
	private int boardSize;
	private static Random random;
	public Board(int size){	
		this.boardSize=size;
		this.board= new Cell[boardSize][boardSize];
	}
	/**
	 * Método que ejecuta las dos primeras
	 * acciones de un movimiento (desplazar y fusionar) en la dirección dir
	 * del tablero. Devuelve un objeto con los resultados del movimiento
	 */
	public MoveResults executeMove(Cell[][] tablero,Direction dir){
		MoveResults moveResults = null;
		int score=0,hight=0;
		boolean movido=false;
		boolean[][] tabMovidas = new boolean[boardSize][boardSize];
		Position aux=null;
		switch(dir){
		case UP:
			moveResults =executeMoveUp(tablero,aux,tabMovidas, score,hight,movido);
			break;
		case DOWN:
			moveResults =executeMoveDown(tablero,aux,tabMovidas, score,hight,movido);
			break;
		case RIGHT:
			moveResults =executeMoveRight(tablero,aux,tabMovidas, score,hight,movido);
			break;
		case LEFT:
			moveResults =executeMoveLeft(tablero,aux,tabMovidas, score,hight,movido);
			break;
		}
		return moveResults;
	}
	
	public boolean isWinner(int hight){
		if (hight==2048){
			return true;
		}
		return false;
	}
	
	public boolean tableroLleno(){
		boolean ok=true;
		for(int i=0;i<getSize();i++){
			for(int j=0;j<getSize();j++){
				if(board[i][j]==null){
					ok=false;
				}
			}
		}
		return ok;
	}
	/**Copia los valores de las baldosas ocupadas del tablero y lo inserta por valor en las baldosas del tablero Auxiliar*/
//	private static void swap(Cell[][] anArray,Cell[][] aux, int i, int j) {
//		if(anArray[i][j]!=null){
//			int temp=anArray[i][j].getValue();
//			aux[i][j]=new Cell(temp);
//		}
//	}
	/**Crea un tablero auxiliar, evalua cada movimiento, si existe movimiento lo almacena en un contador*/
	public boolean noHayMasMovimientos(){
		
		for(int i = 0; i < this.boardSize; i++) {
			for(int j = 0; j < this.boardSize -1; j++) {
				if(this.board[i][j] == null || this.board[i][j + 1] == null || this.board[i][j].getValue()  == this.board[i][j + 1].getValue() ) {
					return true;
				}

			}
		}
		for(int j = 0; j < this.boardSize; j++) {
			for(int i = 0; i < this.boardSize -1; i++) {
				if(this.board[i][j] == null || this.board[i + 1][j ] == null || this.board[i][j].getValue()  == this.board[i + 1][j].getValue() ) {
					return true;
				}

			}
		}

		
		return false;	

	}
	/**
	 * método que ejecuta un movimiento hacia arriba 
	 * del tablero. Devuelve un objeto con los resultados del movimiento
	 */
	public MoveResults executeMoveUp(Cell[][] tablero,Position aux,boolean[][] tabMovidas,int score,int hight,boolean movido){
		int cont=0;
		for(int i=0;i<boardSize;i++){
			aux=new Position(0,i);
			for(int j= 0; j<boardSize;j++){
				if(estaOcupado(j,i)&&!coincide(aux,j,i)){/**si la baldosa tiene un valor en pos actual y pos actual no coincide con pos destino*/
					if(!estaOcupado(aux.getFila(),aux.getColumna())){/**si es null la pos destino*/
						tablero[aux.getFila()][aux.getColumna()]= new Cell(tablero[j][i].getValue());
						tablero[j][i]=null;
						cont++;
					}
					else {/**si pos destino esta ocupada*/
						/**si pos destino tiene mismo valor que pos actual y en pos destino (no ha habido ni fusion ni desplazamiento) entonces se fusiona*/
						if(!tabMovidas[aux.getFila()][aux.getColumna()]&&tablero[aux.getFila()][aux.getColumna()].doMerge(tablero[j][i])){
							tabMovidas[aux.getFila()][aux.getColumna()]=true;
							tablero[j][i]=null;
							score=score+tablero[aux.getFila()][aux.getColumna()].getValue();
							cont++;
						}
						else{/**si no son iguales los valores entonces se coloca en una pos adyacente segun la dir*/
							/**Se comprueba si la nueva pos destino es valida */
							if(esValida(aux.getFila()+1,i)){
								aux=new Position(aux.getFila()+1,i);
								/**Si la nueva pos destino esta libre se crea una nueva celda */
								if((tablero[aux.getFila()][aux.getColumna()]== null)){
									int a;
									a=tablero[j][i].getValue();
									tablero[j][i]=null;
									tablero[aux.getFila()][aux.getColumna()]=new Cell(a);
									cont++;
								}
							}	
						}
					}
				}
			}
		}
		if(cont!=0){
			reset(tablero,1,random);
			movido=true;
		}
		hight=recorrido();
		return new MoveResults(hight,score,movido);
	}
	
	/**
	 * método que ejecuta un movimiento hacia abajo
	 * del tablero. Devuelve un objeto con los resultados del movimiento
	 */
	public MoveResults executeMoveDown(Cell[][] tablero,Position aux,boolean[][] tabMovidas,int score,int hight,boolean movido){
		int cont=0;
		for(int i=0;i<boardSize;i++){
			aux=new Position(boardSize-1,i);
			for(int j= boardSize-1; j>=0;j--){
				if(estaOcupado(j,i)&&!coincide(aux,j,i)){/**si la baldosa tiene un valor en pos actual y pos actual no coincide con pos destino*/
					if(!estaOcupado(aux.getFila(),aux.getColumna())){/**si es null la pos destino*/
						tablero[aux.getFila()][aux.getColumna()]= new Cell(tablero[j][i].getValue());
						tablero[j][i]=null;
						cont++;
					}
					else {
						if(!tabMovidas[aux.getFila()][aux.getColumna()]&&tablero[aux.getFila()][aux.getColumna()].doMerge(tablero[j][i])){
							tabMovidas[aux.getFila()][aux.getColumna()]=true;
							tablero[j][i]=null;
							score=score+tablero[aux.getFila()][aux.getColumna()].getValue();
							cont++;
						}
						else{/**si no son iguales los valores entonces se coloca en una pos adyacente segun la dir*/
							/**si no son iguales los valores entonces se coloca en una pos adyacente segun la dir*/
							if(esValida(aux.getFila()-1,i)){
								aux=new Position(aux.getFila()-1,i);
								/**Si la nueva pos destino esta libre se crea una nueva celda */
								if((tablero[aux.getFila()][aux.getColumna()]== null)){
									int a;
									a=tablero[j][i].getValue();
									tablero[j][i]=null;
									tablero[aux.getFila()][aux.getColumna()]=new Cell(a);
									cont++;
								}
							}	
						}
					}
				}
			}
		}
		if(cont!=0){
			reset(tablero,1,random);
			movido=true;
		}
		hight=recorrido();
		return new MoveResults(hight,score,movido);
	}
	
	/**
	 * método que ejecuta un movimiento hacia la derecha 
	 * del tablero. Devuelve un objeto con los resultados del movimiento
	 */
	public MoveResults executeMoveRight(Cell[][] tablero,Position aux,boolean[][] tabMovidas,int score,int hight,boolean movido){
		int cont=0;
		for(int i=0;i<boardSize;i++){
			aux=new Position(i,boardSize-1);
			for(int j= boardSize-1; j>=0;j--){
				if(estaOcupado(i,j)&&!coincide(aux,i,j)){/**si la baldosa tiene un valor en pos actual y pos actual no coincide con pos destino*/
					if(!estaOcupado(aux.getFila(),aux.getColumna())){/**si es null la pos destino*/
						tablero[aux.getFila()][aux.getColumna()]= new Cell(tablero[i][j].getValue());
						tablero[i][j]=null;
						cont++;
					}
					else {
						if(!tabMovidas[aux.getFila()][aux.getColumna()]&&tablero[aux.getFila()][aux.getColumna()].doMerge(tablero[i][j])){
							tabMovidas[aux.getFila()][aux.getColumna()]=true;
							tablero[i][j]=null;
							score=score+tablero[aux.getFila()][aux.getColumna()].getValue();
							cont++;
						}
						else{/**si no son iguales los valores entonces se coloca en una pos adyacente segun la dir*/
							/**si no son iguales los valores entonces se coloca en una pos adyacente segun la dir*/
							if(esValida(i,aux.getColumna()-1)){
								aux=new Position(i,aux.getColumna()-1);
								/**Si la nueva pos destino esta libre se crea una nueva celda */
								if((tablero[aux.getFila()][aux.getColumna()]== null)){
									int a;
									a=tablero[i][j].getValue();
									tablero[i][j]=null;
									tablero[aux.getFila()][aux.getColumna()]=new Cell(a);
									cont++;
								}
							}	
						}
					}
				}
			}
		}
		if(cont!=0){
			reset(tablero,1,random);
			movido=true;
		}
		hight=recorrido();
		return new MoveResults(hight,score,movido);
	}
	/**
	 * método que ejecuta un movimiento hacia izquierda 
	 * del tablero. Devuelve un objeto con los resultados del movimiento
	 */
	public MoveResults executeMoveLeft(Cell[][] tablero,Position aux,boolean[][] tabMovidas,int score,int hight,boolean movido){
		int cont=0;
		for(int i=0;i<boardSize;i++){
			aux=new Position(i,0);
			for(int j= 0; j<boardSize;j++){
				if(estaOcupado(i,j)&&!coincide(aux,i,j)){/**si la baldosa tiene un valor en pos actual y pos actual no coincide con pos destino*/
					if(!estaOcupado(aux.getFila(),aux.getColumna())){/**si es null la pos destino*/
						tablero[aux.getFila()][aux.getColumna()]= new Cell(tablero[i][j].getValue());
						tablero[i][j]=null;
						cont++;
					}
					else {
						if(!tabMovidas[aux.getFila()][aux.getColumna()]&&tablero[aux.getFila()][aux.getColumna()].doMerge(tablero[i][j])){
							tabMovidas[aux.getFila()][aux.getColumna()]=true;
							tablero[i][j]=null;
							score=score+tablero[aux.getFila()][aux.getColumna()].getValue();
							cont++;
						}
						else{/**si no son iguales los valores entonces se coloca en una pos adyacente segun la dir*/
							/**si no son iguales los valores entonces se coloca en una pos adyacente segun la dir*/
							if(esValida(i,aux.getColumna()+1)){
								aux=new Position(i,aux.getColumna()+1);
								/**Si la nueva pos destino esta libre se crea una nueva celda */
								if((tablero[aux.getFila()][aux.getColumna()]== null)){
									int a;
									a=tablero[i][j].getValue();
									tablero[i][j]=null;
									tablero[aux.getFila()][aux.getColumna()]=new Cell(a);
									cont++;
								}
							}					
						}
					}
				}
			}
		}
		if(cont!=0){
			reset(tablero,1,random);
			movido=true;
		}
		hight=recorrido();
		return new MoveResults(hight,score,movido);
	}
	
	public void aleatorio(Random rand){
		random=rand;
	}
	/**
	 * Genera las baldosas con un valor aleatoriamente al inicio de la partida
	 */
	public void reset(Cell[][] tablero,int initCells,Random rand) {
		int aux = initCells;
		while (aux > 0) {
			int a = rand.nextInt(getSize());
			int b = rand.nextInt(getSize());
			if (addSemillaNueva(tablero,a, b,rand)) {
				aux = aux - 1;
			}
		}
	}
	
	private boolean addSemillaNueva(Cell[][] tablero,int f, int c,Random rand) {
		int aux;
		if (tablero[f][c] == null) {
			aux=probabilidadSemilla(rand);
			tablero[f][c] = new Cell(aux);
			return true;
		} else
			return false;
	}
	/**
	 * Pone los valores de las baldosas a null
	 */
	public void vaciarTablero(int size) {
		for (int f = 0; f < getSize(); f++) {
			for (int c = 0; c < getSize(); c++) {
				this.board[f][c] = null;
			}
		}
	}
	/**
	 * Genera un numero aleatorio
	 */
	public int probabilidadSemilla(Random rand){
		 int random= rand.nextInt(100)+1;
	        int number=0;
	        if (random > 90) {
	            number = 4;
	        } 
	        else if (random <= 90) {
	            number=2;
	        }
	 return number;
	}
	/**
	 * Devuelve el valor mas alto del tablero
	 */
	public int recorrido(){
	int highest=2;
	for(int f=0;f<getSize();f++){
		for(int c=0;c<getSize();c++){
			if(board[f][c]!=null){
				if(board[f][c].getValue()>highest){
					highest=board[f][c].getValue();
				}
			}	
		}
	}
	return highest;
	}
	
	public int getSize() {
		return boardSize;
	}
	/**
	 * Comprueba si una baldosa esta ocupada
	 */
	public boolean estaOcupado(int f, int c) {
		if (this.board[f][c] != null) {
			return true;
		} else
			return false;
	}
	/**
	 * Comprueba si la posicion actual coincide con las posicion de destino,
	 * si coincide devolvera false
	 */
	public boolean coincide(Position aux,int f, int c){
		if(f!=aux.getFila()||c!=aux.getColumna()){
			return false;
		}
		else/**Concide la posicion*/
			return true;
	}
	public Cell[][] getBoard(){
		return this.board;
	}
	
	/**Si la posicion esta en un rango valido del tablero devuelve true*/
	public boolean esValida(int f, int c) {
		if (f < boardSize && f >= 0 && c >= 0 && c < boardSize) {
			return true;
		} else
			return false;
	}

	/**
	 * Imprime el estado del tablero
	 */
	public String toString() {
		StringBuilder render = new StringBuilder();
		int height = getSize();
		int width = getSize();
		for (int r = 0; r < height; ++r) {
			render.append("  +");
			for (int c = 0; c < width; ++c)
				render.append("-----+");
			render.append("\n");
			render.append("" + (r % 10) + " |");
			for (int c = 0; c < width; ++c) {
				if (board[r][c] == null) {
					render.append("     |");
				} 
				else {
					if(board[r][c].getValue()<9){
						render.append("  " + board[r][c].getValue()+ "  |");
					}
					else if(board[r][c].getValue()>9){
						render.append("  " + board[r][c].getValue()+ " |");
					}
					else if(board[r][c].getValue()>99){
						render.append(" " + board[r][c].getValue()+ " |");
					}
					else if(board[r][c].getValue()>999){
						render.append(" " + board[r][c].getValue()+ "|");
					}
				}
			} // for columns
			render.append("\n");
		} // for rows
		render.append("  +");
		for (int c = 0; c < width; ++c)
			render.append("-----+");
		render.append("\n");
		render.append("   ");
		for (int c = 0; c < width; ++c)
			render.append("  " + (c % 10) + "   ");
		render.append("\n");
		return render.toString();
	}
	
}

