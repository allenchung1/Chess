import java.awt.Point;
import java.util.ArrayList;
public class Knight extends Piece {
    public Knight(char t, int x, int y, boolean w) {
        super(t, x, y, w);
    }

    @Override
    public ArrayList<Point> findMoves(Square[][] board) {
        ArrayList<Point> moves = new ArrayList<Point>();
        int x = this.getFile();
        int y = this.getRank();
        if(x-2 >= 0) {
            if(y-1 >= 0) {
                if(board[y-1][x-2].getPiece() == null) {
                    moves.add(new Point(x-2, y-1));
                } else {
                    if(Character.isUpperCase(board[y-1][x-2].getPiece().getType()) != Character.isUpperCase(this.getType())) {
                        moves.add(new Point(x-2, y-1));
                    }
                }
            }
            if(y+1 <= 7) {
                if(board[y+1][x-2].getPiece() == null) {
                    moves.add(new Point(x-2, y+1));
                } else {
                    if(Character.isUpperCase(board[y+1][x-2].getPiece().getType()) != Character.isUpperCase(this.getType())) {
                        moves.add(new Point(x-2, y+1));
                    }
                }
            }
        }
        if(x+2 <= 7) {
            if(y-1 >= 0) {
                if(board[y-1][x+2].getPiece() == null) {
                    moves.add(new Point(x+2, y-1));
                } else {
                    if(Character.isUpperCase(board[y-1][x+2].getPiece().getType()) != Character.isUpperCase(this.getType())) {
                        moves.add(new Point(x+2, y-1));
                    }
                }
            }
            if(y+1 <= 7) {
                if(board[y+1][x+2].getPiece() == null) {
                    moves.add(new Point(x+2, y+1));
                } else {
                    if(Character.isUpperCase(board[y+1][x+2].getPiece().getType()) != Character.isUpperCase(this.getType())) {
                        moves.add(new Point(x+2, y+1));
                    }
                }

            }
        }
        if(y-2 >= 0) {
            if(x-1 >= 0) {
                if(board[y-2][x-1].getPiece() == null) {
                    moves.add(new Point(x-1, y-2));
                } else {
                    if(Character.isUpperCase(board[y-2][x-1].getPiece().getType()) != Character.isUpperCase(this.getType())) {
                        moves.add(new Point(x-1, y-2));
                    }
                }
            }
            if(x+1 <= 7) {
                if(board[y-2][x+1].getPiece() == null) {
                    moves.add(new Point(x+1, y-2));
                } else {
                    if(Character.isUpperCase(board[y-2][x+1].getPiece().getType()) != Character.isUpperCase(this.getType())) {
                        moves.add(new Point(x+1, y-2));
                    }
                }
            }
        }
        if(y+2 <= 7) {
            if(x-1 >= 0) {
                if(board[y+2][x-1].getPiece() == null) {
                    moves.add(new Point(x-1, y+2));
                } else {
                    if(Character.isUpperCase(board[y+2][x-1].getPiece().getType()) != Character.isUpperCase(this.getType())) {
                        moves.add(new Point(x-1, y+2));
                    }
                }

            }
            if(x+1 <= 7) {
                if(board[y+2][x+1].getPiece() == null) {
                    moves.add(new Point(x+1, y+2));
                } else {
                    if(Character.isUpperCase(board[y+2][x+1].getPiece().getType()) != Character.isUpperCase(this.getType())) {
                        moves.add(new Point(x+1, y+2));
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
        if(x-2 >= 0) {
            if(y-1 >= 0) {
                defense.add(new Point(x-2, y-1));
            }
            if(y+1 <= 7) {
                defense.add(new Point(x-2, y+1));
            }
        }
        if(x+2 <= 7) {
            if(y-1 >= 0) {
                defense.add(new Point(x+2, y-1));
            }
            if(y+1 <= 7) {
                defense.add(new Point(x+2, y+1));
            }
        }
        if(y-2 >= 0) {
            if(x-1 >= 0) {
                defense.add(new Point(x-1, y-2));
            }
            if(x+1 <= 7) {
                defense.add(new Point(x+1, y-2));
            }
        }
        if(y+2 <= 7) {
            if(x-1 >= 0) {
                defense.add(new Point(x-1, y+2));

            }
            if(x+1 <= 7) {
                defense.add(new Point(x+1, y+2));
            }
        }
        return defense;
    }
}
