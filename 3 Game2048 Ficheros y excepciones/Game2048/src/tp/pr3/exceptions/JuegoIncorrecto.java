package tp.pr3.exceptions;

public class JuegoIncorrecto extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JuegoIncorrecto() {

		super();
	}

	public JuegoIncorrecto(String argumento) {

		super(argumento);
	}

	public JuegoIncorrecto(Throwable argumento) {

		super(argumento);
	}
}
