/**
 * Frontend representation of block.
 * @brief Package for BlockComponent.
 * @file BlockComponent.java
 * @author Peter Havan (xhavan00)
 */

package ija.proj.gui;

import java.awt.Component;
import java.io.Serializable;

import javax.swing.JComponent;
import ija.proj.*;
import ija.proj.block.Block;




public class BlockComponent implements Serializable
{
	private static int createdBlocks = 0;
	private static int existingBlocks = 0;
	private Block block;
	private Component component;

	public BlockComponent(Block block, Component component)
	{
		super();
		this.block = block; 
		this.createdBlocks++;
		this.existingBlocks++;
		this.component = component;
	}
	
	public Block getBlock()
	{
		return this.block;
	}

	public void setBlock(Block block)
	{
		this.block = block;
	}

	public Component getComponent()
	{
		return this.component;
	}

	public void setComponent(Component component)
	{
		this.component = component;
	}
	
/*	public void updateTip()
	{
		((JComponent) component).setToolTipText("<html>"
				+"Input port 0:" + block.getInputPort(0).getValue("float") 
				+"<br>"
				+"Input port 1:" + block.getInputPort(0).getValue("float") 
				+"<br>"
				+"Output port 0:" + block.getOutputPort(0).getValue("float"));
	}*/
}
