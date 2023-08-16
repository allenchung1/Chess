import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
public class Game extends JFrame {
    public static HashMap<Piece, JLabel> pieces;
    public static Square board[][];
    public static Square futureBoard[][];
    public static Square selectedSquare;
    public static int clickCount;
    public static ArrayList<Point> moves;
    public static ArrayList<Point> defended;
    public static boolean Wturn = true;

    public static boolean[][] WDefended;
    public static boolean[][] BDefended;

    public static boolean WKingCheck = false;
    public static boolean BKingCheck = false;

    public static HashMap<Queen, JLabel> wQueens;
    public static HashMap<Queen, JLabel> bQueens;

    public static Point WDoublePushedPawn;
    public static Point BDoublePushedPawn;


    public static JFrame frame;

    Game() {
        frame = this;
        pieces = new HashMap<Piece, JLabel>(32);
        wQueens = new HashMap<Queen, JLabel>(8);
        bQueens = new HashMap<Queen, JLabel>(8);
        
        this.setTitle("Chess Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(640, 668);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        board = new Square[8][16];
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 16; j++) {
                if(j < 8) {
                    JPanel panel = new JPanel();
                    if((i+j) % 2 == 0) {
                        panel.setBackground(Color.LIGHT_GRAY);
                    } else {
                        panel.setBackground(Color.darkGray);
                    }
                    panel.setBounds(j*80, i*80, 80, 80);
                    panel.setLayout(new BorderLayout());
                    this.add(panel);
                    Square square = new Square(panel, j, i);
                    board[i][j] = square;
                } else if(i == 0){
                    JPanel panel = new JPanel();
                    panel.setBackground(Color.GRAY);
                    panel.setBounds(j*80, i*80, 80, 80);
                    panel.setLayout(new BorderLayout());
                    this.add(panel);
                    Square square = new Square(panel, j, i);
                    board[i][j] = square;
                    ImageIcon image = new ImageIcon("images/wq.png");
                    JLabel label = new JLabel();
                    label.setIcon(image);
                    label.setVerticalAlignment(JLabel.CENTER);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    board[i][j].getPanel().add(label);
                    Queen queen = new Queen('Q', j, i, true);
                    board[i][j].setPiece(queen);
                    wQueens.put(queen, label);
                } else if(i == 7) {
                    JPanel panel = new JPanel();
                    panel.setBackground(Color.GRAY);
                    panel.setBounds(j*80, i*80, 80, 80);
                    panel.setLayout(new BorderLayout());
                    this.add(panel);
                    Square square = new Square(panel, j, i);
                    board[i][j] = square;
                    ImageIcon image = new ImageIcon("images/bq.png");
                    JLabel label = new JLabel();
                    label.setIcon(image);
                    label.setVerticalAlignment(JLabel.CENTER);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    board[i][j].getPanel().add(label);
                    Queen queen = new Queen('q', j, i, false);
                    board[i][j].setPiece(queen);
                    bQueens.put(queen, label);
                }
            }
        }
        JPanel savedPanel = new JPanel();
        savedPanel.setBackground(Color.CYAN);
        savedPanel.setBounds(640, 80, 80, 80);
        savedPanel.setLayout(new BorderLayout());
        this.add(savedPanel);
        Square savedSquare = new Square(savedPanel, 8, 1);
        board[1][8] = savedSquare;
        JLabel savedLabel = new JLabel();
        savedLabel.setVerticalAlignment(JLabel.CENTER);
        savedLabel.setHorizontalAlignment(JLabel.CENTER);
        board[1][8].getPanel().add(savedLabel);



        ImageIcon wkImage = new ImageIcon("images/wk.png");
        JLabel wkLabel = new JLabel();
        wkLabel.setIcon(wkImage);
        wkLabel.setVerticalAlignment(JLabel.CENTER);
        wkLabel.setHorizontalAlignment(JLabel.CENTER);
        board[7][4].getPanel().add(wkLabel);
        King whiteKing = new King('K', 4, 7, true);
        board[7][4].setPiece(whiteKing);
        pieces.put(whiteKing, wkLabel);

        ImageIcon wrImage = new ImageIcon("images/wr.png");
        JLabel wrLabel1 = new JLabel();
        wrLabel1.setIcon(wrImage);
        wrLabel1.setVerticalAlignment(JLabel.CENTER);
        wrLabel1.setHorizontalAlignment(JLabel.CENTER);
        board[7][0].getPanel().add(wrLabel1);
        Rook whiteRook1 = new Rook('R', 0, 7, true, false);
        board[7][0].setPiece(whiteRook1);
        pieces.put(whiteRook1, wrLabel1);

        JLabel wrLabel2 = new JLabel();
        wrLabel2.setIcon(wrImage);
        wrLabel2.setVerticalAlignment(JLabel.CENTER);
        wrLabel2.setHorizontalAlignment(JLabel.CENTER);
        board[7][7].getPanel().add(wrLabel2);
        Rook whiteRook2 = new Rook('R', 7, 7, true, true);
        board[7][7].setPiece(whiteRook2);
        pieces.put(whiteRook2, wrLabel2);

        ImageIcon wnImage = new ImageIcon("images/wn.png");
        JLabel wnLabel1 = new JLabel();
        wnLabel1.setIcon(wnImage);
        wnLabel1.setVerticalAlignment(JLabel.CENTER);
        wnLabel1.setHorizontalAlignment(JLabel.CENTER);
        board[7][1].getPanel().add(wnLabel1);
        Knight whiteKnight1 = new Knight('N', 1, 7, true);
        board[7][1].setPiece(whiteKnight1);
        pieces.put(whiteKnight1, wnLabel1);

