package com.ll.learn.hibernate.util;

abstract class C1{
	public C1(){
		System.out.println("1");
	}
	
	public C1(String c1){
		System.out.println(c1);
	}
}

class C2 extends C1{
	public C2(){
		System.out.println("2");
	}
	
	public C2(String c2){
		System.out.println(c2);
	}
}

class C3 extends C2{
	public C3(){
		System.out.println("3");
	}
	
	public C3(String c3){
		super(c3);
		System.out.println(c3);
	}
	
}

/**
 * super  就是指用父类的那个类    不写就是没有参数的构造方法
 * @author Administrator
 *
 */
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		new C3();
		new C3("c3");
	}

}
