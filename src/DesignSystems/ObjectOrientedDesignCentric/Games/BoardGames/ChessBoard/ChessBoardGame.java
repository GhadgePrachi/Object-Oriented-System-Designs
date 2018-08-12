package DesignSystems.ObjectOrientedDesignCentric.Games.BoardGames.ChessBoard;

import java.util.ArrayList;

public class ChessBoardGame {
    ArrayList<Piece> blackSet;
    ArrayList<Piece> whiteSet;
    Piece[][] chessBoard;
    final static int BOARD_SIZE = 8;
    int twoCounts = 2;
    int eightCounts = 8;

    public ChessBoardGame(Player player1, Player player2){
        chessBoard = new Piece[BOARD_SIZE][BOARD_SIZE];
        blackSet = new ArrayList<>();
        whiteSet = new ArrayList<>();

        initializeChessBoardGame();
    }

    public void initializeChessBoardGame(){
        initializeSet(PieceColor.White);
        initializeSet(PieceColor.Black);
        initializeChessBoard();
    }

    public void initializeSet(PieceColor color){
        ArrayList<Piece> currentSet = new ArrayList<>();
        if(color == PieceColor.White){
            currentSet = whiteSet;
        }else if(color == PieceColor.Black){
            currentSet = blackSet ;
        }

        for(PieceType type : PieceType.values()){
            if(type==PieceType.Bishop || type==PieceType.Knight || type==PieceType.Rook) {
                while(twoCounts>0) {
                    currentSet.add(new Piece(color, type));
                    twoCounts--;
                }
            }else if(type==PieceType.Pawn){
                while(eightCounts>0) {
                    currentSet.add(new Piece(color, type));
                    eightCounts--;
                }
            }else if(type==PieceType.Queen || type==PieceType.King){
                currentSet.add(new Piece(color, type));
            }
        }
    }

    public void initializeChessBoard(){
        //WhiteSet Pieces placed at bottom two rows & BlackSet Pieces placed at top two rows
    }

    public boolean isValidMove(Piece piece,Position previous, Position moved){

        if(piece.type == PieceType.Bishop){
            //Step 1 : Path Validity
            int colDiff = Math.abs(moved.col - previous.col);
            int rowDiff = Math.abs(moved.row - previous.row);
            if(colDiff-rowDiff!=0){
                return false;
            }

            //Step 2 : Position Validity
            Piece existingPiece = chessBoard[moved.row][moved.col];
            //Traverse path and check if any piece is in on that path - if any return false;
            //Check for moved position validity
            if(existingPiece!=null){
                if(existingPiece.color==piece.color) {
                    return false;
                }else{
                    //update chessboard  - remove existing piece in chessboard and delete from set
                    //placePiece()
                    return true;
                }
            }
            //placePiece()
            return true;
        }
        //TODO : different piece types

        return true;
    }

    public void placePiece(Piece[][] chessBoard,Position previous, Position moved){
        //vacate previous position and place piece onto moved position in chessboard
    }

    public boolean hasWon(Piece[][] chessBoard, Piece piece){
        //Step 1 : Opponent has no pieces left
        if(piece.color == PieceColor.White){
            return blackSet.isEmpty();
        }else if (piece.color == PieceColor.Black) {
            return whiteSet.isEmpty();
        }

        //Step 2 : Checkmate for opponent's king with current move
         /*
         Run : Can I move the king/mate to safe position?
         Block : Can I block king/mate?
         Capture : Can I take the attacker?

        The king only has 8 spots to move.
        One should already have a list of squares under attack from enemy pieces, just check if that square is in that list.
        One should have a list of squares that are movable. Check if one of those blocks every attacker.
        One can easily tell who is attacking the king by checking each enemy piece's attacked squares for the king's square. If there's only one attacker, and that piece's squares is in one of your piece's attacked squares, you're all set.
        */
        return true;
    }

    public boolean hasWonWithMove(Piece[][] chessBoard, Piece piece,Position previous, Position moved ){
        //TODO
        return true;
    }
}
