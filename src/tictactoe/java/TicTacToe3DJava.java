/*-----------------------------------------------------------------------------------------------
Program: TicTacToe3D.java - this program lets two human player play tic tac toe against each other in 3D 4x4x4
Author: Andrilla Rahman
Date: December 14, 2018
 ------------------------------------------------------------------------------------------*/

package tictactoe.java;

import java.util.ArrayList;
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
        int totalTurns = 0,
                totalGames = 0,
                result = 0,
                row, col;

        String name, symbol, move, choice;
        char user1Symbol, user2Symbol;

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

                    move = getUserMove(board);
                    row = Integer.parseInt(move.substring(0,1)) - 1;
                }
                else {
                    System.out.println("\n" + user2.getName() + ", it's your turn. [example usage: 1Bc, 4Aa]");

                    move = getUserMove(board);
                    row = Integer.parseInt(move.substring(0,1)) - 1;
                }
                if (checkForWin(board)) {
                    break;
                }
                totalTurns++;
            }
            if (checkForWin(board)) {
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

    private static boolean checkForWin(char[][][] board) {
        ArrayList<String> possibleWins = new ArrayList<>();

        boolean gameWon = false;
        for (String line : possibleWins) {
            if (line.equals("XXXX") || line.equals("OOOO"))
            {
                gameWon = true;
                lastWinner = line.charAt(0) == user2.getSymbol() ? user2 : user1;
                break;
            }
        }
        return gameWon;
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
        int row = Integer.parseInt(move.substring(0,1)) - 1;
        int col = 0;
        int height = 0;

        switch (move.charAt(1)) {
            case 'a':
                col = 0;
                break;
            case 'b':
                col = 1;
                break;
            case 'c':
                col = 2;
                break;
            case 'd':
                col = 3;
                break;
        }

        switch (move.charAt(2)) {
            case 'A':
                height = 0;
                break;
            case 'B':
                height = 1;
                break;
            case 'C':
                height = 2;
                break;
            case 'D':
                height = 3;
                break;
        }

        return board[row][col][height] == ' ';
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
