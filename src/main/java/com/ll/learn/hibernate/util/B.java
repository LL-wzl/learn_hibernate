package com.ll.learn.hibernate.util;

public class B {

	private static A  a = new A(); //静态局部变量只会实例化一次
	
	public B(String b){
		System.out.println(b);
	}
	
}
