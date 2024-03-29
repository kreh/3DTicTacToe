/*-----------------------------------------------------------------------------------------------
Program: TicTacToe3D.java - this program lets two human player play tic tac toe against each other in 3D 4x4x4
Author: Andrilla Rahman
Date: December 14, 2018
 ------------------------------------------------------------------------------------------*/

package tictactoe.java;

import java.util.Scanner;

public class TicTacToe3DJava
{
    public static Scanner input = new Scanner(System.in); // keyboard input

    public static void main(String[] args)
    {
        char[][][] board = new char[4][4][4];
        int totalTurns = 0,
                totalGames = 0,
                result = 0,
                row, col;

        String name, symbol, move, choice;
        char user1Symbol, user2Symbol;

        User user1 = new User();
        User user2 = new User();

        System.out.print("Enter User 1 name: ");
        name = input.nextLine();

        System.out.print("Please select your symbol (O or X): ");
        symbol = input.nextLine().toUpperCase();

        user1Symbol = symbol.charAt(0);
        user1.setName(name);
        user1.setSymbol(user1Symbol);

        System.out.print("Enter User 2 name: ");
        name = input.nextLine();
        System.out.println("\n");

        if (user1Symbol == 'O')
            user2Symbol = 'X';
        else
            user2Symbol = 'O';

        user2.setName(name);
        user2.setSymbol(user2Symbol);

        // empty board
        while (true) {
            totalGames++;

            for (int a = 0; a < 4; a++)
                for (int b = 0; b < 4; b++)
                    for(int c = 0; c < 4; c++)
                        board[a][b][c] = ' ';

            printBoard(board);

            System.out.println("\nPlayer 1: " + user1.getName() + ": " + user1.getSymbol() + "s");
            System.out.println("Player 2: " + user2.getName() + ": " + user2.getSymbol() + "s\n");

            while(totalTurns < 64)
            {
                if (totalTurns % 2 == 0) {
                    System.out.println("\n" + user1.getName() + ", it's your turn. [example usage: 1Bc, 4Aa]");

                    move = getUserMove(board);
                    row = Integer.parseInt(move.substring(0,1)) - 1;

                    col = getCol(move);
                }
            }
        }
    }

    private static int getRow(String move)
    {
        int row = 0;
        switch (move.charAt(1))
        {
            case 'A':
                return row;
            case 'B':
                row = 1;
                break;
            case 'C':
                row = 2;
                break;
            case 'D':
                row = 3;
                break;
        }

        return row;
    }

    private static int getCol(String move)
    {
        int col = 0;
        switch (move.charAt(1))
        {
            case 'A':
                return col;
            case 'B':
                col = 1;
                break;
            case 'C':
                col = 2;
                break;
            case 'D':
                col = 3;
                break;
        }

        return col;
    }

    private static String getUserMove(char[][][] board)
    {
        String move;
        while(true)
        {
            System.out.print("Enter your next move: ");
            move = input.nextLine();
            System.out.println("\n");

            if ((move.charAt(0) == '1' || move.charAt(0) == '2' || move.charAt(0) == '3')
             && (move.charAt(1) == 'A' || move.charAt(1) == 'B' || move.charAt(1) == 'C')
             && (move.charAt(2) == 'a' || move.charAt(2) == 'b' || move.charAt(2) == 'c'))
            {
                if (checkOnBoard(move, board))
                    return move;
                else
                    System.out.println("That postion is already taken.\n");
            }
            else
                System.out.println("Invalid move. Try again please!\n");
        }
    }

    private static boolean checkOnBoard(String move, char[][][] board)
    {
        int row = Integer.parseInt(move.substring(0,1)) - 1;
        int col = 0;
        int height = 0;

        switch (move.charAt(1)) {
            case 'A':
                col = 0;
                break;
            case 'B':
                col = 1;
                break;
            case 'C':
                col = 2;
                break;
            case 'D':
                col = 3;
                break;
        }

        switch (move.charAt(2)) {
            case 'a':
                height = 0;
                break;
            case 'b':
                height = 1;
                break;
            case 'c':
                height = 2;
                break;
            case 'd':
                height = 3;
                break;
        }

        return board[row][col][height] == ' ';
    }

    private static int makePlay(char[][][] board, int r, int c, int h, char symbol)
    {
        board[r][c][h] = symbol;
        if (win(board, r, c, h, symbol))
            return 1;
        else
            return 0;
    }

    private static boolean win(char[][][] board, int row, int col, int height, char symbol)
    {
        if (checkRow(board, row, height, symbol ))
            return true;
        else if (checkCol(board, col, height, symbol))
            return true;
        else if (checkHeight(board, row, col, symbol))
            return true;
        else
            return checkDia(board, symbol);
    }

    private static boolean checkRow(char[][][] board, int r, int h, char symbol)
    {
        return board[r][0][h] == symbol && board[r][1][h] == symbol && board[r][2][h] == symbol && board[r][3][h] == symbol;
    }

    private static boolean checkCol(char[][][] board, int c, int h, char symbol)
    {
        return board[0][c][h] == symbol && board[1][c][h] == symbol && board[2][c][h] == symbol && board[3][c][h] == symbol;
    }

    private static boolean checkHeight(char[][][] board, int r, int c, char symbol)
    {
        return board[r][c][0] == symbol && board[r][c][1] == symbol && board[r][c][2] == symbol && board[r][c][3] == symbol;
    }

    private static boolean checkDia(char[][][] board, char symbol)
    {
        if (
                board[0][0][0] == symbol && board[1][1][1] == symbol && board[2][2][2] == symbol && board[3][3][3] == symbol
        )
            return true;

        return (board[0][3][0] == symbol && board[1][2][1] == symbol && board[2][1][2] == symbol && board[3][0][3] == symbol);
    }

    private static void printBoard(char[][][] board)
    {
        int a, b, c;
        char[] smallLetters = { 'a', 'b', 'c', 'd' };
        char[] bigLetters = { 'A', 'B', 'C', 'D' };

        for (a = 0; a < 4; a++) {

            System.out.print("Level " + smallLetters[a] + ":\n");
            System.out.println("\t  " + bigLetters[0] + "\t  " + bigLetters[1] + "\t  " + bigLetters[2] + "\t  " + bigLetters[3]);

            for (b = 0; b < 4; b++) {

                System.out.println("\t-----------------");
                System.out.print((b + 1) + "\t|");

                for (c = 0; c < 4; c++) {
                    System.out.print("" + board[a][b][c] + "\t|" );
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
