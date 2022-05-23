package tp.pr3.logica;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Board {
	private Cell[][] board; 
	private int boardSize;
	private static Random random;
	private GameStateStack gameStateStack;
	private static int [][]m;
	private static String s;
	private static int tam;
	/**Constructor*/
	public Board(int size){	
		this.boardSize=size;
		this.board= new Cell[boardSize][boardSize];
		this.gameStateStack= new GameStateStack();
	}
	/**
	 * Método que ejecuta las dos primeras
	 * acciones de un movimiento (desplazar y fusionar) en la dirección dir
	 * del tablero. Devuelve un objeto con los resultados del movimiento
	 */
	public MoveResults executeMove(GameRules rules,Cell[][] tablero,Direction dir){
		MoveResults moveResults = null;
		int score=0;
		boolean movido=false;
		boolean[][] tabMovidas = new boolean[boardSize][boardSize];
		Position aux=null;
		switch(dir){
		case UP:
			moveResults =executeMoveUp(rules,tablero,aux,tabMovidas, score,movido);
			break;
		case DOWN:
			moveResults =executeMoveDown(rules,tablero,aux,tabMovidas, score,movido);
			break;
		case RIGHT:
			moveResults =executeMoveRight(rules,tablero,aux,tabMovidas, score,movido);
			break;
		case LEFT:
			moveResults =executeMoveLeft(rules,tablero,aux,tabMovidas, score,movido);
			break;
		}
		return moveResults;
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
	
	/**Crea un tablero auxiliar, evalua cada movimiento, si existe movimiento lo almacena en un contador*/
	public boolean hayMasMovimientos(GameRules rules){
		Cell[][] aux;
		aux= new Cell[boardSize][boardSize];
		for(int i = 0; i < this.boardSize; i++) {
			for(int j = 0; j < this.boardSize; j++) {
				swap(this.board, aux,  i,  j);
			}
		}
		
		/*
		for(int i = 0; i < this.boardSize; i++) {
			for(int j = 0; j < this.boardSize -1; j++) {
				if(this.board[i][j] == null || this.board[i][j + 1] == null || this.board[i][j].getValue()  == this.board[i][j + 1].getValue() ) {
				//if(this.board[i][j] == null || this.board[i][j + 1] == null || this.board[i][j].doMerge( rules,this.board[i][j + 1])!=0) {	
					return true;
				}

			}
		}
		*/
		/**recorre cada fila de una columna  */
		/*
		for(int j = 0; j < this.boardSize; j++) {
			for(int i = 0; i < this.boardSize -1; i++) {
				if(this.board[i][j] == null || this.board[i + 1][j ] == null || this.board[i][j].getValue()  == this.board[i + 1][j].getValue() ) {
				//if(this.board[i][j] == null || this.board[i + 1][j ] == null || this.board[i][j].doMerge( rules,this.board[i+1][j ])!=0 ) {	
					return true;
				}

			}
		}*/
		
		
		
		for(int i = 0; i < this.boardSize; i++) {
			for(int j = 0; j < this.boardSize -1; j++) {
				//if(this.board[i][j] == null || this.board[i][j + 1] == null || this.board[i][j].getValue()  == this.board[i][j + 1].getValue() ) {
				if(aux[i][j] == null || aux[i][j + 1] == null || aux[i][j].doMerge( rules,aux[i][j + 1])!=0) {	
					return true;
				}

			}
		}
		for(int j = 0; j < this.boardSize; j++) {
			for(int i = 0; i < this.boardSize -1; i++) {
				//if(this.board[i][j] == null || this.board[i + 1][j ] == null || this.board[i][j].getValue()  == this.board[i + 1][j].getValue() ) {
				if(aux == null || aux[i + 1][j ] == null || aux[i][j].doMerge( rules,aux[i+1][j ])!=0 ) {	
					return true;
				}

			}
		}
		return false;	
	}
	
	/**copia los valores del tablero en una matriz*/
	public int[][] getState(){
	
		int [][]matriz =new int[boardSize][boardSize];
		for (int i=0;i<boardSize;i++) {
			for(int j=0;j<boardSize;j++) {
				if(this.board[i][j]!=null) {
					matriz[i][j]=this.board[i][j].getValue();
				}
				
			}
		}
		return matriz;
	}
	
	
	/**Recibe una matriz de enteros y copia los valores de la matriz en el tablero*/
	public void setState(GameState state) {
		vaciarTablero(boardSize);
		int aux=0;
		for (int i=0;i<boardSize;i++) {
			for(int j=0;j<boardSize;j++) {
				if(state.getState()[i][j]!=0) {
					aux=state.getState()[i][j];
					this.board[i][j]=new Cell(aux);
				}
				
			}
		}
		
	}
	/**
	 * método que ejecuta un movimiento hacia arriba 
	 * del tablero. Devuelve un objeto con los resultados del movimiento
	 */
	public MoveResults executeMoveUp(GameRules rules,Cell[][] tablero,Position aux,boolean[][] tabMovidas,int score,boolean movido){
		int cont=0;
//		int auxiliar = 0;
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
						//auxiliar = tablero[aux.getFila()][aux.getColumna()].doMerge(rules, tablero[j][i]);
						if(!tabMovidas[aux.getFila()][aux.getColumna()]&&/* rules.canMergeNeighbours(tablero[aux.getFila()][aux.getColumna()], tablero[i][j])*/tablero[aux.getFila()][aux.getColumna()].doMerge(rules, tablero[j][i]) != 0){
							//score=score + tablero[aux.getFila()][aux.getColumna()].doMerge(rules, tablero[j][i]);
							tabMovidas[aux.getFila()][aux.getColumna()]=true;
							tablero[j][i]=null;
							score=score+ rules.getScore(tablero[aux.getFila()][aux.getColumna()]);
							//score= score + auxiliar;
							
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
			rules.addNewCell(this, random);
//			reset(rules,tablero,1,random);
			movido=true;
		}
		return new MoveResults(score,movido);
	}
	
	/**
	 * método que ejecuta un movimiento hacia abajo
	 * del tablero. Devuelve un objeto con los resultados del movimiento
	 */
	public MoveResults executeMoveDown(GameRules rules,Cell[][] tablero,Position aux,boolean[][] tabMovidas,int score,boolean movido){
		int cont=0;
//		int auxiliar = 0;
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
						//auxiliar = tablero[aux.getFila()][aux.getColumna()].doMerge(rules, tablero[j][i]);
						if(!tabMovidas[aux.getFila()][aux.getColumna()]&& tablero[aux.getFila()][aux.getColumna()].doMerge(rules, tablero[j][i])!= 0){
							tabMovidas[aux.getFila()][aux.getColumna()]=true;
							tablero[j][i]=null;
							//score= score + auxiliar;
							score=score + rules.getScore(tablero[aux.getFila()][aux.getColumna()]);
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
			rules.addNewCell(this, random);
//			reset(rules,tablero,1,random);
			movido=true;
		}
		return new MoveResults(score,movido);
	}
	
	/**
	 * método que ejecuta un movimiento hacia la derecha 
	 * del tablero. Devuelve un objeto con los resultados del movimiento
	 */
	public MoveResults executeMoveRight(GameRules rules,Cell[][] tablero,Position aux,boolean[][] tabMovidas,int score,boolean movido){
		int cont=0;
//		int auxiliar = 0;
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
						 //auxiliar = tablero[aux.getFila()][aux.getColumna()].doMerge(rules, tablero[i][j]);
						if(!tabMovidas[aux.getFila()][aux.getColumna()]&& tablero[aux.getFila()][aux.getColumna()].doMerge(rules, tablero[i][j]) != 0){
							tabMovidas[aux.getFila()][aux.getColumna()]=true;
							tablero[i][j]=null;
							//score = score + auxiliar;
							score=score+ rules.getScore(tablero[aux.getFila()][aux.getColumna()]);
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
			rules.addNewCell(this, random);
//			reset(rules,tablero,1,random);
			movido=true;
		}
		return new MoveResults(score,movido);
	}
	/**
	 * método que ejecuta un movimiento hacia izquierda 
	 * del tablero. Devuelve un objeto con los resultados del movimiento
	 */
	public MoveResults executeMoveLeft(GameRules rules,Cell[][] tablero,Position aux,boolean[][] tabMovidas,int score,boolean movido){
		int cont=0;
//		int auxiliar = 0;
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
						//auxiliar = tablero[aux.getFila()][aux.getColumna()].doMerge(rules, tablero[i][j]);
						//if(!tabMovidas[aux.getFila()][aux.getColumna()]&& auxiliar != 0){
						if(!tabMovidas[aux.getFila()][aux.getColumna()]&&tablero[aux.getFila()][aux.getColumna()].doMerge(rules, tablero[i][j]) != 0){
							tabMovidas[aux.getFila()][aux.getColumna()]=true;
							tablero[i][j]=null;
							score=score+ rules.getScore(tablero[aux.getFila()][aux.getColumna()]);
							//score = score + auxiliar;
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
			rules.addNewCell(this, random);
			//reset(rules,tablero,1,random);
			movido=true;
		}
		return new MoveResults(score,movido);
	}
	
	public void aleatorio(Random rand){
		random=rand;
	}
	
	/**
	 * Genera las baldosas con un valor aleatoriamente al inicio de la partida
	 */
	public void reset(GameRules rules,Cell[][] tablero,int initCells,Random rand) {
		int aux = initCells;
		Position position;
		while (aux > 0) {
			int a = rand.nextInt(getSize());
			int b = rand.nextInt(getSize());
			position=new Position(a,b);
			if (rules.addNewCellAt(this,position,rand)) {//si hay celdas libres se añade semilla 
				aux = aux - 1;
			}
		}
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
	
	public GameStateStack getGameStateStack() {
		return this.gameStateStack;
	}

	/* este metodo realiza una copia del tipo board actual*/
	public Board boardCopy(){
		Board newBoard = new Board(this.boardSize);
		for ( int i = 0; i < this.boardSize; i++){
			for( int j = 0; j < this.boardSize; j++){
				if(this.getBoard()[i][j] != null)
					newBoard.getBoard()[i][j] = new Cell (this.getBoard()[i][j].getValue());
				else 
					newBoard.getBoard()[i][j] = null;
			}
		}
		return newBoard;
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
				else if (board[r][c] != null){
					if(board[r][c].getValue()<9){
						render.append("  " + board[r][c].getValue()+ "  |");
					}
					else if(board[r][c].getValue()<99){
						render.append("  " + board[r][c].getValue()+ " |");
					}
					else if(board[r][c].getValue()<999){
						render.append(" " + board[r][c].getValue()+ " |");
					}
					else if(board[r][c].getValue()<9999){
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
	
	
	public void load(String linea,Scanner scanner) {
		// TODO Auto-generated method stub
		linea = scanner.nextLine();
		String[] cadenaArray = linea.split(" +");
		tam=cadenaArray.length;
		m=new int[tam][tam];
		 for(int i=0;i<tam;i++) {
			 for(int j=0;j<tam;j++) {
				 m[i][j]=Integer.parseInt(cadenaArray[j]);	
	    	 }
			 linea = scanner.nextLine();
			 cadenaArray = linea.split(" +");
    	 }
		 linea = scanner.nextLine();
		 cadenaArray = linea.split(" +");
		 s=linea;
	}
	
	
	public int [][]getMatriz(){
		return m;
	}
	public String getS(){
		return s;
	}
	public int getTam() {
		return tam;
	}
	
	public void store(FileWriter fichero,Game game) {
		// TODO Auto-generated method stub
		PrintWriter linea = new PrintWriter(fichero);
		linea.println("This file stores a saved 2048 game");
		linea.println();
		for(int i=0;i<boardSize;i++) {
			for(int j=0;j<boardSize;j++) {
				linea.print(game.getState().getState()[i][j]+" ");//Game y GameState tienen un metodo getState
			}
			linea.println();
		}
		linea.println();
		linea.println(game.getInitCells()+" "+game.getScore()+ " "+ game.getGameType().externalise() );
		
	}
	
	
	
	
	
	private static void swap(Cell[][] anArray,Cell[][] aux, int i, int j) {
	if(anArray[i][j]!=null){
		int temp=anArray[i][j].getValue();
		aux[i][j]=new Cell(temp);
	}
	}
}

