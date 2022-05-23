package tp.pr3.logica;

import java.util.Random;

public interface GameRules {
	
	/**ABSTRACT METHODS*/
	
	/**Incorpora una célula con valor aleatorio en la posición pos del tablero board*/
	boolean addNewCellAt(Board board,Position position,Random rand);
	
	/**Fusiona dos células y devuelve el número de puntos obtenido por la fusión*/
	int merge(Cell self, Cell other);
	
	/**Devuelve el mejor valor del tablero, según las reglas de ese juego, 
	 * comprobándose si es un valor ganador y se ha ganado el juego*/
	int getWinValue(Board board);
	
	/**Devuelve si el juego se ha ganado o no*/
	boolean win(Board board);
	
	int getScore(Cell cell);
	
	/**DEFAULT METHODS*/
	
	/**Devuelve si el juego se ha perdido o no.*/
	default boolean lose(Board board) {
		Board auxBoard;
		Direction[] myDirections = Direction.values();
		MoveResults result ;
		for ( int  i = 0; i < myDirections.length; i++ ){
			auxBoard = board.boardCopy();
			result = auxBoard.executeMove(this, auxBoard.getBoard(), myDirections[i]);
			if (result.getMovido())  return true;
			
		}
		return false;
	}
	/**Cuya implementación por defecto crea y devuelve un tablero size × size,*/
	default Board createBoard(int size) {
		return new Board(size);
	}
	
	/**Cuya implementación por defecto elige una posición libre de board e invoca
	 *  el método addNewCellAt() para añadir una célula en esa posición*/
	default void addNewCell(Board board, Random rand) {
		int a = rand.nextInt(board.getSize());
		int b = rand.nextInt(board.getSize());
		while(board.getBoard()[a][b] != null){
			a = rand.nextInt(board.getSize());
			b = rand.nextInt(board.getSize());			
		}
		Position pos = new Position(a,b);
		addNewCellAt(board, pos, rand);
		
	}
	
	//RESET
	/**Cuya implementación por defecto inicializa board eligiendo numCells posiciones libres,
	 * e invoca el método addNewCellAt() para añadir nuevas células en esas posiciones*/
	default void initBoard(Board board, int numCells, Random rand) {
		board.vaciarTablero(board.getSize());
		for(int i = 0; i < numCells; i ++) {
			addNewCell(board, rand);
		}
	}

	default boolean canMergeNeighbours(Cell cell1, Cell cell2 ) {
		if(cell1 != null && cell2 != null && cell1.getValue() == cell2.getValue())return true;
		return false;
	}

}
