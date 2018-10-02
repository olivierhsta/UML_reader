package models;

import java.util.ArrayList;

/**
 * Abstract class parent of everything to be parsed inside a model
 * 
 * Used to hold the name of things, and its lines to be parsed. Also used to force the implementation
 * of parsedLines() custom to each elements.
 * 
 * @author franc
 *
 */
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

	/**
	 * Concat every lines in one string.
	 * @return string of every lines
	 */
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

	/**
	 * Must implement a custom parse for each model declaration.
	 * 
	 * This function will be called by the model once it knows that a declaration has all its lines
	 */
	public abstract void parseLines();
	
	@Override
	public abstract String toString();

}
