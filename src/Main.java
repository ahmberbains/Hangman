public class Main {

    public static void main (String[] args){
        Game game = new Game();
        HangmanGraphics graphics = new HangmanGraphics();
        game.setGraphics(graphics);
        game.playGame();
    }

} //end of main class
