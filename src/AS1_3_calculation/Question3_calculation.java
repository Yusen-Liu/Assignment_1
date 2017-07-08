package AS1_3_calculation;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Question3_calculation {

	public static void main(String[] args) {
		   
		try {
			double ZL6r =0, ZL6x = 0, YL6shg = 0, YL6shb = 0, L6 = 0, ZL2r = 0, ZL2x = 0 , YL2shg = 0, YL2shb = 0, L2 = 0, ZT1r = 0, 
			ZT1x = 0, ZT2r = 0, ZT2x = 0, ZT3r = 0, ZT3x = 0, V1 = 0, V2 = 0, V3 = 0, V4 = 0, V6 = 0, YC1g = 0, YC1b = 0, YC2g = 0, YC2b = 0, P123r = 0,
			P123x = 0, PL2r = 0, PL2x = 0, PL1r = 0, PL1x = 0;
						
			File XmlFile = new File("/Users/Lysen/Documents/computer application/Assignment 1/Assignment_EQ_reduced.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XmlFile);
						 
			File XmlFile2 = new File("/Users/Lysen/Documents/computer application/Assignment 1/Assignment_SSH_reduced.xml");
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
				YL6shg = Double.parseDouble(ZLshreal);
				YL6shb = Double.parseDouble(ZLshreal);
				L6 = Double.parseDouble(ZLlength);
				
			}else{
				ZL2r = Double.parseDouble(ZLreal);
				ZL2x = Double.parseDouble(ZLim);
				YL2shg = Double.parseDouble(ZLshreal);
				YL2shb = Double.parseDouble(ZLshim);
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

							}
							else{
								if (i==3){
									 V2 = Double.parseDouble(busvolt);
								}
								else{
									 V6 = Double.parseDouble(busvolt);
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
					 YC1b = Double.parseDouble(ZCreal);
					 YC1g = Double.parseDouble(ZCim);
					
					}
					else{
						 YC2b = Double.parseDouble(ZCreal);
						 YC2g = Double.parseDouble(ZCim);
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

							
						}
					
					}
					}

			        
				double Sbase = 1000, Zbase1 = 0, Zbase2 = 0, Zbase3 = 0, Zbase4 = 0, Zbase6 = 0;
				Zbase1 = V1*V1/Sbase;
				Zbase2 = V2*V2/Sbase;
				Zbase3 = V3*V3/Sbase;
				Zbase4 = V4*V4/Sbase;
				Zbase6 = V6*V6/Sbase;
				
				/*
				System.out.println("Zbase1: " + Zbase1 + "\n");
				System.out.println("Zbase2: " + Zbase2 + "\n");
				System.out.println("Zbase3: " + Zbase3 + "\n");
				System.out.println("Zbase4: " + Zbase4 + "\n");
				System.out.println("Zbase6: " + Zbase6 + "\n");
				*/
				Complex YC1 = new Complex(YC1g,YC1b);
		        Complex YC2 = new Complex(YC2g,YC2b);			       
			    Complex ZT1 = new Complex(ZT1r/Zbase6,ZT1x/Zbase6);
			    Complex ZT2 = new Complex(ZT2r/Zbase6,ZT2x/Zbase6);
			    Complex ZT3 = new Complex(ZT3r/Zbase4,ZT3x/Zbase4);
			    Complex ZL2 = new Complex(ZL2r/Zbase3,ZL2x/Zbase3);
			    Complex ZL6 = new Complex(ZL6r/Zbase3,ZL6x/Zbase3);
			    Complex SLD1 = new Complex(PL1r,PL1x);
			    Complex SLD2 = new Complex(PL2r,PL2x);
			    Complex SLD123 = new Complex(P123r,P123x);
			    Complex YL2sh = new Complex(YL2shg,YL2shb);
			    Complex YL6sh = new Complex(YL6shg,YL6shb);
			    Complex U6 = new Complex(V6,0);
			    Complex U2 = new Complex(V2,0);
			    Complex U3 = new Complex(V3,0);
				Complex Z_base1 = new Complex(Zbase1,0);
			    Complex Z_base6 = new Complex(Zbase6,0);
			    Complex Z_base3 = new Complex(Zbase3,0);
			    Complex Z_base2 = new Complex(Zbase2,0);
			    Complex ref = new Complex(1,0);
			    Complex refm = new Complex(-1,0);
			    Complex ZC1 = div(div(ref,YC1),Z_base1);
			    Complex ZC2 = div(div(ref,YC2),Z_base6);
			    Complex ZLD1 = div(div(mul(U6,U6),SLD1),Z_base6);
			    Complex ZLD2 = div(div(mul(U3,U3),SLD2),Z_base3);
			    Complex ZLD123 = div(div(mul(U2,U2),SLD123),Z_base2);
			    Complex y[][] = new Complex[5][5];
			    YL6sh = div(YL6sh, div(ref,Z_base3));
			    YL2sh = div(YL2sh, div(ref,Z_base3));
			         
			    y[0][0] = add(div(ref,ZC2),div(ref,ZT1));
			    y[0][1] = new Complex(0,0);
			    y[0][2] = new Complex(0,0);
			    y[0][3] = new Complex(0,0);
			    y[0][4] = div(refm,ZT1);
			    y[1][0] = new Complex(0,0);
			    y[1][1] = add(add(add(div(ref,ZL6),YL6sh),add(div(ref,ZL2),YL2sh)),div(ref,ZLD123));
			    y[1][2] = add(div(refm,ZL6),div(refm,ZL2));
			    y[1][3] = new Complex(0,0);
			    y[1][4] = new Complex(0,0);
			    y[2][0] = new Complex(0,0);
			    y[2][1] = add(div(refm,ZL6),div(refm,ZL2));
			    y[2][2] = add(add(add(add(div(ref,ZL6),YL6sh),add(div(ref,ZL2),YL2sh)),div(ref,ZLD2)),div(ref,ZT2));
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
			         
			        Object[][] output = new Object [5][5];
					String Y[][] = new String[5][5];
					for(int p = 0 ; p < 5 ; p++){
						for(int q = 0 ; q < 5 ; q++){
							output[p][q] = Double.toString(getReal(y[p][q]))+ " + j*" + Double.toString(getImage(y[p][q]));
							
						}
					}
					String[] columnNames = {" ", " ", " ", " "," "};
					JTable table2 = new JTable(output, columnNames);
					JFrame jf2 = new JFrame();
					jf2.setTitle("Admittance Matrix");
					JScrollPane jsp2 = new JScrollPane(table2);
					jf2.add(jsp2);
					jf2.setBounds(0, 0, 500, 500);
					jf2.setVisible(true);
					/*
					for (int p = 0 ; p < 5 ; p++){
						for(int q = 0 ; q < 5 ; q++){
							System.out.print(Y[p][q] + "          ");
						}
						System.out.print("\n");
						
					}*/
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


