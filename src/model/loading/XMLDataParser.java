package model.loading;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import model.board.*;

public class XMLDataParser implements DataParser {

	private static final XMLDataParser instance = new XMLDataParser();

	// only one level up because of classpath
	private final String boardPath = "../resources/xmldata/finalboard.xml";
	private final String cardPath = "../resources/xmldata/finalcards.xml";
	private final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

	private XMLDataParser() {}

	private Element getElementByTagName(Element e, String name) {
		NodeList nl = e.getElementsByTagName(name);
		return e.item(0);
	}

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
					Element currCard = (Element) n;
					SceneCardInfo currCardInfo = new SceneCardInfo();
					// name
					Element nameElement = getElementByTagName(currCard, "name");
					currCardInfo.title = nameElement.getTextContent();
					// Roles
					NodeList parts = currCard.getElementsByTagName("part");
					List<RoleInfo> newRoleInfos = new ArrayList<>();
					for (int j = 0; j < parts.getLength(); j++) {
						Node currNode = parts.item(i);
						if (currNode.getNodeType() == Node.ELEMENT_NODE) {
							RoleInfo currRoleInfo = new RoleInfo();
							Element currRole = (Element) currNode;
							Element roleName = getElementByTagName(currRole, "name")
							currRoleInfo.title = roleName.getTextContent();
							Element roleLine = getElementByTagName(currRole, "line");
							currRoleInfo.line = roleLine.getTextContent();
							Element roleLevel = getElementByTagName(currRole, "level");
							currRoleInfo.rankRequired = Integer.parseInt(roleLevel);
							newRoleInfos.add(currRoleInfo);
						}
					}
					currRoleInfo.roleInfos = newRoleInfos;
					// scene description
					Element sceneElement = getElementByTagName(currCard, "scene");
					currCardInfo.number = Integer.parseInt(getElementByTagName(sceneElement, "number"));
					currCardInfo.description = getElementByTagName(sceneElement, "text");
					// budget
					Element budgetElement = getElementByTagName(currCard, "budget");
					currCardInfo.budget = Integer.parseInt(budgetElement.getTextContent());
					ret.add(currCardInfo);
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
			// Element officeElem = (Element) doc.getElementsByTagName("office").item(0);
			// RoomInfo office = new RoomInfo();
			// office.name = "office";
			// office.roomType = "office";
			// office.id = id++;
			// NodeList officeNeighborNodes = officeElem.getFirstChild().getChildNodes();
			// String officeNeighbors = new String[officeNeighborNodes.getLength()];
			// for (int j = 0; i < oficeNeighborNodes.getLength(); i++) {
			// 	System.out.println(officeNeighborNodes.item(i).getTextContent());
			// 	officeNeighbors[i] = officeNeighborNodes.item(i).getTextContent();
			// }
			// office.neighbors = officeNeighbors;
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

	public static XMLDataParser getInstance() { return instance; }



}