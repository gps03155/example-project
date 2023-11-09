package collection;

public class Money {
	private int val;
	
	public Money(int val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return "Money [val=" + val + "]";
	}

	// 기본 주소기반 
	// 내용기반으로 비교하기 위해 equals()를 같이 오버라이딩 해야함
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + val;
		return result;
	}

	// hashCode()가 같으면 equals() 호출
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		if (val != other.val)
			return false;
		return true;
	}
	
	
}
