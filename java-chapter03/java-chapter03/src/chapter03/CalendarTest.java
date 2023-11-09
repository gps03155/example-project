package chapter03;

import java.util.Calendar;

public class CalendarTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 구체적인 객체는 Gregorian Calendar
		// Calendar - Abstract Class (확장하면서 사용)
		Calendar cal = Calendar.getInstance(); // Singleton Pattern - 객체는 1개만 생성가능
		System.out.println(cal);
		printDate(cal);
		
		// 시간 세팅 안하면 현재 시간
		cal.set(Calendar.YEAR, 2020);
		cal.set(Calendar.MONTH, 11); // month(12) - 1 : 12월
		cal.set(Calendar.DATE, 25);
		printDate(cal);
		
		cal.set(2007, 10, 18); // month : 원하는 달 - 1
		printDate(cal);
		
		cal.add(Calendar.DATE, 1000); // 1000일 지난 날
		printDate(cal);
	}
	
	public static void printDate(Calendar cal) {
		String[] days = {"일", "월", "화", "수", "목", "금", "토"};
		
		// 년도 (1900 안더해도 됨)
		int year = cal.get(Calendar.YEAR);
		
		// 월 (0-11, +1 해줘야함)
		int month = cal.get(Calendar.MONTH);
		
		// 일
		int date = cal.get(Calendar.DATE);
		
		// 요일 (1:일요일 ~ 7:토요일)
		int day = cal.get(Calendar.DAY_OF_WEEK);
		
		// 시간
		int hour = cal.get(Calendar.HOUR);
		
		// 분
		int minutes = cal.get(Calendar.MINUTE);
		
		// 초
		int seconds = cal.get(Calendar.SECOND);
		
		System.out.println(year + "-" + (month+1) + "-" + date + " " + days[day-1] + "요일" + " " + hour + ":" + minutes + ":" + seconds);
	}
}
