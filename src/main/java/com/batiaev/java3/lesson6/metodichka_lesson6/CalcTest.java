package com.batiaev.java3.lesson6.metodichka_lesson6;

import org.junit.*;

public class CalcTest {

    private Calculator calculator;

    @Before
    public void startTest(){
        calculator = new Calculator();
    }

    @Test
    public void testAdd() {
        Assert.assertEquals(4, calculator.add(2, 2));
    }

    @Test
  @Ignore("Деление пока тестировать не нужно")
    public void testSub() {
        Assert.assertEquals(3, calculator.sub(5, 2));
    }

    @Test
    public void testMul() {
        Assert.assertEquals(9, calculator.mul(3, 3));
    }

    @Test
    public void testDiv() {
        Assert.assertEquals(1, calculator.div(2, 2));
    }

  @AfterClass
    public static void endTest(){
      System.out.println("end");
  }

}
