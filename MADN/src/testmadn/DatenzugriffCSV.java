package testmadn;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.itextpdf.text.log.SysoLogger;


public class DatenzugriffCSV  implements iDatenzugriff,Serializable{
	private static final long serialVersionUID = 1L;
	
	
	
	private SpielBean s=new SpielBean();
	
	private BufferedWriter br;
	private FileWriter file;
	private BufferedReader rd;
	private FileReader reader;
	public static ByteArrayOutputStream baos = new ByteArrayOutputStream();
	
	
	
	
	
	@Override
	public Object laden(String filename){
		try{
			
			rd= new BufferedReader(new FileReader(filename));
			String line= null;
			
			while((line= rd.readLine() )!= null){ 
				if(line.startsWith("player")){	
					
			  		String[] F = line.split(";");
			  		String name = F[0];
			  		String farbe = F[1];
			  		String KI = F[2];
			  		
			  		if(KI.equals("null")&&(farbe!="null")&&(name.length()>2)){	
			    	  this.s.newSpieler(name.substring(8), farbe, KI);
			  		}
			       
			  		else if(KI.equals("agr")&&(farbe!="null")&&(name.length()>2)){
			  			this.s.newSpieler(name.substring(8), farbe, this.bestimmeKi(KI));
			  		}
			      
			  		else if(KI.equals("def")&&(farbe!="null")&&(name.length()>2)){
			  			this.s.newSpieler(name.substring(8), farbe, this.bestimmeKi(KI));
			  			line= rd.readLine();
			  		}
			  	   
				}
				
				else if((line.startsWith("S"))){
					
					String[] F = line.split(";");  
					String name = F[0];
					String farbe = F[1];
					String figur = F[2];
		        	
					//-----------Startfelder----------
					if(farbe.startsWith("R")&&(!farbe.equals("null"))){
						for(Spielfeld c:s.getBrett().getStartRot()){ 
							if(c.getId().equals(name)){
								if(!figur.equals("null")){
									Spielfigur x = new Spielfigur(this.bestimmeFarbeFigur(figur), this.idfigur(F[2]), c);
									c.setFigur(x);
									
									this.setFeldFigur(c,this.idfigur(F[2]),this.bestimmeFarbeFigur(figur) ,s);
	        						
	        						for(Spieler l :s.getSpielerlist()){
	        							if(l.getFarbe()==this.bestimmeFarbeFigur(figur)){
	        								l.getFigurlist().add(x);
	        								break;
	        							}
	        						}
								}
							}
						}	
					}
					
					else if((!farbe.equals("null"))&&(farbe.startsWith("B"))){
						for(Spielfeld c:s.getBrett().getStartBlau()){ 
	        				if(c.getId().equals(name)){
	        					if(!figur.equals("null")){
	        						Spielfigur x = new Spielfigur(this.bestimmeFarbeFigur(figur), this.idfigur(F[2]), c);
	        						c.setFigur(x);
	        						
	        						this.setFeldFigur(c,this.idfigur(F[2]),this.bestimmeFarbeFigur(figur) ,s);
	        						
	        						for(Spieler l :s.getSpielerlist()){
	        							if(l.getFarbe()==this.bestimmeFarbeFigur(figur)){
	        								l.getFigurlist().add(x);
	        								break;
	        							}
	        						}
	        					}
	        				}
	        			}
	        		}
					
					else if((!farbe.equals("null"))&&(farbe.startsWith("GR"))){
						for(Spielfeld c:s.getBrett().getStartGruen()){ 
	        				if(c.getId().equals(name)){
	        					if(!figur.equals("null")){
	        						Spielfigur x = new Spielfigur(this.bestimmeFarbeFigur(figur), this.idfigur(F[2]), c);
	        						c.setFigur(x);
	        						
	        						this.setFeldFigur(c,this.idfigur(F[2]),this.bestimmeFarbeFigur(figur) ,s);
	        						
	        						for(Spieler l :s.getSpielerlist()){
	        							if(l.getFarbe()==this.bestimmeFarbeFigur(figur)){
	        								l.getFigurlist().add(x);
	        								break;
	        							}
	        						}
	        					}
	        				}
						}
					}
					
					else if(farbe.startsWith("GE")&&(!farbe.equals("null"))){
						for(Spielfeld c:s.getBrett().getStartGelb()){
	       					if(c.getId().equals(name)){
	       						if(!figur.equals("null")){
	       							Spielfigur x = new Spielfigur(this.bestimmeFarbeFigur(figur), this.idfigur(F[2]), c);
						        	c.setFigur(x);
						        	
						        	this.setFeldFigur(c,this.idfigur(F[2]),this.bestimmeFarbeFigur(figur) ,s);
	        						
	        						for(Spieler l :s.getSpielerlist()){
	        							if(l.getFarbe()==this.bestimmeFarbeFigur(figur)){
	        								l.getFigurlist().add(x);
	        								break;
	        							}
	        						}
			        			}
	       					}
	       				}
					}
				}
				else if(line.startsWith("E")){
					
					String[] F = line.split(";");  
					String name = F[0];
					String farbe = F[1];
					String figur = F[2];
					
					//-----------Endefelder-----------
					
					if(farbe.startsWith("R")&&(!farbe.equals("null"))){
						for(Spielfeld c:s.getBrett().getEndRot()){ 
							if(c.getId().equals(name)){
								if(!figur.equals("null")){
									Spielfigur x = new Spielfigur(this.bestimmeFarbeFigur(figur), this.idfigur(F[2]), c);
									c.setFigur(x);
									
									this.setFeldFigur(c,this.idfigur(F[2]),this.bestimmeFarbeFigur(figur) ,s);
	        						
	        						for(Spieler l :s.getSpielerlist()){
	        							if(l.getFarbe()==this.bestimmeFarbeFigur(figur)){
	        								l.getFigurlist().add(x);
	        								break;
	        							}
	        						}
								}
							}
						}	
					}
					
					else if((!farbe.equals("null"))&&(farbe.startsWith("B"))){
						for(Spielfeld c:s.getBrett().getEndBlau()){ 
	        				if(c.getId().equals(name)){
	        					if(!figur.equals("null")){
	        						Spielfigur x = new Spielfigur(this.bestimmeFarbeFigur(figur), this.idfigur(F[2]), c);
	        						c.setFigur(x);
	        						
	        						this.setFeldFigur(c,this.idfigur(F[2]),this.bestimmeFarbeFigur(figur) ,s);
	        						
	        						for(Spieler l :s.getSpielerlist()){
	        							if(l.getFarbe()==this.bestimmeFarbeFigur(figur)){
	        								l.getFigurlist().add(x);
	        								break;
	        							}
	        						}
	        					}
	        				}
	        			}
	        		}
					
					else if((!farbe.equals("null"))&&(farbe.startsWith("GR"))){
						for(Spielfeld c:s.getBrett().getEndGruen()){ 
	        				if(c.getId().equals(name)){
	        					if(!figur.equals("null")){
	        						Spielfigur x = new Spielfigur(this.bestimmeFarbeFigur(figur), this.idfigur(F[2]), c);
	        						c.setFigur(x);
	        						
	        						this.setFeldFigur(c,this.idfigur(F[2]),this.bestimmeFarbeFigur(figur) ,s);
	        						
	        						for(Spieler l :s.getSpielerlist()){
	        							if(l.getFarbe()==this.bestimmeFarbeFigur(figur)){
	        								l.getFigurlist().add(x);
	        								break;
	        							}
	        						}
	        					}
	        				}
						}
					}
					
					else if(farbe.startsWith("GE")&&(!farbe.equals("null"))){
						for(Spielfeld c:s.getBrett().getEndGelb()){
	       					if(c.getId().equals(name)){
	       						if(!figur.equals("null")){
	       							Spielfigur x = new Spielfigur(this.bestimmeFarbeFigur(figur), this.idfigur(F[2]), c);
						        	c.setFigur(x);
						        	
						        	this.setFeldFigur(c,this.idfigur(F[2]),this.bestimmeFarbeFigur(figur) ,s);
	        						
	        						for(Spieler l :s.getSpielerlist()){
	        							if(l.getFarbe()==this.bestimmeFarbeFigur(figur)){
	        								l.getFigurlist().add(x);
	        								break;
	        							}
	        						}
			        			}
	       					}
	       				}
					}
				}
				//------------Weg------------
				else{
					String[] F = line.split(";");  
					String name = F[0];
					String farbe = F[1];
					String figur = F[2];
					
					if(!figur.equals("null")){
						
						for(Spielfeld c : s.getBrett().getWeg()){
							if(c.getId().equals(name)){
								
								Spielfigur x = new Spielfigur(this.bestimmeFarbeFigur(figur), this.idfigur(F[2]), c);
					        	c.setFigur(x);
					        	
					        	this.setFeldFigur(c,this.idfigur(F[2]),this.bestimmeFarbeFigur(figur) , s);
					        	
					        	for(Spieler l :s.getSpielerlist()){
        							if(l.getFarbe()==this.bestimmeFarbeFigur(figur)){
        								l.getFigurlist().add(x);
        								break;
        							}
        						}
							}
						}
						
					}
				}
			}
		}	       
		catch(IOException e){
			try {
				throw new IOException ("not found ");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return s;
	    
	}

	public void setFeldFigur(Spielfeld x ,int idFigur,FarbEnum e,SpielBean s){
		if(e==FarbEnum.ROT){
			for(Spieler a : s.getSpielerlist()){
				if(a.getFarbe()==FarbEnum.ROT){
					a.getFigurlist().get(idFigur).setFeld(x);
					x.setFigur(a.getFigurlist().get(idFigur));
				}
			}
		}else if(e==FarbEnum.BLAU){
			for(Spieler a : s.getSpielerlist()){
				if(a.getFarbe()==FarbEnum.BLAU){
					a.getFigurlist().get(idFigur).setFeld(x);
					x.setFigur(a.getFigurlist().get(idFigur));
				}
			}
		}else if(e==FarbEnum.GELB){
			for(Spieler a : s.getSpielerlist()){
				if(a.getFarbe()==FarbEnum.GELB){
					a.getFigurlist().get(idFigur).setFeld(x);
					x.setFigur(a.getFigurlist().get(idFigur));
				}
			}
		}else if(e==FarbEnum.GRUEN){
			for(Spieler a : s.getSpielerlist()){
				if(a.getFarbe()==FarbEnum.GRUEN){
					a.getFigurlist().get(idFigur).setFeld(x);
					x.setFigur(a.getFigurlist().get(idFigur));
				}
			}
		}
	}
	
	
	public FarbEnum bestimmeFarbe(String farbe) {
		FarbEnum wert = null;
		switch(farbe) {
			case "ROT" : 
				wert=FarbEnum.ROT;
				return wert;
			case "BLAU" : 
				wert=FarbEnum.BLAU;
				return wert;
			case "GRUEN" : 
				wert=FarbEnum.GRUEN;
				return wert;
			case "GELB" : 
				wert=FarbEnum.GELB;
			default: wert =null;
		}
		return wert;
	}
	
	public FarbEnum bestimmeFarbeFigur(String figur) {
		FarbEnum wert = null;
		
		
		if(figur.startsWith("ro")){
			wert=FarbEnum.ROT;
		}else if(figur.startsWith("b")){
			wert=FarbEnum.BLAU;
		}else if(figur.startsWith("gr")){
			wert=FarbEnum.GRUEN;
		}else if(figur.startsWith("ge")){
			wert=FarbEnum.GELB;
		}
		
		return wert;
	}

	public String bestimmeKi(String ki){
		
		String a="";
		if(ki.equals("agr")){
			a="KI Aggressiv";
		}else if(ki.equals("def")){
			a="KI Defensiv";
		}
		
		return a;
	}
	
	@Override
	public void speichern(String filename,Object o){
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		
		
		this.s=(SpielBean)o;
		
		Spielfigur e = null;
		try{
			br= new BufferedWriter(new FileWriter(filename+".csv"));
		} catch (IOException e2){
			e2.printStackTrace();
		}
		try{
			for(int i =0;i<s.getSpielerlist().size();i++){
				KI ki = s.getSpielerlist().get(i).getKi();
				FarbEnum farbe = s.getSpielerlist().get(i).getFarbe();
				if((ki==null)){
					
					if(farbe==FarbEnum.ROT){
						br.write("player :"+ s.getSpielerlist().get(i).getName()+ " ;" + "Rot"+ ";" + "null");
						br.newLine();
					}
					if(farbe==FarbEnum.BLAU){
						br.write("player :"+s.getSpielerlist().get(i).getName()+ " ;" + "Blau"+ ";" + "null");
						br.newLine();
					}
					if(farbe==FarbEnum.GRUEN){
						br.write("player :"+s.getSpielerlist().get(i).getName()+ " ;" + "Grün"+ ";" + "null");
						br.newLine();
					}
					if(farbe==FarbEnum.GELB){
						br.write("player :"+s.getSpielerlist().get(i).getName()+ " ;" + "Gelb"+ ";" + "null");
						br.newLine();
					}
				}
				else if(ki instanceof KI_Aggressiv){
					if(farbe==FarbEnum.BLAU){
						br.write("player :"+s.getSpielerlist().get(i).getName()+ " ;" + "Blau"+";" + "agr");
						br.newLine();
					}
					
					if(farbe==FarbEnum.ROT){
						br.write("player :"+s.getSpielerlist().get(i).getName()+ " ;" + "Rot"+";" + "agr");
						br.newLine();
					}
					
					if(farbe==FarbEnum.GRUEN){
						br.write("player :"+s.getSpielerlist().get(i).getName()+ " ;" + "Grün"+";" + "agr");
						br.newLine();
					}
					
					if(farbe==FarbEnum.GELB){
						br.write("player :"+s.getSpielerlist().get(i).getName()+ " ;" + "Gelb"+";" + "agr");
						br.newLine();
					}
				}
				else if(ki instanceof KI_Defensiv){
					if(farbe==FarbEnum.ROT){
						br.write("player :"+s.getSpielerlist().get(i).getName()+ " ;" +"Rot"+";" + "def");
						br.newLine();
					}
					
					if(farbe==FarbEnum.GELB){
						br.write("player :"+s.getSpielerlist().get(i).getName()+ " ;" + "Gelb"+";" + "def");
						br.newLine();
					}
					
					if(farbe==FarbEnum.BLAU){
						br.write("player :"+s.getSpielerlist().get(i).getName()+ " ;" + "Blau"+";" + "def");
						br.newLine();
					}
					
					if(farbe==FarbEnum.GRUEN){
						br.write("player :"+s.getSpielerlist().get(i).getName()+ " ;" + "Grün"+";" + "def");
						br.newLine();
					}
				}
				
			}
			
			for(Spielfeld a :s.getBrett().getStartRot()){
				if((a.getFarbe()!= null)&&(a.getFigur()==null)){
					br.write(a.getId()+ ";"+"ROT"+ ";" +"null");
					br.newLine();
				} 
				
				else if(a.getFigur()!=null){
					br.write(a.getId()+ ";"+"ROT"+ ";" +"rot"+a.getFigur().getId());
					br.newLine();
				}
			}
			
			for(Spielfeld a :s.getBrett().getStartBlau()){
				if((a.getFarbe()!= null)&&(a.getFigur()==null)){
					br.write(a.getId()+ ";"+"BLAU"+ ";" +"null");
					br.newLine();
				}
				
				else if(a.getFigur()!=null){
					br.write(a.getId()+ ";"+"BLAU"+ ";" +"blau"+a.getFigur().getId());
					br.newLine();
				}
			}
			
			for(Spielfeld a :s.getBrett().getStartGruen()){
				if((a.getFarbe()!= null)&&(a.getFigur()==null)){
					br.write(a.getId()+ ";"+"GRUEN"+ ";" +"null");
					br.newLine();
				}
				
				else if(a.getFigur()!=null){
					br.write(a.getId()+ ";"+"GRUEN"+ ";" + "gruen"+a.getFigur().getId());
					br.newLine();
				}
			}
			
			for(Spielfeld a :s.getBrett().getStartGelb()){
				if((a.getFarbe()!= null)&&(a.getFigur()==null)){
					br.write(a.getId()+ ";"+"GELB"+ ";" +"null");
					br.newLine();
				}
				
				else if(a.getFigur()!=null){
					br.write(a.getId()+ ";"+"GELB"+ ";" +"gelb"+a.getFigur().getId());
					br.newLine();
				}
			}
			for(Spielfeld a :s.getBrett().getWeg()){
				if((a.getFigur()==null)){
					br.write(a.getId()+ ";"+"null"+ ";" +"null");
					br.newLine();
				}
				
				else if(  a.getFigur()!=null){
					
					if(a.getFigur().getFarbe()==FarbEnum.ROT){
						br.write(a.getId()+ ";"+"null"+ ";"+"rot"+a.getFigur().getId());
			    	   	br.newLine();
					}
			       
					else if( a.getFigur().getFarbe()==FarbEnum.BLAU){
						br.write(a.getId()+ ";"+"null"+ ";"+"blau"+a.getFigur().getId());
				   		br.newLine();
					}
					
					else if( a.getFigur().getFarbe()==FarbEnum.GRUEN){
						br.write(a.getId()+ ";"+"null"+ ";"+"gruen"+a.getFigur().getId());
						br.newLine();
					}
					
					else if( a.getFigur().getFarbe()==FarbEnum.GELB){
						br.write(a.getId()+ ";"+"null"+ ";"+"gelb"+a.getFigur().getId());
					}
				}
			}
			
			for(Spielfeld a :s.getBrett().getEndRot()){
				if((a.getFarbe()!= null)&&(a.getFigur()==null)){
					br.write(a.getId()+ ";"+"ROT"+ ";" +"null");
					br.newLine();
				}
				
				else if	((a.getFigur()!=null)&&(a.getFigur()!=null)){
					br.write(a.getId()+ ";"+"ROT"+ ";" +"rot"+a.getFigur().getId());
					br.newLine();
				}
			}
			
			for(Spielfeld a :s.getBrett().getEndBlau()){
				if((a.getFarbe()!= null)&&(a.getFigur()==null)){
					br.write(a.getId()+ ";"+"BLAU"+ ";" +"null");
					br.newLine();
				}
				
				else if	((a.getFigur()!=null)&&(a.getFigur()!=null)){
					br.write(a.getId()+ ";"+"BLAU"+ ";" + "rot"+a.getFigur().getId());
					br.newLine();
				}
			}
			
			for(Spielfeld a :s.getBrett().getEndGruen()){
				if((a.getFarbe()!= null)&&(a.getFigur()==null)){
					br.write(a.getId()+ ";"+"GRUEN"+ ";" +"null");
					br.newLine();
				}
				
				else if	((a.getFigur()!=null)&&(a.getFigur()!=null)){
					br.write(a.getId()+ ";"+"GRUEN"+ ";"+ "rot"+a.getFigur().getId());
					br.newLine();
				}
			}
			
			for(Spielfeld a :s.getBrett().getEndGelb()){
				if((a.getFarbe()!= null)&&(a.getFigur()==null)){
					br.write(a.getId()+ ";"+"GELB"+ ";" +"null");
					br.newLine();
				}
				
				else if	((a.getFigur()!=null)&&(a.getFigur()!=null)){
					br.write(a.getId()+ ";"+"GELB"+ ";" + "rot"+a.getFigur().getId());
					br.newLine();
				}
			}
			System.out.println("size: "+s.getSpielerlist().size());
			
			br.write("unser Spieler am zug heisst :"+s.getSpielerAmZug().getName()+";"+s.getSpielerAmZug().getFarbe()+";"+ s.getSpielerAmZug().getLetzterWurf());
			System.out.println("SPIEL CSV WURDE ERFOLGREICH GESPEICHERT !!!");
		}
		catch(IOException e1){
			e1.printStackTrace();
		}
		finally{
			try{
				br.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		System.out.flush();
		System.setOut(old);
		
	}


	public int idfigur(String s	){
		int wert= 1;
		switch(s){
		case "rot0":
			wert= 0;
			return wert;
		case "rot1":
			wert= 1;
			return wert;
		case "rot2":
			wert= 2;
			return wert;
		case "rot3":
			wert= 3;
			return wert;
		case "blau0":
			wert= 0;
			return wert;
		case "blau1":
			wert= 1;
			return wert;
		case "blau2":
			wert= 2;
			return wert;
		case "blau3":
			wert= 3;
			return wert;
		case "gelb0":
			wert= 0;
			return wert;
		case "gelb1":
			wert= 1;
			return wert;
		case "gelb2":
			wert= 2;
			return wert;
		case "gelb3":
			wert= 3;
			return wert;
		case "gruen0":
			wert= 0;
			return wert;
		case "gruen1":
			wert= 1;
			return wert;
		case "gruen2":
			wert= 2;
			return wert;
		case "gruenb3":
			wert= 3;
			return wert;
		}
		return wert;
		
	}



	
	

	
	
	

	

}
