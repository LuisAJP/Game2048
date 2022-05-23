package tp.pr3.comandos;

import tp.pr3.exceptions.CommandExecuteException;
import tp.pr3.exceptions.EmptyStackException;
import tp.pr3.logica.Game;

public class UndoCommand extends NoParamsCommand{

	public UndoCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException, EmptyStackException {
		String error;
		boolean ok=false;
		try {
			ok= game.undo();
		} 
		catch (EmptyStackException e) {
			// TODO Auto-generated catch block
			error="Undo is not available";
			throw new CommandExecuteException(error);	
			
		}
		return ok;
	}
}
