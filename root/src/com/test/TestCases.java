package com.test;

import java.util.*;

import org.junit.Test;


public class TestCases {
	/**
	 * 1.用1,2,2,3,4,5六个数字，组成一个6位数。
	 * 要求： 第三位数不能是2;
	 * 3和5不能相邻。
	 * 编写java程序显示所有的数字。
	 * 123245
	 */
	@Test
	public void test02() {
		int[] a1 = { 1, 2, 2, 3, 4, 5 };
		int[] a2 = { 1, 2, 2, 3, 4, 5 };
		int[] a3 = { 1, 2, 2, 3, 4, 5 };
		int[] a4 = { 1, 2, 2, 3, 4, 5 };
		int[] a5 = { 1, 2, 2, 3, 4, 5 };
		int[] a6 = { 1, 2, 2, 3, 4, 5 };
		Set<String> set = new TreeSet<String>();
		/**
		 * buheshide : r3==2的
		 * 35 相邻的 用正则表达式解决
		 */
		for (int r1 : a1) {
			System.out.print("\nr1:"+ r1);
			String str = "";
			str = r1+"";
			for (int r2 : a2) {
				if(str.length()>2){
				str = str.substring(0, 1);
				}
				str += r2;
				for (int r3 : a3) {
					if (r3 == 2) {
						continue;
					}
					if(str.length()>3){
						str = str.substring(0, 2);
					}
					str += r3;
					for (int r4 : a4) {
						if(str.length()>4){
							str = str.substring(0, 3);
						}
						str += r4;
						for (int r5 : a5) {
							if(str.length()>5){
								str = str.substring(0, 4);
							}
							str += r5;
							for (int r6 : a6) {
								if(str.length()>=5){
									str = str.substring(0, 5);
								}
								str += r6;
								// 判断 -- 3和5 相邻的
								if(		!str.matches("[1-5]*[3][5][1-5]*")
									  &&!str.matches("[1-5]*[5][3][1-5]*")
									  &&str.matches("([0-9]*[1][0-9]*){1}")
									  &&str.matches("([0-9]*[2][0-9]*){2}")
									  &&str.matches("([0-9]*[3][0-9]*){1}")
									  &&str.matches("([0-9]*[4][0-9]*){1}")
									  &&str.matches("([0-9]*[5][0-9]*){1}")
									  ){
									System.out.print("\t"+str);
									set.add(str);
								}

							}
						}
					}
				}
			}
		}
		System.out.println("去重复后数据：");
		System.out.println(set.size());
		System.out.println(set);
		
	}
	
	public void sayHe(){
		System.out.println("hahahahHHHHH");
	}
}	
