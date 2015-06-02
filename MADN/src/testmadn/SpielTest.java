package testmadn;



/**
 * fuehrt das ganze Spiel aus 
 * @author Gruppe A2
 *
 */
public class SpielTest {

	public static void main(String[] args) {
		
		SpielBean spiel1 = new SpielBean();
		
		Spieler gelb=new Spieler("Klajdi",FarbEnum.GELB,null);
		Spieler gruen=new Spieler("Myt",FarbEnum.GRUEN,null);
//		spiel1.newSpieler("Klajdi", "Rot", "KI Aggressiv");
//		Spieler blau=new Spieler(null,null,null);
//		Spieler gruen=new Spieler("Kati",FarbEnum.GRUEN,new KI_Defensiv(spiel1));
//		Spieler gelb=new Spieler("Ray",FarbEnum.GELB,null);
		
		
		
		spiel1.nimmtTeil(gelb);
		spiel1.nimmtTeil(gruen);
//		spiel1.nimmtTeil(gruen);
//		spiel1.nimmtTeil(gelb);

		spiel1.spielStart();
//		rot.wuerfeln();

		
//		if(spiel1.getBrett().getWeg().get(0) instanceof JButton){
//			System.out.println("im button");
//		}
//		
//		if(rot.getFigurlist().get(0) instanceof ImageIcon){
//			System.out.println("im figur");
//		}
		
		
		
//		-------------------KI LAEUFT------------------------
		spiel1.setWuerfelZahl(6);	
		spiel1.laufen(0);
		spiel1.setWuerfelZahl(1);	
		spiel1.laufen(0);
		spiel1.beenden();
//		
		spiel1.setWuerfelZahl(6);	
		spiel1.laufen(0);
		spiel1.setWuerfelZahl(11);	
		spiel1.laufen(0);
//		spiel1.laufen(spiel1.gibFigurKi());
		
//		spiel1.setWuerfelZahl(6);
//		spiel1.laufen(rot.getKi().ermittleFigur().getId());
//		spiel1.setWuerfelZahl(9);
//		spiel1.laufen(rot.getKi().ermittleFigur().getId());
//		spiel1.beenden();
//		
//		spiel1.setWuerfelZahl(6);
//		spiel1.laufen(blau.getKi().ermittleFigur().getId());
//		spiel1.setWuerfelZahl(1);
//		spiel1.laufen(blau.getKi().ermittleFigur().getId());
//		spiel1.beenden();
//		
//		spiel1.setWuerfelZahl(2);
//		spiel1.laufen(rot.getKi().ermittleFigur().getId());
		
//		spiel1.laufen(spiel1.gibFigurKi());
//		
//		spiel1.setWuerfelZahl(4);	
//		spiel1.laufen(spiel1.gibFigurKi());
//		
//		spiel1.setWuerfelZahl(6);
//		spiel1.laufen(spiel1.gibFigurKi());
//		
//		spiel1.setWuerfelZahl(39);
//		spiel1.laufen(spiel1.gibFigurKi());
//		
//		spiel1.setWuerfelZahl(3);	
//		spiel1.laufen(spiel1.gibFigurKi());
//		
//		spiel1.setWuerfelZahl(6);
//		spiel1.laufen(spiel1.gibFigurKi());
//		
//		spiel1.setWuerfelZahl(39);
//		spiel1.laufen(spiel1.gibFigurKi());
//		
//		spiel1.setWuerfelZahl(2);	
//		spiel1.laufen(spiel1.gibFigurKi());
//		
//		spiel1.setWuerfelZahl(6);
//		spiel1.laufen(spiel1.gibFigurKi());
//		
//		spiel1.setWuerfelZahl(39);
//		spiel1.laufen(spiel1.gibFigurKi());
//		
//		spiel1.setWuerfelZahl(2);	
//		spiel1.laufen(spiel1.gibFigurKi());
//		spiel1.setWuerfelZahl(1);	
//		spiel1.laufen(spiel1.gibFigurKi());
		
//		spiel1.beenden();
////		
//		spiel1.setWuerfelZahl(5);
//		spiel1.laufen(rot.getKi().ermittleFigur().getId());
////
//		spiel1.setWuerfelZahl(3);
//		spiel1.laufen(rot.getKi().ermittleFigur().getId());
//		spiel1.setWuerfelZahl(6);
//		spiel1.laufen(rot.getKi().ermittleFigur().getId());
////		spiel1.beenden();
////		
//		spiel1.setWuerfelZahl(3);
//		spiel1.laufen(rot.getKi().ermittleFigur().getId());
//		spiel1.setWuerfelZahl(2);
//		spiel1.laufen(rot.getKi().ermittleFigur().getId());
//		spiel1.setWuerfelZahl(6);
//		spiel1.laufen(rot.getKi().ermittleFigur().getId());
		
		
//		spiel1.setWuerfelZahl(39);
//		spiel1.laufen(rot.getKi().ermittleFigur().getId());
//		spiel1.setWuerfelZahl(1);
//		spiel1.laufen(rot.getKi().ermittleFigur().getId());
		
		
		
		
		
		
		
		
		
//		-----------------SPIELER LAEUFT---------------------
//		spiel1.setWuerfelZahl(6);
//		spiel1.laufen(1);
//		
//		spiel1.setWuerfelZahl(5);
//		spiel1.laufen(1);
////		System.out.println(spiel1.updatePos(0, 0));
//
//		spiel1.beenden();
////		
//		spiel1.setWuerfelZahl(6);
//		spiel1.laufen(0);
////		spiel1.setWuerfelZahl();
////		spiel1.laufen(0);
////		spiel1.beenden();
////		
//		spiel1.setWuerfelZahl(1);
//		spiel1.laufen(0);
//		spiel1.beenden();
////		
//		spiel1.setWuerfelZahl(7);
//		spiel1.laufen(1);
////		spiel1.setWuerfelZahl(1);
////		spiel1.laufen(2);
//		spiel1.beenden();
////		
//		spiel1.setWuerfelZahl(1);
//		spiel1.laufen(0);
//		spiel1.setWuerfelZahl(11);
//		spiel1.laufen(2);
//		spiel1.beenden();
//		
//		spiel1.setWuerfelZahl(5);
//		spiel1.laufen(1);
//		spiel1.beenden();
//		
//		spiel1.setWuerfelZahl(29);
//		spiel1.laufen(2);
//		spiel1.beenden();
//		
//		spiel1.setWuerfelZahl(2);
//		spiel1.laufen(1);
//		spiel1.beenden();
//		
//		spiel1.setWuerfelZahl(6);
//		spiel1.laufen(3);
////		spiel1.setWuerfelZahl(41); //ueberholen ueberpruefen
////		spiel1.laufen(3);
//		spiel1.setWuerfelZahl(1);
//		spiel1.laufen(1);
//		spiel1.setWuerfelZahl(2);
//		spiel1.laufen(2);
//		spiel1.setWuerfelZahl(41);
//		spiel1.laufen(3);
//		spiel1.setWuerfelZahl(6);
//		spiel1.laufen(4);
//		spiel1.setWuerfelZahl(40);
//		spiel1.laufen(4);
//		spiel1.beenden();
		
		
		
//		System.out.println(spiel1.getBrett().getEndRot());
		
		
		
	}

}