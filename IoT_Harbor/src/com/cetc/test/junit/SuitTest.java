package com.cetc.test.junit;

 import  org.junit.runner.RunWith;
 import  org.junit.runners.Suite;
 
 @RunWith(Suite.class )
 @Suite.SuiteClasses( {
         CalculatorTest.class ,
         SquareTest.class,
         OnvifCamTest.class,
         VEServiceImplTest.class
         } )
 public   class  SuitTest  {
	 
	 
 } 