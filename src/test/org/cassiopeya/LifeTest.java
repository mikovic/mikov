package org.cassiopeya;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;




public class LifeTest {
    String expected;
    String result;
    Life life;
    @Before
    public void setup() throws Exception{
        life = new Life();

    }
    @Test
    public void testGetStrLen() throws Exception{
        expected = "marvell7";
        result = life.getStrLen("marvell");
        assertFalse(!expected.equals(result));
        assertNotSame(expected, result);
        assertEquals("Error",expected, result);


        result = life.getStrLen("");
        expected ="0";
        assertNotNull("result is  Null", result);

    }
}
