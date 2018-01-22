package UI;
import javax.swing.*;

/**
 * Created by Adam on 2017-07-09.
 */
public class ChessGame extends JFrame{

    public ChessGame() {
        Chessboard.createInstance();
        Chessboard chessboard = Chessboard.getInstance();
        chessboard.startGame();
    }

    public static void main(String[] args) {
        new ChessGame();
    }
}
