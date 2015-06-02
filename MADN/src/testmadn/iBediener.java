package testmadn;


/**
 * Klasse der vorhanden Methoden die ein Spieler nutzen kann
 * @author Gruppe A2
 *
 */
public interface iBediener {


	void laufen(int figurId);
	void setWuerfelZahl(Integer wuerfelZahl);
	void wuerfeln();
	void beenden();
	void spielStart();
	void nimmtTeil(Spieler pl);
	void newSpieler(String name, String Farbe, String KI);
	String updatePos(int figurID);
	String giveList(int figurID);
	String farbePlayer();
	int ermittleSpielerAmZug();
	int gibFigurKi();
	boolean ermittleGewinner();
	Integer getWuerfelZahl();
	int bestandSpielerlist();
	String ladeFarbe(int i);
	int updatePosLaden(int spieler, int figurID);
	String ladeName(int i);
	
}

