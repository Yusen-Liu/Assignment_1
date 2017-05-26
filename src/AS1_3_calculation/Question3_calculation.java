package AS1_3_calculation;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Question3_calculation {

	public static void main(String[] args) {
		   
		try {
			double ZL6r =0, ZL6x = 0, ZL6shr = 0, ZL6shx = 0, L6 = 0, ZL2r = 0, ZL2x = 0 , ZL2shr = 0, ZL2shx = 0, L2 = 0, ZT1r = 0, 
			ZT1x = 0, ZT2r = 0, ZT2x = 0, ZT3r = 0, ZT3x = 0, V1 = 0, V2 = 0, V3 = 0, V4 = 0, V5 = 0, ZC1r = 0, ZC1x = 0, ZC2r = 0, ZC2x = 0, P123r = 0,
			P123x = 0, PL2r = 0, PL2x = 0, PL1r = 0, PL1x = 0;
						
			File XmlFile = new File("C:/Users/lfs/Desktop/eh2745/Assignment_EQ_reduced.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XmlFile);
						 
			File XmlFile2 = new File("C:/Users/lfs/Desktop/eh2745/Assignment_SSH_reduced.xml");
			DocumentBuilderFactory dbFactory2 = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder2 = dbFactory2.newDocumentBuilder();
			Document doc2 = dBuilder2.parse(XmlFile2);
			// normalize XML file
				
			doc.getDocumentElement().normalize();
				
			NodeList ACLList = doc.getElementsByTagName("cim:ACLineSegment");
			NodeList TransformerList = doc.getElementsByTagName("cim:PowerTransformerEnd");
			NodeList VoltList = doc.getElementsByTagName("cim:VoltageLevel");
			NodeList ShuntList = doc.getElementsByTagName("cim:LinearShuntCompensator");
			NodeList LoadList2 = doc2.getElementsByTagName("cim:EnergyConsumer");

				
			for (int i = 0; i < ACLList.getLength(); i++) {
				
			Element element = (Element) ACLList.item(i);
				
			String ZLreal = element.getElementsByTagName("cim:ACLineSegment.r").item(0).getTextContent();
			String ZLim = element.getElementsByTagName("cim:ACLineSegment.x").item(0).getTextContent();		
			String ZLshreal = element.getElementsByTagName("cim:ACLineSegment.gch").item(0).getTextContent();
			String ZLshim = element.getElementsByTagName("cim:ACLineSegment.bch").item(0).getTextContent();
			String ZLlength = element.getElementsByTagName("cim:Conductor.length").item(0).getTextContent();
				
			if (i==0){
				ZL6r = Double.parseDouble(ZLreal);
				ZL6x = Double.parseDouble(ZLim);
				ZL6shr = Double.parseDouble(ZLshreal);
				ZL6shx = Double.parseDouble(ZLshreal);
				L6 = Double.parseDouble(ZLlength);
				
			}else{
				ZL2r = Double.parseDouble(ZLreal);
				ZL2x = Double.parseDouble(ZLim);
				ZL2shr = Double.parseDouble(ZLshreal);
				ZL2shx = Double.parseDouble(ZLshim);
				L2 = Double.parseDouble(ZLlength);	
				}
				
			}
			
			for (int i = 0; i < TransformerList.getLength(); i++) {
					
				Element element = (Element) TransformerList.item(i);
					
				String ZTreal = element.getElementsByTagName("cim:PowerTransformerEnd.r").item(0).getTextContent();
				String ZTim = element.getElementsByTagName("cim:PowerTransformerEnd.x").item(0).getTextContent();		

					
				if (i==0){
					ZT1r = Double.parseDouble(ZTreal);
					ZT1x = Double.parseDouble(ZTim);
					
					}
					else{
						if (i==2){
						 ZT2r = Double.parseDouble(ZTreal);
						 ZT2x = Double.parseDouble(ZTim);
					}
						else{
							if (i==4){
								 ZT3r = Double.parseDouble(ZTreal);
								 ZT3x = Double.parseDouble(ZTim);
								System.out.println( ZT3r +"\n" + ZT3x +"\n" );
							}
							else{
								continue;
							}
						}
					
					}
					
					}
				
			    for (int i = 0; i < VoltList.getLength(); i++) {
					
					Element element = (Element) VoltList.item(i);
					
				    String busvolt = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
						
					
					if (i==0){
					 V4 = Double.parseDouble(busvolt);
					
					
					}
					else{
						if (i==1){
						 V1 = Double.parseDouble(busvolt);
					}
						else{
							if (i==2){
								 V3 = Double.parseDouble(busvolt);
								System.out.println( V3 +"\n" );
							}
							else{
								if (i==3){
									 V2 = Double.parseDouble(busvolt);
								}
								else{
									 V5 = Double.parseDouble(busvolt);
								}
							}
						}
					
					}
					
					}
			    
				for (int i = 0; i < ShuntList.getLength(); i++) {
					
					Element element = (Element) ShuntList.item(i);
					
				    String ZCreal = element.getElementsByTagName("cim:LinearShuntCompensator.bPerSection").item(0).getTextContent();
					String ZCim = element.getElementsByTagName("cim:LinearShuntCompensator.gPerSection").item(0).getTextContent();		

					
					if (i==0){
					 ZC1r = Double.parseDouble(ZCreal);
					 ZC1x = Double.parseDouble(ZCim);
					
					}
					else{
						 ZC2r = Double.parseDouble(ZCreal);
						 ZC2x = Double.parseDouble(ZCim);
						System.out.println( ZC2r +"\n" + ZC2x +"\n");
					}
				}
						
			for (int i = 0; i < LoadList2.getLength(); i++) {
					
					Element element = (Element) LoadList2.item(i);
					
				    String ZLreal = element.getElementsByTagName("cim:EnergyConsumer.p").item(0).getTextContent();
					String ZLim = element.getElementsByTagName("cim:EnergyConsumer.q").item(0).getTextContent();		

					
					if (i==0){
					 P123r = Double.parseDouble(ZLreal);
					 P123x = Double.parseDouble(ZLim);
					
					}
					else{
						if (i==1){
						 PL2r = Double.parseDouble(ZLreal);
						 PL2x = Double.parseDouble(ZLim);
					}
						else{
								 PL1r = Double.parseDouble(ZLreal);
								 PL1x = Double.parseDouble(ZLim);
								System.out.println( PL1r +"\n" + PL1x +"\n" );
							
						}
					
					}
					}
				System.out.println( PL1r +"\n" + PL1x +"\n" );
			        Complex ZC1 = new Complex(ZC1r,ZC1x);
			        Complex ZC2 = new Complex(ZC2r,ZC2x);
			        Complex ZT1 = new Complex(ZT1r,ZT1x);
			        Complex ZT2 = new Complex(ZT2r,ZT2x);
			        Complex ZT3 = new Complex(ZT3r,ZT3x);
			        Complex ZL2 = new Complex(L2*ZL2r,L2*ZL2x);
			        Complex ZL6 = new Complex(L6*ZL6r,L6*ZL6x);
			        Complex SLD1 = new Complex(PL1r,PL1x);
			        Complex SLD2 = new Complex(PL2r,PL2x);
			        Complex SLD123 = new Complex(P123r,P123x);
			        Complex ZL2sh = new Complex(L2*ZL2shr,L2*ZL2shx);
			        Complex ZL6sh = new Complex(L6*ZL6shr,L6*ZL6shx);
			        Complex U5 = new Complex(V5,0);
			        Complex U2 = new Complex(V2,0);
			        Complex U3 = new Complex(V3,0);
			        Complex ref = new Complex(1,0);
			        Complex refm = new Complex(-1,0);
			        Complex ZLD1 = div(mul(U5,U5),SLD1);
			        Complex ZLD2 = div(mul(U3,U3),SLD2);
			        Complex ZLD123 = div(mul(U2,U2),SLD123);
			        Complex y[][] = new Complex[5][5];
			         y[0][0] = add(div(ref,ZC2),div(ref,ZT1));
			         y[0][1] = new Complex(0,0);
			         y[0][2] = new Complex(0,0);
			         y[0][3] = new Complex(0,0);
			         y[0][4] = div(refm,ZT1);
			         y[1][0] = new Complex(0,0);
			         y[1][1] = add(add(add(div(ref,ZL6),div(ref,ZL6sh)),add(div(ref,ZL2),div(ref,ZL2sh))),div(ref,ZLD123));
			         y[1][2] = add(div(refm,ZL6),div(refm,ZL2));
			         y[1][3] = new Complex(0,0);
			         y[1][4] = new Complex(0,0);
			         y[2][0] = new Complex(0,0);
			         y[2][1] = add(div(refm,ZL6),div(refm,ZL2));
			         y[2][2] = add(add(add(add(div(ref,ZL6),div(ref,ZL6sh)),add(div(ref,ZL2),div(ref,ZL2sh))),div(ref,ZLD2)),div(ref,ZT2));
			         y[2][3] = new Complex(0,0);
			         y[2][4] = div(refm,ZT2);
			         y[3][0] = new Complex(0,0);
			         y[3][1] = new Complex(0,0);
			         y[3][2] = new Complex(0,0);
			         y[3][3] = div(ref,ZT3);
			         y[3][4] = div(refm,ZT3);
			         y[4][0] = div(refm,ZT1);
			         y[4][1] = new Complex(0,0);
			         y[4][2] = div(refm,ZT2);
			         y[4][3] = div(refm,ZT3);
			         y[4][4] = add(add(div(ref,ZT3),div(ref,ZT1)),add(div(ref,ZC1),div(ref,ZLD1)));

			        
					String Y[][] = new String[5][5];
					for(int p = 0 ; p < 5 ; p++){
						for(int q = 0 ; q < 5 ; q++){
							Y[p][q] = Double.toString(getReal(y[p][q]))+ "j*" + Double.toString(getImage(y[p][q]));
							
						}
					}
					for (int p = 0 ; p < 5 ; p++){
						for(int q = 0 ; q < 5 ; q++){
							System.out.print(Y[p][q] + " ");
						}
						System.out.print("\n");
					}
					}
					catch(Exception e){
					e.printStackTrace();
				}
					
			}

				public static double getReal(Complex comp) {  
			        return comp.real;  
			    }  
			  

			  
			    public static double getImage(Complex comp) {  
			        return comp.image;  
			    }  
			  

			    public static Complex add(Complex a , Complex b){ // ∏¥ ˝œ‡º”  
			    	double real = getReal(a);  
			        double image = getImage(a);  
			    	double real2 = getReal(b);  
			        double image2 = getImage(b);  
			        double newReal = real + real2;  
			        double newImage = image + image2;  
			        Complex result = new Complex(newReal,newImage);  
			        return result;  
			    }  
			      
			    public static Complex sub(Complex a , Complex b){ // ∏¥ ˝œ‡ºı  
			    	double real = getReal(a);  
			        double image = getImage(a);
			    	double real2 = getReal(b);  
			        double image2 = getImage(b);  
			        double newReal = real - real2;  
			        double newImage = image - image2;  
			        Complex result = new Complex(newReal,newImage);  
			        return result;  
			    }  
			      
			    public static Complex mul(Complex a , Complex b){ // ∏¥ ˝œ‡≥À  
			    	double real   =  getReal(a);  
			        double image  =  getImage(a);
			    	double real2  =  getReal(b);  
			        double image2 =  getImage(b);  
			        double newReal = real*real2 - image*image2;  
			        double newImage = image*real2 + real*image2;  
			        Complex result = new Complex(newReal,newImage);  
			        return result;  
			    }  
			      
			    public static Complex div(Complex a , Complex b){ // ∏¥ ˝œ‡≥˝  
			    	double real   =  getReal(a);  
			        double image  =  getImage(a);
			    	double real2  =  getReal(b);  
			        double image2 =  getImage(b);  
			        double newReal = (real*real2 + image*image2)/(real2*real2 + image2*image2);  
			        double newImage = (image*real2 - real*image2)/(real2*real2 + image2*image2);  
			        Complex result = new Complex(newReal,newImage);  
			        return result;  
			    }
			
			    
			    
			      
       
					
		
	}


