package servlets;



public class Brett {
	
	public static String getHeader(){
	return 
	"<html>" +
	"<head>"+ 
	
	"<meta http-equiv='X-UA-Compatible' content='IE=edge'>"+
	"<meta name='viewport' content='width=device-width, initial-scale=1'>"+
	"<link rel='stylesheet' type='text/css' href='style.css'>"+
	"<script src='http://code.jquery.com/jquery-latest.min.js' type='text/javascript'></script>"+
	"<script src='script.js'></script>"+
	"<title>Spielbrett</title>"+
	
	"</head>" +
	"<body>" ;
	}
	
	
	public static String getFooter(){
	return 
	"</body>"+
	"</html>";
	}
	public static  String getMenu(){
	return
	"<center>"+
	"<div id='cssmenu'>"+
	"<ul>"+
	"<li class='active'><a href='#'>Speichern als</a></li>"+
	"<li><a href='#'>SER</a></li>"+
	"<li><a href='#'>CSV</a></li>"+
	"<li><a href='#'>PDF</a></li>"+
	"<li><a href='#'>XML</a></li>"+
	"</ul>"+
	"</div>"+
	"</center>"+

	"<div id='dice'>"+
	"<img src='Bilder/dice6.png'>"+
	"</div>"+

	"<div id='steuerung'>"+
	"<font color='white' face='Trebuchet MS'>Steuerung</font>"+
	"<br><br>"+
	"<form action='Spielbrett' method='post'>"+
	"<input id='refresh' type = 'submit' value = 'Refresh'>"+
	"</form>"+
	"<br><br>"+
	"<input id='wurf' type = 'submit' value = 'Würfeln'>"+
	"<br><br>"+
	"<input id='laufKi' type = 'submit' value = 'Laufen (Ki)'>"+
	"<br><br>"+
	"<input id='beenden' type = 'submit' value = 'Zug Beenden'>"+
	"</div>"+
	"<br>"+


	"<!--"+
	"<center><table border='1' width='20' >"+
	"<tr>  <td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td></td><td></td> 		<td><a><img src='Bilder/rot1.png'></a>  </td><td><a><img src='Bilder/rot1.png'></a>  </td><td><a><img src='Bilder/rot1.png'></a>  </td> 	<td></td><td></td> 		<td><a><img src='Bilder/rot1.png'></a>  </td><td><a><img src='Bilder/rot1.png'></a>  </td>  </tr>"+
	"<tr>  <td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td></td><td></td> 		<td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td></td><td></td> 		<td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td>  </tr>"+
	"<tr>  <td></td><td></td> 		<td></td><td></td> 		<td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td></td><td></td> 		<td></td><td></td>  	</tr>"+
	"<tr>  <td></td><td></td> 		<td></td><td></td> 		<td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td></td><td></td> 		<td></td><td></td>  	</tr>"+
	"<tr>  <td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td>  </tr>"+
	"<tr>  <td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td><a><img src='Bilder/rot1.png'></a> </td><td></td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td>  </tr>  "+
	"<tr>  <td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td>  </tr>"+ 
	"<tr>  <td></td><td></td> 		<td></td><td></td> 		<td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td></td><td></td> 		<td></td><td></td>  	</tr>"+
	"<tr>  <td></td><td></td> 		<td></td><td></td> 		<td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td></td><td></td> 		<td></td><td></td>  	</tr>"+
	"<tr>  <td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td></td><td></td> 		<td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td></td><td></td> 		<td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td>  </tr>"+
	"<tr>  <td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td></td><td></td> 		<td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td> 	<td></td><td></td> 		<td><a><img src='Bilder/rot1.png'></a> </td><td><a><img src='Bilder/rot1.png'></a> </td>  </tr>"+
	"</table></center>"+
	"-->"+
	"<center><table border='1' width='20' >"+
	"<tr>  <td><a></a> </td><td><a></a> </td> 	<td></td><td></td> 		<td><a></a>  </td><td><a></a>  </td><td><a></a>  </td> 	<td></td><td></td> 		<td><a></a>  </td><td><a></a>  </td>  </tr>"+
	"<tr>  <td><a></a> </td><td><a></a> </td> 	<td></td><td></td> 		<td><a></a> </td><td><a></a> </td><td><a></a> </td> 	<td></td><td></td> 		<td><a></a> </td><td><a></a> </td>  </tr>"+
	"<tr>  <td></td><td></td> 		<td></td><td></td> 		<td><a></a> </td><td><a></a> </td><td><a></a> </td> 	<td></td><td></td> 		<td></td><td></td>  	</tr>"+
	"<tr>  <td></td><td></td> 		<td></td><td></td> 		<td><a></a> </td><td><a></a> </td><td><a></a> </td> 	<td></td><td></td> 		<td></td><td></td>  	</tr>"+
	"<tr>  <td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td>  </tr>"+
	"<tr>  <td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td></td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td>  </tr>  "+
	"<tr>  <td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td> 	<td><a></a> </td><td><a></a> </td>  </tr> "+
	"<tr>  <td></td><td></td> 		<td></td><td></td> 		<td><a></a> </td><td><a></a> </td><td><a></a> </td> 	<td></td><td></td> 		<td></td><td></td>  	</tr>"+
	"<tr>  <td></td><td></td> 		<td></td><td></td> 		<td><a></a> </td><td><a></a> </td><td><a></a> </td> 	<td></td><td></td> 		<td></td><td></td>  	</tr>"+
	"<tr>  <td><a></a> </td><td><a></a> </td> 	<td></td><td></td> 		<td><a></a> </td><td><a></a> </td><td><a></a> </td> 	<td></td><td></td> 		<td><a></a> </td><td><a></a> </td>  </tr>"+
	"<tr>  <td><a></a> </td><td><a></a> </td> 	<td></td><td></td> 		<td><a></a> </td><td><a></a> </td><td><a></a> </td> 	<td></td><td></td> 		<td><a></a> </td><td><a></a> </td>  </tr>"+
	"</table></center>"+



	"<br><br><br><br>"+
	
	"<center><div id='log'><textarea readonly disabled id='logging' rows='1' cols='1'></textarea></div></center>";	
	}

}

