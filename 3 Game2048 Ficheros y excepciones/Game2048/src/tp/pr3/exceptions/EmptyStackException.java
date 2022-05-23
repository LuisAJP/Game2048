package tp.pr3.exceptions;

public class EmptyStackException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmptyStackException() {

		super();
	}

	public EmptyStackException(String argumento) {

		super(argumento);
	}

	public EmptyStackException(Throwable argumento) {

		super(argumento);
	}
}
