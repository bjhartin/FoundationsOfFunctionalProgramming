package org.studentloan.fp;

import org.junit.Test;

public class ExpressionsTest {
    @Test
    public void testExpression(){
        System.out.println("tryToReturnAStatement() = " + tryToReturnAStatement());
        assert(1 == 1);
    }

    private int tryToReturnAStatement() {
        return (int x = 1);
    }
}
