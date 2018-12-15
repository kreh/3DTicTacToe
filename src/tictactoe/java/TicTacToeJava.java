/*-----------------------------------------------------------------------------------------------
Program: TicTacToe.java - this program lets two human player play tic tac toe against each other
Author: Andrilla Rahman
Date: December 14, 2018
 ------------------------------------------------------------------------------------------*/

package tictactoe.java;
import java.util.Scanner; // use Scanner class for console input

public class TicTacToeJava
{
    public static Scanner input = new Scanner(System.in);  // attach to System.in

public static void main(String[] args)
{
    char [][]board=new char [3][3];
    int totalTurn=0,totalGames=0,result=0,row,col;

    String name,symbol,move,choice;
    char user1Symbol,user2Symbol;

    User user1 = new User();
    User user2 = new User();

    System.out.print("Enter User 1 name: ");
    name=input.nextLine();

    System.out.print("Please select your symbol (O or X): ");
    symbol = input.nextLine().toUpperCase();

    user1Symbol = symbol.charAt(0);
    user1.setName(name);
    user1.setSymbol(user1Symbol);

    System.out.print("Enter User 2 name: ");
    name = input.nextLine();
    System.out.println("\n");

    if(user1Symbol=='O')
        user2Symbol='X';
    else
        user2Symbol='O';

     user2.setName(name);
     user2.setSymbol(user2Symbol);

  while(true)
 {
  totalTurn = 0;
  totalGames++;

  for(int i=0;i<3;i++)
  for(int j=0;j<3;j++)
  board[i][j]=' ';

   printBoard(board);

  System.out.println("\nPlayer 1: "+user1.getName()+": "+user1.getSymbol()+"s");
  System.out.println("Player 2: "+user2.getName()+": "+user2.getSymbol()+"s\n");

    while(totalTurn<9)
    {
        if(totalTurn%2==0)
        {
         System.out.println("\n"+user1.getName()+", it's your turn.((RowCol) \"Please use lower case letters\")");

         move=getUserMove(board);
         row=Integer.parseInt(move.substring(0, 1))-1;
            
            if(move.charAt(1)=='a')
               col=0;

                else if(move.charAt(1)=='b')
                   col=1;

            else
             col=2;

             result=makePlay(board,row,col,user1.getSymbol());
             printBoard(board);

            if(result==1)
            {
             System.out.println("\nHurray!!!"+user1.getName()+" you win.");

             user1.userWinIncrement();

             break;
            }
        }

        else
        {
         System.out.println("\n"+user2.getName()+", it's your turn.((RowCol) \"Please use lower case letters\")");

         move=getUserMove(board);
         row=Integer.parseInt(move.substring(0, 1))-1;

            if(move.charAt(1)=='a')
                col=0;

                else if(move.charAt(1)=='b')
                   col=1;

            else
                col=2;

                result=makePlay(board,row,col,user2.getSymbol());
                printBoard(board);

            if(result==1)
            {
             System.out.println("\nHurray!!!" +user2.getName()+" you win.\n");

             user2.userWinIncrement();

             break;
            }
        }

        totalTurn++;
    }
    if(totalTurn==9)
    {
        System.out.println("\n\nGAME DRAW!!!\n\n");
    }

if(totalTurn==1)

    System.out.println("\n\nGame Stastatics:");

   System.out.println("Total Gmaes: "+totalGames);

    System.out.println(user1.getName()+"'s total wins: "+user1.getNoOfWins());
    System.out.println(user2.getName()+"'s total wins: "+user2.getNoOfWins());

    System.out.print("\n\nWould you two like to play another game?(y/n): \n");

    choice=input.nextLine();

if("n".equalsIgnoreCase(choice))
    {
        System.out.println("\nGame over!!!");

        break;
    }
 }
}

public static int makePlay(char Board[][],int r,int c,char symbol)
{
 Board[r][c]=symbol;
 if(win(Board,r,c,symbol))
    return 1;

 else
    return 0;
}

public static boolean win(char Board[][],int row,int col,char symbol)
{
 if(checkRow(Board,row,symbol))
    return true;

    else if(checkCol(Board, col, symbol))
    return true;

 else return checkDia(Board, symbol);
}

public static boolean checkRow(char Board[][],int r,char symbol)
{
    return Board[r][0]==symbol && Board[r][1]==symbol && Board[r][2]==symbol;
}

public static boolean checkCol(char Board[][],int c,char symbol)
{
    return Board[0][c]==symbol && Board[1][c]==symbol && Board[2][c]==symbol;
}

public static boolean checkDia(char board[][],char symbol)
{
    if(board[0][0]==symbol && board[1][1]==symbol && board[2][2]==symbol)
    return true;

return board[0][2]==symbol && board[1][1]==symbol && board[2][0]==symbol;
}

static String getUserMove(char Board[][] )
{
    String move;
    while(true)
   {
        System.out.print("Enter your next move: ");
        move=input.nextLine();
        System.out.println("\n");

     if((move.charAt(0)=='1' || move.charAt(0)=='2' || move.charAt(0)=='3')&&(move.charAt(1)=='a'||move.charAt(1)=='b'||move.charAt(1)=='c'))
     {
        if(checkOnBoard(move,Board))
            return move;

        else
            System.out.println("That postion is already taken.\n");
     }

     else
        System.out.println("Invalid move. Try again please!\n");
   }
}

public static boolean checkOnBoard(String move,char Board[][])
{
int row=Integer.parseInt(move.substring(0, 1))-1;
int col;

if(move.charAt(1)=='a')
    col=0;

    else if(move.charAt(1)=='b')
    col=1;

else
    col=2;

return Board[row][col]==' ';
}

public static void printBoard(char Board[][])
{
  System.out.println("  A B C");

  for(int i=0;i<3;i++)
  {
    System.out.println("--------");
    System.out.print((i+1)+"|");

    for(int j=0;j<3;j++)
    {
        System.out.print(Board[i][j]+"|");
    }

    System.out.println();
  }
}
}
