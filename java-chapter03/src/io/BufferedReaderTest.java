package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderTest {
	public static void main(String[] args) {
		BufferedReader br = null;

		// 기반 스트림
		try {
			// 현재 디렉토리에 있는 파일 읽기 - 리눅스 스타일로 작성하는 것이 좋음
			FileReader fr = new FileReader("./src/io/BufferedReaderTest.java");

			// 보조 스트림
			br = new BufferedReader(fr);
			
			// input
			String line = null;
			int index = 0;
			
			while((line = br.readLine()) != null){
				index++;
				System.out.println(index + " " + line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found : " + e);
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
