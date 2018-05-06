package gui;

import java.awt.Component;

import javax.swing.JComponent;



public class BlockComponent 
{
	private static int createdBlocks = 0;
	private static int existingBlocks = 0;
	private Component blockComponent;
	
	public BlockComponent(Component component)
	{
		this.blockComponent = component; 
		this.createdBlocks++;
		this.existingBlocks++;
	}

}
