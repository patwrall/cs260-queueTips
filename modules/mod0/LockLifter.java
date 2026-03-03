/*
    Terrell Richey - richtj03@pfw.edu
    LOCK LIFTER
    This class handles the manipulation of the Lock and the overall game logic.
*/

package cs260.queueTips.mod0;
import java.util.Scanner;

public class LockLifter {

    //Convert the user input to an int array
    public static int[] convertGuess(String guess) {
        int[] conversion = new int[guess.length()];
        for (int i = 0; i < guess.length() ; i++){
            conversion[i] = guess.charAt(i) - '0';
        }

        return conversion;
    }

    //Run the game
    public static void run() {
        int len = 1;
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while(running){
            Lock lock = new Lock(len);

            for (int i = 0; i < len; i++){
                System.out.print('#');
            }
            System.out.print('\n');

            while(true){
                try {
                    System.out.print("Enter guess: ");
                    String userInput = input.next().trim();

                    if (userInput.equals("q") || userInput.equals("Q")) {
                        running = false;
                        break;
                    }

                    int[] guess = convertGuess(userInput);
                    int result = lock.getDifference(guess);

                    if (result == 0) {
                        System.out.println("Correct!");
                        len++;
                        break;
                    } else {
                        System.out.println("Incorrect: " + result);
                    }
                } catch (Exception e) {
                    System.out.println("\nInvalid input. Must be a natural number and equal to hash length.\n");
                    System.err.print(e.getMessage());
                }
            }
        }
    
    }

    public static void main(String[] args) {
        run();
    }
}
