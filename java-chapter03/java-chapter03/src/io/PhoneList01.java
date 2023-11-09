package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class PhoneList01 {

	public static void main(String[] args) {
		BufferedReader br = null;

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

			// 기반 스트림
			FileInputStream fis = new FileInputStream(file); // String, File 객체 가능

			// 보조스트림1 (bytes -> char)
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");

			// 보조스트림2 (Line 입력)
			br = new BufferedReader(isr);

			// 처리
			String line = null;
			
			while((line = br.readLine()) != null) {
				System.out.println("String : " + line);
				
				// tab, space, 개행 분류
				// 문자 하나씩 하나씩
				StringTokenizer st = new StringTokenizer(line, "\t "); // spilt()과 차이 - 분리할 문자를 문자열로 줌(\t, space 두개)
				int index = 0;
				
				while(st.hasMoreElements()) {
					System.out.print(st.nextToken()); // 한 라인씩 읽어서 처리함
					
					if(index == 0) { // name
						System.out.print(" : ");
					}
					else if(index == 1) { // 번호 1
						System.out.print("-");
					}
					else if(index == 2) { // 번호 1
						System.out.print("-");
					}
						
					index++; 
				}
				
				System.out.println();
			}
			
		} catch (IOException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}

}
