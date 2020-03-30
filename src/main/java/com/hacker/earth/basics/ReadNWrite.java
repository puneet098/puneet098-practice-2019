package com.hacker.earth.basics;

import java.util.Scanner;

public class ReadNWrite {
	
	public static void displayLine(int count,String line) {
		if(count==1) {
			int lineInt = Integer.valueOf(line);
			if((lineInt < 0 || lineInt >10)) {	
				System.out.println("More than ten not allowed. Exiting");
				throw new RuntimeException();
			}
			System.out.println(lineInt*2);
		}
		if(line.length() < 16 && count > 1) {
			System.out.println(line);
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int count =0;
		try {
			while(scanner.hasNext()) {
				String line = scanner.next();
				count++;
				displayLine(count,line);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
