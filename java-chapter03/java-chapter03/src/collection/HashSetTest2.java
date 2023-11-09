package collection;

import java.util.HashSet;
import java.util.Set;

// hashCode(), equals() 오버라이딩
// 오버라이딩해서 Set에 들어가는지 안들어가는지 확인
// hashCode(), equals() 같이 오버라이딩 해야하는 이유 확인
public class HashSetTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<Money> s = new HashSet<Money>();
		
		s.add(new Money(1));
		s.add(new Money(2));
		s.add(new Money(3));
		s.add(new Money(1)); // hashCode(), equals() 오버라이딩 안하면 중복된 값이 들어감
							 // 주소기반으로 hashCode()를 만들어서 같은 값 다른 객체라 중복된 내용이 들어감
		
		for(Money m : s) {
			System.out.println(m);
		}
	}

}
