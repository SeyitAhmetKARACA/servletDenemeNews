package com.karaca.web.jdbc;

import java.sql.Date;

public class comment {
	private int id;
	private int newsId;
	private String comment;
	private Date date;
	private String autor;
	
	public comment(int _newsId,String _autor,String _comment,Date _date) {
		newsId = _newsId;
		comment = _comment;
		date = _date;
		autor = _autor;
	}
	
	public comment(int _id,int _newsId,String _autor,String _comment,Date _date) {
		newsId = _newsId;
		comment = _comment;
		date = _date;
		id = _id;
		autor = _autor;
	}

	public int getid() {
		return id;
	}

	/* autor */
	public String getautor() {
		return autor;
	}

	public void setautor(String _autor) {
		autor = _autor;
	}	
	
	
	/* haber id (fk) */
	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int _newsId) {
		newsId = _newsId;
	}

	/* comment */
	public String getComment() {
		return comment;
	}

	public void setComment(String _comment) {
		comment = _comment;
	}

	/* date */
	public Date getDate() {
		return date;
	}

	public void setDate(Date _date) {
		date = _date;
	}
	
	
}
