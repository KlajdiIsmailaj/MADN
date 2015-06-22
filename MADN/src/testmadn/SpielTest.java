package testmadn;



/**
 * fuehrt das ganze Spiel aus 
 * @author Gruppe A2
 *
 */
public class SpielTest {

	public static void main(String[] args) {
		
		iDatenzugriff d=new DatenzugriffXML();
		
//		SpielBean spiel1 = new SpielBean();
//		spiel1.newSpieler("Klajdi", "Rot", "Mensch");
//		spiel1.newSpieler("Myt", "Blau", "KI Aggressiv");
		
		
//		Spieler rot=new Spieler("Klajdi",FarbEnum.ROT,null);
//		Spieler blau=new Spieler("Myt",FarbEnum.BLAU,null);
//		spiel1.newSpieler("Klajdi", "Rot", "KI Aggressiv");
//		Spieler blau=new Spieler(null,null,null);
//		Spieler gruen=new Spieler("Kati",FarbEnum.GRUEN,new KI_Defensiv(spiel1));
//		Spieler gelb=new Spieler("Ray",FarbEnum.GELB,null);
		
		
		
//		spiel1.nimmtTeil(gelb);
//		spiel1.nimmtTeil(gruen);
//		spiel1.nimmtTeil(gruen);
//		spiel1.nimmtTeil(gelb);

//		spiel1.spielStart();
//		rot.wuerfeln();

		
//		if(spiel1.getBrett().getWeg().get(0) instanceof JButton){
//			System.out.println("im button");
//		}
//		
//		if(rot.getFigurlist().get(0) instanceof ImageIcon){
//			System.out.println("im figur");
//		}
		
		SpielBean spiel1=(SpielBean)d.laden("/Users/sevenvista/Desktop/kitest.xml");
	
		
//		-------------------KI LAEUFT------------------------
//		System.out.println(spiel1.getSpielerlist());
//		System.out.println(spiel1.getSpielerAmZug().getName());
//		System.out.println(spiel1.getBrett());
//		for(Spieler a:spiel1.getSpielerlist()){
//			if(a!=null&&a.getKi()!=null){
//				a.getKi().setSpiel(spiel1);
//			}
//		}
		
		spiel1.setWuerfelZahl(6);	
		spiel1.laufen(3);
		
		
//		System.out.println(spiel1.getBrett());
		spiel1.setWuerfelZahl(5);	
		spiel1.laufen(0);
		spiel1.beenden();
		
		spiel1.wuerfeln();
//		if(spiel1.getSpielerlist().get(1)==spiel1.getSpielerAmZug()){
//			System.out.println("true");
//		}else{
//			System.out.println("false");
//		}
//		System.out.println(spiel1.getSpielerAmZug().getFarbe());
//		if(spiel1.getSpielerAmZug().getFarbe()==FarbEnum.BLAU){
//			System.out.println("true");
//		}else{
//			System.out.println("false");
//		}
		spiel1.laufen(spiel1.gibFigurKi());
//		
////		
//		
//		spiel1.setWuerfelZahl(6);
//		spiel1.laufen(0);
//		spiel1.setWuerfelZahl(28);
//		spiel1.laufen(0);
//		spiel1.setWuerfelZahl(6);
//		spiel1.laufen(0);
//		spiel1.setWuerfelZahl(9);
//		spiel1.laufen(0);
//		
//		spiel1.setWuerfelZahl(6);
//		spiel1.laufen(1);
//		spiel1.setWuerfelZahl(28);
//		spiel1.laufen(1);
//		spiel1.setWuerfelZahl(6);
//		spiel1.laufen(1);
//		spiel1.setWuerfelZahl(8);
//		spiel1.laufen(1);
//		
//		spiel1.setWuerfelZahl(6);
//		spiel1.laufen(2);
//		spiel1.setWuerfelZahl(28);
//		spiel1.laufen(2);
//		spiel1.setWuerfelZahl(6);
//		spiel1.laufen(2);
//		spiel1.setWuerfelZahl(7);
//		spiel1.laufen(2);
//		
//		spiel1.setWuerfelZahl(6);
//		spiel1.laufen(3);
//		spiel1.setWuerfelZahl(28);
//		spiel1.laufen(3);
//		spiel1.setWuerfelZahl(6);
//		spiel1.laufen(3);
//		spiel1.setWuerfelZahl(4);
//		spiel1.laufen(3);
//		
//		spiel1.laufKi();
//		
//		spiel1.wuerfeln();
//		spiel1.laufen(spiel1.gibFigurKi());
		
		
		
		
////		
//////		
//			
//		
//		
//		
//		spiel1.setWuerfelZahl(6);	
//		spiel1.laufen(0);
//		spiel1.setWuerfelZahl(6);	
//		spiel1.laufen(0);
//		spiel1.setWuerfelZahl(4);	
//		spiel1.laufen(1);
//		spiel1.beenden();
//		
//		spiel1.setWuerfelZahl(6);	
//		spiel1.laufen(0);
//		spiel1.setWuerfelZahl(3);	
//		spiel1.laufen(0);
		
		
//		
//		iDatenzugriff csv = new DatenzugriffCSV();
//		csv.speichern("C:\\Users\\tanifor\\Desktop\\halo123.csv", spiel1);
//		csv.laden("C:\\Users\\tanifor\\Desktop\\halo123.csv");
		
		
//		iDatenzugriff pdf = new DatenzugriffPDF();
//		pdf.speichern("hi", spiel1);
		
//		System.out.println(spiel1.getBrett());
		
		
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