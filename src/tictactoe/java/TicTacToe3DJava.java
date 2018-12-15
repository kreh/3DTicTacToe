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
    public static User lastWinner;
    public static User user1;
    public static User user2;

    public static void main(String[] args)
    {
        char[][][] board = new char[4][4][4];
        char user1Symbol, user2Symbol;
        int result = 0;
        int     totalTurns = 0,
                totalGames = 0;
        String name, symbol;

        user1 = new User();
        user2 = new User();

        System.out.print("Enter User 1 name: ");
        name = input.nextLine();

        System.out.print("Please select your symbol (O or X): ");
        symbol = input.nextLine().toUpperCase();

        user1Symbol = symbol.charAt(0) == 'X' ? 'X' : 'O';
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


            System.out.println("\nPlayer 1: " + user1.getName() + ": " + user1.getSymbol() + "s");
            System.out.println("Player 2: " + user2.getName() + ": " + user2.getSymbol() + "s\n");

            while(totalTurns < 64)
            {
                printBoard(board);
                if (totalTurns % 2 == 0) {
                    System.out.println("\n" + user1.getName() + ", it's your turn. [example usage: 1Bc, 4Aa]");
                    result = go(board, user1.getSymbol());
                }
                else {
                    System.out.println("\n" + user2.getName() + ", it's your turn. [example usage: 1Bc, 4Aa]");
                    result = go(board, user2.getSymbol());
                }
                if (result == 1) {
                    break;
                }
                totalTurns++;
            }
            if (result == 1) {
                System.out.print("\n" + lastWinner.getName() + " won! Would you like to play again? Y/N: ");
                if (input.nextLine().substring(0, 1).matches("[NnQq]")) {
                    System.out.println("Goodbye!");
                    break;
                }
            }
            else {
                System.out.print("\nThe game was a draw. Would you like to try again? Y/N: ");
                if (input.nextLine().substring(0, 1).matches("[NnQq]")) {
                    System.out.println("Goodbye!");
                    break;
                }
            }
        }
    }

    private static int go(char[][][] board, char symbol) {
        String move = getUserMove(board);
        int col = getCol(move);
        int row = getRow(move);
        int hyt = getHyt(move);
        return makePlay(board, row, col, hyt, symbol);
    }

    private static String getUserMove(char[][][] board)
    {
        String move;
        while(true)
        {
            System.out.print("Enter your next move: ");
            move = input.nextLine();
            System.out.println("\n");

            char row = move.charAt(0);
            char col = move.charAt(1);
            char brd = move.charAt(2);
            if ((row=='1'||row=='2'||row=='3'||row=='4')
             && (col=='A'||col=='B'||col=='C'||col=='D')
             && (brd=='a'||brd=='b'||brd=='c'||brd=='d'))
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
        int row = getRow(move);
        int col = getCol(move);
        int hyt = getHyt(move);
        return board[row][col][hyt] == ' ';
    }

    private static int makePlay(char[][][] board, int r, int c, int h, char symbol)
    {
        board[r][c][h] = symbol;
        if (win(board, r, c, h, symbol)) {
            lastWinner = symbol == user2.getSymbol() ? user2 : user1;
            return 1;
        }
        else
            return 0;
    }

    private static boolean win(char[][][] board, int row, int col, int hyt, char symbol)
    {
        if (checkRow(board, row, hyt, symbol ))
            return true;
        else if (checkCol(board, col, hyt, symbol))
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
        TriIntPredicate checkPoint = (x, y, z) -> board[x][y][z] == symbol;
        // diagonal on each board layer
        if (
                (checkPoint.apply(0,0,0) && checkPoint.apply(1,1,0) && checkPoint.apply(2,2,0) && checkPoint.apply(3,3,0))
              ||(checkPoint.apply(0,3,0) && checkPoint.apply(1,2,0) && checkPoint.apply(2,1,0) && checkPoint.apply(3,0,0))
              ||(checkPoint.apply(0,0,1) && checkPoint.apply(1,1,1) && checkPoint.apply(2,2,1) && checkPoint.apply(3,3,1))
              ||(checkPoint.apply(0,3,1) && checkPoint.apply(1,2,1) && checkPoint.apply(2,1,1) && checkPoint.apply(3,0,1))
              ||(checkPoint.apply(0,0,2) && checkPoint.apply(1,1,2) && checkPoint.apply(2,2,2) && checkPoint.apply(3,3,2))
              ||(checkPoint.apply(0,3,2) && checkPoint.apply(1,2,2) && checkPoint.apply(2,1,2) && checkPoint.apply(3,0,2))
              ||(checkPoint.apply(0,0,3) && checkPoint.apply(1,1,3) && checkPoint.apply(2,2,3) && checkPoint.apply(3,3,3))
              ||(checkPoint.apply(0,3,3) && checkPoint.apply(1,2,3) && checkPoint.apply(2,1,3) && checkPoint.apply(3,0,3))
        )
            return true;

        // diagonal from corner top layer to corner bottom layer
        return (
                (checkPoint.apply(0,0,0) && checkPoint.apply(1,1,1) && checkPoint.apply(2,2,2) && checkPoint.apply(3,3,3))
              ||(checkPoint.apply(0,3,0) && checkPoint.apply(1,2,1) && checkPoint.apply(2,1,2) && checkPoint.apply(3,0,3))
        );
    }

    private static void printBoard(char[][][] board)
    {
        int hyt, row, col;
        char[] smallLetters = { 'a', 'b', 'c', 'd' };
        char[] bigLetters = { 'A', 'B', 'C', 'D' };

        for (hyt = 0; hyt < 4; hyt++) {

            System.out.print("\nLevel " + smallLetters[hyt] + ":\n");
            System.out.print("\t  " + bigLetters[0] + "\t  " + bigLetters[1] + "\t  " + bigLetters[2] + "\t  " + bigLetters[3]);

            for (row = 0; row < 4; row++) {

                System.out.println("\n\t-----------------");
                System.out.print((row + 1) + "\t|");

                for (col = 0; col < 4; col++) {
                    System.out.print(" " + board[row][col][hyt] + " |" );
                }
            }
            System.out.println("\n\t-----------------");
        }
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

    private static int getRow(String move)
    {
        return Integer.parseInt(move.substring(0, 1)) - 1;
    }

    private static int getHyt(String move)
    {
        int hyt = 0;
        switch (move.charAt(2))
        {
            case 'a':
                return hyt;
            case 'b':
                hyt = 1;
                break;
            case 'c':
                hyt = 2;
                break;
            case 'd':
                hyt = 3;
                break;
        }

        return hyt;
    }
}
