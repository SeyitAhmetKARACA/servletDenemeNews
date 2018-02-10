package com.karaca.web.jdbc;

import java.sql.Date;

public class News {
	private int id;
	private String header;
	private String content;
	private Date date;
	
	public News(int _id,String _header ,String _content, Date _date) {
		id = _id;
		header = _header;
		content = _content;
		date = _date;
	}
	
	public News(String _header,String _content, Date _date) {
		header = _header;
		content = _content;
		date = _date;
	}
	
	/*  ID */
	public void setid(int _id) {
		id = _id;
	}
	
	public int getid() {
		return id;
	}
	
	/* Content */
	public void setcontent(String _content) {
		content = _content;
	}
	
	public String getcontent() {
		return content;
	}
	
	/* Date */
	public void setdate(Date _date) {
		date = _date;
	}
	
	public Date getdate() {
		return date;
	}
	
	/* header */
	public void setheader(String _header) {
		header = _header;
	}
	
	public String getheader() {
		return header;
	}
}
