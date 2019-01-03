package chapter03;

public class StringTest03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "aBcABCabcAbc";
		
		System.out.println(s.length());
		System.out.println(s.charAt(2));
		System.out.println(s.indexOf("abc"));
		System.out.println(s.indexOf("abc", 7));
		System.out.println(s.substring(3));
		System.out.println(s.substring(3, 5)); // start <= string < end
		
		String s2 = "    ab cd    ";
		String s3 = "efg,hij,lmno,pq";
		String s4 = s2.concat(s3);
		
		System.out.println(s4);
		System.out.println("----" + s4.trim() + "----"); // 앞 뒤 공백 제거  - id, pwd 체크시 주로 사용
		
		// 정규 표현식
		// .* : 모든 문자
		// [0-9a-zA-Z]{8, } : 8자 이상의 숫자, 알파벳
		// 특정 패턴으로 적길 원할 때 정규 표현식 사용
		System.out.println("----" + s4.replaceAll(" ", "") + "----"); // 가운데 공백까지 제거
		
		String[] tokens = s3.split(",");
		
		// 해당 되는게 없어도 절대로 null이 되지않음
		// 하나짜리 배열이 생성됨 - 값이 하나
		// null 체크 안해도 됨
		for(String str : tokens) { // 원래는 iterator만 가능 - 내부적으로 배열을 list로 바꾸어 변환 
			System.out.println(str);
		}
		
		// StringBuffer : 내부가 변할 수 있는 객체 (자기 자신을 return - 함수 체인 method chain) - 메소드 지원이 많음
		StringBuffer sb = new StringBuffer(""); // 기본으로 size가 잡힘
		
		sb.append("Hello");
		sb.append("World");
		sb.append(2018);
		
//		String str = "Hello " + "World" + 2018; // 실제적으로 사용하면 안좋은 코드 - String + String 연산 자체가 옳지 않음
		                                        // 객체끼리 더하는 연산은 옳지 않음
												// +는 매번 new String으로 바뀌는 효과
		String str = new StringBuffer("Hello ").append("World ").append(2018).toString(); // String +연산을 했을 경우의 실제 코드
		// StringBuffer를 사용하는게 성능면에서 더 좋을 수 있음
		// 안좋은 경우 : +를 많이 쓰는 경우 - + 연산자는 적게 쓸수록 좋음
		// 좋은 경우 : +를 적게 쓰는 경우
		
		// 주의 : + 연산자로 문자열을 더할 때 
		String str2 = "";
		StringBuffer sb2 = new StringBuffer("");
		
		for(int i=0;i<100000;i++) {
			// str2 = str2 + i;
			// String str2 = new StringBuffer("Hello ").append("World ").append(2018).toString();
			// 100만번의 new가 일어나게 됨
			// +를 쓰면 안됨
			
			// method 호출 100만번
			sb2.append(i); // + 연산보다 빨리 끝남 - 성능이 좋음
		}
		
		// String + 연산 : 내부적으로 StringBuffer로 변환 - new 호출
		// 많은 + 연산을 쓸 경우 적합하지 않음 - StringBuffer를 사용해서 구현할 것
	}

}
