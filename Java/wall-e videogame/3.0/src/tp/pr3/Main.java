package tp.pr3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import tp.pr3.cityLoader.CityLoaderFromTxtFile;
import tp.pr3.cityLoader.cityLoaderExceptions.WrongCityFormatException;

public class Main {

	
    public static void main(String[] args){
	  	
    	try{
    		FileInputStream file = new FileInputStream(args[0]);
 			CityLoaderFromTxtFile loader = new CityLoaderFromTxtFile();
    		City citymap = loader.loadCity(file);
    		Place initialPlace = loader.getInitialPlace();
    		RobotEngine robot = new RobotEngine(citymap, initialPlace, Direction.NORTH);	//Crea una instancia de robotEngine, que sera nuestro Wall-E
    		robot.startEngine();
    		System.exit(0);

    	}catch(ArrayIndexOutOfBoundsException exc){
			String LINE_SEPARATOR = System.getProperty("line.separator");
			System.err.println("Bad params."+LINE_SEPARATOR+"Usage: java tp.pr3.Main <mapfile>"+LINE_SEPARATOR+LINE_SEPARATOR+"<mapfile> : file with the description of the city.");
			System.exit(1);
    	}
    	catch(FileNotFoundException exc){ // Wrong map file
			
			System.err.println("Error reading the map file: " + args[0] + " (No existe el fichero o el directorio)");
			System.exit(2);
				
		}
    	catch(WrongCityFormatException exc){ // Wrong map format
			System.err.println("Error reading the map file: " + args[0] + " (Formate del mapa incorrecto)");
			System.exit(2);		
		} catch (IOException e) {
			System.err.println("Error reading the map file: " + args[0] + " (no existe el fichero o el directorio)");
			System.exit(2);
		}
    }
}	


