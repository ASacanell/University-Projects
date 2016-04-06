package tp.pr3.cityLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import tp.pr3.Direction;
import tp.pr3.Place;
import tp.pr3.Street;
import tp.pr3.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr3.items.*;

import tp.pr3.*;

public class CityLoaderFromTxtFile {
		
	private Place[] places;
	private Street[] streets;
	private BufferedReader buffer;
	static final  int MAXPLACES = 10;
	static final int MAXSTREETS = 11;


	public CityLoaderFromTxtFile(){
		this.places= new Place[MAXPLACES];
		this.streets = new Street[MAXSTREETS];
		this.buffer = null;
	}
	
	/**
	 * 
	 * @param file The input stream where the city is stored
	 * @return The city
	 * @throws IOException When there is some format error in the file (WrongCityFormatException) or some errors in IO operations.
	 */
	public City loadCity(InputStream file) throws IOException{
			
		try
		{
			this.buffer = new BufferedReader(new InputStreamReader(file));
			if(buffer.readLine().equals("BeginCity")){
				
				this.places = this.createPlaces();
				this.streets = this.createStreets();
				this.places = this.createItems(this.places);
					
			}
			else {
				throw new IOException();
			}
		}catch (Exception e) {

			throw new WrongCityFormatException();
		}
		
		

		City city = new City(this.streets);
		return city;	
	}
	
	
	/**
	 * Returns the place where the robot will start the simulation
	 * @return The initial place
	 */
	public Place getInitialPlace(){
		return places[0];
	}
	
	
	private Place[] createPlaces() throws WrongCityFormatException,IOException{
		
		Place[] placesArray = new Place[MAXPLACES];
		String aux = buffer.readLine();
		boolean spaceship = false;
		int cont = 0;
		
		if(aux.equals("BeginPlaces"))
		{
			aux = buffer.readLine();
			while (!aux.equals("EndPlaces")){
				String[] words = aux.split(" ");
				
				if(words[0].equals("place") && Integer.parseInt(words[1]) == cont){
					if(words[words.length-1].equals("noSpaceShip")){
						spaceship = false;
					}else{
						spaceship = true;
					}
						
					String descripcion = new String();
						
					for(int i = 3; i < (words.length-1);i++){
						descripcion += words[i];
						if(i<words.length-2)	//Para añadir espacios entre las palabras de la descripcion excepto en la ultima palabra
							descripcion += " ";
					}
						
					placesArray[cont] = new Place(words[2],spaceship, descripcion.replace('"'+"", "").replaceAll("_", " "));
				}
				else
				{
					throw new WrongCityFormatException();
				}
				cont++;
				aux = buffer.readLine();	
			}
		}		
		return placesArray;
	}
	
	private Street[] createStreets() throws WrongCityFormatException,IOException{
		
		Street[] streetsArray = new Street[MAXSTREETS];
		String aux = buffer.readLine();
		int contS = 0;
		
		
		if(aux.equals("BeginStreets")){ 
			
			aux = buffer.readLine();
			
			while(!aux.equals("EndStreets") || aux == null){
				
				String[] words = aux.split(" ");
				
				if(words[0].equals("street") && Integer.parseInt(words[1]) == contS){
				
					if(words[7].equals("open")){
						streetsArray[contS] = new Street(this.places[Integer.parseInt(words[3])], this.toDirec(words[4]), this.places[Integer.parseInt(words[6])]);
					}else{
						streetsArray[contS] = new Street(this.places[Integer.parseInt(words[3])], this.toDirec(words[4]), this.places[Integer.parseInt(words[6])], false, words[8]);
					}
				}
				else
				{
					throw new WrongCityFormatException();
				}
				contS++;
				aux = buffer.readLine();
			}
		
		}
		return streetsArray;
	}
	
	private Place[] createItems(Place[] placesArray) throws WrongCityFormatException,IOException {
		
		String aux = buffer.readLine();
		int cont = 0;		
		
		if(aux.equals("BeginItems")){
			
			aux = buffer.readLine();
			
			while(!aux.equals("EndItems") || aux == null){
				
				String[] words = aux.split(" ");
				
				if(words[0].equals("fuel") && Integer.parseInt(words[1]) == cont){ 
				
					String descripcion = new String();
					for(int i = 3; i < (words.length-1);i++){
						descripcion += words[i];
						if(i<words.length-2)	//Para añadir espacios entre las palabras de la descripcion excepto en la ultima palabra
							descripcion += " ";
					}
											
					Item it = new Fuel(words[2], descripcion.replace('"'+"", "").replaceAll("_", " "), Integer.parseInt(words[words.length-4]), Integer.parseInt(words[words.length-3]));
													
					placesArray[Integer.parseInt(words[words.length-1])].addItem(it);
					
				}else if(words[0].equals("codecard") && Integer.parseInt(words[1]) == cont){
					
					String descripcion = new String();
					for(int i = 3; i < (words.length-1);i++){
						descripcion += words[i];
						if(i<words.length-2)	//Para añadir espacios entre las palabras de la descripcion excepto en la ultima palabra
							descripcion += " ";
					}
					
					Item it = new CodeCard(words[2], descripcion.replace('"'+"", "").replaceAll("_", " "),words[words.length-3]);
					
					placesArray[Integer.parseInt(words[words.length-1])].addItem(it);
					
				}else if(words[0].equals("garbage") && Integer.parseInt(words[1]) == cont){
					
					String descripcion = new String();
					for(int i = 3; i < (words.length-1);i++){
						descripcion += words[i];
						if(i<words.length-2)	//Para añadir espacios entre las palabras de la descripcion excepto en la ultima palabra
							descripcion += " ";
					}
					
					Item it = new Garbage(words[2], descripcion.replace('"'+"", "").replaceAll("_", " "), Integer.parseInt(words[words.length-3]));
					placesArray[Integer.parseInt(words[words.length-1])].addItem(it);
				}else
					throw new WrongCityFormatException();
				
				aux = buffer.readLine();
				cont++;						
		
			}

		}
		return placesArray;
	}
	
	
	private Direction toDirec(String words){
		Direction direc = Direction.UNKNOWN;
		
		if(words.equalsIgnoreCase("north"))
			direc = Direction.NORTH;
		else if(words.equalsIgnoreCase("south"))
			direc = Direction.SOUTH;
		else if(words.equalsIgnoreCase("east"))
			direc = Direction.EAST;
		else if(words.equalsIgnoreCase("west"))
			direc = Direction.WEST;
		
		return direc;
	}

}
