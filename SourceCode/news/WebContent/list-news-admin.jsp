<%@ page import="java.util.* , com.karaca.web.jdbc.*" %>

<html>
<head>
	<title>
		News
	</title>
</head>
<!-- haberleri getAttribute ile servletten aldim. -->
<% List<News> newsList = (List<News>)request.getAttribute("LIST_NEWS"); %>

<body>
	<fieldset>
		<legend>News</legend>
		<table>
		
				<%
					for(News fn : newsList){
						out.print("<tr> <td> <a href=\"newsController?command=show&nid="+fn.getid()+"\"> "+fn.getheader()+"</a> </td>"+
								"<td>"+ 
									"<a href=\"newsController?command=load&nid="+fn.getid()+"\"> update </a> | "+
									"<a href=\"newsController?command=delete&nid="+fn.getid()+"\"> delete </a>"+
								"</td>	"+
								"</tr>");
					}
				%>
		</table>
	</fieldset>
	
	<br>
	
	<form action="newsController" method="GET">
		<input type="button"  value="Haber Ekle"
			onClick="window.location.href='add-news-form.jsp'; return false;"/>
		<input type="hidden" name="command" value="0"/>
		<input type="submit" value="Normal modu"/>
	</form>

</body>

</html>