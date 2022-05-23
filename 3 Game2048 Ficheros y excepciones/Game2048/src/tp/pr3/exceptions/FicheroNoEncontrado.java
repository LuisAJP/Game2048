package tp.pr3.exceptions;

public class FicheroNoEncontrado extends Exception  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FicheroNoEncontrado() {

		super();
	}

	public FicheroNoEncontrado(String argumento) {

		super(argumento);
	}

	public FicheroNoEncontrado(Throwable argumento) {

		super(argumento);
	}
}
