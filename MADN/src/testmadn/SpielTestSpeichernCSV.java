package testmadn;



/**
 * klasse zum testen f�r CSV speichern
 */
import java.io.PrintWriter;
public class SpielTestSpeichernCSV {
	public static void main(String[] args) {
		PrintWriter pw = null;
		iDatenzugriff d=new DatenzugriffSerialisiert();
		SpielBean spiel1=new SpielBean();
		Spieler s1=new Spieler("Kati",FarbEnum.BLAU, null);
	
		spiel1.nimmtTeil(s1);
//		spiel1.nimmtTeil(s1);
		
		
		spiel1.spielStart();

		System.out.println("Hallo");
		d.speichern("test6.csv",spiel1);
	}
}
