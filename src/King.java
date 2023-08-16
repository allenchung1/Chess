import java.awt.Point;
import java.util.ArrayList;

public class King extends Piece {
    private static boolean[][] defended;
    public King (char t, int x, int y, boolean w) {
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

        if(this.isWhite()) {
            defended = Game.getBDefended();
        } else {
            defended = Game.getWDefended();
        }
        if(y-1 >= 0) {
            if(x-1 >= 0) {
                if(board[y-1][x-1].getPiece() == null && defended[y-1][x-1] == false) {
                    moves.add(new Point(x-1, y-1));
                } else if(board[y-1][x-1].getPiece() != null) {
                    if(board[y-1][x-1].getPiece().isWhite() != this.isWhite() && defended[y-1][x-1] == false) {
                        moves.add(new Point(x-1, y-1));
                    }
                }
            }
            if(board[y-1][x].getPiece() == null && defended[y-1][x] == false) {
                moves.add(new Point(x, y-1));
            } else if(board[y-1][x].getPiece() != null) {
                if(board[y-1][x].getPiece().isWhite() != this.isWhite() && defended[y-1][x] == false) {
                    moves.add(new Point(x, y-1));
                }
            }
            if(x+1 <= 7) {
                if(board[y-1][x+1].getPiece() == null && defended[y-1][x+1] == false) {
                    moves.add(new Point(x+1, y-1));
                } else if(board[y-1][x+1].getPiece() != null) {
                    if(board[y-1][x+1].getPiece().isWhite() != this.isWhite() && defended[y-1][x+1] == false) {
                        moves.add(new Point(x+1, y-1));
                    }   
                }
            }
        }
        if(x+1 <= 7) {
            if(board[y][x+1].getPiece() == null && defended[y][x+1] == false) {
                moves.add(new Point(x+1, y));
            } else if(board[y][x+1].getPiece() != null) {
                if(board[y][x+1].getPiece().isWhite() != this.isWhite() && defended[y][x+1] == false) {
                    moves.add(new Point(x+1, y));
                }
            }

            if(y+1 <= 7) {
                if(board[y+1][x+1].getPiece() == null && defended[y+1][x+1] == false) {
                    moves.add(new Point(x+1, y+1));
                } else if(board[y+1][x+1].getPiece() != null) {
                    if(board[y+1][x+1].getPiece().isWhite() != this.isWhite() && defended[y+1][x+1] == false) {
                        moves.add(new Point(x+1, y+1));
                    }
                }
            }
        }
        if(y+1 <= 7) {
            if(board[y+1][x].getPiece() == null && defended[y+1][x] == false) {
                moves.add(new Point(x, y+1));
            } else if(board[y+1][x].getPiece() != null) {
                if(board[y+1][x].getPiece().isWhite() != this.isWhite() && defended[y+1][x] == false) {
                    moves.add(new Point(x, y+1));
                }
            }
            if(x-1 >= 0) {
                if(board[y+1][x-1].getPiece() == null && defended[y+1][x-1] == false) {
                    moves.add(new Point(x-1, y+1));
                } else if(board[y+1][x-1].getPiece() != null) {
                    if(board[y+1][x-1].getPiece().isWhite() != this.isWhite() && defended[y+1][x-1] == false) {
                        moves.add(new Point(x-1, y+1));
                    }
                }
            }
        }
        if(x-1 >= 0) {
            if(board[y][x-1].getPiece() == null && defended[y][x-1] == false) {
                moves.add(new Point(x-1, y));
            } else if(board[y][x-1].getPiece() != null) {
                if(board[y][x-1].getPiece().isWhite() != this.isWhite() && defended[y][x-1] == false) {
                    moves.add(new Point(x-1, y));
                }
                
            }
        }
        return moves;
    }

    public ArrayList<Point> findDefense(Square[][] board) {
        ArrayList<Point> defense = new ArrayList<Point>();
        int x = this.getFile();
        int y = this.getRank();
        if(y-1 >= 0) {
            if(x-1 >= 0) {
                defense.add(new Point(x-1, y-1));
            }
            defense.add(new Point(x, y-1));
            if(x+1 <= 7) {
                defense.add(new Point(x+1, y-1));
            }
        }
        if(x+1 <= 7) {
            defense.add(new Point(x+1, y));
            if(y+1 <= 7) {
                defense.add(new Point(x+1, y+1));
            }
        }
        if(y+1 <= 7) {
            defense.add(new Point(x, y+1));
            if(x-1 >= 0) {
                defense.add(new Point(x-1, y+1));
            }
        }
        if(x-1 >= 0) {
            defense.add(new Point(x-1, y));
        }
        return defense;
    }
}
