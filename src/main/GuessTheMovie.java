package main;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class GuessTheMovie {
	
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
	
	public static void printQuiz(String movie) {
		
		char[] movieArray = new char[(movie.length())];
		String quiz = "";
		
		for (int i=0; i<=movieArray.length; i++) {
			quiz += "_ ";
		}
		
		System.out.println("You are guessing: " + quiz);
	}
	
	public static void getUserInput(String movie) {
		
		Scanner scanner = new Scanner(System.in);
		
		String guess = scanner.next();
		
		if (guess.equals(movie)) {
			System.out.println("Congratulations! Your guess was right");
		} else {
			System.out.println("Guess again.");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to guess the movie. Game still needs to be built...");
		String movie = getMovie();
		printQuiz(movie);
		getUserInput(movie);
	}

}
