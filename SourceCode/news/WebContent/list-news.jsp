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
						out.print("<tr> <td> <a href=\"newsController?command=show&nid="+fn.getid()+"\"> "+fn.getheader()+"</a> </td></tr>");
					}
				%>
		</table>
	</fieldset>
	
	<br>
	
	<form action="newsController" method="GET">
		<input type="button"  value="Haber Ekle"
			onClick="window.location.href='add-news-form.jsp'; return false;"/>
		<input type="hidden" name="command" value="1"/>
		<input type="submit" value="Yonetici modu"/>
	</form>

</body>

</html>