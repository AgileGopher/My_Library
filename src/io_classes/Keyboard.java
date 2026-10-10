package io_classes;

import java.util.Scanner;

public class Keyboard {

	
	// fields
	private Scanner input;
	
	
	// constructor
	public Keyboard() {
		input = new Scanner(System.in);
	}
	
	
	public int readInteger(String promptMsg, String errorMsg) { 
		
		int num = 0;
		String strInput;
		boolean valid = false;
		
		while(valid == false) {  
			
			System.out.println(promptMsg);
			strInput = input.nextLine();
			
			try {
				num   = Integer.parseInt(strInput);
				valid = true;
			}
			catch(NumberFormatException e) {
				System.out.println(errorMsg);
			}			
		}
		return num;
	}
	
	
	public String readString(String promptMsg, String errorMsg) {
		
		String strInput = null;
		boolean valid   = false;
		
		while(valid == false) {
			
			System.out.println(promptMsg);
			strInput = input.nextLine();
			
			try {
				if(!(strInput == null) && !strInput.isBlank()) {
					valid = true;
				}
				else {
					System.out.println(errorMsg);
				}
			}	
			catch(Exception e) {
				System.out.println(errorMsg);
		    }	
		}
		return strInput;
	}
}
