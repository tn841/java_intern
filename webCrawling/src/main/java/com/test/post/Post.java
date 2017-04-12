package com.test.post;

import java.util.Date;

public class Post {
	private int p_no;
	private String p_title;
	private String p_content;
	private String p_writer;
	private Date p_date;
	
	public Post() {
		super();
	}

	public Post(String p_title, String p_content, String p_writer, Date p_date) {
		super();
		this.p_title = p_title;
		this.p_content = p_content;
		this.p_writer = p_writer;
		this.p_date = p_date;
	}

	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

	public String getP_title() {
		return p_title;
	}

	public void setP_title(String p_title) {
		this.p_title = p_title;
	}

	public String getP_content() {
		return p_content;
	}

	public void setP_content(String p_content) {
		this.p_content = p_content;
	}

	public String getP_writer() {
		return p_writer;
	}

	public void setP_writer(String p_writer) {
		this.p_writer = p_writer;
	}

	public Date getP_date() {
		return p_date;
	}

	public void setP_date(Date p_date) {
		this.p_date = p_date;
	}
	
	
	
	
	
	
}
