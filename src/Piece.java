import java.awt.Point;
import java.util.ArrayList;
public abstract class Piece {
    private char type;
    private int file;
    private int rank;
    private boolean isWhite;
    private boolean defended;
    private boolean moved;

    public Piece(char t, int x, int y, boolean w) {
        type = t;
        file = x;
        rank = y;
        isWhite = w;
        defended = false;
    }

    public char getType() { 
        return this.type;
    }

    public int getFile() {
        return this.file;
    }

    public int getRank() {
        return this.rank;
    }

    public boolean isWhite() {
        return this.isWhite;
    }

    public boolean getDefended() {
        return defended;
    }

    public void setDefended(boolean b) {
        this.defended = b;
    }

    public boolean hasMoved() {
        return this.moved;
    }

    public void setMoved(boolean b) {
        this.moved = b;
    }
    
    public void move(int x, int y) {
        this.file = x;
        this.rank = y;
    }
    
    public abstract ArrayList<Point> findMoves(Square[][] b);

    public abstract ArrayList<Point> findDefense(Square [][]b);
    
}
