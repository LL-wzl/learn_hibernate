package com.ll.learn.hibernate.g_oneToOne;

/**
 * 基于外键
 * @author Administrator
 *
 */
public class Person {

	private Integer id;
	
	private String name;
	
	private IDCard idCard;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IDCard getIdCard() {
		return idCard;
	}

	public void setIdCard(IDCard idCard) {
		this.idCard = idCard;
	}

	@Override
	public String toString() {
		return "[Person id="+id+", name="+name+", idCard="+idCard+"]";
	}
	
}
