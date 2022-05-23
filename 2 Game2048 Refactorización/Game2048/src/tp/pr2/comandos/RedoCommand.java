package tp.pr2.comandos;

import tp.pr2.control.Controller;
import tp.pr2.logica.Game;

public class RedoCommand extends Command{

	@Override
	public void execute(Game game, Controller controller) {
		game.redo();
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		int longitud = commandWords.length;
		if (longitud == 1) {
			if (commandWords[0].equalsIgnoreCase(this.toString())) {
				return this;
			} 
		}
		return null;
	}

	@Override
	public String helpText() {
		return "  redo: redo the last undone command." + System.getProperty("line.separator");
	}
	public String toString(){
		return "REDO";
	}

}
