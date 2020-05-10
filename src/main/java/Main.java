
import Controller.GameController;
import Controller.GraphicLoop;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {


        GameController.getInstance().initialBoardFromConfig();
        GameController.getInstance().setImageOfPuzzlePieces();
        GraphicLoop graphicLoop = new GraphicLoop();
        graphicLoop.start();

    }
}
