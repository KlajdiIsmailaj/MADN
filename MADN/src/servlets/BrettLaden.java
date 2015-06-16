package servlets;

import testmadn.DatenzugriffSerialisiert;
import testmadn.SpielBean;








public class BrettLaden {
	
	
	static String s="";
	
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
	
	String s="";
	
	
	s+="<center>";
	s+="<div id='cssmenu'>";
	s+="<ul>";
	s+="<li><a href='/MADNWeb/Speichern.jsp'>SER</a></li>";
	s+="<li><a href='#'>CSV</a></li>";
	s+="<li><a href='#'>PDF</a></li>";
	s+="<li><a href='#'>XML</a></li>";
	s+="</ul>";
	s+="</div>";
	s+="</center>";

	s+="<div id='dice'>";
	s+="<img src='Bilder/dice6.png'>";
	s+="</div>";

	s+="<div id='steuerung'>";
	s+="<font color='white' face='Trebuchet MS'>Steuerung</font>";
	s+="<br><br>";
	
	//------Refresh------
	s+="<form action='Refresh' method='post'>";
	s+="<input id='refresh' type = 'submit' value = 'Refresh' name='refreshLaden'>";
	s+="</form>";
	
	
	
	//------wuerfeln------
	s+="<form action='Wuerfeln' method='post' >";
	if(SpielerLadenWeb.getGameLaden().ermittleSpielerAmZugFarbe().equals(SpielerLadenWeb.getGameLaden().gibFarbeSession())){
		s+="<input  id='wurf' type = 'submit' value = 'Würfeln' name='wurfLaden'>";
	}else{
		s+="<input disabled id='wurf' type = 'submit' value = 'Würfeln'>";
	}
	s+="</form>";
	
	
	
	
	//------Laufen Ki------
	s+="<form action='LaufenKi' method='post'>";
	if(SpielerLadenWeb.getGameLaden().ermittleKiWeb()==true){
		s+="<input disabled id='laufKi' type = 'submit' value = 'Laufen (Ki)'>";
	}else{
		s+="<input id='laufKi' type = 'submit' value = 'Laufen (Ki)' name='laufenLadenKi'>";
	}
	s+="</form>";
	
	
	
	
	//--------beenden-----
	s+="<form action='Beenden' method='post'>";
	if(SpielerLadenWeb.getGameLaden().ermittleSpielerAmZugFarbe().equals(SpielerLadenWeb.getGameLaden().gibFarbeSession())){
		s+="<input id='beenden' type = 'submit' value = 'Zug Beenden' name='beendenLaden'>";
	}else{
		s+="<input disabled id='beenden' type = 'submit' value = 'Zug Beenden'>";
	}
	s+="</form>";
	
	
	
	s+="</div>";
	s+=	"<br>";

	s+="<center><table border='1' width='20' >";
	
	int b=0;
	for(int i=0;i<11;i++){
		s+="<tr>";
		for(int k=0;k<11;k++){
			s+="<td id='"+ b++ +"'><a>";
			
//			s+=b++;
	
			
			if(b==1||b==2||b==5||b==6||b==7||b==10||b==11){
				s+=link(updateFigur(b))+updateFigur(b)+"</a>";
			}
			if(b==12||b==13||b==16||b==17||b==18||b==21||b==22){
				s+=link(updateFigur(b))+updateFigur(b)+"</a>";
			}
			if(b==27||b==28||b==29){
				s+=link(updateFigur(b))+updateFigur(b)+"</a>";
			}
			if(b==38||b==39||b==40){
				s+=link(updateFigur(b))+updateFigur(b)+"</a>";
			}
			if(b==45||b==46||b==47||b==48||b==49||b==50||b==51||b==52||b==53||b==54||b==55){
				s+=link(updateFigur(b))+updateFigur(b)+"</a>";
			}
			if(b==56||b==57||b==58||b==59||b==60||b==62||b==63||b==64||b==65||b==66){
				s+=link(updateFigur(b))+updateFigur(b)+"</a>";
			}
			if(b==67||b==68||b==69||b==70||b==71||b==72||b==73||b==74||b==75||b==76||b==77){
				s+=link(updateFigur(b))+updateFigur(b)+"</a>";
			}
			if(b==82||b==83||b==84){
				s+=link(updateFigur(b))+updateFigur(b)+"</a>";
			}
			if(b==93||b==94||b==95){
				s+=link(updateFigur(b))+updateFigur(b)+"</a>";
			}
			if(b==100||b==101||b==104||b==105||b==106||b==109||b==110){
				s+=link(updateFigur(b))+updateFigur(b)+"</a>";
			}
			if(b==111||b==112||b==115||b==116||b==117||b==120||b==121){
				s+=link(updateFigur(b))+updateFigur(b)+"</a>";
			}

			
			
			s+= "</a></td>";
		}
		s+="</tr>";
	}
	
	
	s+="</table></center>";

