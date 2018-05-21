package ija.proj.gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import ija.proj.block.Block;
import ija.proj.block.Port;

public class MainPanel extends JPanel
{
	int x1;
	int y1;
	int x2;
	int y2;
	public MainPanel()
	{
		setLayout(null);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (BlockComponent blockComponent : MainFrame.getBlockComponents())
		{
			blockComponent.getBlock().setxLocation(blockComponent.getComponent().getX());
			blockComponent.getBlock().setyLocation(blockComponent.getComponent().getY());
		//	System.out.print(blockComponent.getComponent().getX() + "     " + blockComponent.getComponent().getY() + "\n");
		}
		
		for (BlockComponent blockComponent : MainFrame.getBlockComponents())
		{
			for (Port currentPort : blockComponent.getBlock().getOutputPorts())
			{
				Port connectedPort = currentPort.getConnectedPort();
				if (connectedPort != null)
				{
					Block connectedBlock = connectedPort.getOwnerBlock();
					x1 = currentPort.getOwnerBlock().getxLocation();
					y1 = currentPort.getOwnerBlock().getyLocation();
					x2 = connectedPort.getOwnerBlock().getxLocation();
					y2 = connectedPort.getOwnerBlock().getyLocation();
				//	System.out.print("x1: " + x1 + " y1: " + y1 + " x2: " + x2 + " y2:" + y2 + "\n");
					g.drawLine(x1+64, y1+67, x2+64, y2+67);
					
				}
				
			}
			
		}
		
	//	repaint();
	}

}
