package com.ll.learn.hibernate.util;

public class TestAB {

	public static void main(String[] args){
//		B b1 = new B("b1");
//		B b2 = new B("b2");
		
		//静态变量只会实例化一次
		
		Thread t1 = new Thread(){

			@Override
			public void run() {
				B b1 = new B("b1");
			}
			
		};
		t1.start();
		
		Thread t2 = new Thread(){

			@Override
			public void run() {
				B b = new B("b2");
			}
			
		};
		t2.start();
		
	}
	
}
