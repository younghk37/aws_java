package ch06_반복;

import java.util.Scanner;

public class For05 {

	public static void main(String[] args) {
		int i = 0;
		
		while(i < 10) {
			if(i % 2 == 0) {
				i++;
				continue;
			}
			System.out.println(i);
			i++;
		}
		
	}
}