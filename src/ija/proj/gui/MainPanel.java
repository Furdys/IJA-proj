package ija.proj.gui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import javax.swing.JPanel;
import javax.swing.ToolTipManager;
import javax.swing.event.MouseInputAdapter;

import ija.proj.block.Block;
import ija.proj.block.Port;
import javafx.scene.shape.Line;

public class MainPanel extends JPanel
{
	int x1;
	int y1;
	int x2;
	int y2;
    //private Line line=new Line();
	public MainPanel()
	{
		
        addMouseMotionListener(new MouseMotionListener() {
            
            @Override
            public void mouseMoved(MouseEvent e) {
            	
            	for (BlockComponent blockComponent : MainFrame.getBlockComponents())
            	{
            		if (blockComponent.getLine() != null)
            		{
	            		if ( blockComponent.getLine().contains(e.getX(), e.getY() ))
	            		{
	            			System.out.println("contains");
	            			setToolTipText(blockComponent.getBlock().getOutputPort(0).print());
	            			break;
	            		}
	            		
	            		else
	            		{
	            			setToolTipText(null);
	            		}
            		}
            		
            	}

                ToolTipManager.sharedInstance().mouseMoved(e);
                ToolTipManager.sharedInstance().setDismissDelay(60000);
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                // TODO Auto-generated method stub
                 
            }
        });
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
			if (blockComponent.getLine() != null)
			{
			for (Port currentPort : blockComponent.getBlock().getOutputPorts())
			{
				Port connectedPort = currentPort.getConnectedPort();
				if (connectedPort != null)
				{
			        Graphics2D g2d = (Graphics2D) g;
					Block connectedBlock = connectedPort.getOwnerBlock();
					x1 = currentPort.getOwnerBlock().getxLocation();
					y1 = currentPort.getOwnerBlock().getyLocation();
					x2 = connectedPort.getOwnerBlock().getxLocation();
					y2 = connectedPort.getOwnerBlock().getyLocation();
				//	System.out.print("x1: " + x1 + " y1: " + y1 + " x2: " + x2 + " y2:" + y2 + "\n");
				//	g2d.drawLine(x1+64, y1+67, x2+64, y2+67);
					//Line line = blockComponent.getLine();
					blockComponent.getLine().setStartX(x1+64);
					blockComponent.getLine().setStartY(y1+67);
					blockComponent.getLine().setEndX(x2+64);
					blockComponent.getLine().setEndY(y2+67);
					Line line = blockComponent.getLine();
				    g2d.setStroke(new BasicStroke(3));
					g2d.drawLine((int) line.getStartX(), (int) line.getStartY(), (int) line.getEndX(), (int) line.getEndY());
				//	g2d.draw(rect);
				}
				
			}
			}

		}
		
	//	repaint();
	}

}
