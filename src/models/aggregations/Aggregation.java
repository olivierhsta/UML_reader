package models.aggregations;

import java.util.ArrayList;

/**
 * Class contained in each classes, with the information to be displayed
 * 
 * @author franc
 *
 */
public class Aggregation {
	
	private String name = "P_";
	private String details = "";

	public Aggregation(ArrayList<String> lines) {
		ArrayList<String> parts = new ArrayList<>();
		Boolean isPart = false;
		for (String line : lines) {
			this.details += line + "\n";
			if (!isPart) {
				if (line.contains("PARTS")) {
					isPart = true;
				}
			} else {
				if (line.contains("CLASS")) {
					parts.add(line.replace("CLASS", "").trim().split(" ")[0]);
				}
			}
		}

//		Generate name
		for (int i = 0 ; i < parts.size() ; i++) {
			this.name += parts.get(i);
			
			if (i + 1 < parts.size()) {
				this.name += "_";
			}
		}
	}
	
	public String getName() {
		return this.name;
	}

	public String getDetails() {
		return this.details;
	}

}
