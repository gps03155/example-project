package practice02;

import java.util.Random;
import java.util.Scanner;

public class Prob05 {


	public static void main(String[] args) {
		System.out.println("수를 결정하였습니다. 맞추어 보세요.");
		
		Random rand = new Random();
		int rand_num = rand.nextInt(100) + 1;
		//System.out.println(rand_num);
		
		Scanner sc = new Scanner(System.in);
		
		int min = 1;
		int max = 100;
		int count = 1;
		String answer;
		
		while(true) {
			int input;
			
			System.out.println(min + " - " + max);
			System.out.print(count + " >> ");
			input = sc.nextInt();
			
			if(rand_num > input) {
				min = input;
				System.out.println("더 높게");
			}
			if(rand_num < input) {
				max = input;
				System.out.println("더 낮게");
			}
			if(rand_num == input) {
				System.out.println("맞았습니다.");
				System.out.println("다시하시겠습니까(y/n) >> ");
				
				answer = sc.next();
				
				if(answer.equals("n")) {
					System.out.println("프로그램 종료");
					break;
				}
				else if(answer.equals("y")){
					min = 1;
					max = 100;
					count = 1;
				}
				else {
					System.out.println("y/n으로 입력해주세요.");
					break;
				}
			}
			
			count++;
		}
	}

}
