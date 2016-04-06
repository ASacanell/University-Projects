package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;


import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import tp.pr5.Constants;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;

public class RobotPanel extends JPanel implements InventoryObserver{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyTableModel modelo;
	private JLabel fuel;
	private JLabel recycled;
	private JTable tabla;
	
	
	public RobotPanel(){
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder("Robot info"));

		
		//-------------Robot Info----------------------
		String[] columnas = {"Id", "Description"};
		
		this.modelo = new MyTableModel();
		this.modelo.addColumn(columnas[0]);
		this.modelo.addColumn(columnas[1]);
		
		this.tabla = new JTable(this.modelo);
		JScrollPane panel = new JScrollPane(this.tabla);
		panel.setPreferredSize(new Dimension(450, 110));
		this.add(panel, BorderLayout.SOUTH);
		
		this.fuel = new JLabel();
		this.recycled = new JLabel();
		
		JPanel status = new JPanel();
		this.add(status, BorderLayout.NORTH);
		
		status.add(this.fuel);
		status.add(this.recycled);

		this.updateRobotHealth(Constants.INITIAL_FUEL);
		this.updateRobotScore(Constants.INITIAL_MATERIAL);
		//-----------------------------------------------
		
	}
	
	//Sets the robot's health in the player info.
	public void updateRobotHealth(int robotFuel){
		
		this.fuel.setText("Fuel: "+ robotFuel);
	}
	
	//Sets the robot's score in the player info.
	public void updateRobotScore(int robotRecycled){
		this.recycled.setText("Recycled: " + robotRecycled);
	}
	
	public JTable getTabla() {
		return tabla;
	}
	
	public void setTabla(JTable tabla) {
		this.tabla = tabla;
	}
		
	public void outOfFuel() {
		JOptionPane.showMessageDialog(this, "You run out of fuel");
	}
		
	public void inventoryUpdate(ArrayList<Item> inventory) {
		int size = this.modelo.getRowCount();
		for(int i = 0; i < size;i++){
			this.modelo.removeRow(0);// Tiene que quitar siempre el primero !!!
		}
//		for(int i = 0; i < inventory.numberOfItems(); i++){
//			inventory.orderList();
//			String[] item = {inventory.getIt(i).getId(), inventory.getIt(i).getDescription()};
//			this.modelo.addRow(item);
//		}
		
		for(int i = 0; i < inventory.size();i++){
			String[] item = {inventory.get(i).getId(), inventory.get(i).getDescription()};
			this.modelo.addRow(item);
		}
	}
	
	public class MyTableModel extends DefaultTableModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable (int row, int column){
		       return false;
		   }
		
	}

	
	public void inventoryChange(ArrayList<Item> inventory) {
		this.inventoryUpdate(inventory);
		
	}

	@Override
	public void inventoryScanned(String inventoryDescription) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemScanned(String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemEmpty(String itemName) {
		// TODO Auto-generated method stub
		
	}
}
