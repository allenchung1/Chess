import java.awt.Point;
import java.util.ArrayList;
public class Bishop extends Piece {
    public Bishop(char t, int x, int y, boolean w) {
        super(t, x, y, w);
    }

    @Override
    public ArrayList<Point> findMoves(Square[][] board) {
        ArrayList<Point> moves = new ArrayList<Point>();
        int x = this.getFile();
        int y = this.getRank();
        while(x > 0 && y > 0) { //topleft
            x--;
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
        x = this.getFile();
        y = this.getRank();
        while(x > 0 && y < 7) { //bottomleft
            x--;
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
        x = this.getFile();
        y = this.getRank();
        while(x < 7 && y > 0) { //topright
            x++;
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
        x = this.getFile();
        y = this.getRank();
        while(x < 7 && y < 7) { //bottomright
            x++;
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
        while(x > 0 && y > 0) { //topleft
            x--;
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
        x = this.getFile();
        y = this.getRank();
        while(x > 0 && y < 7) { //bottomleft
            x--;
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
        x = this.getFile();
        y = this.getRank();
        while(x < 7 && y > 0) { //topright
            x++;
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
        x = this.getFile();
        y = this.getRank();
        while(x < 7 && y < 7) { //bottomright
            x++;
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
