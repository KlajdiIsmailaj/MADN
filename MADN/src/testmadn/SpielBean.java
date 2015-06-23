package testmadn;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import testmadn.FarbEnum;
import testmadn.Spieler;


/**
 * Ein ganzes Spiel wird erzeugt
 * @author Gruppe A2
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(namespace="https://www.youtube.com/?hl=de&gl=DE")
@XmlType(propOrder={"spielerlist","spielerAmZug","brett"})
public class SpielBean implements iBediener, Serializable {
	private static final long serialVersionUID = 1L;
	
	private Spieler spielerAmZug;
	private ArrayList <Spieler> spielerlist;
	private Spielbrett brett;
	private int spielerAnzahl;
	private Integer wuerfelZahl;
	private Integer anzahlWeb=null;
	private String farbe;
	private String[]figurWebId2=null;
	public static ByteArrayOutputStream baos = new ByteArrayOutputStream();
	
	@Override
	public int gibAnzahlMensch(){
		int i=0;
		
		for(Spieler s:this.spielerlist){
			if(s.getKi()==null){
				i++;
			}
		}
		
		return i;
	}
	
	public int getSpielerAnzahl() {
		return spielerAnzahl;
	}

	public void setSpielerAnzahl(int spielerAnzahl) {
		this.spielerAnzahl = spielerAnzahl;
	}
	
	@Override
	public void laufKi(){
		
//		System.out.println("lauf");
//		System.out.println(spielerAmZug.getName());
//		System.out.println(this.spielerlist.size());
		while(spielerAmZug.getKi()!=null){
			if(spielerAmZug.getKi()!=null){
				if(this.ermittleGewinner()==true){
					break;
				}
				this.wuerfeln();
				this.laufen(this.gibFigurKi());
				
				while(this.getWuerfelZahl()==6){
					this.wuerfeln();
					this.laufen(this.gibFigurKi());
				}
				
				this.beenden();
				
			}else{
				
			}
		}
		
		
	}

	@Override
	public boolean menschDrin(){
		if(this.bestandSpielerlist()==2){
			if(spielerlist.get(1).getKi()!=null){
				return false;
			}
		}else if(this.bestandSpielerlist()==3){
			if(spielerlist.get(1).getKi()!=null&&spielerlist.get(2).getKi()!=null){
				return false;
			}
		}else if(this.bestandSpielerlist()==4){
			if(spielerlist.get(1).getKi()!=null&&spielerlist.get(2).getKi()!=null&&spielerlist.get(3).getKi()!=null){
				return false;
			}
		}
		return true;
	}

	@Override
	public Integer getAnzahlWeb() {
		return anzahlWeb;
	}
	@Override
	public void setAnzahlWeb(Integer anzahlWeb) {
		this.anzahlWeb = anzahlWeb;
	}
	
	@Override
	public void farbeSession(String farbeSess){
		farbe=farbeSess;
	}
	
	@Override
	public String gibFarbeSession(){
		return farbe;
	}
	
	
	@Override
	public String nameWeb1(){
		String name=null;
		if(spielerlist.get(0)!=null){
			name=spielerlist.get(0).getName();
		}
		
		return name;
	}
	@Override
	public String nameWeb2(){
		String name=null;
		if(spielerlist.get(1)!=null){
			name=spielerlist.get(1).getName();
		}
		
		return name;
	}
	@Override
	public String nameWeb3(){
		String name=null;
		if(spielerlist.get(1)!=null){
			name=spielerlist.get(1).getName();
		}
		
		return name;
	}
	@Override
	public String nameWeb4(){
		String name=null;
		if(spielerlist.get(1)!=null){
			name=spielerlist.get(1).getName();
		}
		
		return name;
	}

	/**
	 * der Konstruktor der Klasse Spiel es wird ein neues Spielbrett erzeugt es
	 * wird geprueft ob eine gueltige Anzahl an Spielern angegeben wurde
	 * 
	 * @param spielerAnz ist die ausgewaehlte Spieleranzahl
	 */
	public SpielBean() {
		
		spielerlist=new ArrayList<Spieler>();
		brett = new Spielbrett();
//		setCsv(new DatenzugriffCSV());
	}
	
	//-------Laden Methoden------
	
	@Override	
	public int bestandSpielerlist(){
		
		int i=0;
		for(Spieler a:this.getSpielerlist()){
			if(a!=null){
				i++;		
			}		
		}
		return i;
	}
	
	@Override
	public String ladeFarbe(int spielerIndex){
		FarbEnum spielerfarbe =null;
		String farbe= null;
		Spieler a= this.getSpielerlist().get(spielerIndex);
		spielerfarbe= a.getFarbe();	
		
		if(spielerfarbe==FarbEnum.ROT){
			farbe = "Rot";
			return farbe;
		}else if(spielerfarbe==FarbEnum.BLAU){
			farbe = "Blau";
			return farbe;
		}else if(spielerfarbe==FarbEnum.GRUEN){
			farbe = "Grün";
			return farbe;
		}else if(spielerfarbe==FarbEnum.GELB){
			farbe = "Gelb";	
			return farbe;
		}
		return farbe;
	}
	
	@Override
	public int updatePosLaden(int spieler,int figurID){
		return spielerlist.get(spieler).getFigurlist().get(figurID).getId();
	}
	
	@Override
	public String ladeName(int i){
		String 	spielername =null;
		Spieler a= this.getSpielerlist().get(i);
		spielername= a.getName();
		return spielername;
		
	}
	
	//-----Laden Ende Methoden--------
	
	
	@Override
	public int ermittleSpielerAmZug(){
		int i=0;
		if(spielerAmZug.getFarbe()==FarbEnum.ROT){
			i=0;
		}
		else if(spielerAmZug.getFarbe()==FarbEnum.BLAU){
			i=1;
		}
		else if(spielerAmZug.getFarbe()==FarbEnum.GRUEN){
			i=2;
		}
		else if(spielerAmZug.getFarbe()==FarbEnum.GELB){
			i=3;
		}
		return i;
	}
	
	@Override
	public String ermittleSpielerAmZugFarbe(){
		String i="";
		if(spielerAmZug.getFarbe()==FarbEnum.ROT){
			i="Rot";
		}
		else if(spielerAmZug.getFarbe()==FarbEnum.BLAU){
			i="Blau";
		}
		else if(spielerAmZug.getFarbe()==FarbEnum.GRUEN){
			i="Grün";
		}
		else if(spielerAmZug.getFarbe()==FarbEnum.GELB){
			i="Gelb";
		}
		return i;
	}
	
	@Override
	public boolean ermittleKiWeb(){
		
		
		if(spielerAmZug.getKi()==null){
			return true;
		}
		
		return false;
	}
	
	
	@Override
	public int gibFigurKi(){
		int i=0;
		if(spielerAmZug.getFarbe()==FarbEnum.ROT){
			i=spielerAmZug.getKi().ermittleFigur().getId();
		}
		else if(spielerAmZug.getFarbe()==FarbEnum.BLAU){
			i=spielerAmZug.getKi().ermittleFigur().getId();
		}
		else if(spielerAmZug.getFarbe()==FarbEnum.GRUEN){
			i=spielerAmZug.getKi().ermittleFigur().getId();
		}
		else if(spielerAmZug.getFarbe()==FarbEnum.GELB){
			i=spielerAmZug.getKi().ermittleFigur().getId();
		}
		return i;
	}
	
	@Override
	public void nimmtTeil(Spieler pl){
		
		if(pl==null){
			throw new RuntimeException("es nimmt kein Spieler teil !");
		}
		
		setSpielerAmZug(pl);
		
		spielerlist.add(pl); // add(0<--index vom spieler,spieler);
		spielerAnzahl++;
		System.out.println("Teilnehmer: --> "+pl.getName());
		
	}
	
	@Override
	public void newSpieler(String name,String Farbe,String KI){
		
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
//		
		
		Spieler x =new Spieler(name,this.bestimmeFarbe(Farbe),this.bestimmeKI(KI)) ;
		spielerlist.add(x); // add(0<--index vom spieler,spieler);
		setSpielerAmZug(x);
		spielerAnzahl++;
		System.out.println("Teilnehmer: --> "+x.getName());
		
		System.out.flush();
		System.setOut(old);
	}
	
	@Override
	public String updatePos(int figurID){
		return spielerAmZug.getFigurlist().get(figurID).getFeld().getId(); 
	}
	
	@Override
	public String  giveList(int figurID){
		String  liste =null;
		
		if(this.istFigurDrin(this.getBrett().getStartRot(), spielerAmZug.getFigurlist().get(figurID).getId())==true&&
			(spielerAmZug.getFarbe()==FarbEnum.ROT)){
			
			liste ="StartRot";
		}
		
		else if(this.istFigurDrin(this.getBrett().getStartBlau(), spielerAmZug.getFigurlist().get(figurID).getId())==true&&
			(spielerAmZug.getFarbe()==FarbEnum.BLAU)){
			
			liste ="StartBlau";
		}
		
		else if(this.istFigurDrin(this.getBrett().getStartGruen(), spielerAmZug.getFigurlist().get(figurID).getId())==true&&
			(spielerAmZug.getFarbe()==FarbEnum.GRUEN)){
			
			liste ="StartGruen";
		}
		
		else if(this.istFigurDrin(this.getBrett().getStartGelb(), spielerAmZug.getFigurlist().get(figurID).getId())==true&&
    		(spielerAmZug.getFarbe()==FarbEnum.GELB)){
			
			liste ="StartGelb";
		}
		
		else if(this.istFigurDrin(this.getBrett().getEndRot(), spielerAmZug.getFigurlist().get(figurID).getId())==true&&
    		(spielerAmZug.getFarbe()==FarbEnum.ROT)){
			
			liste ="EndRot";
		}
		
		else if(this.istFigurDrin(this.getBrett().getEndBlau(), spielerAmZug.getFigurlist().get(figurID).getId())==true&&
    		(spielerAmZug.getFarbe()==FarbEnum.BLAU)){
			
			liste ="EndBlau";
		}
		
		else if(this.istFigurDrin(this.getBrett().getEndGelb(), spielerAmZug.getFigurlist().get(figurID).getId())==true&&
    		(spielerAmZug.getFarbe()==FarbEnum.GELB)){
			
			liste ="EndGelb";		
		}
		
		else if(this.istFigurDrin(this.getBrett().getEndGruen(),spielerAmZug.getFigurlist().get(figurID).getId())==true&&
    		(spielerAmZug.getFarbe()==FarbEnum.GRUEN)){
			
			liste ="EndGruen";
		}
		
		else {
			liste ="Weg";	
		}
		
		return liste;
	}
	
	@Override
	public String farbePlayer(){
		
		FarbEnum player=spielerAmZug.getFarbe();
		String pl=null;
		switch(player){
		case ROT:
			pl="Rot";
			return pl;
		case BLAU:
			pl="Blau";
			return pl;
		case GRUEN:
			pl="Gruen";
			return pl;
		case GELB:
			pl="Gelb";
			return pl;
		}
		
		return pl;
		
	}
	
	@Override
	public boolean farbeWebRot(){
		FarbEnum rot=FarbEnum.ROT;
		for(Spieler spielerRot:spielerlist){
			if(spielerRot.getFarbe()==rot){
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean farbeWebBlau(){
		FarbEnum blau=FarbEnum.BLAU;
		for(Spieler spielerBlau:spielerlist){
			if(spielerBlau.getFarbe()==blau){
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean farbeWebGelb(){
		FarbEnum gelb=FarbEnum.GELB;
		for(Spieler spielerGelb:spielerlist){
			if(spielerGelb.getFarbe()==gelb){
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean farbeWebGruen(){
		FarbEnum gruen=FarbEnum.GRUEN;
		for(Spieler spielerGruen:spielerlist){
			if(spielerGruen.getFarbe()==gruen){
				return true;
			}
		}
		return false;
	}


	@Override
	public String[] gibFigurWebId(){
		
		figurWebId2=new String [122];
		
		//-----------Weg-------------
		for(Spielfeld f:brett.getWeg()){
			
			if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.ROT&&f.getFigur().getId()==0){
				figurWebId2[convertPos(f)]="rot0";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.ROT&&f.getFigur().getId()==1){
				figurWebId2[convertPos(f)]="rot1";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.ROT&&f.getFigur().getId()==2){
				figurWebId2[convertPos(f)]="rot2";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.ROT&&f.getFigur().getId()==3){
				figurWebId2[convertPos(f)]="rot3";
			}
			
			if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.BLAU&&f.getFigur().getId()==0){
				figurWebId2[convertPos(f)]="blau0";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.BLAU&&f.getFigur().getId()==1){
				figurWebId2[convertPos(f)]="blau1";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.BLAU&&f.getFigur().getId()==2){
				figurWebId2[convertPos(f)]="blau2";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.BLAU&&f.getFigur().getId()==3){
				figurWebId2[convertPos(f)]="blau3";
			}
			
			if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GRUEN&&f.getFigur().getId()==0){
				figurWebId2[convertPos(f)]="gruen0";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GRUEN&&f.getFigur().getId()==1){
				figurWebId2[convertPos(f)]="gruen1";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GRUEN&&f.getFigur().getId()==2){
				figurWebId2[convertPos(f)]="gruen2";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GRUEN&&f.getFigur().getId()==3){
				figurWebId2[convertPos(f)]="gruen3";
			}
			
			if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GELB&&f.getFigur().getId()==0){
				figurWebId2[convertPos(f)]="gelb0";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GELB&&f.getFigur().getId()==1){
				figurWebId2[convertPos(f)]="gelb1";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GELB&&f.getFigur().getId()==2){
				figurWebId2[convertPos(f)]="gelb2";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GELB&&f.getFigur().getId()==3){
				figurWebId2[convertPos(f)]="gelb3";
			}
		}
		
		//-----------Startfelder------------
		for(Spielfeld f:brett.getStartRot()){
			if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.ROT&&f.getFigur().getId()==0){
				figurWebId2[convertPos(f)]="rot0";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.ROT&&f.getFigur().getId()==1){
				figurWebId2[convertPos(f)]="rot1";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.ROT&&f.getFigur().getId()==2){
				figurWebId2[convertPos(f)]="rot2";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.ROT&&f.getFigur().getId()==3){
				figurWebId2[convertPos(f)]="rot3";
			}
		}
		
		for(Spielfeld f:brett.getStartBlau()){
			if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.BLAU&&f.getFigur().getId()==0){
				figurWebId2[convertPos(f)]="blau0";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.BLAU&&f.getFigur().getId()==1){
				figurWebId2[convertPos(f)]="blau1";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.BLAU&&f.getFigur().getId()==2){
				figurWebId2[convertPos(f)]="blau2";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.BLAU&&f.getFigur().getId()==3){
				figurWebId2[convertPos(f)]="blau3";
			}
		}
		
		for(Spielfeld f:brett.getStartGruen()){
			if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GRUEN&&f.getFigur().getId()==0){
				figurWebId2[convertPos(f)]="gruen0";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GRUEN&&f.getFigur().getId()==1){
				figurWebId2[convertPos(f)]="gruen1";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GRUEN&&f.getFigur().getId()==2){
				figurWebId2[convertPos(f)]="gruen2";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GRUEN&&f.getFigur().getId()==3){
				figurWebId2[convertPos(f)]="gruen3";
			}
		}
		
		for(Spielfeld f:brett.getStartGelb()){
			if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GELB&&f.getFigur().getId()==0){
				figurWebId2[convertPos(f)]="gelb0";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GELB&&f.getFigur().getId()==1){
				figurWebId2[convertPos(f)]="gelb1";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GELB&&f.getFigur().getId()==2){
				figurWebId2[convertPos(f)]="gelb2";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GELB&&f.getFigur().getId()==3){
				figurWebId2[convertPos(f)]="gelb3";
			}
		}
		
		//---------EndFelder-------------
		
		for(Spielfeld f:brett.getEndRot()){
			if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.ROT&&f.getFigur().getId()==0){
				figurWebId2[convertPos(f)]="rot0";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.ROT&&f.getFigur().getId()==1){
				figurWebId2[convertPos(f)]="rot1";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.ROT&&f.getFigur().getId()==2){
				figurWebId2[convertPos(f)]="rot2";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.ROT&&f.getFigur().getId()==3){
				figurWebId2[convertPos(f)]="rot3";
			}
		}
		
		for(Spielfeld f:brett.getEndBlau()){
			if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.BLAU&&f.getFigur().getId()==0){
				figurWebId2[convertPos(f)]="blau0";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.BLAU&&f.getFigur().getId()==1){
				figurWebId2[convertPos(f)]="blau1";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.BLAU&&f.getFigur().getId()==2){
				figurWebId2[convertPos(f)]="blau2";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.BLAU&&f.getFigur().getId()==3){
				figurWebId2[convertPos(f)]="blau3";
			}
		}
		
		for(Spielfeld f:brett.getEndGruen()){
			if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GRUEN&&f.getFigur().getId()==0){
				figurWebId2[convertPos(f)]="gruen0";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GRUEN&&f.getFigur().getId()==1){
				figurWebId2[convertPos(f)]="gruen1";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GRUEN&&f.getFigur().getId()==2){
				figurWebId2[convertPos(f)]="gruen2";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GRUEN&&f.getFigur().getId()==3){
				figurWebId2[convertPos(f)]="gruen3";
			}
		}
		
		for(Spielfeld f:brett.getEndGelb()){
			if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GELB&&f.getFigur().getId()==0){
				figurWebId2[convertPos(f)]="gelb0";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GELB&&f.getFigur().getId()==1){
				figurWebId2[convertPos(f)]="gelb1";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GELB&&f.getFigur().getId()==2){
				figurWebId2[convertPos(f)]="gelb2";
			}else if(f.getFigur()!=null&&f.getFigur().getFarbe()==FarbEnum.GELB&&f.getFigur().getId()==3){
				figurWebId2[convertPos(f)]="gelb3";
			}
		}
		
		return figurWebId2;
	}
	
	
	public int convertPos(Spielfeld s){
		int i=0;
		String pos=s.getId();
		FarbEnum f=s.getFarbe();
		
		switch(pos){
		case "1":
			i=45;
			return i;
		case "2":
			i=46;
			return i;
		case "3":
			i=47;
			return i;
		case "4":
			i=48;
			return i;
		case "5":
			i=49;
			return i;
		case "6":
			i=38;
			return i;
		case "7":
			i=27;
			return i;
		case "8":
			i=16;
			return i;
		case "9":
			i=5;
			return i;
		case "10":
			i=6;
			return i;
		case "11":
			i=7;
			return i;
		case "12":
			i=18;
			return i;
		case "13":
			i=29;
			return i;
		case "14":
			i=40;
			return i;
		case "15":
			i=51;
			return i;
		case "16":
			i=52;
			return i;
		case "17":
			i=53;
			return i;
		case "18":
			i=54;
			return i;
		case "19":
			i=55;
			return i;
		case "20":
			i=66;
			return i;
		case "21":
			i=77;
			return i;
		case "22":
			i=76;
			return i;
		case "23":
			i=75;
			return i;
		case "24":
			i=74;
			return i;
		case "25":
			i=73;
			return i;
		case "26":
			i=84;
			return i;
		case "27":
			i=95;
			return i;
		case "28":
			i=106;
			return i;
		case "29":
			i=117;
			return i;
		case "30":
			i=116;
			return i;
		case "31":
			i=115;
			return i;
		case "32":
			i=104;
			return i;
		case "33":
			i=93;
			return i;
		case "34":
			i=82;
			return i;
		case "35":
			i=71;
			return i;
		case "36":
			i=70;
			return i;
		case "37":
			i=69;
			return i;
		case "38":
			i=68;
			return i;
		case "39":
			i=67;
			return i;
		case "40":
			i=56;
			return i;
		case "S1":
			if(f==FarbEnum.ROT){
				i=2;
				return i;
			}
			else if(f==FarbEnum.BLAU){
				i=11;
				return i;
			}
			else if(f==FarbEnum.GRUEN){
				i=110;
				return i;
			}
			else if(f==FarbEnum.GELB){
				i=101;
				return i;
			}
			
		case "S2":
			if(f==FarbEnum.ROT){
				i=1;
				return i;
			}
			else if(f==FarbEnum.BLAU){
				i=10;
				return i;
			}
			else if(f==FarbEnum.GRUEN){
				i=109;
				return i;
			}
			else if(f==FarbEnum.GELB){
				i=100;
				return i;
			}
		case "S3":
			if(f==FarbEnum.ROT){
				i=12;
				return i;
			}
			else if(f==FarbEnum.BLAU){
				i=21;
				return i;
			}
			else if(f==FarbEnum.GRUEN){
				i=120;
				return i;
			}
			else if(f==FarbEnum.GELB){
				i=111;
				return i;
			}
		case "S4":
			if(f==FarbEnum.ROT){
				i=13;
				return i;
			}
			else if(f==FarbEnum.BLAU){
				i=22;
				return i;
			}
			else if(f==FarbEnum.GRUEN){
				i=121;
				return i;
			}
			else if(f==FarbEnum.GELB){
				i=112;
				return i;
			}
		case "E1":
			if(f==FarbEnum.ROT){
				i=57;
				return i;
			}
			else if(f==FarbEnum.BLAU){
				i=17;
				return i;
			}
			else if(f==FarbEnum.GRUEN){
				i=65;
				return i;
			}
			else if(f==FarbEnum.GELB){
				i=105;
				return i;
			}
		case "E2":
			if(f==FarbEnum.ROT){
				i=58;
				return i;
			}
			else if(f==FarbEnum.BLAU){
				i=28;
				return i;
			}
			else if(f==FarbEnum.GRUEN){
				i=64;
				return i;
			}
			else if(f==FarbEnum.GELB){
				i=94;
				return i;
			}
		case "E3":
			if(f==FarbEnum.ROT){
				i=59;
				return i;
			}
			else if(f==FarbEnum.BLAU){
				i=39;
				return i;
			}
			else if(f==FarbEnum.GRUEN){
				i=63;
				return i;
			}
			else if(f==FarbEnum.GELB){
				i=83;
				return i;
			}
		case "E4":
			if(f==FarbEnum.ROT){
				i=60;
				return i;
			}
			else if(f==FarbEnum.BLAU){
				i=50;
				return i;
			}
			else if(f==FarbEnum.GRUEN){
				i=62;
				return i;
			}
			else if(f==FarbEnum.GELB){
				i=72;
				return i;
			}
		}
		return i;
	}
	
	
	
	public FarbEnum bestimmeFarbe(String farbe) {
		FarbEnum wert = null;
		switch(farbe){
			case "Rot" : 
				wert=FarbEnum.ROT;
				return wert;
			case "Blau" : 
				wert=FarbEnum.BLAU;
				return wert;
			case "Grün" : 
				wert=FarbEnum.GRUEN;
				return wert;
			case "Gelb" : 
				wert=FarbEnum.GELB;
				return wert;
		}
		return wert;
	}
	
	public KI bestimmeKI(String eingabe	){
		KI wert2= null;
		switch(eingabe){
			case "Mensch":
				wert2= null;
				return wert2;
			case "KI Aggressiv":
				wert2=new KI_Aggressiv(this);
				return wert2;
			case "KI Defensiv":
				wert2=new KI_Defensiv(this);
				return wert2;
		}
		return wert2;
	}
	
	@Override
	public void spielStart(){
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		
		if ((spielerlist.size()==0)){
			throw new RuntimeException ("Es nimmt noch kein Spieler teil !");
		}
		
		int k=0;
		do{
			if (spielerlist.get(k).getFarbe()==FarbEnum.ROT){
				int i=0;
				for(Spielfigur b:spielerlist.get(k).getFigurlist()){
					b=spielerlist.get(k).getFigurlist().get(i);
					brett.getStartRot().get(i).setFigur(b);
					i++;
				}
				System.out.println("StartBox Bereit ROT ");
			}
			else if (spielerlist.get(k).getFarbe()==FarbEnum.BLAU){
				int i=0;
				for(Spielfigur b:spielerlist.get(k).getFigurlist()){
					b=spielerlist.get(k).getFigurlist().get(i);
					brett.getStartBlau().get(i).setFigur(b);
					i++;
				}
				System.out.println("StartBox Bereit BLAU ");
			}
			else if (spielerlist.get(k).getFarbe()==FarbEnum.GRUEN){
				int i=0;
				for(Spielfigur b:spielerlist.get(k).getFigurlist()){
					b=spielerlist.get(k).getFigurlist().get(i);
					brett.getStartGruen().get(i).setFigur(b);
					i++;
				}
				System.out.println("StartBox Bereit GRUEN ");
			}
			else if (spielerlist.get(k).getFarbe()==FarbEnum.GELB){
				int i=0;
				for(Spielfigur b:spielerlist.get(k).getFigurlist()){
					b=spielerlist.get(k).getFigurlist().get(i);
					brett.getStartGelb().get(i).setFigur(b);
					i++;
				}
				System.out.println("StartBox Bereit GELB ");
			}
			k++;
		}while(k<spielerAnzahl);
		
		System.out.println("!!! START !!!");
		
		setSpielerAmZug(spielerlist.iterator().next());
		
		System.out.flush();
		System.setOut(old);
		
		
	}
	
	/**
	 * Zuweisung der Startposition der jeweiligen Spielfiguren der Spieler
	 *  in Abhaengigkeit der Farbe
	 *  @param spieler ist die liste der figuren 
	 *  @param figur ist die Spielfigur die zu bewegen ist
	 */
	public void startPosZuweisen(Spieler spieler, int figur){
		
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		
		if (spieler.getFarbe() == FarbEnum.ROT){
			Spielfigur farbeFigur=this.brett.getStartRot().get(figur).getFigur();
			int figurBox=this.brett.getStartRot().indexOf(farbeFigur.getFeld());
			Spielfeld rausFeld=this.brett.getWeg().get(0);
			
			if(rausFeld.istFeldBelegt()==false){
				rausFeld.setFigur(farbeFigur);
				this.brett.getStartRot().get(figurBox).setFigur(null);
				System.out.println(spieler.getName()+" deine Figur: "+farbeFigur.getId()+" startet bei Feld: "+rausFeld.getId());
			}
			else if(rausFeld.istFeldBelegt()==true){
				if(rausFeld.getFigur().getFarbe()!=farbeFigur.getFarbe()){
					this.schlagen(0);
					
					rausFeld.setFigur(farbeFigur);
					this.brett.getStartRot().get(figurBox).setFigur(null);
					System.out.println(spieler.getName()+" deine Figur: "+farbeFigur.getId()+" startet bei Feld: "+rausFeld.getId());
				}else{
					System.out.println("nicht gleichzeitig rausgehen bitte !");
				}
				
			}
		}
		
		else if (spieler.getFarbe() == FarbEnum.BLAU){
			Spielfigur farbeFigur=this.brett.getStartBlau().get(figur).getFigur();
			int figurBox=this.brett.getStartBlau().indexOf(farbeFigur.getFeld());
			Spielfeld rausFeld=this.brett.getWeg().get(10);
			
			if(rausFeld.istFeldBelegt()==false){
				rausFeld.setFigur(farbeFigur);
				this.brett.getStartBlau().get(figurBox).setFigur(null);
				System.out.println(spieler.getName()+" deine Figur: "+farbeFigur.getId()+" startet bei Feld: "+rausFeld.getId());
			}
			else if(rausFeld.istFeldBelegt()==true){
				if(rausFeld.getFigur().getFarbe()!=farbeFigur.getFarbe()){
					this.schlagen(10);
					
					rausFeld.setFigur(farbeFigur);
					this.brett.getStartBlau().get(figurBox).setFigur(null);
					System.out.println(spieler.getName()+" deine Figur: "+farbeFigur.getId()+" startet bei Feld: "+rausFeld.getId());
				}else{
					System.out.println("nicht gleichzeitig rausgehen bitte !");
				}
				
			}
		}	
		
		else if (spieler.getFarbe() == FarbEnum.GRUEN){
			Spielfigur farbeFigur=this.brett.getStartGruen().get(figur).getFigur();
			int figurBox=this.brett.getStartGruen().indexOf(farbeFigur.getFeld());
			Spielfeld rausFeld=this.brett.getWeg().get(20);
			
			if(rausFeld.istFeldBelegt()==false){
				rausFeld.setFigur(farbeFigur);
				this.brett.getStartGruen().get(figurBox).setFigur(null);
				System.out.println(spieler.getName()+" deine Figur: "+farbeFigur.getId()+" startet bei Feld: "+rausFeld.getId());
			}
			else if(rausFeld.istFeldBelegt()==true){
				if(rausFeld.getFigur().getFarbe()!=farbeFigur.getFarbe()){
					this.schlagen(20);
					
					rausFeld.setFigur(farbeFigur);
					this.brett.getStartGruen().get(figurBox).setFigur(null);
					System.out.println(spieler.getName()+" deine Figur: "+farbeFigur.getId()+" startet bei Feld: "+rausFeld.getId());
				}else{
					System.out.println("nicht gleichzeitig rausgehen bitte !");
				}
				
			}
		}
		
		else if (spieler.getFarbe() == FarbEnum.GELB){
			Spielfigur farbeFigur=this.brett.getStartGelb().get(figur).getFigur();
			int figurBox=this.brett.getStartGelb().indexOf(farbeFigur.getFeld());
			Spielfeld rausFeld=this.brett.getWeg().get(30);
			
			if(rausFeld.istFeldBelegt()==false){
				rausFeld.setFigur(farbeFigur);
				this.brett.getStartGelb().get(figurBox).setFigur(null);
				System.out.println(spieler.getName()+" deine Figur: "+farbeFigur.getId()+" startet bei Feld: "+rausFeld.getId());
			}
			else if(rausFeld.istFeldBelegt()==true){
				if(rausFeld.getFigur().getFarbe()!=farbeFigur.getFarbe()){
					this.schlagen(30);
					
					rausFeld.setFigur(farbeFigur);
					this.brett.getStartGelb().get(figurBox).setFigur(null);
					System.out.println(spieler.getName()+" deine Figur: "+farbeFigur.getId()+" startet bei Feld: "+rausFeld.getId());
				}else{
					System.out.println("nicht gleichzeitig rausgehen bitte !");
				}
				
			}
		}
		
		System.out.flush();
		System.setOut(old);
		
	}
	
	/**
	 * Eine Spielfigur eines Gegners wird geschlagen falls man auf das selbe
	 * Spielfeld kommt
	 * @param feldId ist das feld wo die Figur die geschlagen wird und zurueck in die Startbox kommt
	 */
	public void schlagen(int feldId) {
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		
		Spielfigur schlageFigur = brett.getWeg().get(feldId).getFigur();
		
		
		if(schlageFigur==null){
			throw new RuntimeException("Es gibt keine figur zu schlagen");
		}
		
		int figOrt = schlageFigur.getId();
		
		if (schlageFigur.getFarbe() == FarbEnum.ROT) {
			if(brett.getStartRot().get(figOrt).getFigur()==null){
				brett.getStartRot().get(figOrt).setFigur(schlageFigur);
				brett.getWeg().get(feldId).setFigur(null);
				System.out.println("ROTE Figur geschlagen");
			}
		}
		
		if (schlageFigur.getFarbe() == FarbEnum.BLAU) {
			if(brett.getStartBlau().get(figOrt).getFigur()==null){
				brett.getStartBlau().get(figOrt).setFigur(schlageFigur);
				brett.getWeg().get(feldId).setFigur(null);
				System.out.println("BLAUE Figur geschlagen");
			}
		}
		
		if (schlageFigur.getFarbe() == FarbEnum.GRUEN) {
			if(brett.getStartGruen().get(figOrt).getFigur()==null){
				brett.getStartGruen().get(figOrt).setFigur(schlageFigur);
				brett.getWeg().get(feldId).setFigur(null);
				System.out.println("GRUENE Figur geschlagen");
			}
		}
		
		if (schlageFigur.getFarbe() == FarbEnum.GELB) {
			if(brett.getStartGelb().get(figOrt).getFigur()==null){
				brett.getStartGelb().get(figOrt).setFigur(schlageFigur);
				brett.getWeg().get(feldId).setFigur(null);
				System.out.println("GELBE Figur geschlagen");
			}
		}
		
		System.out.flush();
		System.setOut(old);
	}
	
	@Override
	public void beenden(){
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		
		if(this.ermittleGewinner()==true){
			System.out.println(spielerAmZug.getName()+ " HAT GEWONNEN !");
		}
		
		else{
			switch (spielerAnzahl) {
				case 2:
				if(spielerAmZug==spielerlist.get(0)){
					if(this.getWuerfelZahl()==6){
					}
					else{
						setSpielerAmZug(spielerlist.get(1));
						break;
					}
				}
				if(spielerAmZug==spielerlist.get(1)){
					if(this.getWuerfelZahl()==6){
					}
					else{
						setSpielerAmZug(spielerlist.get(0));
						break;
					}
				}
				
				break;
				
				case 3:
				if(spielerAmZug==spielerlist.get(0)){
					if(this.getWuerfelZahl()==6){
					}
					else{
						setSpielerAmZug(spielerlist.get(1));
						break;
					}
				}
				if(spielerAmZug==spielerlist.get(1)){
					if(this.getWuerfelZahl()==6){
					}
					else{
						setSpielerAmZug(spielerlist.get(2));
						break;
					}
				}
				if(spielerAmZug==spielerlist.get(2)){
					if(this.getWuerfelZahl()==6){
					}
					else{
						setSpielerAmZug(spielerlist.get(0));
						break;
					}
				}
				break;
				
				
				case 4:
				if(spielerAmZug==spielerlist.get(0)){
					if(this.getWuerfelZahl()==6){
					}
					else{
						setSpielerAmZug(spielerlist.get(1));
						break;
					}
				}
				if(spielerAmZug==spielerlist.get(1)){
					if(this.getWuerfelZahl()==6){
					}
					else{
						setSpielerAmZug(spielerlist.get(2));
						break;
					}
				}
				if(spielerAmZug==spielerlist.get(2)){
					if(this.getWuerfelZahl()==6){
					}
					else{
						setSpielerAmZug(spielerlist.get(3));
						break;
					}
				}
				if(spielerAmZug==spielerlist.get(3)){
					if(this.getWuerfelZahl()==6){
					}
					else{
						setSpielerAmZug(spielerlist.get(0));
						break;
					}
				}
				break;
			}	
		}
		
		System.out.flush();
		System.setOut(old);
	}
	
	public void beendenXML(){
		
		if(this.ermittleGewinner()==true){
			System.out.println(spielerAmZug.getName()+ " HAT GEWONNEN !");
		}
		
		else{
			switch (spielerAnzahl) {
				case 2:
				
				if((spielerAmZug.getName().equals(spielerlist.get(0).getName()))&&(spielerAmZug instanceof Spieler)&&(spielerlist.get(0) instanceof Spieler)
					&&(spielerAmZug.getFarbe().equals(spielerlist.get(0).getFarbe()))){
					
					if(this.getWuerfelZahl()==6){
					}
					else{
						setSpielerAmZug(spielerlist.get(1));
						break;
					}
				}  
				
				if((spielerAmZug.getName().equals(spielerlist.get(1).getName()))&&(spielerAmZug instanceof Spieler)&&(spielerlist.get(1) instanceof Spieler)
					&&(spielerAmZug.getFarbe().equals(spielerlist.get(1).getFarbe()))){
					
					if(this.getWuerfelZahl()==6){
						
					}
					else{
						setSpielerAmZug(spielerlist.get(0));
						break;
					}
				}
				
				break;
				
				case 3:
				if((spielerAmZug.getName().equals(spielerlist.get(0).getName()))&&(spielerAmZug instanceof Spieler)&&(spielerlist.get(0) instanceof Spieler)
					&&(spielerAmZug.getFarbe().equals(spielerlist.get(0).getFarbe()))){
					
					if(this.getWuerfelZahl()==6){
					}
					else{
						setSpielerAmZug(spielerlist.get(1));
						break;
					}
				}
				
				if((spielerAmZug.getName().equals(spielerlist.get(1).getName()))&&(spielerAmZug instanceof Spieler)&&(spielerlist.get(1) instanceof Spieler)
					&&(spielerAmZug.getFarbe().equals(spielerlist.get(1).getFarbe()))){
					
					if(this.getWuerfelZahl()==6){
					}
					else{
						setSpielerAmZug(spielerlist.get(2));
						break;
					}
				}
				
				if((spielerAmZug.getName().equals(spielerlist.get(2).getName()))&&(spielerAmZug instanceof Spieler)&&(spielerlist.get(2) instanceof Spieler)
					&&(spielerAmZug.getFarbe().equals(spielerlist.get(2).getFarbe()))){
					
					if(this.getWuerfelZahl()==6){
					}
					else{
						setSpielerAmZug(spielerlist.get(0));
						break;
					}
				}
				break;
				
				
				case 4:
				if((spielerAmZug.getName().equals(spielerlist.get(0).getName()))&&(spielerAmZug instanceof Spieler)&&(spielerlist.get(0) instanceof Spieler)
					&&(spielerAmZug.getFarbe().equals(spielerlist.get(0).getFarbe()))){
					
					if(this.getWuerfelZahl()==6){
					}
					else{
						setSpielerAmZug(spielerlist.get(1));
						break;
					}
				}
				
				if((spielerAmZug.getName().equals(spielerlist.get(1).getName()))&&(spielerAmZug instanceof Spieler)&&(spielerlist.get(1) instanceof Spieler)
					&&(spielerAmZug.getFarbe().equals(spielerlist.get(1).getFarbe()))){
					
					if(this.getWuerfelZahl()==6){
					}
					else{
						setSpielerAmZug(spielerlist.get(2));
						break;
					}
				}
				
				if((spielerAmZug.getName().equals(spielerlist.get(2).getName()))&&(spielerAmZug instanceof Spieler)&&(spielerlist.get(2) instanceof Spieler)
					&&(spielerAmZug.getFarbe().equals(spielerlist.get(2).getFarbe()))){
					
					if(this.getWuerfelZahl()==6){
					}
					else{
						setSpielerAmZug(spielerlist.get(3));
						break;
					}
				}
				
				if((spielerAmZug.getName().equals(spielerlist.get(3).getName()))&&(spielerAmZug instanceof Spieler)&&(spielerlist.get(3) instanceof Spieler)
					&&(spielerAmZug.getFarbe().equals(spielerlist.get(3).getFarbe()))){
					
					if(this.getWuerfelZahl()==6){
					}
					else{
						setSpielerAmZug(spielerlist.get(0));
						break;
					}
				}
				break;
			}	
		}
	}
	
	public int bestand(ArrayList<Spielfeld> liste){
		
		int i=0;
		for(Spielfeld a:liste){
			
			if(a.getFigur()!=null){
				i++;		
			}		
		}
		return i;
	}
	
	public boolean istEndfeldVoll(ArrayList<Spielfeld> liste){
		if(bestand(liste)==4){
			return true;
		}
		return false;
		
	}
	
	@Override
	public boolean ermittleGewinner(){
		if(	istEndfeldVoll(brett.getEndRot())==true||
			istEndfeldVoll(brett.getEndBlau())==true||
			istEndfeldVoll(brett.getEndGruen())==true||
			istEndfeldVoll(brett.getEndGelb())==true){
			return true;
		}
		return false;
	}
	
	public boolean istFigurDrin(ArrayList<Spielfeld> liste,int figurId){
		
		for(Spielfeld b:liste){
			if(b.getFigur()!=null){
				if(b.getFigur().getId()==figurId){
					return true;
				}
			}
		}
		return false;
	}
	
	public int letztePosRechnen(ArrayList<Spielfeld> liste, Spielfigur figur){
		
		int letztePos=liste.indexOf(figur.getFeld());
		
		return letztePos;
	}
	
	public boolean kannUeberhoeln(ArrayList<Spielfeld> liste,int altePos, int neuePos){
		
		for(Spielfeld a:liste.subList(altePos, neuePos)){
			if(liste.indexOf(a)<neuePos){				
				if(a.istFeldBelegt()==true){
					return false;
				}
			}
		}
		return true;
	}
	
	
	/**
	 * die Spielfigur eines Spielers laeuft
	 * @param figurId ist, welche figur laufen moechte
	 */
	@Override
	public void laufen(int figurId){
//		
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		
		Spielfigur nowFigur=spielerAmZug.getFigurlist().get(figurId);
		int letztePos=getBrett().getWeg().indexOf(nowFigur.getFeld());
		int letzterWurf= getWuerfelZahl();//spielerAmZug.getLetzterWurf();
		int neuePos=letztePos + letzterWurf;
		int indexMax=39;
		Spielfeld startRot=this.brett.getWeg().get(0);
		Spielfeld startBlau=this.brett.getWeg().get(10);
		Spielfeld startGruen=this.brett.getWeg().get(20);
		Spielfeld startGelb=this.brett.getWeg().get(30);
		
		
		
		// ------------------------ROT---------------------------
		if (nowFigur.getFarbe()==FarbEnum.ROT){
			
			//zum ersten mal raus kommen 
			if (letzterWurf==6&&this.bestand(this.getBrett().getStartRot())==4){
				this.startPosZuweisen(spielerAmZug, figurId);
				System.out.println("Du darfst nochmal wuerfeln");
			}
			
			//der spieler moechte raus gehen aber er hat keine 6 gewuerfelt 
			else if (letzterWurf!=6&&this.bestand(this.getBrett().getStartRot())==4){
				System.out.println("Du musst erst 6 wuerfeln um zu starten");
			}
			
			//verucht eine andere figur rauszugehen wen er keine 6 gewuerfelt hat == false
			else if (letzterWurf!=6&&this.bestand(this.getBrett().getStartRot())<=4&&this.istFigurDrin(this.getBrett().getStartRot(), figurId)==true){
				System.out.println("Du musst erst 6 wuerfeln um raus zugehen");
			}
			
			//der spieler wuerfelt 6 und das startfeld von rot ist besetzt 
			else if(letzterWurf==6&&this.bestand(this.getBrett().getStartRot())<4&&startRot.istFeldBelegt()==true&&this.istFigurDrin(this.brett.getStartRot(), figurId)==true){
				//die if abfrage schaut ob die figur die gleiche farbe hat wie deine figur
				if(startRot.getFigur().getFarbe()==nowFigur.getFarbe()){
					System.out.println("Du musst dich erst weg bewegen");		
				}
				else if(startRot.getFigur().getFarbe()!=nowFigur.getFarbe()){
					System.out.println("Figur auf Startfeld von Rot geschlagen");
					this.schlagen(0);
				}
			}
			
			//Bringt eine neue figur raus 
			else if(letzterWurf==6&&this.bestand(this.getBrett().getStartRot())<4&&startRot.istFeldBelegt()==false&&this.istFigurDrin(this.brett.getStartRot(), figurId)==true){
//				System.out.println("Wahele eine figur um raus zu gehen oder\nmit einer anderen weiter zu laufen");
				
				if (this.istFigurDrin(this.getBrett().getStartRot(), figurId)==true){
					this.startPosZuweisen(spielerAmZug, figurId);
					System.out.println("Du darfst nochmal wuerfeln");
				}
			}
			
			//-----------LAUFEN-----------
			else if(this.bestand(this.getBrett().getStartRot())<4&&this.istFigurDrin(this.brett.getStartRot(), figurId)!=true){
				if(neuePos<=indexMax&&letztePos>=0){
					
					Spielfeld neuesFeld=getBrett().getWeg().get(neuePos);
					if(neuesFeld.istFeldBelegt()==false){
						neuesFeld.setFigur(nowFigur);
						getBrett().getWeg().get(letztePos).setFigur(null);
						System.out.println("Deine Figur: "+nowFigur.getId()+" sitzt auf dem Feld: "+neuesFeld.getId());
					}		
					else if(neuesFeld.istFeldBelegt()==true){	
						if(neuesFeld.getFigur().getFarbe()!=nowFigur.getFarbe()){
							this.schlagen(neuePos);
							neuesFeld.setFigur(nowFigur);
							getBrett().getWeg().get(letztePos).setFigur(null);
							System.out.println("Deine Figur: "+nowFigur.getId()+" sitzt auf dem Feld: "+neuesFeld.getId());	
						}else{
							System.out.println("Du kannst deine Figur nicht schlagen !");
						}	
					}
				}
				//ins ENDFELD reingehen
				else {
					
					int differenz = neuePos-indexMax;
					int endPos=getBrett().getEndRot().indexOf(nowFigur.getFeld());
					neuePos=endPos+letzterWurf;
					
					//diese if abfrage ist wen figur im endfeld drin ist und weiter laeuft
					if(differenz<=4&&neuePos<brett.getEndRot().size()&&this.istFigurDrin(brett.getEndRot(), figurId)==true){
						Spielfeld endFeld=getBrett().getEndRot().get(neuePos);
						
						if(endFeld.getFigur()==null){
							if(this.kannUeberhoeln(this.brett.getEndRot(),endPos+1, neuePos)==true){
								endFeld.setFigur(nowFigur);
								brett.getEndRot().get(endPos).setFigur(null);
								System.out.println("Deine Figur: "+nowFigur.getId()+ " sitzt auf dem Feld: "+getBrett().getEndRot().get(neuePos).getId());
							}else{
								System.out.println("Du kannst nicht ueberholen !");
							}
						}else{
							System.out.println("Feld ist besetzt !");
						}
					}
					//diese if abfrage ist wen figur von weg ins endfeld drin rein will
					else if(differenz<=4&&differenz>0){
						Spielfeld endFeld=getBrett().getEndRot().get(differenz-1);
						
						if(endFeld.getFigur()==null){
							if(this.kannUeberhoeln(this.brett.getEndRot(),0, differenz-1)==true){
								endFeld.setFigur(nowFigur);
								brett.getWeg().get(letztePos).setFigur(null);
								System.out.println("Deine Figur: "+nowFigur.getId()+ " sitzt auf dem Feld: "+getBrett().getEndRot().get(differenz-1).getId());
							}else{
								System.out.println("Du kannst nicht ueberholen !");
							}
						}else{
							System.out.println("Feld ist besetzt !");
						}
					}
					
					else{
						System.out.println("Du kannst mit der Figur nicht weitergehen !");
					}
				}
			}
		}
		
		
		
		// ------------------------BLAU---------------------------
		else if (nowFigur.getFarbe()==FarbEnum.BLAU){
			
			
			//zum ersten mal raus kommen 
			if (letzterWurf==6&&this.bestand(this.getBrett().getStartBlau())==4){
				this.startPosZuweisen(spielerAmZug, figurId);
				System.out.println("Du darfst nochmal wuerfeln");
			}
			
			//der spieler moechte raus gehen aber er hat keine 6 gewuerfelt 
			else if (letzterWurf!=6&&this.bestand(this.getBrett().getStartBlau())==4){
				System.out.println("Du musst erst 6 wuerfeln um zu starten");
			}
			
			//verucht eine andere figur rauszugehen wen er keine 6 gewuerfelt hat == false
			else if (letzterWurf!=6&&this.bestand(this.getBrett().getStartBlau())<=4&&this.istFigurDrin(this.getBrett().getStartBlau(), figurId)==true){
				System.out.println("Du musst erst 6 wuerfeln um raus zugehen");
			}
			
			//der spieler wuerfelt 6 und das startfeld von rot ist besetzt 
			else if(letzterWurf==6&&this.bestand(this.getBrett().getStartBlau())<4&&startBlau.istFeldBelegt()==true&&this.istFigurDrin(this.brett.getStartBlau(), figurId)==true){
				//die if abfrage schaut ob die figur die gleiche farbe hat wie deine figur
				if(startBlau.getFigur().getFarbe()==nowFigur.getFarbe()){
					System.out.println("Du musst dich erst weg bewegen");
				}
				else if(startBlau.getFigur().getFarbe()!=nowFigur.getFarbe()){
					System.out.println("Figur auf Startfeld von Blau geschlagen");
					this.schlagen(10);
				}
			}
			
			//Bringt eine neue figur raus 
			else if(letzterWurf==6&&this.bestand(this.getBrett().getStartBlau())<4&&startBlau.istFeldBelegt()==false&&this.istFigurDrin(this.brett.getStartBlau(), figurId)==true){
//					System.out.println("Wahele eine figur um raus zu gehen oder\nmit einer anderen weiter zu laufen !!");
				if (this.istFigurDrin(this.getBrett().getStartBlau(), figurId)==true){
					this.startPosZuweisen(spielerAmZug, figurId);
					System.out.println("Du darfst nochmal wuerfeln");
				}
			}
			
			//-----------LAUFEN-----------
			else if(this.bestand(this.getBrett().getStartBlau())<4&&this.istFigurDrin(this.brett.getStartBlau(), figurId)!=true){
				if(neuePos>=11&&neuePos<=39&&letztePos>=10){
					Spielfeld neuesFeld=getBrett().getWeg().get(neuePos);
					if(neuesFeld.istFeldBelegt()==false){
						neuesFeld.setFigur(nowFigur);
						getBrett().getWeg().get(letztePos).setFigur(null);
						System.out.println("Deine Figur: "+nowFigur.getId()+" sitzt auf dem Feld: "+neuesFeld.getId());
					}		
					else if(neuesFeld.istFeldBelegt()==true){	
						if(neuesFeld.getFigur().getFarbe()!=nowFigur.getFarbe()){
							this.schlagen(neuePos);
							neuesFeld.setFigur(nowFigur);
							getBrett().getWeg().get(letztePos).setFigur(null);
							System.out.println("Deine Figur: "+nowFigur.getId()+" sitzt auf dem Feld: "+neuesFeld.getId());
						}
						else{
							System.out.println("Du kannst deine Figur nicht schlagen !");
						}	
					}
				}
				//hier kommst du rein wen du ueber das maximum gehst (ueberschreitest feld 40)
				else{	
					int newIndex=((neuePos-indexMax)-1);
					int wegMaxBlau = 9;
					
					if(newIndex<0)
						newIndex=neuePos;
						
						Spielfeld neuesFeld2=this.brett.getWeg().get(newIndex);
						
						//von feld 1 bis zum feld 10 laufen
						if(newIndex>=0&&newIndex<=9&&this.istFigurDrin(this.brett.getEndBlau(), figurId)==false){
							
							if(neuesFeld2.istFeldBelegt()==false){
								neuesFeld2.setFigur(nowFigur);
								brett.getWeg().get(letztePos).setFigur(null);
								System.out.println("Deine Figur: "+nowFigur.getId()+" sitzt auf dem Feld: "+neuesFeld2.getId());
							}
							
							else if(neuesFeld2.istFeldBelegt()==true){
								if(neuesFeld2.getFigur().getFarbe()!=nowFigur.getFarbe()){
									
									this.schlagen(newIndex);
									neuesFeld2.setFigur(nowFigur);
									brett.getWeg().get(letztePos).setFigur(null);
									System.out.println("Deine Figur: "+nowFigur.getId()+" sitzt auf dem Feld: "+neuesFeld2.getId());
								}
								else{
									System.out.println("Du kannst deine Figur nicht schlagen !");
								}	
							}
						}
						//ins ENDFELD reingehen
						else {
							
							int differenz = neuePos-wegMaxBlau;
							int endPos=getBrett().getEndBlau().indexOf(nowFigur.getFeld());
							neuePos=endPos+letzterWurf;
							
							//diese if abfrage ist wen figur im endfeld drin ist und weiter laeuft
							if(differenz<=4&&neuePos<brett.getEndBlau().size()&&this.istFigurDrin(brett.getEndBlau(), figurId)==true){
								Spielfeld endFeld=getBrett().getEndBlau().get(neuePos);
								
								if(endFeld.getFigur()==null){
									if(this.kannUeberhoeln(this.brett.getEndBlau(),endPos+1, neuePos)==true){
										endFeld.setFigur(nowFigur);
										brett.getEndBlau().get(endPos).setFigur(null);
										System.out.println("Deine Figur: "+nowFigur.getId()+ " sitzt auf dem Feld: "+getBrett().getEndBlau().get(neuePos).getId());
									}else{
										System.out.println("Du kannst nicht ueberholen !");
									}
								}else{
									System.out.println("Feld ist besetzt !");
								}
							}
							//diese if abfrage ist wen figur von weg ins endfeld drin rein will
							else if(differenz<=4&&differenz>0){
								Spielfeld endFeld=getBrett().getEndBlau().get(differenz-1);
								
								if(endFeld.getFigur()==null){
									if(this.kannUeberhoeln(this.brett.getEndBlau(),0, differenz-1)==true){
										endFeld.setFigur(nowFigur);
										brett.getWeg().get(letztePos).setFigur(null);
										System.out.println("Deine Figur: "+nowFigur.getId()+ " sitzt auf dem Feld: "+getBrett().getEndBlau().get(differenz-1).getId());
									}else{
										System.out.println("Du kannst nicht ueberholen !");
									}
								}else{
									System.out.println("Feld ist besetzt !");
								}
							}
								
							else{
								System.out.println("Du kannst mit der Figur nicht weitergehen !");
							}
						}
				}
			}
		}
		
		
		// ------------------------GRUEN---------------------------
		else if (nowFigur.getFarbe()==FarbEnum.GRUEN){
			
			//zum ersten mal raus kommen 
			if (letzterWurf==6&&this.bestand(this.getBrett().getStartGruen())==4){
				this.startPosZuweisen(spielerAmZug, figurId);
				System.out.println("Du darfst nochmal wuerfeln");
			}
			
			//der spieler moechte raus gehen aber er hat keine 6 gewuerfelt 
			else if (letzterWurf!=6&&this.bestand(this.getBrett().getStartGruen())==4){
				System.out.println("Du musst erst 6 wuerfeln um zu starten");
			}				
			
			//verucht eine andere figur rauszugehen wen er keine 6 gewuerfelt hat == false
			else if (letzterWurf!=6&&this.bestand(this.getBrett().getStartGruen())<=4&&this.istFigurDrin(this.getBrett().getStartGruen(), figurId)==true){
				System.out.println("Du musst erst 6 wuerfeln um raus zugehen");
			}
			
			//der spieler wuerfelt 6 und das startfeld von rot ist besetzt 
			else if(letzterWurf==6&&this.bestand(this.getBrett().getStartGruen())<4&&startGruen.istFeldBelegt()==true&&this.istFigurDrin(this.brett.getStartGruen(), figurId)==true){
				//die if abfrage schaut ob die figur die gleiche farbe hat wie deine figur
				if(startGruen.getFigur().getFarbe()==nowFigur.getFarbe()){
					System.out.println("Du musst dich erst weg bewegen");
				}
				else if(startGruen.getFigur().getFarbe()!=nowFigur.getFarbe()){
					System.out.println("Figur auf Startfeld von Gruen geschlagen");
					this.schlagen(20);
				}
			}
			
			//Bringt eine neue figur raus 
			else if(letzterWurf==6&&this.bestand(this.getBrett().getStartGruen())<4&&startGruen.istFeldBelegt()==false&&this.istFigurDrin(this.brett.getStartGruen(), figurId)==true){
//				System.out.println("Wahele eine figur um raus zu gehen oder\nmit einer anderen weiter zu laufen !!");
				if (this.istFigurDrin(this.getBrett().getStartGruen(), figurId)==true){
					this.startPosZuweisen(spielerAmZug, figurId);
					System.out.println("Du darfst nochmal wuerfeln");
				}
			}
			
			//-----------LAUFEN-----------
			else if(this.bestand(this.getBrett().getStartGruen())<4&&this.istFigurDrin(this.brett.getStartGruen(), figurId)!=true){
				if(neuePos>=21&&neuePos<=39&&letztePos>=20){
					Spielfeld neuesFeld=getBrett().getWeg().get(neuePos);
					if(neuesFeld.istFeldBelegt()==false){
						neuesFeld.setFigur(nowFigur);
						getBrett().getWeg().get(letztePos).setFigur(null);
						System.out.println("Deine Figur: "+nowFigur.getId()+" sitzt auf dem Feld: "+neuesFeld.getId());
					}		
					else if(neuesFeld.istFeldBelegt()==true){	
						if(neuesFeld.getFigur().getFarbe()!=nowFigur.getFarbe()){
							this.schlagen(neuePos);
							neuesFeld.setFigur(nowFigur);
							getBrett().getWeg().get(letztePos).setFigur(null);
							System.out.println("Deine Figur: "+nowFigur.getId()+" sitzt auf dem Feld: "+neuesFeld.getId());
						}
						else{
							System.out.println("Du kannst deine Figur nicht schlagen !");
						}	
					}
				}
				//hier kommst du rein wen du ueber das maximum gehst (ueberschreitest feld 40)
				else{	
					int newIndex=((neuePos-indexMax)-1);
					int wegMaxGruen = 19;
					
					if(newIndex<0)
						newIndex=neuePos;
						
						Spielfeld neuesFeld2=this.brett.getWeg().get(newIndex);
						
						//von feld 1 bis zum feld 20 laufen
						if(newIndex>=0&&newIndex<=19&&this.istFigurDrin(this.brett.getEndGruen(), figurId)==false){
							
							if(neuesFeld2.istFeldBelegt()==false){
								neuesFeld2.setFigur(nowFigur);
								brett.getWeg().get(letztePos).setFigur(null);
								System.out.println("Deine Figur: "+nowFigur.getId()+" sitzt auf dem Feld: "+neuesFeld2.getId());
							}
							
							else if(neuesFeld2.istFeldBelegt()==true){
								if(neuesFeld2.getFigur().getFarbe()!=nowFigur.getFarbe()){
									this.schlagen(newIndex);
									neuesFeld2.setFigur(nowFigur);
									brett.getWeg().get(letztePos).setFigur(null);
									System.out.println("Deine Figur: "+nowFigur.getId()+" sitzt auf dem Feld: "+neuesFeld2.getId());
								}
								else{
									System.out.println("Du kannst deine Figur nicht schlagen !");
								}	
							}
						}
						//ins ENDFELD reingehen
						else {
							
							int differenz = neuePos-wegMaxGruen;
							int endPos=getBrett().getEndGruen().indexOf(nowFigur.getFeld());
							neuePos=endPos+letzterWurf;
							
							//diese if abfrage ist wen figur im endfeld drin ist und weiter laeuft
							if(differenz<=4&&neuePos<brett.getEndGruen().size()&&this.istFigurDrin(brett.getEndGruen(), figurId)==true){
								Spielfeld endFeld=getBrett().getEndGruen().get(neuePos);
								
								if(endFeld.getFigur()==null){
									if(this.kannUeberhoeln(this.brett.getEndGruen(),endPos+1, neuePos)==true){
										endFeld.setFigur(nowFigur);
										brett.getEndGruen().get(endPos).setFigur(null);
										System.out.println("Deine Figur: "+nowFigur.getId()+ " sitzt auf dem Feld: "+getBrett().getEndGruen().get(neuePos).getId());
									}else{
										System.out.println("Du kannst nicht ueberholen !");
									}
								}else{
									System.out.println("Feld ist besetzt !");
								}
							}
							//diese if abfrage ist wen figur von weg ins endfeld drin rein will
							else if(differenz<=4&&differenz>0){
								Spielfeld endFeld=getBrett().getEndGruen().get(differenz-1);
								
								if(endFeld.getFigur()==null){
									if(this.kannUeberhoeln(this.brett.getEndGruen(),0, differenz-1)==true){
										endFeld.setFigur(nowFigur);
										brett.getWeg().get(letztePos).setFigur(null);
										System.out.println("Deine Figur: "+nowFigur.getId()+ " sitzt auf dem Feld: "+getBrett().getEndGruen().get(differenz-1).getId());
									}else{
										System.out.println("Du kannst nicht ueberholen !");
									}
								}else{
									System.out.println("Feld ist besetzt !");
								}
							}
							
							else{
								System.out.println("Du kannst mit der Figur nicht weitergehen !");
							}
						}
				}
			}
		}
		
		// ------------------------GELB---------------------------
		else if (nowFigur.getFarbe()==FarbEnum.GELB){
			
			//zum ersten mal raus kommen 
			if (letzterWurf==6&&this.bestand(this.getBrett().getStartGelb())==4){
				this.startPosZuweisen(spielerAmZug, figurId);
				System.out.println("Du darfst nochmal wuerfeln");
			}
			
			//der spieler moechte raus gehen aber er hat keine 6 gewuerfelt 
			else if (letzterWurf!=6&&this.bestand(this.getBrett().getStartGelb())==4){
				System.out.println("Du musst erst 6 wuerfeln um zu starten");
			}				
			
			//verucht eine andere figur rauszugehen wen er keine 6 gewuerfelt hat == false
			else if (letzterWurf!=6&&this.bestand(this.getBrett().getStartGelb())<=4&&this.istFigurDrin(this.getBrett().getStartGelb(), figurId)==true){
				System.out.println("Du musst erst 6 wuerfeln um raus zugehen");
			}
			
			//der spieler wuerfelt 6 und das startfeld von rot ist besetzt 
			else if(letzterWurf==6&&this.bestand(this.getBrett().getStartGelb())<4&&startGelb.istFeldBelegt()==true&&this.istFigurDrin(this.brett.getStartGelb(), figurId)==true){
				//die if abfrage schaut ob die figur die gleiche farbe hat wie deine figur
				if(startGelb.getFigur().getFarbe()==nowFigur.getFarbe()){
					System.out.println("Du musst dich erst weg bewegen");
				}
				else if(startGelb.getFigur().getFarbe()!=nowFigur.getFarbe()){
					System.out.println("Figur auf Startfeld von Gelb geschlagen");
					this.schlagen(30);
				}
			}
			
			//Bringt eine neue figur raus 
			else if(letzterWurf==6&&this.bestand(this.getBrett().getStartGelb())<4&&startGelb.istFeldBelegt()==false&&this.istFigurDrin(this.brett.getStartGelb(), figurId)==true){
//				System.out.println("Wahele eine figur um raus zu gehen oder\nmit einer anderen weiter zu laufen !!");
				if (this.istFigurDrin(this.getBrett().getStartGelb(), figurId)==true){
					this.startPosZuweisen(spielerAmZug, figurId);
					System.out.println("Du darfst nochmal wuerfeln");
				}
			}
			
			//-----------LAUFEN-----------
			else if(this.bestand(this.getBrett().getStartGelb())<4&&this.istFigurDrin(this.brett.getStartGelb(), figurId)!=true){
				if(neuePos>=31&&neuePos<=39&&letztePos>=30){
					Spielfeld neuesFeld=getBrett().getWeg().get(neuePos);
					if(neuesFeld.istFeldBelegt()==false){
						neuesFeld.setFigur(nowFigur);
						getBrett().getWeg().get(letztePos).setFigur(null);
						System.out.println("Deine Figur: "+nowFigur.getId()+" sitzt auf dem Feld: "+neuesFeld.getId());
					}		
					else if(neuesFeld.istFeldBelegt()==true){	
						if(neuesFeld.getFigur().getFarbe()!=nowFigur.getFarbe()){
							this.schlagen(neuePos);
							neuesFeld.setFigur(nowFigur);
							getBrett().getWeg().get(letztePos).setFigur(null);
							System.out.println("Deine Figur: "+nowFigur.getId()+" sitzt auf dem Feld: "+neuesFeld.getId());
						}
						else{
							System.out.println("Du kannst deine Figur nicht schlagen !");
						}	
					}
				}
				//hier kommst du rein wen du ueber das maximum gehst (ueberschreitest feld 40)
				else{	
					int newIndex=((neuePos-indexMax)-1);
					int wegMaxGelb = 29;
					
					if(newIndex<0)
						newIndex=neuePos;
						
						Spielfeld neuesFeld2=this.brett.getWeg().get(newIndex);
						
						//von feld 1 bis zum feld 30 laufen
						if(newIndex>=0&&newIndex<=29&&this.istFigurDrin(this.brett.getEndGelb(), figurId)==false){
							
							if(neuesFeld2.istFeldBelegt()==false){
								neuesFeld2.setFigur(nowFigur);
								brett.getWeg().get(letztePos).setFigur(null);
								System.out.println("Deine Figur: "+nowFigur.getId()+" sitzt auf dem Feld: "+neuesFeld2.getId());
							}
							
							else if(neuesFeld2.istFeldBelegt()==true){
								if(neuesFeld2.getFigur().getFarbe()!=nowFigur.getFarbe()){
									this.schlagen(newIndex);
									neuesFeld2.setFigur(nowFigur);
									brett.getWeg().get(letztePos).setFigur(null);
									System.out.println("Deine Figur: "+nowFigur.getId()+" sitzt auf dem Feld: "+neuesFeld2.getId());
								}
								else{
									System.out.println("Du kannst deine Figur nicht schlagen !");
								}	
							}
						}
						//ins ENDFELD reingehen
						else {
							
							int differenz = neuePos-wegMaxGelb;
							int endPos=getBrett().getEndGelb().indexOf(nowFigur.getFeld());
							neuePos=endPos+letzterWurf;
							
							//diese if abfrage ist wen figur im endfeld drin ist und weiter laeuft
							if(differenz<=4&&neuePos<brett.getEndGelb().size()&&this.istFigurDrin(brett.getEndGelb(), figurId)==true){
								Spielfeld endFeld=getBrett().getEndGelb().get(neuePos);
								
								if(endFeld.getFigur()==null){
									if(this.kannUeberhoeln(this.brett.getEndGelb(),endPos+1, neuePos)==true){
										endFeld.setFigur(nowFigur);
										brett.getEndGelb().get(endPos).setFigur(null);
										System.out.println("Deine Figur: "+nowFigur.getId()+ " sitzt auf dem Feld: "+getBrett().getEndGelb().get(neuePos).getId());
									}else{
										System.out.println("Du kannst nicht ueberholen !");
									}
								}else{
									System.out.println("Feld ist besetzt !");
								}
							}
							//diese if abfrage ist wen figur von weg ins endfeld drin rein will
							else if(differenz<=4&&differenz>0){
								Spielfeld endFeld=getBrett().getEndGelb().get(differenz-1);
								
								if(endFeld.getFigur()==null){
									if(this.kannUeberhoeln(this.brett.getEndGelb(),0, differenz-1)==true){
										endFeld.setFigur(nowFigur);
										brett.getWeg().get(letztePos).setFigur(null);
										System.out.println("Deine Figur: "+nowFigur.getId()+ " sitzt auf dem Feld: "+getBrett().getEndGelb().get(differenz-1).getId());
									}else{
										System.out.println("Du kannst nicht ueberholen !");
									}
								}else{
									System.out.println("Feld ist besetzt !");
								}
							}
							
							else{
								System.out.println("Du kannst mit der Figur nicht weitergehen !");
							}
						}
				}
			}
		}
		
		System.out.flush();
		System.setOut(old);
		
	}	

	/**
	 * das Spielbrett wird zurueckgegeben
	 * @return brett ist das Spielbrett
	 */
	@XmlElement(name="Spielbrett")
	public Spielbrett getBrett() {
		return this.brett;
	}
 
	/**
	 * das Spielbrett wird gesetzt
	 * @param brett ist das Spielbrett
	 */
	public void setBrett(Spielbrett brett) {
		this.brett = brett;
	}

	/**
	 * gibt den Spieler der gerade am Zug ist zurueck
	 * @return spielerAmZug der Spieler der am Zug ist
	 */
	public Spieler getSpielerAmZug() {
		
		return spielerAmZug;
	}
 
	/**
	 * der Spieler der gerade am Zug ist wird gesetzt
	 * @param spielerAmZug der Spieler der am Zug ist
	 */
	@XmlElement(name="SpielerAmZug")
	public void setSpielerAmZug(Spieler spielerAmZug) {
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		
		this.spielerAmZug = spielerAmZug;
		System.out.println("-----"+spielerAmZug.getFarbe()+"----");
		
		System.out.flush();
		System.setOut(old);
	}	
	
	@XmlElement(name="Spieler")
	public ArrayList<Spieler> getSpielerlist() {
		return spielerlist;
	}

	public void setSpielerlist(ArrayList<Spieler> spielerlist) {
		this.spielerlist = spielerlist;
	}

	@XmlTransient
	@Override
	public Integer getWuerfelZahl() {
		
		return wuerfelZahl;
	}

	@Override
	public void setWuerfelZahl(Integer wuerfelZahl) {
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		
		
		if (wuerfelZahl!=null && wuerfelZahl >=1 ){
			System.out.println( spielerAmZug.getName()+" hat " + wuerfelZahl + " gewuerfelt");
//			System.out.println(wuerfelZahl);
		}
		this.wuerfelZahl = wuerfelZahl;
		
		System.out.flush();
		System.setOut(old);
	}
	
	
	
	@Override
	public void wuerfeln() {
		
		setWuerfelZahl(spielerAmZug.wuerfeln());
		
	}

	


	
 
}