package tp.pr3.exceptions;

public class CommandParseException  extends Exception {
	private static final long serialVersionUID = 1L;

	public CommandParseException() {

		super();
	}

	public CommandParseException(String argumento) {

		super(argumento);
	}

	public CommandParseException(Throwable argumento) {

		super(argumento);
	}
}
