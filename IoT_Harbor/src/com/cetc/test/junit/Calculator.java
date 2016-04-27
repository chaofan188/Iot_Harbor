/**
 * 
 */
package com.cetc.test.junit;

/**
 * @author ZTB
 *
 */
public class Calculator {

    private static int result; // 静态变量，用于存储运行结果
    
    public void add(int n, int m) {
        result = m + n; //隐含的warning是：n,m通常是用边界的
    }
    
    public void substract(int n, int m) {
    	if(n>=m)
    	{
    		result = n - m;  //Bug1: 判断n,m之间的关系 
    	}
    	else
    	{
    		System.out.println("exeption for 被减数小于减数");
    		result = -1; //举例：-1 代表errcode
    	}
    		
    }
    
    public void multiply(int n) {
    	 // 此方法尚未写好 
    } 
    
   
    public void divide(int m, int n) {
      System.out.println(".divide()...");   
      result = m / n; //Bug2: 没做异常处理   	
         
   /*if(n!=0){
    	result = m / n; //Bug2: 没做异常处理
        }
        else
        {
    		System.out.println("exeption for 除数为0 ");
    		result = -2; //举例：-2 代表errcode        	
        }*/
     }
    
    public void squareRoot(int n) {
        for (; ;) ;            //Bug3 : 死循环
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
	public Calculator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}





