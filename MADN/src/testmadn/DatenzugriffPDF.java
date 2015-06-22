package testmadn;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;


/**
 * die klasse datenzugriffPDF erzeugt pdf dateien und implementiert das interface idatenzugriff neu
 * @author Gruppe A2
 *
 */

public class DatenzugriffPDF implements iDatenzugriff,Serializable {
	private static final long serialVersionUID = 1L;

	Document document=null;
	PdfWriter writer=null;
	Font font=null;
	PdfPTable tab=null;

	
	String realPath="/Users/sevenvista/Documents/Informatik2/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MADNWeb/Bilder/";
	private ArrayList<String> StartRot;
	private ArrayList<String> StartBlau;
	private ArrayList<String> StartGruen;
	private ArrayList<String> StartGelb;
	private ArrayList<String> EndRot;
	private ArrayList<String> EndBlau;
	private ArrayList<String> EndGruen;
	private ArrayList<String> EndGelb;
	private ArrayList<String> Weg;
	

	
	
	/**
	 * das aktuelle Spiel wird als PDF Datei gespeichert
	 * die implemntierte interface methode zum speichern nur fuer pdf
	 */
	@Override
	public void speichern(String filename,Object spiel) {
		try {
			
			SpielBean s=(SpielBean)spiel;
			
			
			document = new Document(PageSize.A4);
			writer = PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
			document.open();
			Image image = Image.getInstance(realPath+"brett8.png");
			Paragraph title = new Paragraph("Mensch Ärgere Dich Nicht\nSpielstand", new Font(Font.FontFamily.HELVETICA, 20)); 
			
			title.setAlignment(Element.ALIGN_CENTER);
			title.setSpacingAfter(10);
			
			image.scaleToFit(510,510);
			image.setAlignment(Element.ALIGN_CENTER);
			document.add(title);
			
			image.setSpacingBefore(10);
			image.setWidthPercentage(100);
			
			document.add(image);
			image.setSpacingAfter(10);
			PdfContentByte over = writer.getDirectContent();
			over.saveState();
			
			//------Weg-----------
			Image feld1 = Image.getInstance(realPath+Weg(s).get(0));
			Image feld2 = Image.getInstance(realPath+Weg(s).get(1));
			Image feld3 = Image.getInstance(realPath+Weg(s).get(2));
			Image feld4 = Image.getInstance(realPath+Weg(s).get(3));
			Image feld5 = Image.getInstance(realPath+Weg(s).get(4));
			Image feld6 = Image.getInstance(realPath+Weg(s).get(5));
			Image feld7 = Image.getInstance(realPath+Weg(s).get(6));
			Image feld8 = Image.getInstance(realPath+Weg(s).get(7));
			Image feld9 = Image.getInstance(realPath+Weg(s).get(8));
			Image feld10 = Image.getInstance(realPath+Weg(s).get(9));
			Image feld11 = Image.getInstance(realPath+Weg(s).get(10));
			Image feld12 = Image.getInstance(realPath+Weg(s).get(11));
			Image feld13 = Image.getInstance(realPath+Weg(s).get(12));
			Image feld14 = Image.getInstance(realPath+Weg(s).get(13));
			Image feld15 = Image.getInstance(realPath+Weg(s).get(14));
			Image feld16 = Image.getInstance(realPath+Weg(s).get(15));
			Image feld17 = Image.getInstance(realPath+Weg(s).get(16));
			Image feld18 = Image.getInstance(realPath+Weg(s).get(17));
			Image feld19 = Image.getInstance(realPath+Weg(s).get(18));
			Image feld20 = Image.getInstance(realPath+Weg(s).get(19));
			Image feld21 = Image.getInstance(realPath+Weg(s).get(20));
			Image feld22 = Image.getInstance(realPath+Weg(s).get(21));
			Image feld23 = Image.getInstance(realPath+Weg(s).get(22));
			Image feld24 = Image.getInstance(realPath+Weg(s).get(23));
			Image feld25 = Image.getInstance(realPath+Weg(s).get(24));
			Image feld26 = Image.getInstance(realPath+Weg(s).get(25));
			Image feld27 = Image.getInstance(realPath+Weg(s).get(26));
			Image feld28 = Image.getInstance(realPath+Weg(s).get(27));
			Image feld29 = Image.getInstance(realPath+Weg(s).get(28));
			Image feld30 = Image.getInstance(realPath+Weg(s).get(29));
			Image feld31 = Image.getInstance(realPath+Weg(s).get(30));
			Image feld32 = Image.getInstance(realPath+Weg(s).get(31));
			Image feld33 = Image.getInstance(realPath+Weg(s).get(32));
			Image feld34 = Image.getInstance(realPath+Weg(s).get(33));
			Image feld35 = Image.getInstance(realPath+Weg(s).get(34));
			Image feld36 = Image.getInstance(realPath+Weg(s).get(35));
			Image feld37 = Image.getInstance(realPath+Weg(s).get(36));
			Image feld38 = Image.getInstance(realPath+Weg(s).get(37));
			Image feld39 = Image.getInstance(realPath+Weg(s).get(38));
			Image feld40 = Image.getInstance(realPath+Weg(s).get(39));
			
			//-------Startfelder--------
			Image s1rot = Image.getInstance(realPath+SR(s).get(0));
			Image s2rot = Image.getInstance(realPath+SR(s).get(1));
			Image s3rot = Image.getInstance(realPath+SR(s).get(2));
			Image s4rot = Image.getInstance(realPath+SR(s).get(3));
			
			Image s1blau = Image.getInstance(realPath+SB(s).get(0));
			Image s2blau = Image.getInstance(realPath+SB(s).get(1));
			Image s3blau = Image.getInstance(realPath+SB(s).get(2));
			Image s4blau = Image.getInstance(realPath+SB(s).get(3));
			
			Image s1gruen = Image.getInstance(realPath+SGr(s).get(0));
			Image s2gruen = Image.getInstance(realPath+SGr(s).get(1));
			Image s3gruen = Image.getInstance(realPath+SGr(s).get(2));
			Image s4gruen = Image.getInstance(realPath+SGr(s).get(3));
			
			Image s1gelb = Image.getInstance(realPath+SGe(s).get(0));
			Image s2gelb = Image.getInstance(realPath+SGe(s).get(1));
			Image s3gelb = Image.getInstance(realPath+SGe(s).get(2));
			Image s4gelb = Image.getInstance(realPath+SGe(s).get(3));
			
			//------Endfelder------
			Image e1rot = Image.getInstance(realPath+ER(s).get(0));
			Image e2rot = Image.getInstance(realPath+ER(s).get(1));
			Image e3rot = Image.getInstance(realPath+ER(s).get(2));
			Image e4rot = Image.getInstance(realPath+ER(s).get(3));
			
			Image e1blau = Image.getInstance(realPath+EB(s).get(0));
			Image e2blau = Image.getInstance(realPath+EB(s).get(1));
			Image e3blau = Image.getInstance(realPath+EB(s).get(2));
			Image e4blau = Image.getInstance(realPath+EB(s).get(3));
			
			Image e1gruen = Image.getInstance(realPath+EGr(s).get(0));
			Image e2gruen = Image.getInstance(realPath+EGr(s).get(1));
			Image e3gruen = Image.getInstance(realPath+EGr(s).get(2));
			Image e4gruen = Image.getInstance(realPath+EGr(s).get(3));
			
			Image e1gelb = Image.getInstance(realPath+EGe(s).get(0));
			Image e2gelb = Image.getInstance(realPath+EGe(s).get(1));
			Image e3gelb = Image.getInstance(realPath+EGe(s).get(2));
			Image e4gelb = Image.getInstance(realPath+EGe(s).get(3));	
			
			//-------Startfelder-----------
			s1rot.setAbsolutePosition(130, 660); // S1 ROT
			over.addImage(s1rot);
			s2rot.setAbsolutePosition(90, 660); // S2 ROT
			over.addImage(s2rot);
			s3rot.setAbsolutePosition(90, 620); // S3 ROT
			over.addImage(s3rot);
			s4rot.setAbsolutePosition(130, 620); // S4 ROT
			over.addImage(s4rot);
			
			s1blau.setAbsolutePosition(460, 660); // S1 BLAU
			over.addImage(s1blau);
			s2blau.setAbsolutePosition(425, 660); // S2 BLAU
			over.addImage(s2blau);
			s3blau.setAbsolutePosition(425, 620); // S3 BLAU
			over.addImage(s3blau);
			s4blau.setAbsolutePosition(460, 620); // S4 BLAU
			over.addImage(s4blau);
			
			s1gruen.setAbsolutePosition(460, 270); //S1 GRUEN
			over.addImage(s1gruen);
			s2gruen.setAbsolutePosition(425, 270); //S2 GRUEN
			over.addImage(s2gruen);
			s3gruen.setAbsolutePosition(425, 220); //S3 GRUEN
			over.addImage(s3gruen);
			s4gruen.setAbsolutePosition(460, 220); //S4 GRUEN
			over.addImage(s4gruen);
			
			s1gelb.setAbsolutePosition(130, 260); //S1 GELB
			over.addImage(s1gelb);
			s2gelb.setAbsolutePosition(90, 260); // S2 GELB
			over.addImage(s2gelb);
			s3gelb.setAbsolutePosition(90, 220); // S3 GELB
			over.addImage(s3gelb);
			s4gelb.setAbsolutePosition(130, 220); //S4 GELB
			over.addImage(s4gelb);
			
			//-------Endfelder-----------
			e1rot.setAbsolutePosition(130, 440); // E1 ROT
			over.addImage(e1rot);
			e2rot.setAbsolutePosition(165, 440); // E2 ROT
			over.addImage(e2rot);
			e3rot.setAbsolutePosition(200, 440); // E3 ROT
			over.addImage(e3rot);
			e4rot.setAbsolutePosition(240, 440); // E4 ROT
			over.addImage(e4rot);
			
			e1blau.setAbsolutePosition(275, 620); // E1 BLAU
			over.addImage(e1blau);
			e2blau.setAbsolutePosition(275, 570); // E2 BLAU
			over.addImage(e2blau);
			e3blau.setAbsolutePosition(275, 530); // E3 BLAU
			over.addImage(e3blau);
			e4blau.setAbsolutePosition(275, 490); // E4 BLAU
			over.addImage(e4blau);
			
			e1gruen.setAbsolutePosition(425, 440); // E1 GRUEN
			over.addImage(e1gruen);
			e2gruen.setAbsolutePosition(390, 445); // E2 GRUEN
			over.addImage(e2gruen);
			e3gruen.setAbsolutePosition(350, 445); // E3 GRUEN
			over.addImage(e3gruen);
			e4gruen.setAbsolutePosition(310, 445); // E4 GRUEN
			over.addImage(e4gruen);
			
			e1gelb.setAbsolutePosition(275, 270); // E1 GELB
			over.addImage(e1gelb);
			e2gelb.setAbsolutePosition(275, 310); // E2 GELB
			over.addImage(e2gelb);
			e3gelb.setAbsolutePosition(275, 355); // E3 GELB
			over.addImage(e3gelb);
			e4gelb.setAbsolutePosition(275, 400); // E4 GELB
			over.addImage(e4gelb);
			
			//-------Weg-----------
			feld1.setAbsolutePosition(90, 480);  // 1
			over.addImage(feld1);
			feld2.setAbsolutePosition(130, 480); // 2
			over.addImage(feld2);
			feld3.setAbsolutePosition(170, 480); // 3
			over.addImage(feld3);
			feld4.setAbsolutePosition(200, 490); // 4
			over.addImage(feld4);
			feld5.setAbsolutePosition(240, 485); // 5
			over.addImage(feld5);
			feld6.setAbsolutePosition(240, 530); // 6
			over.addImage(feld6);
			feld7.setAbsolutePosition(240, 570); // 7
			over.addImage(feld7);
			feld8.setAbsolutePosition(240, 615); // 8
			over.addImage(feld8);
			feld9.setAbsolutePosition(240, 660); // 9
			over.addImage(feld9);
			feld10.setAbsolutePosition(270, 660); // 10
			over.addImage(feld10);
			feld11.setAbsolutePosition(310, 660); // 11
			over.addImage(feld11);
			feld12.setAbsolutePosition(310, 620); // 12
			over.addImage(feld12);
			feld13.setAbsolutePosition(310, 575); // 13
			over.addImage(feld13);
			feld14.setAbsolutePosition(310, 530); // 14
			over.addImage(feld14);
			feld15.setAbsolutePosition(310, 490); // 15
			over.addImage(feld15);
			feld16.setAbsolutePosition(350, 490); // 16
			over.addImage(feld16);
			feld17.setAbsolutePosition(390, 490); // 17
			over.addImage(feld17);
			feld18.setAbsolutePosition(425, 490); // 18
			over.addImage(feld18);
			feld19.setAbsolutePosition(460, 490); // 19
			over.addImage(feld19);
			feld20.setAbsolutePosition(460, 445); // 20
			over.addImage(feld20);
			feld21.setAbsolutePosition(460, 400); // 21
			over.addImage(feld21);
			feld22.setAbsolutePosition(425, 400); // 22
			over.addImage(feld22);
			feld23.setAbsolutePosition(385, 400); // 23	
			over.addImage(feld23);
			feld24.setAbsolutePosition(350, 400); // 24
			over.addImage(feld24);
			feld25.setAbsolutePosition(310, 400); // 25
			over.addImage(feld25);
			feld26.setAbsolutePosition(310, 355); // 26
			over.addImage(feld26);
			feld27.setAbsolutePosition(310, 310); // 27
			over.addImage(feld27);
			feld28.setAbsolutePosition(310, 270); // 28
			over.addImage(feld28);
			feld29.setAbsolutePosition(310, 220); // 29
			over.addImage(feld29);
			feld30.setAbsolutePosition(275, 220); // 30
			over.addImage(feld30);
			feld31.setAbsolutePosition(240, 220); // 31
			over.addImage(feld31);
			feld32.setAbsolutePosition(240, 265); // 32
			over.addImage(feld32);
			feld33.setAbsolutePosition(240, 310); // 33
			over.addImage(feld33);
			feld34.setAbsolutePosition(240, 350); // 34
			over.addImage(feld34);
			feld35.setAbsolutePosition(240, 400); // 35
			over.addImage(feld35);
			feld36.setAbsolutePosition(200, 400); // 36
			over.addImage(feld36);
			feld37.setAbsolutePosition(160, 400); // 37
			over.addImage(feld37);
			feld38.setAbsolutePosition(125, 400); // 38
			over.addImage(feld38);
			feld39.setAbsolutePosition(90, 400); // 39
			over.addImage(feld39);
			feld40.setAbsolutePosition(90, 440); // 40
			over.addImage(feld40);
			
			
			
			
			over.restoreState();
			
		} catch (FileNotFoundException e) {
			document.close();
			System.err.println(filename + ".pdf konnte nicht erzeugt werden");
			
		} catch (DocumentException e) {
			document.close();
			System.err.println("Das Dokument wurde bereits geschlossen oder ist nicht offen");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			document.close();
		}
	}
	
