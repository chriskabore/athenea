package com.beogotech.athenea.util;

import java.util.Date;

public class Notification {
	private String author;
	private String type;
	private Date date;
	
	public String getTimeAgo() {
		return timeAgo;
	}
	
	public void setTimeAgo(String timeAgo) {
		this.timeAgo = timeAgo;
	}
	
	private String timeAgo;
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Notification(String author, String type, Date date,String timeAgo){
		this.author = author;
		this.type = type;
		this.date = date;
		this.timeAgo = timeAgo;
	}
	
	
}
