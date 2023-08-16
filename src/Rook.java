import java.awt.Point;
import java.util.ArrayList;

public class Rook extends Piece {
    public Rook (char t, int x, int y, boolean w, boolean k) {
        super(t, x, y, w);
    }

    public void move(int x, int y) {
        super.move(x, y);
        setMoved(true);
    }

    @Override
    public ArrayList<Point> findMoves(Square[][] board) {
        ArrayList<Point> moves = new ArrayList<Point>();
        int x = this.getFile();
        int y = this.getRank();
        while(x > 0) {
            x--;
            if(board[y][x].getPiece() == null) {
                moves.add(new Point(x,y));
            } else if(Character.isUpperCase(board[y][x].getPiece().getType()) != Character.isUpperCase(this.getType())) {
                moves.add(new Point(x,y));
                break;
            } else {
                break;
            }
        }
        x = this.getFile();
        while(x < 7) {
            x++;
            if(board[y][x].getPiece() == null) {
                moves.add(new Point(x,y));
            } else if(Character.isUpperCase(board[y][x].getPiece().getType()) != Character.isUpperCase(this.getType())) {
                moves.add(new Point(x,y));
                break;
            } else {
                break;
            }
        }
        x = this.getFile();
        while(y > 0) {
            y--;
            if(board[y][x].getPiece() == null) {
                moves.add(new Point(x,y));
            } else if(Character.isUpperCase(board[y][x].getPiece().getType()) != Character.isUpperCase(this.getType())) {
                moves.add(new Point(x,y));
                break;
            } else {
                break;
            }
        }
        y = this.getRank();
        while(y < 7) {
            y++;
            if(board[y][x].getPiece() == null) {
                moves.add(new Point(x,y));
            } else if(Character.isUpperCase(board[y][x].getPiece().getType()) != Character.isUpperCase(this.getType())) {
                moves.add(new Point(x,y));
                break;
            } else {
                break;
            }
        }
        return moves;
    }

    public ArrayList<Point> findDefense(Square[][] board) {
        ArrayList<Point> defense = new ArrayList<Point>();
        int x = this.getFile();
        int y = this.getRank();
        while(x > 0) {
            x--;
            defense.add(new Point(x,y));
            if(board[y][x].getPiece() != null) {
                if(board[y][x].getPiece().getType() == 'K' || board[y][x].getPiece().getType() == 'k') {
                    defense.add(new Point(x, y));
                } else {
                    break; 
                }
            }
        }
        x = this.getFile();
        while(x < 7) {
            x++;
            defense.add(new Point(x,y));
            if(board[y][x].getPiece() != null) {
                if(board[y][x].getPiece().getType() == 'K' || board[y][x].getPiece().getType() == 'k') {
                    defense.add(new Point(x, y));
                } else {
                    break; 
                }
            }
        }
        x = this.getFile();
        while(y > 0) {
            y--;
            defense.add(new Point(x,y));
            if(board[y][x].getPiece() != null) {
                if(board[y][x].getPiece().getType() == 'K' || board[y][x].getPiece().getType() == 'k') {
                    defense.add(new Point(x, y));
                } else {
                    break; 
                }
            }
        }
        y = this.getRank();
        while(y < 7) {
            y++;
            defense.add(new Point(x,y));
            if(board[y][x].getPiece() != null) {
                if(board[y][x].getPiece().getType() == 'K' || board[y][x].getPiece().getType() == 'k') {
                    defense.add(new Point(x, y));
                } else {
                    break; 
                }
            }
        }
        return defense;
    }
}
