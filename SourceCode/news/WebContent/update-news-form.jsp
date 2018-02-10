<%@ page import="com.karaca.web.jdbc.* , java.util.*" %>

<html>

<% News n = (News)request.getAttribute("THE_NEWS"); %>

<body>
	<form action="newsController" method="GET">
		<fieldset>
			<legend>UPDATE News Form</legend>
			
			<input type="hidden" name="command" value="update"/>
			<input type="hidden" name="nid" value="<% out.print(n.getid()); %>"/>
			
			Hader :   <input type="text" name="newsHeader" value="<% out.print(n.getheader()); %>" />
			
			<br>
			Content : <br><input type="text" name="newsContent" value="<% out.print(n.getcontent()); %>" size="50" />
			
			<br>
			
			<input type="submit" value="Haber Guncelle">
		</fieldset>
		<br>
		
	</form>

</body>

</html>