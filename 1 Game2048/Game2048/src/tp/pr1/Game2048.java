package tp.pr1;
import java.util.Random;
import java.util.Scanner;
/**
 * @author Luis Alberto Jaramillo Pulido
 * 
 */
public class Game2048 {
	/**
	 * Esta es la clase con el metodo main de inicio del programa.
	 */
	
	public static void main(String[] args){
		Random rand=null;
		
		if(args.length==2){
			rand = new Random();
		}
		else if(args.length==3){/**Si se introduce opcionalmente una semilla*/
			long seed = Long.parseLong(args[2]);
			rand = new Random(seed);
		}
		else {
			usage();
			System.exit(1);
		}
		Scanner scan = new Scanner(System.in);
		Game game= new Game(Integer.parseInt(args[0]),Integer.parseInt(args[1]), rand); 
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
