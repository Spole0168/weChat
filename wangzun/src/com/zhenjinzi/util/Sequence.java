package com.zhenjinzi.util;

import java.util.Random;


/**
 * 
 * @author Brad
 *
 */
public class Sequence {
	/** 
     *   getPK,获得数据库使用的一个long型唯一主键 
     *   @return   long 
     */ 
   private   static   String[]   ls   =   new   String[3000]; 
   private   static   int   li   =   0; 
   
   public   synchronized   static   String   getPK(String suffix) 
   { 
           String   lo   =   getpk(suffix); 
           for   (int   i   =   0;   i   <   3000;   i++) 
           { 
                   String   lt   =   ls[i]; 
                   if   (lt   ==   lo) 
                   { 
                           lo   =   getPK(suffix); 
                           break; 
                   } 
           } 
           ls[li]   =   lo; 
           li++; 
           if   (li   ==   3000) 
           { 
                   li   =   0; 
           } 
           return   lo; 
   } 

   private   static   String   getpk(String suffix) 
   { 
           String   a   =   String.valueOf(System.currentTimeMillis()); 
           Random rad=new Random();
           String   d   = rad.nextInt(1000)+""; 
           return   suffix+a+d; 
   }
   
   
   public static void main(String[] args) throws InterruptedException{
		  
		   System.out.println(getPK(""));
		   System.out.println(System.currentTimeMillis());
		   Thread.sleep(10);
   }
   
}

