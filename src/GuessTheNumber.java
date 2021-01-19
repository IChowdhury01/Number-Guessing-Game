import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GuessTheNumber {

    private Scanner reader;
    private int numQuestions;

    public GuessTheNumber() {
        this.reader = new Scanner(System.in);
        this.numQuestions = 0;
    }

    /**
     * Uses Binary Search:
     *
     * Create array for number range
     * Locate middle value of array
     * Find if guessed value > middle value
     *  If it is, update lower limit to [middle value + 1]
     *  Else, update upper limit to middle value
     *
     * Repeat
     */
    public void playGame() {
        int[] limits = new int[0];

        try {
            limits = askRange();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int lowerLimit = limits[0];
        int upperLimit = limits[1];

        printInstructions(lowerLimit, upperLimit);

        while(lowerLimit != upperLimit) {
            int[] possibleNumbers = createRange(lowerLimit, upperLimit);

            int middleVal = findMiddleVal(possibleNumbers);

            if (isGreaterThan(middleVal)) {
                lowerLimit = middleVal+1;
            }
            else {
                upperLimit = middleVal;
            }
        }

        System.out.println("The number you're thinking of is " + lowerLimit + "!");
        System.out.println("How many questions did it take? " + this.numQuestions + ".");
    }


    // Returns an array with the lower/upper bounds of the game.
    public int[] askRange() throws InterruptedException {
        System.out.println("Please think of a number, any number! I'll wait...");
        TimeUnit.MILLISECONDS.sleep(3500);

        System.out.println("Now, please set a range where your number might be in. It could be as large as you want.");

        int vals[] = new int[2];
        System.out.println("Minimum: ");
        vals[0] = Integer.parseInt(reader.nextLine());

        System.out.println("Maximum: ");
        vals[1] = Integer.parseInt(reader.nextLine());

        return vals;
    }

    public void printInstructions(int lowerLimit, int upperLimit) {
        int maxQuestions = halfableCount(upperLimit - lowerLimit);

        System.out.println("Thanks. I promise you that I can guess the number you are thinking within " + maxQuestions + " questions.");
        System.out.println("");
        System.out.println("Now, I'll ask you a series of questions. Please answer them honestly.");
        System.out.println("");
    }


    // a helper method: Finds how many times a number can be halfed.
    public static int halfableCount(int number) {
        return (int) (Math.log(number) / Math.log(2)) + 1;
    }


    public boolean isGreaterThan(int value) {
        numQuestions++;
        System.out.println("Is your number greater than " + value + "? (y/n)");
        String answer = this.reader.nextLine();
        if (answer.equals("y")) {
            return true;
        }
        else {
            return false;
        }
    }


    public static int[] createRange (int lowerLimit, int upperLimit) {
        int[] array = new int[upperLimit+1-lowerLimit];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + lowerLimit;
        }
        return array;
    }


    public int average (int first, int second) {
        return (first+second)/2;
    }


    public int findMiddleVal (int[] array) {
        int middleIndex = average(0,array.length-1);
        return array[middleIndex];
    }
}
