package tp.pr3.comandos;

import tp.pr3.logica.Game;

public class ResetCommand extends NoParamsCommand{
	
	public ResetCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		game.reset();
		return  true;
	}

}
