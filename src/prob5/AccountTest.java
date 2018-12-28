package prob5;

public class AccountTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Account account = new Account();
		
		account.setAccountNo("078-3762-293");
		account.balance();
		
		account.save(100);
		account.balance();
		
		account.deposit(30);
		account.balance();
	}

}
