package Hangmangame;
import java.util.*;
public class HangmanGame {
	    private static final String[] WORDS = {"java", "hangman", "programming", "computer", "algorithm"};
	    private static final int MAX_TRIES = 6;
	    private static final String[] HANGMAN_IMAGES = {
	            "  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========",
	            "  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========",
	            "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========",
	            "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========",
	            "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========",
	            "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========",
	            "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="
	    };

	    public static void main(String[] args) 
	    {
	        Scanner scanner = new Scanner(System.in);
	        Random random = new Random();
	        String wordToGuess = WORDS[random.nextInt(WORDS.length)];
	        char[] guessedLetters = new char[wordToGuess.length()];
	        Arrays.fill(guessedLetters, '_');
	        int tries = 0;
	        Set<Character> incorrectGuesses = new HashSet<>();

	        System.out.println("Welcome to Hangman!");
	        while (tries < MAX_TRIES) {
	            System.out.println(HANGMAN_IMAGES[tries]);
	            System.out.println("Word: " + new String(guessedLetters));
	            System.out.println("Incorrect guesses: " + incorrectGuesses);
	            System.out.print("Enter a letter: ");
	            char guess = scanner.nextLine().toLowerCase().charAt(0);

	            if (!Character.isLetter(guess)) {
	                System.out.println("Please enter a valid letter.");
	                continue;
	            }

	            if (wordToGuess.contains(String.valueOf(guess))) {
	                for (int i = 0; i < wordToGuess.length(); i++) {
	                    if (wordToGuess.charAt(i) == guess) {
	                        guessedLetters[i] = guess;
	                    }
	                }
	                if (!new String(guessedLetters).contains("_")) {
	                    System.out.println("Congratulations! You've guessed the word: " + wordToGuess);
	                    break;
	                }
	            } else {
	                incorrectGuesses.add(guess);
	                tries++;
	                System.out.println("Incorrect guess. You have " + (MAX_TRIES - tries) + " tries left.");
	            }
	        }

	        if (tries == MAX_TRIES) {
	            System.out.println("Sorry, you ran out of tries. The word was: " + wordToGuess);
	            System.out.println(HANGMAN_IMAGES[MAX_TRIES]);
	        }

	        scanner.close();
	    }
	}

	

	


