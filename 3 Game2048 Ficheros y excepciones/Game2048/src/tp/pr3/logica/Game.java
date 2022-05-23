package tp.pr3.logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import tp.pr3.comandos.CommandParser;
import tp.pr3.control.Controller;
import tp.pr3.exceptions.EmptyStackException;
import tp.pr3.exceptions.FileContentsException;
import tp.pr3.exceptions.GameOverException;
import tp.pr3.exceptions.YesOrNoException;
import tp.pr3.exceptions.CommandExecuteException;



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
	private static GameType gameType=GameType.ORIG ;
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
	 * @throws GameOverException 
	 */
	public boolean move(Direction dir) throws GameOverException{
		undo =false;
		posActual=1;
		boolean mov=true;
		MoveResults results;
		board.aleatorio(getMyRandom());
		results=board.executeMove(currentRules,board.getBoard(),dir);
		mejorValor=currentRules.getWinValue(board);
		this.score=this.score + results.getScore();
		if(results.getMovido())
			almacenaMovimiento();
		this.terminada=this.currentRules.win(this.board);
		mov = this.getGameRules().lose(this.getBoard());
		mov=board.hayMasMovimientos(this.currentRules);
		
		if(!mov){
			this.terminada=true;
			throw new GameOverException("No hay mas movimientos, Game Over!!");
		}
		return results.getMovido();
	}
	
	public void almacenaMovimiento() {
		int [][] m=board.getState();
		gameState=new GameState(m,this.mejorValor,this.score);
		this.board.getGameStateStack().almacenar(gameState);
	}
	
	/**Restablece el juego al estado que ten�a antes del �ltimo movimiento,
	 * @throws EmptyStackException */
	public boolean undo() throws EmptyStackException{
	
		undo=true;
		if( this.board.getGameStateStack().getContador()-posActual<=0) {
			
			throw new EmptyStackException();	
		}
		else {
			gameState=this.board.getGameStateStack().getArray()[(this.board.getGameStateStack().getContador()-1)-posActual];
			posActual++;
			this.board.setState(gameState);
			this.score=gameState.getScore();
			this.mejorValor=gameState.getHighest();
			System.out.println("Undoing one move...");
			return true;
		}
		
	}
	
	/**permite ejecutar de nuevo un comando previamente realizado
	 * @throws EmptyStackException */
	public boolean redo() throws EmptyStackException{
		if (posActual!=1&&undo) {
			gameState=this.board.getGameStateStack().getArray()[((this.board.getGameStateStack().getContador()-1)-posActual)+2];
			posActual--;
			this.board.setState(gameState);
			this.score=gameState.getScore();
			this.mejorValor=gameState.getHighest();
			System.out.println("Redoing one move...");
			return true;
		}else {
			throw new EmptyStackException();
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
	public GameType getGameType() {
		return gameType;
	}
	
	public void setGameType(GameType gType) {
		gameType=gType;
	}
	public int getInitCells() {
		return this.initCells;
	}
	public void ayuda() {	
		System.out.println("The available commands are:");
		System.out.println();
		CommandParser.commandHelp();
			
	}
	public boolean getUndo() {return undo;}
	
	
	public void jugar(Game game, GameType gType, Scanner scanner,int size,int initCells,int seed) {
		game=new Game(gType.getRules(),size,initCells,seed);
		setGameType(gType);
		Controller.setGame(game);
		game.reset();
	}
	

	public boolean load(String nomFichero, Scanner scanner) throws CommandExecuteException, FileContentsException {
//		String error;
		File fichero = null;
		scanner = null;	
		int [][] matriz=null;
		try {
			fichero = new File(nomFichero);
			scanner = new Scanner(fichero);
			String linea = scanner.nextLine();
			 scanner.nextLine();//lee espacio en blanco
			if (linea.equals("This file stores a saved 2048 game")) {
				this.board.load(linea,scanner);
				matriz=board.getMatriz();
				linea=board.getS();
				scanner.close();
				
			}
			else { 
				scanner.close();
				throw new  FileContentsException("existe un error");
			}
			
			linea.toLowerCase();
			String [] l=linea.split(" +");
			GameType gType=GameType.parse(l[2]);
			long sem=getSeed();
			Game game=new Game(gType.getRules(),board.getTam(),Integer.parseInt(l[0]),sem);
			
			gameState=new GameState(matriz,0,0);//
			game.board.setState(gameState);
			game.setScore(Integer.parseInt(l[1]));
			int a=game.getGameRules().getWinValue(game.getBoard());
			game.setMejorValor(a);
			
			gameState=new GameState(matriz,game.getMejorValor(),game.getScore());
			game.board.getGameStateStack().almacenar(gameState);	
			Controller.setGame(game);
			System.out.println("Game successfully loaded from file: "+ gameType.toString()  );
			return true;
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		return false;
		
	}
	
	
	
	public void store(String fileName,  Scanner scanner) throws IOException {
		// TODO Auto-generated method stub
		FileWriter fichero = null;
			try {
				fichero = new FileWriter(fileName);
				board.store(fichero,this);
				System.out.println("Game successfully saved to file; use load command to reload it. " );

			} catch (IOException e) {
				e.printStackTrace();

			} finally {
				fichero.close();
			}
	}
		
	
	public void setScore(int sco) {
		this.score=sco;
	}
	public void setMejorValor(int valor) {
		this.mejorValor=valor;
	}
	
}
