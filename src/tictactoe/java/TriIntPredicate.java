package tictactoe.java;

/**
 * Title: COMP 1230 Assignment X:
 * File: TriIntPredicate.java
 * Description:
 *
 * @author Dave Aldrich
 * Student ID: T00593238
 * Date: December 14, 2018
 */
@FunctionalInterface
public interface TriIntPredicate
{
    boolean apply(int x, int y, int z);
    default TriIntPredicate negate() { return (x, y, z) -> !apply(x, y, z); }
}
