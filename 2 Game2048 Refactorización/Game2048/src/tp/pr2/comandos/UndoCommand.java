package tp.pr2.comandos;

import tp.pr2.control.Controller;
import tp.pr2.logica.Game;

public class UndoCommand extends Command{

	@Override
	public void execute(Game game, Controller controller) {
		game.undo();
		
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
		return "  undo: undo the last command.." + System.getProperty("line.separator");
	}
	public String toString(){
		return "UNDO";
	}
}
