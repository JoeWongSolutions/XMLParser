/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlvisual;

import java.io.File;
import java.util.Stack;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author joewong
 */
public class XMLTreeLoader {
    static XMLNode root;
    public static XMLNode load(File xmlCourseFile) throws Exception {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
            DefaultHandler handler = new DefaultHandler() {
                Stack<XMLNode> stack;
                XMLNode currentNode = null;
                String currentElementName = null;
                String currentElementData = "";
                
                @Override
                public void startDocument(){
                    root = null;
                    stack = new Stack<>();
                }
                
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    XMLNode newNode = new XMLNode(qName);
                    int attLength = attributes.getLength();
                    for(int i = 0; i < attLength; i++){
                        newNode.setAttributes(attributes.getQName(i), attributes.getValue(i));
                    }
                    stack.push(newNode);
                    
                    if (currentNode != null){
                        currentNode.addProperty(qName, newNode);
                    }
                    
                    currentNode = newNode;
                }
                
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if(stack != null){
                        XMLNode poppedNode = stack.pop();
                        poppedNode.setContent(poppedNode.getContent().trim());
                        if(stack.empty()){
                            root = poppedNode;
                            currentNode = null;
                        } else {
                            currentNode = stack.lastElement();
                        }
                    }
                }
                
                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    currentNode.setContent(currentNode.getContent() + String.valueOf(ch, start, length));
                }
                
                
            };
            
            saxParser.parse(xmlCourseFile.getAbsoluteFile(), handler);
            
        } catch (Exception e) {
            throw e;
        }
        
      return root; 
    }
}
