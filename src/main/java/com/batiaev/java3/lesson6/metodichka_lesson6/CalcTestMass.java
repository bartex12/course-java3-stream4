package com.batiaev.java3.lesson6.metodichka_lesson6;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class CalcTestMass {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {3, 1, 4},
                {5, 3, 8},
                {0, 0, 0},
                {1, -5, -4},
                {-5, -2, -7}
        });

    }

    private int a, b, c;
    public CalcTestMass(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private Calculator calculator;

    @Before
    public void startTest() {
        calculator = new Calculator();
    }

    @AfterClass
    public static void endTest() {
        System.out.println("end");
    }

    @Test
    public  void testAddMass(){
        Assert.assertEquals(c, calculator.add(a,b) );
    }
}

