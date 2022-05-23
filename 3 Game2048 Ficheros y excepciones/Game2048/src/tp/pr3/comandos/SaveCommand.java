package tp.pr3.comandos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import tp.pr3.exceptions.CommandParseException;
import tp.pr3.logica.Game;

public class SaveCommand extends Command {
	
	private static String fileName;
	private static Scanner scanner;
	public static final String filenameInUseMsg= "The file already exists ; do you want to overwrite it ? (Y/N)";
	
	public SaveCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) throws IOException {
		// TODO Auto-generated method stub
		game.store(fileName,scanner);
		return false;
	}

	
	@Override
	public Command parse(String[] commandWords, Scanner in) throws CommandParseException {
		String error;
		int longitud = commandWords.length;
		if (longitud == 2) {
			if (commandWords[0].equalsIgnoreCase(this.commandName)) {
				fileName=commandWords[1];
				scanner=in;
				fileName=confirmFileNameStringForWrite(fileName, in);
				return this;
			}
		}
		
		else if(commandWords.length == 1) {
			if(commandWords[0].equalsIgnoreCase(this.commandName)) {
				error="falta el nombre del archivo para guardar la partida";
				throw new CommandParseException(error); 
			}
		}
		else if(commandWords[0].equalsIgnoreCase(this.commandName)) {
			error="el nombre del archivo no puede contener espacios";
			throw new CommandParseException(error); 
			
		}
		return null;
	}

	
	private String confirmFileNameStringForWrite(String filenameString, Scanner in) {
		
		String loadName = filenameString;
		boolean filename_confirmed = false;
		while (!filename_confirmed) {
			if (validFileName(loadName)) {
				File file = new File(loadName);
				if (!file.exists())
					filename_confirmed = true;
				else {
					loadName = getLoadName(filenameString, in);
					filename_confirmed = true;
				}
			} 
			else {
			// ADD SOME CODE HERE
			}
		}
	return loadName;
	}
	
	public String getLoadName(String filenameString, Scanner in) {
		String newFilename = null;
		boolean yesOrNo = false;
		while (!yesOrNo) {
			System.out.print(filenameInUseMsg + ": ");
			String[] responseYorN = in.nextLine().toLowerCase().trim().split("\\s+");
			if (responseYorN.length == 1) {
				switch (responseYorN[0]) {
					case "y":
						yesOrNo = true;
						newFilename=filenameString;
					break;
					// ADD SOME CODE HERE
					case "n":
						yesOrNo = true;
						System.out.println("Please enter another filename: " );
						String cadena = scanner.nextLine().toLowerCase();
						while(cadena.equals(fileName)) {
							System.out.println("Please enter another filename: " );
							cadena = scanner.nextLine().toLowerCase();
						}
						fileName=cadena;
						newFilename=fileName;
					break;
					// ADD SOME CODE HERE
					default:
					// ADD SOME CODE HERE
				}
			} 
			else {
			// ADD SOME CODE HERE
			}
		}
		return newFilename;
	}
	
	public static boolean validFileName(String filename) {
		File file = new File(filename);
		if (file.exists() ) {
			return canWriteLocal(file);
		} 
		else {
			try {
				file.createNewFile();
				file.delete () ;
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
	
	
	public static boolean canWriteLocal(File file) {
		// works OK on Linux but not on Windows (apparently!)
		if (! file . canWrite()) {
			return false;
		}
		// works on Windows
		try {
			new FileOutputStream(file, true).close();
		} 
		catch (IOException e) {
			return false;
		}
		return true;
	}
}
