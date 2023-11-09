package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStream is = null;
		OutputStream os = null;
		
		try {
			is = new FileInputStream("image.png"); // 경로가 없을 경우 프로젝트 경로와 같음
			os = new FileOutputStream("image_copy.png");
			
			int data = -1;
			
			while((data = is.read()) != -1) { // -1이면 끝
				os.write(data);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("파일이 없습니다." + e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e);
		} finally {
			// 자원 해제
			try {
				if (is != null) {
					is.close();
				}
				
				if(os != null) {
					os.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
