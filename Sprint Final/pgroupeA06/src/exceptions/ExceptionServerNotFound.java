package exceptions;

public class ExceptionServerNotFound extends Exception{
	public ExceptionServerNotFound() {
		super("No server found");
	}
}
