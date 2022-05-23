package tp.pr3.logica;

import java.util.Random;

public interface GameRules {
	
	/**ABSTRACT METHODS*/
	
	/**Incorpora una c�lula con valor aleatorio en la posici�n pos del tablero board*/
	boolean addNewCellAt(Board board,Position position,Random rand);
	
	/**Fusiona dos c�lulas y devuelve el n�mero de puntos obtenido por la fusi�n*/
	int merge(Cell self, Cell other);
	
	/**Devuelve el mejor valor del tablero, seg�n las reglas de ese juego, 
	 * comprob�ndose si es un valor ganador y se ha ganado el juego*/
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
	/**Cuya implementaci�n por defecto crea y devuelve un tablero size � size,*/
	default Board createBoard(int size) {
		return new Board(size);
	}
	
	/**Cuya implementaci�n por defecto elige una posici�n libre de board e invoca
	 *  el m�todo addNewCellAt() para a�adir una c�lula en esa posici�n*/
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
	/**Cuya implementaci�n por defecto inicializa board eligiendo numCells posiciones libres,
	 * e invoca el m�todo addNewCellAt() para a�adir nuevas c�lulas en esas posiciones*/
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
