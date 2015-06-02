package testmadn;

import java.io.*;

import testmadn.SpielBean;


/**
 * Klasse DatenzugriffSerialisiert
 * @author Gruppe A2
 *
 */
public class DatenzugriffSerialisiert implements iDatenzugriff,Serializable{
	private static final long serialVersionUID = 1L;
/**
 * methode speichern aus dem Interface iDatenzugriff wird implementiert
 */
	@Override
	public void speichern(String s,Object sp){
		String sav = s;
		sav.concat(".ser");
		try{
			ObjectOutputStream oos1= new ObjectOutputStream(new FileOutputStream(sav+".ser"));
			oos1.writeObject(sp);
			oos1.close();
			System.out.println("SPIEL WURDE GESPEICHERT !");
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
/**
 * methode laden aus dem Interface iDatenzugriff wird implementiert
 */
	@Override
	public Object laden(String filename) {
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		Object obj = null;
		try {
		  fis = new FileInputStream(filename);
		  ois = new ObjectInputStream(fis);
		  obj = ois.readObject();
//		  	if (obj instanceof Spiel) {
//			  Spiel sp = (Spiel)obj;
//			  System.out.println(sp.getSpielerAmZug()); // String
//			  System.out.println(sp.getWuerfelZahl()); // 1
//			  System.out.println(sp.getBrett()); // true
//		  	}
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
		  e.printStackTrace();
		}
		finally {
		  if (ois != null) try { ois.close(); } catch (IOException e) {}
		  if (fis != null) try { fis.close(); } catch (IOException e) {}
		}
		  return obj;
	}

}
