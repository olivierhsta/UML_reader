package models;

import java.util.ArrayList;

public abstract class ModelDeclaration {
	
	protected String name;
	protected ArrayList<String> lines = new ArrayList<>();
	

	public ModelDeclaration(String name) {
		super();
		this.name = name;
	}

	public void addLine(String line) {
		this.lines.add(line);
	}
	
	public int getLineCount() {
		return this.lines.size();
	}
        
        public String getName(){
            return this.name;
        }
	
	public String joinLines() {
		String str = "";
		
		for (String line : this.lines) {
			str += line;
		}
		
		return str;
	}
        
        public String getDetails(){
            return this.joinLines();
        }
	
	public abstract void parseLines();
	@Override
	public abstract String toString();

}
