package testmadn;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class DatenzugriffXML implements iDatenzugriff{

	@Override
	public void speichern(String filename,Object spiel ) {
		FileWriter fw=null;
		try{
			JAXBContext context=JAXBContext.newInstance(SpielBean.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//			m.marshal(spiel,new File(".xml"));
//			m.marshal(spiel, System.out);
			fw=new FileWriter(filename+".xml");
			m.marshal(spiel, fw);
			
		} 
		catch (JAXBException e) {
			e.printStackTrace();
		}
		catch (IOException ex){
			ex.getMessage();
		}
		finally{
			try{
				fw.close();
			}
			catch (Exception e){
				e.getStackTrace();
			}
		}
	}

	@Override
	public Object laden(String filename){
		SpielBean s1 =null;
		try{
			
			JAXBContext context=JAXBContext.newInstance(SpielBean.class);
			Unmarshaller um=context.createUnmarshaller();
			
			s1 = (SpielBean) um.unmarshal(new FileReader(filename));
			
			if(s1!=null){
				s1.setSpielerAmZug((Spieler)s1.getSpielerAmZug());
				s1.setSpielerAnzahl(s1.getSpielerlist().size());
				s1.setWuerfelZahl(s1.getWuerfelZahl());
				
				for(Spieler a:s1.getSpielerlist()){
					if(a!=null&&a.getKi()!=null){
						a.getKi().setSpiel(s1);
					}
				}
				
				this.setSpielAmZugXML(s1.getSpielerAmZug(), s1);
				
				//-------Startfelder-------
				for(Spielfeld e :s1.getBrett().getStartRot()){
					if(e.istFeldBelegt()==true){
						setFeldBeifigurÜberSpieler(s1, e, e.getFigur().getId());
					}
					
				}
				
				for(Spielfeld e :s1.getBrett().getStartBlau()){
					if(e.istFeldBelegt()==true){
						setFeldBeifigurÜberSpieler(s1, e, e.getFigur().getId());
					}
				}
				
				for(Spielfeld e :s1.getBrett().getStartGruen()){
					if(e.istFeldBelegt()==true){
						setFeldBeifigurÜberSpieler(s1, e, e.getFigur().getId());
					}
				}
				
				for(Spielfeld e :s1.getBrett().getStartGelb()){
					if(e.istFeldBelegt()==true){
						setFeldBeifigurÜberSpieler(s1, e, e.getFigur().getId());
					}
				}
				
				//---------Endfelder----------
				for(Spielfeld e :s1.getBrett().getEndRot()){
					if(e.istFeldBelegt()==true){
						setFeldBeifigurÜberSpieler(s1, e, e.getFigur().getId());
					}
				}
				
				for(Spielfeld e :s1.getBrett().getEndBlau()){
					if(e.istFeldBelegt()==true){
						setFeldBeifigurÜberSpieler(s1, e, e.getFigur().getId());
					}
				}
				
				for(Spielfeld e :s1.getBrett().getEndGruen()){
					if(e.istFeldBelegt()==true){
						setFeldBeifigurÜberSpieler(s1, e, e.getFigur().getId());
					}
				}
				
				for(Spielfeld e :s1.getBrett().getEndGelb()){
					if(e.istFeldBelegt()==true){
						setFeldBeifigurÜberSpieler(s1, e, e.getFigur().getId());
					}
				}
				
				//-------------Weg-----------
				for(Spielfeld feld:s1.getBrett().getWeg()){
					if(feld.istFeldBelegt()==true){
						setFeldBeifigurÜberSpieler(s1, feld, feld.getFigur().getId());
					}
				}
				
			}
			else{
//				System.out.println("nope");
			}
		}
		
		catch (Exception e){
			e.getMessage();
		}
		return s1;
	}
	
	public void setFeldBeifigurÜberSpieler(SpielBean s,Spielfeld f,int figId){
		
		if(f.getFigur().getFarbe()==FarbEnum.ROT){
			for(Spieler y:s.getSpielerlist()){
				if(y.getFarbe()==FarbEnum.ROT){
					y.getFigurlist().get(figId).setFeld(f);
//					f.setFigur(f.getFigur());
					f.setFigur(y.getFigurlist().get(figId));
					break ;
				}
			}
		}
		
		else if(f.getFigur().getFarbe()==FarbEnum.BLAU){
			for(Spieler y:s.getSpielerlist()){
				if(y.getFarbe()==FarbEnum.BLAU){
					y.getFigurlist().get(figId).setFeld(f);
					f.setFigur(y.getFigurlist().get(figId));
					break ;
				}
				
			}
		}
		
		else if(f.getFigur().getFarbe()==FarbEnum.GRUEN){
			for(Spieler y:s.getSpielerlist()){
				if(y.getFarbe()==FarbEnum.GRUEN){
					y.getFigurlist().get(figId).setFeld(f);
					f.setFigur(y.getFigurlist().get(figId));
					break ;
				}
			}
		} 
		
		else if(f.getFigur().getFarbe()==FarbEnum.GELB){
			for(Spieler y:s.getSpielerlist()){
				if(y.getFarbe()==FarbEnum.GELB){
					y.getFigurlist().get(figId).setFeld(f);
					f.setFigur(y.getFigurlist().get(figId));
					break ;
				}
			}
		}
		
	}
	
    public void setSpielAmZugXML(Spieler s, SpielBean game){
    	
    	if(s.getFarbe()==FarbEnum.ROT){
    		for(Spieler a : game.getSpielerlist()){
    			if(a!=null){
    				if(a.getFarbe()==FarbEnum.ROT){
    					game.setSpielerAmZug(a);
    					break;
    				}
    			}
    		}
    	}
    	
    	else if(s.getFarbe()==FarbEnum.BLAU){
    		for(Spieler a : game.getSpielerlist()){
    			if(a!=null){
    				if(a.getFarbe()==FarbEnum.BLAU){
    					game.setSpielerAmZug(a);
    					break;
    				}
    			}
    		}
        }
    	
    	else if(s.getFarbe()==FarbEnum.GRUEN){
    		for(Spieler a : game.getSpielerlist()){
    			if(a!=null){
    				if(a.getFarbe()==FarbEnum.GRUEN){
    					game.setSpielerAmZug(a);
    					break;
    				}
    			}
    		}
    	}
    	
    	else if(s.getFarbe()==FarbEnum.GELB){
    		for(Spieler a : game.getSpielerlist()){
    			if(a!=null){
    				if(a.getFarbe()==FarbEnum.GELB){
    					game.setSpielerAmZug(a);
    					break;
    				}
    			}
    		}
        }
    }
	
}

