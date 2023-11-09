package collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Integer> m = new HashMap<String, Integer>(); // key : hashCode(), equals() 오버라이딩 되어 있어야 함 
							 									 // key(String), value(Integer) : Generic 안에 변수 2개 들어가야함
		m.put("one", 1); // auto boxing
		m.put("two", 2);
		m.put("three", 3);
		
		int i = m.get("two"); // auto unboxing : Integer -> int
		System.out.println(i);
		
		m.put("three", 33333);
		System.out.println(m.get("three")); // 동일한 key에 새로운 값을 넣으면 값이 덮어쓰임 : 3 -> 33333
		
		// iterator - key 순회
		Set<String> keys = m.keySet();
		
		for(String key : keys) {
			System.out.println(m.get(key));
		}
	}

}
