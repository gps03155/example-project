package io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamTest {

	public static void main(String[] args) {
		// 기반 스트림 (소스에 연결)
		BufferedOutputStream bos = null;
		
		try {
			FileOutputStream fos = new FileOutputStream("test.txt");
			
			// 보조 스트림 (기반 스트림에 연결)
			// 객체를 가지고 들어가는 부분이 상속이랑 다름 - 부모가 어떻게 구현되어 있는지 알 필요가 없음
			// return되는 타입만 신경쓰면됨 - 캡슐화에 적합함
			bos = new BufferedOutputStream(fos);
			
			// output
			// for(int i=97;i<=122;i++) - ASCII Code
			for(int i='a'; i<='z'; i++) {
				bos.write(i);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found : " + e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				// bos가 닫히면 알아서 닫힘
				// 맨 나중에 사용한거만 닫으면 됨
//				if (fos != null) {
//					fos.close();
//				}
//				
				if(bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}

}
