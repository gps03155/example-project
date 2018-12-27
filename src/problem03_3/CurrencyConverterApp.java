package problem03_3;

public class CurrencyConverterApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CurrencyConverter converter = new CurrencyConverter();
		
		converter.setRate(1121);
		
		System.out.println("백만원은 " + converter.toDollar(1000000) + "달러입니다.");
		System.out.println("백달러는 " + converter.toKRW(100) + "원입니다.");
	}

}
