package tp.pr2.comandos;

import tp.pr2.control.Controller;
import tp.pr2.logica.Game;

public class HelpCommand extends NoParamsCommand{
	
	
	public void execute(Game game, Controller controller) {
		// TODO Auto-generated method stub
		CommandParser.commandHelp();
	}
	public String toString(){
		return "HELP";
	}
	@Override
	public String helpText() {
		// TODO Auto-generated method stub
		return "The available commands are:"+ System.getProperty("line.separator")+"  help : print this help message" + System.getProperty("line.separator");
	}
}
