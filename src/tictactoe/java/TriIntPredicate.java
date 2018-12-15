/*-----------------------------------------------------------------------------------------------
Program: TriIntPredicatejava - this is to make typing easier than typing board[][][] == symbol
Author: Andrilla Rahman
Date: December 14, 2018
 ------------------------------------------------------------------------------------------*/

package tictactoe.java;


@FunctionalInterface
public interface TriIntPredicate
{
    boolean apply(int x, int y, int z);
    default TriIntPredicate negate() { return (x, y, z) -> !apply(x, y, z); }
}
