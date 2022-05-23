package tp.pr2.comandos;

import tp.pr2.control.Controller;
import tp.pr2.logica.Direction;
import tp.pr2.logica.Game;

public class MoveCommand extends Command{
	private static Direction dir;
	
	@Override
	public void execute(Game game, Controller controller) {
		// TODO Auto-generated method stub
		game.move(dir);
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		int longitud = commandWords.length;
		if (longitud == 2) {
			if (commandWords[0].equalsIgnoreCase(this.toString())) {
				dir= controller.getGame().convertirCadenaDirection(commandWords[1]);
				if (dir!=null) return this;
			} 
		}
		return null;
	}
	
	
	
	@Override
	public String helpText() {
		// TODO Auto-generated method stub
		return "  move <direction>: execute a move in one of the four directions,up, down, left, right" + System.getProperty("line.separator");
	}

	public String toString(){
		return "MOVE";
	}
	
}
