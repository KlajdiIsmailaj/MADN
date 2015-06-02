<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spieler 1</title>
</head>
<body>

 <h1>Spieler 1</h1>

 <form action="Spieler1" method="post">

   <div id="form">
  <label id="labels1">Bitte gib deinen Namen ein:</label> 
  <input type="text">
  
   <br>
   <br>
   
   <label id="labels1">Wähle deine Farbe aus:</label> 
  <select id ="farbe">
<option value="Rot">Rot</option>
	<option value="Blau">Blau</option>
	<option value="Grün">Grün</option>
	<option value="Gelb">Gelb</option>
</select>
   <br>
   <br>
   
   <label id="labels1">Wähle deinen Spieltyp: </label> 
   <select id ="spielerart">
	<option value="Mensch">Mensch</option>
	<option value="Aggressiv">Ki-Aggressiv</option>
	<option value="Defensiv">Ki-Defensiv</option>
</select>

<br>
<br>

  <input id="spielerAnlegen" type="submit" value="Spieler anlegen">
  
   </div>
    </form>

</body>
</html>