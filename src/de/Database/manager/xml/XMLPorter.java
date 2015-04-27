package de.database.manager.xml;

import de.database.DataKnot;
import de.database.InOuter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
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
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream(pFile) );

            writer.writeStartDocument();
            writer.writeCharacters("\n");

            appendNode(writer, pData, 1);

            writer.writeEndDocument();

            writer.close();
        } catch (XMLStreamException e) {
        e.printStackTrace();
        } catch (FileNotFoundException e) {
        e.printStackTrace();
        }
    }

    public void appendNode(XMLStreamWriter pWriter, DataKnot pKnot, int pLevel) throws XMLStreamException{
        pWriter.writeStartElement(pKnot.getTag());
        HashMap<String, String> tempMap = pKnot.getData();
        for (String eachKey : tempMap.keySet()) {
            if (!eachKey.equals("Tag")) {
                pWriter.writeAttribute(eachKey, tempMap.get(eachKey));
            }
        }
        pWriter.writeCData(pKnot.getValue());
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

    @Override
    public DataKnot read(String pFile) {
        return null;
    }
}
