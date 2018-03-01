/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlvisual;

import java.util.ArrayList;
import java.util.HashMap;
import org.xml.sax.Attributes;

/**
 *
 * @author joewong
 */
public class XMLNode {
    private String name = "";
    private String content = "";
    private HashMap<String, ArrayList<XMLNode>> properties = null;
    private Attributes attributes = null;
    
    public XMLNode(){
        
    }
    
    public XMLNode(String name, Attributes attributes){
        this.name = name;
        this.attributes = attributes;
    }
    
    public XMLNode(String name, String content, HashMap<String, ArrayList<XMLNode>> properties, Attributes attributes){
        this(name, attributes);
        this.content = content;
        this.properties = properties;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setContent(String content){
        this.content = content;
    }
    
    public String getContent(){
        return content;
    }
    
    
    
    public void addProperty(String name, XMLNode node){
        if (properties == null){
            properties = new HashMap<>();
        }
        this.properties.putIfAbsent(name, new ArrayList<>());
        this.properties.get(name).add(node);
    }
    
    public void deleteProperty(String name){
        if (properties == null){
            return;
        }
        this.properties.remove(name);
    }
    
    public boolean propertyExists(String name){
        if (properties == null){
            return false;
        }
        return this.properties.containsKey(name);
    }
    
    public ArrayList<XMLNode> getProperty(String name){
        return this.properties.get(name);
    }
    
    public HashMap<String, ArrayList<XMLNode>> getProperties(){
        return properties;
    }
    
    public void setAttributes(Attributes attributes){
        this.attributes = attributes;
    }
    
    public Attributes getAttributes(){
        return attributes;
    }
}
