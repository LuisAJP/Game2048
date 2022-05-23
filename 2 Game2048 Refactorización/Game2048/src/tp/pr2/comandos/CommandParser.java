package tp.pr2.comandos;

import tp.pr2.control.Controller;

public class CommandParser {
	private static Command[] availableCommands = { new HelpCommand(),new ResetCommand(), 
			new ExitCommand(), new MoveCommand(),new UndoCommand(), new RedoCommand(), new PlayCommand()};
	
	
	public static Command parseCommand(String[ ] commandWords, Controller controller){
		
		for (Command command : availableCommands) {

			Command comandoAux = command.parse(commandWords, controller);

			if (comandoAux != null) {
				return comandoAux;
			}
		}
		return null;
	}
	
	public static String commandHelp(){
		for (Command command : availableCommands) {

			String comandoHelp = command.helpText();
			
			System.out.println(comandoHelp);
		}
		return null;
	}
}