        JLabel wnLabel2 = new JLabel();
        wnLabel2.setIcon(wnImage);
        wnLabel2.setVerticalAlignment(JLabel.CENTER);
        wnLabel2.setHorizontalAlignment(JLabel.CENTER);
        board[7][6].getPanel().add(wnLabel2);
        Knight whiteKnight2 = new Knight('N', 6, 7, true);
        board[7][6].setPiece(whiteKnight2);
        pieces.put(whiteKnight2, wnLabel2);

        ImageIcon wbImage = new ImageIcon("images/wb.png");
        JLabel wbLabel1 = new JLabel();
        wbLabel1.setIcon(wbImage);
        wbLabel1.setVerticalAlignment(JLabel.CENTER);
        wbLabel1.setHorizontalAlignment(JLabel.CENTER);
        board[7][2].getPanel().add(wbLabel1);
        Bishop whiteBishop1 = new Bishop('B', 2, 7, true);
        board[7][2].setPiece(whiteBishop1);
        pieces.put(whiteBishop1, wbLabel1);

        JLabel wbLabel2 = new JLabel();
        wbLabel2.setIcon(wbImage);
        wbLabel2.setVerticalAlignment(JLabel.CENTER);
        wbLabel2.setHorizontalAlignment(JLabel.CENTER);
        board[7][5].getPanel().add(wbLabel2);
        Bishop whiteBishop2 = new Bishop('B', 5, 7, true);
        board[7][5].setPiece(whiteBishop2);
        pieces.put(whiteBishop2, wbLabel2);

        ImageIcon wqImage = new ImageIcon("images/wq.png");
        JLabel wqLabel = new JLabel();
        wqLabel.setIcon(wqImage);
        wqLabel.setVerticalAlignment(JLabel.CENTER);
        wqLabel.setHorizontalAlignment(JLabel.CENTER);
        board[7][3].getPanel().add(wqLabel);
        Queen whiteQueen = new Queen('Q', 3, 7, true);
        board[7][3].setPiece(whiteQueen);
        pieces.put(whiteQueen, wqLabel);

        ImageIcon pImage = new ImageIcon("images/wp.png");
        JLabel wpLabel1 = new JLabel();
        wpLabel1.setIcon(pImage);
        wpLabel1.setVerticalAlignment(JLabel.CENTER);
        wpLabel1.setHorizontalAlignment(JLabel.CENTER);
        board[6][0].getPanel().add(wpLabel1);
        Pawn whitePawn1 = new Pawn('P', 0, 6, true);
        board[6][0].setPiece(whitePawn1);
        pieces.put(whitePawn1, wpLabel1);

        JLabel wpLabel2 = new JLabel();
        wpLabel2.setIcon(pImage);
        wpLabel2.setVerticalAlignment(JLabel.CENTER);
        wpLabel2.setHorizontalAlignment(JLabel.CENTER);
        board[6][1].getPanel().add(wpLabel2);
        Pawn whitePawn2 = new Pawn('P', 1, 6, true);
        board[6][1].setPiece(whitePawn2);
        pieces.put(whitePawn2, wpLabel2);

        JLabel wpLabel3 = new JLabel();
        wpLabel3.setIcon(pImage);
        wpLabel3.setVerticalAlignment(JLabel.CENTER);
        wpLabel3.setHorizontalAlignment(JLabel.CENTER);
        board[6][2].getPanel().add(wpLabel3);
        Pawn whitePawn3 = new Pawn('P', 2, 6, true);
        board[6][2].setPiece(whitePawn3);
        pieces.put(whitePawn3, wpLabel3);

        JLabel wpLabel4 = new JLabel();
        wpLabel4.setIcon(pImage);
        wpLabel4.setVerticalAlignment(JLabel.CENTER);
        wpLabel4.setHorizontalAlignment(JLabel.CENTER);
        board[6][3].getPanel().add(wpLabel4);
        Pawn whitePawn4 = new Pawn('P', 3, 6, true);
        board[6][3].setPiece(whitePawn4);
        pieces.put(whitePawn4, wpLabel4);

        JLabel wpLabel5 = new JLabel();
        wpLabel5.setIcon(pImage);
        wpLabel5.setVerticalAlignment(JLabel.CENTER);
        wpLabel5.setHorizontalAlignment(JLabel.CENTER);
        board[6][4].getPanel().add(wpLabel5);
        Pawn whitePawn5 = new Pawn('P', 4, 6, true);
        board[6][4].setPiece(whitePawn5);
        pieces.put(whitePawn5, wpLabel5);

        JLabel wpLabel6 = new JLabel();
        wpLabel6.setIcon(pImage);
        wpLabel6.setVerticalAlignment(JLabel.CENTER);
        wpLabel6.setHorizontalAlignment(JLabel.CENTER);
        board[6][5].getPanel().add(wpLabel6);
        Pawn whitePawn6 = new Pawn('P', 5, 6, true);
        board[6][5].setPiece(whitePawn6);
        pieces.put(whitePawn6, wpLabel6);

        JLabel wpLabel7 = new JLabel();
        wpLabel7.setIcon(pImage);
        wpLabel7.setVerticalAlignment(JLabel.CENTER);
        wpLabel7.setHorizontalAlignment(JLabel.CENTER);
        board[6][6].getPanel().add(wpLabel7);
        Pawn whitePawn7 = new Pawn('P', 6, 6, true);
        board[6][6].setPiece(whitePawn7);
        pieces.put(whitePawn7, wpLabel7);

