package practice01;

import java.util.Scanner;

public class Prob5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("숫자를 입력하세요 : ");
		int input = sc.nextInt();
		
		for(int i=0;i<=input;i++) {
			String num = String.valueOf(i);
			boolean isCheck = false;
			int count = 0;
			
			for(int j=0;j<num.length();j++) {
				if(num.charAt(j) == '3' || num.charAt(j) == '6' || num.charAt(j) == '9') {
					System.out.print("짝");
					isCheck = true;
				}
			}
			
			if(isCheck) {
				System.out.println(num);
			}
			else {
				continue;
			}
			
			if(i % 10 == 0) {
				System.out.println();
			}
			else {
				System.out.print(' ');
			}
		}
		
		sc.close();
	}
}
