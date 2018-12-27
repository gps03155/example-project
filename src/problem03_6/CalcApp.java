package problem03_6;

import java.util.Scanner;

public class CalcApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		System.out.println(">> ");
		String input = sc.nextLine();

		int a = Integer.parseInt(input.split(" ")[0]);
		String op = input.split(" ")[1];
		int b = Integer.parseInt(input.split(" ")[2]);
		
		int result = 0;
		
		if(op.equals("+")) {
			Calculate add = new Add();
			add.setValue(a, b);
			result = add.calculate();
		}
		else if(op.equals("-")) {
			Calculate sub = new Sub();
			sub.setValue(a, b);
			result = sub.calculate();
		}
		else if(op.equals("*")) {
			Calculate mul = new Mul();
			mul.setValue(a, b);
			result = mul.calculate();
		}
		else if(op.equals("/")) {
			Calculate div = new Div();
			div.setValue(a, b);
			result = div.calculate();
		}
		
		System.out.println(">> " + result);
		System.out.println(">> quit");
	}

}
