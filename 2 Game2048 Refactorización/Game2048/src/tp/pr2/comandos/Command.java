package tp.pr2.comandos;

import tp.pr2.control.Controller;
import tp.pr2.logica.Game;

public abstract class Command {

	public abstract void execute(Game game, Controller controller);
	
	public abstract Command parse(String[] commandWords, Controller controller);
	
	public abstract String helpText();
}