	s+="<br><br>";
	s+="<center><div id='log'><textarea readonly disabled id='logging' rows='1' cols='1'>"+SpielBean.baos.toString()+DatenzugriffSerialisiert.baos.toString()+"</textarea></div></center>";
	s+="<br><br>";
	

	return s;
	
	}
	
	public static String link(String im){
		String fig="";
		
		if(im.equals("<img src='Bilder/rot1.png'>")||im.equals("<img src='Bilder/blau1.png'>")||im.equals("<img src='Bilder/gruen1.png'>")||im.equals("<img src='Bilder/gelb1.png'>")){
			fig="<a href='/MADNWeb/Laufen0Laden'>";
		}
		else if(im.equals("<img src='Bilder/rot2.png'>")||im.equals("<img src='Bilder/blau2.png'>")||im.equals("<img src='Bilder/gruen2.png'>")||im.equals("<img src='Bilder/gelb2.png'>")){
			fig="<a href='/MADNWeb/Laufen1Laden'>";
		}
		else if(im.equals("<img src='Bilder/rot3.png'>")||im.equals("<img src='Bilder/blau3.png'>")||im.equals("<img src='Bilder/gruen3.png'>")||im.equals("<img src='Bilder/gelb3.png'>")){
			fig="<a href='/MADNWeb/Laufen2Laden'>";
		}
		else if(im.equals("<img src='Bilder/rot4.png'>")||im.equals("<img src='Bilder/blau4.png'>")||im.equals("<img src='Bilder/gruen4.png'>")||im.equals("<img src='Bilder/gelb4.png'>")){
			fig="<a href='/MADNWeb/Laufen3Laden'>";
		}
		return fig;
	}
	
	
	public static String updateFigur(int i){
		String o="";
		
		//----Rot------
		if(SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]!=null&&SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]=="rot0"){
			o="<img src='Bilder/rot1.png'>";
		}
		if(SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]!=null&&SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]=="rot1"){
			o="<img src='Bilder/rot2.png'>";
		}
		if(SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]!=null&&SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]=="rot2"){
			o="<img src='Bilder/rot3.png'>";
		}
		if(SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]!=null&&SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]=="rot3"){
			o="<img src='Bilder/rot4.png'>";
		}
		
		//-----Blau-----
		if(SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]!=null&&SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]=="blau0"){
			o="<img src='Bilder/blau1.png'>";
		}
		if(SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]!=null&&SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]=="blau1"){
			o="<img src='Bilder/blau2.png'>";
		}
		if(SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]!=null&&SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]=="blau2"){
			o="<img src='Bilder/blau3.png'>";
		}
		if(SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]!=null&&SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]=="blau3"){
			o="<img src='Bilder/blau4.png'>";
		}
		
		//----Gruen-----
		if(SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]!=null&&SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]=="gruen0"){
			o="<img src='Bilder/gruen1.png'>";
		}
		if(SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]!=null&&SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]=="gruen1"){
			o="<img src='Bilder/gruen2.png'>";
		}
		if(SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]!=null&&SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]=="gruen2"){
			o="<img src='Bilder/gruen3.png'>";
		}
		if(SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]!=null&&SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]=="gruen3"){
			o="<img src='Bilder/gruen4.png'>";
		}
		
		//----Gelb-----
		if(SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]!=null&&SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]=="gelb0"){
			o="<img src='Bilder/gelb1.png'>";
		}
		if(SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]!=null&&SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]=="gelb1"){
			o="<img src='Bilder/gelb2.png'>";
		}
		if(SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]!=null&&SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]=="gelb2"){
			o="<img src='Bilder/gelb3.png'>";
		}
		if(SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]!=null&&SpielerLadenWeb.getGameLaden().gibFigurWebId()[i]=="gelb3"){
			o="<img src='Bilder/gelb4.png'>";
		}
		
		
		
		
		return o;
		
	}
	

	
	
	
	
}
