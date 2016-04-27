/**
 * 
 */
package com.cetc.test.junit;
/**
 * @author ZTB
 *
 */
public class Square {
	
	 private static int result; // 静态变量，用于存储运行结果

	    public void square(int n) {
	        result = n * n;
	    }
	        
	    public void clear() {     // 将结果清零
	        result = 0;
	    }
	    
	    public int getResult() {
	        return result;
	    }
	    
		/**
		 * 
		 */
		public Square() {
			// TODO Auto-generated constructor stub
		}

		/**
		 * @param args
		 */
		public static void main(String[] args) {
			// TODO Auto-generated method stub

		}

}