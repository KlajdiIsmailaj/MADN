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
 <h1>Ki Daten eingeben</h1>



   <div id="form">
   <form action="Ki" method="post" >
  
<%
Integer anz=(Integer)request.getSession(false).getAttribute("anzahl");
String typ1=(String) request.getSession(false).getAttribute("typ1");
String typ2=(String) request.getSession(false).getAttribute("typ2");
String typ3=(String) request.getSession(false).getAttribute("typ3");
String farbeSpieler=(String) request.getSession(false).getAttribute("farbeSpieler");


if(anz==2){
%>
<label id="labels1">Ki 2 Farbe:</label> 
<select id ="farbe" name="kifarbe2">

<%if(farbeSpieler.equals("Rot")){
	%><option disabled value="Rot">Rot</option><%
}else{
	%><option value="Rot">Rot</option><%
}
if(farbeSpieler.equals("Blau")){
%><option disabled value="Blau">Blau</option><%
}else{
	%><option value="Blau">Blau</option><%
}
if(farbeSpieler.equals("Grün")){
%><option disabled value="Grün">Grün</option><%
}else{
	%><option value="Grün">Grün</option><%
}
if(farbeSpieler.equals("Gelb")){
%><option disabled value="Gelb">Gelb</option><%
}else{
	%><option value="Gelb">Gelb</option><%
}
%>

</select>

<br>
<br>

<label id="labels1">Ki 2 Typ: </label> 
<select id ="spielerart" name ="kityp2">
	<option value="KI Aggressiv">Ki-Aggressiv</option>
	<option value="KI Defensiv">Ki-Defensiv</option>
</select>

<br>
<br>
<% 
}else if(anz==3){
	if(typ1.equals("Ki")){
%>
<label id="labels1">Ki 2 Farbe:</label> 
<select id ="farbe" name="kifarbe2">
	<option value="Rot">Rot</option>
	<option value="Blau">Blau</option>
	<option value="Grün">Grün</option>
	<option value="Gelb">Gelb</option>
</select>

<br>
<br>

<label id="labels1">Ki 2 Typ: </label> 
<select id ="spielerart" name ="kityp2">
	<option value="KI Aggressiv">Ki-Aggressiv</option>
	<option value="KI Defensiv">Ki-Defensiv</option>
</select>

<br>
<br>
<% 
	}if(typ2.equals("Ki")){
%>

<label id="labels1">Ki 3 Farbe:</label> 
<select id ="farbe" name="kifarbe3">
	<option value="Rot">Rot</option>
	<option value="Blau">Blau</option>
	<option value="Grün">Grün</option>
	<option value="Gelb">Gelb</option>
</select>

<br>
<br>

<label id="labels1">Ki 3 Typ: </label> 
<select id ="spielerart" name ="kityp3">
	<option value="KI Aggressiv">Ki-Aggressiv</option>
	<option value="KI Defensiv">Ki-Defensiv</option>
</select>

<br>
<br>

<% 
	}

}else if(anz==4){
	if(typ1.equals("Ki")){
%>
<label id="labels1">Ki 2 Farbe:</label> 
<select id ="farbe" name="kifarbe2">
	<option value="Rot">Rot</option>
	<option value="Blau">Blau</option>
	<option value="Grün">Grün</option>
	<option value="Gelb">Gelb</option>
</select>

<br>
<br>

<label id="labels1">Ki 2 Typ: </label> 
<select id ="spielerart" name ="kityp2">
	<option value="KI Aggressiv">Ki-Aggressiv</option>
	<option value="KI Defensiv">Ki-Defensiv</option>
</select>

<br>
<br>
<% 
	}if(typ2.equals("Ki")){
%>


<label id="labels1">Ki 3 Farbe:</label> 
<select id ="farbe" name="kifarbe3">
	<option value="Rot">Rot</option>
	<option value="Blau">Blau</option>
	<option value="Grün">Grün</option>
	<option value="Gelb">Gelb</option>
</select>

<br>
<br>

<label id="labels1">Ki 3 Typ: </label> 
<select id ="spielerart" name ="kityp3">
	<option value="KI Aggressiv">Ki-Aggressiv</option>
	<option value="KI Defensiv">Ki-Defensiv</option>
</select>

<br>
<br>

<% 
	}if(typ3.equals("Ki")){
%>


<label id="labels1">Ki 4 Farbe:</label> 
<select id ="farbe" name="kifarbe4">
	<option value="Rot">Rot</option>
	<option value="Blau">Blau</option>
	<option value="Grün">Grün</option>
	<option value="Gelb">Gelb</option>
</select>

<br>
<br>
	
<label id="labels1">Ki 4 Typ: </label> 
<select id ="spielerart" name ="kityp4">
	<option value="KI Aggressiv">Ki-Aggressiv</option>
	<option value="KI Defensiv">Ki-Defensiv</option>
</select>

<br>
<br>
	
<% 
	}
}
%>





<input id="spielerAnlegen" name="spielerAnlegen" type="submit" value="Weiter" >
</form>


  
   </div>


</body>
</html>