package tp.pr2.logica;
/**
 * Clase que permite representar los resultados de la ejecución de un movimiento
 * en Score y Highest 
 */
public class MoveResults {
	/**
	 * Atributos
	 */

	private int score;
	private boolean movido;
	
	public MoveResults( int score, boolean mov) {
	
		this.score=score;
		this.movido=mov;
	}
	/**
	 * Metodos Accesores
	 */
	public int getScore(){
		return this.score;
	}
	public boolean getMovido(){
		return this.movido;
	}
}
