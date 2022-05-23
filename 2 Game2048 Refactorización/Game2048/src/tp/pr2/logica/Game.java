package tp.pr2.logica;

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
	private long seed;
	private int score;
	private int mejorValor;
	private boolean terminada;
	private GameRules currentRules;
	private static GameState gameState;
	private static int posActual=1;
	private static boolean undo=false;
	
	public Game(GameRules rules,int dim, int baldosas,long seed){
		this.currentRules=rules;
		this.size=dim;
		this.initCells=baldosas;
		this.seed=seed;
		this.myRandom=new Random(this.seed);
		this.terminada =false;
		this.mejorValor=0;
		this.score=0;
		this.board=currentRules.createBoard(this.size);
	}
	/**
	 * Reinicia la partida
	 */
	public void reset() {
		vaciar();
		this.mejorValor=0;
		this.score=0;
		this.board.reset(currentRules,this.board.getBoard(),initCells,myRandom);
		this.mejorValor=currentRules.getWinValue(this.board);
		almacenaMovimiento();
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
		undo =false;
		posActual=1;
		boolean mov=true;
		MoveResults results;
		board.aleatorio(getMyRandom());
		results=board.executeMove(currentRules,board.getBoard(),dir);
		mejorValor=currentRules.getWinValue(board);
		this.score=this.score + results.getScore();
		
		almacenaMovimiento();
		
		
		this.terminada=this.currentRules.win(this.board);
		mov=board.noHayMasMovimientos();
		if(!mov){
			this.terminada=true;
		}
	}
	
	public void almacenaMovimiento() {
		int [][] m=board.getState();
		gameState=new GameState(m,this.mejorValor,this.score);
		this.board.getGameStateStack().almacenar(gameState);
	}
	
	/**restablece el juego al estado que ten�a antes del �ltimo movimiento,*/
	public void undo(){
		undo=true;
		if( this.board.getGameStateStack().getContador()-posActual<=0) {
			System.out.println("Undo is not available");
		}
		else {
			gameState=this.board.getGameStateStack().getArray()[(this.board.getGameStateStack().getContador()-1)-posActual];
			posActual++;
			this.board.setState(gameState);
			this.score=gameState.getScore();
			this.mejorValor=gameState.getHighest();
			System.out.println("Undoing one move...");
		}
		
	}
	/**permite ejecutar de nuevo un comando previamente realizado*/
	public void redo(){
		if (posActual!=1&&undo) {
			gameState=this.board.getGameStateStack().getArray()[((this.board.getGameStateStack().getContador()-1)-posActual)+2];
			posActual--;
			this.board.setState(gameState);
			this.score=gameState.getScore();
			this.mejorValor=gameState.getHighest();
			System.out.println("Redoing one move...");
		}else {
			System.out.println("Nothing to redo");
		}
	}
	
	public GameState getState() {
		return gameState;
		
	}
	
	public void setState(GameState state) {
		gameState=state;
		
	}
	public int getMejorValor(){
		return this.mejorValor;
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
	
	public GameRules getGameRules() {
		return this.currentRules;
	}
	
	public long getSeed() {
		return this.seed;
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
	/**
	 * Parsea el juego introducido y devuelve gameType
	 */
	public GameType convertirCadenaGameType(String cadena) {
		GameType gameType=null;
		String s=cadena.toUpperCase();//la cadena lo convierte a mayuscula
		switch(s){
		case "FIB":
			gameType=GameType.FIB;
			break;
		case "INVERSE":
			gameType=GameType.INV;
			break;
		case "ORIG":
			gameType=GameType.ORIG;
			break;
		}
		return gameType;
	}
	
}
