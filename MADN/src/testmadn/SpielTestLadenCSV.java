package testmadn;


/**
 * klasse zum testen ob CSV geladen werden kann
 * @author Gruppe A2
 *
 */
public class SpielTestLadenCSV {
	public static void main(String[] args) {
		iDatenzugriff d =new DatenzugriffSerialisiert();
		//d= new DatenzugriffSerialisiert();
		SpielBean s = (SpielBean)d.laden("test6.csv");
		
	}
}
