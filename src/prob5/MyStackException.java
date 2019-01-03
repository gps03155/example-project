package prob5;

public class MyStackException extends Exception {
	private static final long serialVersionUID = -6672702507525718955L;

	public MyStackException(String message) {
		super(message);
	}

	public MyStackException() {
		super("stack is empty");
	}
}
