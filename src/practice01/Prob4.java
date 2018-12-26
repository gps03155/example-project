package practice01;

import java.util.Scanner;

public class Prob4 {

	public static void main(String[] args) {
		String word;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("문자열을 입력하세요. : ");
		word = sc.nextLine();
		
		for(int i=1;i<word.length()+1;i++) {
			System.out.println(word.substring(0, i));
		}
		
		sc.close();
	}

}
