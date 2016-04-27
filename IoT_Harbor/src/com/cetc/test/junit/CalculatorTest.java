/**
 * 
 */
package com.cetc.test.junit;

import static org.junit.Assert.*;
import  org.junit.runners.Parameterized;
import  org.junit.runners.Parameterized.Parameters;
import  org.junit.internal.runners.TestClassRunner;
import  org.junit.runner.RunWith;
import  org.junit.Before;
import  org.junit.Test;
import  org.junit.*;
import  java.util.Arrays;
import  java.util.Collection;
/**
 * @author ZTB
 *
 */
/*@RunWith(Parameterized. class )*/
public class CalculatorTest {

 //定义成员变量，i为测试参数，j为测试结果  
    int expected = 0; 
    int actual = 0;  
    private int param;
    private int result;
           
    /*  //构造函数  
    public CalculatorTest(int i, int j) {  
        //super();  
        this.i = i;  
        this.j = j;  
    }  */
  
/*   @Parameters  
	public static Collection data() {  
	        return Arrays.asList(
	        		new Object[][]{
	        				{2,4},
	        				{3,9}
	        				});  
	    } 
    */
	private static Calculator calculator = new Calculator();
	
	/*Tip1:
	 * 给这些测试函数设定一个执行时间，超过了这个时间，他们就会被系统强行终止，
	 * 并且系统还会向你汇报该函数结束的原因是因为超时，这样你就可以发现这些 Bug.
	 * 要实现这一功能，只需要给 @Test 标注加
	 * */
	@Test(timeout  =   1000 )

	
	/**
	 * @throws java.lang.Exception
	 */
	
	/*Tip2:
	 * 我们非常希望每一个测试都是独立的，相互之间没有任何耦合度。
     *很有必要在执行每一个测试之前，对Calculator对象进行一个“复原”操作，以消除其他测试造成的影响。
     *因此，“在任何一个测试执行之前必须执行的代码”就是一个Fixture，用@Before来标注它，	 * 
	 * */
	@Before
	public void setUp() throws Exception {
		 calculator.clear();
	}

	/**
	 * Test method for {@link com.cetc.test.junitexample.Calculator#add(int, int)}.
	 */
	@Test
	public final void testAdd() {
        calculator.add(2,3);
        assertEquals(5, calculator.getResult());
	}

	/**
	 * Test method for {@link com.cetc.test.junitexample.Calculator#divide(int, int)}.
	 */
	/*tip4:
	 * JAVA 中的异常处理也是一个重点
	 * 一个函数应该抛出异常，但是它没抛出
	 * */
/**/	
/*	@Test(expected  =  ArithmeticException. class )*/
/*	@Test*/
	@Test
	public void testDivide() {
       System.out.println(".testDivide()..."); 
	   calculator.divide(10, 2);
       assertEquals(5, calculator.getResult());
       try { 
              calculator.divide(10, 0); 
        } catch (Exception e) { 
            Assert.fail("测试失败"); //不应该执行这段. 
        } 
        assertEquals(-2, calculator.getResult());
	}
	/**
	 * Test method for {@link com.cetc.test.junitexample.Calculator#substract(int, int)}.
	 */
	@Test
	public final void testSubstract() {
        calculator.substract(10,2);
        assertEquals(8, calculator.getResult());
   
        calculator.substract(2,10);
        assertEquals(-1, calculator.getResult());
	}


	/**
	 * Test method for {@link com.cetc.test.junitexample.Calculator#squareRoot(int)}.
	 */
	@Test
	@Ignore
	public final void testSquareRoot() {
		fail("Not yet implemented"); // TODO
	}
	
	/*Tip3:
	 * 那就是在这种测试函数的前面加上@Ignore标注，
	 * 这个标注的含义就是“某些方法尚未完成，暂不参与此次测试”。
	 * 这样的话测试结果就会提示你有几个测试被忽略，而不是失败。
	 * 一旦你完成了相应函数，只需要把@Ignore标注删去，就可以进行正常的测试。
	 * 	 * */
	/**
	 * Test method for {@link com.cetc.test.junitexample.Calculator#multiply(int)}.
	 */
	@Test
	@Ignore
	public final void testMultiply() {
		fail("Not yet implemented"); // TODO
	}




}
