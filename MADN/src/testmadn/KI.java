package testmadn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;





 
 public abstract class KI implements Serializable{
	private static final long serialVersionUID = 1L;
 
	private SpielBean spiel;
	private Spieler spieler;
	private Spielfigur figur;
	private Spielbrett brett;
	private ArrayList <Integer> wegUebrig = new ArrayList <Integer>();
	protected boolean kannSchlagen = false;
	protected boolean kannLaufen = false;
	protected boolean kannRaus = false;
 
 
	/**
	 * der Konstruktor der Klasse mit der Komposition zu Spieler
	 * @param spieler ist der Spieler
	 */
 
	public KI (SpielBean spiel){
		this.setKannSchlagen(kannSchlagen);
		this.setKannLaufen(kannLaufen);
		this.setKannRaus(kannRaus);
		this.setSpiel(spiel);
	}
	
	private int uberholenPruefen(ArrayList<Spielfeld>feld){
		int figurIndex=0;
		for(Spielfeld endFeld : feld ){
			if(endFeld.istFeldBelegt()==true){
				figurIndex=feld.indexOf(endFeld);
			}
		}
		return figurIndex;
	}
	
	private int MeinWegBestand(FarbEnum farbe){
		farbe = this.spiel.getSpielerAmZug().getFarbe();
		int figurAnz=0;
		for(Spielfeld feld : this.spiel.getBrett().getWeg()){
			if((feld.istFeldBelegt()==true) && (feld.getFigur().getFarbe()==farbe)){
				figurAnz++;
				
			}
		}
		return figurAnz;
		
	}
 
	/**
	 * Vorgangsberechnungen des naechsten Zuges der KI
	 */
	public abstract Spielfigur ermittleFigur();
 
 
//	 GETTER SETTER ANFANG--------
 
	/**
	 * das Spiel wird zurueck gegeben
	 * @return spiel ist das Spiel
	 */
	public SpielBean getSpiel() {
		return spiel;
	}
 
	/**
	 * das Spiel wird gesetzt
	 * @param spiel ist das Spiel
	 */
 
	public void setSpiel(SpielBean spiel) {
		this.spiel = spiel;
	}
 
	/**
	 * die boolsche Antwort ob geschlagen werden kann wird zurueck gegeben
	 * @return kannSchlagen ist die boolsche Antwort ob geschlagen werden kann
	 */
 
	public boolean getKannSchlagen(){
		return kannSchlagen;
	}
	/**
	 * die boolsche Antwort ob geschlagen werden kann wird gesetzt
	 * @param kannSchlagen ist die boolsche Antwort ob geschlagen werden kann
	 */
 
 
	public void setKannSchlagen(boolean kannSchlagen){
		this.kannSchlagen=kannSchlagen;
	}
 /**
  * die boolsche Antwort ob gelaufen werden kann wird zurueck gegeben
  * @return kannLaufen ist die boolsche Antwort ob gelaufen werden kann
  */
	public boolean getKannLaufen(){
		return kannLaufen;
	}
 
	/**
	 * die boolsche Antwort ob gelaufen werden kann wird gesetzt
	 * @param kannLaufen ist die boolsche Antwort ob gelaufen werden kann
	 */
 
	public void setKannLaufen(boolean kannLaufen){
		this.kannLaufen=kannLaufen;
	}
 
	/**
	 * die boolsche Antwort ob man rausgehen kann wird zurueck gegeben
	 * @return kannRaus ist die boolsche Antwort ob rausgegangen werden kann
	 */
 
	public boolean getKannRaus(){
		return kannRaus;
	}
 
	/**
	 * die boolsche Antwort ob man rausgehen kann wird gesetzt
	 * @param kannRaus ist die boolsche Antwort ob rausgegangen werden kann
	 */
 
	public void setKannRaus(boolean kannRaus){
		this.kannRaus=kannRaus;
	}
 
	/**
	 * der Spieler wird zurueckgegeben
	 * @return spieler ist der Spieler
	 */
	public Spieler getSpieler(){
		return this.spieler;
	}
 
//	GETTER & SETTER ENDE--------
 
	/**
	 * mit dieser Methode wird ermittelt ob der Spieler am Zug also die KI in richtung Endfeld laufen kann 
	 * @return ergebnis ist das Ergebnis welche Spielfigur bewegt werden soll
	 */
	public Spielfigur ErmittleKannLaufenUndWer() {
		Spielfigur ergebnis= null;
		kannLaufen=false;
		
		try{
			if (this.spiel.getSpielerAmZug().getFarbe() == FarbEnum.ROT) {
				int wegMax = 39;
				int endMax=3;
				int bedenkLaufLimit=40;
				
				//kann niocht laufen wen keiner draussen ist
				if (this.spiel.bestand(this.spiel.getBrett().getStartRot()) == 4) {
					kannLaufen=false;
				}
				//wenn 1 figur draussen ist (und ob sie im weg ist oder endfeld)
				else if (this.spiel.bestand(this.spiel.getBrett().getStartRot()) == 3) {
					if(MeinWegBestand(FarbEnum.ROT)==1){
						wegUebrig.clear();
						for(Spielfeld feld : this.spiel.getBrett().getWeg() ){
							if(feld.istFeldBelegt()==true && feld.getFigur().getFarbe()==this.spiel.getSpielerAmZug().getFarbe()){
								int aktuellePos=spiel.getBrett().getWeg().indexOf(feld);
								if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4){
									ergebnis = feld.getFigur();
									kannLaufen=true;
									break;
								}else if(spiel.getWuerfelZahl()==6){
									kannLaufen=false;
								}else{
									kannLaufen=true;
								}
							}
						}
					}
					else if(MeinWegBestand(FarbEnum.ROT)==0){
						wegUebrig.clear();
						for(Spielfeld endBox:this.spiel.getBrett().getEndRot()){
							if(endBox.istFeldBelegt()==true){
								if(this.spiel.getBrett().getEndRot().indexOf(endBox.getFigur())+this.spiel.getWuerfelZahl()<this.spiel.getBrett().getEndRot().size()){
									ergebnis = endBox.getFigur();
									kannLaufen=true;
									break;
								}else if (this.spiel.getWuerfelZahl()!=6){
									ergebnis = endBox.getFigur();
									kannLaufen=true;
									break;
								}else{
									kannLaufen=false;
								}
							}
						}
					}
				}
				//wenn 2 figuren draussen sind (wie sie sich verhalten/ und welche rausgeht)
				else if (this.spiel.bestand(this.spiel.getBrett().getStartRot())== 2) {
					//Wenn beide Figuren auf dem Weg sind
					if(MeinWegBestand(FarbEnum.ROT)==2){
						wegUebrig.clear();
						for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
							if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
								figur = wegFeld.getFigur();
								int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
								wegUebrig.add(wegMax - feldIndex);
							}
						}//Figur 1 drauﬂen
						ergebnis = this.spiel.getBrett().getWeg().get(wegMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
						int aktuellePos=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
						
						if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4 ){
							wegUebrig.clear();
							kannLaufen=true;
						}
						else{
							// Figur 2 drauﬂen
							ergebnis = this.spiel.getBrett().getWeg().get(wegMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
							int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
							
							if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4){
								wegUebrig.clear();
								kannLaufen=true;
							}else if(spiel.getWuerfelZahl()==6){
									kannLaufen=false;
							}else{
								kannLaufen=true;
							}
							
						}
					}//----- WEG = 1 ------ ENDFELD = 1 ----
					else if(MeinWegBestand(FarbEnum.ROT)==1){
						wegUebrig.clear();
						for(Spielfeld endBox:this.spiel.getBrett().getEndRot()){
							if(endBox.istFeldBelegt()==true){
								figur = endBox.getFigur();
								int aktuellePos = this.spiel.getBrett().getEndRot().indexOf(figur.getFeld());
								wegUebrig.add(endMax - aktuellePos);
								
							}
						}//Figur 1 drinnen
						ergebnis = this.spiel.getBrett().getEndRot().get(endMax - wegUebrig.get(0)).getFigur();
						int aktuellePos=spiel.getBrett().getEndRot().indexOf(ergebnis.getFeld());
						
						if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndRot().size()){
							wegUebrig.clear();
							kannLaufen=true;
						}else{
							for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
								if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
									figur = wegFeld.getFigur();
									int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
									wegUebrig.add(wegMax - feldIndex);
								}
							}
							//Figur 2 drauﬂen
							ergebnis = this.spiel.getBrett().getWeg().get(wegMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
							int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
							int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl()-wegMax-1;
							
							if(aktuellePos2+spiel.getWuerfelZahl()<bedenkLaufLimit){
								if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4 ){
									wegUebrig.clear();
									kannLaufen=true;
								}
							}else{
								if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4 && this.spiel.getBrett().getEndRot().get(endFeldBelegt2).istFeldBelegt()==false){
									wegUebrig.clear();
									kannLaufen=true;
								}else if(spiel.getWuerfelZahl()==6){
									kannLaufen=false;
								}else{
									kannLaufen=true;
								}
							}
							
						}
					}//Wenn beide Figuren im Endfeld sind
					else if(MeinWegBestand(FarbEnum.ROT)==0){
						wegUebrig.clear();
						for(Spielfeld endBox:this.spiel.getBrett().getEndRot()){
							if(endBox.istFeldBelegt()==true){
								figur = endBox.getFigur();
								int aktuellePos = this.spiel.getBrett().getEndRot().indexOf(figur.getFeld());
								wegUebrig.add(endMax - aktuellePos);
							}
						}//Figur 1 drinnen
						ergebnis = this.spiel.getBrett().getEndRot().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
						int aktuellePos=spiel.getBrett().getEndRot().indexOf(ergebnis.getFeld());
						
						if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndRot().size()){
							wegUebrig.clear();
							kannLaufen=true;
						}else{//Figur 2 drinnen
							ergebnis = this.spiel.getBrett().getEndRot().get(endMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
							int aktuellePos2=spiel.getBrett().getEndRot().indexOf(ergebnis.getFeld());
							int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl();
							
							if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getEndRot().size() && this.spiel.getBrett().getEndRot().get(endFeldBelegt2).istFeldBelegt()==false){
								wegUebrig.clear();
								kannLaufen=true;
							}else if(spiel.getWuerfelZahl()==6){
								kannLaufen=false;
							}else{
								kannLaufen=true;
							}
						}
						
					}
				}//wenn 3 figuren draussen sind (wie sie sich verhalten/ und welche rausgeht)
				else if (this.spiel.bestand(this.spiel.getBrett().getStartRot()) == 1) {
					//Wenn 3 Figuren auf dem Weg sind
					if(MeinWegBestand(FarbEnum.ROT)==3){
						wegUebrig.clear();
						for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
							if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
								figur = wegFeld.getFigur();
								int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
								wegUebrig.add(wegMax - feldIndex);
							}
						}
						//Figur 1 drauﬂen
						ergebnis = this.spiel.getBrett().getWeg().get(wegMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
						int aktuellePos=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
						
							
							if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4){
								wegUebrig.clear();
								kannLaufen=true;
							}else{
								// Figur 2 drauﬂen
								ergebnis = this.spiel.getBrett().getWeg().get(wegMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
								int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
								if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4){
									wegUebrig.clear();
									kannLaufen=true;
								}else{
									//Figur 3 drauﬂen
									ergebnis = this.spiel.getBrett().getWeg().get(wegMax - wegUebrig.get(wegUebrig.size()-3)).getFigur();
									int aktuellePos3=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
									if(aktuellePos3+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4){
										wegUebrig.clear();
										kannLaufen=true;
									}else if(spiel.getWuerfelZahl()==6){
										kannLaufen=false;
									}else{
										kannLaufen=true;
									}
								}
						}
					}
					//Wenn 2 Figuren auf dem Weg sind und 1 im Endfeld
					else if(MeinWegBestand(FarbEnum.ROT)==2){
						wegUebrig.clear();
						//Die Figur die drinnen ist versucht zuerst zu laufen
						for(Spielfeld endBox:this.spiel.getBrett().getEndRot()){
							if(endBox.istFeldBelegt()==true){
								figur = endBox.getFigur();
								int aktuellePos = this.spiel.getBrett().getEndRot().indexOf(figur.getFeld());
								wegUebrig.add(endMax - aktuellePos);
							}
						}//Figur 1 drinnen
						ergebnis = this.spiel.getBrett().getEndRot().get(endMax - wegUebrig.get(0)).getFigur();
						int aktuellePos=spiel.getBrett().getEndRot().indexOf(ergebnis.getFeld());
						if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndRot().size()){
							wegUebrig.clear();
							kannLaufen=true;
						}else{
							//Wenn die Figur drinnen nicht laufen kann werden die anderen Figuren drauﬂen berechnet
							for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
								if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
									figur = wegFeld.getFigur();
									int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
									wegUebrig.add(wegMax - feldIndex);
								}
							}//Figur 2 drauﬂen
							ergebnis = this.spiel.getBrett().getWeg().get(wegMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
							int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
							int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl()-wegMax-1;
							
							if(aktuellePos2+spiel.getWuerfelZahl()<bedenkLaufLimit){
								if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4){
									wegUebrig.clear();
									kannLaufen=true;
								}
							}else{
								if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4 &&
										this.spiel.getBrett().getEndRot().get(endFeldBelegt2).istFeldBelegt()==false &&
										this.uberholenPruefen(spiel.getBrett().getEndRot())>endFeldBelegt2){
									wegUebrig.clear();
									kannLaufen=true;
								}else{
									//Figur 3 drauﬂen
									ergebnis = this.spiel.getBrett().getWeg().get(wegMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
									int aktuellePos3=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
									int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl()-wegMax-1;
									
									if(aktuellePos3+spiel.getWuerfelZahl()<bedenkLaufLimit){
										if(aktuellePos3+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4){
											wegUebrig.clear();
											kannLaufen=true;
										}
									}else{
										if(aktuellePos3+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4 && 
												this.spiel.getBrett().getEndRot().get(endFeldBelegt3).istFeldBelegt()==false){
											wegUebrig.clear();
											kannLaufen=true;
										}else if(spiel.getWuerfelZahl()==6){
											kannLaufen=false;
										}else{
											kannLaufen=true;
										}
									}
									
								}
							}
						}
						
					
					} //wenn 1 drauﬂen ist und 2 im endFeld
					else if(MeinWegBestand(FarbEnum.ROT)==1){
						wegUebrig.clear();
								for(Spielfeld endBox:this.spiel.getBrett().getEndRot()){
									if(endBox.istFeldBelegt()==true){
										figur = endBox.getFigur();
										int aktuellePos = this.spiel.getBrett().getEndRot().indexOf(figur.getFeld());
										wegUebrig.add(endMax - aktuellePos);
									}
								}//Figur 1 drinnen
								ergebnis = this.spiel.getBrett().getEndRot().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
								int aktuellePos=spiel.getBrett().getEndRot().indexOf(ergebnis.getFeld());
								if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndRot().size()){
									wegUebrig.clear();
									kannLaufen=true;
								}else{//Figur 2 drinnen
									ergebnis = this.spiel.getBrett().getEndRot().get(endMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
									int aktuellePos2=spiel.getBrett().getEndRot().indexOf(ergebnis.getFeld());
									int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl();
									if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getEndRot().size() && 
											this.spiel.getBrett().getEndRot().get(endFeldBelegt2).istFeldBelegt()==false){
										wegUebrig.clear();
										kannLaufen=true;
									}else{
										for(Spielfeld feld : this.spiel.getBrett().getWeg()){
											if(feld.istFeldBelegt()==true && feld.getFigur().getFarbe()==this.spiel.getSpielerAmZug().getFarbe()){
												int aktuellePos3=spiel.getBrett().getWeg().indexOf(feld);
												int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl()-wegMax-1;
												
												if(aktuellePos3+spiel.getWuerfelZahl()<bedenkLaufLimit){
													if(aktuellePos3+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4){
														ergebnis = feld.getFigur();
														wegUebrig.clear();
														kannLaufen=true;
													}
												}else{
													if(aktuellePos3+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4  && 
															this.spiel.getBrett().getEndRot().get(endFeldBelegt3).istFeldBelegt()==false){
														ergebnis = feld.getFigur();
														kannLaufen=true;
														break;
													}else if(spiel.getWuerfelZahl()==6){
														kannLaufen=false;
													}else{
														kannLaufen=true;
													}
												}
											}
										}
									}
								}
							}//Wenn alle 3 Figuren im Endfeld sind 
							else if(MeinWegBestand(FarbEnum.ROT)==0){
								wegUebrig.clear();
								for(Spielfeld endBox:this.spiel.getBrett().getEndRot()){
									if(endBox.istFeldBelegt()==true){
										figur = endBox.getFigur();
										int aktuellePos = this.spiel.getBrett().getEndRot().indexOf(figur.getFeld());
										wegUebrig.add(endMax - aktuellePos);
									}
								}
								ergebnis = this.spiel.getBrett().getEndRot().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
								int aktuellePos=spiel.getBrett().getEndRot().indexOf(ergebnis.getFeld());
								if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndRot().size()){
									wegUebrig.clear();
									kannLaufen=true;
								}else{
									ergebnis = this.spiel.getBrett().getEndRot().get(endMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
									int aktuellePos2=spiel.getBrett().getEndRot().indexOf(ergebnis.getFeld());
									int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl();
									if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getEndRot().size() && 
											this.spiel.getBrett().getEndRot().get(endFeldBelegt2).istFeldBelegt()==false){
										wegUebrig.clear();
										kannLaufen=true;
									}else{
										ergebnis = this.spiel.getBrett().getEndRot().get(endMax - wegUebrig.get(wegUebrig.size()-3)).getFigur();
										int aktuellePos3=spiel.getBrett().getEndRot().indexOf(ergebnis.getFeld());
										int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl();
										if(aktuellePos3+spiel.getWuerfelZahl() < spiel.getBrett().getEndRot().size() && 
												this.spiel.getBrett().getEndRot().get(endFeldBelegt3).istFeldBelegt()==false){
											wegUebrig.clear();
											kannLaufen=true;
										}else if(spiel.getWuerfelZahl()==6){
											kannLaufen=false;
										}else{
											kannLaufen=true;
										}
										
									}
								}
								
								
							}
					
				}//Wenn 4 Figuren drauﬂen sind
				else if (this.spiel.bestand(this.spiel.getBrett().getStartRot()) == 0) {
					//Wenn alle 4 Figuren auf dem Weg sind
					if(MeinWegBestand(FarbEnum.ROT)==4){
						wegUebrig.clear();
						for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
							if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
								figur = wegFeld.getFigur();
								int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
								wegUebrig.add(wegMax - feldIndex);
							}
						}//Figur 1
						ergebnis = this.spiel.getBrett().getWeg().get(wegMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
						int aktuellePos=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
						
						if(aktuellePos+spiel.getWuerfelZahl()<bedenkLaufLimit){
							if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4){
								wegUebrig.clear();
								kannLaufen=true;
							}
						}else{
							if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4){
								wegUebrig.clear();
								kannLaufen=true;
							}else{//Figur 2
								ergebnis = this.spiel.getBrett().getWeg().get(wegMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
								int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
								
								if(aktuellePos2+spiel.getWuerfelZahl()<bedenkLaufLimit){
									if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4){
										wegUebrig.clear();
										kannLaufen=true;
									}
								}else{
									if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4){
										wegUebrig.clear();
										kannLaufen=true;
									}else{//Figur 3
										ergebnis = this.spiel.getBrett().getWeg().get(wegMax - wegUebrig.get(wegUebrig.size()-3)).getFigur();
										int aktuellePos3=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
										
										if(aktuellePos3+spiel.getWuerfelZahl()<bedenkLaufLimit){
											if(aktuellePos3+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4){
												wegUebrig.clear();
												kannLaufen=true;
											}
										}else{
											if(aktuellePos3+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4){
												wegUebrig.clear();
												kannLaufen=true;
											}else{//Figur 4
												ergebnis = this.spiel.getBrett().getWeg().get(wegMax - wegUebrig.get(wegUebrig.size()-4)).getFigur();
												wegUebrig.clear();
												kannLaufen=true;
												
											}
										}
									}
								}
							}
						}	
					}//Wenn 3 Figuren drauﬂen und 1 drinnen ist
					else if(MeinWegBestand(FarbEnum.ROT)==3){
						wegUebrig.clear();
						for(Spielfeld endBox:this.spiel.getBrett().getEndRot()){
							if(endBox.istFeldBelegt()==true){
								figur = endBox.getFigur();
								int aktuellePos = this.spiel.getBrett().getEndRot().indexOf(figur.getFeld());
								wegUebrig.add(endMax - aktuellePos);
								
							}
						}//Figur 1 drinnen
						ergebnis = this.spiel.getBrett().getEndRot().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
						int aktuellePos=spiel.getBrett().getEndRot().indexOf(ergebnis.getFeld());
						if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndRot().size()){
							wegUebrig.clear();
							kannLaufen=true;
						}else{
							//wen 3 figuren auf dem weg sind und nur sie laufen koennen
							for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
								if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
									figur = wegFeld.getFigur();
									int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
									wegUebrig.add(wegMax - feldIndex);
								}
							}//Figur 2 drauﬂen
								ergebnis = this.spiel.getBrett().getWeg().get(wegMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
								int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
								int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl()-wegMax-1;
								
								if(aktuellePos2+spiel.getWuerfelZahl()<bedenkLaufLimit){
									if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4){
										wegUebrig.clear();
										kannLaufen=true;
									}
								}else{
									if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4 && 
											this.spiel.getBrett().getEndRot().get(endFeldBelegt2).istFeldBelegt()==false &&
											this.uberholenPruefen(spiel.getBrett().getEndRot())>endFeldBelegt2){
										wegUebrig.clear();
										kannLaufen=true;
									}else{//Figur 3 drauﬂen
										ergebnis = this.spiel.getBrett().getWeg().get(wegMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
										int aktuellePos3=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
										int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl()-wegMax-1;
										
										if(aktuellePos3+spiel.getWuerfelZahl()<bedenkLaufLimit){
											if(aktuellePos3+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4){
												wegUebrig.clear();
												kannLaufen=true;
											}
										}else{
											if(aktuellePos3+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4 && 
													this.spiel.getBrett().getEndRot().get(endFeldBelegt3).istFeldBelegt()==false &&
													this.uberholenPruefen(spiel.getBrett().getEndRot())>endFeldBelegt3){
												wegUebrig.clear();
												kannLaufen=true;
											}else{//Figur 4 drauﬂen
												ergebnis = this.spiel.getBrett().getWeg().get(wegMax - wegUebrig.get(wegUebrig.size()-3)).getFigur();
												wegUebrig.clear();
												kannLaufen=true;
											}
										}
									}
								}
						}	
					}//wenn 2 drauﬂen sind und 2 drinnen
					else if(MeinWegBestand(FarbEnum.ROT)==2){
						wegUebrig.clear();
						for(Spielfeld endBox:this.spiel.getBrett().getEndRot()){
							if(endBox.istFeldBelegt()==true){
								figur = endBox.getFigur();
								int feldIndex = this.spiel.getBrett().getEndRot().indexOf(figur.getFeld());
								wegUebrig.add(endMax - feldIndex);
							}
						}//Figur 1 drinnen
								ergebnis = this.spiel.getBrett().getEndRot().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
								int aktuellePos=spiel.getBrett().getEndRot().indexOf(ergebnis.getFeld());
								if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndRot().size()){
									wegUebrig.clear();
									kannLaufen=true;
								}else{//Figur 2 drinnen
									ergebnis = this.spiel.getBrett().getEndRot().get(endMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
									int aktuellePos2=spiel.getBrett().getEndRot().indexOf(ergebnis.getFeld());
									int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl();
									if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getEndRot().size() && 
											this.spiel.getBrett().getEndRot().get(endFeldBelegt2).istFeldBelegt()==false){
										wegUebrig.clear();
										kannLaufen=true;
									}else{
										for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
											if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
												figur = wegFeld.getFigur();
												int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
												wegUebrig.add(wegMax - feldIndex);
											}
										}//Figur 3 drauﬂen
											ergebnis = this.spiel.getBrett().getWeg().get(wegMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
											int aktuellePos3=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
											int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl()-wegMax-1;
											
											if(aktuellePos3+spiel.getWuerfelZahl()<bedenkLaufLimit){
												if(aktuellePos3+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4){
													wegUebrig.clear();
													kannLaufen=true;
												}
											}else{
												if(aktuellePos3+spiel.getWuerfelZahl() < spiel.getBrett().getWeg().size()+4 && 
														this.spiel.getBrett().getEndRot().get(endFeldBelegt3).istFeldBelegt()==false &&
														this.uberholenPruefen(spiel.getBrett().getEndRot())>endFeldBelegt3){
													wegUebrig.clear();
													kannLaufen=true;
												}else{//Figur 4 drauﬂen
													ergebnis = this.spiel.getBrett().getWeg().get(wegMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
													wegUebrig.clear();
													kannLaufen=true;
													
												}
											}
										}
									}
								
							
							//wenn einer drauﬂen ist und 3 im Endfeld
						} else if(MeinWegBestand(FarbEnum.ROT)==1){
							wegUebrig.clear();
							for(Spielfeld endBox:this.spiel.getBrett().getEndRot()){
								if(endBox.istFeldBelegt()==true){
									figur = endBox.getFigur();
									int aktuellePos = this.spiel.getBrett().getEndRot().indexOf(figur.getFeld());
									wegUebrig.add(endMax - aktuellePos);
								}
							}//Figur 1 drinnen
							ergebnis = this.spiel.getBrett().getEndRot().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
							int aktuellePos=spiel.getBrett().getEndRot().indexOf(ergebnis.getFeld());
							if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndRot().size()){
								wegUebrig.clear();
								kannLaufen=true;
							}else{//Figur 2 drinnen
								ergebnis = this.spiel.getBrett().getEndRot().get(endMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
								int aktuellePos2=spiel.getBrett().getEndRot().indexOf(ergebnis.getFeld());
								int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl();
								if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getEndRot().size() && 
										this.spiel.getBrett().getEndRot().get(endFeldBelegt2).istFeldBelegt()==false){
									wegUebrig.clear();
									kannLaufen=true;
								}else{//Figur 3 drinnen
									ergebnis = this.spiel.getBrett().getEndRot().get(endMax - wegUebrig.get(wegUebrig.size()-3)).getFigur();
									int aktuellePos3=spiel.getBrett().getEndRot().indexOf(ergebnis.getFeld());
									int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl();
									if(aktuellePos3+spiel.getWuerfelZahl() < spiel.getBrett().getEndRot().size() && 
											this.spiel.getBrett().getEndRot().get(endFeldBelegt3).istFeldBelegt()==false){
										wegUebrig.clear();
										kannLaufen=true;
									}else{//Figur 4 drauﬂen
										for(Spielfeld feld : this.spiel.getBrett().getWeg()){
											if(feld.istFeldBelegt()==true&&feld.getFigur().getFarbe()==this.spiel.getSpielerAmZug().getFarbe()){
												ergebnis = feld.getFigur();
												kannLaufen=true;
												break;
											}
										}
									}
								}
							}
						}
					}
				}//hier kommt blau du depp
				else if (this.spiel.getSpielerAmZug().getFarbe() == FarbEnum.BLAU) {
					int wegMaxBlau2 = 53;
					int wegMaxBlau1 = 13;
					int endMax = 3;
					int limitBlauAnfang = 10;
					//kann nicht laufen wen keiner draussen ist
					if (this.spiel.bestand(this.spiel.getBrett().getStartBlau()) == 4) {
						kannLaufen=false;
					}
					//wenn 1 figur draussen ist (und ob sie im weg ist oder endfeld)
					else if (this.spiel.bestand(this.spiel.getBrett().getStartBlau()) == 3) {
					if(MeinWegBestand(FarbEnum.BLAU)==1){
						wegUebrig.clear();
						for(Spielfeld feld : this.spiel.getBrett().getWeg()){
							if(feld.istFeldBelegt()==true && feld.getFigur().getFarbe()==this.spiel.getSpielerAmZug().getFarbe()){
								int aktuellePos=spiel.getBrett().getWeg().indexOf(feld);
								if((aktuellePos+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos+spiel.getWuerfelZahl()){
									ergebnis = feld.getFigur();
									kannLaufen=true;
									break;
								}else if(spiel.getWuerfelZahl()==6){
									kannLaufen=false;
								}else{
									kannLaufen=true;
									ergebnis = feld.getFigur();
								}
							}
						}
					}
					else if(MeinWegBestand(FarbEnum.BLAU)==0){
						wegUebrig.clear();
						for(Spielfeld endBox:this.spiel.getBrett().getEndBlau()){
							if(endBox.istFeldBelegt()==true){
								if(this.spiel.getBrett().getEndBlau().indexOf(endBox.getFigur())+this.spiel.getWuerfelZahl()<this.spiel.getBrett().getEndBlau().size()){
									ergebnis = endBox.getFigur();
									kannLaufen=true;
									break;
								}else if (this.spiel.getWuerfelZahl()!=6){
									ergebnis = endBox.getFigur();
									kannLaufen=true;
									break;
								}else{
									kannLaufen=false;
								}
							}
						}
					}
				}
				//wenn 2 figuren draussen sind (wie sie sich verhalten/ und welche rausgeht)
				else if (this.spiel.bestand(this.spiel.getBrett().getStartBlau())== 2) {
					//Wenn beide Figuren auf dem Weg sind
					if(MeinWegBestand(FarbEnum.BLAU)==2){
						wegUebrig.clear();
						for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
							if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
								figur = wegFeld.getFigur();
								int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
								
								if(feldIndex<limitBlauAnfang){
									wegUebrig.add(wegMaxBlau1 - feldIndex);
								}
								else if(feldIndex>=limitBlauAnfang){
									wegUebrig.add(wegMaxBlau2 - feldIndex);
								}
								
							}
						}
						int minIndex = wegUebrig.indexOf(Collections.min(wegUebrig));
						int min = Collections.min(wegUebrig);
						//Figur 1 draußen
						if(min<=wegMaxBlau1){
							wegMaxBlau2=wegMaxBlau1;
						}
						
						ergebnis = this.spiel.getBrett().getWeg().get(wegMaxBlau2 -  wegUebrig.get(minIndex)).getFigur();
						int aktuellePos=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
						
						if((aktuellePos+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos+spiel.getWuerfelZahl()){
							wegUebrig.clear();
							kannLaufen=true;
						}
						else{
							
							// Figur 2 drauﬂen
							wegMaxBlau2=53;
							if(wegUebrig.get(minIndex+1)<=wegMaxBlau1){
								wegMaxBlau2=wegMaxBlau1;
							}
							
							ergebnis = this.spiel.getBrett().getWeg().get(wegMaxBlau2 - wegUebrig.get(minIndex+1)).getFigur();
							int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
							
							if((aktuellePos2+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos2>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos2+spiel.getWuerfelZahl()){
								wegUebrig.clear();
								kannLaufen=true;
							}else if(spiel.getWuerfelZahl()==6){
								kannLaufen=false;
							}else{
								kannLaufen=true;
							}
							
						}
					}//----- WEG = 1 ------ ENDFELD = 1 ----
					else if(MeinWegBestand(FarbEnum.BLAU)==1){
						wegUebrig.clear();
						for(Spielfeld endBox:this.spiel.getBrett().getEndBlau()){
							if(endBox.istFeldBelegt()==true){
								figur = endBox.getFigur();
								int aktuellePos = this.spiel.getBrett().getEndBlau().indexOf(figur.getFeld());
								wegUebrig.add(endMax - aktuellePos);
								
							}
						}//Figur 1 drinnen
						ergebnis = this.spiel.getBrett().getEndBlau().get(endMax - wegUebrig.get(0)).getFigur();
						int aktuellePos=spiel.getBrett().getEndBlau().indexOf(ergebnis.getFeld());
						
						if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndBlau().size()){
							wegUebrig.clear();
							kannLaufen=true;
						}else{
							wegUebrig.clear();
							for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
								if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
									figur = wegFeld.getFigur();
									int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
									
									if(feldIndex<limitBlauAnfang){
										wegUebrig.add(wegMaxBlau1 - feldIndex);
									}
									else if(feldIndex>=limitBlauAnfang){
										wegUebrig.add(wegMaxBlau2 - feldIndex);
									}
								}
							}
							int minIndex = wegUebrig.indexOf(Collections.min(wegUebrig));
							
							
							//Figur 2 drauﬂen
							if(wegUebrig.get(minIndex)<=wegMaxBlau1){
								wegMaxBlau2=wegMaxBlau1;
							}
						
							ergebnis = this.spiel.getBrett().getWeg().get(wegMaxBlau2 - wegUebrig.get(minIndex)).getFigur();
							
							int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
							int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl()-limitBlauAnfang;
							
							if((aktuellePos2+spiel.getWuerfelZahl()>limitBlauAnfang && aktuellePos2>=limitBlauAnfang) ||aktuellePos2+spiel.getWuerfelZahl()<limitBlauAnfang){
								if((aktuellePos2+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos2>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos2+spiel.getWuerfelZahl() ){
									wegUebrig.clear();
									kannLaufen=true;
								}
							}else{
								if((aktuellePos2+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos2>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos2+spiel.getWuerfelZahl() && 
										this.spiel.getBrett().getEndBlau().get(endFeldBelegt2).istFeldBelegt()==false){
									wegUebrig.clear();
									kannLaufen=true;
								}else if(spiel.getWuerfelZahl()==6){
									kannLaufen=false;
								}else{
									kannLaufen=true;
								}
							}
							
						}
					}//Wenn beide Figuren im Endfeld sind
					else if(MeinWegBestand(FarbEnum.BLAU)==0){
						wegUebrig.clear();
						for(Spielfeld endBox:this.spiel.getBrett().getEndBlau()){
							if(endBox.istFeldBelegt()==true){
								figur = endBox.getFigur();
								int aktuellePos = this.spiel.getBrett().getEndBlau().indexOf(figur.getFeld());
								wegUebrig.add(endMax - aktuellePos);
							}
						}//Figur 1 drinnen
						ergebnis = this.spiel.getBrett().getEndBlau().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
						int aktuellePos=spiel.getBrett().getEndBlau().indexOf(ergebnis.getFeld());
						
						if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndBlau().size()){
							wegUebrig.clear();
							kannLaufen=true;
						}else{//Figur 2 drinnen
							ergebnis = this.spiel.getBrett().getEndBlau().get(endMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
							int aktuellePos2=spiel.getBrett().getEndBlau().indexOf(ergebnis.getFeld());
							int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl();
							
							if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getEndBlau().size() && 
									this.spiel.getBrett().getEndBlau().get(endFeldBelegt2).istFeldBelegt()==false){
								wegUebrig.clear();
								kannLaufen=true;
							}else if(spiel.getWuerfelZahl()==6){
								kannLaufen=false;
							}else{
								kannLaufen=true;
							}
						}
						
					}
				}//wenn 3 figuren draussen sind (wie sie sich verhalten/ und welche rausgeht)
				else if (this.spiel.bestand(this.spiel.getBrett().getStartBlau()) == 1) {
					//Wenn 3 Figuren auf dem Weg sind
					if(MeinWegBestand(FarbEnum.BLAU)==3){
						wegUebrig.clear();
						for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
							if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
								figur = wegFeld.getFigur();
								int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
								
								if(feldIndex<limitBlauAnfang){
									wegUebrig.add(wegMaxBlau1 - feldIndex);
								}
								else if(feldIndex>=limitBlauAnfang){
									wegUebrig.add(wegMaxBlau2 - feldIndex);
								}
							}
						}
						Collections.sort(wegUebrig);
						int minIndex = wegUebrig.indexOf(Collections.min(wegUebrig));
						int min = Collections.min(wegUebrig);
						
						
						//Figur 1 draußen
						if(min<=wegMaxBlau1){
							wegMaxBlau2=wegMaxBlau1;
						}
						
						ergebnis = this.spiel.getBrett().getWeg().get(wegMaxBlau2 - wegUebrig.get(minIndex)).getFigur();
						int aktuellePos=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
						
							if((aktuellePos+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos+spiel.getWuerfelZahl()){
								wegUebrig.clear();
								kannLaufen=true;
								
							}else{
								
								// Figur 2 drauﬂen
								wegMaxBlau2=53;
								if(wegUebrig.get(minIndex+1)<=wegMaxBlau1){
									wegMaxBlau2=wegMaxBlau1;
								}
								
								ergebnis = this.spiel.getBrett().getWeg().get(wegMaxBlau2 - wegUebrig.get(minIndex+1)).getFigur();
								int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
								
								if((aktuellePos2+spiel.getWuerfelZahl()  > limitBlauAnfang && aktuellePos2>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos2+spiel.getWuerfelZahl()){
									wegUebrig.clear();
									kannLaufen=true;
								}else{
									
									//Figur 3 drauﬂen
									wegMaxBlau2=53;
									if( wegUebrig.get(minIndex+2)<=wegMaxBlau1){
										wegMaxBlau2=wegMaxBlau1;
									}
									
									
									ergebnis = this.spiel.getBrett().getWeg().get(wegMaxBlau2 - wegUebrig.get(minIndex+2)).getFigur();
									int aktuellePos3=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
									if((aktuellePos3+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos3>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()){
										wegUebrig.clear();
										kannLaufen=true;
									}else if(spiel.getWuerfelZahl()==6){
										kannLaufen=false;
									}else{
										kannLaufen=true;
									}
								}
						}
					}
					//Wenn 2 Figuren auf dem Weg sind und 1 im Endfeld
					else if(MeinWegBestand(FarbEnum.BLAU)==2){
						wegUebrig.clear();
						//Die Figur die drinnen ist versucht zuerst zu laufen
						for(Spielfeld endBox:this.spiel.getBrett().getEndBlau()){
							if(endBox.istFeldBelegt()==true){
								figur = endBox.getFigur();
								int aktuellePos = this.spiel.getBrett().getEndBlau().indexOf(figur.getFeld());
								wegUebrig.add(endMax - aktuellePos);
							}
						}//Figur 1 drinnen
						ergebnis = this.spiel.getBrett().getEndBlau().get(endMax - wegUebrig.get(0)).getFigur();
						int aktuellePos=spiel.getBrett().getEndBlau().indexOf(ergebnis.getFeld());
						if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndBlau().size()){
							wegUebrig.clear();
							kannLaufen=true;
						}else{
							//Wenn die Figur drinnen nicht laufen kann werden die anderen Figuren drauﬂen berechnet
							for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
								if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
									figur = wegFeld.getFigur();
									int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
									
									if(feldIndex<limitBlauAnfang){
										wegUebrig.add(wegMaxBlau1 - feldIndex);
									}
									else if(feldIndex>=limitBlauAnfang){
										wegUebrig.add(wegMaxBlau2 - feldIndex);
									}
								}
							}
							Collections.sort(wegUebrig);
							int minIndex = wegUebrig.indexOf(Collections.min(wegUebrig));
							
							
							//Figur 2 drauﬂen
							if(wegUebrig.get(minIndex+1)<=wegMaxBlau1){
								wegMaxBlau2=wegMaxBlau1;
							}
							
							ergebnis = this.spiel.getBrett().getWeg().get(wegMaxBlau2 - wegUebrig.get(minIndex+1)).getFigur();
							int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
							int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl()-limitBlauAnfang;
							
							if((aktuellePos2+spiel.getWuerfelZahl()>limitBlauAnfang && aktuellePos2>=limitBlauAnfang) ||aktuellePos2+spiel.getWuerfelZahl()<limitBlauAnfang){
								if((aktuellePos2+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos2>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos2+spiel.getWuerfelZahl()){
									wegUebrig.clear();
									kannLaufen=true;
								}
							}else{
								if((aktuellePos2+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos2>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos2+spiel.getWuerfelZahl() &&
										this.spiel.getBrett().getEndBlau().get(endFeldBelegt2).istFeldBelegt()==false &&
										this.uberholenPruefen(spiel.getBrett().getEndBlau())>endFeldBelegt2){
									wegUebrig.clear();
									kannLaufen=true;
								}else{
									//Figur 3 drauﬂen
									wegMaxBlau2=53;
									if(wegUebrig.get(minIndex+2)<=wegMaxBlau1){
										wegMaxBlau2=wegMaxBlau1;
									}
									ergebnis = this.spiel.getBrett().getWeg().get(wegMaxBlau2 - wegUebrig.get(minIndex+2)).getFigur();
									int aktuellePos3=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
									int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl()-limitBlauAnfang;
									
									if((aktuellePos3+spiel.getWuerfelZahl()>limitBlauAnfang && aktuellePos3>=limitBlauAnfang) ||aktuellePos3+spiel.getWuerfelZahl()<limitBlauAnfang){
										if((aktuellePos3+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos3>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()){
											wegUebrig.clear();
											kannLaufen=true;
										}
									}else{
										if((aktuellePos3+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos3>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos3+spiel.getWuerfelZahl() && 
												this.spiel.getBrett().getEndBlau().get(endFeldBelegt3).istFeldBelegt()==false){
											wegUebrig.clear();
											kannLaufen=true;
										}else if(spiel.getWuerfelZahl()==6){
											kannLaufen=false;
										}else{
											kannLaufen=true;
										}
									}
									
								}
							}
						}
						
					
					} //wenn 1 drauﬂen ist und 2 im endFeld
					else if(MeinWegBestand(FarbEnum.BLAU)==1){
						wegUebrig.clear();
								for(Spielfeld endBox:this.spiel.getBrett().getEndBlau()){
									if(endBox.istFeldBelegt()==true){
										figur = endBox.getFigur();
										int aktuellePos = this.spiel.getBrett().getEndBlau().indexOf(figur.getFeld());
										wegUebrig.add(endMax - aktuellePos);
									}
								}//Figur 1 drinnen
								ergebnis = this.spiel.getBrett().getEndBlau().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
								int aktuellePos=spiel.getBrett().getEndBlau().indexOf(ergebnis.getFeld());
								if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndBlau().size()){
									wegUebrig.clear();
									kannLaufen=true;
								}else{//Figur 2 drinnen
									ergebnis = this.spiel.getBrett().getEndBlau().get(endMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
									int aktuellePos2=spiel.getBrett().getEndBlau().indexOf(ergebnis.getFeld());
									int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl();
									if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getEndBlau().size() && 
											this.spiel.getBrett().getEndBlau().get(endFeldBelegt2).istFeldBelegt()==false){
										wegUebrig.clear();
										kannLaufen=true;
									}else{
										for(Spielfeld feld : this.spiel.getBrett().getWeg()){
											if(feld.istFeldBelegt()==true && feld.getFigur().getFarbe()==this.spiel.getSpielerAmZug().getFarbe()){
												int aktuellePos3=spiel.getBrett().getWeg().indexOf(feld);
												int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl()-limitBlauAnfang;
												
												if((aktuellePos3+spiel.getWuerfelZahl()>limitBlauAnfang && aktuellePos3>=limitBlauAnfang) ||aktuellePos3+spiel.getWuerfelZahl()<limitBlauAnfang){
													if((aktuellePos3+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos3>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()){
														ergebnis = feld.getFigur();
														wegUebrig.clear();
														kannLaufen=true;
													}
												}else{
													if((aktuellePos3+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos3>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()  && 
															this.spiel.getBrett().getEndBlau().get(endFeldBelegt3).istFeldBelegt()==false){
														ergebnis = feld.getFigur();
														kannLaufen=true;
														break;
													}else if(spiel.getWuerfelZahl()==6){
														kannLaufen=false;
													}else{
														kannLaufen=true;
													}
												}
											}
										}
									}
								}
							}//Wenn alle 3 Figuren im Endfeld sind 
							else if(MeinWegBestand(FarbEnum.BLAU)==0){
								wegUebrig.clear();
								for(Spielfeld endBox:this.spiel.getBrett().getEndBlau()){
									if(endBox.istFeldBelegt()==true){
										figur = endBox.getFigur();
										int aktuellePos = this.spiel.getBrett().getEndBlau().indexOf(figur.getFeld());
										wegUebrig.add(endMax - aktuellePos);
									}
								}
								ergebnis = this.spiel.getBrett().getEndBlau().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
								int aktuellePos=spiel.getBrett().getEndBlau().indexOf(ergebnis.getFeld());
								if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndBlau().size()){
									wegUebrig.clear();
									kannLaufen=true;
								}else{
									ergebnis = this.spiel.getBrett().getEndBlau().get(endMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
									int aktuellePos2=spiel.getBrett().getEndBlau().indexOf(ergebnis.getFeld());
									int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl();
									if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getEndBlau().size() && 
											this.spiel.getBrett().getEndBlau().get(endFeldBelegt2).istFeldBelegt()==false){
										wegUebrig.clear();
										kannLaufen=true;
									}else{
										ergebnis = this.spiel.getBrett().getEndBlau().get(endMax - wegUebrig.get(wegUebrig.size()-3)).getFigur();
										int aktuellePos3=spiel.getBrett().getEndBlau().indexOf(ergebnis.getFeld());
										int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl();
										if(aktuellePos3+spiel.getWuerfelZahl() < spiel.getBrett().getEndBlau().size() && 
												this.spiel.getBrett().getEndBlau().get(endFeldBelegt3).istFeldBelegt()==false){
											wegUebrig.clear();
											kannLaufen=true;
										}else if(spiel.getWuerfelZahl()==6){
											kannLaufen=false;
										}else{
											kannLaufen=true;
										}
										
									}
								}
								
								
							}
					
				}//Wenn 4 Figuren drauﬂen sind
				else if (this.spiel.bestand(this.spiel.getBrett().getStartBlau()) == 0) {
					//Wenn alle 4 Figuren auf dem Weg sind
					if(MeinWegBestand(FarbEnum.BLAU)==4){
						wegUebrig.clear();
						for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
							if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
								figur = wegFeld.getFigur();
								int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
								
								if(feldIndex<limitBlauAnfang){
									wegUebrig.add(wegMaxBlau1 - feldIndex);
								}
								else if(feldIndex>=limitBlauAnfang){
									wegUebrig.add(wegMaxBlau2 - feldIndex);
								}
							}
						}
						Collections.sort(wegUebrig);
						int minIndex = wegUebrig.indexOf(Collections.min(wegUebrig));
						int min = Collections.min(wegUebrig);
						
						//Figur 1
						if(min<=wegMaxBlau1){
							wegMaxBlau2=wegMaxBlau1;
						}
						
						ergebnis = this.spiel.getBrett().getWeg().get(wegMaxBlau2 - wegUebrig.get(minIndex)).getFigur();
						int aktuellePos=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
						
						if((aktuellePos+spiel.getWuerfelZahl()>limitBlauAnfang && aktuellePos>=limitBlauAnfang) ||aktuellePos+spiel.getWuerfelZahl()<limitBlauAnfang){
							if((aktuellePos+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos+spiel.getWuerfelZahl()){
								wegUebrig.clear();
								kannLaufen=true;
							}
						}else{
							if((aktuellePos+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos+spiel.getWuerfelZahl()){
								wegUebrig.clear();
								kannLaufen=true;
							}else{
								
								//Figur 2
								wegMaxBlau2=53;
								if(wegUebrig.get(minIndex+1)<=wegMaxBlau1){
									wegMaxBlau2=wegMaxBlau1;
								}
								
								ergebnis = this.spiel.getBrett().getWeg().get(wegMaxBlau2 - wegUebrig.get(minIndex+1)).getFigur();
								int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
								
								if((aktuellePos2+spiel.getWuerfelZahl()>limitBlauAnfang && aktuellePos2>=limitBlauAnfang) ||aktuellePos2+spiel.getWuerfelZahl()<limitBlauAnfang){
									if((aktuellePos2+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos2>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos2+spiel.getWuerfelZahl()){
										wegUebrig.clear();
										kannLaufen=true;
									}
								}else{
									if((aktuellePos2+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos2>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos2+spiel.getWuerfelZahl()){
										wegUebrig.clear();
										kannLaufen=true;
									}else{
										
										//Figur 3
										wegMaxBlau2=53;
										if(wegUebrig.get(minIndex+2)<=wegMaxBlau1){
											wegMaxBlau2=wegMaxBlau1;
										}
										
										ergebnis = this.spiel.getBrett().getWeg().get(wegMaxBlau2 - wegUebrig.get(minIndex+2)).getFigur();
										int aktuellePos3=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
										
										if((aktuellePos3+spiel.getWuerfelZahl()>limitBlauAnfang && aktuellePos3>=limitBlauAnfang) ||aktuellePos3+spiel.getWuerfelZahl()<limitBlauAnfang){
											if((aktuellePos3+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos3>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()){
												wegUebrig.clear();
												kannLaufen=true;
											}
										}else{
											if((aktuellePos3+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos3>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()){
												wegUebrig.clear();
												kannLaufen=true;
											}else{//Figur 4
												wegMaxBlau2=53;
												if(wegUebrig.get(minIndex+3)<=wegMaxBlau1){
													wegMaxBlau2=wegMaxBlau1;
												}
												ergebnis = this.spiel.getBrett().getWeg().get(wegMaxBlau2 - wegUebrig.get(minIndex+3)).getFigur();
												wegUebrig.clear();
												kannLaufen=true;
												
											}
										}
									}
								}
							}
						}	
					}//Wenn 3 Figuren drauﬂen und 1 drinnen ist
					else if(MeinWegBestand(FarbEnum.BLAU)==3){
						wegUebrig.clear();
						for(Spielfeld endBox:this.spiel.getBrett().getEndBlau()){
							if(endBox.istFeldBelegt()==true){
								figur = endBox.getFigur();
								int aktuellePos = this.spiel.getBrett().getEndBlau().indexOf(figur.getFeld());
								wegUebrig.add(endMax - aktuellePos);
								
							}
						}//Figur 1 drinnen
						ergebnis = this.spiel.getBrett().getEndBlau().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
						int aktuellePos=spiel.getBrett().getEndBlau().indexOf(ergebnis.getFeld());
						if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndBlau().size()){
							wegUebrig.clear();
							kannLaufen=true;
						}else{
							//wen 3 figuren auf dem weg sind und nur sie laufen koennen
							wegUebrig.clear();
							for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
								if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
									figur = wegFeld.getFigur();
									int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
									if(feldIndex<limitBlauAnfang){
										wegUebrig.add(wegMaxBlau1 - feldIndex);
									}
									else if(feldIndex>=limitBlauAnfang){
										wegUebrig.add(wegMaxBlau2 - feldIndex);
									}
								}
							}
							Collections.sort(wegUebrig);
							int minIndex = wegUebrig.indexOf(Collections.min(wegUebrig));
							int min = Collections.min(wegUebrig);
							
							//Figur 2 drauﬂen
							if(min<=wegMaxBlau1){
								wegMaxBlau2=wegMaxBlau1;
							}
							
							
								ergebnis = this.spiel.getBrett().getWeg().get(wegMaxBlau2 - wegUebrig.get(minIndex)).getFigur();
								int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
								int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl()-limitBlauAnfang;
								
								if((aktuellePos2+spiel.getWuerfelZahl()>limitBlauAnfang && aktuellePos2>=limitBlauAnfang) ||aktuellePos2+spiel.getWuerfelZahl()<limitBlauAnfang){
									if((aktuellePos2+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos2>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos2+spiel.getWuerfelZahl()){
										wegUebrig.clear();
										kannLaufen=true;
									}
								}else{
									if((aktuellePos2+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos2>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos2+spiel.getWuerfelZahl() && 
											this.spiel.getBrett().getEndBlau().get(endFeldBelegt2).istFeldBelegt()==false &&
											this.uberholenPruefen(spiel.getBrett().getEndBlau())>endFeldBelegt2){
										wegUebrig.clear();
										kannLaufen=true;
									}else{
										//Figur 3 drauﬂen
										wegMaxBlau2=53;
										if(wegUebrig.get(minIndex+1)<=wegMaxBlau1){
											wegMaxBlau2=wegMaxBlau1;
										}
										
										ergebnis = this.spiel.getBrett().getWeg().get(wegMaxBlau2 - wegUebrig.get(minIndex+1)).getFigur();
										int aktuellePos3=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
										int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl()-limitBlauAnfang;
										
										if((aktuellePos3+spiel.getWuerfelZahl()>limitBlauAnfang && aktuellePos3>=limitBlauAnfang) ||aktuellePos3+spiel.getWuerfelZahl()<limitBlauAnfang){
											if((aktuellePos3+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos3>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()){
												wegUebrig.clear();
												kannLaufen=true;
											}
										}else{
											if((aktuellePos3+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos3>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos3+spiel.getWuerfelZahl() && 
													this.spiel.getBrett().getEndBlau().get(endFeldBelegt3).istFeldBelegt()==false &&
													this.uberholenPruefen(spiel.getBrett().getEndBlau())>endFeldBelegt3){
												wegUebrig.clear();
												kannLaufen=true;
											}else{
												//Figur 4 drauﬂen
												wegMaxBlau2=53;
												if(wegUebrig.get(minIndex+2)<=wegMaxBlau1){
													wegMaxBlau2=wegMaxBlau1;
												}
												ergebnis = this.spiel.getBrett().getWeg().get(wegMaxBlau2 - wegUebrig.get(minIndex+2)).getFigur();
												wegUebrig.clear();
												kannLaufen=true;
											}
										}
									}
								}
						}	
					}
					else if(MeinWegBestand(FarbEnum.BLAU)==2){
						wegUebrig.clear();
						//wenn 2 drauﬂen sind und 2 drinnen
						for(Spielfeld endBox:this.spiel.getBrett().getEndBlau()){
							if(endBox.istFeldBelegt()==true){
								figur = endBox.getFigur();
								int feldIndex = this.spiel.getBrett().getEndBlau().indexOf(figur.getFeld());
								wegUebrig.add(endMax - feldIndex);
							}
						}//Figur 1 drinnen
								ergebnis = this.spiel.getBrett().getEndBlau().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
								int aktuellePos=spiel.getBrett().getEndBlau().indexOf(ergebnis.getFeld());
								if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndBlau().size()){
									wegUebrig.clear();
									kannLaufen=true;
								}else{//Figur 2 drinnen
									ergebnis = this.spiel.getBrett().getEndBlau().get(endMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
									int aktuellePos2=spiel.getBrett().getEndBlau().indexOf(ergebnis.getFeld());
									int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl();
									if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getEndBlau().size() && 
											this.spiel.getBrett().getEndBlau().get(endFeldBelegt2).istFeldBelegt()==false){
										wegUebrig.clear();
										kannLaufen=true;
									}else{
										wegUebrig.clear();
										for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
											if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
												figur = wegFeld.getFigur();
												int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
												
												if(feldIndex<limitBlauAnfang){
													wegUebrig.add(wegMaxBlau1 - feldIndex);
												}
												else if(feldIndex>=limitBlauAnfang){
													wegUebrig.add(wegMaxBlau2 - feldIndex);
												}
											}
										}
										Collections.sort(wegUebrig);
										int minIndex = wegUebrig.indexOf(Collections.min(wegUebrig));
										int min = Collections.min(wegUebrig);
										
										//Figur 3 drauﬂen
										if(min<=wegMaxBlau1){
											wegMaxBlau2=wegMaxBlau1;
										}
										
											ergebnis = this.spiel.getBrett().getWeg().get(wegMaxBlau2 - wegUebrig.get(minIndex)).getFigur();
											int aktuellePos3=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
											int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl()-limitBlauAnfang;
											
											if((aktuellePos3+spiel.getWuerfelZahl()>limitBlauAnfang && aktuellePos3>=limitBlauAnfang) ||aktuellePos3+spiel.getWuerfelZahl()<limitBlauAnfang){
												if((aktuellePos3+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos3>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()){
													wegUebrig.clear();
													kannLaufen=true;
												}
											}else{
												if((aktuellePos3+spiel.getWuerfelZahl() > limitBlauAnfang && aktuellePos3>=limitBlauAnfang) || limitBlauAnfang+3>=aktuellePos3+spiel.getWuerfelZahl() && 
														this.spiel.getBrett().getEndBlau().get(endFeldBelegt3).istFeldBelegt()==false &&
														this.uberholenPruefen(spiel.getBrett().getEndBlau())>endFeldBelegt3){
													wegUebrig.clear();
													kannLaufen=true;
												}else{
													//Figur 4 drauﬂen
													wegMaxBlau2=53;
													if(wegUebrig.get(minIndex+1)<=wegMaxBlau1){
														wegMaxBlau2=wegMaxBlau1;
													}
													ergebnis = this.spiel.getBrett().getWeg().get(wegMaxBlau2 - wegUebrig.get(minIndex+1)).getFigur();
													wegUebrig.clear();
													kannLaufen=true;
													
												}
											}
										}
									}
								
							
							//wenn einer drauﬂen ist und 3 im Endfeld
						} else if(MeinWegBestand(FarbEnum.BLAU)==1){
							wegUebrig.clear();
							for(Spielfeld endBox:this.spiel.getBrett().getEndBlau()){
								if(endBox.istFeldBelegt()==true){
									figur = endBox.getFigur();
									int aktuellePos = this.spiel.getBrett().getEndBlau().indexOf(figur.getFeld());
									wegUebrig.add(endMax - aktuellePos);
								}
							}//Figur 1 drinnen
							ergebnis = this.spiel.getBrett().getEndBlau().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
							int aktuellePos=spiel.getBrett().getEndBlau().indexOf(ergebnis.getFeld());
							if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndBlau().size()){
								wegUebrig.clear();
								kannLaufen=true;
							}else{//Figur 2 drinnen
								ergebnis = this.spiel.getBrett().getEndBlau().get(endMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
								int aktuellePos2=spiel.getBrett().getEndBlau().indexOf(ergebnis.getFeld());
								int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl();
								if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getEndBlau().size() && 
										this.spiel.getBrett().getEndBlau().get(endFeldBelegt2).istFeldBelegt()==false){
									wegUebrig.clear();
									kannLaufen=true;
								}else{//Figur 3 drinnen
									ergebnis = this.spiel.getBrett().getEndBlau().get(endMax - wegUebrig.get(wegUebrig.size()-3)).getFigur();
									int aktuellePos3=spiel.getBrett().getEndBlau().indexOf(ergebnis.getFeld());
									int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl();
									if(aktuellePos3+spiel.getWuerfelZahl() < spiel.getBrett().getEndBlau().size() && 
											this.spiel.getBrett().getEndBlau().get(endFeldBelegt3).istFeldBelegt()==false){
										wegUebrig.clear();
										kannLaufen=true;
									}else{//Figur 4 drauﬂen
										for(Spielfeld feld : this.spiel.getBrett().getWeg()){
											if(feld.istFeldBelegt()==true && feld.getFigur().getFarbe()==this.spiel.getSpielerAmZug().getFarbe()){
												ergebnis = feld.getFigur();
												kannLaufen=true;
												break;
											}
										}
									}
								}
							}
						}
					}
			}	//hier kommt GRUEN
				else if (this.spiel.getSpielerAmZug().getFarbe() == FarbEnum.GRUEN) {
					int wegMaxGruen2 = 63;
					int wegMaxGruen1 = 23; 
					int endMax = 3;
					int limitGruenAnfang = 20;
					//kann nicht laufen wen keiner draussen ist
					if (this.spiel.bestand(this.spiel.getBrett().getStartGruen()) == 4) {
						kannLaufen=false;
					}
					//wenn 1 figur draussen ist (und ob sie im weg ist oder endfeld)
					else if (this.spiel.bestand(this.spiel.getBrett().getStartGruen()) == 3) {
					if(MeinWegBestand(FarbEnum.GRUEN)==1){
						wegUebrig.clear();
						for(Spielfeld feld : this.spiel.getBrett().getWeg()){
							if(feld.istFeldBelegt()==true && feld.getFigur().getFarbe()==this.spiel.getSpielerAmZug().getFarbe()){
								int aktuellePos=spiel.getBrett().getWeg().indexOf(feld);
								if((aktuellePos+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos+spiel.getWuerfelZahl()){
									ergebnis = feld.getFigur();
									kannLaufen=true;
									break;
								}else if(spiel.getWuerfelZahl()==6){
									kannLaufen=false;
								}else{
									kannLaufen=true;
									ergebnis = feld.getFigur();
								}
							}
						}
					}
					else if(MeinWegBestand(FarbEnum.GRUEN)==0){
						wegUebrig.clear();
						for(Spielfeld endBox:this.spiel.getBrett().getEndGruen()){
							if(endBox.istFeldBelegt()==true){
								if(this.spiel.getBrett().getEndGruen().indexOf(endBox.getFigur())+this.spiel.getWuerfelZahl()<this.spiel.getBrett().getEndGruen().size()){
									ergebnis = endBox.getFigur();
									kannLaufen=true;
									break;
								}else if (this.spiel.getWuerfelZahl()!=6){
									ergebnis = endBox.getFigur();
									kannLaufen=true;
									break;
								}else{
									kannLaufen=false;
								}
							}
						}
					}
				}
				//wenn 2 figuren draussen sind (wie sie sich verhalten/ und welche rausgeht)
				else if (this.spiel.bestand(this.spiel.getBrett().getStartGruen())== 2) {
					//Wenn beide Figuren auf dem Weg sind
					if(MeinWegBestand(FarbEnum.GRUEN)==2){
						wegUebrig.clear();
						for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
							if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
								figur = wegFeld.getFigur();
								int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
								
								if(feldIndex<limitGruenAnfang){
									wegUebrig.add(wegMaxGruen1 - feldIndex);
								}
								else if(feldIndex>=limitGruenAnfang){
									wegUebrig.add(wegMaxGruen2 - feldIndex);
								}
								
							}
						}
						int minIndex = wegUebrig.indexOf(Collections.min(wegUebrig));
						int min = Collections.min(wegUebrig);
						//Figur 1 draußen
						if(min<=wegMaxGruen1){
							wegMaxGruen2=wegMaxGruen1;
						}
						
						ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGruen2 -  wegUebrig.get(minIndex)).getFigur();
						int aktuellePos=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
						
						if((aktuellePos+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos+spiel.getWuerfelZahl()){
							wegUebrig.clear();
							kannLaufen=true;
						}
						else{
							
							// Figur 2 drauﬂen
							wegMaxGruen2=63;
							if(wegUebrig.get(minIndex+1)<=wegMaxGruen1){
								wegMaxGruen2=wegMaxGruen1;
							}
							
							ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGruen2 - wegUebrig.get(minIndex+1)).getFigur();
							int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
							
							if((aktuellePos2+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos2>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos2+spiel.getWuerfelZahl()){
								wegUebrig.clear();
								kannLaufen=true;
							}else if(spiel.getWuerfelZahl()==6){
								kannLaufen=false;
							}else{
								kannLaufen=true;
							}
							
						}
					}//----- WEG = 1 ------ ENDFELD = 1 ----
					else if(MeinWegBestand(FarbEnum.GRUEN)==1){
						wegUebrig.clear();
						for(Spielfeld endBox:this.spiel.getBrett().getEndGruen()){
							if(endBox.istFeldBelegt()==true){
								figur = endBox.getFigur();
								int aktuellePos = this.spiel.getBrett().getEndGruen().indexOf(figur.getFeld());
								wegUebrig.add(endMax - aktuellePos);
								
							}
						}//Figur 1 drinnen
						ergebnis = this.spiel.getBrett().getEndGruen().get(endMax - wegUebrig.get(0)).getFigur();
						int aktuellePos=spiel.getBrett().getEndGruen().indexOf(ergebnis.getFeld());
						
						if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndGruen().size()){
							wegUebrig.clear();
							kannLaufen=true;
						}else{
							wegUebrig.clear();
							for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
								if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
									figur = wegFeld.getFigur();
									int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
									
									if(feldIndex<limitGruenAnfang){
										wegUebrig.add(wegMaxGruen1 - feldIndex);
									}
									else if(feldIndex>=limitGruenAnfang){
										wegUebrig.add(wegMaxGruen2 - feldIndex);
									}
								}
							}
							int minIndex = wegUebrig.indexOf(Collections.min(wegUebrig));
							
							
							//Figur 2 drauﬂen
							if(wegUebrig.get(minIndex)<=wegMaxGruen1){
								wegMaxGruen2=wegMaxGruen1;
							}
						
							ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGruen2 - wegUebrig.get(minIndex)).getFigur();
							
							int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
							int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl()-limitGruenAnfang;
							
							if((aktuellePos2+spiel.getWuerfelZahl()>limitGruenAnfang && aktuellePos2>=limitGruenAnfang) ||aktuellePos2+spiel.getWuerfelZahl()<limitGruenAnfang){
								if((aktuellePos2+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos2>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos2+spiel.getWuerfelZahl() ){
									wegUebrig.clear();
									kannLaufen=true;
								}
							}else{
								if((aktuellePos2+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos2>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos2+spiel.getWuerfelZahl() && 
										this.spiel.getBrett().getEndGruen().get(endFeldBelegt2).istFeldBelegt()==false){
									wegUebrig.clear();
									kannLaufen=true;
								}else if(spiel.getWuerfelZahl()==6){
									kannLaufen=false;
								}else{
									kannLaufen=true;
								}
							}
							
						}
					}//Wenn beide Figuren im Endfeld sind
					else if(MeinWegBestand(FarbEnum.GRUEN)==0){
						wegUebrig.clear();
						for(Spielfeld endBox:this.spiel.getBrett().getEndGruen()){
							if(endBox.istFeldBelegt()==true){
								figur = endBox.getFigur();
								int aktuellePos = this.spiel.getBrett().getEndGruen().indexOf(figur.getFeld());
								wegUebrig.add(endMax - aktuellePos);
							}
						}//Figur 1 drinnen
						ergebnis = this.spiel.getBrett().getEndGruen().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
						int aktuellePos=spiel.getBrett().getEndGruen().indexOf(ergebnis.getFeld());
						
						if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndGruen().size()){
							wegUebrig.clear();
							kannLaufen=true;
						}else{//Figur 2 drinnen
							ergebnis = this.spiel.getBrett().getEndGruen().get(endMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
							int aktuellePos2=spiel.getBrett().getEndGruen().indexOf(ergebnis.getFeld());
							int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl();
							
							if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getEndGruen().size() && 
									this.spiel.getBrett().getEndGruen().get(endFeldBelegt2).istFeldBelegt()==false){
								wegUebrig.clear();
								kannLaufen=true;
							}else if(spiel.getWuerfelZahl()==6){
								kannLaufen=false;
							}else{
								kannLaufen=true;
							}
						}
						
					}
				}//wenn 3 figuren draussen sind (wie sie sich verhalten/ und welche rausgeht)
				else if (this.spiel.bestand(this.spiel.getBrett().getStartGruen()) == 1) {
					//Wenn 3 Figuren auf dem Weg sind
					if(MeinWegBestand(FarbEnum.GRUEN)==3){
						wegUebrig.clear();
						for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
							if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
								figur = wegFeld.getFigur();
								int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
								
								if(feldIndex<limitGruenAnfang){
									wegUebrig.add(wegMaxGruen1 - feldIndex);
								}
								else if(feldIndex>=limitGruenAnfang){
									wegUebrig.add(wegMaxGruen2 - feldIndex);
								}
							}
						}
						Collections.sort(wegUebrig);
						int minIndex = wegUebrig.indexOf(Collections.min(wegUebrig));
						int min = Collections.min(wegUebrig);
						
						
						//Figur 1 draußen
						if(min<=wegMaxGruen1){
							wegMaxGruen2=wegMaxGruen1;
						}
						
						ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGruen2 - wegUebrig.get(minIndex)).getFigur();
						int aktuellePos=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
						
							if((aktuellePos+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos+spiel.getWuerfelZahl()){
								wegUebrig.clear();
								kannLaufen=true;
								
							}else{
								
								// Figur 2 drauﬂen
								wegMaxGruen2=63;
								if(wegUebrig.get(minIndex+1)<=wegMaxGruen1){
									wegMaxGruen2=wegMaxGruen1;
								}
								
								ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGruen2 - wegUebrig.get(minIndex+1)).getFigur();
								int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
								
								if((aktuellePos2+spiel.getWuerfelZahl()  > limitGruenAnfang && aktuellePos2>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos2+spiel.getWuerfelZahl()){
									wegUebrig.clear();
									kannLaufen=true;
								}else{
									
									//Figur 3 drauﬂen
									wegMaxGruen2=63;
									if( wegUebrig.get(minIndex+2)<=wegMaxGruen1){
										wegMaxGruen2=wegMaxGruen1;
									}
									
									
									ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGruen2 - wegUebrig.get(minIndex+2)).getFigur();
									int aktuellePos3=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
									if((aktuellePos3+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos3>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()){
										wegUebrig.clear();
										kannLaufen=true;
									}else if(spiel.getWuerfelZahl()==6){
										kannLaufen=false;
									}else{
										kannLaufen=true;
									}
								}
						}
					}
					//Wenn 2 Figuren auf dem Weg sind und 1 im Endfeld
					else if(MeinWegBestand(FarbEnum.GRUEN)==2){
						wegUebrig.clear();
						//Die Figur die drinnen ist versucht zuerst zu laufen
						for(Spielfeld endBox:this.spiel.getBrett().getEndGruen()){
							if(endBox.istFeldBelegt()==true){
								figur = endBox.getFigur();
								int aktuellePos = this.spiel.getBrett().getEndGruen().indexOf(figur.getFeld());
								wegUebrig.add(endMax - aktuellePos);
							}
						}//Figur 1 drinnen
						ergebnis = this.spiel.getBrett().getEndGruen().get(endMax - wegUebrig.get(0)).getFigur();
						int aktuellePos=spiel.getBrett().getEndGruen().indexOf(ergebnis.getFeld());
						if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndGruen().size()){
							wegUebrig.clear();
							kannLaufen=true;
						}else{
							//Wenn die Figur drinnen nicht laufen kann werden die anderen Figuren drauﬂen berechnet
							for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
								if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
									figur = wegFeld.getFigur();
									int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
									
									if(feldIndex<limitGruenAnfang){
										wegUebrig.add(wegMaxGruen1 - feldIndex);
									}
									else if(feldIndex>=limitGruenAnfang){
										wegUebrig.add(wegMaxGruen2 - feldIndex);
									}
								}
							}
							Collections.sort(wegUebrig);
							int minIndex = wegUebrig.indexOf(Collections.min(wegUebrig));
							
							
							//Figur 2 drauﬂen
							if(wegUebrig.get(minIndex+1)<=wegMaxGruen1){
								wegMaxGruen2=wegMaxGruen1;
							}
							
							ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGruen2 - wegUebrig.get(minIndex+1)).getFigur();
							int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
							int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl()-limitGruenAnfang;
							
							if((aktuellePos2+spiel.getWuerfelZahl()>limitGruenAnfang && aktuellePos2>=limitGruenAnfang) ||aktuellePos2+spiel.getWuerfelZahl()<limitGruenAnfang){
								if((aktuellePos2+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos2>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos2+spiel.getWuerfelZahl()){
									wegUebrig.clear();
									kannLaufen=true;
								}
							}else{
								if((aktuellePos2+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos2>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos2+spiel.getWuerfelZahl() &&
										this.spiel.getBrett().getEndGruen().get(endFeldBelegt2).istFeldBelegt()==false &&
										this.uberholenPruefen(spiel.getBrett().getEndGruen())>endFeldBelegt2){
									wegUebrig.clear();
									kannLaufen=true;
								}else{
									//Figur 3 drauﬂen
									wegMaxGruen2=63;
									if(wegUebrig.get(minIndex+2)<=wegMaxGruen1){
										wegMaxGruen2=wegMaxGruen1;
									}
									ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGruen2 - wegUebrig.get(minIndex+2)).getFigur();
									int aktuellePos3=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
									int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl()-limitGruenAnfang;
									
									if((aktuellePos3+spiel.getWuerfelZahl()>limitGruenAnfang && aktuellePos3>=limitGruenAnfang) ||aktuellePos3+spiel.getWuerfelZahl()<limitGruenAnfang){
										if((aktuellePos3+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos3>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()){
											wegUebrig.clear();
											kannLaufen=true;
										}
									}else{
										if((aktuellePos3+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos3>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos3+spiel.getWuerfelZahl() && 
												this.spiel.getBrett().getEndGruen().get(endFeldBelegt3).istFeldBelegt()==false){
											wegUebrig.clear();
											kannLaufen=true;
										}else if(spiel.getWuerfelZahl()==6){
											kannLaufen=false;
										}else{
											kannLaufen=true;
										}
									}
									
								}
							}
						}
						
					
					} //wenn 1 drauﬂen ist und 2 im endFeld
					else if(MeinWegBestand(FarbEnum.GRUEN)==1){
						wegUebrig.clear();
								for(Spielfeld endBox:this.spiel.getBrett().getEndGruen()){
									if(endBox.istFeldBelegt()==true){
										figur = endBox.getFigur();
										int aktuellePos = this.spiel.getBrett().getEndGruen().indexOf(figur.getFeld());
										wegUebrig.add(endMax - aktuellePos);
									}
								}//Figur 1 drinnen
								ergebnis = this.spiel.getBrett().getEndGruen().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
								int aktuellePos=spiel.getBrett().getEndGruen().indexOf(ergebnis.getFeld());
								if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndGruen().size()){
									wegUebrig.clear();
									kannLaufen=true;
								}else{//Figur 2 drinnen
									ergebnis = this.spiel.getBrett().getEndGruen().get(endMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
									int aktuellePos2=spiel.getBrett().getEndGruen().indexOf(ergebnis.getFeld());
									int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl();
									if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getEndGruen().size() && 
											this.spiel.getBrett().getEndGruen().get(endFeldBelegt2).istFeldBelegt()==false){
										wegUebrig.clear();
										kannLaufen=true;
									}else{
										for(Spielfeld feld : this.spiel.getBrett().getWeg()){
											if(feld.istFeldBelegt()==true && feld.getFigur().getFarbe()==this.spiel.getSpielerAmZug().getFarbe()){
												int aktuellePos3=spiel.getBrett().getWeg().indexOf(feld);
												int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl()-limitGruenAnfang;
												
												if((aktuellePos3+spiel.getWuerfelZahl()>limitGruenAnfang && aktuellePos3>=limitGruenAnfang) ||aktuellePos3+spiel.getWuerfelZahl()<limitGruenAnfang){
													if((aktuellePos3+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos3>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()){
														ergebnis = feld.getFigur();
														wegUebrig.clear();
														kannLaufen=true;
													}
												}else{
													if((aktuellePos3+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos3>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()  && 
															this.spiel.getBrett().getEndGruen().get(endFeldBelegt3).istFeldBelegt()==false){
														ergebnis = feld.getFigur();
														kannLaufen=true;
														break;
													}else if(spiel.getWuerfelZahl()==6){
														kannLaufen=false;
													}else{
														kannLaufen=true;
													}
												}
											}
										}
									}
								}
							}//Wenn alle 3 Figuren im Endfeld sind 
							else if(MeinWegBestand(FarbEnum.GRUEN)==0){
								wegUebrig.clear();
								for(Spielfeld endBox:this.spiel.getBrett().getEndGruen()){
									if(endBox.istFeldBelegt()==true){
										figur = endBox.getFigur();
										int aktuellePos = this.spiel.getBrett().getEndGruen().indexOf(figur.getFeld());
										wegUebrig.add(endMax - aktuellePos);
									}
								}
								ergebnis = this.spiel.getBrett().getEndGruen().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
								int aktuellePos=spiel.getBrett().getEndGruen().indexOf(ergebnis.getFeld());
								if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndGruen().size()){
									wegUebrig.clear();
									kannLaufen=true;
								}else{
									ergebnis = this.spiel.getBrett().getEndGruen().get(endMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
									int aktuellePos2=spiel.getBrett().getEndGruen().indexOf(ergebnis.getFeld());
									int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl();
									if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getEndGruen().size() && 
											this.spiel.getBrett().getEndGruen().get(endFeldBelegt2).istFeldBelegt()==false){
										wegUebrig.clear();
										kannLaufen=true;
									}else{
										ergebnis = this.spiel.getBrett().getEndGruen().get(endMax - wegUebrig.get(wegUebrig.size()-3)).getFigur();
										int aktuellePos3=spiel.getBrett().getEndGruen().indexOf(ergebnis.getFeld());
										int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl();
										if(aktuellePos3+spiel.getWuerfelZahl() < spiel.getBrett().getEndGruen().size() && 
												this.spiel.getBrett().getEndGruen().get(endFeldBelegt3).istFeldBelegt()==false){
											wegUebrig.clear();
											kannLaufen=true;
										}else if(spiel.getWuerfelZahl()==6){
											kannLaufen=false;
										}else{
											kannLaufen=true;
										}
										
									}
								}
								
								
							}
					
				}//Wenn 4 Figuren drauﬂen sind
				else if (this.spiel.bestand(this.spiel.getBrett().getStartGruen()) == 0) {
					//Wenn alle 4 Figuren auf dem Weg sind
					if(MeinWegBestand(FarbEnum.GRUEN)==4){
						wegUebrig.clear();
						for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
							if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
								figur = wegFeld.getFigur();
								int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
								
								if(feldIndex<limitGruenAnfang){
									wegUebrig.add(wegMaxGruen1 - feldIndex);
								}
								else if(feldIndex>=limitGruenAnfang){
									wegUebrig.add(wegMaxGruen2 - feldIndex);
								}
							}
						}
						Collections.sort(wegUebrig);
						int minIndex = wegUebrig.indexOf(Collections.min(wegUebrig));
						int min = Collections.min(wegUebrig);
						
						//Figur 1
						if(min<=wegMaxGruen1){
							wegMaxGruen2=wegMaxGruen1;
						}
						
						ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGruen2 - wegUebrig.get(minIndex)).getFigur();
						int aktuellePos=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
						
						if((aktuellePos+spiel.getWuerfelZahl()>limitGruenAnfang && aktuellePos>=limitGruenAnfang) ||aktuellePos+spiel.getWuerfelZahl()<limitGruenAnfang){
							if((aktuellePos+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos+spiel.getWuerfelZahl()){
								wegUebrig.clear();
								kannLaufen=true;
							}
						}else{
							if((aktuellePos+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos+spiel.getWuerfelZahl()){
								wegUebrig.clear();
								kannLaufen=true;
							}else{
								
								//Figur 2
								wegMaxGruen2=63;
								if(wegUebrig.get(minIndex+1)<=wegMaxGruen1){
									wegMaxGruen2=wegMaxGruen1;
								}
								
								ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGruen2 - wegUebrig.get(minIndex+1)).getFigur();
								int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
								
								if((aktuellePos2+spiel.getWuerfelZahl()>limitGruenAnfang && aktuellePos2>=limitGruenAnfang) ||aktuellePos2+spiel.getWuerfelZahl()<limitGruenAnfang){
									if((aktuellePos2+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos2>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos2+spiel.getWuerfelZahl()){
										wegUebrig.clear();
										kannLaufen=true;
									}
								}else{
									if((aktuellePos2+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos2>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos2+spiel.getWuerfelZahl()){
										wegUebrig.clear();
										kannLaufen=true;
									}else{
										
										//Figur 3
										wegMaxGruen2=63;
										if(wegUebrig.get(minIndex+2)<=wegMaxGruen1){
											wegMaxGruen2=wegMaxGruen1;
										}
										
										ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGruen2 - wegUebrig.get(minIndex+2)).getFigur();
										int aktuellePos3=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
										
										if((aktuellePos3+spiel.getWuerfelZahl()>limitGruenAnfang && aktuellePos3>=limitGruenAnfang) ||aktuellePos3+spiel.getWuerfelZahl()<limitGruenAnfang){
											if((aktuellePos3+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos3>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()){
												wegUebrig.clear();
												kannLaufen=true;
											}
										}else{
											if((aktuellePos3+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos3>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()){
												wegUebrig.clear();
												kannLaufen=true;
											}else{//Figur 4
												wegMaxGruen2=63;
												if(wegUebrig.get(minIndex+3)<=wegMaxGruen1){
													wegMaxGruen2=wegMaxGruen1;
												}
												ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGruen2 - wegUebrig.get(minIndex+3)).getFigur();
												wegUebrig.clear();
												kannLaufen=true;
												
											}
										}
									}
								}
							}
						}	
					}//Wenn 3 Figuren drauﬂen und 1 drinnen ist
					else if(MeinWegBestand(FarbEnum.GRUEN)==3){
						wegUebrig.clear();
						for(Spielfeld endBox:this.spiel.getBrett().getEndGruen()){
							if(endBox.istFeldBelegt()==true){
								figur = endBox.getFigur();
								int aktuellePos = this.spiel.getBrett().getEndGruen().indexOf(figur.getFeld());
								wegUebrig.add(endMax - aktuellePos);
								
							}
						}//Figur 1 drinnen
						ergebnis = this.spiel.getBrett().getEndGruen().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
						int aktuellePos=spiel.getBrett().getEndGruen().indexOf(ergebnis.getFeld());
						if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndGruen().size()){
							wegUebrig.clear();
							kannLaufen=true;
						}else{
							//wen 3 figuren auf dem weg sind und nur sie laufen koennen
							wegUebrig.clear();
							for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
								if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
									figur = wegFeld.getFigur();
									int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
									if(feldIndex<limitGruenAnfang){
										wegUebrig.add(wegMaxGruen1 - feldIndex);
									}
									else if(feldIndex>=limitGruenAnfang){
										wegUebrig.add(wegMaxGruen2 - feldIndex);
									}
								}
							}
							Collections.sort(wegUebrig);
							int minIndex = wegUebrig.indexOf(Collections.min(wegUebrig));
							int min = Collections.min(wegUebrig);
							
							//Figur 2 drauﬂen
							if(min<=wegMaxGruen1){
								wegMaxGruen2=wegMaxGruen1;
							}
							
							
								ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGruen2 - wegUebrig.get(minIndex)).getFigur();
								int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
								int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl()-limitGruenAnfang;
								
								if((aktuellePos2+spiel.getWuerfelZahl()>limitGruenAnfang && aktuellePos2>=limitGruenAnfang) ||aktuellePos2+spiel.getWuerfelZahl()<limitGruenAnfang){
									if((aktuellePos2+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos2>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos2+spiel.getWuerfelZahl()){
										wegUebrig.clear();
										kannLaufen=true;
									}
								}else{
									if((aktuellePos2+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos2>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos2+spiel.getWuerfelZahl() && 
											this.spiel.getBrett().getEndGruen().get(endFeldBelegt2).istFeldBelegt()==false &&
											this.uberholenPruefen(spiel.getBrett().getEndGruen())>endFeldBelegt2){
										wegUebrig.clear();
										kannLaufen=true;
									}else{
										//Figur 3 drauﬂen
										wegMaxGruen2=63;
										if(wegUebrig.get(minIndex+1)<=wegMaxGruen1){
											wegMaxGruen2=wegMaxGruen1;
										}
										
										ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGruen2 - wegUebrig.get(minIndex+1)).getFigur();
										int aktuellePos3=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
										int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl()-limitGruenAnfang;
										
										if((aktuellePos3+spiel.getWuerfelZahl()>limitGruenAnfang && aktuellePos3>=limitGruenAnfang) ||aktuellePos3+spiel.getWuerfelZahl()<limitGruenAnfang){
											if((aktuellePos3+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos3>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()){
												wegUebrig.clear();
												kannLaufen=true;
											}
										}else{
											if((aktuellePos3+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos3>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos3+spiel.getWuerfelZahl() && 
													this.spiel.getBrett().getEndGruen().get(endFeldBelegt3).istFeldBelegt()==false &&
													this.uberholenPruefen(spiel.getBrett().getEndGruen())>endFeldBelegt3){
												wegUebrig.clear();
												kannLaufen=true;
											}else{
												//Figur 4 drauﬂen
												wegMaxGruen2=63;
												if(wegUebrig.get(minIndex+2)<=wegMaxGruen1){
													wegMaxGruen2=wegMaxGruen1;
												}
												ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGruen2 - wegUebrig.get(minIndex+2)).getFigur();
												wegUebrig.clear();
												kannLaufen=true;
											}
										}
									}
								}
						}	
					}
					else if(MeinWegBestand(FarbEnum.GRUEN)==2){
						wegUebrig.clear();
						//wenn 2 drauﬂen sind und 2 drinnen
						for(Spielfeld endBox:this.spiel.getBrett().getEndGruen()){
							if(endBox.istFeldBelegt()==true){
								figur = endBox.getFigur();
								int feldIndex = this.spiel.getBrett().getEndGruen().indexOf(figur.getFeld());
								wegUebrig.add(endMax - feldIndex);
							}
						}//Figur 1 drinnen
								ergebnis = this.spiel.getBrett().getEndGruen().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
								int aktuellePos=spiel.getBrett().getEndGruen().indexOf(ergebnis.getFeld());
								if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndGruen().size()){
									wegUebrig.clear();
									kannLaufen=true;
								}else{//Figur 2 drinnen
									ergebnis = this.spiel.getBrett().getEndGruen().get(endMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
									int aktuellePos2=spiel.getBrett().getEndGruen().indexOf(ergebnis.getFeld());
									int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl();
									if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getEndGruen().size() && 
											this.spiel.getBrett().getEndGruen().get(endFeldBelegt2).istFeldBelegt()==false){
										wegUebrig.clear();
										kannLaufen=true;
									}else{
										wegUebrig.clear();
										for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
											if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
												figur = wegFeld.getFigur();
												int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
												
												if(feldIndex<limitGruenAnfang){
													wegUebrig.add(wegMaxGruen1 - feldIndex);
												}
												else if(feldIndex>=limitGruenAnfang){
													wegUebrig.add(wegMaxGruen2 - feldIndex);
												}
											}
										}
										Collections.sort(wegUebrig);
										int minIndex = wegUebrig.indexOf(Collections.min(wegUebrig));
										int min = Collections.min(wegUebrig);
										
										//Figur 3 drauﬂen
										if(min<=wegMaxGruen1){
											wegMaxGruen2=wegMaxGruen1;
										}
										
											ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGruen2 - wegUebrig.get(minIndex)).getFigur();
											int aktuellePos3=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
											int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl()-limitGruenAnfang;
											
											if((aktuellePos3+spiel.getWuerfelZahl()>limitGruenAnfang && aktuellePos3>=limitGruenAnfang) ||aktuellePos3+spiel.getWuerfelZahl()<limitGruenAnfang){
												if((aktuellePos3+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos3>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()){
													wegUebrig.clear();
													kannLaufen=true;
												}
											}else{
												if((aktuellePos3+spiel.getWuerfelZahl() > limitGruenAnfang && aktuellePos3>=limitGruenAnfang) || limitGruenAnfang+3>=aktuellePos3+spiel.getWuerfelZahl() && 
														this.spiel.getBrett().getEndGruen().get(endFeldBelegt3).istFeldBelegt()==false &&
														this.uberholenPruefen(spiel.getBrett().getEndGruen())>endFeldBelegt3){
													wegUebrig.clear();
													kannLaufen=true;
												}else{
													//Figur 4 drauﬂen
													wegMaxGruen2=63;
													if(wegUebrig.get(minIndex+1)<=wegMaxGruen1){
														wegMaxGruen2=wegMaxGruen1;
													}
													ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGruen2 - wegUebrig.get(minIndex+1)).getFigur();
													wegUebrig.clear();
													kannLaufen=true;
													
												}
											}
										}
									}
								
							
							//wenn einer drauﬂen ist und 3 im Endfeld
						} else if(MeinWegBestand(FarbEnum.GRUEN)==1){
							wegUebrig.clear();
							for(Spielfeld endBox:this.spiel.getBrett().getEndGruen()){
								if(endBox.istFeldBelegt()==true){
									figur = endBox.getFigur();
									int aktuellePos = this.spiel.getBrett().getEndGruen().indexOf(figur.getFeld());
									wegUebrig.add(endMax - aktuellePos);
								}
							}//Figur 1 drinnen
							ergebnis = this.spiel.getBrett().getEndGruen().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
							int aktuellePos=spiel.getBrett().getEndGruen().indexOf(ergebnis.getFeld());
							if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndGruen().size()){
								wegUebrig.clear();
								kannLaufen=true;
							}else{//Figur 2 drinnen
								ergebnis = this.spiel.getBrett().getEndGruen().get(endMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
								int aktuellePos2=spiel.getBrett().getEndGruen().indexOf(ergebnis.getFeld());
								int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl();
								if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getEndGruen().size() && 
										this.spiel.getBrett().getEndGruen().get(endFeldBelegt2).istFeldBelegt()==false){
									wegUebrig.clear();
									kannLaufen=true;
								}else{//Figur 3 drinnen
									ergebnis = this.spiel.getBrett().getEndGruen().get(endMax - wegUebrig.get(wegUebrig.size()-3)).getFigur();
									int aktuellePos3=spiel.getBrett().getEndGruen().indexOf(ergebnis.getFeld());
									int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl();
									if(aktuellePos3+spiel.getWuerfelZahl() < spiel.getBrett().getEndGruen().size() && 
											this.spiel.getBrett().getEndGruen().get(endFeldBelegt3).istFeldBelegt()==false){
										wegUebrig.clear();
										kannLaufen=true;
									}else{//Figur 4 drauﬂen
										for(Spielfeld feld : this.spiel.getBrett().getWeg()){
											if(feld.istFeldBelegt()==true && feld.getFigur().getFarbe()==this.spiel.getSpielerAmZug().getFarbe()){
												ergebnis = feld.getFigur();
												kannLaufen=true;
												break;
											}
										}
									}
								}
							}
						}
					}
			}//Hier kommt Gelb
				else if (this.spiel.getSpielerAmZug().getFarbe() == FarbEnum.GELB) {
					int wegMaxGelb2 = 73;
					int wegMaxGelb1 = 33; 
					int endMax = 3;
					int limitGelbAnfang = 30;
					//kann nicht laufen wen keiner draussen ist
					if (this.spiel.bestand(this.spiel.getBrett().getStartGelb()) == 4) {
						kannLaufen=false;
					}
					//wenn 1 figur draussen ist (und ob sie im weg ist oder endfeld)
					else if (this.spiel.bestand(this.spiel.getBrett().getStartGelb()) == 3) {
					if(MeinWegBestand(FarbEnum.GELB)==1){
						wegUebrig.clear();
						for(Spielfeld feld : this.spiel.getBrett().getWeg()){
							if(feld.istFeldBelegt()==true && feld.getFigur().getFarbe()==this.spiel.getSpielerAmZug().getFarbe()){
								int aktuellePos=spiel.getBrett().getWeg().indexOf(feld);
								if((aktuellePos+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos+spiel.getWuerfelZahl()){
									ergebnis = feld.getFigur();
									kannLaufen=true;
									break;
								}else if(spiel.getWuerfelZahl()==6){
									kannLaufen=false;
								}else{
									kannLaufen=true;
									ergebnis = feld.getFigur();
								}
							}
						}
					}
					else if(MeinWegBestand(FarbEnum.GELB)==0){
						wegUebrig.clear();
						for(Spielfeld endBox:this.spiel.getBrett().getEndGelb()){
							if(endBox.istFeldBelegt()==true){
								if(this.spiel.getBrett().getEndGelb().indexOf(endBox.getFigur())+this.spiel.getWuerfelZahl()<this.spiel.getBrett().getEndGelb().size()){
									ergebnis = endBox.getFigur();
									kannLaufen=true;
									break;
								}else if (this.spiel.getWuerfelZahl()!=6){
									ergebnis = endBox.getFigur();
									kannLaufen=true;
									break;
								}else{
									kannLaufen=false;
								}
							}
						}
					}
				}
				//wenn 2 figuren draussen sind (wie sie sich verhalten/ und welche rausgeht)
				else if (this.spiel.bestand(this.spiel.getBrett().getStartGelb())== 2) {
					//Wenn beide Figuren auf dem Weg sind
					if(MeinWegBestand(FarbEnum.GELB)==2){
						wegUebrig.clear();
						for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
							if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
								figur = wegFeld.getFigur();
								int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
								
								if(feldIndex<limitGelbAnfang){
									wegUebrig.add(wegMaxGelb1 - feldIndex);
								}
								else if(feldIndex>=limitGelbAnfang){
									wegUebrig.add(wegMaxGelb2 - feldIndex);
								}
								
							}
						}
						int minIndex = wegUebrig.indexOf(Collections.min(wegUebrig));
						int min = Collections.min(wegUebrig);
						//Figur 1 draußen
						if(min<=wegMaxGelb1){
							wegMaxGelb2=wegMaxGelb1;
						}
						
						ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGelb2 -  wegUebrig.get(minIndex)).getFigur();
						int aktuellePos=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
						
						if((aktuellePos+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos+spiel.getWuerfelZahl()){
							wegUebrig.clear();
							kannLaufen=true;
						}
						else{
							
							// Figur 2 drauﬂen
							wegMaxGelb2=73;
							if(wegUebrig.get(minIndex+1)<=wegMaxGelb1){
								wegMaxGelb2=wegMaxGelb1;
							}
							
							ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGelb2 - wegUebrig.get(minIndex+1)).getFigur();
							int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
							
							if((aktuellePos2+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos2>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos2+spiel.getWuerfelZahl()){
								wegUebrig.clear();
								kannLaufen=true;
							}else if(spiel.getWuerfelZahl()==6){
								kannLaufen=false;
							}else{
								kannLaufen=true;
							}
							
						}
					}//----- WEG = 1 ------ ENDFELD = 1 ----
					else if(MeinWegBestand(FarbEnum.GELB)==1){
						wegUebrig.clear();
						for(Spielfeld endBox:this.spiel.getBrett().getEndGelb()){
							if(endBox.istFeldBelegt()==true){
								figur = endBox.getFigur();
								int aktuellePos = this.spiel.getBrett().getEndGelb().indexOf(figur.getFeld());
								wegUebrig.add(endMax - aktuellePos);
								
							}
						}//Figur 1 drinnen
						ergebnis = this.spiel.getBrett().getEndGelb().get(endMax - wegUebrig.get(0)).getFigur();
						int aktuellePos=spiel.getBrett().getEndGelb().indexOf(ergebnis.getFeld());
						
						if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndGelb().size()){
							wegUebrig.clear();
							kannLaufen=true;
						}else{
							wegUebrig.clear();
							for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
								if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
									figur = wegFeld.getFigur();
									int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
									
									if(feldIndex<limitGelbAnfang){
										wegUebrig.add(wegMaxGelb1 - feldIndex);
									}
									else if(feldIndex>=limitGelbAnfang){
										wegUebrig.add(wegMaxGelb2 - feldIndex);
									}
								}
							}
							int minIndex = wegUebrig.indexOf(Collections.min(wegUebrig));
							
							
							//Figur 2 drauﬂen
							if(wegUebrig.get(minIndex)<=wegMaxGelb1){
								wegMaxGelb2=wegMaxGelb1;
							}
						
							ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGelb2 - wegUebrig.get(minIndex)).getFigur();
							
							int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
							int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl()-limitGelbAnfang;
							
							if((aktuellePos2+spiel.getWuerfelZahl()>limitGelbAnfang && aktuellePos2>=limitGelbAnfang) ||aktuellePos2+spiel.getWuerfelZahl()<limitGelbAnfang){
								if((aktuellePos2+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos2>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos2+spiel.getWuerfelZahl() ){
									wegUebrig.clear();
									kannLaufen=true;
								}
							}else{
								if((aktuellePos2+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos2>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos2+spiel.getWuerfelZahl() && 
										this.spiel.getBrett().getEndGelb().get(endFeldBelegt2).istFeldBelegt()==false){
									wegUebrig.clear();
									kannLaufen=true;
								}else if(spiel.getWuerfelZahl()==6){
									kannLaufen=false;
								}else{
									kannLaufen=true;
								}
							}
							
						}
					}//Wenn beide Figuren im Endfeld sind
					else if(MeinWegBestand(FarbEnum.GELB)==0){
						wegUebrig.clear();
						for(Spielfeld endBox:this.spiel.getBrett().getEndGelb()){
							if(endBox.istFeldBelegt()==true){
								figur = endBox.getFigur();
								int aktuellePos = this.spiel.getBrett().getEndGelb().indexOf(figur.getFeld());
								wegUebrig.add(endMax - aktuellePos);
							}
						}//Figur 1 drinnen
						ergebnis = this.spiel.getBrett().getEndGelb().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
						int aktuellePos=spiel.getBrett().getEndGelb().indexOf(ergebnis.getFeld());
						
						if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndGelb().size()){
							wegUebrig.clear();
							kannLaufen=true;
						}else{//Figur 2 drinnen
							ergebnis = this.spiel.getBrett().getEndGelb().get(endMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
							int aktuellePos2=spiel.getBrett().getEndGelb().indexOf(ergebnis.getFeld());
							int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl();
							
							if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getEndGelb().size() && 
									this.spiel.getBrett().getEndGelb().get(endFeldBelegt2).istFeldBelegt()==false){
								wegUebrig.clear();
								kannLaufen=true;
							}else if(spiel.getWuerfelZahl()==6){
								kannLaufen=false;
							}else{
								kannLaufen=true;
							}
						}
						
					}
				}//wenn 3 figuren draussen sind (wie sie sich verhalten/ und welche rausgeht)
				else if (this.spiel.bestand(this.spiel.getBrett().getStartGelb()) == 1) {
					//Wenn 3 Figuren auf dem Weg sind
					if(MeinWegBestand(FarbEnum.GELB)==3){
						wegUebrig.clear();
						for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
							if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
								figur = wegFeld.getFigur();
								int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
								
								if(feldIndex<limitGelbAnfang){
									wegUebrig.add(wegMaxGelb1 - feldIndex);
								}
								else if(feldIndex>=limitGelbAnfang){
									wegUebrig.add(wegMaxGelb2 - feldIndex);
								}
							}
						}
						Collections.sort(wegUebrig);
						int minIndex = wegUebrig.indexOf(Collections.min(wegUebrig));
						int min = Collections.min(wegUebrig);
						
						
						//Figur 1 draußen
						if(min<=wegMaxGelb1){
							wegMaxGelb2=wegMaxGelb1;
						}
						
						ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGelb2 - wegUebrig.get(minIndex)).getFigur();
						int aktuellePos=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
						
							if((aktuellePos+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos+spiel.getWuerfelZahl()){
								wegUebrig.clear();
								kannLaufen=true;
								
							}else{
								
								// Figur 2 drauﬂen
								wegMaxGelb2=73;
								if(wegUebrig.get(minIndex+1)<=wegMaxGelb1){
									wegMaxGelb2=wegMaxGelb1;
								}
								
								ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGelb2 - wegUebrig.get(minIndex+1)).getFigur();
								int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
								
								if((aktuellePos2+spiel.getWuerfelZahl()  > limitGelbAnfang && aktuellePos2>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos2+spiel.getWuerfelZahl()){
									wegUebrig.clear();
									kannLaufen=true;
								}else{
									
									//Figur 3 drauﬂen
									wegMaxGelb2=73;
									if( wegUebrig.get(minIndex+2)<=wegMaxGelb1){
										wegMaxGelb2=wegMaxGelb1;
									}
									
									
									ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGelb2 - wegUebrig.get(minIndex+2)).getFigur();
									int aktuellePos3=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
									if((aktuellePos3+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos3>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()){
										wegUebrig.clear();
										kannLaufen=true;
									}else if(spiel.getWuerfelZahl()==6){
										kannLaufen=false;
									}else{
										kannLaufen=true;
									}
								}
						}
					}
					//Wenn 2 Figuren auf dem Weg sind und 1 im Endfeld
					else if(MeinWegBestand(FarbEnum.GELB)==2){
						wegUebrig.clear();
						//Die Figur die drinnen ist versucht zuerst zu laufen
						for(Spielfeld endBox:this.spiel.getBrett().getEndGelb()){
							if(endBox.istFeldBelegt()==true){
								figur = endBox.getFigur();
								int aktuellePos = this.spiel.getBrett().getEndGelb().indexOf(figur.getFeld());
								wegUebrig.add(endMax - aktuellePos);
							}
						}//Figur 1 drinnen
						ergebnis = this.spiel.getBrett().getEndGelb().get(endMax - wegUebrig.get(0)).getFigur();
						int aktuellePos=spiel.getBrett().getEndGelb().indexOf(ergebnis.getFeld());
						if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndGelb().size()){
							wegUebrig.clear();
							kannLaufen=true;
						}else{
							//Wenn die Figur drinnen nicht laufen kann werden die anderen Figuren drauﬂen berechnet
							for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
								if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
									figur = wegFeld.getFigur();
									int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
									
									if(feldIndex<limitGelbAnfang){
										wegUebrig.add(wegMaxGelb1 - feldIndex);
									}
									else if(feldIndex>=limitGelbAnfang){
										wegUebrig.add(wegMaxGelb2 - feldIndex);
									}
								}
							}
							Collections.sort(wegUebrig);
							int minIndex = wegUebrig.indexOf(Collections.min(wegUebrig));
							
							
							//Figur 2 drauﬂen
							if(wegUebrig.get(minIndex+1)<=wegMaxGelb1){
								wegMaxGelb2=wegMaxGelb1;
							}
							
							ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGelb2 - wegUebrig.get(minIndex+1)).getFigur();
							int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
							int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl()-limitGelbAnfang;
							
							if((aktuellePos2+spiel.getWuerfelZahl()>limitGelbAnfang && aktuellePos2>=limitGelbAnfang) ||aktuellePos2+spiel.getWuerfelZahl()<limitGelbAnfang){
								if((aktuellePos2+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos2>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos2+spiel.getWuerfelZahl()){
									wegUebrig.clear();
									kannLaufen=true;
								}
							}else{
								if((aktuellePos2+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos2>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos2+spiel.getWuerfelZahl() &&
										this.spiel.getBrett().getEndGelb().get(endFeldBelegt2).istFeldBelegt()==false &&
										this.uberholenPruefen(spiel.getBrett().getEndGelb())>endFeldBelegt2){
									wegUebrig.clear();
									kannLaufen=true;
								}else{
									//Figur 3 drauﬂen
									wegMaxGelb2=73;
									if(wegUebrig.get(minIndex+2)<=wegMaxGelb1){
										wegMaxGelb2=wegMaxGelb1;
									}
									ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGelb2 - wegUebrig.get(minIndex+2)).getFigur();
									int aktuellePos3=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
									int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl()-limitGelbAnfang;
									
									if((aktuellePos3+spiel.getWuerfelZahl()>limitGelbAnfang && aktuellePos3>=limitGelbAnfang) ||aktuellePos3+spiel.getWuerfelZahl()<limitGelbAnfang){
										if((aktuellePos3+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos3>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()){
											wegUebrig.clear();
											kannLaufen=true;
										}
									}else{
										if((aktuellePos3+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos3>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos3+spiel.getWuerfelZahl() && 
												this.spiel.getBrett().getEndGelb().get(endFeldBelegt3).istFeldBelegt()==false){
											wegUebrig.clear();
											kannLaufen=true;
										}else if(spiel.getWuerfelZahl()==6){
											kannLaufen=false;
										}else{
											kannLaufen=true;
										}
									}
									
								}
							}
						}
						
					
					} //wenn 1 drauﬂen ist und 2 im endFeld
					else if(MeinWegBestand(FarbEnum.GELB)==1){
						wegUebrig.clear();
								for(Spielfeld endBox:this.spiel.getBrett().getEndGelb()){
									if(endBox.istFeldBelegt()==true){
										figur = endBox.getFigur();
										int aktuellePos = this.spiel.getBrett().getEndGelb().indexOf(figur.getFeld());
										wegUebrig.add(endMax - aktuellePos);
									}
								}//Figur 1 drinnen
								ergebnis = this.spiel.getBrett().getEndGelb().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
								int aktuellePos=spiel.getBrett().getEndGelb().indexOf(ergebnis.getFeld());
								if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndGelb().size()){
									wegUebrig.clear();
									kannLaufen=true;
								}else{//Figur 2 drinnen
									ergebnis = this.spiel.getBrett().getEndGelb().get(endMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
									int aktuellePos2=spiel.getBrett().getEndGelb().indexOf(ergebnis.getFeld());
									int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl();
									if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getEndGelb().size() && 
											this.spiel.getBrett().getEndGelb().get(endFeldBelegt2).istFeldBelegt()==false){
										wegUebrig.clear();
										kannLaufen=true;
									}else{
										for(Spielfeld feld : this.spiel.getBrett().getWeg()){
											if(feld.istFeldBelegt()==true && feld.getFigur().getFarbe()==this.spiel.getSpielerAmZug().getFarbe()){
												int aktuellePos3=spiel.getBrett().getWeg().indexOf(feld);
												int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl()-limitGelbAnfang;
												
												if((aktuellePos3+spiel.getWuerfelZahl()>limitGelbAnfang && aktuellePos3>=limitGelbAnfang) ||aktuellePos3+spiel.getWuerfelZahl()<limitGelbAnfang){
													if((aktuellePos3+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos3>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()){
														ergebnis = feld.getFigur();
														wegUebrig.clear();
														kannLaufen=true;
													}
												}else{
													if((aktuellePos3+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos3>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()  && 
															this.spiel.getBrett().getEndGelb().get(endFeldBelegt3).istFeldBelegt()==false){
														ergebnis = feld.getFigur();
														kannLaufen=true;
														break;
													}else if(spiel.getWuerfelZahl()==6){
														kannLaufen=false;
													}else{
														kannLaufen=true;
													}
												}
											}
										}
									}
								}
							}//Wenn alle 3 Figuren im Endfeld sind 
							else if(MeinWegBestand(FarbEnum.GELB)==0){
								wegUebrig.clear();
								for(Spielfeld endBox:this.spiel.getBrett().getEndGelb()){
									if(endBox.istFeldBelegt()==true){
										figur = endBox.getFigur();
										int aktuellePos = this.spiel.getBrett().getEndGelb().indexOf(figur.getFeld());
										wegUebrig.add(endMax - aktuellePos);
									}
								}
								ergebnis = this.spiel.getBrett().getEndGelb().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
								int aktuellePos=spiel.getBrett().getEndGelb().indexOf(ergebnis.getFeld());
								if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndGelb().size()){
									wegUebrig.clear();
									kannLaufen=true;
								}else{
									ergebnis = this.spiel.getBrett().getEndGelb().get(endMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
									int aktuellePos2=spiel.getBrett().getEndGelb().indexOf(ergebnis.getFeld());
									int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl();
									if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getEndGelb().size() && 
											this.spiel.getBrett().getEndGelb().get(endFeldBelegt2).istFeldBelegt()==false){
										wegUebrig.clear();
										kannLaufen=true;
									}else{
										ergebnis = this.spiel.getBrett().getEndGelb().get(endMax - wegUebrig.get(wegUebrig.size()-3)).getFigur();
										int aktuellePos3=spiel.getBrett().getEndGelb().indexOf(ergebnis.getFeld());
										int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl();
										if(aktuellePos3+spiel.getWuerfelZahl() < spiel.getBrett().getEndGelb().size() && 
												this.spiel.getBrett().getEndGelb().get(endFeldBelegt3).istFeldBelegt()==false){
											wegUebrig.clear();
											kannLaufen=true;
										}else if(spiel.getWuerfelZahl()==6){
											kannLaufen=false;
										}else{
											kannLaufen=true;
										}
										
									}
								}
								
								
							}
					
				}//Wenn 4 Figuren drauﬂen sind
				else if (this.spiel.bestand(this.spiel.getBrett().getStartGelb()) == 0) {
					//Wenn alle 4 Figuren auf dem Weg sind
					if(MeinWegBestand(FarbEnum.GELB)==4){
						wegUebrig.clear();
						for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
							if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
								figur = wegFeld.getFigur();
								int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
								
								if(feldIndex<limitGelbAnfang){
									wegUebrig.add(wegMaxGelb1 - feldIndex);
								}
								else if(feldIndex>=limitGelbAnfang){
									wegUebrig.add(wegMaxGelb2 - feldIndex);
								}
							}
						}
						Collections.sort(wegUebrig);
						int minIndex = wegUebrig.indexOf(Collections.min(wegUebrig));
						int min = Collections.min(wegUebrig);
						
						//Figur 1
						if(min<=wegMaxGelb1){
							wegMaxGelb2=wegMaxGelb1;
						}
						
						ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGelb2 - wegUebrig.get(minIndex)).getFigur();
						int aktuellePos=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
						
						if((aktuellePos+spiel.getWuerfelZahl()>limitGelbAnfang && aktuellePos>=limitGelbAnfang) ||aktuellePos+spiel.getWuerfelZahl()<limitGelbAnfang){
							if((aktuellePos+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos+spiel.getWuerfelZahl()){
								wegUebrig.clear();
								kannLaufen=true;
							}
						}else{
							if((aktuellePos+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos+spiel.getWuerfelZahl()){
								wegUebrig.clear();
								kannLaufen=true;
							}else{
								
								//Figur 2
								wegMaxGelb2=73;
								if(wegUebrig.get(minIndex+1)<=wegMaxGelb1){
									wegMaxGelb2=wegMaxGelb1;
								}
								
								ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGelb2 - wegUebrig.get(minIndex+1)).getFigur();
								int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
								
								if((aktuellePos2+spiel.getWuerfelZahl()>limitGelbAnfang && aktuellePos2>=limitGelbAnfang) ||aktuellePos2+spiel.getWuerfelZahl()<limitGelbAnfang){
									if((aktuellePos2+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos2>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos2+spiel.getWuerfelZahl()){
										wegUebrig.clear();
										kannLaufen=true;
									}
								}else{
									if((aktuellePos2+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos2>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos2+spiel.getWuerfelZahl()){
										wegUebrig.clear();
										kannLaufen=true;
									}else{
										
										//Figur 3
										wegMaxGelb2=73;
										if(wegUebrig.get(minIndex+2)<=wegMaxGelb1){
											wegMaxGelb2=wegMaxGelb1;
										}
										
										ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGelb2 - wegUebrig.get(minIndex+2)).getFigur();
										int aktuellePos3=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
										
										if((aktuellePos3+spiel.getWuerfelZahl()>limitGelbAnfang && aktuellePos3>=limitGelbAnfang) ||aktuellePos3+spiel.getWuerfelZahl()<limitGelbAnfang){
											if((aktuellePos3+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos3>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()){
												wegUebrig.clear();
												kannLaufen=true;
											}
										}else{
											if((aktuellePos3+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos3>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()){
												wegUebrig.clear();
												kannLaufen=true;
											}else{//Figur 4
												wegMaxGelb2=73;
												if(wegUebrig.get(minIndex+3)<=wegMaxGelb1){
													wegMaxGelb2=wegMaxGelb1;
												}
												ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGelb2 - wegUebrig.get(minIndex+3)).getFigur();
												wegUebrig.clear();
												kannLaufen=true;
												
											}
										}
									}
								}
							}
						}	
					}//Wenn 3 Figuren drauﬂen und 1 drinnen ist
					else if(MeinWegBestand(FarbEnum.GELB)==3){
						wegUebrig.clear();
						for(Spielfeld endBox:this.spiel.getBrett().getEndGelb()){
							if(endBox.istFeldBelegt()==true){
								figur = endBox.getFigur();
								int aktuellePos = this.spiel.getBrett().getEndGelb().indexOf(figur.getFeld());
								wegUebrig.add(endMax - aktuellePos);
								
							}
						}//Figur 1 drinnen
						ergebnis = this.spiel.getBrett().getEndGelb().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
						int aktuellePos=spiel.getBrett().getEndGelb().indexOf(ergebnis.getFeld());
						if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndGelb().size()){
							wegUebrig.clear();
							kannLaufen=true;
						}else{
							//wen 3 figuren auf dem weg sind und nur sie laufen koennen
							wegUebrig.clear();
							for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
								if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
									figur = wegFeld.getFigur();
									int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
									if(feldIndex<limitGelbAnfang){
										wegUebrig.add(wegMaxGelb1 - feldIndex);
									}
									else if(feldIndex>=limitGelbAnfang){
										wegUebrig.add(wegMaxGelb2 - feldIndex);
									}
								}
							}
							Collections.sort(wegUebrig);
							int minIndex = wegUebrig.indexOf(Collections.min(wegUebrig));
							int min = Collections.min(wegUebrig);
							
							//Figur 2 drauﬂen
							if(min<=wegMaxGelb1){
								wegMaxGelb2=wegMaxGelb1;
							}
							
							
								ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGelb2 - wegUebrig.get(minIndex)).getFigur();
								int aktuellePos2=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
								int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl()-limitGelbAnfang;
								
								if((aktuellePos2+spiel.getWuerfelZahl()>limitGelbAnfang && aktuellePos2>=limitGelbAnfang) ||aktuellePos2+spiel.getWuerfelZahl()<limitGelbAnfang){
									if((aktuellePos2+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos2>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos2+spiel.getWuerfelZahl()){
										wegUebrig.clear();
										kannLaufen=true;
									}
								}else{
									if((aktuellePos2+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos2>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos2+spiel.getWuerfelZahl() && 
											this.spiel.getBrett().getEndGelb().get(endFeldBelegt2).istFeldBelegt()==false &&
											this.uberholenPruefen(spiel.getBrett().getEndGelb())>endFeldBelegt2){
										wegUebrig.clear();
										kannLaufen=true;
									}else{
										//Figur 3 drauﬂen
										wegMaxGelb2=73;
										if(wegUebrig.get(minIndex+1)<=wegMaxGelb1){
											wegMaxGelb2=wegMaxGelb1;
										}
										
										ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGelb2 - wegUebrig.get(minIndex+1)).getFigur();
										int aktuellePos3=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
										int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl()-limitGelbAnfang;
										
										if((aktuellePos3+spiel.getWuerfelZahl()>limitGelbAnfang && aktuellePos3>=limitGelbAnfang) ||aktuellePos3+spiel.getWuerfelZahl()<limitGelbAnfang){
											if((aktuellePos3+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos3>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()){
												wegUebrig.clear();
												kannLaufen=true;
											}
										}else{
											if((aktuellePos3+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos3>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos3+spiel.getWuerfelZahl() && 
													this.spiel.getBrett().getEndGelb().get(endFeldBelegt3).istFeldBelegt()==false &&
													this.uberholenPruefen(spiel.getBrett().getEndGelb())>endFeldBelegt3){
												wegUebrig.clear();
												kannLaufen=true;
											}else{
												//Figur 4 drauﬂen
												wegMaxGelb2=73;
												if(wegUebrig.get(minIndex+2)<=wegMaxGelb1){
													wegMaxGelb2=wegMaxGelb1;
												}
												ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGelb2 - wegUebrig.get(minIndex+2)).getFigur();
												wegUebrig.clear();
												kannLaufen=true;
											}
										}
									}
								}
						}	
					}
					else if(MeinWegBestand(FarbEnum.GELB)==2){
						wegUebrig.clear();
						//wenn 2 drauﬂen sind und 2 drinnen
						for(Spielfeld endBox:this.spiel.getBrett().getEndGelb()){
							if(endBox.istFeldBelegt()==true){
								figur = endBox.getFigur();
								int feldIndex = this.spiel.getBrett().getEndGelb().indexOf(figur.getFeld());
								wegUebrig.add(endMax - feldIndex);
							}
						}//Figur 1 drinnen
								ergebnis = this.spiel.getBrett().getEndGelb().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
								int aktuellePos=spiel.getBrett().getEndGelb().indexOf(ergebnis.getFeld());
								if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndGelb().size()){
									wegUebrig.clear();
									kannLaufen=true;
								}else{//Figur 2 drinnen
									ergebnis = this.spiel.getBrett().getEndGelb().get(endMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
									int aktuellePos2=spiel.getBrett().getEndGelb().indexOf(ergebnis.getFeld());
									int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl();
									if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getEndGelb().size() && 
											this.spiel.getBrett().getEndGelb().get(endFeldBelegt2).istFeldBelegt()==false){
										wegUebrig.clear();
										kannLaufen=true;
									}else{
										wegUebrig.clear();
										for(Spielfeld wegFeld : this.spiel.getBrett().getWeg()){
											if(wegFeld.istFeldBelegt()==true && wegFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
												figur = wegFeld.getFigur();
												int feldIndex = this.spiel.getBrett().getWeg().indexOf(figur.getFeld());
												
												if(feldIndex<limitGelbAnfang){
													wegUebrig.add(wegMaxGelb1 - feldIndex);
												}
												else if(feldIndex>=limitGelbAnfang){
													wegUebrig.add(wegMaxGelb2 - feldIndex);
												}
											}
										}
										Collections.sort(wegUebrig);
										int minIndex = wegUebrig.indexOf(Collections.min(wegUebrig));
										int min = Collections.min(wegUebrig);
										
										//Figur 3 drauﬂen
										if(min<=wegMaxGelb1){
											wegMaxGelb2=wegMaxGelb1;
										}
										
											ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGelb2 - wegUebrig.get(minIndex)).getFigur();
											int aktuellePos3=spiel.getBrett().getWeg().indexOf(ergebnis.getFeld());
											int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl()-limitGelbAnfang;
											
											if((aktuellePos3+spiel.getWuerfelZahl()>limitGelbAnfang && aktuellePos3>=limitGelbAnfang) ||aktuellePos3+spiel.getWuerfelZahl()<limitGelbAnfang){
												if((aktuellePos3+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos3>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos3+spiel.getWuerfelZahl()){
													wegUebrig.clear();
													kannLaufen=true;
												}
											}else{
												if((aktuellePos3+spiel.getWuerfelZahl() > limitGelbAnfang && aktuellePos3>=limitGelbAnfang) || limitGelbAnfang+3>=aktuellePos3+spiel.getWuerfelZahl() && 
														this.spiel.getBrett().getEndGelb().get(endFeldBelegt3).istFeldBelegt()==false &&
														this.uberholenPruefen(spiel.getBrett().getEndGelb())>endFeldBelegt3){
													wegUebrig.clear();
													kannLaufen=true;
												}else{
													//Figur 4 drauﬂen
													wegMaxGelb2=73;
													if(wegUebrig.get(minIndex+1)<=wegMaxGelb1){
														wegMaxGelb2=wegMaxGelb1;
													}
													ergebnis = this.spiel.getBrett().getWeg().get(wegMaxGelb2 - wegUebrig.get(minIndex+1)).getFigur();
													wegUebrig.clear();
													kannLaufen=true;
													
												}
											}
										}
									}
								
							
							//wenn einer drauﬂen ist und 3 im Endfeld
						} else if(MeinWegBestand(FarbEnum.GELB)==1){
							wegUebrig.clear();
							for(Spielfeld endBox:this.spiel.getBrett().getEndGelb()){
								if(endBox.istFeldBelegt()==true){
									figur = endBox.getFigur();
									int aktuellePos = this.spiel.getBrett().getEndGelb().indexOf(figur.getFeld());
									wegUebrig.add(endMax - aktuellePos);
								}
							}//Figur 1 drinnen
							ergebnis = this.spiel.getBrett().getEndGelb().get(endMax - wegUebrig.get(wegUebrig.size()-1)).getFigur();
							int aktuellePos=spiel.getBrett().getEndGelb().indexOf(ergebnis.getFeld());
							if(aktuellePos+spiel.getWuerfelZahl() < spiel.getBrett().getEndGelb().size()){
								wegUebrig.clear();
								kannLaufen=true;
							}else{//Figur 2 drinnen
								ergebnis = this.spiel.getBrett().getEndGelb().get(endMax - wegUebrig.get(wegUebrig.size()-2)).getFigur();
								int aktuellePos2=spiel.getBrett().getEndGelb().indexOf(ergebnis.getFeld());
								int endFeldBelegt2=aktuellePos2 + spiel.getWuerfelZahl();
								if(aktuellePos2+spiel.getWuerfelZahl() < spiel.getBrett().getEndGelb().size() && 
										this.spiel.getBrett().getEndGelb().get(endFeldBelegt2).istFeldBelegt()==false){
									wegUebrig.clear();
									kannLaufen=true;
								}else{//Figur 3 drinnen
									ergebnis = this.spiel.getBrett().getEndGelb().get(endMax - wegUebrig.get(wegUebrig.size()-3)).getFigur();
									int aktuellePos3=spiel.getBrett().getEndGelb().indexOf(ergebnis.getFeld());
									int endFeldBelegt3=aktuellePos3 + spiel.getWuerfelZahl();
									if(aktuellePos3+spiel.getWuerfelZahl() < spiel.getBrett().getEndGelb().size() && 
											this.spiel.getBrett().getEndGelb().get(endFeldBelegt3).istFeldBelegt()==false){
										wegUebrig.clear();
										kannLaufen=true;
									}else{//Figur 4 drauﬂen
										for(Spielfeld feld : this.spiel.getBrett().getWeg()){
											if(feld.istFeldBelegt()==true && feld.getFigur().getFarbe()==this.spiel.getSpielerAmZug().getFarbe()){
												ergebnis = feld.getFigur();
												kannLaufen=true;
												break;
											}
										}
									}
								}
							}
						}
					}
			}
			
			
			
			
			
			
			
			
			
			
			
		}
		catch(Exception e){
			e.getMessage();
		}
		return ergebnis;
	}
 
	/**
	 * es wird ermittelt ob der Spieler am Zug also die KI eine gegnerische Spielfigur schlagen kann
	 * @return ergebnis ist das Ergebnis welche Spielfigur bewegt werden soll
	 */
	public Spielfigur ErmittleWertVonKannSchlagen() {
		Spielfigur ergebnis= null;
		try{
			if (this.spiel.getSpielerAmZug().getFarbe() == FarbEnum.ROT) {
				if (this.spiel.bestand(this.spiel.getBrett().getStartRot()) == 4 && this.spiel.getWuerfelZahl()==6 && this.spiel.getBrett().getWeg().get(0).istFeldBelegt()==true) {
					ergebnis=this.spiel.getBrett().getStartRot().get(0).getFigur();
					kannSchlagen=true;
					
					} else if (this.spiel.bestand(this.spiel.getBrett().getStartRot()) == 3) {
						for(Spielfigur schlaeger : this.spiel.getSpielerAmZug().getFigurlist()){
						int z = this.spiel.getBrett().getWeg().indexOf(schlaeger.getFeld());
						
						int posErg = this.spiel.getWuerfelZahl() + z;
						
						for(Spielfeld feld : this.spiel.getBrett().getWeg()){
							if(feld.istFeldBelegt()&&feld.getFigur().getFarbe()==this.spiel.getSpielerAmZug().getFarbe()){
								if (this.spiel.getBrett().getWeg().get(posErg).istFeldBelegt() == true) {
									if (this.spiel.getBrett().getWeg().get(posErg).getFigur().getFarbe() == this.spiel.getSpielerAmZug().getFarbe()) {
										kannSchlagen=false;
									
									} else {
											ergebnis = feld.getFigur();
											kannSchlagen = true;
											break;
											}
								}else{
									kannSchlagen=false;
								}
									}
								}
						break;
							}
						
						
						
						} else if ((this.spiel.bestand(this.spiel.getBrett().getStartRot()) < 3) && (this.spiel.bestand(this.spiel.getBrett().getStartRot()) >= 0)) {
							//this.spiel.spielerAmZug().getFigurlist()
							for(Spielfigur figurLaeufer:this.spiel.getSpielerAmZug().getFigurlist()){
								int z = this.spiel.getBrett().getWeg().indexOf(figurLaeufer.getFeld());
								int posErg = this.spiel.getWuerfelZahl() + z;
								for (Spielfeld a : this.spiel.getBrett().getWeg()) {
									if (this.spiel.getBrett().getFeld().getFigur().getFarbe() == FarbEnum.ROT) 
									{
										if (this.spiel.getBrett().getWeg().get(posErg).istFeldBelegt() == true) 
										{
											if (this.spiel.getSpielerAmZug().getFigur().getFarbe() == this.spiel.getSpielerAmZug().getFarbe()) 
											{
												kannSchlagen=false;
											} 
											else
											{
												//this.spiel.schlagen(posErg);
												ergebnis= figurLaeufer;
												kannSchlagen = true;
												break;
											}
										}
										else
										{
											break;
										}
										}
									}
								if(kannSchlagen == true){
									ergebnis= figurLaeufer;
									break;
									}
								}
							}
				}else if (this.spiel.getSpielerAmZug().getFarbe() == FarbEnum.BLAU) {
					if (this.spiel.bestand(this.spiel.getBrett().getStartBlau()) == 4) {
						kannSchlagen=false;
						} else if (this.spiel.bestand(this.spiel.getBrett().getStartBlau()) == 3) {
							int z = this.spiel.getBrett().getWeg().indexOf(this.spiel.getSpielerAmZug().getFigur().getFeld());
							int posErg = this.spiel.getWuerfelZahl() + z;
							for(Spielfeld feld : this.spiel.getBrett().getWeg()){
								if(feld.istFeldBelegt()&&feld.getFigur().getFarbe()==this.spiel.getSpielerAmZug().getFarbe()){
									if (this.spiel.getBrett().getWeg().get(posErg).istFeldBelegt() == true) {
										if (this.spiel.getSpielerAmZug().getFigur().getFarbe() == this.spiel.getSpielerAmZug().getFarbe()) {
											kannSchlagen=false;
											} else {
												ergebnis = feld.getFigur();
												kannSchlagen = true;
												break;
												}
										}
									}
								} 
							} else if ((this.spiel.bestand(this.spiel.getBrett().getStartBlau()) < 3) && (this.spiel.bestand(this.spiel.getBrett().getStartBlau()) >= 0)) {
								//this.spiel.spielerAmZug().getFigurlist()
								for(Spielfigur figurLaeufer:this.spiel.getSpielerAmZug().getFigurlist()){
									int z = this.spiel.getBrett().getWeg().indexOf(figurLaeufer.getFeld());
									int posErg = this.spiel.getWuerfelZahl() + z;
									for (Spielfeld a :this.spiel.getBrett().getWeg()) {
										if (this.spiel.getBrett().getFeld().getFigur().getFarbe() == FarbEnum.BLAU) {
											if (this.spiel.getBrett().getWeg().get(posErg).istFeldBelegt() == true) {
												if (this.spiel.getSpielerAmZug().getFigur().getFarbe() == this.spiel.getSpielerAmZug().getFarbe()) {
													kannSchlagen=false;
													} else {
														//this.spiel.schlagen(posErg);
														ergebnis= figurLaeufer;
														kannSchlagen = true;
														break;
														}
												}else{
													break;
													}
											}
										}
									if(kannSchlagen == true){
										ergebnis= figurLaeufer;
										break;
										}
									}
								}
					}else if (this.spiel.getSpielerAmZug().getFarbe() == FarbEnum.GRUEN) {
						if (this.spiel.bestand(this.spiel.getBrett().getStartGruen()) == 4) {
							kannSchlagen=false;
							} else if (this.spiel.bestand(this.spiel.getBrett().getStartGruen()) == 3) {
								int z = this.spiel.getBrett().getWeg().indexOf(this.spiel.getSpielerAmZug().getFigur().getFeld());
								int posErg = this.spiel.getWuerfelZahl() + z;
								for(Spielfeld feld : this.spiel.getBrett().getWeg()){
									if(feld.istFeldBelegt()&&feld.getFigur().getFarbe()==this.spiel.getSpielerAmZug().getFarbe()){
										if (this.spiel.getBrett().getWeg().get(posErg).istFeldBelegt() == true) {
											if (this.spiel.getSpielerAmZug().getFigur().getFarbe() == this.spiel.getSpielerAmZug().getFarbe()) {
												kannSchlagen=false;
												} else {
													ergebnis = feld.getFigur();
													kannSchlagen = true;
													break;
													}
											}
										}
									} 
								} else if ((this.spiel.bestand(this.spiel.getBrett().getStartGruen()) < 3) && (this.spiel.bestand(this.spiel.getBrett().getStartGruen()) >= 0)) {
									//this.spiel.spielerAmZug().getFigurlist()
									for(Spielfigur figurLaeufer:this.spiel.getSpielerAmZug().getFigurlist()){
										int z =this.spiel.getBrett().getWeg().indexOf(figurLaeufer.getFeld());
										int posErg = this.spiel.getWuerfelZahl() + z;
										for (Spielfeld a :this.spiel.getBrett().getWeg()) {
											if (this.spiel.getBrett().getFeld().getFigur().getFarbe() == FarbEnum.GRUEN) {
												if (this.spiel.getBrett().getWeg().get(posErg).istFeldBelegt() == true) {
													if (this.spiel.getSpielerAmZug().getFigur().getFarbe() == this.spiel.getSpielerAmZug().getFarbe()) {
														kannSchlagen=false;
														} else {
															//this.spiel.schlagen(posErg);
															ergebnis= figurLaeufer;
															kannSchlagen = true;
															break;
															}
													}else{
														break;
														}
												}
											}
										if(kannSchlagen == true){
											ergebnis= figurLaeufer;
											break;
											}
										}
									}
						}else if (this.spiel.getSpielerAmZug().getFarbe() == FarbEnum.GELB) {
							if (this.spiel.bestand(this.spiel.getBrett().getStartGelb()) == 4) {
								kannSchlagen=false;
								} else if (this.spiel.bestand(this.spiel.getBrett().getStartGelb()) == 3) {
									int z =this.spiel.getBrett().getWeg().indexOf(this.spiel.getSpielerAmZug().getFigur().getFeld());
									int posErg = this.spiel.getWuerfelZahl() + z;
									for(Spielfeld feld :this.spiel.getBrett().getWeg()){
										if(feld.istFeldBelegt()&&feld.getFigur().getFarbe()==this.spiel.getSpielerAmZug().getFarbe()){
											if (this.spiel.getBrett().getWeg().get(posErg).istFeldBelegt() == true) {
												if (this.spiel.getSpielerAmZug().getFigur().getFarbe() == this.spiel.getSpielerAmZug().getFarbe()) {
													kannSchlagen=false;
													} else {
														ergebnis = feld.getFigur();
														kannSchlagen = true;
														break;
														}
												}
											}
										} 
									} else if ((this.spiel.bestand(this.spiel.getBrett().getStartGelb()) < 3) && (this.spiel.bestand(this.spiel.getBrett().getStartGelb()) >= 0)) {
										//this.spiel.spielerAmZug().getFigurlist()
										for(Spielfigur figurLaeufer:this.spiel.getSpielerAmZug().getFigurlist()){
											int z = this.spiel.getBrett().getWeg().indexOf(figurLaeufer.getFeld());
											int posErg = this.spiel.getWuerfelZahl() + z;
											for (Spielfeld a :this.spiel.getBrett().getWeg()) {
												if (this.spiel.getBrett().getFeld().getFigur().getFarbe() == FarbEnum.GELB) {
													if (this.spiel.getBrett().getWeg().get(posErg).istFeldBelegt() == true) {
														if (this.spiel.getSpielerAmZug().getFigur().getFarbe() == this.spiel.getSpielerAmZug().getFarbe()) {
															kannSchlagen=false;
															} else {
																//this.spiel.schlagen(posErg);
																ergebnis= figurLaeufer;
																kannSchlagen = true;
																break;
																}
														}else{
															break;
															}
													}
												}
											if(kannSchlagen == true){
												ergebnis= figurLaeufer;
												break;
												}
											}
										}
							}
			
			}catch(Exception e){
				e.getMessage();
				}
		return ergebnis;
		}
 
	/**
	 * es wird ermittelt ob eine Figur des Spielers Am Zug also die KI aus ihrer Startbox ins Spiel geholt werden kann
	 * @return ergebnis ist das Ergebnis welche Spielfigur bewegt werden soll
	 */
	public Spielfigur ErmittleKannRaus() {
		Spielfigur ergebnis= null;
 
		try{
			if (this.spiel.getSpielerAmZug().getFarbe() == FarbEnum.ROT) {
				
				if ((this.spiel.bestand(this.spiel.getBrett().getStartRot())<=4)&&(this.spiel.bestand(this.spiel.getBrett().getStartRot())>0)){
					for(Spielfeld figurRausgeher:this.spiel.getBrett().getStartRot() ){
						if (MeinWegBestand(FarbEnum.ROT)==0 && this.spiel.getWuerfelZahl()!=6 && this.spiel.bestand(this.spiel.getBrett().getEndRot())==0) {
							ergebnis=figurRausgeher.getFigur();
							kannRaus=true;
							break;
						}
						if(figurRausgeher.istFeldBelegt()==true){
					if (this.spiel.getWuerfelZahl() == 6) {
						if (this.spiel.getBrett().getWeg().get(0).getFigur() != null) {
							if (this.spiel.getBrett().getWeg().get(0).getFigur().getFarbe() == FarbEnum.ROT) {
								kannRaus=false;
								} else {
									//this.spiel.schlagen(1);
									//this.spiel.startPosZuweisen(spiel.getSpielerAmZug());
									
									kannRaus=true;
									ergebnis=figurRausgeher.getFigur();
									break;
									}
							} else {
								//this.spiel.startPosZuweisen(spiel.getSpielerAmZug());
								kannRaus = true;
								ergebnis=figurRausgeher.getFigur();
								break;
								}
					}else {
						kannRaus=false;
					}
						//this.spiel.bewegen(feldzahl, true);
						} else {
							kannRaus=false;
							//this.spiel.spielerAmZug();
							}
					}
					} else if (this.spiel.bestand(this.spiel.getBrett().getStartRot()) == 0) {
						kannRaus=false;
						}
				
			}
			else if (this.spiel.getSpielerAmZug().getFarbe() == FarbEnum.BLAU) {
					if ((this.spiel.bestand(this.spiel.getBrett().getStartBlau())<=4)&&(this.spiel.bestand(this.spiel.getBrett().getStartBlau())>0)){
						for(Spielfeld figurRausgeher:this.spiel.getBrett().getStartBlau() ){
							if (MeinWegBestand(FarbEnum.BLAU)==0 && this.spiel.getWuerfelZahl()!=6 && this.spiel.bestand(this.spiel.getBrett().getEndBlau())==0) {
								ergebnis=figurRausgeher.getFigur();
								kannRaus=true;
								break;
								
							}
							if(figurRausgeher.istFeldBelegt()==true){
						if (this.spiel.getWuerfelZahl() == 6) {
							if (this.spiel.getBrett().getWeg().get(10).getFigur() != null) {
								if (this.spiel.getBrett().getWeg().get(10).getFigur().getFarbe() == FarbEnum.BLAU) {
									kannRaus=false;
									} else {
										//this.spiel.schlagen(1);
										//this.spiel.startPosZuweisen(spiel.getSpielerAmZug());
										kannRaus=true;
										ergebnis=figurRausgeher.getFigur();
										break;
										}
								} else {
									//this.spiel.startPosZuweisen(spiel.getSpielerAmZug());
									kannRaus = true;
									ergebnis=figurRausgeher.getFigur();
									break;
									}
						}//this.spiel.bewegen(feldzahl, true);
							} else {
								kannRaus=false;
								//this.spiel.spielerAmZug();
								}
						}
						} else if (this.spiel.bestand(this.spiel.getBrett().getStartBlau()) == 0) {
							kannRaus=false;
							}
					}else if (this.spiel.getSpielerAmZug().getFarbe() == FarbEnum.GRUEN) {
						if ((this.spiel.bestand(this.spiel.getBrett().getStartGruen())<=4)&&(this.spiel.bestand(this.spiel.getBrett().getStartGruen())>0)){
							for(Spielfeld figurRausgeher:this.spiel.getBrett().getStartGruen() ){
								if (MeinWegBestand(FarbEnum.GRUEN)==0 && this.spiel.getWuerfelZahl()!=6 && this.spiel.bestand(this.spiel.getBrett().getEndGruen())==0) {
									ergebnis=figurRausgeher.getFigur();
									kannRaus=true;
									break;
									
								}
								if(figurRausgeher.istFeldBelegt()==true){
							if (this.spiel.getWuerfelZahl() == 6) {
								if (this.spiel.getBrett().getWeg().get(20).getFigur()  != null) {
									if (this.spiel.getBrett().getWeg().get(20).getFigur().getFarbe() == FarbEnum.GRUEN) {
										kannRaus=false;
										} else {
											//this.spiel.schlagen(1);
											//this.spiel.startPosZuweisen(spiel.getSpielerAmZug());
											kannRaus=true;
											ergebnis=figurRausgeher.getFigur();
											break;
											}
									} else {
										//this.spiel.startPosZuweisen(spiel.getSpielerAmZug());
										kannRaus = true;
										ergebnis=figurRausgeher.getFigur();
										break;
										}
							}//this.spiel.bewegen(feldzahl, true);
								} else {
									kannRaus=false;
									//this.spiel.spielerAmZug();
							}
								}
							} else if (this.spiel.bestand(this.spiel.getBrett().getStartGruen()) == 0) {
								kannRaus=false;
								}
						}
					
					
					
					else if (this.spiel.getSpielerAmZug().getFarbe() == FarbEnum.GELB) {
							if ((this.spiel.bestand(this.spiel.getBrett().getStartGelb())<=4)&&(this.spiel.bestand(this.spiel.getBrett().getStartGelb())>0)){
								for(Spielfeld figurRausgeher:this.spiel.getBrett().getStartGelb() ){
									if (MeinWegBestand(FarbEnum.GELB)==0 && this.spiel.getWuerfelZahl()!=6 && this.spiel.bestand(this.spiel.getBrett().getEndGelb())==0) {
										ergebnis=figurRausgeher.getFigur();
										kannRaus=true;
										break;
										
									}
									if(figurRausgeher.istFeldBelegt()==true){
								if (this.spiel.getWuerfelZahl()== 6) {
									if (this.spiel.getBrett().getWeg().get(30).getFigur()  != null) {
										if (this.spiel.getBrett().getWeg().get(30).getFigur().getFarbe() == FarbEnum.GELB) {
											kannRaus=false;
											} else {
												//this.spiel.schlagen(1);
												//this.spiel.startPosZuweisen(spiel.getSpielerAmZug());
												kannRaus=true;
												ergebnis=figurRausgeher.getFigur();
												break;
												}
										} else {
											//this.spiel.startPosZuweisen(spiel.getSpielerAmZug());
											kannRaus = true;
											ergebnis=figurRausgeher.getFigur();
											break;
											}
								}//this.spiel.bewegen(feldzahl, true);
									} else {
										kannRaus=false;
										//this.spiel.spielerAmZug();
										}
								}
								} else if (this.spiel.bestand(this.spiel.getBrett().getStartGelb()) == 0) {
									kannRaus=false;
									}
							}
			
			}catch(Exception e){
				e.getMessage();
				}
		return ergebnis;
		}

	public Spielbrett getBrett() {
		return brett;
	}

	public void setBrett(Spielbrett brett) {
		this.brett = brett;
	}
	}
