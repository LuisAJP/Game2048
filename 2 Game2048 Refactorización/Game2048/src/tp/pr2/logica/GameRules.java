package tp.pr2.logica;

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
	
	int puntuacion(Cell[][] tablero, Position position, int score);
	
	
	/**DEFAULT METHODS*/
	
	/**Devuelve si el juego se ha perdido o no.*/
	default boolean lose() {
		return false;
	}
	/**Cuya implementaci�n por defecto crea y devuelve un tablero size � size,*/
	default Board createBoard(int size) {
		return new Board(size);
	}
	
	/**Cuya implementaci�n por defecto elige una posici�n libre de board e invoca
	 *  el m�todo addNewCellAt() para a�adir una c�lula en esa posici�n*/
	default void addNewCell(Board board, Random rand) {
		
	}
	
	//RESET
	/**Cuya implementaci�n por defecto inicializa board eligiendo numCells posiciones libres,
	 * e invoca el m�todo addNewCellAt() para a�adir nuevas c�lulas en esas posiciones*/
	default void initBoard(Board board, int numCells, Random rand) {
	
	}



}
