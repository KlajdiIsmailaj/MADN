

package testmadn;





/**
 * Klasse zum testen von speichern
 * @author Gruppe A2
 *
 */
public class SpielTestSpeichern {
	public static void main(String []args){
		iDatenzugriff d=new DatenzugriffSerialisiert();
		SpielBean spiel1=new SpielBean();
		Spieler s1=new Spieler("Kati",FarbEnum.ROT, null);
		spiel1.nimmtTeil(s1);
		
		spiel1.spielStart();
		d.speichern( "test.ser",spiel1);
		
		System.out.println(spiel1.getBrett());
	}

}
