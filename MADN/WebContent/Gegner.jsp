<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gegner</title>



</head>
<body>
<br>
 <h1>Gegner Daten eingeben</h1>


<form action="Gegner" method="post" >
   <div id="form">
  
<%Integer anz=(Integer)request.getSession(false).getAttribute("anzahl");
if(anz==2){
%>
<label id="labels1">Spieler 2: </label> 
<select id ="spielerart" name ="spielerart1">
	<option value="Mensch">Mensch</option>
	<option value="Ki">Ki</option>
</select>
<% 
}else if(anz==3){
%>
<label id="labels1">Spieler 2: </label> 
<select id ="spielerart" name ="spielerart1">
	<option value="Mensch">Mensch</option>
	<option value="Ki">Ki</option>
</select>

<br>
<br>

<label id="labels1">Spieler 3: </label> 
<select id ="spielerart" name ="spielerart2">
	<option value="Mensch">Mensch</option>
	<option value="Ki">Ki</option>
</select>
<% 
}else if(anz==4){
%>
<label id="labels1">Spieler 2: </label> 
<select id ="spielerart" name ="spielerart1">
	<option value="Mensch">Mensch</option>
	<option value="Ki">Ki</option>
</select>

<br>
<br>

<label id="labels1">Spieler 3: </label> 
<select id ="spielerart" name ="spielerart2">
	<option value="Mensch">Mensch</option>
	<option value="Ki">Ki</option>
</select>

<br>
<br>
	
<label id="labels1">Spieler 4: </label> 
<select id ="spielerart" name ="spielerart3">
	<option value="Mensch">Mensch</option>
	<option value="Ki">Ki</option>
</select>
	
<% 	
}
%>


<br>
<br>


<input id="spielerAnlegen" name="spielerAnlegen" type="submit" value="Weiter" >
  </div>
</form>


  
 


</body>
</html>