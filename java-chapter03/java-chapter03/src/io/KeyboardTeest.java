package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyboardTeest {

	public static void main(String[] args) {
		BufferedReader br = null;

		try {
			// 기반 스트림 (JVM이 만들어준 표준 입력 : stdin, System.in etc...)

			// 보조 스트림1
			InputStreamReader isr = new InputStreamReader(System.in, "UTF-8");

			// 보조 스트림2 (char | char | char | ..... |\n -> "charcharchar"
			br = new BufferedReader(isr);
			
			// read
			String line = null;
			
			while((line = br.readLine()) != null) {
				if("exit".equals(line)) {
					break;
				}
				
				System.out.println(">>" + line);
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
