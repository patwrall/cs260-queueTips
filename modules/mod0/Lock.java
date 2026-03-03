package cs260.queueTips.mod0;

import java.util.Random;

public class Lock {
    private final int[] combo;
    private final static Random rng = new Random();

    //Constructer for Lock.
    public Lock(int length) {
        this.combo = new int[length];
        generate();    
    }

    // Generate a new lock combination
    public final void generate() {
        for (int i = 0; i < combo.length; i++) {
            combo[i] = rng.nextInt(10);
        }
    }
    
    //Returns the difference of the lock and user guess. Throws a error if input is null
    //or is not the same length as the lock.
    public int getDifference(int[] guess) {
        if (guess == null || guess.length != combo.length) {
            throw new IllegalArgumentException("\nGuess length must be equal to " + combo.length + "\n");
        }
        
        int score = 0;
        for (int i = 0; i < combo.length; i++) {
            score += Math.abs(combo[i] - guess[i]);
        }

        return score;
    }
}