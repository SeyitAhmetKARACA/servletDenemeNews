<%@ page import="java.util.*,com.karaca.web.jdbc.*" %>

<html>

<% News n = (News)request.getAttribute("THE_NEWS"); 
   List<comment> c = (List<comment>)request.getAttribute("LIST_COMMENT");
%>

<body>
	<fieldset>
		<legend><% out.print(n.getheader()); %></legend>
			<div>
				<% out.print(n.getcontent()); %>
			</div>
	</fieldset>
	
	<form action="newsController" method="GET">
		<input type="hidden" name="command" value="addComment" />
		<input type="hidden" name="nid" value="<% out.print(n.getid());%>"/>
		<input type="submit" value="yorum ekle"/>
		<input type="text" name="textAutor" />
		<input type="text" name="textComment" />
	</form>

	<br>
	
	<%
		for(comment temp : c){
			out.print("<fieldset><legend>"+temp.getautor()+" Diyor ki :  "+
						"<a href=\"newsController?command=deleteComment&nid="+n.getid()+"&cid="+temp.getid()+"\">DELETE </a>"+ 
						"| UPDATE</legend>"+

					"<div>"+ temp.getComment()+
					"</div> </fieldset> <br>"
					);
		}
	%>
	

</body>

</html>