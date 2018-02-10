package com.karaca.web.jdbc;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class newsController
 */
@WebServlet("/newsController")
public class newsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NewsDbUtil newsDb;
	commentDbUtil commentDb;
	
	@Resource(name="jdbc/dbnews")
	// database baðlantý için gerekli.
	private DataSource dataSource;
	
	private String modePath = "";
	
	@Override
	public void init() throws ServletException {
		super.init();
		newsDb = new NewsDbUtil(dataSource);
		commentDb = new commentDbUtil(dataSource);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		if(command == null)
			command = "list";	
		
		try{
			if(command.equals("list")) { // haberleri listeliyor
				listNews(request,response);
			}else if(command.equals("add")) { // yeni haber ekleme
				addNews(request,response);
			}else if(command.equals("addComment")) { // yorum ekleme
				addComment(request,response);
			}else if(command.equals("show")) { // haberin icerigini gosteriyor
				loadNews(request,response);
			}else if(command.equals("1")) { // yonetici moduna geciriyor
				modePath = "-admin";
				listNews(request,response); 
			}else if(command.equals("0")) { // normal moda geciriyor
				modePath = "";
				listNews(request,response); 
			}else if(command.equals("deleteComment")) { // yorum siliyor
				deleteComment(request,response);
			}else if(command.equals("load")) { // update icin haber icerigini dolduruyor
				load4UpdateNews(request,response);
			}else if(command.equals("update")) { // haberi guncelliyor
				updateNews(request,response);
			}else if(command.equals("delete")) { // haberi guncelliyor
				deleteNews(request,response);
			}else
				System.out.println("anything else .. ");
		
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void deleteNews(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String sNid = request.getParameter("nid");
		int nid = Integer.parseInt(sNid);
		
		commentDb.deleteComment(nid,"news");
		newsDb.deleteNews(nid);
		
		listNews(request,response);
	}

	private void updateNews(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String sNid = request.getParameter("nid");
		int nid = Integer.parseInt(sNid);
		String header = request.getParameter("newsHeader");
		String content = request.getParameter("newsContent");
		Date date = new Date(0);
		
		News news = new News(nid,header,content,date);
		
		newsDb.updateNews(news);
		
		listNews(request,response);
	}

	private void load4UpdateNews(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String sNid = request.getParameter("nid");
		int nid = Integer.parseInt(sNid);
		News _news = newsDb.loadNews(nid);
		
		request.setAttribute("THE_NEWS", _news);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-news-form.jsp");
		
		dispatcher.forward(request, response);
	}

	private void deleteComment(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String scid = request.getParameter("cid");
		int cid = Integer.parseInt(scid);
		
		commentDb.deleteComment(cid,"");
		
		loadNews(request,response);
	}

	private void loadNews(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String newsId = request.getParameter("nid");
		int id = Integer.parseInt(newsId);

		List<comment> commentList = commentDb.getComment(id);
		News news = newsDb.getNews(id);
		
		request.setAttribute("THE_NEWS", news);
		request.setAttribute("LIST_COMMENT", commentList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/show-news-content"+modePath+".jsp");
		dispatcher.forward(request, response);
		
	}

	private void addComment(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String cNewsId = request.getParameter("nid");
		String cAutor = request.getParameter("textAutor");
		String cComment = request.getParameter("textComment");
		Date cDate = new Date(0);
		
		int nid = Integer.parseInt(cNewsId);

		comment _comment = new comment(nid,cAutor,cComment,cDate);
		
		commentDb.addComment(_comment);
		
		loadNews(request,response);
	}

	private void addNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String header = request.getParameter("newsHeader");
		String content = request.getParameter("newsContent");
		Date date = new Date(0);

		News news = new News(header,content,date);
			
		newsDb.addNews(news);
		
		listNews(request,response);
		
	}
	

	private void listNews(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException  {
		List<News> nl = newsDb.getNews();
		
		request.setAttribute("LIST_NEWS", nl);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-news"+modePath+".jsp");
		dispatcher.forward(request, response);
		
	}
	


}
