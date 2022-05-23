package tp.pr3.comandos;


import java.io.IOException;
import java.util.Scanner;

import tp.pr3.comandos.Command;
import tp.pr3.exceptions.CommandParseException;
import tp.pr3.exceptions.EmptyStackException;
import tp.pr3.exceptions.GameOverException;
import tp.pr3.exceptions.CommandExecuteException;
import tp.pr3.logica.Game;

public abstract class Command {
	
	private String helpText;
	private String commandText;
	protected final String commandName;
	
	public Command(String commandInfo, String helpInfo){
		commandText = commandInfo;
		helpText = helpInfo;
		String[] commandInfoWords = commandText.split("\\s+");
		commandName = commandInfoWords[0];
	}

	public abstract boolean execute(Game game) throws CommandExecuteException, IOException, EmptyStackException,GameOverException;
	
	public abstract Command parse(String[] commandWords, Scanner in) throws CommandParseException;
	
	public String helpText(){return " " + this.commandName + ": " + this.helpText + '\n';}
}