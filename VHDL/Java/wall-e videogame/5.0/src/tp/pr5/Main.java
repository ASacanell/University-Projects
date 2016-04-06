package tp.pr5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.commons.cli.*;


import tp.pr5.cityLoader.CityLoaderFromTxtFile;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr5.console.Console;
import tp.pr5.console.ConsoleController;
import tp.pr5.gui.GUIController;
import tp.pr5.gui.MainWindow;

public class Main {

	public static void help(){
		
		System.out.println("Execute this assignment with these parameters:"
			+	"\nusage: tp.pr5.Main [-h] [-i <type>] [-m <mapfile>]"
			+	"\n -h,--help               Shows this help message"
			+	"\n -i,--interface <type>   The type of interface: console, swing or both"
			+	"\n -m,--map <mapfile>      File with the description of the city");
	}
	
	public static void main(String[] args) throws Exception{
	 	
		Options options = new Options();
		options.addOption("h", "help", false, "Shows this help message");
		options.addOption("i", "interface", true, "The type of interface: console, swing or both");
		options.addOption("m", "map", true, "File with the description of the city");
    	    	
    	
    	try {
            // parse the command line arguments
    		CommandLineParser parser = new BasicParser();
			CommandLine line;
			line = parser.parse(options, args);		
         
            if(line.hasOption("help"))
            {
            	
            	help();
            	System.exit(0);	
            	
            }
            
            else if(line.hasOption("map"))
            {
            	
            	if(!line.getOptionValues("map").equals(null))
            	{
            		
            		String mapfile = line.getOptionValue("map");
            		
            		if(line.hasOption("interface"))
            		{
            			
            			if(line.getOptionValue("interface").equals("swing"))
            			{
            				
            				try
            				{
            					FileInputStream file = new FileInputStream(mapfile);			
            					CityLoaderFromTxtFile loader = new CityLoaderFromTxtFile();
            					City citymap = loader.loadCity(file);
            					Place initialPlace = loader.getInitialPlace();
            					RobotEngine robot = new RobotEngine(citymap, initialPlace, Direction.NORTH);

            					GUIController gui = new GUIController(robot);
            					new MainWindow(gui);
								gui.startEngine();
            					
            					
            				}
            				catch(WrongCityFormatException wrongMap)
            				{
            				
            					System.err.println("Error reading the map file: "+ mapfile +" (No existe el fichero o el directorio)");
								System.exit(2);
            					
            				}
            				catch(FileNotFoundException notExist)
            				{
                				
            					System.err.println("Error reading the map file: "+ mapfile +" (No existe el fichero o el directorio)");
								System.exit(2);
            					
            				}
            				
            			}
            			else if(line.getOptionValue("interface").equals("console"))
            			{
            				
            				try
            				{
            				
            					FileInputStream file = new FileInputStream(mapfile);			
            					CityLoaderFromTxtFile loader = new CityLoaderFromTxtFile();
            					City citymap = loader.loadCity(file);
            					Place initialPlace = loader.getInitialPlace();
            					RobotEngine robot = new RobotEngine(citymap, initialPlace, Direction.NORTH);
            					
            					ConsoleController cons = new ConsoleController(robot); //Cargamos observadores de Console
								cons.startEngine(); //Comienza a escribir por pantalla
            					
								System.exit(0);
								
	
            				}
            				catch(WrongCityFormatException wrongMap)
            				{
            					
            					System.err.println("Error reading the map file: "+ mapfile +" (No existe el fichero o el directorio)");
								System.exit(2);
            					
            				}
            				catch(FileNotFoundException notExist)
            				{
                				
            					System.err.println("Error reading the map file: "+ mapfile +" (No existe el fichero o el directorio)");
								System.exit(2);
            					
            				}
         
            			}else if(line.getOptionValue("interface").equals("both")){
            				
            				try
            				{
            					
            					FileInputStream file = new FileInputStream(mapfile);			
            					CityLoaderFromTxtFile loader = new CityLoaderFromTxtFile();
            					City citymap = loader.loadCity(file);
            					Place initialPlace = loader.getInitialPlace();
            					RobotEngine robot = new RobotEngine(citymap, initialPlace, Direction.NORTH);
            					
            					GUIController gui = new GUIController(robot);
	            				new MainWindow(gui);
	            				gui.startEngine();
	            				
	            				
	            				
	            				ConsoleController cons = new ConsoleController(robot); 
	            				new Console();
	            				cons.startEngine(); // No se permiten comandos desde la consola en este modo.
	            				
            				}
	        				catch(WrongCityFormatException wrongMap)
	        				{
	        					
	        					System.err.println("Error reading the map file: "+ mapfile +" (No existe el fichero o el directorio)");
								System.exit(2);
	        					
	        				}
	        				catch(FileNotFoundException notExist)
	        				{
	            				
	        					System.err.println("Error reading the map file: "+ mapfile +" (No existe el fichero o el directorio)");
								System.exit(2);
	        					
	        				}		
            			}
            			else
            			{
            			
            				System.err.println("Wrong type of interface");
							System.exit(3);
            				
            			}
            		}
            		else
            		{
            			
            			System.err.println("Interface not specified");
						System.exit(1);
            			
            		}
            	}
              
            }
            else
            {
            
            	System.err.println("Map file not specified");
				System.exit(1);
            	
            }
            
    	}
    	catch(ParseException exc)
    	{
    		
    		help();
        	System.exit(1);	
    		
    	}
	}
}


