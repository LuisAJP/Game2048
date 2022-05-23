package tp.pr3.control;

import java.io.IOException;
import java.util.Scanner;
import tp.pr3.comandos.Command;
import tp.pr3.comandos.CommandParser;
import tp.pr3.exceptions.CommandParseException;
import tp.pr3.exceptions.EmptyStackException;
import tp.pr3.exceptions.FicheroNoEncontrado;
import tp.pr3.exceptions.GameOverException;
import tp.pr3.exceptions.CommandExecuteException;
import tp.pr3.logica.Game;

/**
 * Clase Controlador que permitira la interaccion con el usuario de la aplicacion
 */
public class Controller {
	
	private static Game game;
	private static Scanner scanner;
	
	
	public Controller(Game game1, Scanner scan){
		Controller.game=game1;
		Controller.scanner = scan;
	}
	
	/**
	 * Metodo que pide los datos al usuario y parsea las opciones introducidas
	 * @throws CommandParseException 
	 * @throws EmptyStackException 
	 * @throws GameOverException 
	 * @throws FicheroNoEncontrado 
	 * @throws IOException 
	 */
	public void run() throws CommandParseException, EmptyStackException, GameOverException {
		String cadena;
		boolean refresh = true;
		game.reset();
		while (!game.getTerminada()) {
			
			try {
				if(refresh) {
					System.out.println(game.getBoard().toString());
					System.out.print("  best value: "+ game.getMejorValor()+ "            score: " + game.getScore() );
					System.out.println();
					System.out.println();
				}
				refresh = false;
				System.out.print("Command>  ");
				String com = scanner.nextLine().toLowerCase();
				System.out.println();
				com=com.trim();
				String[] cadenaArray = com.split(" +");
				Command comando= CommandParser.parseCommand(cadenaArray, scanner);
				
				if (comando != null) {
					
					refresh = comando.execute(game);
					
					if(game.getTerminada()&&game.getGameRules().win(game.getBoard())){	
						cadena="Congratulations, You Win!!!";
						throw new GameOverException(cadena);
						
					}
					else if(game.getTerminada()){
						System.out.println(game.getBoard().toString());
						System.out.print("  best value: "+ game.getMejorValor()+ "            score: " + game.getScore() );
						System.out.println();
						System.out.println();
						cadena="Game Over!";
						throw new GameOverException(cadena);
						
					}
				} 
				else error(cadenaArray);	
			}
			catch (CommandExecuteException e) {
				System.out.println(e.getMessage());
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (CommandParseException e) {
				System.out.println(e.getMessage());
			} 
			catch (EmptyStackException e) {
				System.out.println(e.getMessage());
			} 
			catch (GameOverException e) {
				System.out.println(e.getMessage());
			} 
		}
	}
	
	
	
	public Game getGame(){
		return Controller.game;
	}
	public static void setGame(Game game1){
		Controller.game=game1;
	}
	
	public void error(String [] cadenaArray) {
		switch(cadenaArray[0]){
		case "move":
			if (cadenaArray.length==1)
				System.out.println("Move must be followed by a direction: up, down, left, right");
			else 
				System.out.println("Unknown direction for move command");
			break;
		case "play":	
			if (cadenaArray.length==1)
				System.out.println("Play must be followed by a game type: original, fib, inverse");
			else 
				System.out.println("Unknown game type for play command");
			break;
		default:
			System.out.println("Unknown command. Use ’help’ to see the available commands");
			break;
		}
		
	}
	
}
