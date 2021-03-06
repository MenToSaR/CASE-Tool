import de.database.DataKnot;
import de.database.InOuter;

import javax.xml.stream.*;
import java.io.*;
import java.util.HashMap;

/**
 * Created by Marcel on 20.04.2015.
 */
public class XMLPorter extends InOuter {

    /**
     * Klasse und DataKnot in XML Datei zu exportieren oder importieren
     * @param pFile
     * @param pData
     */

    public void write(String pFile, DataKnot pData) {
        FileOutputStream theOS = null;
        XMLStreamWriter theWriter = null;
        try {
            XMLOutputFactory theFactory = XMLOutputFactory.newInstance();
            theOS = new FileOutputStream(pFile);
            theWriter = theFactory.createXMLStreamWriter(theOS, "UTF-16");

            theWriter.writeStartDocument("UTF-16", "1.0");
            theWriter.writeCharacters("\n");

            appendNode(theWriter, pData, 1);

            theWriter.writeEndDocument();

            theWriter.close();
            theOS.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                theWriter.close();
                theOS.close();
            } catch (Exception e1) {
            }
        }
    }

    public void appendNode(XMLStreamWriter pWriter, DataKnot pKnot, int pLevel) throws XMLStreamException {
        pWriter.writeStartElement(pKnot.getTag());
        HashMap<String, String> tempMap = pKnot.getData();
        for (String eachKey : tempMap.keySet()) {
            pWriter.writeAttribute(eachKey, tempMap.get(eachKey));
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
                    DataKnot tempKnotElement = pKnot.addChild(pReader.getLocalName());
                    for (int i = 0; i < pReader.getAttributeCount(); i++) {
                        tempKnotElement.addData(pReader.getAttributeLocalName(i), pReader.getAttributeValue(i));
                    }
                    pReader.next();
                    interpreteKnot(pReader, tempKnotElement);
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

    public DataKnot read(String pFile) throws FileNotFoundException {
        if (!new File(pFile).exists()) {
            throw new FileNotFoundException();
        }

        DataKnot tempKnot = new DataKnot("NULL");
        FileInputStream theIS = null;
        XMLStreamReader theReader = null;
        try {
            XMLInputFactory theFactory = XMLInputFactory.newInstance();
            theIS = new FileInputStream(pFile);
            theReader = theFactory.createXMLStreamReader(theIS, "UTF-16");

            interpreteKnot(theReader, tempKnot);

            theReader.close();
            theIS.close();
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                theReader.close();
                theIS.close();
            } catch (Exception e1) {
            }
        }

        if (tempKnot.getChildren().size() > 1) {
            System.err.println("XML FILE PARSING ERROR");
        }

        if (tempKnot.getChildren().size() > 0) {
            return tempKnot.getChildren().get(0);
        } else {
            return null;
        }
    }
}
