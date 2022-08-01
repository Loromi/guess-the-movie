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
		System.out.println("File exists: " + movies.exists());
		
		try {
			Scanner scanner = new Scanner(movies, StandardCharsets.UTF_8);
			
			int i = 0;
			while (scanner.hasNextLine()) {
				i++;
				scanner.nextLine();
			} 
			scanner.close();
			
			String[] lines = new String[i+1];
			scanner = new Scanner(movies, StandardCharsets.UTF_8);
			
			int j = 0;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				lines[j] = line;
				j++;
			}
			scanner.close();
			System.out.println("Number of movies: " + lines.length);
			int k = ThreadLocalRandom.current().nextInt(0, lines.length);
			String movie = lines[k];
			System.out.println("random movie: " + movie);
			
			return movie;
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	
	public static void printSolution(char[] solution, char[] guess, char[] movieArray) {
		
		char firstChar = guess[0];
		System.out.println(guess);
		System.out.println(movieArray);
		if (Arrays.equals(guess, movieArray)) {
			foundSolution = true;
			System.out.println("foundSolution: " + foundSolution);
		}
		if (new String(movieArray).indexOf(firstChar) < 0) {
			tries--;
		}
		for (int i=0; i<movieArray.length; i++) {
			if (movieArray[i] == firstChar) {
				solution[i] = movieArray[i];
			}
		}
		System.out.println(solution);
		System.out.println("You have guessed " + (-1 * (tries - 10)) + " wrong letters");
	}
	
	public static char[] getUserInput(String movie) throws NoSuchElementException {
		
		Scanner scanner = new Scanner(System.in);
		
		char[] userInput;

		userInput = scanner.nextLine().toCharArray();
		// scanner.close();
			
		// char guess = userInput[0];
		return userInput;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		tries = 10;
		System.out.println("Welcome to guess the movie. Game still needs to be built...");
		String movie = getMovie();
		char[] movieArray = printQuiz(movie);
		char[] solution = new char[(movie.length())];
		
		for (int i=0; i<movieArray.length; i++) {
			solution[i] = '_';
		}
		
		while (!foundSolution && tries > 0) {
			
			char[] guess = getUserInput(movie);

			printSolution(solution, guess, movieArray);
		}
		
		if (tries == 0) {
			System.out.println("Unfortunately you're out of tries");
		}
	}
}
