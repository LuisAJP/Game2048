package tp.pr2.main;
import java.util.Random;
import java.util.Scanner;

import tp.pr2.control.Controller;
import tp.pr2.logica.Game;
import tp.pr2.logica.Rules2048;
/**
 * @author Luis Alberto Jaramillo Pulido
 * @author Diego Gómez Rodríguez
 * 
 */
public class Game2048 {
	private static long seed;
	/**
	 * Esta es la clase con el metodo main de inicio del programa.
	 */
	public static void main(String[] args){
		Random rand=null;
		rand = new Random();
		
		if(args.length==2){
			//rand = new Random();
			
			seed= rand.nextInt();//generas un numero aleatorio
		}
		else if(args.length==3){/**Si se introduce opcionalmente una semilla*/
		 	seed = Long.parseLong(args[2]);//numero dado por el usuario
			//rand = new Random(seed);
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
	
	/**
	 * Muestra mensaje de error
	 */
	private static void usage() {
		System.out.println("Error");
		}
	
}
