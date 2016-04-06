package tp.pr3.items;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Collections;

public class ItemContainer {

	private ArrayList <Item> ItemList; 

	/**
	 * Creates the empty container
	 */
	public ItemContainer(){
		this.ItemList = new ArrayList<Item>();
	};

	/**
	 * Returns the number of items contained
	 * @return the number of items in the container
	 */
	public int numberOfItems(){
		return this.ItemList.size();
	};

	/**
	 * Add an item to the container. The operation can fail, returning false
	 * @param extItem The name of the item to be added.
	 * @return true if and only if the item is added, i.e., an item with the same identifier does not exists in the container
	 */
	public boolean addItem(Item extItem){
		boolean insertado = false;
		String name = extItem.getId();

		if(!this.containsItem(name)){
			this.ItemList.add(extItem);
			insertado = true;
		}else {
			insertado = false;}

		return insertado;

	}

	/**
	 * Returns the item from the container according to the item name
	 * @param id Item name
	 * @return Item with that name or null if the container does not store an item with that name.
	 */
	public Item getItem(String id){
		int pos=0;
		boolean encontrado = false;
		Item it = null;
		if(numberOfItems()==0){
			return it;
		}else{
			while(!encontrado && pos<ItemList.size()){
				if(id.equalsIgnoreCase(this.ItemList.get(pos).getId())){
					it = this.ItemList.get(pos);
					encontrado = true;
				}
				pos++;
			}
			return it;}
	};

	/**
	 * Returns and deletes an item from the inventory. This operation can fail, returning null
	 * @param id Name of the item
	 * @return An item if and only if the item identified by id exists in the inventory. Otherwise it returns null
	 */
	public Item pickItem(String id){
		Item it = null;
		if(this.numberOfItems()==0){
			return it;
		}else{
			int pos = this.ItemList.indexOf(getItem(id));
			if (pos != -1){
				it = this.ItemList.get(pos);
				this.ItemList.remove(pos);
			}else{System.out.println("WALL·E says: Ooops, this place has not the object " + id);}
			return it;}
	};

	/**
	 * Generates a String with information about the items contained in the container. Note that the items must appear sorted but the item name.
	 */
	public String toString()
	{
		orderList();
		String line ="";

		ListIterator<Item> litr = this.ItemList.listIterator();
		String aux ="";

		while (litr.hasNext()) {
			Item element = litr.next();
			aux= "   " + element.getId();
			if(litr.hasNext())
			{
				aux += "\n";
			}
			line = line.concat(aux);
		}
		return line;
	}

	/**
	 * Method that removes an item from the Itemlist
	 */
	public void removeItem(Item item){
		this.ItemList.remove(item);
	}

	/**
	 * Method that sorts the itemlist
	 */
	public void orderList(){
		Collections.sort(this.ItemList);
	}

	/**
	 * Checks if the Item with this id exists in the container.
	 * @param id Name of the item.
	 * @return true if the container as an item with that name.
	 */
	public boolean containsItem(String id)
	{
		boolean encontrado = false;
		for(int pos = 0; pos<this.ItemList.size();pos++)
		{
			if(this.ItemList.get(pos).getId().equalsIgnoreCase(id))
			{
				encontrado = true;
			}
		}
		return encontrado;
	}
	
	/**
	 * Use an item, removing the item from the inventory if the item can not be used any more
	 * @param item to be used
	 */
	public void useItem(Item extItem)
	{
		if(!extItem.canBeUsed()){
			this.removeItem(extItem);
			//this.pickItem(extItem.getId());
		}
	}
}
