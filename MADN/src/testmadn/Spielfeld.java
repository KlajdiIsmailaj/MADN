package testmadn;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Es wird ein Spielfeld erzeugt
 * @author Gruppe A2
 *
 */
@XmlType(propOrder={"id","farbe","figur"})
public class Spielfeld implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private String id;
	private FarbEnum farbe;
	
	private Spielbrett brett;
	private Spielfigur figur;
	
	public Spielfeld(){
		
	}
	
	/**
	 * Konstruktor der Klasse
	 * @param farbe ist die Farbe eines Spielfeldes
	 * @param id ist die ID eines Spielfeldes
	 */
	public Spielfeld(FarbEnum farbe, String id){
		this.setId(id);
		this.setFarbe(farbe);
//		this.setFigur(figur);
	}

	/**
	 * die ID eines Spielfeldes wird zurueck gegeben
	 * @return id ist die ID eines Spielfeldes
	 */
	//----------Setter & Getter-----------
	
	@XmlAttribute(name="Feld-Id")
	public String getId(){
		return id;
	}
	
	/**
	 * die ID eines Spielfeldes wird gesetzt
	 * @param id ist die ID eines Spielfeldes
	 */
	private void setId(String id){
		
		
		if(id==null){
			throw new RuntimeException("ein feld muss eine id haben !");
		}
		
		else if( id.equals("1") ||id.equals("2")||id.equals("3")||id.equals("4")||id.equals("5")||id.equals("6")||id.equals("7")||id.equals("8")||id.equals("9")||id.equals("10")||
			id.equals("11")||id.equals("12")||id.equals("13")||id.equals("14")||id.equals("15")||id.equals("16")||id.equals("17")||id.equals("18")||id.equals("19")||id.equals("20")||
			id.equals("21")||id.equals("22")||id.equals("23")||id.equals("24")||id.equals("25")||id.equals("26")||id.equals("27")||id.equals("28")||id.equals("29")||id.equals("30")||
			id.equals("31")||id.equals("32")||id.equals("33")||id.equals("34")||id.equals("35")||id.equals("36")||id.equals("37")||id.equals("38")||id.equals("39")||id.equals("40")){
			this.id=id;
		}
		
		else if(id.equals("S1")||id.equals("S2")||id.equals("S3")||id.equals("S4")){
			this.id=id;
		}
		
		else if(id.equals("E1")||id.equals("E2")||id.equals("E3")||id.equals("E4")){
			this.id=id;
		}
		
		else{
			throw new RuntimeException("keine gueltige id");
		}
		
	}
	
	/**
	 * eine auf dem Feld stehende Spielfigur wird zurueck gegeben
	 * @return figur ist die Spielfigur auf einem Spielfeld
	 */
	public Spielfigur getFigur() {
		return figur;
	}
	
	/**
	 * eine auf dem Feld stehende Spielfigur wird gesetzt
	 * @param figur ist die Spielfigur auf einem Spielfeld
	 */
	@XmlElement(name="Figur")
	public void setFigur(Spielfigur figur){
//		if(figur == null){
//			throw new RuntimeException("Die Figur ist nicht vorhanden!");
//		}
		
		this.figur=figur;
		
		if(figur!=null){
			this.figur.setFeld(this);
		}
		
	}
	
	/**
	 * die Farbe eines Spielfeldes wird zurueck gegeben
	 * @return farbe ist die Farbe eines Spielfeldes
	 */
	@XmlElement(name="Feldfarbe")
	public FarbEnum getFarbe() {
		return farbe;
	}
	
	/**
	 * die Farbe eines Spielfeldes wird gesetzt
	 * @param farbe ist die Farbe eines Spielfeldes
	 */
	public void setFarbe(FarbEnum farbe) {
		
		this.farbe=farbe;
	}
	
	/**
	 * gibt an welches feld auf welchem ArrayList sitzt
	 * @return brett ist die ArrayList wo das feld liegt
	 */
	@XmlTransient
	public Spielbrett getBrett() {
		return brett;
	}

	/**
	 * setzt das ein brett auf ein feld
	 * @param brett ist die ArrayList wo das feld liegt
	 */
	public void setBrett(Spielbrett brett) {
		this.brett = brett;
	}
	//----------------------------------
	
	
	/**
	 * schaut ob das feld durch eine figur belegt ist
	 */
	public boolean istFeldBelegt() {
		
		if (figur == null) {
			return false;
		}
		
		else{
			return true;
		}
	}
	


	
	@Override
	public String toString(){
		return 
				("Farbe: " + getFarbe() +
				" | "+ "ID: " + getId() +
				" --- "+"FIGUR: "+ getFigur() +
				"\n"); 
		
	}



}
