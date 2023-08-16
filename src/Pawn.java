import java.awt.Point;
import java.util.ArrayList;

public class Pawn extends Piece {
    public boolean doublePush;
    public Pawn(char t, int x, int y, boolean w) {
        super(t, x, y, w);
        doublePush = false;
        
    }

    @Override
    public ArrayList<Point> findMoves(Square[][] board) {
        ArrayList<Point> moves = new ArrayList<Point>();
        int x = this.getFile();
        int y = this.getRank();
        if(this.getType() == 'p') {
            if(y == 1) {
                moves.add(new Point(x, y+2));
            }
            if(y+1 <= 7) {
                
                if(board[y+1][x].getPiece() == null) {
                    moves.add(new Point(x, y+1));
                }
                if(x+1 <= 7) {
                    if(board[y+1][x+1].getPiece() != null) {
                        if(Character.isUpperCase(board[y+1][x+1].getPiece().getType()) != Character.isUpperCase(this.getType())) {
                            moves.add(new Point(x+1, y+1));
                        }
                    }
                }
                if(x-1 >= 0) {
                    if(board[y+1][x-1].getPiece() != null) {
                        if(Character.isUpperCase(board[y+1][x-1].getPiece().getType()) != Character.isUpperCase(this.getType())) {
                            moves.add(new Point(x-1, y+1));
                        }
                    }
                }
            }
        } else if (this.getType() == 'P') {
            if(y == 6) {
                moves.add(new Point(x, y-2));
            }
            if(y-1 >= 0) {
                if(board[y-1][x].getPiece() == null) {
                    moves.add(new Point(x, y-1));
                }
                
                if(x-1 >= 0) {
                    if(board[y-1][x-1].getPiece() != null) {
                        if(Character.isUpperCase(board[y-1][x-1].getPiece().getType()) != Character.isUpperCase(this.getType())) {
                            moves.add(new Point(x-1, y-1));
                        }
                    }
                }
                if(x+1 <= 7) {
                    if(board[y-1][x+1].getPiece() != null) {
                        if(Character.isUpperCase(board[y-1][x+1].getPiece().getType()) != Character.isUpperCase(this.getType())) {
                            moves.add(new Point(x+1, y-1));
                        }
                    }
                }
            }   
        }
        return moves;
    }

    public ArrayList<Point> findDefense(Square[][] board) {
        ArrayList<Point> defense = new ArrayList<Point>();
        int x = this.getFile();
        int y = this.getRank();
        if(this.isWhite()) {
            if(x-1 >= 0 && y-1 >= 0) {
                defense.add(new Point(x-1, y-1));
            }
            if(x+1 <= 7 && y-1 >= 0) {
                defense.add(new Point(x+1, y-1));
            }
        } else {
            if(x-1 >= 0 && y+1 <= 7) {
                defense.add(new Point(x-1, y+1));
            }
            if(x+1 <= 7 && y+1 <= 7) {
                defense.add(new Point(x+1, y+1));
            }
        }
        return defense;
    }

    public boolean getDoublePush() {
        return this.doublePush;
    }

    public void setDoublePush(boolean b) {
        this.doublePush = b;
    }
}
