package tictactoe.java;

import java.util.function.IntPredicate;

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
    boolean test(int x, int y, int z);
    default TriIntPredicate negate() { return (x, y, z) -> !test(x, y, z); }
}
