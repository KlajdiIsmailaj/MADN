<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" type="text/css" href="style.css">
   <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
   <script src="script.js"></script>
<title>Spielbrett</title>


</head>
<body>
<center>
<div id='cssmenu'>
<ul>
   <li class='active'><a href='#'>Speichern als</a></li>
   <li><a href='#'>SER</a></li>
   <li><a href='#'>CSV</a></li>
   <li><a href='#'>PDF</a></li>
   <li><a href='#'>XML</a></li>
</ul>
</div>
</center>

<div id="dice">
<img src="Bilder/dice6.png">
</div>

<div id="steuerung">
<font color="white" face="Trebuchet MS">Steuerung</font>
<br><br>
 <form action="Spielbrett" method="post">
<input id="refresh" type = "submit" value = "Refresh">
</form>
<br><br>


<form action='Wuerfeln' method='post'>
<input id="wurf" type = "submit" value = "Würfeln">
</form>
<br><br>
<input id="laufKi" type = "submit" value = "Laufen (Ki)">
<br><br>
<input id="beenden" type = "submit" value = "Zug Beenden">
</div>
<br>





<center><table border="1" width="20" >
<tr>  <td id="1"><a><img src='Bilder/rot2.png'></a> </td><td><a><img src='Bilder/rot2.png'></a> </td> 	<td></td><td></td> 		<td><a></a>  </td><td><a></a>  </td><td><a></a>  </td> 	<td></td><td></td> 		<td><a></a>  </td><td><a></a>  </td>  </tr>
<tr>  <td><a><img src='Bilder/rot2.png'></a> </td><td><a><img src='Bilder/rot2.png'></a> </td> 	<td></td><td></td> 		<td><a></a> </td><td><a></a> </td><td><a></a> </td> 	<td></td><td></td> 		<td><a></a> </td><td><a></a> </td>  </tr>
<tr>  <td></td><td></td> 		<td></td><td></td> 		<td><a></a> </td><td><a></a> </td><td><a></a> </td> 	<td></td><td></td> 		<td></td><td></td>  	</tr>
<tr>  <td></td><td></td> 		<td></td><td></td> 		<td><a></a> </td><td><a></a> </td><td><a></a> </td> 	<td></td><td></td> 		<td></td><td></td>  	</tr>
<tr>  <td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td>  </tr>
<tr>  <td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td></td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td>  </tr>  
<tr>  <td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td>  </tr> 
<tr>  <td></td><td></td> 		<td></td><td></td> 		<td><a></a> </td><td><a></a> </td><td><a></a> </td> 	<td></td><td></td> 		<td></td><td></td>  	</tr>
<tr>  <td></td><td></td> 		<td></td><td></td> 		<td><a></a> </td><td><a></a> </td><td><a></a> </td> 	<td></td><td></td> 		<td></td><td></td>  	</tr>
<tr>  <td><a></a> </td><td><a></a> </td> 	<td></td><td></td> 		<td><a></a> </td><td><a></a> </td><td><a></a> </td> 	<td></td><td></td> 		<td><a></a> </td><td><a></a> </td>  </tr>
<tr>  <td><a></a> </td><td><a></a> </td> 	<td></td><td></td> 		<td><a></a> </td><td><a></a> </td><td><a></a> </td> 	<td></td><td></td> 		<td><a></a> </td><td><a></a> </td>  </tr>
</table></center>



<br><br><br>
<br>
<center><div id="log"><textarea name="gg" readonly disabled id="logging" rows="1" cols="1"></textarea></div></center>


</body>
</html>
