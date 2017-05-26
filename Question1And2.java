package AS1;


import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.sql.*;

public class Question1And2 {
	public static void main(String[] args){
        
		// JDBC driver name and database URL
		 final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	     final String DB_URL = "jdbc:mysql://localhost/";
		 final String DISABLE_SSL = "?useSSL=false";
		 // Database credentials
		 final String USER = "root";
		 final String PASS = "ok27987268"; // insert the password to SQL server

		
		 Connection conn = null;
		 Statement stmt = null;
		 try{
		 // Register JDBC driver
		 Class.forName(JDBC_DRIVER);
		 // Open a connection
		 System.out.println("Connecting to SQL server...");
		 conn = DriverManager.getConnection(DB_URL+DISABLE_SSL, USER, PASS);

		// execute a query to create database
		 System.out.println("Creating database...");
		 stmt = conn.createStatement();
		 String sql = "CREATE DATABASE IF NOT EXISTS PowerSystem"; // create database Students
		 stmt.executeUpdate(sql);
		 System.out.println("Database created successfully...");

		 // Connect to the created database power system and create sub-tables
		 conn = DriverManager.getConnection(DB_URL + "PowerSystem"+DISABLE_SSL, USER, PASS);
		 sql = "USE PowerSystem";
		 stmt.executeUpdate(sql);
		 sql = "DROP TABLE IF EXISTS BaseVoltage";
		 stmt.executeUpdate(sql);
		 sql = "DROP TABLE IF EXISTS Substation";
		 stmt.executeUpdate(sql);
		 sql = "DROP TABLE IF EXISTS VoltageLevel";
		 stmt.executeUpdate(sql);
		 sql = "DROP TABLE IF EXISTS GeneratingUnit";
		 stmt.executeUpdate(sql);
		 sql = "DROP TABLE IF EXISTS SynchronousMachine";
		 stmt.executeUpdate(sql);
		 sql = "DROP TABLE IF EXISTS RegulatingControl";
		 stmt.executeUpdate(sql);
		 sql = "DROP TABLE IF EXISTS PowerTransformer";
		 stmt.executeUpdate(sql);
		 sql = "DROP TABLE IF EXISTS EnergyConsumer";
		 stmt.executeUpdate(sql);
		 sql = "DROP TABLE IF EXISTS PowerTransformerEnd";
		 stmt.executeUpdate(sql);
		 sql = "DROP TABLE IF EXISTS Breaker";
		 stmt.executeUpdate(sql);
		 sql = "DROP TABLE IF EXISTS RatioTapChanger";
		 stmt.executeUpdate(sql);
	//
		 sql = "CREATE TABLE IF NOT EXISTS BaseVoltage (rdfID VARCHAR(37)  NOT NULL , NominalValue VARCHAR(9) , PRIMARY KEY(rdfID))"; 
		 stmt.executeUpdate(sql) ; // execute query
		 sql = "CREATE TABLE IF NOT EXISTS Substation (rdfID VARCHAR(37)  NOT NULL , Name VARCHAR(11) , Region_rdfID VARCHAR(34), PRIMARY KEY(rdfID))"; 
		 stmt.executeUpdate(sql) ; 
		 sql = "CREATE TABLE IF NOT EXISTS VoltageLevel (rdfID VARCHAR(37)  NOT NULL , Name VARCHAR(11) , Substation_rdfID VARCHAR(38), baseVoltage_rdfID  VARCHAR(38), PRIMARY KEY(rdfID))"; 
		 stmt.executeUpdate(sql) ; 
		 sql = "CREATE TABLE IF NOT EXISTS GeneratingUnit (rdfID VARCHAR(37)  NOT NULL , Name VARCHAR(30) , MaxP VARCHAR(11) , MinP VARCHAR(11), EquipmentContainer_rdfID VARCHAR(38), PRIMARY KEY(rdfID))"; 
		 stmt.executeUpdate(sql) ; 
		 sql = "CREATE TABLE IF NOT EXISTS SynchronousMachine (rdfID VARCHAR(37)  NOT NULL, Name VARCHAR(10) , RatedS DOUBLE , P VARCHAR(11), Q VARCHAR(11), GenUnit_rdfID VARCHAR(38), regControl_rdfID VARCHAR(38), EquipmentContainer_rdfID VARCHAR(38) , PRIMARY KEY(rdfID))"; 
		 stmt.executeUpdate(sql) ; 
		 sql = "CREATE TABLE IF NOT EXISTS RegulatingControl (rdfID VARCHAR(37)  NOT NULL, Name VARCHAR(10) , TargetValue VARCHAR(11) , PRIMARY KEY(rdfID))"; 
		 stmt.executeUpdate(sql) ; 
		 sql = "CREATE TABLE IF NOT EXISTS PowerTransformer (rdfID VARCHAR(37)  NOT NULL, Name VARCHAR(10) , EquipmentContainer_rdfID VARCHAR(38), PRIMARY KEY(rdfID))"; 
		 stmt.executeUpdate(sql) ; 
		 sql = "CREATE TABLE IF NOT EXISTS EnergyConsumer (rdfID VARCHAR(37)  NOT NULL, Name VARCHAR(30) , P VARCHAR(11), Q VARCHAR(11), EquipmentContainer_rdfID VARCHAR(38) , PRIMARY KEY(rdfID))"; 
		 stmt.executeUpdate(sql) ; 
		 sql = "CREATE TABLE IF NOT EXISTS PowerTransformerEnd (rdfID VARCHAR(37)  NOT NULL, Name VARCHAR(10) , Transformer_r VARCHAR(11) , Transformer_x VARCHAR(11) , Transformer_rdfID VARCHAR(38) , BaseVoltage_rdfID VARCHAR(38) , PRIMARY KEY(rdfID))"; 
		 stmt.executeUpdate(sql) ; 
		 sql = "CREATE TABLE IF NOT EXISTS Breaker (rdfID VARCHAR(37)  NOT NULL, Name VARCHAR(30) , State_open VARCHAR(5) , EquipmentContainer_rdfID VARCHAR(38) ,PRIMARY KEY(rdfID))"; 
		 stmt.executeUpdate(sql) ; 
		 sql = "CREATE TABLE IF NOT EXISTS RatioTapChanger (rdfID VARCHAR(37)  NOT NULL, Name VARCHAR(10) , Step VARCHAR(11) ,PRIMARY KEY(rdfID))"; 
		 stmt.executeUpdate(sql) ; 
		 
		 
		 
		 
		 System.out.println("Created tables in given database successfully...");
        
		 
		 }catch(SQLException se){
		 //Handle errors for JDBC
		 se.printStackTrace();
		 }catch(Exception e){
		 //Handle errors for Class.forName
		 e.printStackTrace();}
		
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
			
			NodeList basevoltList = doc.getElementsByTagName("cim:BaseVoltage");
			NodeList subList = doc.getElementsByTagName("cim:Substation");
			NodeList voltList = doc.getElementsByTagName("cim:VoltageLevel");
			NodeList genList = doc.getElementsByTagName("cim:GeneratingUnit");
			NodeList machineList = doc.getElementsByTagName("cim:SynchronousMachine");
			NodeList machineList2 = doc2.getElementsByTagName("cim:SynchronousMachine");
			NodeList reguList = doc.getElementsByTagName("cim:RegulatingControl");
			NodeList reguList2 = doc2.getElementsByTagName("cim:RegulatingControl");
			NodeList tranList = doc.getElementsByTagName("cim:PowerTransformer");
			NodeList loadList = doc.getElementsByTagName("cim:EnergyConsumer");
			NodeList loadList2 = doc2.getElementsByTagName("cim:EnergyConsumer");
			NodeList windingList = doc.getElementsByTagName("cim:PowerTransformerEnd");
			NodeList breakList = doc.getElementsByTagName("cim:Breaker");
			NodeList breakList2 = doc2.getElementsByTagName("cim:Breaker");
			NodeList ratioList = doc.getElementsByTagName("cim:RatioTapChanger");
			NodeList ratioList2 = doc2.getElementsByTagName("cim:RatioTapChanger");
			


			System.out.println("Inserted below records into the table...");
			System.out.println("\nBase Voltage: \n" );

			for (int i = 0; i < basevoltList.getLength(); i++){
				Element element = (Element) basevoltList.item(i);
			
				String rdfID = element.getAttribute("rdf:ID");
				String nominalValue = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
	        
				System.out.println("rdfID: " + rdfID +"\n" + "nominal value: " + nominalValue +"\n" );
			
				String query = "insert into BaseVoltage values(?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, rdfID);
				preparedStmt.setString(2, nominalValue);
				preparedStmt.executeUpdate();
			}
			
			System.out.println("\nSubstaintion: \n" );
			for (int i = 0; i < subList.getLength(); i++){
				Element element = (Element) subList.item(i);
				NodeList region = element.getElementsByTagName("cim:Substation.Region");
				Element regionID = (Element) region.item(0);
				
				String rdfID = element.getAttribute("rdf:ID");
				String name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
			    String regionRfID = regionID.getAttribute("rdf:resource");
				
				System.out.println("rdfID: " + rdfID +"\n" + "name: " + name + "\n" + "region_rdfID: " + regionRfID + "\n");
				
				String query = "insert into Substation values(?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, rdfID);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, regionRfID);
				
				preparedStmt.executeUpdate();
			}

			System.out.println("\nVoltage Level: \n" );
			for (int i = 0; i < voltList.getLength(); i++){
				Element element = (Element) voltList.item(i);
				
				NodeList substaintion = element.getElementsByTagName("cim:VoltageLevel.Substation");
				NodeList baseVoltage = element.getElementsByTagName("cim:VoltageLevel.BaseVoltage");
				
				Element subID = (Element) substaintion.item(0);
				Element baseVoltID = (Element) baseVoltage.item(0);
				
				String rdfID = element.getAttribute("rdf:ID");
				String name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
			    String subRfID = subID.getAttribute("rdf:resource");
				String baseVoltRfID = baseVoltID.getAttribute("rdf:resource");
				System.out.println("rdfID: " + rdfID +"\n" + "name: " + name + "\n" + "substation_rdfID: " + subRfID + "\n" + "baseVoltage_rdfID: " + baseVoltRfID + "\n");
				
				String query = "insert into VoltageLevel values(?, ?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, rdfID);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, subRfID);
				preparedStmt.setString(4, baseVoltRfID);
				
				preparedStmt.executeUpdate();

			}
			
			System.out.println("\nGenerating Unit: \n" );
			for (int i = 0; i < genList.getLength(); i++){
				Element element = (Element) genList.item(i);
				NodeList container = element.getElementsByTagName("cim:Equipment.EquipmentContainer");
				Element containerID = (Element) container.item(0);
				
				String rdfID = element.getAttribute("rdf:ID");
				String name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
				String maxP = element.getElementsByTagName("cim:GeneratingUnit.maxOperatingP").item(0).getTextContent(); 
			    String minP = element.getElementsByTagName("cim:GeneratingUnit.minOperatingP").item(0).getTextContent();
				String containerRfID = containerID.getAttribute("rdf:resource");
				
				System.out.println("rdfID: " + rdfID +"\n" + "name: " + name + "\n" + "maxP: " + maxP + "\n" + "minP: " + minP + "\n" + "equipmentContainer_rdfID: " + containerRfID + "\n");
				
				String query = "insert into GeneratingUnit values(?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, rdfID);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, maxP);
				preparedStmt.setString(4, minP);
				preparedStmt.setString(5, containerRfID);
				
				preparedStmt.executeUpdate();
			}
			
			System.out.println("\nSychronous Machine: \n" );
			for (int i = 0; i < machineList.getLength(); i++){
				Element element = (Element) machineList.item(i);
				
				NodeList genUnit = element.getElementsByTagName("cim:RotatingMachine.GeneratingUnit");
				NodeList regControl = element.getElementsByTagName("cim:RegulatingCondEq.RegulatingControl");
				NodeList equipment = element.getElementsByTagName("cim:Equipment.EquipmentContainer");
				
				Element genUnitID = (Element) genUnit.item(0);
				Element regConID = (Element) regControl.item(0);
				Element equipID =  (Element) equipment.item(0);
				
				String rdfID = element.getAttribute("rdf:ID");
				String name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
				String ratedS = element.getElementsByTagName("cim:RotatingMachine.ratedS").item(0).getTextContent();
				String genUnitRfID = genUnitID.getAttribute("rdf:resource");
				String regConRfID = regConID.getAttribute("rdf:resource");
				String equipRfID = equipID.getAttribute("rdf:resource");
				
				Element element2 = (Element) machineList2.item(i);
				
				String P = element2.getElementsByTagName("cim:RotatingMachine.p").item(0).getTextContent();
				String Q = element2.getElementsByTagName("cim:RotatingMachine.q").item(0).getTextContent();
				
				System.out.println("rdfID: " + rdfID +"\n" + "name: " + name + "\n" + "ratedS: " + ratedS + "\n" + "P: " + P +"\n" + "Q: " + Q +"\n"+ "genUnit_rdfID: " + genUnitRfID + "\n" + "regControl_rdfID: " + regConRfID + "\n" + "equipmentContainer_rdfID: " + equipRfID +"\n" );
				
				String query = "insert into SynchronousMachine values(?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, rdfID);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, ratedS);
				preparedStmt.setString(4, P);
				preparedStmt.setString(5, Q);
				preparedStmt.setString(6, genUnitRfID);
				preparedStmt.setString(7, regConRfID);
				preparedStmt.setString(8, equipRfID);
				preparedStmt.executeUpdate();
			}

			System.out.println("\nRegulating Control: \n" );
			for (int i = 0; i < reguList.getLength(); i++){

				Element element = (Element) reguList.item(i);
				
				String rdfID = element.getAttribute("rdf:ID");
				String name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
			   
				Element element2 = (Element) reguList2.item(i);
				
				String targetValue = element2.getElementsByTagName("cim:RegulatingControl.targetValue").item(0).getTextContent();
				
				System.out.println("rdfID: " + rdfID +"\n" + "name: " + name + "\n" + "targetValue: " + targetValue +"\n");

				String query = "insert into RegulatingControl values(?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, rdfID);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, targetValue);
				preparedStmt.executeUpdate();
			}
			
			System.out.println("\nPower Transformer: \n" );
			for (int i = 0; i < tranList.getLength(); i++){
				Element element = (Element) tranList.item(i);
				NodeList equipment = element.getElementsByTagName("cim:Equipment.EquipmentContainer");
				Element equipID = (Element) equipment.item(0);
				
				String rdfID = element.getAttribute("rdf:ID");
				String name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
			    String equipRfID = equipID.getAttribute("rdf:resource");
				
				System.out.println("rdfID: " + rdfID +"\n" + "name: " + name + "\n" + "equipmentContainer_rdfID: " + equipRfID + "\n");

				String query = "insert into PowerTransformer values(?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, rdfID);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, equipRfID);
				preparedStmt.executeUpdate();
			}
			
			System.out.println("\nEnergy Consumer: \n" );
			for (int i = 0; i < loadList.getLength(); i++){
				Element element = (Element) loadList.item(i);
				NodeList equipment = element.getElementsByTagName("cim:Equipment.EquipmentContainer");
				Element equipID = (Element) equipment.item(0);
				
				String rdfID = element.getAttribute("rdf:ID");
				String name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
			    String equipRfID = equipID.getAttribute("rdf:resource");
				
				Element element2 = (Element) loadList2.item(i);
				
				String P = element2.getElementsByTagName("cim:EnergyConsumer.p").item(0).getTextContent();
				String Q = element2.getElementsByTagName("cim:EnergyConsumer.q").item(0).getTextContent();

				System.out.println("rdfID: " + rdfID +"\n" + "name: " + name + "\n" + "P: " + P + "\n" + "Q: " + Q +"\n" + "equipmentContainer_rdfID: " + equipRfID + "\n");

				String query = "insert into EnergyConsumer values(?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, rdfID);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, P);
				preparedStmt.setString(4, Q);
				preparedStmt.setString(5, equipRfID);
				preparedStmt.executeUpdate();
				
			}
			
			System.out.println("\nPower Transformer End: \n" );
			for (int i = 0; i < windingList.getLength(); i++){
				Element element = (Element) windingList.item(i);
				
				NodeList transformer = element.getElementsByTagName("cim:PowerTransformerEnd.PowerTransformer");
				NodeList baseVoltage = element.getElementsByTagName("cim:TransformerEnd.BaseVoltage");
				
				Element transID = (Element) transformer.item(0);
				Element baseVoltID = (Element) baseVoltage.item(0);
				
				String rdfID = element.getAttribute("rdf:ID");
				String name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
				String r = element.getElementsByTagName("cim:PowerTransformerEnd.r").item(0).getTextContent();
				String x = element.getElementsByTagName("cim:PowerTransformerEnd.x").item(0).getTextContent();
				String transRfID = transID.getAttribute("rdf:resource");
				String baseVoltRfID = baseVoltID.getAttribute("rdf:resource");
				
				System.out.println("rdfID: " + rdfID +"\n" + "name: " + name + "\n" + "Transformer.r: " + r + "\n" + "Transformer.x: " + x +"\n" +"Transformer_rdfID: " + transRfID + "\n" + "baseVoltage_rdfID: " + baseVoltRfID + "\n");
				
				String query = "insert into PowerTransformerEnd values(?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, rdfID);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, r);
				preparedStmt.setString(4, x);
				preparedStmt.setString(5, transRfID);
				preparedStmt.setString(6, baseVoltRfID);
				preparedStmt.executeUpdate();
			}
			
			System.out.println("\nBreaker: \n" );
			for (int i = 0; i < breakList.getLength(); i++){
				Element element = (Element) breakList.item(i);
				NodeList equipment = element.getElementsByTagName("cim:Equipment.EquipmentContainer");
				Element equipID = (Element) equipment.item(0);
				
				String rdfID = element.getAttribute("rdf:ID");
				String name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
			    String equipRfID = equipID.getAttribute("rdf:resource");
				
				Element element2 = (Element) breakList2.item(i);
				
				String state = element2.getElementsByTagName("cim:Switch.open").item(0).getTextContent();

				System.out.println("rdfID: " + rdfID +"\n" + "name: " + name + "\n" + "state(open?): " + state + "\n" + "equipmentContainer_rdfID: " + equipRfID + "\n");
			
				String query = "insert into Breaker values(?, ?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, rdfID);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, state);
				preparedStmt.setString(4, equipRfID);
				preparedStmt.executeUpdate();
			}
			
			System.out.println("\nRatio Tap Changer: \n" );
			for (int i = 0; i < ratioList.getLength(); i++){
				Element element = (Element) ratioList.item(i);
				
				String rdfID = element.getAttribute("rdf:ID");
				String name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();

				System.out.println();
				
				Element element2 = (Element) ratioList2.item(i);
				
				String step = element2.getElementsByTagName("cim:TapChanger.step").item(0).getTextContent();
			   
				System.out.println("rdfID: " + rdfID +"\n" + "name: " + name + "\n" + "step: " + step +"\n" );
				
				String query = "insert into RatioTapChanger values(?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, rdfID);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, step);
				preparedStmt.executeUpdate();
			}
			conn.close();
			 
		 }catch(SQLException se){
		 //Handle errors for JDBC
		 se.printStackTrace();
		 }catch(Exception e){
		 //Handle errors for Class.forName
		 e.printStackTrace();}
		 
