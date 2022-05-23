package tp.pr3.comandos;

import tp.pr3.logica.Game;

public class ExitCommand extends NoParamsCommand{

	public ExitCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean execute (Game game) {
		game.setTerminada(true);
		return false;
	}

}
