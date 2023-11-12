package practice01;

import java.util.Scanner;

public class Prob3 {
	
	public static void main(String[] args) {
		int num;
		int sum = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("숫자를 입력하세요 : ");
		num = sc.nextInt();
		
		if(num % 2 != 0) {
			for(int i=1;i<=num;i+=2) {
				sum += i;
			}
		}
		else if(num % 2 == 0) {
			for(int i=2;i<=num;i+=2) {
				sum += i;
			}
		}
		
		System.out.println("결과 값 : " + sum);
		
		sc.close();
	}
}
