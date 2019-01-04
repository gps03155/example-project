package chapter03;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date now = new Date();	// window의 현재 시간 
		// Date now = new Date(2017, 11, 19); // 특정 시간 적어도 되지만 duplicate 때문에 쓰면 안됨 - 지원 불가 (에러는 나지 않음)
											  // Calendar로 대체
											  // 년도는 1900 빼야함
		
		System.out.println(now);
		
		printDate1(now);
		printDate2(now);
	}

	public static void printDate1(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		String s = sdf.format(d);
		System.out.println(s);
	}
	
	// duplicate된거 사용 자제 - Calender 클래스 쓰기
	public static void printDate2(Date d) {
		// 년도 (+ 1900)
		int year = d.getYear();
		
		// 월 (0-11, +1 해줘야함)
		int month = d.getMonth();
		
		// 일
		int day = d.getDay();
		
		// 시
		int hour = d.getHours();
		
		// 분
		int minutes = d.getMinutes();
		
		// 초
		int seconds = d.getSeconds();
		
		System.out.println((year+1900) + "-" + (month+1) + "-" + day + " " + hour + ":" + minutes + ":" + seconds);
	}
}
