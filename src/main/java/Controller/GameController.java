package Controller;

import Model.Board;
import Model.PuzzlePiece;
import Utility.ConfigLoader;
import View.GamePanel;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;


public class GameController {

    private static GameController gameController;

    static {
        try {
            gameController = new GameController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Properties properties = ConfigLoader.getInstance().readProperties("src/main/resources/ConfigFiles/InitialBoardConfig.properties");
    ArrayList<String> imagesPath = new ArrayList<>(Arrays.asList(properties.get("Images").toString().split(",")));
    public HashMap<Integer, Image> imageOfPuzzlePieces = new HashMap<Integer, Image>();

    public GameController() throws IOException {
    }

    public static GameController getInstance() {
        return gameController;
    }

    private String gameState = "#";
    private boolean gameFinished = false;

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public void moveRight() throws IOException {
        if (Board.getInstance().getMissingPiece() % 3 == 2) {
            return;
        }
        swapPieces(Board.getInstance().getMissingPiece(), Board.getInstance().getMissingPiece() + 1);
        Board.getInstance().setMissingPiece(Board.getInstance().getMissingPiece() + 1);
    }

    public void moveLeft() throws IOException {
        if (Board.getInstance().getMissingPiece() % 3 == 0) {
            return;
        }
        swapPieces(Board.getInstance().getMissingPiece(), Board.getInstance().getMissingPiece() - 1);
        Board.getInstance().setMissingPiece(Board.getInstance().getMissingPiece() - 1);
    }

    public void moveUp() throws IOException {
        if (Board.getInstance().getMissingPiece() <= 2) {
            return;
        }
        swapPieces(Board.getInstance().getMissingPiece(), Board.getInstance().getMissingPiece() - 3);
        Board.getInstance().setMissingPiece(Board.getInstance().getMissingPiece() - 3);
    }

    public void moveDown() throws IOException {
        if (Board.getInstance().getMissingPiece() >= 6) {
            return;
        }
        swapPieces(Board.getInstance().getMissingPiece(), Board.getInstance().getMissingPiece() + 3);
        Board.getInstance().setMissingPiece(Board.getInstance().getMissingPiece() + 3);
    }

    public void initialBoardFromConfig() {
        ArrayList<String> initialOrdering = new ArrayList<String>(Arrays.asList(properties.get("InitialOrdering").toString().split(",")));
        ArrayList<Integer> integers = new ArrayList<>();
        for (String s : initialOrdering) {
            integers.add(Integer.parseInt(s));
        }
        Board.getInstance().setPuzzlePieces(integers);

        if (!solvable(Board.getInstance().getMissingPiece(),integers)) {
            JOptionPane.showMessageDialog(null, "this puzzle is not solvable, change your config and try again", "Puzzle not solvable", JOptionPane.WARNING_MESSAGE);
            GameController.getInstance().setGameFinished(true);
        }
    }

    private  boolean solvable(int missingPiece, ArrayList<Integer> piecesOrder) {
        int inversionCount = 0;
        for (int i = 0; i < piecesOrder.size(); i++) {
            for (int j = i + 1; j < piecesOrder.size(); j++) {
                if (piecesOrder.get(i) > piecesOrder.get(j)) {
                    inversionCount += 1;
                }
            }
        }

        int parity = inversionCount % 2;

        int  distanceOfMissingPiece = (2 - (missingPiece % 3)) + (2 - (missingPiece / 3));

        parity ^= (distanceOfMissingPiece % 2);
        if (parity == 0) {
            return true;
        }
        return false;
    }

    public HashMap<Integer, Image> setImageOfPuzzlePieces() throws IOException {
        String address = "src/main/resources/Assets/";
        imageOfPuzzlePieces.clear();
        for (String path : imagesPath) {
            switch (path.substring(0, 1)) {
                case "1":
                    imageOfPuzzlePieces.put(0, ImageIO.read(new File(address + path)));
                    break;
                case "2":
                    imageOfPuzzlePieces.put(1, ImageIO.read(new File(address + path)));
                    break;
                case "3":
                    imageOfPuzzlePieces.put(2, ImageIO.read(new File(address + path)));
                    break;
                case "4":
                    imageOfPuzzlePieces.put(3, ImageIO.read(new File(address + path)));
                    break;
                case "5":
                    imageOfPuzzlePieces.put(4, ImageIO.read(new File(address + path)));
                    break;
                case "6":
                    imageOfPuzzlePieces.put(5, ImageIO.read(new File(address + path)));
                    break;
                case "7":
                    imageOfPuzzlePieces.put(6, ImageIO.read(new File(address + path)));
                    break;
                case "8":
                    imageOfPuzzlePieces.put(7, ImageIO.read(new File(address + path)));
                    break;
                default:
                    imageOfPuzzlePieces.put(8, ImageIO.read(new File(address + path)));
            }
        }
        return imageOfPuzzlePieces;
    }

    public void drawBoard(Graphics2D graphics2D){

        int i = 0;
        for (PuzzlePiece puzzlePiece : Board.getInstance().getPuzzlePieces()) {
            graphics2D.drawImage(imageOfPuzzlePieces.get(puzzlePiece.getPieceNumber()),
                    (int) GamePanel.getInstance().getPreferredSize().getWidth() / 3 * (i % 3),
                    (int) GamePanel.getInstance().getPreferredSize().getHeight() / 3 * (i / 3),
                    (int) GamePanel.getInstance().getPreferredSize().getWidth() / 3,
                    (int) GamePanel.getInstance().getPreferredSize().getHeight() / 3, null);
            i++;
        }
    }

    public void swapPieces(int i, int j) throws IOException {
        PuzzlePiece copy = Board.getInstance().getPuzzlePieces().get(i).getClone();
        Board.getInstance().getPuzzlePieces().get(i).setPieceNumber(Board.getInstance().getPuzzlePieces().get(j).getPieceNumber());
        Board.getInstance().getPuzzlePieces().get(j).setPieceNumber(copy.getPieceNumber());
        setImageOfPuzzlePieces();

        if (gameFinished()) {
            gameState = "finished";
        }
    }

    public boolean gameFinished() {
        for (int i = 0; i < 9; i++) {
            int pieceIdentifier = Board.getInstance().getPuzzlePieces().get(i).getPieceNumber();
            if (pieceIdentifier == 8) {
                continue;
            }

            if (pieceIdentifier != i) {
                return false;
            }
        }
        return true;
    }

}
