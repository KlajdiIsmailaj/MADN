package testmadn;

import java.io.Serializable;

 
public class Wuerfel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
//	private Spieler player;

	public Wuerfel(){
	}

	public static int werfen () {
		
		
		int zufall = (int) (6 * Math.random()) + 1;
//		if (zufall!=6){
//			return 6;
//		}
		return zufall;
	}
	
	
	
	@Override
	public String toString() {
		return (""+werfen());
	}
	
}