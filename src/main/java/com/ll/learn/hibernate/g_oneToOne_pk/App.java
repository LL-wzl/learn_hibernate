package com.ll.learn.hibernate.g_oneToOne_pk;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;


public class App {

	private static SessionFactory sessionFactory = null;
	
	static{
		sessionFactory = new Configuration()
			.configure()
			.addClass(Person.class)
			.addClass(IDCard.class)
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
			
			//不会添加Crad中值
//			Person p = new Person();
//			p.setName("杭珊");
//			
//			IDCard card = new IDCard();
//			card.setCardNo("123");
//			
//			p.setIdCard(card);
//			
//			session.save(p);
			
//			IDCard c = new IDCard();
//			c.setCardNo("456");
//			
//			Person person = new Person();
//			person.setName("王五");
//			c.setPerson(person);	
//			
//			session.save(c);
			
			Person person = new Person();
			person.setName("猪八");
			session.save(person);
			
			IDCard idCard = new IDCard();
			idCard.setCardNo("890");
			idCard.setPerson(person);
			session.save(idCard);
			
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
	
	@Test
	public void testFind(){
		Session session = null;
		
		try{
			session = sessionFactory.openSession();
			session.getTransaction().begin();
			
			
			
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
	
	@Test
	public void testRemoveRelation(){
		Session session = null;
		
		try{
			session = sessionFactory.openSession();
			session.getTransaction().begin();
			
			
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
	
	@Test
	public void testDelete(){
		Session session = null;
		
		try{
			session = sessionFactory.openSession();
			session.getTransaction().begin();
			
			
			
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
