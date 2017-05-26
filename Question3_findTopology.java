package AS1_3;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class Question3_findTopology {

	public static void main(String[] args) {

		System.out.println("Reading xml file...");
		try {
			File XmlFile = new File("/Users/Lysen/Documents/computer application/Assignment 1/Assignment_EQ_reduced.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XmlFile);
			
			doc.getDocumentElement().normalize();
		    
		   
			File XmlFile2 = new File("/Users/Lysen/Documents/computer application/Assignment 1/Assignment_SSH_reduced.xml");
			DocumentBuilderFactory dbFactory2 = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder2 = dbFactory2.newDocumentBuilder();
			Document doc2 = dBuilder2.parse(XmlFile2);
			
			doc2.getDocumentElement().normalize();
			
			NodeList busList = doc.getElementsByTagName("cim:BusbarSection");
			NodeList terminalList = doc.getElementsByTagName("cim:Terminal");
			
			System.out.println("Record data...");
			System.out.println("\nBus: \n" );
            
			ArrayList<bus> buses = new ArrayList<bus>();
			for (int i = 0; i < busList.getLength(); i++){
				Element element = (Element) busList.item(i);
			
				String rdfID = element.getAttribute("rdf:ID");
				String name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
	        
				System.out.println("rdfID: " + rdfID +"\n" + "name: " + name +"\n" );
			    buses.add(new bus(rdfID, name));

			}
			
			ArrayList<terminal> terminals = new ArrayList<terminal>();
			System.out.println("\nTerminal: \n" );
			for (int i = 0; i < terminalList.getLength(); i++){
				Element element = (Element) terminalList.item(i);
				NodeList equip = element.getElementsByTagName("cim:Terminal.ConductingEquipment");
				Element equipID = (Element) equip.item(0);
				NodeList node = element.getElementsByTagName("cim:Terminal.ConnectivityNode");
				Element nodeID = (Element) node.item(0);
				
				String rdfID = element.getAttribute("rdf:ID");
				String name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
			    String equipRfID = equipID.getAttribute("rdf:resource");
			    String nodeRfID = nodeID.getAttribute("rdf:resource");
			    
				System.out.println("rdfID: " + rdfID +"\n" + "name: " + name + "\n" + "equipRfID: " + equipRfID + "\n" + "nodeRfID: " + nodeRfID + "\n");
				
				terminals.add(new terminal(rdfID, name, equipRfID, nodeRfID));
				
			}
			System.out.println("\nIt is time to draw topology\n");
			for (int i = 0 ; i < busList.getLength(); i++){
				bus busStart = buses.get(i);
		
				System.out.println("Starting point:\nbus_rdfID: " + busStart.rdfID );
				
				for (int j = 0; j < terminalList.getLength(); j++){
					//find bus terminal
					terminal terbus = terminals.get(j);
				
					if ( busStart.rdfID.equals(terbus.equipID.subSequence(1, 38)) ){

						System.out.println("Bus terminal: " + terbus.name + "\n");

						
						for (int k = 0; k < terminalList.getLength(); k++){
							//find first terminal of first equipment
							terminal terquip1_1 = terminals.get(k);
							
							if ( ( terbus.nodeID.equals(terquip1_1.nodeID) ) && ( k != j ) ){
								System.out.println("First equipment: ");
								System.out.println("terminalName: " + terquip1_1.name +"\n"); 
								
								for(int q = 0; q < terminalList.getLength(); q++){
									//find second terminal of first equipment
									terminal terquip1_2 = terminals.get(q);
									
									if ( ( terquip1_1.equipID.equals(terquip1_2.equipID) ) && ( q != k ) ){
									
										for (int w = 0; w < terminalList.getLength(); w++){
											//find first terminal of second equipment
											terminal terquip2_1 = terminals.get(w);
											
											if ( terquip1_2.nodeID.equals(terquip2_1.nodeID) && ( w != q ) ){
												System.out.println("Second equipment!");
												System.out.println("terminalName: " + terquip2_1.name + "\n"); 
												
												for(int e = 0; e < terminalList.getLength(); e++){
													//find second terminal of second equipment
													terminal terquip2_2 = terminals.get(e);
													if ( terquip2_1.equipID.equals(terquip2_2.equipID) && ( e != w ) ){
														for(int r = 0; r < terminalList.getLength(); r++){
															//find first terminal of third equipment
															terminal terquip3_1 = terminals.get(r); 
															if ( terquip3_1.nodeID.equals(terquip2_2.nodeID) && ( r != e ) ){
																// Is the third equipment a bus?
														
																if ( terquip3_1.name.subSequence(0, 9).equals("BE-Busbar")){
																	System.out.println("The end is busbar:" + terquip3_1.name + "\n");
																	break;
																}else{
																	System.out.println("Third equipment!");
																	System.out.println("terminalName: " + terquip3_1.name + "\n"); 
																	
																	for(int t = 0; t < terminalList.getLength(); t++){
																		//find second terminal of the third equipment
																		
																		terminal terquip3_2 = terminals.get(t);
																		if ( terquip3_2.equipID.equals(terquip3_1.equipID) && ( t != r ) ){
																			for (int y = 0; y < terminalList.getLength(); y++){
																				//find fourth equipment
																				terminal terquip4_1 = terminals.get(y);
																				
																				if ( terquip4_1.nodeID.equals(terquip3_2.nodeID) && ( y != t ) ){
																					// Is the fourth equipment a bus?
																					if ( terquip4_1.name.subSequence(0, 9).equals("BE-Busbar")){
																						System.out.println("The end is busbar:" + terquip4_1.name + "\n");
																						break;
																					}else{
																						System.out.println("Fourth equipment!");
																						System.out.println("terminalName: " + terquip4_1.name + "\n"); 
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														
													}
												}
											}
										}
									}
								}
									
							}
						}
					}

				}
			}
            
		}

			catch(Exception e){
				e.printStackTrace();
			}
	}
}
