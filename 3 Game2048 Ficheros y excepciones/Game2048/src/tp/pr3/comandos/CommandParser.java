package tp.pr3.comandos;

import java.util.Scanner;

import tp.pr3.exceptions.CommandParseException;

public class CommandParser {
	
	private final static Command[ ] availableCommands = { new HelpCommand("help", "  print this help message."), new ResetCommand("reset", "  start a new game."),
			new ExitCommand("exit", "  terminate the program."), new MoveCommand("move", "  <directionn> execute a move in one of the directions: up, down, left, right."),
			new UndoCommand("undo", "  undo the last move."), new RedoCommand("redo", "  redo the last undone command."), 
			new PlayCommand("play", "  <game> start a new game of one of the game types: original, fib, inverse."), new LoadCommand("load", "  load <filename> carga el estado completo de una partida almacenada previamente en un fichero."), 
			new SaveCommand ("save", "  <filename> guardar en un fichero el estado completo de una partida.")} ;
	
	
	public static Command parseCommand(String[ ] commandWords, Scanner scanner)throws CommandParseException{
		
		for (Command command : availableCommands) {

			Command comandoAux = command.parse(commandWords, scanner);

			if (comandoAux != null) {
				return comandoAux;
			}
		}
		return null;
	}
	
	public static String commandHelp(){
		for (Command command : availableCommands) {

			String comandoHelp = command.helpText();
			
			System.out.println(comandoHelp);
		}
		return null;
	}
}
