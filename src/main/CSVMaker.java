package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import models.Model;

abstract public class CSVMaker {
	
	public static void export(ArrayList<Model> models, String fileName) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".csv"));
			
			// Print header
			writer.append("Model,Class,ANA,NOM,NOA,ITC,ETC,CAC,DIT,CLD,NOC,NOD");
			writer.newLine();

			for (Model model : models) {
				writer.append(model.getName());
				
				for (models.classes.Class classe : model.getClasses()) {
					String line = ",";
					line += classe.getName() + ","
							+ classe.getANA() + ","
							+ classe.getNOM() + ","
							+ classe.getNOA() + ","
							+ classe.getITC() + ","
							+ classe.getETC() + ","
							+ classe.getCAC() + ","
							+ classe.getDIT() + ","
							+ classe.getCLD() + ","
							+ classe.getNOC() + ","
							+ classe.getNOD();
					
					writer.newLine();
					writer.append(line);
				}
				
			}
			
			
			writer.close();
		} catch (IOException e) {
			
		}
	}

}
