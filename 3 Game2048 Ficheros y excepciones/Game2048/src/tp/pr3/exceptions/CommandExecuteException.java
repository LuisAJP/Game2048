package tp.pr3.exceptions;

public class CommandExecuteException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CommandExecuteException() {

		super();
	}

	public CommandExecuteException(String argumento) {

		super(argumento);
	}

	public CommandExecuteException(Throwable argumento) {

		super(argumento);
	}
}
