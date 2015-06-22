package testmadn;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;



/**
 * die Unterklasse KI Aggressiv von KI wird erzeugt
 * @author Gruppe A2
 *
 */
@XmlType
public class KI_Aggressiv extends KI implements Serializable {
	private static final long serialVersionUID = 1L;
	
 
//	private boolean kannSchlagen = false;
//	private boolean kannLaufen = false;
//	private boolean kannRaus = false;
	
	public KI_Aggressiv(){
		
	}
 
	/**
	 * der Konstruktor der Klasse KI Aggressiv
	 * @param spieler ist die KI 
	 */
 
	public KI_Aggressiv(SpielBean spiel) {
		super(spiel);
		}
 
	/**
	 * die Methode ErmittleZuSpielendeFigur aus der Oberklasse KI wird �berschrieben
	 */
 
	@Override
	public Spielfigur ermittleFigur(){
		
		Spielfigur ergebnis = null;
		
		Spielfigur schlaeger = ErmittleWertVonKannSchlagen();
		Spielfigur rausgeher = ErmittleKannRaus();
		Spielfigur laeufer = ErmittleKannLaufenUndWer();
		
		
		
		if(kannSchlagen==true){
			ergebnis = schlaeger;
			}
		else if (kannRaus==true){
			ergebnis = rausgeher;
			}
		else if (kannLaufen==true){
			ergebnis = laeufer;
			}
		
		return ergebnis;
		
		}
	
	}