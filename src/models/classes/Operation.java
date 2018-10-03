package models.classes;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class representing an operation attribute for a class
 * @author franc
 *
 */
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
        
        public String getDetails(){
            String details = this.type;
            for (Attribute parameter : parameters){
                if (details.equals(this.type)){
                    details += "\nParameters : " + parameter.getName() + "(" + parameter.getType() + ")\n";
                } else {
                    details += "    " + parameter.getName() + "(" + parameter.getType() + ")\n";
                }
            }
            return details;
        }


	/**
	 * Static function used to parse a string and extract the operation from it
	 * 
	 * @param str String to parse
	 * @return attribute contained in the line | return null if nothing was found
	 */
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
