package tp.pr2.comandos;

import tp.pr2.control.Controller;
import tp.pr2.logica.Game;

public class ResetCommand extends NoParamsCommand{
	
	@Override
	public void execute(Game game, Controller controller) {
		// TODO Auto-generated method stub
		game.reset();
	}
	@Override
	public String helpText() {
		return "  reset: start a new game" + System.getProperty("line.separator");
	}	
	

	public String toString(){
		return "RESET";
	}
}
