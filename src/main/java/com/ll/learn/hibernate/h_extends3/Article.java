package com.ll.learn.hibernate.h_extends3;

import java.util.Date;

/**
 * 贴吧公共属性
 * @author Administrator
 *
 */
public abstract class Article {

	private String id;
	
	private String title;
	
	private String content;
	
	private Date postDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "method()=====>>" + title;
	}
	
	
}
