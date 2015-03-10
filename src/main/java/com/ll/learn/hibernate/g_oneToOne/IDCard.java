package com.ll.learn.hibernate.g_oneToOne;

/**
 * 
 * @author Administrator
 *
 */
public class IDCard {

	private Integer id;
	
	private String cardNo;
	
	private Person person;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "[IDCard id="+id+", idCard="+cardNo+", person="+person.toString()+"]";
	}
	
}
