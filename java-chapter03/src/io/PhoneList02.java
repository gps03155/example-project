package io;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PhoneList02 {

	public static void main(String[] args) {
		Scanner sc = null;

		try {
			File file = new File("phone.txt");

			// exception보다 if문으로 판단하는게 더 나음
			if (!file.exists()) {
				System.out.println("File Not Found");
				return;
			}

			System.out.println("============ 파일정보 =============");
			System.out.println(file.getAbsolutePath()); // fullpath
			System.out.println(file.length() + "bytes");
			System.out.println(file.lastModified()); // seconds 1900년부터
			System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(file.lastModified())));

			System.out.println("========== 전화번호 ==========");

			sc = new Scanner(file); // Scanner는 Keyboard 뿐만 아니라 File에도 쓸 수 있음
			
			while(sc.hasNextLine()) { // scanner는 내부적으로 \t, space, 개행이 있음	
				String name = sc.next();
				String phone1 = sc.next();
				String phone2 = sc.next();
				String phone3 = sc.next();
				
				System.out.println(name + " " + phone1 + "-" + phone2 + "-" + phone3);
			}
			
		} catch (IOException e) {
			System.out.println("error : " + e);
		} finally {
			if (sc != null) {
				sc.close(); // keyboard는 안해줘도 괜찮음
			}
		}
	}

}
