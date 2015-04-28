package de.database.manager.xml;

import de.database.DataKnot;
import de.database.InOuter;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

/**
 * Created by Marcel on 20.04.2015.
 */
public class XMLPorter extends InOuter {

    @Override
    public void write(String pFile, DataKnot pData) {
        try {
            XMLOutputFactory theFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter theWriter = theFactory.createXMLStreamWriter(new FileOutputStream(pFile) );

            theWriter.writeStartDocument();
            theWriter.writeCharacters("\n");

            appendNode(theWriter, pData, 1);

            theWriter.writeEndDocument();

            theWriter.close();
        } catch (XMLStreamException e) {
        e.printStackTrace();
        } catch (FileNotFoundException e) {
        e.printStackTrace();
        }
    }

    public void appendNode(XMLStreamWriter pWriter, DataKnot pKnot, int pLevel) throws XMLStreamException {
        pWriter.writeStartElement(pKnot.getTag());
        HashMap<String, String> tempMap = pKnot.getData();
        for (String eachKey : tempMap.keySet()) {
            if (!eachKey.equals("Tag")) {
                pWriter.writeAttribute(eachKey, tempMap.get(eachKey));
            }
        }
        pWriter.writeCharacters(pKnot.getValue());
        if (pKnot.getChildren().size() > 0) {
            pWriter.writeCharacters("\n");
        }
        for (DataKnot eachKnot : pKnot.getChildren()) {
            for (int i = 0; i < pLevel; i++) {
                pWriter.writeCharacters("\t");
            }
            pLevel++;
            appendNode(pWriter, eachKnot, pLevel);
            pLevel--;
            pWriter.writeCharacters("\n");
        }
        if (pKnot.getChildren().size() > 0) {
            for (int i = 0; i < pLevel - 1; i++) {
                pWriter.writeCharacters("\t");
            }
        }
        pWriter.writeEndElement();
    }

    public void interpreteKnot(XMLStreamReader pReader, DataKnot pKnot) throws XMLStreamException {
        while (pReader.hasNext()) {
            switch (pReader.getEventType()) {
                case XMLStreamConstants.START_DOCUMENT:
                    break;

                case XMLStreamConstants.END_DOCUMENT:
                    pReader.close();
                    return;

                case XMLStreamConstants.NAMESPACE:
                    System.err.println("NAMESPACE FOUND BUT NOT INTERPRETET");
                    break;

                case XMLStreamConstants.START_ELEMENT:
                    DataKnot tempKnot = pKnot.addChild(pReader.getLocalName());
                    for (int i = 0; i < pReader.getAttributeCount(); i++) {
                        tempKnot.addData(pReader.getAttributeLocalName(i), pReader.getAttributeValue(i));
                    }
                    pReader.next();
                    interpreteKnot(pReader, tempKnot);
                    break;

                case XMLStreamConstants.CHARACTERS:
                    if (!pReader.isWhiteSpace()) {
                        pKnot.setValue(pReader.getText());
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    return;

                default:
                    System.err.println("WRONG PARAMETER FOUND IN XML PARSER" + pReader.getText());
                    break;
            }
            pReader.next();
        }
    }

    @Override
    public DataKnot read(String pFile) {
        DataKnot tempKnot = new DataKnot("NULL");
        try {
            XMLInputFactory theFactory = XMLInputFactory.newInstance();
            XMLStreamReader theReader = theFactory.createXMLStreamReader(new FileInputStream(pFile));

            interpreteKnot(theReader, tempKnot);

        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (tempKnot.getChildren().size() > 1) {
            System.err.println("XML FILE PARSING ERROR");
        }

        return tempKnot.getChildren().get(0);
    }
}
