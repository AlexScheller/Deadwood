package model.loading;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import model.board.*;

public class XMLParser {

	private static final XMLParser instance = new XMLParser();

	// only one level up because of classpath
	private final String boardPath = "../resources/xmldata/finalboard.xml";
	private final String cardPath = "../resources/xmldata/finalcards.xml";
	private final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

	private XMLParser() {}

	public List<SceneCardInfo> parseSceneCardInfos() {
		
		List<SceneCardInfo> ret = new ArrayList<>();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(cardPath));
			doc.getDocumentElement().normalize();
			NodeList cards = doc.getElementsByTagName("card");
			int id = 1;
			for (int i = 0; i < cards.getLength(); i++) {
				Node n = cards.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) n;
					SceneCardInfo curr = new SceneCardInfo();
					Element currScene = (Element) e.getElementsByTagName("scene").item(0);
					curr.id = id++;
					curr.number = Integer.parseInt(currScene.getAttribute("number"));
					curr.budget = Integer.parseInt(e.getAttribute("budget"));
					curr.title = e.getAttribute("name");
					curr.description = currScene.getTextContent();
					ret.add(curr);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;

	}

	public List<RoomInfo> parseRoomInfos () {

		List<RoomInfo> ret = new ArrayList<>();
		int id = 1;
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(boardPath));
			doc.getDocumentElement().normalize();
			NodeList sets = doc.getElementsByTagName("set");
			for (int i = 0; i < sets.getLength(); i++) {
				Node n = sets.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) n;
					RoomInfo curr = new RoomInfo();
					curr.id = id++;
					curr.name = e.getAttribute("name");
					curr.roomType = "set";
					NodeList neighborNodes = e.getFirstChild().getChildNodes();
					String[] neighbors = new String[neighborNodes.getLength()];
					for (int j = 0; i < neighborNodes.getLength(); i++) {
						System.out.println(neighborNodes.item(i).getTextContent());
						neighbors[i] = neighborNodes.item(i).getTextContent();
					}
					curr.neighbors = neighbors;
					ret.add(curr);
				}
			}
			Element officeElem = (Element) doc.getElementsByTagName("office").item(0);
			RoomInfo office = new RoomInfo();
			office.name = "office";
			office.roomType = "office";
			office.id = id++;
			NodeList officeNeighborNodes = officeElem.getFirstChild().getChildNodes();
			String officeNeighbors = new String[officeNeighborNodes.getLength()];
			for (int j = 0; i < oficeNeighborNodes.getLength(); i++) {
				System.out.println(officeNeighborNodes.item(i).getTextContent());
				officeNeighbors[i] = officeNeighborNodes.item(i).getTextContent();
			}
			office.neighbors = officeNeighbors;
			// ret.add(office);

			// NodeList neighborNodes = e.getFirstChild().getChildNodes();
			// String[] neighbors = new String[neighborNodes.getLength()];
			// for (int j = 0; i < neighborNodes.getLength(); i++) {
			// 	System.out.println(neighborNodes.item(i).getTextContent());
			// 	neighbors[i] = neighborNodes.item(i).getTextContent();
			// }
			// curr.neighbors = neighbors;

			// RoomInfo trailer = new RoomInfo();
			// trailer.name = "trailer";
			// trailer.roomType = "trailer";
			// trailer.id = id++;
			// ret.add(trailer);

			// NodeList neighborNodes = e.getFirstChild().getChildNodes();
			// String[] neighbors = new String[neighborNodes.getLength()];
			// for (int j = 0; i < neighborNodes.getLength(); i++) {
			// 	System.out.println(neighborNodes.item(i).getTextContent());
			// 	neighbors[i] = neighborNodes.item(i).getTextContent();
			// }
			// curr.neighbors = neighbors;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;

	} 

	public static XMLParser getInstance() { return instance; }



}