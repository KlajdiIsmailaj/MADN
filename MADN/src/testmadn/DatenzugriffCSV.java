package testmadn;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


public class DatenzugriffCSV  implements iDatenzugriff,Serializable{
	private static final long serialVersionUID = 1L;
	
	
	
	private SpielBean s;
	
	private BufferedWriter br;
	private FileWriter file;
	private BufferedReader rd;
	private FileReader reader;
	
	
	
	public DatenzugriffCSV(SpielBean spiel){
		this.s=spiel;
	}
	
	
	
	@Override
	public Object laden(String filename){
		try
		{
			rd= new BufferedReader(new FileReader(filename));
			ArrayList<Spieler>splielerlist2=new ArrayList<Spieler>();
			String line= null ;
			SpielBean s1 = new SpielBean();
			while((line= rd.readLine() )!= null)
			 { 
				if(line.startsWith("spieler"))	
			  	{
			  		String[] F = line.split(";");
			  		String name = F[0];
			  		String farbe = F[1];
			  		String KI = F[2];
			  		if(KI.equals("null")&&(farbe!="null")&&(name.length()>2)) 
			  		{		
			    	  Spieler f = new Spieler(F[0],bestimmeFarbe(F[1]),null);
			    	  splielerlist2.add(f);
			    	  this.s.nimmtTeil(f);
			  		}
			       else if(KI.equals("agr")&&(farbe!=null)&&(name.length()>2))
			       {
			    	   Spieler f= new Spieler(F[0],bestimmeFarbe(F[1]), new KI_Aggressiv(s1));
			    	   splielerlist2.add(f);
			    	   this.s.nimmtTeil(f);
			       }
			       else if(KI.equals("def")&&(farbe!=null)&&(name.length()>2))
			       {
			    	  Spieler f= new Spieler(F[0],bestimmeFarbe(F[1]),new KI_Defensiv(s1));
			    	  splielerlist2.add(f);
			    	  this.s.nimmtTeil(f); 
			    	  line= rd.readLine();
			       }
			  	}
			  	else
			  	{
			         do
			         {
			        	 line= rd.readLine();
			        	 String[] F = line.split(";");  
			        	 String name = F[0];
			        	 String farbe = F[1];
			        	 String figur = F[2];	
//			        	 (name.length()<=2)&&
			        	 
			        	 if ((farbe!="null")&&(line.startsWith("S")))
			        	 {
			        		if(farbe.startsWith("R"))
			        		 line= rd.readLine();
			        		 if(F[2]!="null"){
			        			 
			        		 }
			        		 
			        		 s1.getBrett().setStartRot(s.getBrett().getStartRot());
			        		 for(Spielfeld c:s.getBrett().getStartRot())
			        			 
			        		 {
			        			
			        			 if(c.istFeldBelegt()){
			        			System.out.println(String.valueOf(c.getFigur().getId())); 
			        			// F[0]=c.getId();F[1]=c.getFarbe().toString() ;F[2]=String.valueOf(c.getFigur().getFigurId());
			        			 //line= rd.readLine();
			        			// c.setFigur((F[2].);
			        			
			        			//if(line.substring(3).startsWith(figur, 1))
			        			
			        			Spielfigur x = new Spielfigur(FarbEnum.ROT, 1, null);
			        			c.setFigur(x);
			        		 }
			        		 }
			        		 s1.getBrett().setStartBlau(s.getBrett().getStartBlau());
			        		 for(Spielfeld c:s1.getBrett().getStartBlau())
			        		 {
			        			 if(c.istFeldBelegt()){
			        			 F[0]=c.getId();F[1]=c.getFarbe().toString() ;F[2]=c.getFigur().toString();
			        		 }
			        		 }
			        		 s1.getBrett().setStartGruen(s.getBrett().getStartGruen());
			        		 for(Spielfeld c:s1.getBrett().getStartGruen())
			        		 {
			        			 if(c.istFeldBelegt()){
			        			 F[0]=c.getId();F[1]=c.getFarbe().toString() ;F[2]=c.getFigur().toString();
			        		 }
			        		 }
			        		 s1.getBrett().setStartGelb(s.getBrett().getStartGelb());
			        		 for(Spielfeld c:s1.getBrett().getStartGelb())
			        		 {
			        			 if(c.istFeldBelegt()){
			    			 	F[0]=c.getId();F[1]=c.getFarbe().toString() ;F[2]=c.getFigur().toString();
			        		 }
			        		 }
			        		 line= rd.readLine();
			        		 }
			        	 	else
			        	 	{
//			        	 		s1.getBrett().setWeg(s.getBrett().getWeg()); 
//			        	 		for(Spielfeld c:s1.getBrett().getWeg())
//			        	 		{
			        	 			line= rd.readLine();
			        	 		//}
			        	 	} 
			        	 }while((line= rd.readLine() )!= null);
			         } 
			  	}	
		    	s1.setSpielerAmZug(s.getSpielerAmZug());
		    	//s1.setBrett(s.getBrett());
		    	//System.out.println(s1.getBrett());	
    	}
		catch(IOException e)
		{
			try {
				throw new IOException ("not found ");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return filename;
		
	    
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
	public String setFarbe(FarbEnum wert){
		
		
		String farb = null;
		switch(wert){
		case ROT:
			farb = "ROT";
			return farb;
		case BLAU:
			farb = "ROT";
			return farb;
		case GELB:
			farb = "ROT";
			return farb;
		case GRUEN:
			farb = "ROT";
			return farb;
		}
		return farb;
		
	}


	 
		
		
	
	@Override
	public void speichern(String filename,Object o){
		Spielfigur e = null;
		try {
			br= new BufferedWriter(new FileWriter(filename+".csv"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try
		{
			for(int i =0;i<s.getSpielerlist().size();i++)
			{
				KI ki = s.getSpielerlist().get(i).getKi();
				FarbEnum farbe = s.getSpielerlist().get(i).getFarbe();
				if((ki==null))
				{
					if(farbe==FarbEnum.ROT)
					{
						br.write("spieler :"+ s.getSpielerlist().get(i).getName()+ " ;" + "ROT"+ ";" + "null");
						br.newLine();
					}
					if(farbe==FarbEnum.BLAU)
					{
						br.write("spieler :"+s.getSpielerlist().get(i).getName()+ " ;" + "BLAU"+ ";" + "null");
						br.newLine();
					}
					if(farbe==FarbEnum.GRUEN)
					{
						br.write("spieler :"+s.getSpielerlist().get(i).getName()+ " ;" + "GRUEN"+ ";" + "null");
						br.newLine();
					}
					if(farbe==FarbEnum.GELB)
					{
						br.write("spieler :"+s.getSpielerlist().get(i).getName()+ " ;" + "GELB"+ ";" + "null");
						br.newLine();
					}
				}
				else if(ki instanceof KI_Aggressiv)
				{
					if(farbe==FarbEnum.BLAU)
					{
					br.write("spieler :"+s.getSpielerlist().get(i).getName()+ " ;" + "BLAU"+";" + "agr");
					br.newLine();
					}if(farbe==FarbEnum.ROT)
					{
						br.write(s.getSpielerlist().get(i).getName()+ " ;" + "ROT"+";" + "agr");
						br.newLine();
					}if(farbe==FarbEnum.GRUEN)
					{
						br.write("spieler :"+s.getSpielerlist().get(i).getName()+ " ;" + "GRUEN"+";" + "agr");
						br.newLine();
					}if(farbe==FarbEnum.GELB)
					{
						br.write("spieler :"+s.getSpielerlist().get(i).getName()+ " ;" + "GELB"+";" + "agr");
						br.newLine();
					}
				}
				else if(ki instanceof KI_Defensiv)
				{
					if(farbe==FarbEnum.ROT)
					{
					br.write("spieler :"+s.getSpielerlist().get(i).getName()+ " ;" +"ROT"+";" + "def");
					br.newLine();
					
				}
					if(farbe==FarbEnum.GELB)
					{
						br.write("spieler :"+s.getSpielerlist().get(i).getName()+ " ;" + "GELB"+";" + "def");
						br.newLine();
					}
					if(farbe==FarbEnum.BLAU)
					{
						br.write("spieler :"+s.getSpielerlist().get(i).getName()+ " ;" + "BLAU"+";" + "def");
						br.newLine();
					}
					if(farbe==FarbEnum.GRUEN)
					{
						br.write("spieler :"+s.getSpielerlist().get(i).getName()+ " ;" + "GRUEN"+";" + "def");
						br.newLine();
					}
				}
				//br.write("spieler :"+s.getSpielerlist().get(i).getFigurlist().get(i).getFigurId());
			}
			for(Spielfeld a :s.getBrett().getStartRot())
			{
				if((a.getFarbe()!= null)&&(a.getFigur()==null)) 
				{
					br.write(a.getId()+ ";"+"ROT"+ ";" +"null");
					br.newLine();
				} 
				else if(a.getFigur()!=null){
			    System.out.println(a.getFigur());
				br.write(a.getId()+ ";"+"ROT"+ ";" +"rot"+a.getFigur().getId());
				br.newLine();
				}
			}
			
		
			for(Spielfeld a :s.getBrett().getStartBlau())
			{
				if((a.getFarbe()== null)&&(a.getFigur()==null))
				{
					br.write(a.getId()+ ";"+"BLAU"+ ";" +"null");
					br.newLine();
				}else if(a.getFigur()!=null){
					System.out.println(a.getFigur());
				br.write(a.getId()+ ";"+"BLAU"+ ";" +"blau"+a.getFigur().getId());
				br.newLine();
				}
			}
			for(Spielfeld a :s.getBrett().getStartGruen())
			{
				if((a.getFarbe()== null)&&(a.getFigur()==null))
				{
					br.write(a.getId()+ ";"+"GRUEN"+ ";" +"null");
					br.newLine();
				}else if(a.getFigur()!=null){
				br.write(a.getId()+ ";"+"GRUEN"+ ";" + "gruen"+a.getFigur().getId());
				br.newLine();
				}
			}
			for(Spielfeld a :s.getBrett().getStartGelb())
			{
				if((a.getFarbe()!= null)&&(a.getFigur()==null))
				{
					br.write(a.getId()+ ";"+"GELB"+ ";" +"null");
					br.newLine();
				}else if(a.getFigur()!=null){
				br.write(a.getId()+ ";"+"GELB"+ ";" +"gelb"+a.getFigur().getId());
				br.newLine();
				}
			}
			for(Spielfeld a :s.getBrett().getWeg())
			{
				//System.out.println(s.getBrett().getWeg().get(0).getFigur());
				if((a.getFigur()==null))
				{
					br.write(a.getId()+ ";"+"null"+ ";" +"null");
					br.newLine();
				}else if(  a.getFigur()!=null){
				
			       if( a.getFigur().getFarbe()==FarbEnum.ROT){
				   br.write(a.getId()+ ";"+"null"+ ";"+"rot"+a.getFigur().getId());
				   br.newLine();
			
				   }else if( a.getFigur().getFarbe()==FarbEnum.BLAU){
				   br.write(a.getId()+ ";"+"null"+ ";"+"blau"+a.getFigur().getId());
				    br.newLine();
				
			     }else if( a.getFigur().getFarbe()==FarbEnum.GRUEN){
				br.write(a.getId()+ ";"+"null"+ ";"+"gruen"+a.getFigur().getId());
				br.newLine();
				
			    }else if( a.getFigur().getFarbe()==FarbEnum.GELB)
				br.write(a.getId()+ ";"+"null"+ ";"+"gelb"+a.getFigur().getId());
			//	br.newLine();
				
			}
			}
			for(Spielfeld a :s.getBrett().getEndRot())
			{
				if((a.getFarbe()!= null)&&(a.getFigur()==null))
				{
					br.write(a.getId()+ ";"+"ROT"+ ";" +"null");
					br.newLine();
				}else if(a.getFigur()!=null){
				br.write(a.getId()+ ";"+"ROT"+ ";" +"rot"+a.getFigur().getId());
				br.newLine();
				}
			}
			for(Spielfeld a :s.getBrett().getEndBlau())
			{
				if((a.getFarbe()!= null)&&(a.getFigur()==null))
				{
					br.write(a.getId()+ ";"+"BLAU"+ ";" +"null");
					br.newLine();
				}else if(a.getFigur()!=null){
				br.write(a.getId()+ ";"+"BLAU"+ ";" + "rot"+a.getFigur().getId());
				br.newLine();
				}
			}
			for(Spielfeld a :s.getBrett().getEndGruen())
			{
				if((a.getFarbe()!= null)&&(a.getFigur()==null))
				{
					br.write(a.getId()+ ";"+"GRUEN"+ ";" +"null");
					br.newLine();
				}else if(a.getFigur()!=null){
				br.write(a.getId()+ ";"+"GRUEN"+ ";"+ "rot"+a.getFigur().getId());
				br.newLine();
			}
			}
			for(Spielfeld a :s.getBrett().getEndGelb())
			{
				if((a.getFarbe()!= null)&&(a.getFigur()==null))
				{
					br.write(a.getId()+ ";"+"GELB"+ ";" +"null");
					br.newLine();
				}else if(a.getFigur()!=null){
				br.write(a.getId()+ ";"+"GELB"+ ";" + "rot"+a.getFigur().getId());
				br.newLine();
				}
			}
			br.write("unserSpielerAmzug heisst :"+s.getSpielerAmZug().getName()+";"+s.getSpielerAmZug().getFarbe()+";"+ s.getSpielerAmZug().getLetzterWurf());
		}
		catch(IOException e1) 
		{
			
		}
		finally 
		{
			try {
				br.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		
	}



	
	

	
	
	

	

}
