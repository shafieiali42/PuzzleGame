package Controller;

import Model.Board;
import Model.PuzzlePiece;
import View.GamePanel;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class GameController {

    private static GameController gameController = new GameController();
    public HashMap<Integer, Image> imageOfPuzzlePieces = new HashMap<Integer, Image>();

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

    public void moveRight() {
        if (Board.getInstance().getMissingPiece() % 3 == 2) {
            return;
        }
        swapPieces(Board.getInstance().getMissingPiece(), Board.getInstance().getMissingPiece() + 1);
        Board.getInstance().setMissingPiece(Board.getInstance().getMissingPiece() + 1);
    }

    public void moveLeft() {
        if (Board.getInstance().getMissingPiece() % 3 == 0) {
            return;
        }
        swapPieces(Board.getInstance().getMissingPiece(), Board.getInstance().getMissingPiece() - 1);
        Board.getInstance().setMissingPiece(Board.getInstance().getMissingPiece() - 1);
    }

    public void moveUp() {
        if (Board.getInstance().getMissingPiece() <= 2) {
            return;
        }
        swapPieces(Board.getInstance().getMissingPiece(), Board.getInstance().getMissingPiece() - 3);
        Board.getInstance().setMissingPiece(Board.getInstance().getMissingPiece() - 3);
    }

    public void moveDown() {
        if (Board.getInstance().getMissingPiece() >= 6) {
            return;
        }
        swapPieces(Board.getInstance().getMissingPiece(), Board.getInstance().getMissingPiece() + 3);
        Board.getInstance().setMissingPiece(Board.getInstance().getMissingPiece() + 3);
    }


    public HashMap<Integer, Image> setImageOfPuzzlePieces() throws IOException {

        for (PuzzlePiece puzzlePiece : Board.getInstance().getPuzzlePieces()) {
            imageOfPuzzlePieces.put(puzzlePiece.getPieceNumber(), ImageIO.read(new File(puzzlePiece.getImagePath())));
        }
        return imageOfPuzzlePieces;
    }

    public void drawBoard(JPanel panel, Graphics2D graphics2D) throws IOException {

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

    public void swapPieces(int i, int j) {
        PuzzlePiece copy = Board.getInstance().getPuzzlePieces().get(i).getClone();
        Board.getInstance().getPuzzlePieces().get(i).setImagePath(Board.getInstance().getPuzzlePieces().get(j).getImagePath());
        Board.getInstance().getPuzzlePieces().get(i).setPieceNumber(Board.getInstance().getPuzzlePieces().get(j).getPieceNumber());
        Board.getInstance().getPuzzlePieces().get(j).setImagePath(copy.getImagePath());
        Board.getInstance().getPuzzlePieces().get(j).setPieceNumber(copy.getPieceNumber());


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
