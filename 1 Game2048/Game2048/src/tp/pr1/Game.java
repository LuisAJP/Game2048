package tp.pr1;

import java.util.Random;
/**
 *Clase para representar el estado de una partida (tablero, dimensión del tablero,
 *número de baldosas en la configuración inicial y un objeto de la clase java.
 *util.Ramdom para gestionar el comportamiento aleatorio del juego). Entre sus
 *atributos privados se encuentran 
 */
public class Game {
	private Board board;//tablero de celdas
	private int size;//tamaño del tablero
	private int initCells;//numero de baldosas iniciales
	private Random myRandom;
	private int score;
	private int highest;
	private boolean terminada;
	
	public Game(int dim, int baldosas,Random rand){
		this.size=dim;
		this.initCells=baldosas;
		this.myRandom=rand;
		this.terminada =false;
		this.highest=0;
		this.score=0;
		this.board= new Board(size);
		
	}
	/**
	 * Reinicia la partida
	 */
	public void reset() {
		vaciar();
		this.highest=0;
		this.score=0;
		board.reset(this.board.getBoard(),initCells,myRandom);
		highest=board.recorrido();
	}
	
	private void vaciar() {
		board.vaciarTablero(size);
	}
	
	public Board getBoard(){
		return this.board;
	}
	
	/**
	 * M�todo booleano que nos dice si se ha introducido la opci�n salir
	 * 
	 * @return
	 */
	public boolean FinEjecucion() {
		return terminada;
	}
	
	/**Le dice al tablero que haga un movimiento en la dirección dir, actualiza
	 *los resultados tras hacer el movimiento y averigua si la partida ha acabado
	 */
	public void move(Direction dir){
		boolean mov=true;
		MoveResults results;
		board.aleatorio(getMyRandom());
		results=board.executeMove(board.getBoard(),dir);
		this.score=this.score + results.getScore();
		this.highest=results.getHighest();
		this.terminada=board.isWinner(highest);
		mov=board.noHayMasMovimientos();
		if(!mov){
			this.terminada=true;
		}
		
	}
	
	public int getHighest(){
		return this.highest;
	}
	public int getScore(){
		return this.score;
	}
	
	public Random getMyRandom(){
		return this.myRandom;
	}
	public boolean getTerminada(){
		return this.terminada;
	}
	public void setTerminada(boolean b){
		this.terminada=b;
	}
	/**
	 * Parsea la direccion introducida y devuelve dir
	 */
	public Direction convertirCadenaDirection (String cadena){
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
	
	public void ayuda() {
		System.out.println("   Move <direction>: execute a move in one of the four directions,up, down, left, right");
		System.out.println("   Reset: start a new game");
		System.out.println("   Help: print this help message");
		System.out.print("   Exit: terminate the program");
		System.out.println();
	}
}
