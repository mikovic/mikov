package org.cassiopeya;

/**
 * Created with IntelliJ IDEA.
 * User: Masha
 * Date: 09.09.13
 * Time: 14:30
 * To change this template use File | Settings | File Templates.
 */
public class SubClass {
    private final int max;
    private final boolean check;

    public SubClass(final int max, final boolean check) {
        this.max = max;
        this.check = check;
    }

    public int getMax() {
        return max;
    }

    public boolean getCheck() {
        return check;
    }
}
