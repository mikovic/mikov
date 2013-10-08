package org.cassiopeya;


/**
 * Created with IntelliJ IDEA.
 * User: Masha
 * Date: 09.09.13
 * Time: 14:29
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    private final SubClass subClass;

    public Test(final SubClass subClass) {
        this.subClass = subClass;
    }

    public boolean isTested(int count) {
        final int max = subClass.getMax();
        final int result = max + count;
        return (result > 100) == subClass.getCheck();
    }
}
