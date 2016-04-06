package tp.pr4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.cli.*;


import tp.pr4.cityLoader.CityLoaderFromTxtFile;
import tp.pr4.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr4.gui.MainWindow;

public class Main {

	public static void main(String[] args) throws Exception{
	 	
		Options options = new Options();
		options.addOption("h", "help", false, "Shows this help message");
		options.addOption("i", "interface", true, "The type of interface: console or swing");
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
            					MainWindow window = new MainWindow(robot);
            					robot.startGraphicGame();
            					robot.startEngine();
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
            					robot.startEngine();
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
         
            			}
            			else
            			{
            			
            				System.err.println("Wrong type of interface");
							System.exit(1);
            				
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
    	System.exit(0);
	}
	
	
	/**
	 * Prints the help
	 */
	public static void help(){
		
		System.out.println("Execute this assignment with these parameters:"
			+	"\nusage: tp.pr4.Main [-h] [-i <type>] [-m <mapfile>]"
			+	"\n -h,--help               Shows this help message"
			+	"\n -i,--interface <type>   The type of interface: console or swing"
			+	"\n -m,--map <mapfile>      File with the description of the city");
	}
}