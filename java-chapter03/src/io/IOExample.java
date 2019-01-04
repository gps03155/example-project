package io;

import java.io.*;
import java.util.Arrays;

public class IOExample {

	public static void main(String[] args) throws IOException { // throws 쓰면 Exception 회피 (좋은 방법 아님 : try-catch 쓰기)
		// TODO Auto-generated method stub
		byte[] src = {0, 1, 2, 3, 4};
		byte[] dest = null;
		
		InputStream is = new ByteArrayInputStream(src); // 주 Stream
		OutputStream os = new ByteArrayOutputStream(); // 내부에 dest가 존재 - 버퍼 존재
		
		int data = -1;
		
		while((data = is.read()) != -1) { // -1이면 끝
			os.write(data);
		}
		
		dest = ((ByteArrayOutputStream)os).toByteArray();
		
		System.out.println(Arrays.toString(src));
		System.out.println(Arrays.toString(dest));
	}
}