		 System.out.println("The table is updated... \nGoodbye!");
		
		 //Update database
		 //update();
	}		 

		
		
	 public static void update(){
		 final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	     final String DB_URL = "jdbc:mysql://localhost/";
		 final String DISABLE_SSL = "?useSSL=false";
		 // Database credentials
		 final String USER = "root";
		 final String PASS = "ok27987268"; // insert the password to SQL server

		
		 Connection conn = null;
		 Statement stmt = null;
		 try{
			 // Register JDBC driver
			 Class.forName(JDBC_DRIVER);
			 // Open a connection
			 System.out.println("Connecting to SQL server...");
			 conn = DriverManager.getConnection(DB_URL+DISABLE_SSL, USER, PASS);
			 conn = DriverManager.getConnection(DB_URL + "PowerSystem"+DISABLE_SSL, USER, PASS);
			 String sql = "USE PowerSystem";
			 stmt = conn.createStatement();
			 stmt.executeUpdate(sql);
			 
			 String query = "update Breaker SET State_open =? WHERE rdfID=?"; // update status of Breaker, id=103
			 PreparedStatement preparedStmt = conn.prepareStatement(query);
			 preparedStmt.setString(1, "true");
			 preparedStmt.setString(2, "_0a84038e-1952-4d9d-9909-3b49c364a1ac");
			 // execute PreparedStatement
			 preparedStmt.executeUpdate();
			 // insert a new values to the table with preparedstatement
			
			 query = "insert into Breaker values(?, ?, ?, ?)";
			 preparedStmt = conn.prepareStatement(query);
			 preparedStmt.setString(1, "");
			 preparedStmt.setString(2, "A");
			 preparedStmt.setString(3, "A");
			 preparedStmt.setString(4, "");
			 preparedStmt.executeUpdate();
			 // finish the statement
			 System.out.println("The table is updated...");
			 conn.close();
			 
		 }catch(SQLException se){
		 //Handle errors for JDBC
		 se.printStackTrace();
		 }catch(Exception e){
		 //Handle errors for Class.forName
		 e.printStackTrace();}
	}
}
	







