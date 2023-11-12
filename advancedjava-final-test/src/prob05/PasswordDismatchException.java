package prob05;

public class PasswordDismatchException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordDismatchException(String message) {
		super(message);
	}
	
	public PasswordDismatchException() {
		super("비밀번호가 틀렸습니다.");
	}
}
