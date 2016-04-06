package tp.pr5.items;

import java.util.ArrayList;

public interface InventoryObserver {
	
	/**
	 * Notifies that the container has changed
	 * @param robotContainer - New inventory
	 */
	void inventoryChange(ArrayList<Item> inventory);
	
	/**
	 * Notifies that the user requests a SCAN instruction over the inventory.
	 * @param inventoryDescription - Information about the inventory
	 */
	void inventoryScanned(java.lang.String inventoryDescription);
	
	/**
	 * Notifies that the user wants to scan an item allocated in the inventory
	 * @param description - Item description
	 */
	void itemScanned(java.lang.String description);
	
	/**
	 * Notifies that an item is empty and it will be removed from the container. An invocation to the inventoryChange method will follow.
	 * @param itemName - Name of the empty item
	 */
	void itemEmpty(java.lang.String itemName);	
	
}
