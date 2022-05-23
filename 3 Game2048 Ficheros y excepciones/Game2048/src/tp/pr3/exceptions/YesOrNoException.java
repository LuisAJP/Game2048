package tp.pr3.exceptions;

public class YesOrNoException  extends Exception {
	private static final long serialVersionUID = 1L;

	public YesOrNoException() {

		super();
	}

	public YesOrNoException(String argumento) {

		super(argumento);
	}

	public YesOrNoException(Throwable argumento) {

		super(argumento);
	}
}
