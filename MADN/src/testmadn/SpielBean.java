package testmadn;

import java.io.Serializable;
import java.util.*;

import testmadn.FarbEnum;
import testmadn.Spieler;


/**
 * Ein ganzes Spiel wird erzeugt
 * @author Gruppe A2
 */
public class SpielBean implements iBediener, Serializable {
	private static final long serialVersionUID = 1L;
	
	private Spieler spielerAmZug;
	private ArrayList <Spieler> spielerlist;
	private Spielbrett brett;
	private int spielerAnzahl;
	private Integer wuerfelZahl;
	private DatenzugriffCSV csv;
	private Integer anzahlWeb=null;
	private ArrayList<String>name1=new ArrayList<String>();
	private String[]name=new String[2];
	

	@Override
	public Integer getAnzahlWeb() {
		return anzahlWeb;
	}
	@Override
	public void setAnzahlWeb(Integer anzahlWeb) {
		this.anzahlWeb = anzahlWeb;
	}
	
	@Override
	public String[] spielerName(){
		
//		if(spielerlist.size()>0){
//			for(Spieler sp:spielerlist){
//				if(sp!=null){
//					name.add(sp.getName());
//					
//				}
//				
//			}
//		}
		
		if(spielerlist.get(0)!=null){
			name[0]=spielerlist.get(0).getName();
		}
		if(spielerlist.get(1)!=null){
			name[1]=spielerlist.get(1).getName();
		}
		
		return name;
	}
	@Override
	public ArrayList<String> nameSpieler(){
		String c=null;
//		System.out.println(spielerName().length);
		for(int i=0;i<spielerName().length;i++){
			c=spielerName()[i];
//			System.out.println(c);
//			break;
			name1.add(c);
			
		}
		
		
		
		return name1; 
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
		setCsv(new DatenzugriffCSV(this));
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
		Spieler x =new Spieler(name,this.bestimmeFarbe(Farbe),this.bestimmeKI(KI)) ;
		spielerlist.add(x); // add(0<--index vom spieler,spieler);
		setSpielerAmZug(x);
		spielerAnzahl++;
		System.out.println("Teilnehmer: --> "+x.getName());
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
		
		
		
		
	}
	
	/**
	 * Zuweisung der Startposition der jeweiligen Spielfiguren der Spieler
	 *  in Abhaengigkeit der Farbe
	 *  @param spieler ist die liste der figuren 
	 *  @param figur ist die Spielfigur die zu bewegen ist
	 */
	public void startPosZuweisen(Spieler spieler, int figur){
		
		
		
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
				}else{
					System.out.println("nicht gleichzeitig rausgehen bitte !");
				}
				
			}
		}
		
	}
	
	/**
	 * Eine Spielfigur eines Gegners wird geschlagen falls man auf das selbe
	 * Spielfeld kommt
	 * @param feldId ist das feld wo die Figur die geschlagen wird und zurueck in die Startbox kommt
	 */
	public void schlagen(int feldId) {
		Spielfigur schlageFigur = brett.getWeg().get(feldId).getFigur();
		
		if(schlageFigur==null){
			throw new RuntimeException("es gibt keine figur zu schlagen");
		}
		
		if (schlageFigur.getFarbe() == FarbEnum.ROT) {
			for(Spielfeld f:brett.getStartRot()){
				if(f.getFigur()==null){
					f.setFigur(schlageFigur);
					brett.getWeg().get(feldId).setFigur(null);
					System.out.println("ROTE Figur geschlagen");
				}
			}
		}
		
		else if (schlageFigur.getFarbe() == FarbEnum.BLAU) {
			for(Spielfeld f:brett.getStartBlau()){
				if(f.getFigur()==null){
					f.setFigur(schlageFigur);
					brett.getWeg().get(feldId).setFigur(null);
					System.out.println("BLAUE Figur geschlagen");
				}
			}
		}
		
		else if (schlageFigur.getFarbe() == FarbEnum.GRUEN) {
			for(Spielfeld f:brett.getStartGruen()){
				if(f.getFigur()==null){
					f.setFigur(schlageFigur);
					brett.getWeg().get(feldId).setFigur(null);
					System.out.println("GRUENE Figur geschlagen");
				}
			}
		}
		
		else if (schlageFigur.getFarbe() == FarbEnum.GELB) {
			for(Spielfeld f:brett.getStartGelb()){
				if(f.getFigur()==null){
					f.setFigur(schlageFigur);
					brett.getWeg().get(feldId).setFigur(null);
					System.out.println("GELBE Figur geschlagen");
				}
			}
		}
	}
	
	@Override
	public void beenden(){
		
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
		
	}	

	/**
	 * das Spielbrett wird zurueckgegeben
	 * @return brett ist das Spielbrett
	 */
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
	public void setSpielerAmZug(Spieler spielerAmZug) {
		this.spielerAmZug = spielerAmZug;
		System.out.println("-----"+spielerAmZug.getFarbe()+"----");
	}	
	public ArrayList<Spieler> getSpielerlist() {
		return spielerlist;
	}

	public void setSpielerlist(ArrayList<Spieler> spielerlist) {
		this.spielerlist = spielerlist;
	}

	@Override
	public Integer getWuerfelZahl() {
		
		return wuerfelZahl;
	}

	@Override
	public void setWuerfelZahl(Integer wuerfelZahl) {
		
		
		
		if (wuerfelZahl!=null && wuerfelZahl >=1 ){
			System.out.println( spielerAmZug.getName()+" hat " + wuerfelZahl + " gewuerfelt");
//			System.out.println(wuerfelZahl);
		}
		this.wuerfelZahl = wuerfelZahl;
	}
	
	
	
	@Override
	public void wuerfeln() {
		setWuerfelZahl(spielerAmZug.wuerfeln());
	}

	public DatenzugriffCSV getCsv() {
		return csv;
	}

	public void setCsv(DatenzugriffCSV csv) {
		this.csv = csv;
	}

	
 
}