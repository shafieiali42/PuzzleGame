package Model;

import java.util.ArrayList;

public class Board {

    private static Board board = new Board();
    public static Board getInstance() {
        return board;
    }

    private ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();
    private int missingPiece = 0;

    private Board() {

    }

    public void setPuzzlePieces(ArrayList<Integer> initialOrdering) {
        for (int integer = 0; integer < initialOrdering.size(); integer++) {
            if (initialOrdering.get(integer) == 8) {
                this.missingPiece = integer;
            }
            puzzlePieces.add(new PuzzlePiece(initialOrdering.get(integer)));
        }
    }


    public void setMissingPiece(int missingPiece) {
        this.missingPiece = missingPiece;
    }

    public int getMissingPiece() {
        return missingPiece;
    }

    public ArrayList<PuzzlePiece> getPuzzlePieces() {
        return puzzlePieces;
    }




}
