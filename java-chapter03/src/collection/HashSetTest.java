package collection;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> s = new HashSet<String>();
		
		// 같은 객체면 set에 안들어감
		String s1 = new String("고길동"); // 다른 객체로 생성
		String s2 = new String("고길동");
		
		s.add("둘리");
		s.add("마이콜");
		s.add(s1);
		
		// hashCode() 먼저 검사하고 equals() 비교함
		s.contains(s2); // 같은 객체가 아니라 값으로 비교
		System.out.println(s.contains(s2)); // true
		
		System.out.println(s.size());
		
		// remove - 같은 객체가 아닌 값을 지움 (다른 객체여도 내용을 지움)
		String s3 = new String("고길동");
		s.remove(s3); // s3 객체를 지웠으나 Set에 있는 "고길동"의 값이 지워짐 - 값으로 지움
		
		//iterator
		for(String str : s) {
			System.out.println(str);
		}
	}

}
