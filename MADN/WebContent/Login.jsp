<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Anzahl</title>
</head>
<body>
<br><br>
<div id="header">
</div>
<form action="Login" method="post">
 <div id="form1">
 
   <label id="labels1">Spieleranzahl auswählen:</label> 
  <select id ="spielerAnz" name="spielerAnz">
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
</select>
   <br>
   <br>
 
<input id="weiterButton" type = "submit" value = "weiter">
 <br> <br>
</div>
</form>
</body>
</html>