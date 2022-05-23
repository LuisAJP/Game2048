package tp.pr2.comandos;

import tp.pr2.control.Controller;
import tp.pr2.logica.Game;
import tp.pr2.logica.GameType;

public class PlayCommand extends Command {
	//private Game juego;
	private static GameType gameType;
	
	@Override
	public void execute(Game game, Controller controller) {
		// TODO Auto-generated method stub
		controller.jugar(game,gameType);
		
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		int longitud = commandWords.length;
		if (longitud == 2) {
			if (commandWords[0].equalsIgnoreCase(this.toString())) {
				gameType= controller.getGame().convertirCadenaGameType(commandWords[1]);
				if (gameType!=null) return this;
			}
		}
		return null;
	}

	@Override
	public String helpText() {
		return "  play <game>: start a new game of one of the game types: original, fib, inverse." + System.getProperty("line.separator");
	}
	public String toString(){
		return "PLAY";
		}
}

