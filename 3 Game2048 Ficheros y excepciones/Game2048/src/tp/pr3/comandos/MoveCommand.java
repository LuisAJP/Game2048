package tp.pr3.comandos;

import java.util.Scanner;

import tp.pr3.exceptions.CommandParseException;
import tp.pr3.exceptions.GameOverException;
import tp.pr3.logica.Direction;
import tp.pr3.logica.Game;

public class MoveCommand extends Command{
	
	private static Direction dir;

	public MoveCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);

	}

	@Override
	public boolean execute(Game game) throws GameOverException {
		return game.move(dir);
	}

	@Override
	public Command parse(String[] commandWords, Scanner in) throws CommandParseException {
		String error;
		if(commandWords.length == 2) {
			if(commandWords[0].equalsIgnoreCase(this.commandName)) {
				dir= Direction.convertirCadenaDirection(commandWords[1]);
				if (dir!=null) { 
					return this;
				}
				else {
					error=commandWords[1]+" no es una direccion";
					throw new CommandParseException(error); 
				}
				
			}
			
		}
		else if(commandWords.length == 1) {
			if(commandWords[0].equalsIgnoreCase(this.commandName)) {
				error="falta la direccion";
				throw new CommandParseException(error); 
			}
		}
		else if(commandWords[0].equalsIgnoreCase(this.commandName)) {
			error="move no necesita tres o más items";
			throw new CommandParseException(error); 
			
		}
		return null;
	}

}
