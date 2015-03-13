package com.ll.learn.hibernate.h_extends3;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;


public class App {

	private static SessionFactory sessionFactory = null;
	
	static{
		sessionFactory = new Configuration()
			.configure()
			.addClass(Article.class)
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
			
//			Article article = new Article();
//			article.setTitle("Article--");
			
			Topic topic = new Topic();
			topic.setTitle("Topic--");
			
			Reply reply = new Reply();
			reply.setTitle("Reply--");
			
//			session.save(article);
			session.save(topic);
			session.save(reply);
			
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
			
			Article article = (Article) session.get(Article.class, "fbeaf3ad-07ec-4aa2-8168-f6d01eb8a488");
			System.out.println(article.toString());
			
//			Topic topic = (Topic) session.get(Topic.class, "fbeaf3ad-07ec-4aa2-8168-f6d01eb8a488");
//			System.out.println(topic.toString());
			
//			Reply reply = (Reply) session.get(Reply.class, "fbeaf3ad-07ec-4aa2-8168-f6d01eb8a488");
//			System.out.println(reply.toString());
			
			
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
