package DesignSystems.ObjectOrientedDesignCentric.Games.BoardGames.TicTacToe;

import java.util.ArrayList;
import java.util.Iterator;

public class CTC_Q16_04_Tic_Tac_Win {
    enum Piece { Empty, Red, Blue };
    public class Position {
        public int row, column;
        public Position(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    //Solution 1 :
    public static int convertBoardToInt(Piece[][] board) {
        int sum = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int value = board[i][j].ordinal();
                sum = sum * 3 + value;
            }
        }
        return sum;
    }

    //Solution 2 :
    public static Piece hasWon(Piece[][] board, int row, int column) {
        if (board.length != board[0].length) return Piece.Empty;

        Piece piece = board[row][column];

        if (piece == Piece.Empty) return Piece.Empty;
        if (hasWonRow(board, row) || hasWonColumn(board, column)) {
            return piece;
        }

        if (row == column && hasWonDiagonal(board, 1)) {
            return piece;
        }

        if (row == (board.length - column - 1) && hasWonDiagonal(board, -1)) {
            return piece;
        }

        return Piece.Empty;
    }

    public static boolean hasWonRow(Piece[][] board, int row) {
        for (int c = 1; c < board[row].length; c++) {
            if (board[row][c] != board[row][0]) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasWonColumn(Piece[][] board, int column) {
        for (int r = 1; r < board.length; r++) {
            if (board[r][column] != board[0][column]) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasWonDiagonal(Piece[][] board, int direction) {
        int row = 0;
        int column = direction == 1 ? 0 : board.length - 1;
        Piece first = board[0][column];
        for (int i = 0; i < board.length; i++) {
            if (board[row][column] != first) {
                return false;
            }
            row += 1;
            column += direction;
        }
        return true;
    }

    //Solution 3 :
    public static boolean hasWinner03(Piece p1, Piece p2, Piece p3) {
        if (p1 == Piece.Empty) {
            return false;
        }
        return p1 == p2 && p2 == p3;
    }

    public static Piece hasWinner03(Piece[][] board) {
        for (int i = 0; i < board.length; i++) {
            /* Check Rows */
            if (hasWinner03(board[i][0], board[i][1], board[i][2])) {
                return board[i][0];
            }

            /* Check Columns */
            if (hasWinner03(board[0][i], board[1][i], board[2][i])) {
                return board[0][i];
            }
        }

        /* Check Diagonal */
        if (hasWinner03(board[0][0], board[1][1], board[2][2])) {
            return board[0][0];
        }

        if (hasWinner03(board[0][2], board[1][1], board[2][0])) {
            return board[0][2];
        }

        return Piece.Empty;
    }

    //Solution 4 :
    public static boolean hasWinner(Piece p1, Piece p2, Piece p3) {
        if (p1 == Piece.Empty) {
            return false;
        }
        return p1 == p2 && p2 == p3;
    }

    public static Piece hasWon04(Piece[][] board) {
        if (board[0][0] != Piece.Empty &&
                (hasWinner(board[0][0], board[0][1], board[0][2]) ||
                        hasWinner(board[0][0], board[1][0], board[2][0]))) {
            return board[0][0];
        }

        if (board[2][2] != Piece.Empty &&
                (hasWinner(board[2][0], board[2][1], board[2][2]) ||
                        hasWinner(board[0][2], board[1][2], board[2][2]))) {
            return board[2][2];
        }

        if (board[1][1] != Piece.Empty &&
                (hasWinner(board[0][0], board[1][1], board[2][2]) ||
                        hasWinner(board[0][2], board[1][1], board[2][0]) ||
                        hasWinner(board[1][0], board[1][1], board[1][2]) ||
                        hasWinner(board[0][1], board[1][1], board[2][1]))) {
            return board[1][1];
        }

        return Piece.Empty;
    }

    //Solution 5 :
    public static Piece hasWon05(Piece[][] board) {
        int size = board.length;
        if (board[0].length != size) return Piece.Empty;
        Piece first;

        /* Check rows. */
        for (int i = 0; i < size; i++) {
            first = board[i][0];
            if (first == Piece.Empty) continue;
            for (int j = 1; j < size; j++) {
                if (board[i][j] != first) {
                    break;
                } else if (j == size - 1) {
                    return first;
                }
            }
        }

        /* Check columns. */
        for (int i = 0; i < size; i++) {
            first = board[0][i];
            if (first == Piece.Empty) continue;
            for (int j = 1; j < size; j++) {
                if (board[j][i] != first) {
                    break;
                } else if (j == size - 1) {
                    return first;
                }
            }
        }

        /* Check diagonals. */
        first = board[0][0];
        if (first != Piece.Empty) {
            for (int i = 1; i < size; i++) {
                if (board[i][i] != first) {
                    break;
                } else if (i == size - 1) {
                    return first;
                }
            }
        }

        first = board[0][size - 1];
        if (first != Piece.Empty) {
            for (int i = 1; i < size; i++) {
                if (board[i][size - i - 1] != first) {
                    break;
                } else if (i == size - 1) {
                    return first;
                }
            }
        }

        return Piece.Empty;
    }

    //Solution 6 :
    public static Piece hasWon06(Piece[][] board) {
        Piece winner = Piece.Empty;

        /* Check rows. */
        for (int i = 0; i < board.length; i++) {
            winner = hasWon06(board, i, 0, 0, 1);
            if (winner != Piece.Empty) {
                return winner;
            }
        }

        /* Check columns. */
        for (int i = 0; i < board[0].length; i++) {
            winner = hasWon06(board, 0, i, 1, 0);
            if (winner != Piece.Empty) {
                return winner;
            }
        }

        /* Check top/left -> bottom/right diagonal. */
        winner = hasWon06(board, 0, 0, 1, 1);
        if (winner != Piece.Empty) {
            return winner;
        }

        /* Check top/right -> bottom/left diagonal. */
        return hasWon06(board, 0, board[0].length - 1, 1, -1);
    }

    public static Piece hasWon06(Piece[][] board, int row, int col, int incrementRow, int incrementCol) {
        Piece first = board[row][col];
        while (row < board.length && col < board[row].length) {
            if (board[row][col] != first) {
                return Piece.Empty;
            }
            row += incrementRow;
            col += incrementCol;
        }
        return first;
    }

    //Solution 7 :
    public  Piece hasWon(Piece[][] board) {
        if (board.length != board[0].length) return Piece.Empty;
        int size = board.length;

        ArrayList<PositionIterator> instructions = new ArrayList<PositionIterator>();
        for (int i = 0; i < board.length; i++) {
            instructions.add(new PositionIterator(new Position(0, i), 1, 0, size));
            instructions.add(new PositionIterator(new Position(i, 0), 0, 1, size));
        }
        instructions.add(new PositionIterator(new Position(0, 0), 1, 1, size));
        instructions.add(new PositionIterator(new Position(0, size - 1), 1, -1, size));

        for (PositionIterator iterator : instructions) {
            Piece winner = hasWon(board, iterator);
            if (winner != Piece.Empty) {
                return winner;
            }
        }
        return Piece.Empty;
    }

    public  Piece hasWon(Piece[][] board, PositionIterator iterator) {
        Position firstPosition = iterator.next();
        Piece first = board[firstPosition.row][firstPosition.column];
        while (iterator.hasNext()) {
            Position position = iterator.next();
            if (board[position.row][position.column] != first) {
                return Piece.Empty;
            }
        }
        return first;
    }

    public class PositionIterator implements Iterator<Position> {
        private int rowIncrement, colIncrement, size;
        private Position current;

        public PositionIterator(Position p, int rowIncrement, int colIncrement, int size) {
            this.rowIncrement = rowIncrement;
            this.colIncrement = colIncrement;
            this.size = size;
            current = new Position(p.row - rowIncrement, p.column - colIncrement);
        }

        @Override
        public boolean hasNext() {
            return current.row + rowIncrement < size && current.column + colIncrement < size;
        }

        @Override
        public Position next() {
            current = new Position(current.row + rowIncrement, current.column + colIncrement);
            return current;
        }
    }

}
