package com.flooring.view;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class UserIOImpl implements UserIO {
	private Scanner scanner = new Scanner(System.in);

	@Override
	public void print(String message) {
		System.out.println(message);
		
	}

	@Override
	public String readString(String prompt) {
		System.out.println(prompt);
		String input = scanner.nextLine().trim();
		
		return input;
	}

	@Override
	public int readInt(String prompt) {
		System.out.println(prompt);
		int input = scanner.nextInt();
		scanner.nextLine();
		
		return input;
	}

	@Override
	public int readInt(String prompt, int min, int max) {
		int input;
		
		 do {
	            System.out.print(prompt);
	            input = scanner.nextInt();
	    		scanner.nextLine();

	            if (input < min ||input  > max) {
	                System.out.println("Invalid input. Enter a number within the specified range.");
	            }
	        } while (input < min ||input  > max);
		 
		 return input;
	}

	@Override
	public double readDouble(String prompt) {
		System.out.println(prompt);
		double input = scanner.nextDouble();
		scanner.nextLine();
		
		return input;
	}

	@Override
	public double readDouble(String prompt, double min) {
		double input;
		
		 do {
	            System.out.print(prompt);
	    		input = scanner.nextDouble();
	    		scanner.nextLine();
	 
	            if (input < min) {
	                System.out.println("Invalid input. Enter a number within the specified range.");
	            }
	        } while (input < min);
		 
		 return input;
	}

	@Override
	public float readFloat(String prompt) {
		System.out.println(prompt);
		float input = scanner.nextFloat();
		scanner.nextLine();
		
		return input;
	}

	@Override
	public float readFloat(String prompt, float min, float max) {
		float input;
		
		 do {
	            System.out.print(prompt);
	            input = scanner.nextFloat();
	            scanner.nextLine();

	            if (input < min ||input  > max) {
	                System.out.println("Invalid input. Enter a number within the specified range.");
	            }
	        } while (input < min ||input  > max);
		 
		 return input;
	}

	@Override
	public long readLong(String prompt) {
		System.out.println(prompt);
		Long input = scanner.nextLong();
		scanner.nextLine();
		
		return input;
	}

	@Override
	public long readLong(String prompt, long min, long max) {
		long input;
		
		 do {
	            System.out.print(prompt);
	            input = scanner.nextLong();
	            scanner.nextLine();

	            if (input < min ||input  > max) {
	                System.out.println("Invalid input. Enter a number within the specified range.");
	            }
	        } while (input < min ||input  > max);
		 
		 return input;
	}

	@Override
	public boolean getConfirmation(String prompt) {
		boolean result = false;
		System.out.println(prompt);
		String input = "";
		
		do {
			input = scanner.nextLine().toLowerCase();
		} while (!input.equals("y") && !input.equals("n"));
		
		if (input.equals("y")) result = true;
		
		return result;
	}

}
