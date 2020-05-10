package Model;


public class PuzzlePiece {

    private int pieceNumber;

    public PuzzlePiece(int pieceNumber) {
        this.pieceNumber = pieceNumber;
    }

    public int getPieceNumber() {
        return pieceNumber;
    }

    public void setPieceNumber(int pieceNumber) {
        this.pieceNumber = pieceNumber;
    }

    public PuzzlePiece getClone() {
        PuzzlePiece clone = new PuzzlePiece(pieceNumber);
        return clone;
    }

}
