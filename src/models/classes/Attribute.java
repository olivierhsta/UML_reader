package models.classes;

import java.util.ArrayList;

/**
 * Class representing a class attribute
 * Has
 * 		name
 * 		type
 * 
 * @author franc
 *
 */
public class Attribute {
	
	private String name;
	private String type;
	
	
	public Attribute(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}


	/**
	 * Static function used to parse a line and extract the attributes from it
	 * 
	 * @param line String with the line to parse
	 * @return List of Attributes contained in the line
	 */
	public static ArrayList<Attribute> extractAttributesFromString(String line) {
		ArrayList<Attribute> attributes = new ArrayList<>();
		
		String[] strAttr = line.split(",");
		
		for (String attr : strAttr) {
			String[] attrParams = attr.split(":");
			if (attrParams.length == 2) {
				attributes.add(new Attribute(attrParams[0].trim(), attrParams[1].trim()));
			}
		}
		
		return attributes;
	}
        
        public String getDetails(){
            return this.type;
        }

}
