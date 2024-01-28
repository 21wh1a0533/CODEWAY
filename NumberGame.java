import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        playGame();
    }

    public static void playGame() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int rounds = 0;
        int score = 0;

        while (true) {
            System.out.println("\nRound " + (rounds + 1));
            if (guessNumber(random)) {
                score++;
            }
            rounds++;

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.nextLine();
            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }
        }

        System.out.println("\nGame over!");
        System.out.println("Rounds played: " + rounds);
        System.out.println("Score: " + score);
    }

    public static boolean guessNumber(Random random) {
        int targetNumber = random.nextInt(100) + 1;
        int attempts = 0;
        int maxAttempts = 7;

        System.out.println("Guess the number between 1 and 100.");

        while (attempts < maxAttempts) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();

            if (guess == targetNumber) {
                System.out.println("Congratulations! You guessed the correct number: " + targetNumber);
                return true;
            } else if (guess < targetNumber) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }

            attempts++;
            System.out.println("Attempts left: " + (maxAttempts - attempts));
        }

        System.out.println("Sorry, you've run out of attempts. The number was: " + targetNumber);
        return false;
    }
}
