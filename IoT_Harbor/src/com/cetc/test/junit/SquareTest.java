/**
 * 
 */
package com.cetc.test.junit;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author ZTB
 *
 */
@RunWith(Parameterized.class )
public class SquareTest {

    //定义成员变量，i为测试参数，j为测试结果  
    private int param;
    private int result;
    
 //构造函数  
    public SquareTest(int i, int j) {  
        super();  
        this.param = i;  
        this.result = j;  
    }
  
   @Parameters  
	public static Collection data() {  
	        return Arrays.asList(
	        		new Object[][]{
	        				{2,4},
	        				{3,9},
	        				{5,25}
	        				});  
	    } 
    
	private static Square square = new Square();
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		square.clear();
	}

	@Test
	public final void test() {
		square.square(param); 
	    assertEquals(result, square.getResult());
	}

	 @Test
	public void testParams()
	{
	        System.out.println(param);
	 }
	     
}
