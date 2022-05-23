package tp.pr3.exceptions;

public class FileContentsException  extends Exception {
	private static final long serialVersionUID = 1L;

	public FileContentsException() {

		super();
	}

	public FileContentsException(String argumento) {

		super(argumento);
	}

	public FileContentsException(Throwable argumento) {

		super(argumento);
	}

}