	public ArrayList<String> SR(SpielBean s){
		StartRot=new ArrayList<String>(); 
		
		for(Spielfeld feld:s.getBrett().getStartRot()){
			if(feld.istFeldBelegt()==true){
				if(feld.getFigur().getFarbe()==FarbEnum.ROT&&feld.getFigur().getId()==0){
					StartRot.add("rot1.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.ROT&&feld.getFigur().getId()==1){
					StartRot.add("rot2.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.ROT&&feld.getFigur().getId()==2){
					StartRot.add("rot3.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.ROT&&feld.getFigur().getId()==3){
					StartRot.add("rot4.png");
				}
			}else{
				StartRot.add("leereFigur.png");
			}
		}
		return StartRot;
	}
	
	public ArrayList<String> SB(SpielBean s){
		StartBlau=new ArrayList<String>(); 
		
		for(Spielfeld feld:s.getBrett().getStartBlau()){
			if(feld.istFeldBelegt()==true){
				if(feld.getFigur().getFarbe()==FarbEnum.BLAU&&feld.getFigur().getId()==0){
					StartBlau.add("blau1.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.BLAU&&feld.getFigur().getId()==1){
					StartBlau.add("blau2.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.BLAU&&feld.getFigur().getId()==2){
					StartBlau.add("blau3.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.BLAU&&feld.getFigur().getId()==3){
					StartBlau.add("blau4.png");
				}
			}else{
				StartBlau.add("leereFigur.png");
			}
		}
		return StartBlau;
	}
	
	public ArrayList<String> SGr(SpielBean s){
		StartGruen=new ArrayList<String>(); 
		
		for(Spielfeld feld:s.getBrett().getStartGruen()){
			if(feld.istFeldBelegt()==true){
				if(feld.getFigur().getFarbe()==FarbEnum.GRUEN&&feld.getFigur().getId()==0){
					StartGruen.add("gruen1.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.GRUEN&&feld.getFigur().getId()==1){
					StartGruen.add("gruen2.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.GRUEN&&feld.getFigur().getId()==2){
					StartGruen.add("gruen3.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.GRUEN&&feld.getFigur().getId()==3){
					StartGruen.add("gruen4.png");
				}
			}else{
				StartGruen.add("leereFigur.png");
			}
		}
		return 	StartGruen;
	}
	
	public ArrayList<String> SGe(SpielBean s){
		StartGelb=new ArrayList<String>(); 
		
		for(Spielfeld feld:s.getBrett().getStartGelb()){
			if(feld.istFeldBelegt()==true){
				if(feld.getFigur().getFarbe()==FarbEnum.GELB&&feld.getFigur().getId()==0){
					StartGelb.add("gelb1.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.GELB&&feld.getFigur().getId()==1){
					StartGelb.add("gelb2.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.GELB&&feld.getFigur().getId()==2){
					StartGelb.add("gelb3.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.GELB&&feld.getFigur().getId()==3){
					StartGelb.add("gelb4.png");
				}
			}else{
				StartGelb.add("leereFigur.png");
			}
		}
		return 	StartGelb;
	}
	
	public ArrayList<String> ER(SpielBean s){
		EndRot=new ArrayList<String>(); 
		
		for(Spielfeld feld:s.getBrett().getEndRot()){
			if(feld.istFeldBelegt()==true){
				if(feld.getFigur().getFarbe()==FarbEnum.ROT&&feld.getFigur().getId()==0){
					EndRot.add("rot1.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.ROT&&feld.getFigur().getId()==1){
					EndRot.add("rot2.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.ROT&&feld.getFigur().getId()==2){
					EndRot.add("rot3.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.ROT&&feld.getFigur().getId()==3){
					EndRot.add("rot4.png");
				}
			}else{
				EndRot.add("leereFigur.png");
			}
		}
		return 	EndRot;
	}
	
	public ArrayList<String> EB(SpielBean s){
		EndBlau=new ArrayList<String>(); 
		
		for(Spielfeld feld:s.getBrett().getEndBlau()){
			if(feld.istFeldBelegt()==true){
				if(feld.getFigur().getFarbe()==FarbEnum.BLAU&&feld.getFigur().getId()==0){
					EndBlau.add("blau1.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.BLAU&&feld.getFigur().getId()==1){
					EndBlau.add("blau2.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.BLAU&&feld.getFigur().getId()==2){
					EndBlau.add("blau3.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.BLAU&&feld.getFigur().getId()==3){
					EndBlau.add("blau4.png");
				}
			}else{
				EndBlau.add("leereFigur.png");
			}
		}
		return 	EndBlau;
	}
	
	public ArrayList<String> EGr(SpielBean s){
		EndGruen=new ArrayList<String>(); 
		
		for(Spielfeld feld:s.getBrett().getEndGruen()){
			if(feld.istFeldBelegt()==true){
				if(feld.getFigur().getFarbe()==FarbEnum.GRUEN&&feld.getFigur().getId()==0){
					EndGruen.add("gruen1.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.GRUEN&&feld.getFigur().getId()==1){
					EndGruen.add("gruen2.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.GRUEN&&feld.getFigur().getId()==2){
					EndGruen.add("gruen3.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.GRUEN&&feld.getFigur().getId()==3){
					EndGruen.add("gruen4.png");
				}
			}else{
				EndGruen.add("leereFigur.png");
			}
		}
		return 	EndGruen;
	}
	
	public ArrayList<String> EGe(SpielBean s){
		EndGelb=new ArrayList<String>(); 
		
		for(Spielfeld feld:s.getBrett().getEndGelb()){
			if(feld.istFeldBelegt()==true){
				if(feld.getFigur().getFarbe()==FarbEnum.GELB&&feld.getFigur().getId()==0){
					EndGelb.add("gelb1.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.GELB&&feld.getFigur().getId()==1){
					EndGelb.add("gelb2.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.GELB&&feld.getFigur().getId()==2){
					EndGelb.add("gelb3.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.GELB&&feld.getFigur().getId()==3){
					EndGelb.add("gelb4.png");
				}
			}else{
				EndGelb.add("leereFigur.png");
			}
		}
		return 	EndGelb;
	}
	
	public ArrayList<String> Weg(SpielBean s){
		
		Weg=new ArrayList<String>(); 
		
		for(Spielfeld feld:s.getBrett().getWeg()){
			if(feld.istFeldBelegt()==true){
				//--------Rote Figur----
				if(feld.getFigur().getFarbe()==FarbEnum.ROT&&feld.getFigur().getId()==0){
					Weg.add("rot1.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.ROT&&feld.getFigur().getId()==1){
					Weg.add("rot2.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.ROT&&feld.getFigur().getId()==2){
					Weg.add("rot3.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.ROT&&feld.getFigur().getId()==3){
					Weg.add("rot4.png");
				}
				//--------Blaue Figur----
				if(feld.getFigur().getFarbe()==FarbEnum.BLAU&&feld.getFigur().getId()==0){
					Weg.add("blau1.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.BLAU&&feld.getFigur().getId()==1){
					Weg.add("blau2.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.BLAU&&feld.getFigur().getId()==2){
					Weg.add("blau3.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.BLAU&&feld.getFigur().getId()==3){
					Weg.add("blau4.png");
				}
				//--------Gruene Figur----
				if(feld.getFigur().getFarbe()==FarbEnum.GRUEN&&feld.getFigur().getId()==0){
					Weg.add("gruen1.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.GRUEN&&feld.getFigur().getId()==1){
					Weg.add("gruen2.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.GRUEN&&feld.getFigur().getId()==2){
					Weg.add("gruen3.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.GRUEN&&feld.getFigur().getId()==3){
					Weg.add("gruen4.png");
				}
				//--------Gelbe Figur----
				if(feld.getFigur().getFarbe()==FarbEnum.GELB&&feld.getFigur().getId()==0){
					Weg.add("gelb1.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.GELB&&feld.getFigur().getId()==1){
					Weg.add("gelb2.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.GELB&&feld.getFigur().getId()==2){
					Weg.add("gelb3.png");
				}
				if(feld.getFigur().getFarbe()==FarbEnum.GELB&&feld.getFigur().getId()==3){
					Weg.add("gleb4.png");
				}
				
			}else{
				Weg.add("leereFigur.png");
			}
			
		}
		
		return Weg;
	}
	

	/**
	 * implemntierte methode laden des interfaces idatenzugriff
	 * zum laden von pdf dateien
	 */
	@Override
	public Object laden(String filename) {
		throw new RuntimeException("PDF Dateien koennen nicht geladen werden!");
	}
	



}

