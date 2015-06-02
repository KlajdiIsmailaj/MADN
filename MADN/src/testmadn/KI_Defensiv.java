package testmadn;

import java.io.Serializable;


/**
 * die Unterklasse KI Defensiv von KI wird erzeugt
 * @author Gruppe A2
 *
 */
 
public class KI_Defensiv extends KI implements Serializable {
	private static final long serialVersionUID = 1L;
 
//	private boolean kannSchlagen = false;
//	private boolean kannLaufen = false;
//	private boolean kannRaus = false;
 
	/**
	 * der Konstruktor der Klasse KI Aggressiv
	 * @param spieler ist die KI 
	 */
 
	public KI_Defensiv(SpielBean spiel) {
		super(spiel);
	}
 
	/**
	 * die Methode ErmittleZuSpielendeFigur aus der Oberklasse KI wird ¸berschrieben
	 */
 
	@Override
	public Spielfigur ermittleFigur(){
		Spielfigur ergebnis = null;
		Spielfigur schlaeger = ErmittleWertVonKannSchlagen();
		Spielfigur laeufer = ErmittleKannLaufenUndWer();
		Spielfigur rausgeher = ErmittleKannRaus();
		
		if(kannLaufen==true){
			ergebnis = laeufer;
			}
		else if (kannRaus==true){
			ergebnis = rausgeher;
			}
		else if (kannSchlagen==true){
			ergebnis = schlaeger;
			}
		return ergebnis;
		}
	}