package tp.pr2.logica;

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
	
	int puntuacion(Cell[][] tablero, Position position, int score);
	
	
	/**DEFAULT METHODS*/
	
	/**Devuelve si el juego se ha perdido o no.*/
	default boolean lose() {
		return false;
	}
	/**Cuya implementación por defecto crea y devuelve un tablero size × size,*/
	default Board createBoard(int size) {
		return new Board(size);
	}
	
	/**Cuya implementación por defecto elige una posición libre de board e invoca
	 *  el método addNewCellAt() para añadir una célula en esa posición*/
	default void addNewCell(Board board, Random rand) {
		
	}
	
	//RESET
	/**Cuya implementación por defecto inicializa board eligiendo numCells posiciones libres,
	 * e invoca el método addNewCellAt() para añadir nuevas células en esas posiciones*/
	default void initBoard(Board board, int numCells, Random rand) {
	
	}



}
