import javax.swing.*;

public class Square {
    private JPanel panel;
    private int file;
    private int rank;
    private Piece piece;

    public Square(JPanel p, int x, int y) {
        panel = p;
        file = x;
        rank = y;
        piece = null;
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public int getX() {
        return this.file;
    }

    public int getY() {
        return this.rank;
    }

    public Piece getPiece() { 
        return this.piece;
    }

    public void setPiece(Piece p) {
        piece = p;
    }
}
