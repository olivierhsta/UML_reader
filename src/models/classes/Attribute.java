package models.classes;

import java.util.ArrayList;

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

}
