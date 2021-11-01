import java.util.Scanner;
import javax.swing.*;
public class Game {

    Scanner input;
    String guessWord;
    char[] guessChars;
    static int wordLength;
    int numGuesses;
    String hiddenWord;
    static char[] hiddenChars;
    HangmanGraphics graphics;


    public Game(){
        gameInit();
    }

    void setGraphics(HangmanGraphics g){
        graphics = g;
    }

    void gameInit(){

        input = new Scanner(System.in);

        System.out.println("Welcome to Hangman!");
        System.out.println("This is a game for two people. Player 1 starts by writing a word.");
        guessWord = input.next();
        guessChars = guessWord.toCharArray();
        wordLength = guessWord.length();
        hiddenChars = new char[wordLength];
        for(int i=0; i < wordLength; i++){
        hiddenChars[i] = '_';
        }

        hiddenWord = new String(hiddenChars);
        numGuesses = 6;

        System.out.println("Now it's up to player 2 to start guessing!");

    }

    void playGame(){
        while(!guessWord.equals(hiddenWord)){
            System.out.println("Please guess a letter in this 6-letter word.");
            System.out.println("You have " + numGuesses + " guesses left.");
            System.out.println();
            /*
            String inputWord = input.next();
            char[] inputChars = inputWord.toCharArray();
            char inputChar = inputChars[0];

             */
            char  inputChar = getGuess();
            System.out.println("Your guess was: " + inputChar);
            boolean guessedRight = false;
            for (int i=0; i < guessChars.length; i++) {
                char guessChar = guessChars[i];
                char guessLC = Character.toLowerCase(guessChar);
                char inputLC = Character.toLowerCase(inputChar);
                if (guessLC == inputLC){
                    System.out.println("You guessed right!");
                    guessedRight = true;
                    hiddenChars[i] = guessChar;
                }

                if(!guessedRight){
                    numGuesses -- ;
                }

            }

            if(numGuesses == 0){
                System.out.println("Oh no! You ran out of guesses. Better luck next time!");
                return;
            }
            hiddenWord = new String(hiddenChars);
            System.out.println(hiddenChars);

            graphics.update();
        }
        char getGuess(){
            try{
                String guess = JOptionPane.showInputDialog("Guess a letter!");
                if(guess == null){
                    String exitInput = JOptionPane.showInputDialog("Exit game?").toLowerCase();
                    boolean doExit = exitInput.equals("yes") || exitInput.equals("y");
                    if(doExit || exitInput == null)
                        System.exit(0);
                    else
                        getGuess();
                }
                return guess.charAt(0);
            } catch (Exception e){
                getGuess();
            }
            return ' ';
        }
    }


        System.out.println("\nCongratulations! You guessed the word.");
    }

} // public class game