        JLabel wpLabel8 = new JLabel();
        wpLabel8.setIcon(pImage);
        wpLabel8.setVerticalAlignment(JLabel.CENTER);
        wpLabel8.setHorizontalAlignment(JLabel.CENTER);
        board[6][7].getPanel().add(wpLabel8);
        Pawn whitePawn8 = new Pawn('P', 7, 6, true);
        board[6][7].setPiece(whitePawn8);
        pieces.put(whitePawn8, wpLabel8);

        ImageIcon bkImage = new ImageIcon("images/bk.png");
        JLabel bkLabel = new JLabel();
        bkLabel.setIcon(bkImage);
        bkLabel.setVerticalAlignment(JLabel.CENTER);
        bkLabel.setHorizontalAlignment(JLabel.CENTER);
        board[0][4].getPanel().add(bkLabel);
        King blackKing = new King('k', 4, 0, false);
        board[0][4].setPiece(blackKing);
        pieces.put(blackKing, bkLabel);

        ImageIcon brImage = new ImageIcon("images/br.png");
        JLabel brLabel1 = new JLabel();
        brLabel1.setIcon(brImage);
        brLabel1.setVerticalAlignment(JLabel.CENTER);
        brLabel1.setHorizontalAlignment(JLabel.CENTER);
        board[0][0].getPanel().add(brLabel1);
        Rook blackRook1 = new Rook('r', 0, 0, false, false);
        board[0][0].setPiece(blackRook1);
        pieces.put(blackRook1, brLabel1);

        JLabel brLabel2 = new JLabel();
        brLabel2.setIcon(brImage);
        brLabel2.setVerticalAlignment(JLabel.CENTER);
        brLabel2.setHorizontalAlignment(JLabel.CENTER);
        board[0][7].getPanel().add(brLabel2);
        Rook blackRook2 = new Rook('r', 7, 0, false, true);
        board[0][7].setPiece(blackRook2);
        pieces.put(blackRook2, brLabel2);

        ImageIcon bnImage = new ImageIcon("images/bn.png");
        JLabel bnLabel1 = new JLabel();
        bnLabel1.setIcon(bnImage);
        bnLabel1.setVerticalAlignment(JLabel.CENTER);
        bnLabel1.setHorizontalAlignment(JLabel.CENTER);
        board[0][1].getPanel().add(bnLabel1);
        Knight blackKnight1 = new Knight('n', 1, 0, false);
        board[0][1].setPiece(blackKnight1);
        pieces.put(blackKnight1, bnLabel1);

        JLabel bnLabel2 = new JLabel();
        bnLabel2.setIcon(bnImage);
        bnLabel2.setVerticalAlignment(JLabel.CENTER);
        bnLabel2.setHorizontalAlignment(JLabel.CENTER);
        board[0][6].getPanel().add(bnLabel2);
        Knight blackKnight2 = new Knight('n', 6, 0, false);
        board[0][6].setPiece(blackKnight2);
        pieces.put(blackKnight2, bnLabel2);

        ImageIcon bbImage = new ImageIcon("images/bb.png");
        JLabel bbLabel1 = new JLabel();
        bbLabel1.setIcon(bbImage);
        bbLabel1.setVerticalAlignment(JLabel.CENTER);
        bbLabel1.setHorizontalAlignment(JLabel.CENTER);
        board[0][2].getPanel().add(bbLabel1);
        Bishop blackBishop1 = new Bishop('b', 2, 0, false);
        board[0][2].setPiece(blackBishop1);
        pieces.put(blackBishop1, bbLabel1);

        JLabel bbLabel2 = new JLabel();
        bbLabel2.setIcon(bbImage);
        bbLabel2.setVerticalAlignment(JLabel.CENTER);
        bbLabel2.setHorizontalAlignment(JLabel.CENTER);
        board[0][5].getPanel().add(bbLabel2);
        Bishop blackBishop2 = new Bishop('b', 5, 0, false);
        board[0][5].setPiece(blackBishop2);
        pieces.put(blackBishop2, bbLabel2);

        ImageIcon bqImage = new ImageIcon("images/bq.png");
        JLabel bqLabel = new JLabel();
        bqLabel.setIcon(bqImage);
        bqLabel.setVerticalAlignment(JLabel.CENTER);
        bqLabel.setHorizontalAlignment(JLabel.CENTER);
        board[0][3].getPanel().add(bqLabel);
        Queen blackQueen = new Queen('q', 3, 0, false);
        board[0][3].setPiece(blackQueen);
        pieces.put(blackQueen, bqLabel);

        ImageIcon bpImage = new ImageIcon("images/bp.png");
        JLabel bpLabel1 = new JLabel();
        bpLabel1.setIcon(bpImage);
        bpLabel1.setVerticalAlignment(JLabel.CENTER);
        bpLabel1.setHorizontalAlignment(JLabel.CENTER);
        board[1][0].getPanel().add(bpLabel1);
        Pawn blackPawn1 = new Pawn('p', 0, 1, false);
        board[1][0].setPiece(blackPawn1);
        pieces.put(blackPawn1, bpLabel1);

        JLabel bpLabel2 = new JLabel();
        bpLabel2.setIcon(bpImage);
        bpLabel2.setVerticalAlignment(JLabel.CENTER);
        bpLabel2.setHorizontalAlignment(JLabel.CENTER);
        board[1][1].getPanel().add(bpLabel2);
        Pawn blackPawn2 = new Pawn('p', 1, 1, false);
        board[1][1].setPiece(blackPawn2);
        pieces.put(blackPawn2, bpLabel2);

        JLabel bpLabel3 = new JLabel();
        bpLabel3.setIcon(bpImage);
        bpLabel3.setVerticalAlignment(JLabel.CENTER);
        bpLabel3.setHorizontalAlignment(JLabel.CENTER);
        board[1][2].getPanel().add(bpLabel3);
        Pawn blackPawn3 = new Pawn('p', 2, 1, false);
        board[1][2].setPiece(blackPawn3);
        pieces.put(blackPawn3, bpLabel3);

