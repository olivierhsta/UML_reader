package models;

import java.util.ArrayList;

public abstract class ModelDeclaration {
	
	protected ArrayList<String> lines = new ArrayList<>();

	public void addLine(String line) {
		this.lines.add(line);
	}
	
	public int getLineCount() {
		return this.lines.size();
	}
	
	public abstract void parseLines();

}
