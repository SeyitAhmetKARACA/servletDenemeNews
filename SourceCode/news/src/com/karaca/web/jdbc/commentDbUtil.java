package com.karaca.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class commentDbUtil {
	private DataSource ds;
	
	public commentDbUtil(DataSource _dataSource) {
		ds = _dataSource;
	}

	public void deleteComment(int cid,String w) throws SQLException {
		Connection Conn = null;
		PreparedStatement myStat = null;
		
		if(w.toLowerCase().equals("news"))
			w = "haber_id";
		else
			w = "id";
		
		try {
			Conn = ds.getConnection();
			
			String sqlQuery = "delete from tbl_comment " + 
							  "where "+w+"=?";
			
			System.out.println(cid+"  >"+cid+"<");
			myStat = Conn.prepareStatement(sqlQuery);
			myStat.setInt(1, cid);
			
			myStat.execute();
			
		}finally {
			if(Conn != null)
				Conn.close();
			
			if(myStat != null)
				myStat.close();			
		}
		
	}
	
	
	public List<comment> getComment(int newsId) throws SQLException{
		List<comment> commentList = new ArrayList<comment>();
		comment _comment = null;
		
		Connection myConn = null;
		PreparedStatement myStat = null;
		ResultSet myRes = null;
		
		try {
			myConn = ds.getConnection();
			
			String sqlQuery = "select tbl_comment.id,tbl_comment.autor,tbl_comment.comment,tbl_comment.date FROM tbl_comment inner join tbl_news on tbl_comment.haber_id = tbl_news.id " + 
					"where tbl_news.id=?";
			
			myStat = myConn.prepareStatement(sqlQuery);
			
			myStat.setInt(1, newsId);
			
			myRes = myStat.executeQuery();
			
			while(myRes.next()) {
				_comment = new comment(myRes.getInt(1),
										newsId,
										myRes.getString(2),
										myRes.getString(3),
										myRes.getDate(4)
										);
				commentList.add(_comment);
			}		
		}finally {
			if(myConn != null)
				myConn.close();
			
			if(myStat != null)
				myStat.close();
		
			if(myRes != null)
				myRes.close();
		}
		
		return commentList;
	}
	
	public void addComment(comment _comment) throws SQLException {
		Connection Conn = null;
		PreparedStatement myStat = null;
		
		try {
			Conn = ds.getConnection();
			
			String sqlQuery = "insert into tbl_comment(autor, haber_id,comment,date) "+
							  "values ( ? , ? , ? ,? )";
			
			myStat = Conn.prepareStatement(sqlQuery);
			
			myStat.setString(1, _comment.getautor());
			myStat.setInt(2, _comment.getNewsId());
			myStat.setString(3, _comment.getComment());
			myStat.setDate(4, _comment.getDate());
			
			myStat.execute();

		}finally {
			if(Conn != null)
				Conn.close();
			
			if(myStat != null)
				myStat.close();
		}		
	}
}