        JLabel bpLabel4 = new JLabel();
        bpLabel4.setIcon(bpImage);
        bpLabel4.setVerticalAlignment(JLabel.CENTER);
        bpLabel4.setHorizontalAlignment(JLabel.CENTER);
        board[1][3].getPanel().add(bpLabel4);
        Pawn blackPawn4 = new Pawn('p', 3, 1, false);
        board[1][3].setPiece(blackPawn4);
        pieces.put(blackPawn4, bpLabel4);

        JLabel bpLabel5 = new JLabel();
        bpLabel5.setIcon(bpImage);
        bpLabel5.setVerticalAlignment(JLabel.CENTER);
        bpLabel5.setHorizontalAlignment(JLabel.CENTER);
        board[1][4].getPanel().add(bpLabel5);
        Pawn blackPawn5 = new Pawn('p', 4, 1, false);
        board[1][4].setPiece(blackPawn5);
        pieces.put(blackPawn5, bpLabel5);

        JLabel bpLabel6 = new JLabel();
        bpLabel6.setIcon(bpImage);
        bpLabel6.setVerticalAlignment(JLabel.CENTER);
        bpLabel6.setHorizontalAlignment(JLabel.CENTER);
        board[1][5].getPanel().add(bpLabel6);
        Pawn blackPawn6 = new Pawn('p', 5, 1, false);
        board[1][5].setPiece(blackPawn6);
        pieces.put(blackPawn6, bpLabel6);

        JLabel bpLabel7 = new JLabel();
        bpLabel7.setIcon(bpImage);
        bpLabel7.setVerticalAlignment(JLabel.CENTER);
        bpLabel7.setHorizontalAlignment(JLabel.CENTER);
        board[1][6].getPanel().add(bpLabel7);
        Pawn blackPawn7 = new Pawn('p', 6, 1, false);
        board[1][6].setPiece(blackPawn7);
        pieces.put(blackPawn7, bpLabel7);

        JLabel bpLabel8 = new JLabel();
        bpLabel8.setIcon(bpImage);
        bpLabel8.setVerticalAlignment(JLabel.CENTER);
        bpLabel8.setHorizontalAlignment(JLabel.CENTER);
        board[1][7].getPanel().add(bpLabel8);
        Pawn blackPawn8 = new Pawn('p', 7, 1, false);
        board[1][7].setPiece(blackPawn8);
        pieces.put(blackPawn8, bpLabel8);
        
