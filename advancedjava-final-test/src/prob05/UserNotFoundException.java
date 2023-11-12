package prob05;

public class UserNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
	public UserNotFoundException() {
		super("존재하지 않는 사용자입니다.");
	}
}
