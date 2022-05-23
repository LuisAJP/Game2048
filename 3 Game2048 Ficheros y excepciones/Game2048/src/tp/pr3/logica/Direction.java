package tp.pr3.logica;

/**
 * Enumerado que representa la direcci�n de un movimiento. Contiene las
 *constantes de enumeraci�n UP, DOWN, LEFT y RIGHT.
 */
public enum Direction{	
	UP,
	DOWN,
	LEFT,
	RIGHT;	
	
	
	/**
	 * Parsea la direccion introducida y devuelve dir
	 */
	public static Direction convertirCadenaDirection (String cadena){
		Direction dir=null;
		String s=cadena.toUpperCase();
		switch(s){
		case "UP":
			dir=Direction.UP;
			break;
		case "DOWN":
			dir=Direction.DOWN;
			break;
		case "RIGHT":
			dir=Direction.RIGHT;
			break;
		case "LEFT":
			dir=Direction.LEFT;
			break;
		}
		return dir;
	}
	
}


