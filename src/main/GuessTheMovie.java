package main;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class GuessTheMovie {
	
	private static boolean foundSolution = false;
	
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
			
			String[] lines = new String[i+1];
			scanner = new Scanner(movies, StandardCharsets.UTF_8);
			
			int j = 0;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				lines[j] = line;
				j++;
			}
			
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
	
	public static void printSolution(String movie, char guess, char[] movieArray) {
		
		for (int i=0; i<movieArray.length; i++) {
			if (movieArray[i] == guess) {
				System.out.print(movieArray[i]); 
				System.out.print(" ");
			} else { 
				System.out.print("_ ");
			}
			
		}
		System.out.println();
	}
	
	public static char getUserInput(String movie) {
		
		Scanner scanner = new Scanner(System.in);
		
		char[] userInput = scanner.next().toCharArray();
		
		char guess = userInput[0];
		
		return guess;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to guess the movie. Game still needs to be built...");
		String movie = getMovie();
		char[] movieArray = printQuiz(movie);
		while (!foundSolution) {
			char guess = getUserInput(movie);
			printSolution(movie, guess, movieArray);
		}
	}

}
