package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import controllers.UMLController;
import java.util.HashMap;
import main.CSVMaker;

public class UMLDecoder
{
	private File file;
	private UMLController controller;
        private static final HashMap<String, String> metricsDescription = createMetricsDescriptions();
        private static HashMap<String, String> createMetricsDescriptions()
        {
            HashMap<String,String> tempMap = new HashMap<String,String>();
            tempMap.put("ANA", "Nombre moyen d’arguments des méthodes locales pour la classe");
            tempMap.put("NOM", "Nombre de méthodes locales/héritées de la classe. Dans le cas où une méthode est héritée et redéfinie localement (même nom, même ordre et types des arguments et même type de retour), elle ne compte qu’une fois");
            tempMap.put("NOA", "Nombre d’attributs locaux/hérités de la classe");
            tempMap.put("ITC", "Nombre de fois où d’autres classes du diagramme apparaissent comme types des arguments des méthodes de la classe");
            tempMap.put("ETC", "Nombre de fois où la classe apparaît comme type des arguments dans les méthodes des autres classes du diagramme");
            tempMap.put("CAC", "Nombre d’associations (incluant les agrégations) locales/héritées auxquelles participe la classe");
            tempMap.put("DIT", "Taille du chemin le plus long reliant la classe à une classe racine dans le graphe d’héritage");
            tempMap.put("CLD", "Taille du chemin le plus long reliant la classe à une classe feuille dans le graphe d’héritage");
            tempMap.put("NOC", "Nombre de sous-classes directes de la classe");
            tempMap.put("NOD", "Nombre de sous-classes directes et indirectes de la classe");
            return tempMap;
        }
	
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
        
        public String getMetricDescription(String metricName){
            return metricsDescription.get(metricName);
        }
	
}
