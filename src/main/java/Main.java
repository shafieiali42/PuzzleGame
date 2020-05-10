import Utility.ConfigLoader;
import Controller.GameController;
import Model.Board;
import View.GraphicLoop;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        Properties properties =
                ConfigLoader.getInstance().readProperties("src/main/resources/ConfigFiles/InitialBoardConfig.properties");
        ArrayList<String> initialOrdering = new ArrayList<String>(Arrays.asList(properties.get("InitialOrdering").toString().split(",")));
        ArrayList<Integer> integers = new ArrayList<>();
        for (String s : initialOrdering) {
            integers.add(Integer.parseInt(s));
        }
        Board.getInstance().setPuzzlePieces(integers);

        if (!Board.getInstance().solvable(integers)) {
            JOptionPane.showMessageDialog(null, "this puzzle is not solvable, change your config and try again", "Puzzle not solvable", JOptionPane.WARNING_MESSAGE);
            GameController.getInstance().setGameFinished(true);
        }
        GameController.getInstance().setImageOfPuzzlePieces();

        GraphicLoop graphicLoop = new GraphicLoop();
        graphicLoop.start();

    }
}
