package testmadn;



import java.io.Serializable;
import java.util.*;


/**
 * Ein Spielbrett wird erzeugt
 * @author Gruppe A2
 *
 */
public class Spielbrett implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ArrayList <Spielfeld> weg;
	
	private ArrayList <Spielfeld> startRot;
	private ArrayList <Spielfeld> startBlau;
	private ArrayList <Spielfeld> startGruen;
	private ArrayList <Spielfeld> startGelb;
	
	private ArrayList <Spielfeld> endRot;
	private ArrayList <Spielfeld> endBlau;
	private ArrayList <Spielfeld> endGruen;
	private ArrayList <Spielfeld> endGelb;
	
	private Spielfeld feld;


	/**
     * Der Konstruktor der Klasse Spielbrett
     * Es werden ArrayListen erzeugt
     * Es werden die Start/Endboxen erzeugt und der Weg
     */
	
	public Spielbrett(){
		
		weg=new ArrayList <Spielfeld>();
		normalweg();
		
		startRot=new ArrayList <Spielfeld>();
		startRot();
		startBlau=new ArrayList <Spielfeld>();
		startBlau();
		startGruen=new ArrayList <Spielfeld>();
		startGruen();
		startGelb=new ArrayList <Spielfeld>();
		startGelb();
		
		endRot=new ArrayList <Spielfeld>();
		endRot();
		endBlau=new ArrayList <Spielfeld>();
		endBlau();
		endGruen=new ArrayList <Spielfeld>();
		endGruen();
		endGelb=new ArrayList <Spielfeld>();
		endGelb();
	}
	
	/**
	 * der Hauptweg des Spiels mit seinen 40 Spielfeldern wird erzeugt
	 */
	public void normalweg(){
	   	for(int i=1; i<=40; i++){
	   		String id = ""+i;
	   	 	weg.add(new Spielfeld(null, id));
	   	}
	}
	
	/**
	  * Verkettete Liste fuer die 4 Spielfigurenfelder der roten Startbox wird erzeugt
	  */
	//-------------STARTBOX LISTE------------------
	public void startRot(){
	   	for(int i=1; i<=4; i++){
	   		String id = "S"+i ;
	   	 	startRot.add(new Spielfeld(FarbEnum.ROT, id));
	   	}
	}
	
	 /**
	  * Verkettete Liste fuer die 4 Spielfigurenfelder der blauen Startbox wird erzeugt
	  */
	public void startBlau(){
	   	for(int i=1; i<=4; i++){
	   		String id = "S"+i;
	   		startBlau.add(new Spielfeld(FarbEnum.BLAU, id));
	   	}
	}
	
	/**
	  * Verkettete Liste fuer die 4 Spielfigurenfelder der gruenen Startbox wird erzeugt
	  */
	public void startGruen(){
	   	for(int i=1; i<=4; i++){
	   		String id = "S"+i;
	   		startGruen.add(new Spielfeld(FarbEnum.GRUEN, id));	 
	   		}
	}
	
	/**
	  * Verkettete Liste fuer die 4 Spielfigurenfelder der gelben Startbox wird erzeugt
	  */
	public void startGelb(){
	   	for(int i=1; i<=4; i++){
	   		String id = "S"+i ;
	   		startGelb.add(new Spielfeld(FarbEnum.GELB, id));	 
	   		}
	}
	//-------------------------------------------
	
	
	/**
	  * Verkettete Liste fuer die 4 Spielfigurenfelder der roten Endbox wird erzeugt
	  */
	//-------------ENBOXEN LISTE------------------
	public void endRot(){
	   	for(int i=1; i<=4; i++){
	   		String id = "E"+i;
	   		endRot.add(new Spielfeld(FarbEnum.ROT, id));	  
	   	}
	}
	
	/**
	  * Verkettete Liste fuer die 4 Spielfigurenfelder der blauen Endbox wird erzeugt
	  */
	public void endBlau(){
	   	for(int i=1; i<=4; i++){
	   		String id = "E"+i;
	   		endBlau.add(new Spielfeld(FarbEnum.BLAU, id));	 
	   	}
	}
	
	/**
	  * Verkettete Liste fuer die 4 Spielfigurenfelder der gruenen Endbox wird erzeugt
	  */
	public void endGruen(){
	   	for(int i=1; i<=4; i++){
	   		String id = "E"+i;
	   		endGruen.add(new Spielfeld(FarbEnum.GRUEN, id));	 
	   	}
	}
	
	 /**
	  * Verkettete Liste fuer die 4 Spielfigurenfelder der gelben Endbox wird erzeugt
	  */
	public void endGelb(){
	   	for(int i=1; i<=4; i++){
	   		String id = "E"+i;
	   		endGelb.add(new Spielfeld(FarbEnum.GELB, id));	   
	   	}	
	   	
	}
	//-------------------------------------------
	
	

	/**
	 * der Hauptweg des Spiels wird zurueck gegeben 
	 * @return weg ist der Weg des ganzen Spiels
	 */
	public ArrayList<Spielfeld> getWeg() {
		return weg;
	}

	/**
	 * der Hauptweg des Spiels wird gesetzt
	 * @param weg ist der Weg des ganzen Spiels
	 */
	public void setWeg(ArrayList<Spielfeld> weg) {
		this.weg = weg;
	}

	/**
	 * die Startbox von rot wird zurueck gegeben
	 * @return startRot ist die Startbox von rot
	 */
	public ArrayList<Spielfeld> getStartRot() {
		return startRot;
	}

	/**
	 * die Startbox von rot wird gesetzt
	 * @param startRot ist die Startbox von rot
	 */
	public void setStartRot(ArrayList<Spielfeld> startRot) {
		this.startRot = startRot;
	}

	/**
	 * die Startbox von Blau wird zurueck gegeben
	 * @return startBlau ist die Startbox von Blau
	 */
	public ArrayList<Spielfeld> getStartBlau() {
		return startBlau;
	}

	/**
	 * die Startbox von Blau wird gesetzt
	 * @param startBlau ist die Startbox von Blau
	 */
	public void setStartBlau(ArrayList<Spielfeld> startBlau) {
		this.startBlau = startBlau;
	}

	/**
	 * die Startbox von gruen wird zurueck gegeben
	 * @return startGruen ist die Startbox von gruen
	 */
	public ArrayList<Spielfeld> getStartGruen() {
		return startGruen;
	}

	/**
	 * die Startbox von gruen wird gesetzt
	 * @param startGruen ist die Startbox von gruen
	 */
	public void setStartGruen(ArrayList<Spielfeld> startGruen) {
		this.startGruen = startGruen;
	}

	/**
	 * die Startbox von gelb wird zurueck gegeben
	 * @return startGelb ist die Startbox von gelb
	 */
	public ArrayList<Spielfeld> getStartGelb() {
		return startGelb;
	}

	/**
	 * die Startbox von gelb wird gesetzt
	 * @param startGelb ist die Startbox von gelb
	 */
	public void setStartGelb(ArrayList<Spielfeld> startGelb) {
		this.startGelb = startGelb;
	}

	/**
	 * die Endbox von rot wird zurueck gegeben
	 * @return endRot ist die Endbox von rot
	 */
	public ArrayList<Spielfeld> getEndRot() {
		return endRot;
	}

	/**
	 * die Endbox von rot wird gesetzt
	 * @param endRot ist die Endbox von rot
	 */
	public void setEndRot(ArrayList<Spielfeld> endRot) {
		this.endRot = endRot;
	}

	/**
	 * die Endbox von blau wird zurueck gegeben
	 * @return endBlau ist die Endbox von blau
	 */
	public ArrayList<Spielfeld> getEndBlau() {
		return endBlau;
	}

	/**
	 * die Endbox von blau wird gesetzt
	 * @param endBlau ist die Endbox von blau
	 */
	public void setEndBlau(ArrayList<Spielfeld> endBlau) {
		this.endBlau = endBlau;
	}

	/**
	 * die Endbox von gruen wird zurueck gegeben
	 * @return endGruen ist die Endbox von gruen
	 */
	public ArrayList<Spielfeld> getEndGruen() {
		return endGruen;
	}

	/**
	 * die Endbox von gruen wird gesetzt
	 * @param endGruen ist die Endbox von gruen
	 */
	public void setEndGruen(ArrayList<Spielfeld> endGruen) {
		this.endGruen = endGruen;
	}

	/**
	 * die Endbox von gelb wird zurueck gegeben
	 * @return endGelb ist die Endbox von gelb
	 */
	public ArrayList<Spielfeld> getEndGelb() {
		return endGelb;
	}

	/**
	 * die Endbox von gelb wird gesetzt
	 * @param endGelb ist die Endbox von gelb
	 */
	public void setEndGelb(ArrayList<Spielfeld> endGelb) {
		this.endGelb = endGelb;
	}

	/**
	  * ein Spielfeld wird zurueck gegeben
	  * @return feld ist ein Spielfeld
	  */
	public Spielfeld getFeld() {
		return feld;
	}

	/**
	 * ein Spielfeld wird gesetzt
	 * @param feld ist ein Spielfeld
	 */
	public void setFeld(Spielfeld feld) {
		this.feld = feld;
	}
	

	
	
	
	@Override
	public String toString(){
		
		
		return ("Weg Liste: \n"+ weg + "\n"+"\n"+
		"Start_Rot Liste: \n"+ startRot + "\n"+"\n"+
		"Start_Blau Liste: \n"+ startBlau + "\n"+"\n"+
		"Start_Gruen Liste: \n"+ startGruen + "\n"+"\n"+
		"Start_Gelb Liste: \n"+ startGelb + "\n"+"\n"+
		"End_Rot Liste: \n"+ endRot + "\n"+"\n"+
		"End_Blau Liste: \n"+ endBlau + "\n"+"\n"+
		"End_Gruen Liste: \n"+ endGruen + "\n"+"\n"+
		"End_Gelb Liste: \n"+ endGelb );
	}

	


	
	
}
