package models;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operation {
	
	private String name;
	private ArrayList<Attribute> parameters = new ArrayList<>();
	private String type;
	
	public Operation(String name, ArrayList<Attribute> parameters, String type) {
		super();
		this.name = name;
		this.parameters = parameters;
		this.type = type;
	}


	public String getName() {
		return name;
	}



	public ArrayList<Attribute> getParameters() {
		return parameters;
	}



	public String getType() {
		return type;
	}



	public static Operation extractOperationFromString(String str) {
			
		Pattern p = Pattern.compile("\\((.*)\\)");
		Matcher m = p.matcher(str);
		
		if (m.find()) {
			
			String[] declType = str.replace(m.group(0), "").split(":");
			if (declType.length == 2) {

				String name = declType[0].replace(",", "").trim();
				String type = declType[1].replace(",", "").trim();
				
				ArrayList<Attribute> opAttributes = Attribute.extractAttributesFromString(m.group(1));
				
				return new Operation(name, opAttributes, type);
			}
		}
		
		return null;
	}

}
