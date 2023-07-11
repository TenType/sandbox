package com.sandbox;

import java.util.InputMismatchException;
import java.util.Scanner;

enum Color {
    Empty,
    Red,
    Yellow
}

enum PlayResult {
    // Success
    Ongoing,
    P1Win,
    P2Win,
    Draw,

    // Errors
    OutOfBounds,
    ColumnFull,
}

public class ConnectFour {
    // Dimensions of a standard board
    public final static int WIDTH = 7;
    public final static int HEIGHT = 6;

    // Used for the isWinningMove method
    private final static int[] RUNS = {1, 0, 1, 1};
    private final static int[] RISES = {0, 1, -1, 1};

    private Color[][] board = new Color[HEIGHT][WIDTH];
    private boolean isP1Turn = true;

    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();
        System.out.print(game.toString());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (game.isP1Turn) {
                System.out.print(format(Color.Red, "P1 > "));
            } else {
                System.out.print(format(Color.Yellow, "P2 > "));
            }

            // Collect valid input
            int col;
            try {
                col = scanner.nextInt();
            } catch (InputMismatchException error) {
                System.err.println("Error: Please input a number");
                scanner.next();
                continue;
            }

            // Play the 1-indexed column for the current player
            PlayResult result = game.play(col - 1);
            System.out.print(game.toString());

            switch (result) {
                // Errors
                case OutOfBounds:
                    System.err.println("Error: Column is out of bounds");
                    break;

                case ColumnFull:
                    System.err.println("Error: Column is full");
                    break;

                // Game over
                case P1Win:
                    System.out.println(format(Color.Red, "Player 1 wins!"));
                    scanner.close();
                    return;

                case P2Win:
                    System.out.println(format(Color.Yellow, "Player 2 wins!"));
                    scanner.close();
                    return;

                case Draw:
                    System.out.println("The game is a draw!");
                    scanner.close();
                    return;

                // Continue playing
                case Ongoing:
                    break;
            }
        }
    }

    /**
     * Formats a String with a player color.
     * @param color
     * @param s
     */
    private static String format(Color color, String s) {
        switch (color) {
            case Red:
                return "\u001B[31m" + s + "\u001B[0m";
            
            case Yellow:
                return "\u001B[33m" + s + "\u001B[0m";

            default:
                return s;
        }
    }

    public ConnectFour() {
        // Initialize board with empty tiles
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = Color.Empty;
            }
        }
    }

    /**
     * Returns a String representation of the board.
     */
    public String toString() {
        String s = "";
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                Color tile = board[row][col];
                if (tile == Color.Red) {
                    s += format(Color.Red, "X");
                } else if (tile == Color.Yellow) {
                    s += format(Color.Yellow, "O");
                } else {
                    s += "_";
                }
                s += " ";
            }
            s += "\n";
        }
        return s;
    }

    /**
     * Drops the current player's piece in a column.
     * Returns true if the piece was played, or false
     * if the column is full or out of bounds.
     * @param col - 0-indexed
     */
    public PlayResult play(int col) {
        // Check if out of bounds
        if (col < 0 || col >= WIDTH) {
            return PlayResult.OutOfBounds;
        }

        for (int row = board.length - 1; row >= 0; row--) {
            // Find the lowest open cell
            if (board[row][col] == Color.Empty) {
                // Place current player's piece
                if (isP1Turn) {
                    board[row][col] = Color.Red;
                } else {
                    board[row][col] = Color.Yellow;
                }

                // Check if win
                if (isWinningMove(col)) {
                    if (isP1Turn) {
                        return PlayResult.P1Win;
                    }
                    return PlayResult.P2Win;
                }

                // Check if draw
                if (isDraw()) {
                    return PlayResult.Draw;
                }

                // Alternate turns
                isP1Turn = !isP1Turn;
                return PlayResult.Ongoing;
            }
        }
        return PlayResult.ColumnFull;
    }

    /**
     * Returns whether or not the game is a draw.
     */
    private boolean isDraw() {
        // Check top row for empty tiles
        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == Color.Empty) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns whether or not the last piece in the column is a winning move.
     * @param col - 0-indexed
     */
    private boolean isWinningMove(int col) {
        int row = rowOfTopPiece(col);
        Color color = board[row][col];

        // Check horizontal, vertical, and both diagonals
        for (int i = 0; i < RUNS.length; i++) {
            int counter = 1;
            int run = RUNS[i];
            int rise = RISES[i];

            // Check positive and negative directions
            for (int direction = 1; direction >= -1; direction -= 2) {
                // The last piece in a vertical win is always on top, so we do not need to check
                if (run == 0 && direction == -1) {
                    continue;
                }
    
                for (int step = 1; step <= 3; step++) {
                    int rowCheck = row + rise * step * direction;
                    int colCheck = col + run * step * direction;

                    // DEBUG
                    // System.out.println(colCheck + "," + rowCheck);

                    // Out of bounds or not matching
                    boolean rowOutOfBounds = rowCheck < 0 || rowCheck >= HEIGHT;
                    boolean colOutOfBounds = colCheck < 0 || colCheck >= WIDTH;
                    if (rowOutOfBounds || colOutOfBounds || board[rowCheck][colCheck] != color) {
                        break;
                    }

                    counter += 1;

                    // Four in a row
                    if (counter >= 4) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns the row index of the topmost piece in the column.
     * If there is no topmost piece, an exception is thrown.
     * @param col - 0-indexed
     */
    private int rowOfTopPiece(int col) {
        int row = 0;
        while (board[row][col] == Color.Empty) {
            row++;
        }
        return row;
    }
}
