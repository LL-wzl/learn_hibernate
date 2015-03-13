package com.ll.learn.hibernate.h_extends1;

import java.util.Date;

/**
 * 贴吧公共属性
 * @author Administrator
 *
 */
public class Article {

	private Integer id;
	
	private String title;
	
	private String content;
	
	private Date postDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	
}
