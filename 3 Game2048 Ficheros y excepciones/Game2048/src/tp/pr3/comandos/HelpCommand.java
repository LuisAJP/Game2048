package tp.pr3.comandos;

import tp.pr3.logica.Game;

public class HelpCommand extends NoParamsCommand {
	
	public HelpCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}

	@Override
	public boolean execute(Game game) {
		game.ayuda();
		return false;
	}
	

}