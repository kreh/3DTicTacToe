/*-----------------------------------------------------------------------------------------------
Program: TicTacToe.java - this is the user class for human participants' imputs in the game of tic tac toe
Author: Andrilla Rahman
Date: December 14, 2018
 ------------------------------------------------------------------------------------------*/

package tictactoe.java;

public class User
{

     private String name;

     private char symbol;

     public User() {}

     public User(String name, char symbol)
     {

          this.name = name;
          this.symbol = symbol;
     }

     public String getName()
     {
          return name;
     }

     public char getSymbol()
     {
          return symbol;
     }

     public void setName(String name)
     {
          this.name = name;
     }

     public void setSymbol(char symbol)
     {
          this.symbol = symbol;
     }

     @Override
     public String toString()
     {
          return "Name=" + name + "\nSymbol: " + symbol;
     }

}

