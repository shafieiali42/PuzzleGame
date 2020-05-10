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

    public boolean solvable(ArrayList<Integer> piecesOrder) {
        int inversionCount = 0;
        for (int i = 0; i < piecesOrder.size(); i++) {
            for (int j = i + 1; j < piecesOrder.size(); j++) {
                if (piecesOrder.get(i) > piecesOrder.get(j)) {
                    inversionCount += 1;
                }
            }
        }

        int parity = inversionCount % 2;

        int distanceOfMissingPiece = (2 - (missingPiece % 3)) + (2 - (missingPiece / 3));

        parity ^= (distanceOfMissingPiece % 2);
        if (parity == 0) {
            return true;
        }
        return false;
    }


}
