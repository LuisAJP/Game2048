package tp.pr2.control;
import java.util.Scanner;
import tp.pr2.comandos.Command;
import tp.pr2.comandos.CommandParser;
import tp.pr2.logica.Game;
import tp.pr2.logica.GameRules;
import tp.pr2.logica.GameType;
import tp.pr2.logica.Rules2048;
import tp.pr2.logica.RulesFib;
import tp.pr2.logica.RulesInverse;

/**
 * Clase Controlador que permitira la interaccion con el usuario de la aplicacion
 */
public class Controller {
	private Game game;
	private Scanner in;
	private static int defaultDim=4;
	private static int defaultCells=2;
	private static GameRules gameRules;
	
	
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
			System.out.print("  best value: "+ game.getMejorValor()+ "            score: " + game.getScore() );
			System.out.println();
			System.out.println();
			System.out.print("Command>  ");
			String com = in.nextLine().toLowerCase();
			System.out.println();
			com=com.trim();
			String[] cadenaArray = com.split(" +");
			Command comando= CommandParser.parseCommand(cadenaArray, this);
			if (comando != null) {
				comando.execute(game, this);
				if(game.getTerminada()&&game.getGameRules().win(game.getBoard())){	
					System.out.println("Congratulations, You Win!!!");
				}
				else if(game.getTerminada()){
					System.out.println(game.getBoard().toString());
					System.out.print("  best value: "+ game.getMejorValor()+ "            score: " + game.getScore() );
					System.out.println();
					System.out.println();
					System.out.println("Game Over!");
				}
			} 
			else {
				System.out.println("Unknown command");
			}
		}
	}
	public Game getGame(){
		return this.game;
	}

	public void jugar(Game game2, GameType gameType) {
		int dim=0,cel=0;
		long sem=0;
		System.out.println("Please enter the size of the board: ");
		String cadenaDim = in.nextLine().toLowerCase();
		cadenaDim=cadenaDim.trim();
		if (cadenaDim.equals("")) {
			dim=defaultDim;
			System.out.println("  Using the default size of the board: "+ defaultDim);
		}
		else dim=Integer.parseInt(cadenaDim);
		System.out.println("Please enter the number of initial cells: ");
		String cadenaCel = in.nextLine().toLowerCase();	
		cadenaCel=cadenaCel.trim();
		if (cadenaCel.equals("")) {
			cel=defaultCells;
			System.out.println("  Using the default number of initial cells: "+ defaultCells );
		}
		else cel= Integer.parseInt(cadenaCel);
		System.out.println("Please enter the seed for the pseudo-random number generator:");	
		String cadenaSeed = in.nextLine().toLowerCase();	
		cadenaSeed=cadenaSeed.trim();
		if (cadenaSeed.equals("")) {
			sem=game.getSeed();
			System.out.println("  Using the default seed for the pseudo-random number generator:s: "+ game.getSeed() );
		}
		else {
			sem = Long.parseLong(cadenaSeed);
		}
		selectGame(gameType,dim,cel,sem);	 
	}
	
	public void selectGame(GameType gameType, int dim, int cel, long seed) {
		switch (gameType){
		case FIB:
			gameRules=new RulesFib();
			this.game=new Game(gameRules,dim,cel,seed);
			this.game.reset();
			break;
		case ORIG:
			gameRules=new Rules2048();
			this.game=new Game(gameRules,dim,cel,seed);
			this.game.reset();
			break;
		case INV:
			gameRules=new RulesInverse();
			this.game=new Game(gameRules,dim,cel,seed);
			this.game.reset();
			break;	
		}	
	}
}
