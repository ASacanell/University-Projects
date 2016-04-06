package tp.pr4.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import tp.pr4.Constants;
import tp.pr4.items.ItemContainer;

public class RobotPanel extends JPanel{

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

		this.updateRobotFuel(Constants.INITIAL_FUEL);
		this.updateRobotRecycled(Constants.INITIAL_RECYCLED);
		//-----------------------------------------------
		
	}
	
	/**
	 * Sets the robot's fuel in the robot info.
	 * @param robotFuel
	 */
	public void updateRobotFuel(int robotFuel){
		
		this.fuel.setText("Fuel: "+ robotFuel);
	}
	
	/**
	 * Sets the robot's score in the robot info.
	 * @param robotRecycled
	 */
	public void updateRobotRecycled(int robotRecycled){
		this.recycled.setText("Recycled: " + robotRecycled);
	}
	
	public JTable getTabla() {
		return tabla;
	}
	
	public void setTabla(JTable tabla) {
		this.tabla = tabla;
	}
		
	
	/**
	 * Refresh the table info
	 * @param inventory the inventory that is going to update
	 */
	public void inventoryUpdate(ItemContainer inventory) {
		int size = this.modelo.getRowCount();
		for(int i = 0; i < size;i++){
			this.modelo.removeRow(0);// Tiene que quitar siempre el primero !!!
		}
		for(int i = 0; i < inventory.numberOfItems(); i++){
			inventory.orderList();
			String[] item = {inventory.getIt(i).getId(), inventory.getIt(i).getDescription()};
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
}
