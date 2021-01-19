import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GuessTheNumber game = new GuessTheNumber();

        System.out.println("Welcome to the 'Guess The Number' game!");
        TimeUnit.MILLISECONDS.sleep(1500);
        game.playGame();
    }    
}
