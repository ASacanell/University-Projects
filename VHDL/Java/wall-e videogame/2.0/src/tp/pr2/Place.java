package tp.pr2;

public class Place 
{

	private String descripcion;
	private boolean isSpaceShip;
	private String name;
	private ItemContainer listOfItems;

	/**
	 * Creates the place. Initially the list of items is empty
	 * @param extName Place name
	 * @param extIsSpaceShip  Is it a spaceship?
	 * @param extDescription Place description
	 */
	public Place(String extName, boolean extIsSpaceShip, String extDescription){
		this.name=extName;
		this.descripcion=extDescription;
		this.isSpaceShip=extIsSpaceShip;
		this.listOfItems = new ItemContainer();

	}

	/**
	 * Is this place the space ship?
	 * @return true if the place represents a space ship
	 */
	public boolean isSpaceship()
	{
		return this.isSpaceShip;
	}

	/**
	 * Overrides toString method. Returns the place name, its description and the list of items contained in the place
	 */
	public String toString()
	{
		return this.name + "\n" + this.descripcion;	
	}

	/**
	 * Tries to pick an item characterized by a given identifier from the place. If the action was completed the item must be removed from the place.
	 * @param id The identifier of the item
	 * @return The item of identifier id if it exists in the place. Otherwise the method returns null
	 */
	public Item pickItem(String id){

		return this.listOfItems.pickItem(id);

	};

	/**
	 * Tries to add an item to the place. The operation can fail (if the place already contains an item with the same name)
	 * @param it The item to be added
	 * @return true if and only if the item can be added to the place, i.e., the place does not contain an item with the same name
	 */
	public boolean addItem(Item it){
		return this.listOfItems.addItem(it);


	};

	/**
	 * Getter
	 * @return the ItemContainer
	 */
	public ItemContainer getlistOfItems(){
		return this.listOfItems;
	}

	/**
	 * Setter
	 * @param listOfItems
	 */
	public void setlistOfItems(ItemContainer listOfItems){
		this.listOfItems = listOfItems;
	}

}

