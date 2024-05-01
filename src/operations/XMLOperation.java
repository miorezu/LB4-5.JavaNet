package operations;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import data.MetroCard;
import data.MetroCardBank;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class XMLOperation extends CardOperation {

    @Override
    public String execute(MetroCardBank cardBank) {
        XMLSave(XMLCompose(cardBank.getStore()), "Cards.xml");
        return "Success";
    }

    public static Document XMLCompose(ArrayList<MetroCard> cards) {
        Document doc;

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("CardsList");
            doc.appendChild(rootElement);

            for (MetroCard card : cards) {
                Element Card = doc.createElement("InfoCard");
                Attr attribute = doc.createAttribute("SerialNumber");
                attribute.setValue(card.getSerialNumber());
                Card.setAttributeNode(attribute);

                attribute = doc.createAttribute("Balance");
                attribute.setValue(String.valueOf(card.getBalance()));
                Card.setAttributeNode(attribute);

                attribute = doc.createAttribute("Establishment");
                attribute.setValue(card.getEstablishment());
                Card.setAttributeNode(attribute);


                Element User = doc.createElement("User");
                attribute = doc.createAttribute("Name");
                attribute.setValue(card.getUser().getName());
                User.setAttributeNode(attribute);

                attribute = doc.createAttribute("Surname");
                attribute.setValue(card.getUser().getSurname());
                User.setAttributeNode(attribute);

                attribute = doc.createAttribute("Sex");
                attribute.setValue(card.getUser().getSex());
                User.setAttributeNode(attribute);

                attribute = doc.createAttribute("birthdayDate");
                attribute.setValue(String.valueOf(card.getUser().getBirthday()));
                User.setAttributeNode(attribute);
                Card.appendChild(User);
                rootElement.appendChild(Card);
            }

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        return doc;
    }

    public static void XMLSave(Document doc, String fileName) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "Windows-1251");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(fileName.trim()));
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        System.out.println("File " + fileName.trim() + " saved!");
    }
}