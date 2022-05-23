package tp.pr2.comandos;

import tp.pr2.control.Controller;
import tp.pr2.logica.Game;

public class ExitCommand extends NoParamsCommand{

	public String toString(){
		return "EXIT";
		}
	@Override
	public void execute(Game game, Controller controller) {
		// TODO Auto-generated method stub
		game.setTerminada(true);
		
	}
	@Override
	public String helpText() {
		return "  exit: terminate the program" + System.getProperty("line.separator");
	}	
}
