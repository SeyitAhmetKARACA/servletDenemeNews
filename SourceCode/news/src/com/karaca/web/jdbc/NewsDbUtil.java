package com.karaca.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class NewsDbUtil {
	private DataSource dataSource;
	
	public NewsDbUtil(DataSource _dataSource) {
		dataSource = _dataSource;
	}
	
	public List<News> getNews() throws SQLException{
		List<News> nl = new ArrayList<News>();
		News n = null;

		Connection myConn = null;
		Statement myStat = null;
		ResultSet myRes = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sqlQuery = "select * from tbl_news";

			myStat = myConn.createStatement();
			
			myRes = myStat.executeQuery(sqlQuery);
			
			while(myRes.next()) {
				n = new News(myRes.getInt(1),
								myRes.getString(2),
								myRes.getString(3),
								myRes.getDate(4)
							);
				nl.add(n);
			}
		}
		finally {
			close(myConn,myStat,myRes);
		}	
	
		return nl;
	}
	
	public News getNews(int _id) throws SQLException {
		News n = null;

		Connection myConn = null;
		PreparedStatement myStat = null;
		ResultSet myRes = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sqlQuery = "select * from tbl_news where id=? ";

			myStat = myConn.prepareStatement(sqlQuery);
			
			myStat.setInt(1, _id);
			
			myRes = myStat.executeQuery();

			while(myRes.next()) {
				n = new News(myRes.getInt(1),
								myRes.getString(2),
								myRes.getString(3),
								myRes.getDate(4)
							);
			}
		}
		finally {
			close(myConn,myStat,myRes);
		}	
	
		return n;
	}
	
	
	public void addNews(News _news) throws SQLException {
		Connection Conn = null;
		PreparedStatement myStat = null;
		
		try {
			Conn = dataSource.getConnection();
			
			String sqlQuery = "insert into tbl_news(header,content,date)"+
							  "values (? , ? , ?)";

			myStat = Conn.prepareStatement(sqlQuery);
			myStat.setString(1, _news.getheader());
			myStat.setString(2, _news.getcontent());
			myStat.setDate(3,_news.getdate());
			System.out.println(_news.getcontent()+"<<<<<<<==============");
			myStat.execute();
			
		}finally {
			close(Conn,myStat,null);
		}
		
	}
	
	
	private void close(Connection c , Statement s,ResultSet r) throws SQLException {
		try {
		if(c != null)
			c.close();
		
		if(s != null)
			s.close();
		
		if(r != null)
			r.close();
		}catch(SQLException e) {
			throw new SQLException(e);
		}
	}

	public News loadNews(int nid) throws SQLException {
		Connection Conn = null;
		PreparedStatement myStat = null;
		ResultSet myRes = null;
		News news = null;
		
		try {
			Conn = dataSource.getConnection();
			
			String sqlQuery = "select * from tbl_news " + 
							  "where id=?";
			
			myStat = Conn.prepareStatement(sqlQuery);
			myStat.setInt(1, nid);
			
			myRes = myStat.executeQuery();
			
			if(myRes.next()) {
				news = new News(myRes.getInt(1),
								myRes.getString(2),
								myRes.getString(3),
								myRes.getDate(4)
						);
			}
			
		}finally {
			if(Conn != null)
				Conn.close();
			
			if(myStat != null)
				myStat.close();			
		}
		
		return news;
	}

	public void updateNews(News news) throws SQLException {
		Connection Conn = null;
		PreparedStatement myStat = null;
		
		try {
			Conn = dataSource.getConnection();
			
			String sqlQuery = "update dbnews.tbl_news " + 
					"set tbl_news.header = ? , tbl_news.content=? , tbl_news.date=? " + 
					"where tbl_news.id = ?";
			
			myStat = Conn.prepareStatement(sqlQuery);
			myStat.setString(1, news.getheader());
			myStat.setString(2, news.getcontent());
			myStat.setDate(3, news.getdate());
			myStat.setInt(4, news.getid());
			
			myStat.execute();
			
			
		}finally {
			if(Conn != null)
				Conn.close();
			
			if(myStat != null)
				myStat.close();			
		}
	}

	public void deleteNews(int nid) throws SQLException {
		Connection Conn = null;
		PreparedStatement myStat = null;
		
		try {
			Conn = dataSource.getConnection();
			
			String sqlQuery = "delete from tbl_news " + 
							  "where id=?";
			
			myStat = Conn.prepareStatement(sqlQuery);
			myStat.setInt(1, nid);
			
			myStat.execute();
			
		}finally {
			close(Conn,myStat,null);
		}
	}
	
}
