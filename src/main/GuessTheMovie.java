package main;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class GuessTheMovie {
	
	private static boolean foundSolution = false;
	private static int tries;
	
	public static String getMovie() {
		
		String pathname = System.getProperty("user.dir")+"/movies.txt";
		File movies = new File(pathname);
		
		try {
			Scanner scanner = new Scanner(movies, StandardCharsets.UTF_8);
			
			int i = 0;
			while (scanner.hasNextLine()) {
				i++;
				scanner.nextLine();
			} 
			scanner.close();
			
			String[] lines = new String[i];
			scanner = new Scanner(movies, StandardCharsets.UTF_8);
			
			int j = 0;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				lines[j] = line;
				j++;
			}
			scanner.close();
			int k = ThreadLocalRandom.current().nextInt(0, lines.length);
			String movie = lines[k];
			
			return movie;
		} catch (IOException e) {
			e.printStackTrace();
			return "IOException";
		}
	}
	
	public static char[] printQuiz(String movie) {
		
		System.out.print("You are guessing: ");
		char[] movieArray = new char[(movie.length())];
		movieArray = movie.toCharArray();
		
		for (int i=0; i<movieArray.length; i++) {
			System.out.print("_ ");	
		}
		System.out.println();
		return movieArray;
	}
	
	public static void printSolution(char[] solution, char[] guess, char[] movieArray, String movie) {
		
		char firstChar = guess[0];
		
		if (new String(movieArray).indexOf(firstChar) < 0) {
			tries--;
			System.out.println("You have guessed " + (-1 * (tries - 10)) + " wrong letters");
		}
		for (int i=0; i<movieArray.length; i++) {
			if (movieArray[i] == firstChar) {
				solution[i] = movieArray[i];
			}
			System.out.print(solution[i] + " ");
		}
		if (Arrays.equals(guess, movieArray) || Arrays.equals(solution, movieArray)) {
			foundSolution = true;
			System.out.println("You win!");
			System.out.println("You have guessed " + movie + " correctly.");
		}
	}
	
	public static char[] getUserInput(String movie) throws NoSuchElementException {
		
		Scanner scanner = new Scanner(System.in);
		
		char[] userInput;

		userInput = scanner.nextLine().toCharArray();

		return userInput;
	}

	public static void main(String[] args) {
		tries = 10;
		System.out.println("Welcome to guess the movie.");
		String movie = getMovie();
		char[] movieArray = printQuiz(movie);
		char[] solution = new char[(movie.length())];
		
		for (int i=0; i<movieArray.length; i++) {
			solution[i] = '_';
		}
		
		while (!foundSolution && tries > 0) {
			
			char[] guess = getUserInput(movie);
			System.out.println();
			printSolution(solution, guess, movieArray, movie);
			System.out.println();
		}
		
		if (tries == 0) {
			System.out.println("Unfortunately you're out of tries");
		}
	}
}
