package tictactoe;

import java.util.Scanner;


public class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    private boolean gameOver;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        gameOver = false;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            displayBoard();
            makeMove(scanner);
            checkGameOver();
            switchPlayer();
        }

        displayBoard();
        displayResult();
    }

    private void displayBoard() {
        System.out.println("---------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println("\n---------");
        }
    }

    private void makeMove(Scanner scanner) {
        boolean validMove = false;

        while (!validMove) {
            System.out.print("Player " + currentPlayer + ", enter your move (row [1-3] column [1-3]): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
                board[row][col] = currentPlayer;
                validMove = true;
            } else if (board[row][col] != '-') {

                System.out.println("Someone has already made a move at this position! Try again.");
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
    }

    private void checkGameOver() {
        if (checkWin(currentPlayer) || checkDraw()) {
            gameOver = true;
        }
    }

    private boolean checkWin(char player) {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    private boolean checkDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private void switchPlayer() {

        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }

    private void displayResult() {
        if (checkWin('X')) {
            System.out.println("Player X wins!");
        } else if (checkWin('O')) {
            System.out.println("Player O wins!");
        } else {
            System.out.println("None is win!");
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.playGame();
    }
}

