<html>

<body>
	<form action="newsController" method="GET">
		<fieldset>
			<legend>Add News Form</legend>
			
			<input type="hidden" name="command" value="add"/>
			
			Hader :   <input type="text" name="newsHeader" />
			
			<br>
			Content : <br><input type="text" name="newsContent" size="50" />
			
			<br>
			
			<input type="submit" value="Haber Ekle">			
		</fieldset>
		<br>
		
	</form>

</body>

</html>