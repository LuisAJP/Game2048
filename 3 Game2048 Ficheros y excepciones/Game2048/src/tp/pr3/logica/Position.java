package tp.pr3.logica;
/**
 * clase que permite representar posiciones del tablero.
 */
public class Position {
	/**
	 *Atributos
	 */
	private int fila;
	private int columna;
	
	/**
	 *Metodos accesores
	 */
	public Position (int fil, int col){
		this.fila=fil;
		this.columna=col;
	}
	
	public int getFila(){
		return this.fila;
	}
	
	public int getColumna(){
		return this.columna;
	}
}
