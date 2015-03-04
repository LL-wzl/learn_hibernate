package com.ll.learn.hibernate.c_set;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class Member implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5647853797211178840L;

	private Integer id;
	
	private String name;
	
	private String phone;
	
	private Set<String> addressSet;
	
	private List<String> addressList;
	
	private String[] addressArray;
	
	private Map<String, String> addressMap;
	
	private List<String> addressBag;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<String> getAddressSet() {
		return addressSet;
	}

	public void setAddressSet(Set<String> addressSet) {
		this.addressSet = addressSet;
	}

	public List<String> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;
	}

	public String[] getAddressArray() {
		return addressArray;
	}

	public void setAddressArray(String[] addressArray) {
		this.addressArray = addressArray;
	}

	public Map<String, String> getAddressMap() {
		return addressMap;
	}

	public void setAddressMap(Map<String, String> addressMap) {
		this.addressMap = addressMap;
	}

	public List<String> getAddressBag() {
		return addressBag;
	}

	public void setAddressBag(List<String> addressBag) {
		this.addressBag = addressBag;
	}
	
}
