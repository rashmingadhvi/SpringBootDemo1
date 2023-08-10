package com.rmk.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo1Application {

	public static void main(String[] args) {

		SpringApplication.run(Demo1Application.class, args);
	}


	static String rotationalCipher(String input, int rotationFactor) {
		// Write your code here

		StringBuilder stbr = new StringBuilder();
		if (null == input || input.length() == 0) {
			return "Invalid input";
		}
		System.out.println();
		char[] inputChars = input.toCharArray();
		for(int i=0; i< inputChars.length ; ++i) {
			System.out.println((int)inputChars[i] + " - " + ((int)inputChars[i] + rotationFactor));

			stbr.append((char)(((int)inputChars[i] + rotationFactor)));

		}

		return stbr.toString();

	}

}
