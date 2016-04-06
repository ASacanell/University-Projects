package tp.pr1;

public class Place 
{

	private String descripcion;
	private boolean isSpaceShip;
	private String name;
	
		//Constructor
	public Place(String extName, boolean extIsSpaceShip, String extDescription){
		this.name=extName;
		this.descripcion=extDescription;
		this.isSpaceShip=extIsSpaceShip;
		
	}
	
		/**
		 * Metodo que comprueba si un lugar es SpaceShip
		 * @return Booleano indicando si el lugar es SpaceShip(fin de juego)
		 */
	public boolean isSpaceship()
	{
		return (this.isSpaceShip);
	}
	
		/**
		 * Metodo que nos devuelve un string con el nombre del lugar y su descripcion
		 */
	public String toString()
	{
		return this.name + "\n" + this.descripcion;	
	}

}

