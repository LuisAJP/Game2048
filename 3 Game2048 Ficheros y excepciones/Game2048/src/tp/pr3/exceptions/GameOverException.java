package tp.pr3.exceptions;

public class GameOverException  extends Exception{
	private static final long serialVersionUID = 1L;

	public GameOverException() {

		super();
	}

	public GameOverException(String argumento) {

		super(argumento);
	}

	public GameOverException(Throwable argumento) {

		super(argumento);
	}
}
