package practice01;

public class Prob5 {

	public static void main(String[] args) {
		for(int i=0;i<100;i++) {
			String num = String.valueOf(i);
			boolean isCheck = false;
			
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
	}
}
