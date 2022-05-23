package tp.pr2.comandos;
import tp.pr2.control.Controller;


public  abstract class NoParamsCommand extends Command{
	public Command parse(String[] commandWords, Controller controller) {
		int longitud = commandWords.length;
		if (longitud == 1) {
			if (commandWords[0].equalsIgnoreCase(this.toString())) {
				return this;
			} 
		}
		return null;
	}
	
	/*private static void usage(){
		System.out.println("Unknown command");
	}
	*/
}
