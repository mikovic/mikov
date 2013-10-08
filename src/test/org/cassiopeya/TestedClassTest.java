package org.cassiopeya;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static javafx.beans.binding.Bindings.lessThan;
import static javafx.beans.binding.Bindings.lessThanOrEqual;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.junit.Assume.*;


@RunWith(Theories.class)

public class TestedClassTest {
    boolean result;
    Test test;
    SubClass mockSubClass;
    @DataPoints
    public static int[] ints = {50,40,30,100};


    @Before
    public void setup() throws Exception{
        mockSubClass = mock(SubClass.class);
        test = new Test(mockSubClass);
        when(mockSubClass.getMax()).thenReturn(50);

    }




    @Theory
    public void TestedMethodIsTested(int number) throws Exception{


        if (number<51){
            when(mockSubClass.getCheck()).thenReturn(false, true);


        }else {
            when(mockSubClass.getCheck()).thenReturn(true, false);
        }
        result = test.isTested(number);
        assertTrue(result);
        result = test.isTested(number);
        assertFalse(result);



    }
}
/*public class TestedClassTest {
    SubClass mockSubClass;
    Test test;
    boolean result;
    @Before
    public void setup() throws Exception{
    mockSubClass = mock(SubClass.class);
    test = new Test(mockSubClass);
    when(mockSubClass.getMax()).thenReturn(50);
    when(mockSubClass.getCheck()).thenReturn(false,true,false,true);
    }
    @org.junit.Test
    public void TestedMethodIsTested() throws Exception{
    result = test.isTested(50);
        assertTrue(result);
        result = test.isTested(50);
        assertFalse(result);
        result = test.isTested(100);
        assertFalse(result);
        result = test.isTested(100);
        assertTrue(result);
    }

}  */
