package tp.pr1;
/**
 * Clase que permite representar los resultados de la ejecución de un movimiento
 * en Score y Highest 
 */
public class MoveResults {
	/**
	 * Atributos
	 */
	private int highest;
	private int score;
	private boolean movido;
	
	public MoveResults(int highest1, int score, boolean mov) {
		this.highest=highest1;
		this.score=score;
		this.movido=mov;
	}
	/**
	 * Metodos Accesores
	 */
	public int getHighest(){
		return this.highest;
	}
	public int getScore(){
		return this.score;
	}
	public boolean getMovido(){
		return this.movido;
	}
}
