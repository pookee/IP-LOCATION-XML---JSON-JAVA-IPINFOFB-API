package com.company;

import org.xml.sax.*;

        import javax.xml.parsers.SAXParser;
        import javax.xml.parsers.SAXParserFactory;
        import java.io.InputStream;
        import java.util.HashMap;
        import java.util.Map;

// Cette classe sert au traitement du XML (parsing etc)

public class AnalysingXML implements ContentHandler {

    private XMLReader ressource;
    private String lastNode;

    private Map<String, String> response = new HashMap<String, String>();

    public AnalysingXML(InputStream xmlString) {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader(); // On se prépare à parser le XML
            xr.setContentHandler(this);
            xr.parse(new InputSource(xmlString)); // on lance le parsage en donnant le flux xml récupéré auparavant
            this.ressource = xr;
            System.out.println("ci-dessous");
            System.out.println(this.ressource);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getResponse(){ // La Key représente la colonne associée dans le dictionnaire
        return this.response.toString();
    }

    public String response(String key){ // La Key représente la colonne associée dans le dictionnaire
        return this.response.get(key);
    }

    // les fonctions ci-dessous permettent de se déplacer dans le XML
    @Override
    public void setDocumentLocator(Locator locator) {

    }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startPrefixMapping(String s, String s1) throws SAXException {

    }

    @Override
    public void endPrefixMapping(String s) throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        lastNode = qName;
    }

    @Override
    public void endElement(String s, String s1, String s2) throws SAXException {
        lastNode = null;
    }

    @Override
    public void characters(char[] chars, int i, int i1) throws SAXException {
       if(lastNode!=null) {
            String data = new String(chars, i, i1).trim();
            response.put(lastNode, data);
        }
    }

    @Override
    public void ignorableWhitespace(char[] chars, int i, int i1) throws SAXException {

    }

    @Override
    public void processingInstruction(String s, String s1) throws SAXException {

    }

    @Override
    public void skippedEntity(String s) throws SAXException {

    }
}
