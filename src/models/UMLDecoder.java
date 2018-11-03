package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import controllers.UMLController;
import main.CSVMaker;

public class UMLDecoder
{
	private File file;
	private UMLController controller;
	
	private ArrayList<Model> fileModels;

	public UMLDecoder(UMLController controller)
	{
		this.controller = controller;
	}
	
	public void setFile(File file)
	{
		this.file = file;
		this.parseFile();
	}
        
        public File getFile(){
            return this.file;
        }
	
	public void parseFile() {
		
		fileModels = new ArrayList<>();
		
		String line = null;
		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(this.file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String modelName = null;
            ArrayList<String> modelLines = new ArrayList<>();
            while((line = bufferedReader.readLine()) != null) {
            	
            	if (line.contains("MODEL")) {
            		if (modelName != null) {
            			fileModels.add(new Model(modelName, modelLines));
            		}
            		
        			modelName = line.replace("MODEL", "").trim();
        			modelLines = new ArrayList<>();
            	} else {
            		modelLines.add(line);
            	}
            	
            }
            
            if (modelName != null && !modelName.isEmpty())
            	fileModels.add(new Model(modelName, modelLines));
            

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + this.file.getName() + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + this.file.getName() + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
		finally {
		}
	}
        
        public ArrayList<Model> getUMLModels(){
            return this.fileModels;
        }
	
}
