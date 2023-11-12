package prob03;

public class Money {

	private int amount;

	public Money(int amount) {
		this.amount = amount;
	}

	public Money add(Money money) {
		Money result = new Money(money.amount);

		result.amount = amount + money.amount;

		return result;
	}

	public Money minus(Money money) {
		Money result = new Money(money.amount);

		result.amount = amount - money.amount;

		return result;
	}

	public Money multiply(Money money) {
		Money result = new Money(money.amount);

		result.amount = amount * money.amount;

		return result;
	}

	public Money devide(Money money) {
		Money result = null;
		
		try {
			result = new Money(money.amount);

			result.amount = amount / money.amount;
		} catch (ArithmeticException e) {
			System.out.println("0으로 나눌 수 없습니다.");
		}
		
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		if (amount != other.amount)
			return false;
		return true;
	}
}
