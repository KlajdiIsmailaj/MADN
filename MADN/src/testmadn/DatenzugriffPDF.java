package testmadn;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * die klasse datenzugriffPDF erzeugt pdf dateien und implementiert das interface idatenzugriff neu
 * @author Gruppe A2
 *
 */

public class DatenzugriffPDF implements iDatenzugriff,Serializable {
	private static final long serialVersionUID = 1L;

	Document document = null;
	PdfWriter writer = null;
	Font font = null;
	PdfPTable tab = null;
	/**
	 * das aktuelle Spiel wird als PDF Datei gespeichert
	 * die implemntierte interface methode zum speichern nur fuer pdf
	 */
	@Override
	public void speichern(String filename,Object spiel) {
		try {
			document = new Document(PageSize.A4);
			writer = PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
			document.open();
			Image image = Image.getInstance("spielbrett.png");
			Paragraph title = new Paragraph("Mensch Ärgere Dich Nicht\nSpielstand", new Font(Font.FontFamily.HELVETICA, 20)); 
			
			title.setAlignment(Element.ALIGN_CENTER);
			title.setSpacingAfter(10);
			
			image.scaleToFit(300,300);
			image.setAlignment(Element.ALIGN_CENTER);
			document.add(title);
//			document.add(new Paragraph("Spielbrettbelegung "  + " :"));
			
			image.setSpacingBefore(10);
			image.setWidthPercentage(100);
			
			document.add(image);
			
			image.setSpacingAfter(10);
			
			
			
			
//			PDFerzeugen(null, tableBrett1);
//			PdfPCell cellErsteZeile = new PdfPCell(Image.getInstance("madn4.png"), true);
//			cellErsteZeile.setHorizontalAlignment(Element.ALIGN_CENTER);
//			cellErsteZeile.setVerticalAlignment(Element.ALIGN_MIDDLE);
//			tab.addCell(cellErsteZeile);
//			document.add(tableBrett1);
//			document.newPage();
			
//			document.add(new Paragraph("Kati"));

		} catch (FileNotFoundException e) {
			document.close();
			System.err.println(filename + ".pdf konnte nicht erzeugt werden");
			
		} catch (DocumentException e) {
			document.close();
			System.err.println("Das Dokument wurde bereits geschlossen oder ist nicht offen");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//			catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		finally{
			document.close();
		}
	}

	/**
	 * implemntierte methode laden des interfaces idatenzugriff
	 * zum laden von pdf dateien
	 */
	@Override
	public Object laden(String filename) {
		throw new RuntimeException("PDF Dateien koennen nicht geladen werden!");
	}
	
//	private void PDFerzeugen(Spielbrett brett, PdfPTable tab){
//		
//		PdfPCell cellErsteZeile = new PdfPCell(Image.getInstance("madn4.png"), true);
//		cellErsteZeile.setHorizontalAlignment(Element.ALIGN_CENTER);
//		cellErsteZeile.setVerticalAlignment(Element.ALIGN_MIDDLE);
//		tab.addCell(cellErsteZeile);
//		
//	}


}

