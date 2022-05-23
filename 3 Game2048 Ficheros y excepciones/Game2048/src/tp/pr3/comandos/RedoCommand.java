package tp.pr3.comandos;

import java.util.EmptyStackException;

import tp.pr3.exceptions.CommandExecuteException;
import tp.pr3.logica.Game;

public class RedoCommand extends NoParamsCommand{

	public RedoCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException,EmptyStackException, tp.pr3.exceptions.EmptyStackException {
//		if(game.getUndo()) {
		String error;
		boolean ok=false;
			try {
				ok= game.redo();
			} 
			catch (tp.pr3.exceptions.EmptyStackException e) {
				error="Nothing to redo";
				throw new CommandExecuteException(error);	
			}
	
			return ok;
			
	}
}
