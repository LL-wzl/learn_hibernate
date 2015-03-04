package com.ll.learn.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.ll.learn.hibernate.c_set.Member;

public class App {

	private static SessionFactory sessionFactory = null;
	
	static{
		sessionFactory = new Configuration()
			.configure()
			.addClass(Member.class)
			.buildSessionFactory();
	}
	
	/**
	 * 测试set集合
	 */
	@Test
	public void testSave(){
		Session session = null;
		
		try{
			session = sessionFactory.openSession();
			session.getTransaction().begin();
			
			Member member = new Member();
			member.setName("第一个会员");
			member.setPhone("12345678901");
			//关系中若设置了 order-by 属性   就要使用SortedSet
			Set<String> addressSet = new TreeSet<String>();
			addressSet.add("屌丝岑村");
			addressSet.add("公司江南大道中");
			member.setAddressSet(addressSet);
			
			//list集合
			List<String> addressList = new ArrayList<String>();
			addressList.add("屌丝岑村1");
			addressList.add("屌丝岑村2");
			member.setAddressList(addressList);
			
			//array集合
			String[] addressStrs = new String[2];
			addressStrs[0] = "屌丝岑村1";
			addressStrs[1] = "屌丝岑村2";
			member.setAddressArray(addressStrs);
			
			//map集合
			Map<String, String> addressMap = new HashMap<String, String>();
			addressMap.put("家庭", "屌丝岑村");
			addressMap.put("公司", "江南大道中");
			member.setAddressMap(addressMap);
			
			//
			List<String> addressBag = new ArrayList<String>();
			addressBag.add("屌丝岑村1");
			addressBag.add("屌丝岑村2");
			member.setAddressBag(addressBag);
			
			
			session.save(member);
			
			session.getTransaction().commit();
			
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			if(session != null){
				session.close();
			}
		}
		
		
		
	}
	
}
