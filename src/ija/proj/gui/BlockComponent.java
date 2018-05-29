package ija.proj.gui;

import java.awt.Component;
import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.JComponent;
import ija.proj.*;
import ija.proj.block.Block;
import javafx.scene.shape.Line;




public class BlockComponent implements Serializable
{
	private static int createdBlocks = 0;
	private static int existingBlocks = 0;
	private Block block;
	private Component component;
    private Line line = null;
    private Rectangle outputRect;
    private Rectangle inputRect0;
    private Rectangle inputRect1;

	public BlockComponent(Block block, Component component)
	{
		super();
		this.block = block; 
		this.createdBlocks++;
		this.existingBlocks++;
		this.component = component;
		this.inputRect0 = new Rectangle(component.getX(), component.getY(), 22, 20);
		this.inputRect1 = new Rectangle(component.getX(), component.getY(), 22, 20);
		this.outputRect = new Rectangle(component.getX(), component.getY(), 22, 20);
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

	public Line getLine()
	{
		return line;
	}

	public void setLine(Line line)
	{
		this.line = line;
	}

	public Rectangle getOutputRect()
	{
		return outputRect;
	}

	public Rectangle getInputRect0()
	{
		return inputRect0;
	}

	public Rectangle getInputRect1()
	{
		return inputRect1;
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
