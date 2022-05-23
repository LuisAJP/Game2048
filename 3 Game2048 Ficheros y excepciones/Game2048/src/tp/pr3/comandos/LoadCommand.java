package tp.pr3.comandos;


import java.util.Scanner;

import tp.pr3.exceptions.CommandParseException;
import tp.pr3.exceptions.FileContentsException;
import tp.pr3.exceptions.CommandExecuteException;
import tp.pr3.logica.Game;

public class LoadCommand extends Command {
	
	private static String fileName;
	private static Scanner scanner;

	public LoadCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		
		boolean ok=false;
		try {
			ok=game.load(fileName,scanner);
		} catch (FileContentsException e) {
			throw new CommandExecuteException("La primera línea del archivo no es “This file stores a saved 2048 game”");
		}
		catch(NumberFormatException e) {
			System.out.println("The command-line arguments must be numbers");
		}
		return ok;
	}

	
	
	@Override
	public Command parse(String[] commandWords, Scanner in) throws CommandParseException {
		String error;
		int longitud = commandWords.length;
		if (longitud == 2) {
			if (commandWords[0].equalsIgnoreCase(this.commandName)) {
				fileName=commandWords[1];
				scanner=in;
				return this;
			}
		}
		else if(commandWords.length == 1) {
			if(commandWords[0].equalsIgnoreCase(this.commandName)) {
				error="falta el nombre del archivo para cargar la partida";
				throw new CommandParseException(error); 
			}
		}
		else if(commandWords[0].equalsIgnoreCase(this.commandName)) {
			error="el nombre del archivo no puede contener espacios";
			throw new CommandParseException(error); 
			
		}
		return null;
	}
	
}
