package edu.albany.jahadlewis;

import java.util.Scanner;

public class ClassProject {

    static char[][] board = new char[3][3];
    static char currentPlayer = 'X';

    static int xWins = 0;
    static int oWins = 0;
    static int draws = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        System.out.println("=== Welcome to Tic-Tac-Toe ===");
        System.out.println("Players: X and O");
        System.out.println("Enter row and column numbers (1-3) to place your mark.");

        while (playAgain) {

            initializeBoard();
            currentPlayer = 'X';
            boolean gameOver = false;

            while (!gameOver) {

                printBoard();
                System.out.println("Player " + currentPlayer + "'s turn.");

                int row = getValidInput(scanner, "Enter row (1-3): ");
                int col = getValidInput(scanner, "Enter column (1-3): ");

                row = row - 1;
                col = col - 1;

                if (board[row][col] != ' ') {
                    System.out.println("That cell is already taken! Try again.");
                    continue;
                }

                board[row][col] = currentPlayer;

                if (checkWin()) {
                    printBoard();
                    System.out.println("Player " + currentPlayer + " wins! Congratulations!");

                    if (currentPlayer == 'X') {
                        xWins++;
                    } else {
                        oWins++;
                    }

                    printScoreboard();
                    gameOver = true;
                } else if (checkDraw()) {
                    printBoard();
                    System.out.println("It is a draw! Well played by both!");

                    draws++;
                    printScoreboard();
                    gameOver = true;
                } else {
                    switchPlayer();
                }
            }

            System.out.print("Play again? (yes/no): ");
            String again = scanner.next().trim().toLowerCase();

            if (!(again.equals("yes") || again.equals("y"))) {
                playAgain = false;
                System.out.println("Thanks for playing! Goodbye.");
            }
        }

        scanner.close();
    }

    static void initializeBoard() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = ' ';
            }
        }
    }

    static void printBoard() {
        System.out.println("    Col1  Col2  Col3");

        for (int r = 0; r < 3; r++) {
            System.out.print("Row" + (r + 1) + "  ");

            for (int c = 0; c < 3; c++) {
                System.out.print(" " + board[r][c] + " ");

                if (c < 2) {
                    System.out.print("|");
                }
            }

            System.out.println();

            if (r < 2) {
                System.out.println("      ---|---|---");
            }
        }

        System.out.println();
    }

    static int getValidInput(Scanner scanner, String prompt) {
        int value;

        while (true) {
            System.out.print(prompt);

            if (scanner.hasNextInt()) {
                value = scanner.nextInt();

                if (value >= 1 && value <= 3) {
                    return value;
                } else {
                    System.out.println("Error: Please enter a number between 1 and 3.");
                }
            } else {
                System.out.println("Error: Invalid input. Please enter a whole number.");
                scanner.next();
            }
        }
    }

    static boolean checkWin() {
        char p = currentPlayer;

        for (int r = 0; r < 3; r++) {
            if (board[r][0] == p && board[r][1] == p && board[r][2] == p) {
                return true;
            }
        }

        for (int c = 0; c < 3; c++) {
            if (board[0][c] == p && board[1][c] == p && board[2][c] == p) {
                return true;
            }
        }

        if (board[0][0] == p && board[1][1] == p && board[2][2] == p) {
            return true;
        }

        if (board[0][2] == p && board[1][1] == p && board[2][0] == p) {
            return true;
        }

        return false;
    }

    static boolean checkDraw() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    static void switchPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }

    static void printScoreboard() {
        System.out.println("\n=== Scoreboard ===");
        System.out.println("Player X Wins: " + xWins);
        System.out.println("Player O Wins: " + oWins);
        System.out.println("Draws: " + draws);
        System.out.println("==================\n");
    }
}