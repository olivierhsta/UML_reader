package models;

import java.util.ArrayList;

public class AggregationDeclaration extends ModelDeclaration {
	
	private String containerLine = "";

	public AggregationDeclaration() {
		super("");
		// TODO Auto-generated constructor stub
	}
	
	public void apply(ArrayList<Class> classes) {
		System.out.println("applying <" + this.containerLine + ">");
		String containerClassStr = this.containerLine.trim().split(" ")[1];
		
		for (Class containerClass : classes) {
			if (containerClass.name.equals(containerClassStr)) {
				containerClass.addAggregation(new Aggregation(this.lines));
			}
		}
	}

	@Override
	public void parseLines() {
		
		ArrayList<String> strParts = new ArrayList<>();
		Boolean isContainerLine = false;
		for (String line : this.lines) {
			if (!isContainerLine) {
				if (line.contains("CONTAINER")) {
					isContainerLine = true;
				}
			} else {
				this.containerLine = line;
				isContainerLine = false;
			}
		}
		

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