        WDefended = new boolean[8][8];
        BDefended = new boolean[8][8];

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX()/80;
                int y = (e.getY()-30)/80;
                Square currentSquare = board[y][x];
                if(clickCount == 0) {
                    if(currentSquare.getPiece() == null) { // SELECTED EMPTY SQUARE
                        return;
                    } else if(currentSquare.getPiece() != null){ // SELECT A MOVING PIECE
                        if(currentSquare.getPiece().isWhite() != Wturn) {
                            return;
                        }
                        selectedSquare = currentSquare;
                        clickCount++;
                        selectedSquare.getPanel().setBackground(Color.GRAY);
                        moves = selectedSquare.getPiece().findMoves(board); 
                    }
                } else if(clickCount == 1) {
                    if(currentSquare.getPiece() != null) {// MOVING PIECE WAS SELECTED
                        if(Character.isUpperCase(currentSquare.getPiece().getType()) == Character.isUpperCase(selectedSquare.getPiece().getType())) { // RESELECT MOVING PIECE
                            if(currentSquare.getPiece().isWhite() != Wturn) {
                                clickCount = 0;
                                return;
                            }

                            if((selectedSquare.getX()+selectedSquare.getY()) % 2 == 0) {
                                selectedSquare.getPanel().setBackground(Color.LIGHT_GRAY);
                            } else {
                                selectedSquare.getPanel().setBackground(Color.darkGray);
                            }

                            selectedSquare = currentSquare;
                            selectedSquare.getPanel().setBackground(Color.GRAY);
                            moves = selectedSquare.getPiece().findMoves(board);
                        } else if(Character.isUpperCase(currentSquare.getPiece().getType()) != Character.isUpperCase(selectedSquare.getPiece().getType())){ //TAKE PIECE
                            if((selectedSquare.getX()+selectedSquare.getY()) % 2 == 0) {
                                selectedSquare.getPanel().setBackground(Color.LIGHT_GRAY);
                            } else {
                                selectedSquare.getPanel().setBackground(Color.darkGray);
                            }

                            for(Point p : moves) {
                                if(p.getX() == x && p.getY() == y) {
                                    if(Wturn) {
                                        if(WKingCheck) {
                                            clickCount = 0;
                                            board[1][8].setPiece(board[y][x].getPiece());
                                            board[y][x].setPiece(selectedSquare.getPiece());
                                            board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                            boolean[][] futureDefended = findDefended(!Wturn, board);
                                            boolean futureKingCheck = kingInCheck(!Wturn, board, futureDefended);
                                            board[selectedSquare.getY()][selectedSquare.getX()].setPiece(board[y][x].getPiece());
                                            board[y][x].setPiece(board[1][8].getPiece());
                                            if(futureKingCheck) {
                                                return;
                                            } else {
                                                if(selectedSquare.getPiece().getType() == 'P' && y == 0) {// TAKE PROMO (WHITE)
                                                    pieces.remove(board[y][x].getPiece());
                                                    pieces.remove(selectedSquare.getPiece());
                                                    board[y][x+8].getPiece().move(x, y);
                                                    board[y][x].getPanel().remove(0);
                                                    board[y][x].getPanel().add(wQueens.get(board[y][x+8].getPiece()));
                                                    board[y][x].setPiece(board[y][x+8].getPiece());
                                                    pieces.put(board[y][x].getPiece(), wQueens.get(board[y][x].getPiece()));
                                                    board[selectedSquare.getY()][selectedSquare.getX()].getPanel().remove(0);
                                                    board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                                    frame.repaint();
                                                } else {
                                                    pieces.remove(board[y][x].getPiece());
                                                    selectedSquare.getPiece().move(x, y);
                                                    board[y][x].getPanel().remove(0);
                                                    board[y][x].getPanel().add(pieces.get(selectedSquare.getPiece()));
                                                    board[y][x].setPiece(selectedSquare.getPiece());
                                                    board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                                    frame.repaint();
                                                }
                                            }
                                        } else {
                                            clickCount = 0;
                                            board[1][8].setPiece(board[y][x].getPiece());
                                            board[y][x].setPiece(selectedSquare.getPiece());
                                            board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                            boolean[][] futureDefended = findDefended(!Wturn, board);
                                            boolean futureKingCheck = kingInCheck(!Wturn, board, futureDefended);
                                            board[selectedSquare.getY()][selectedSquare.getX()].setPiece(board[y][x].getPiece());
                                            board[y][x].setPiece(board[1][8].getPiece());
                                            if(futureKingCheck) {
                                                return;
                                            } else {
                                                if(selectedSquare.getPiece().getType() == 'P' && y == 0) {// TAKE PROMO (WHITE)
                                                    pieces.remove(board[y][x].getPiece());
                                                    pieces.remove(selectedSquare.getPiece());
                                                    board[y][x+8].getPiece().move(x, y);
                                                    board[y][x].getPanel().remove(0);
                                                    board[y][x].getPanel().add(wQueens.get(board[y][x+8].getPiece()));
                                                    board[y][x].setPiece(board[y][x+8].getPiece());
                                                    pieces.put(board[y][x].getPiece(), wQueens.get(board[y][x].getPiece()));
                                                    board[selectedSquare.getY()][selectedSquare.getX()].getPanel().remove(0);
                                                    board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                                    frame.repaint();
                                                } else {
                                                    pieces.remove(board[y][x].getPiece());
                                                    selectedSquare.getPiece().move(x, y);
                                                    board[y][x].getPanel().remove(0);
                                                    board[y][x].getPanel().add(pieces.get(selectedSquare.getPiece()));
                                                    board[y][x].setPiece(selectedSquare.getPiece());
                                                    board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                                    frame.repaint();
                                                }
                                            }
                                        }
                                    } else {
                                        if(BKingCheck) {
                                            clickCount = 0;
                                            board[1][8].setPiece(board[y][x].getPiece());
                                            board[y][x].setPiece(selectedSquare.getPiece());
                                            board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                            boolean[][] futureDefended = findDefended(!Wturn, board);
                                            boolean futureKingCheck = kingInCheck(!Wturn, board, futureDefended);
                                            board[selectedSquare.getY()][selectedSquare.getX()].setPiece(board[y][x].getPiece());
                                            board[y][x].setPiece(board[1][8].getPiece());
                                            if(futureKingCheck) {
                                                return;
                                            } else {
                                                if(selectedSquare.getPiece().getType() == 'p' && y == 7) {// TAKE PROMO (BLACK)
                                                    clickCount = 0;
                                                    pieces.remove(board[y][x].getPiece());
                                                    pieces.remove(selectedSquare.getPiece());
                                                    board[y][x+8].getPiece().move(x, y);
                                                    board[y][x].getPanel().remove(0);
                                                    board[y][x].getPanel().add(bQueens.get(board[y][x+8].getPiece()));
                                                    board[y][x].setPiece(board[y][x+8].getPiece());
                                                    pieces.put(board[y][x].getPiece(), bQueens.get(board[y][x].getPiece()));
                                                    board[selectedSquare.getY()][selectedSquare.getX()].getPanel().remove(0);
                                                    board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                                    frame.repaint();
                                                } else {
                                                    pieces.remove(board[y][x].getPiece());
                                                    selectedSquare.getPiece().move(x, y);
                                                    board[y][x].getPanel().remove(0);
                                                    board[y][x].getPanel().add(pieces.get(selectedSquare.getPiece()));
                                                    board[y][x].setPiece(selectedSquare.getPiece());
                                                    board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                                    frame.repaint();
                                                }
                                            }
                                        } else {
                                            clickCount = 0;
                                            board[1][8].setPiece(board[y][x].getPiece());
                                            board[y][x].setPiece(selectedSquare.getPiece());
                                            board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                            boolean[][] futureDefended = findDefended(!Wturn, board);
                                            boolean futureKingCheck = kingInCheck(!Wturn, board, futureDefended);
                                            board[selectedSquare.getY()][selectedSquare.getX()].setPiece(board[y][x].getPiece());
                                            board[y][x].setPiece(board[1][8].getPiece());
                                            if(futureKingCheck) {
                                                return;
                                            } else {
                                                if(selectedSquare.getPiece().getType() == 'p' && y == 7) {// TAKE PROMO (BLACK)
                                                    clickCount = 0;
                                                    pieces.remove(board[y][x].getPiece());
                                                    pieces.remove(selectedSquare.getPiece());
                                                    board[y][x+8].getPiece().move(x, y);
                                                    board[y][x].getPanel().remove(0);
                                                    board[y][x].getPanel().add(bQueens.get(board[y][x+8].getPiece()));
                                                    board[y][x].setPiece(board[y][x+8].getPiece());
                                                    pieces.put(board[y][x].getPiece(), bQueens.get(board[y][x].getPiece()));
                                                    board[selectedSquare.getY()][selectedSquare.getX()].getPanel().remove(0);
                                                    board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                                    frame.repaint();
                                                } else {
                                                    pieces.remove(board[y][x].getPiece());
                                                    selectedSquare.getPiece().move(x, y);
                                                    board[y][x].getPanel().remove(0);
                                                    board[y][x].getPanel().add(pieces.get(selectedSquare.getPiece()));
                                                    board[y][x].setPiece(selectedSquare.getPiece());
                                                    board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                                    frame.repaint();
                                                }
                                            }
                                        }
                                    }
                                    if(Wturn) { //find defended squares
                                        WDefended = findDefended(Wturn, board);
                                    } else {
                                        BDefended = findDefended(Wturn, board);
                                    }
                                    if(Wturn) { //check if king in check
                                        BKingCheck = kingInCheck(Wturn, board, WDefended);
                                    } else {
                                        WKingCheck = kingInCheck(Wturn, board, BDefended);
                                    }
                                    Wturn = !Wturn;
                                }
                            }
                            clickCount = 0;
                        }
                    } else { //MOVE TO EMPTY SQUARE
                        if((selectedSquare.getX()+selectedSquare.getY()) % 2 == 0) {
                            selectedSquare.getPanel().setBackground(Color.LIGHT_GRAY);
                        } else {
                            selectedSquare.getPanel().setBackground(Color.darkGray);
                        }
                        //add castling moves
                        if(Wturn &&!WKingCheck) {
                            if(board[7][7].getPiece() != null) { //kingside
                                if(selectedSquare.getPiece().getType() == 'K' && board[7][7].getPiece().getType() == 'R' && !selectedSquare.getPiece().hasMoved() && !board[7][7].getPiece().hasMoved() && board[7][5].getPiece() == null && board[7][6].getPiece() == null && !BDefended[7][5] && !BDefended[7][6]) {
                                    moves.add(new Point(6, 7));
                                }
                            }
                            if(board[7][0].getPiece() != null) { //queenside
                                if(selectedSquare.getPiece().getType() == 'K' && board[7][0].getPiece().getType() == 'R' && !selectedSquare.getPiece().hasMoved() && !board[7][0].getPiece().hasMoved() && board[7][1].getPiece() == null && board[7][2].getPiece() == null && board[7][3].getPiece() == null && !BDefended[7][1] && !BDefended[7][2] && !BDefended[7][3]) {
                                    moves.add(new Point(2, 7));
                                }
                            }
                        } else if(!Wturn && !BKingCheck) {
                            if(board[0][7].getPiece() != null) { //kingside
                                if(selectedSquare.getPiece().getType() == 'k' && board[0][7].getPiece().getType() == 'r' && !selectedSquare.getPiece().hasMoved() && !board[0][7].getPiece().hasMoved() && board[0][5].getPiece() == null && board[0][6].getPiece() == null && !WDefended[0][5] && !WDefended[0][6]) {
                                    moves.add(new Point(6, 0));
                                }
                            }
                            if(board[0][0].getPiece() != null) { //queenside
                                if(selectedSquare.getPiece().getType() == 'k' && board[0][0].getPiece().getType() == 'r' && !selectedSquare.getPiece().hasMoved() && !board[0][0].getPiece().hasMoved() && board[0][1].getPiece() == null && board[0][2].getPiece() == null && board[0][3].getPiece() == null && !WDefended[0][1] && !WDefended[0][2] && !WDefended[0][3]) {
                                    moves.add(new Point(2, 0));
                                }
                            }
                        }
                        
                        //add en passant moves
                        if(Wturn && selectedSquare.getPiece().getType() == 'P' && BDoublePushedPawn != null ) {
                            if(board[(int)BDoublePushedPawn.getY()-1][(int)BDoublePushedPawn.getX()].getPiece() == null) {
                                if(selectedSquare.getX() == BDoublePushedPawn.getX()-1 && selectedSquare.getY() == BDoublePushedPawn.getY() ) {
                                    moves.add(new Point(selectedSquare.getX()+1, selectedSquare.getY()-1));
                                } else if(selectedSquare.getX() == BDoublePushedPawn.getX()+1 && selectedSquare.getY() == BDoublePushedPawn.getY()) {
                                    moves.add(new Point(selectedSquare.getX()-1, selectedSquare.getY()-1));
                                }
                            }
                        } else if(!Wturn && selectedSquare.getPiece().getType() == 'p' && WDoublePushedPawn != null) {
                            if(board[(int)WDoublePushedPawn.getY()+1][(int)WDoublePushedPawn.getX()].getPiece() == null) {
                                if(selectedSquare.getX() == WDoublePushedPawn.getX()+1 && selectedSquare.getY() == WDoublePushedPawn.getY()) {
                                    moves.add(new Point(selectedSquare.getX()-1, selectedSquare.getY()+1));
                                } else if(selectedSquare.getX() == WDoublePushedPawn.getX()-1 && selectedSquare.getY() == WDoublePushedPawn.getY()) {
                                    moves.add(new Point(selectedSquare.getX()+1, selectedSquare.getY()+1));
                                }
                            }
                        }

                        for(Point p : moves) {
                            if(p.getX() == x && p.getY() == y) {
                                if(Wturn) { //whites turn
                                    if(WKingCheck) {
                                        board[y][x].setPiece(selectedSquare.getPiece());
                                        board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                        boolean[][] futureDefended = findDefended(!Wturn, board);
                                        boolean futureKingCheck = kingInCheck(!Wturn, board, futureDefended);
                                        board[selectedSquare.getY()][selectedSquare.getX()].setPiece(board[y][x].getPiece());
                                        board[y][x].setPiece(null);
                                        clickCount = 0;
                                        if(futureKingCheck) {           
                                            return;
                                        } else {
                                            if(selectedSquare.getPiece().getType() == 'P' && y == 0) { //PROMOTION
                                                board[y][x+8].getPiece().move(x, y);
                                                board[y][x].getPanel().add(wQueens.get(board[y][x+8].getPiece()));
                                                board[y][x].setPiece(board[y][x+8].getPiece());
                                                pieces.remove(selectedSquare.getPiece());
                                                pieces.put(board[y][x].getPiece(), wQueens.get(board[y][x].getPiece()));
                                                board[selectedSquare.getY()][selectedSquare.getX()].getPanel().remove(0);
                                                board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                                frame.repaint();
                                            } else {
                                                selectedSquare.getPiece().move(x, y);
                                                board[y][x].setPiece(selectedSquare.getPiece());
                                                board[y][x].getPanel().add(pieces.get(selectedSquare.getPiece()));
                                                board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null); 
                                            }
                                        }
                                    } else { // white king not in check already
                                        if(selectedSquare.getPiece().getType() == 'K' && x == 6 && y == 7) { //king side castles
                                            selectedSquare.getPiece().move(x, y);
                                            board[7][7].getPiece().move(5, 7);
                                            clickCount = 0;
                                            board[y][x].setPiece(selectedSquare.getPiece());
                                            board[y][x].getPanel().add(pieces.get(selectedSquare.getPiece()));
                                            board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                            board[7][5].setPiece(board[7][7].getPiece());
                                            board[7][5].getPanel().add(pieces.get(board[7][7].getPiece()));
                                            board[7][7].setPiece(null);
                                        } else if(selectedSquare.getPiece().getType() == 'K' && x == 2 && y == 7) { //queen side castles
                                            selectedSquare.getPiece().move(x, y);
                                            board[7][0].getPiece().move(3, 7);
                                            clickCount = 0;
                                            board[y][x].setPiece(selectedSquare.getPiece());
                                            board[y][x].getPanel().add(pieces.get(selectedSquare.getPiece()));
                                            board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                            board[7][3].setPiece(board[7][0].getPiece());
                                            board[7][3].getPanel().add(pieces.get(board[7][0].getPiece()));
                                            board[7][0].setPiece(null);
                                        } else {
                                            board[y][x].setPiece(selectedSquare.getPiece());
                                            board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                            boolean[][] futureDefended = findDefended(!Wturn, board);
                                            boolean futureKingCheck = kingInCheck(!Wturn, board, futureDefended);
                                            board[selectedSquare.getY()][selectedSquare.getX()].setPiece(board[y][x].getPiece());
                                            board[y][x].setPiece(null);
                                            clickCount = 0;
                                            if(futureKingCheck) {
                                                return;
                                            } else {
                                                if(selectedSquare.getPiece().getType() == 'P' && y == 0) { //PROMOTION
                                                    board[y][x+8].getPiece().move(x, y);
                                                    board[y][x].getPanel().add(wQueens.get(board[y][x+8].getPiece()));
                                                    board[y][x].setPiece(board[y][x+8].getPiece());
                                                    pieces.remove(selectedSquare.getPiece());
                                                    pieces.put(board[y][x].getPiece(), wQueens.get(board[y][x].getPiece()));
                                                    board[selectedSquare.getY()][selectedSquare.getX()].getPanel().remove(0);
                                                    board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                                    frame.repaint();
                                                } else if(selectedSquare.getPiece().getType() == 'P' && selectedSquare.getY() == 6 && y == 4) { //WHITE DOUBLE PUSH
                                                    selectedSquare.getPiece().setMoved(true);
                                                    selectedSquare.getPiece().move(x, y);
                                                    board[y][x].setPiece(selectedSquare.getPiece());
                                                    board[y][x].getPanel().add(pieces.get(selectedSquare.getPiece()));
                                                    board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                                    WDoublePushedPawn = new Point(x, y);
                                                } else if(selectedSquare.getPiece().getType() == 'P' && BDoublePushedPawn != null && (x == selectedSquare.getX()+1 || x == selectedSquare.getX()-1)) { //WHITE EN PESSANT
                                                    pieces.remove(board[y+1][x].getPiece());
                                                    selectedSquare.getPiece().move(x, y);
                                                    board[y+1][x].getPanel().remove(0);
                                                    board[y+1][x].setPiece(null);
                                                    board[y][x].getPanel().add(pieces.get(selectedSquare.getPiece()));
                                                    board[y][x].setPiece(selectedSquare.getPiece());
                                                    board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                                    frame.repaint();
                                                    BDoublePushedPawn = null;
                                                } else { //WHITE REGULAR MOVE
                                                    selectedSquare.getPiece().move(x, y);
                                                    board[y][x].setPiece(selectedSquare.getPiece());
                                                    board[y][x].getPanel().add(pieces.get(selectedSquare.getPiece()));
                                                    board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                                    BDoublePushedPawn = null;
                                                }
                                            }
                                        }
                                    }
                                } else { //blacks turn
                                    if(selectedSquare.getPiece().getType() == 'k' && x == 6 && y == 0) { //king side castles
                                        selectedSquare.getPiece().move(x, y);
                                        board[0][7].getPiece().move(5, 0);
                                        clickCount = 0;
                                        board[y][x].setPiece(selectedSquare.getPiece());
                                        board[y][x].getPanel().add(pieces.get(selectedSquare.getPiece()));
                                        board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                        board[0][5].setPiece(board[0][7].getPiece());
                                        board[0][5].getPanel().add(pieces.get(board[0][7].getPiece()));
                                        board[0][7].setPiece(null);
                                    } else if(selectedSquare.getPiece().getType() == 'k' && x == 2 && y == 0) { //queen side castles
                                        selectedSquare.getPiece().move(x, y);
                                        board[0][0].getPiece().move(3, 0);
                                        clickCount = 0;
                                        board[y][x].setPiece(selectedSquare.getPiece());
                                        board[y][x].getPanel().add(pieces.get(selectedSquare.getPiece()));
                                        board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                        board[0][3].setPiece(board[0][0].getPiece());
                                        board[0][3].getPanel().add(pieces.get(board[0][0].getPiece()));
                                        board[0][0].setPiece(null);
                                    } else {
                                        board[y][x].setPiece(selectedSquare.getPiece());
                                        board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                        boolean[][] futureDefended = findDefended(!Wturn, board);
                                        boolean futureKingCheck = kingInCheck(!Wturn, board, futureDefended);
                                        board[selectedSquare.getY()][selectedSquare.getX()].setPiece(board[y][x].getPiece());
                                        board[y][x].setPiece(null);
                                        clickCount = 0;
                                        if(futureKingCheck) {
                                            return;
                                        } else {
                                            if(selectedSquare.getPiece().getType() == 'p' && y == 7) { //PROMOTION
                                                clickCount = 0;
                                                board[y][x+8].getPiece().move(x, y);
                                                board[y][x].getPanel().add(bQueens.get(board[y][x+8].getPiece()));
                                                board[y][x].setPiece(board[y][x+8].getPiece());
                                                pieces.remove(selectedSquare.getPiece());
                                                pieces.put(board[y][x].getPiece(), bQueens.get(board[y][x].getPiece()));
                                                board[selectedSquare.getY()][selectedSquare.getX()].getPanel().remove(0);
                                                board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                                frame.repaint();
                                            } else if(selectedSquare.getPiece().getType() == 'p' && selectedSquare.getY() == 1 && y == 3) { //BLACK DOUBLE PUSH
                                                selectedSquare.getPiece().setMoved(true);
                                                selectedSquare.getPiece().move(x, y);
                                                board[y][x].setPiece(selectedSquare.getPiece());
                                                board[y][x].getPanel().add(pieces.get(selectedSquare.getPiece()));
                                                board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                                BDoublePushedPawn = new Point(x, y);
                                            } else if(selectedSquare.getPiece().getType() == 'p' && WDoublePushedPawn != null && (x == selectedSquare.getX()-1 || x == selectedSquare.getX()+1)) {
                                                pieces.remove(board[y-1][x].getPiece());
                                                selectedSquare.getPiece().move(x, y);
                                                board[y-1][x].getPanel().remove(0);
                                                board[y-1][x].setPiece(null);
                                                board[y][x].getPanel().add(pieces.get(selectedSquare.getPiece()));
                                                board[y][x].setPiece(selectedSquare.getPiece());
                                                board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                                frame.repaint();
                                                WDoublePushedPawn = null;
                                            } else { //BLACK REGULAR MOVE
                                                selectedSquare.getPiece().move(x, y);
                                                clickCount = 0; 
                                                board[y][x].setPiece(selectedSquare.getPiece());
                                                board[y][x].getPanel().add(pieces.get(selectedSquare.getPiece()));
                                                board[selectedSquare.getY()][selectedSquare.getX()].setPiece(null);
                                                WDoublePushedPawn = null;
                                            }
                                        }
                                    }
                                }
                                frame.repaint();
                                if(Wturn) { //find defended squares
                                    WDefended = findDefended(Wturn, board);
                                } else {
                                    BDefended = findDefended(Wturn, board);
                                }
                                if(Wturn) { //check if king in check
                                    BKingCheck = kingInCheck(Wturn, board, WDefended);
                                } else {
                                    WKingCheck = kingInCheck(Wturn, board, BDefended);
                                }
                                Wturn = !Wturn;
                            }
                        }
                        clickCount = 0;
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }
    
            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
        
            }

            @Override
            public void mouseExited(MouseEvent e) {
        
            }
        });
        this.setVisible(true);
    }

    public boolean[][] findDefended(boolean white, Square[][] board) {
        boolean defendedSquares[][] = new boolean[8][8];
        if(white) {
            for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++) {
                    if(board[i][j].getPiece() != null) {
                        if(board[i][j].getPiece().isWhite()) {
                            defended = board[i][j].getPiece().findDefense(board);
                            for(Point pp : defended) {
                                defendedSquares[(int)pp.getY()][(int)pp.getX()] = true;
                            }
                        }
                    }
                    
                }
            }
        } else {
            for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++) {
                    if(board[i][j].getPiece() != null) {
                        if(!board[i][j].getPiece().isWhite()) {
                            defended = board[i][j].getPiece().findDefense(board);
                            for(Point pp : defended) {
                                defendedSquares[(int)pp.getY()][(int)pp.getX()] = true;
                            }
                        }
                    }
                    
                }
            }
        }
        return defendedSquares;
    }
    

    public boolean kingInCheck(boolean white, Square[][] board, boolean[][] defendedSquares) {
        if(white) {
            for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++) {
                    if(board[i][j].getPiece() != null) {
                        if(board[i][j].getPiece().getType() == 'k' && defendedSquares[i][j] == true) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } else {
            for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++) {
                    if(board[i][j].getPiece() != null) {
                        if(board[i][j].getPiece().getType() == 'K' && defendedSquares[i][j] == true) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    public static boolean[][] getWDefended() {
        return WDefended;
    }

    public static boolean[][] getBDefended() {
        return BDefended;
    }
}


