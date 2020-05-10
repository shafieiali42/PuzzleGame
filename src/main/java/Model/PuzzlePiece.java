package Model;


public class PuzzlePiece {

    private String imagePath;
    private int pieceNumber;


    public PuzzlePiece(int pieceNumber) {
        this.pieceNumber = pieceNumber;
        if (pieceNumber != 8) {
            int k = pieceNumber + 1;
            imagePath = "src/main/resources/Assets/" + k + ".png";
        } else {

            imagePath = "src/main/resources/Assets/missing.jpg";
        }
    }


    private PuzzlePiece(String imagePath, int pieceIdentifier) {
        this.imagePath = imagePath;
        this.pieceNumber = pieceIdentifier;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getPieceNumber() {
        return pieceNumber;
    }

    public void setPieceNumber(int pieceNumber) {
        this.pieceNumber = pieceNumber;
    }

    public PuzzlePiece getClone() {
        PuzzlePiece clone = new PuzzlePiece(imagePath, pieceNumber);
        return clone;
    }

}
