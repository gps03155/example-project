package io;

import java.io.*;

public class FileReaderTest {

	public static void main(String[] args) {
		Reader in = null;
		InputStream is = null;

		int count = 0;
		int data = -1;
		
		try {
			// char - 문자단위로 읽기
			in = new FileReader("123.txt");

			while ((data = in.read()) != -1) { // -1이면 끝
				count++;
				System.out.print((char)data);
			}
			
			System.out.println();
			System.out.println(count);
			
			System.out.println("================");

			// byte
			is = new FileInputStream("123.txt");

			count = 0;
			
			while ((data = is.read()) != -1) { // -1이면 끝
				count++;
				System.out.print((char)data);
			}			
			
			System.out.println();
			System.out.println(count);

		} catch (IOException e) { // IOException (부모) > FileNotFoundException (자식)
			System.out.println("입출력 에러 : " + e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				
				if(is != null) {
					is.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}

}
