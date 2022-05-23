package tp.pr1;
import java.util.Scanner;

/**
 * Clase Controlador que permitira la interaccion con el usuario de la aplicacion
 */
public class Controller {
	
	private Game game;
	private Scanner in;
	
	public Controller(Game game, Scanner scan){
		this.game=game;
		this.in = scan;
	}
	
	/**
	 * Metodo que pide los datos al usuario y parsea las opciones introducidas
	 */
	public void run() {
		game.reset();
		while (!game.getTerminada()) {
			System.out.println(game.getBoard().toString());
			System.out.print("  highest: "+ game.getHighest()+ "            score: " + game.getScore() );
			System.out.println();
			System.out.println();
			System.out.print("Command>  ");
			String comando = in.nextLine().toLowerCase();
			comando=comando.trim();
			String[] cadenaArray = comando.split(" +");
			//char a=comando.charAt(5);
			//int asciiValue = (int) a;
			int longitud = cadenaArray.length;
			if (longitud == 1) {
				if (cadenaArray[0].equalsIgnoreCase("MOVE")){
					System.out.println("Move must be followed by a direction: up, down, left or right");
				}
				else if (cadenaArray[0].equalsIgnoreCase("RESET")) {
					game.reset();
				} 
				else if (cadenaArray[0].equalsIgnoreCase("HELP")) {
					game.ayuda();
				} 
				else if (cadenaArray[0].equalsIgnoreCase("EXIT")) {
					System.out.println("Game over");
					game.setTerminada(true);
				} 
				else {
					System.out.println("Unknown command");
				}
			} 
			else if (longitud == 2) {
				if (cadenaArray[0].equalsIgnoreCase("MOVE")) {
					Direction dir= game.convertirCadenaDirection(cadenaArray[1]);
					if(dir!=null){
						game.move(dir);
						if(game.getTerminada()&&game.getBoard().isWinner(game.getHighest())){
							System.out.println("Congratulations, You Win!!!");
						}
						else if(game.getTerminada()){
							System.out.println(game.getBoard().toString());
							System.out.print("  highest: "+ game.getHighest()+ "            score: " + game.getScore() );
							System.out.println();
							System.out.println();
							System.out.println("Game Over, has perdido...");
						}
					}
					else{
						System.out.println("Unknown direction for move command");
					}
					
				} 
				else {
					System.out.println("Unknown command");
				}
			} 
			else {
				System.out.println("Unknown command o mal escrito");
			}
		}
	}
}
