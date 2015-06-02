package testmadn;

import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * Es wird eine Spielfigur erzeugt
 * @author Gruppe A2
 *
 */
public class Spielfigur extends ImageIcon implements Serializable {
	private static final long serialVersionUID = 1L;
 
 
	private FarbEnum farbe;
	private int id;
	private Spielfeld feld;
	private Spieler player;
 
	
	/**
	 * Der Konstruktor
	 * @param farbe ist die Farbe der Spielfigur
	 */
	public Spielfigur(FarbEnum farbe, int id, Spielfeld feld) {
		this.setId(id);
		this.setFarbe(farbe);
		this.setFeld(feld);
		this.setPlayer(player);
	}
 
 
	/**
	 * die Figur ID des jeweiligen Spielers wird gesetzt
	 * @param figurID ist die Spielfigur ID eines Spielers
	 */
	// -------Setter & Getter-----------
	public void setId(int id) {
		if(id>=0&&id<=3){
			this.id = id;
		}
		else{
			throw new RuntimeException("Id darf nur von 1 bis 4 sein !");
		}
		
	}
	
	/**
	 * Die Figur ID des jeweiligen Spielers wird zurueck gegeben
	 * @return figurID ist die Spielfigur ID eines Spielers
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * die Farbe der Spielfigur wird zurueck gegeben
	 * @return farbe ist die Farbe der Spielfigur
	 */
	public FarbEnum getFarbe() {
		return farbe;
	}
	
	/**
	 * die Farbe der Spielfigur wird gesetzt
	 * @param farbe ist die Farbe der Spielfigur
	 */
	public void setFarbe(FarbEnum farbe){
		if(farbe==null){
			throw new RuntimeException("Eine Figur muss ein Farbe haben!");
		}
		this.farbe = farbe;
		
	}
	
	/**
	 * das Spielfeld der darauf stehenden Spielfigur wird zurueck gegeben
	 * @return spielfeld ist das momentane Spielfeld einer Spielfigur
	 */
	public Spielfeld getFeld() {		
		return feld;
	}
	
	/**
	 * das Spielfeld der darauf stehenden Spielfigur wird gesetzt
	 * @param spielfeld ist das momentane Spielfeld einer Spielfigur
	 */
	public void setFeld(Spielfeld feld){
//		if(feld==null){
//			throw new RuntimeException("figur hat kein feld !");
//		}
		
//		if(this.getFarbe()!=feld.getFarbe() && feld.getFarbe()!=null){
//			throw new RuntimeException("farbe muss gleich sein"); 
//		}
		
		
		this.feld = feld;
	}
	
	/**
	 * gibt den spieler der figur aus
	 * @return player ist der spieler von dieser figur
	 */
	public Spieler getPlayer() {
		return player;
	}

	/**
	 * setzt eine figur auf ein spieler
	 * @param player ist der spieler von dieser figur
	 */
	public void setPlayer(Spieler player) {
//		if (player==null){
//			throw new RuntimeException("Figur kann ohne Spieler nicht erzeugt werden !");
//		}
		this.player = player;
	}
	// -------------------------------
	
	
	
 
	@Override
	public String toString() {
		return 
				("Farbe: " + getFarbe() + 
				" | "+ "ID: "+ getId()
//				+" --- "+"Position: "+ getFeld()//.getId()     // .getId() nicht kommentieren wen stack overflow kommt
				);			
	}




}