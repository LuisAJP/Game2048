package tp.pr3.comandos;

import java.util.Random;
import java.util.Scanner;

import tp.pr3.exceptions.CommandParseException;
import tp.pr3.logica.Game;
import tp.pr3.logica.GameType;

public class PlayCommand extends Command {
	private static Scanner scanner;
	private static GameType gameType;
	private static int size;
	private static int initCells;
	private static int seed;
	
	public PlayCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		game.jugar(game,gameType,scanner,size,initCells,seed);
		return true;
	}

	@Override
	public Command parse(String[] commandWords, Scanner in) throws CommandParseException {
		
		String error;
		int longitud = commandWords.length;
		if (longitud == 2) {
			if (commandWords[0].equalsIgnoreCase(this.commandName)) {
				gameType=GameType.parse(commandWords[1]);
				if (gameType!=null) {
					scanner=in;
					System.out.println(" *** You have chosen to play the game: "+ gameType.toString() + " ***");
					size=dimension();
					initCells=cellsIniciales();
					seed=semilla();
					return this;
				}
				else {
					error=commandWords[1]+" no es un tipo de juego";
					throw new CommandParseException(error); 
				}
			}
		}
		else if(commandWords.length == 1) {
			if(commandWords[0].equalsIgnoreCase(this.commandName)) {
				error="falta el tipo de juego";
				throw new CommandParseException(error); 
			}
		}
		return null;
	}
	
	
	
	public int dimension() {
		boolean datoCorrecto=false;
		int dim=0;
		while (!datoCorrecto) {
			System.out.println("Please enter the size of the board: ");
			String[] words=scanner.nextLine().trim().split(" +");
			if (words.length==1) {
				if (words[0].equals("")){//El usuario presiona Intro
					dim=4;
					datoCorrecto=true;
					System.out.println("  Using the default size of the board: "+ dim);
				}
				else {
					try {
						dim=Integer.parseInt(words[0]);
						if (dim>0) 
							datoCorrecto=true;
						else 
							System.out.println("The size of the board must be a positive number");
					}//try
					catch (NumberFormatException e) {
						System.out.println("The size of the board must be a number");
					}
				}
			}
			else 
				System.out.println("The size of the board must be a longitud1");
		}//while
		return dim;
	}
	
	
	public int cellsIniciales() {
		int rango=size*size;
		boolean datoCorrecto=false;
		int celdas=0;
		while (!datoCorrecto) {
			System.out.println("Please enter the number of initial cells: ");
			String[] words=scanner.nextLine().trim().split(" +");
			if (words.length==1) {
				if (words[0].equals("")){//El usuario presiona Intro
					celdas=2;
					datoCorrecto=true;
					System.out.println("  Using the default number of initial cells: "+ celdas);
				}
				else {
					try {
						celdas=Integer.parseInt(words[0]);
						if (celdas>0 && celdas<=rango) 
							datoCorrecto=true;
						else if(celdas<0) {
							System.out.println("The number of initial cells must be a positive number");
						}
						else if(celdas>rango) {
							System.out.println("The number of initial cells exceed the limit board");
						}
							
					}//try
					catch (NumberFormatException e) {
						System.out.println("The number of initial cells must be a number");
					}
				}
			}
			else 
				System.out.println("The number of initial cells must be a longitud 1");	
		}//while
		return celdas;	
	}
	
	
	public int semilla() {
		boolean datoCorrecto=false;
		int seed=0;
		while (!datoCorrecto) {
			System.out.println("Please enter the seed for the pseudo-random number generator: ");
			String[] words=scanner.nextLine().trim().split(" +");
			if (words.length==1) {
				if (words[0].equals("")){//El usuario presiona Intro
					seed=new Random().nextInt(1000);
					datoCorrecto=true;
					System.out.println("  Using the default seed for the pseudo-random number generator: "+ seed);
				}
				else {
					try {
						seed=Integer.parseInt(words[0]);
						if (seed>0) 
							datoCorrecto=true;
						else 
							System.out.println("Please provide a single positive integer or press return");
					}//try
					catch (NumberFormatException e) {
						System.out.println("The seed for the pseudo-random number generator must be a number");
					}
				}
			}
			else 
				System.out.println("The seed for the pseudo-random number generator be a longitud1");
				
		}//while
		return seed;
	}

}

