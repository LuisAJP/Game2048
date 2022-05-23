package tp.pr3.main;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import tp.pr3.control.Controller;
import tp.pr3.exceptions.CommandParseException;
import tp.pr3.exceptions.EmptyStackException;
import tp.pr3.exceptions.GameOverException;
import tp.pr3.logica.Game;
import tp.pr3.logica.Rules2048;
/**
 * @author Luis Alberto Jaramillo Pulido
 * @author Diego Gómez Rodríguez
 * 
 */
public class Game2048 {
	private static long seed;
	/**
	 * Esta es la clase con el metodo main de inicio del programa.
	 * @throws CommandParseException 
	 * @throws EmptyStackException 
	 * @throws GameOverException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws CommandParseException, EmptyStackException, GameOverException {
		Random rand=null;
		rand = new Random();
		
		try {
			if(args.length==2){
				seed= rand.nextInt();//generas un numero aleatorio
			}
			else if(args.length==3){/**Si se introduce opcionalmente una semilla*/
			 	seed = Long.parseLong(args[2]);//numero dado por el usuario
			}
			
			else {
				usage();
				System.exit(1);
			}
			
				Scanner scan = new Scanner(System.in);
				Game game= new Game(new Rules2048(),Integer.parseInt(args[0]),Integer.parseInt(args[1]), seed); 
				Controller control = new Controller( game,scan );
				control.run();
		}
		catch(NumberFormatException e) {
			System.out.println("The command-line arguments must be numbers");
		}
	}
	
	/**
	 * Muestra mensaje de error
	 */
	private static void usage() {
		System.out.println("Error");
		}
	
}
