package testmadn;



/**
 * Klasse zum testen f�r serialisierung laden
 * @author Gruppe2
 *
 */

public class SpielTestLaden {
	

	public static void main(String[] args) {
		iDatenzugriff d =new DatenzugriffSerialisiert();
		//d= new DatenzugriffSerialisiert();
		SpielBean s = (SpielBean)d.laden("test.ser");
		//iBediener p = (Spiel) d.laden();
		
	}

}
