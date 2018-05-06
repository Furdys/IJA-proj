package ija.proj.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.event.MouseInputAdapter;

import ija.proj.block.Port;
import ija.proj.block.Block;
import ija.proj.scheme.Scheme;

public class DragListener extends MouseInputAdapter implements ActionListener, Serializable
{
    private Point location;
    private MouseEvent pressed;
    private boolean afterPressing = false;
    private JMenuItem in0, in1, out0, setIn0, setIn1;
    private JPopupMenu menu;
    private Graphics2D graphics;
    private Point helper; // for getLocation()
    private Component currentComponent;
    private Block currentBlock;
    private boolean connecting = false;
    private Port currentPort;
    private JLabel infoBlock;
    private final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
 
    public void mousePressed(MouseEvent me)
    {
    	
    	if(!connecting)
    	{
	    	if (me.getButton() == MouseEvent.BUTTON1)
	    	{
	    		pressed = me;
	    		afterPressing = true;
	    	}
    	}
    	
    	else
    	{
    		Component component = me.getComponent();
    		connecting = false;
    		
    		menu = new JPopupMenu("Connection");
    		in0 = new JMenuItem("Connect to inputPort 0");
    		in1 = new JMenuItem("Connect to inputPort 1");
    		
    		in0.addActionListener(this);
    		in1.addActionListener(this);
    		
    		menu.add(in0);
    		menu.add(in1);
    		
    		currentComponent = me.getComponent();
    		menu.show(component, me.getX(), me.getY());
    		

    	}
    	
    	
    	if (me.getButton() == MouseEvent.BUTTON3)
    	{
    		connecting = false;
    		Component component = me.getComponent();
    		menu = new JPopupMenu("Connection");
    //		in0 = new JMenuItem("Connect inputPort 0");
    //		in1 = new JMenuItem("Connect inputPort 1");
    		out0 = new JMenuItem("Connect outputPort 1");
    		
   // 		in0.addActionListener(this);
   // 		in1.addActionListener(this);
    		out0.addActionListener(this);
    		
   // 		menu.add(in0);
   // 		menu.add(in1);
    		menu.add(out0);
    		
    		currentComponent = me.getComponent();
    		
    		setIn0 = new JMenuItem("Set inputPort 0");
    		setIn1 = new JMenuItem("Set inputPort 1");
    		
    		setIn0.addActionListener(this);
    		setIn1.addActionListener(this);
    		menu.add(setIn0);
    		menu.add(setIn1);
    		

    		menu.show(component, me.getX(), me.getY());
    		
            //menu.show(component, component.getWidth()/2, component.getHeight()/2);
    		
    	}
    }
 
    public void mouseDragged(MouseEvent me)
    {
    	if (afterPressing)
    	{
    		Component component = me.getComponent();
    		location = component.getLocation(location);
    		int x = location.x - pressed.getX() + me.getX();
    		int y = location.y - pressed.getY() + me.getY();
    		component.setLocation(x, y);
    	}
     }
    
    public void mouseReleased(MouseEvent me)
    {
    	afterPressing = false;
    }

	@Override
	public void actionPerformed(ActionEvent me)
	{
		
		
		Block block = null;
		Component endComponent = null;
		for (BlockComponent blockComponent : MainFrame.getBlockComponents())
		{
			if (blockComponent.getComponent().equals(currentComponent))
			{
				block = blockComponent.getBlock();
				endComponent = blockComponent.getComponent();
				break;
			}
			
		}
		
		if (me.getSource().equals(in0))
		{
		    block.getInputPort(0).setConnectedPort(currentPort);
			System.out.println("in0\n");
		}
		
		else if (me.getSource().equals(in1))
		{	
		    block.getInputPort(1).setConnectedPort(currentPort);
			System.out.println("in1\n");
		}
		
		else if (me.getSource().equals(out0))
		{	
		    currentPort = block.getOutputPort(0);
			connecting = true;
			System.out.println("out0\n");
		}
		
		if (me.getSource().equals(setIn0))
		{	
			
			Set<String> names = block.getInputPort(0).getNames();
			for (String name : names)
			{
				if (name == "float" || name == "real" || name == "imaginary")
				{
					String s = (String)JOptionPane.showInputDialog(
					                    currentComponent.getParent(),
					                    "Set inputPort 0 [" + name + "]",
					                    null, JOptionPane.PLAIN_MESSAGE,
					                    null, null, null);
					double value = Double.parseDouble(s);
					block.getInputPort(0).setValue("float", value);
				}
				
				else if (name == "bool")
				{
					Object[] possibilities = {"False", "True"};
					String s = (String)JOptionPane.showInputDialog(
										currentComponent.getParent(),
										"Set inputPort 0 [" + name + "]",
					                    "Choose:",
					                    JOptionPane.PLAIN_MESSAGE,
					                    null,
					                    possibilities,
					                    "True");
					if (s == "False")
					{
						block.getInputPort(0).setValue("bool", 0.0);				
					}
					
					if (s == "False")
					{
						block.getInputPort(0).setValue("bool", 1.0);					
					}
					
				}
				System.out.println("setIn0\n");

			
			}
		}
		
		else if (me.getSource().equals(setIn1))
		{	
			Set<String> names = block.getInputPort(0).getNames();
			for (String name : names)
			{
				if (name == "float" || name == "real" || name == "imaginary")
				{
					String s = (String)JOptionPane.showInputDialog(
					                    currentComponent.getParent(),
					                    "Set inputPort 0 [" + name + "]",
					                    null, JOptionPane.PLAIN_MESSAGE,
					                    null, null, null);
					double value = Double.parseDouble(s);
					block.getInputPort(1).setValue("float", value);
				}
				
				else if (name == "bool")
				{
					Object[] possibilities = {"False", "True"};
					String s = (String)JOptionPane.showInputDialog(
										currentComponent.getParent(),
										"Set inputPort 0 [" + name + "]",
					                    "Choose:",
					                    JOptionPane.PLAIN_MESSAGE,
					                    null,
					                    possibilities,
					                    "True");
					if (s == "False")
					{
						block.getInputPort(1).setValue("bool", 0.0);				
					}
					
					if (s == "False")
					{
						block.getInputPort(1).setValue("bool", 1.0);					
					}
					
				}
				System.out.println("setIn0\n");

			
			}
		}
		// TODO Auto-generated method stub
		
	/*	((JComponent) currentComponent).setToolTipText("<html>"
				+"Input port 0 = " + block.getInputPort(0).getValue("float") 
				+"<br>"
				+"Input port 1 = " + block.getInputPort(1).getValue("float") 
				+"<br>"
				+"Output port 0 = " + block.getOutputPort(0).getValue("float"));*/
		
		((JComponent) currentComponent).setToolTipText(block.printPorts());

	}
	
    public void mouseEntered(MouseEvent me)
    {
    	
        ToolTipManager.sharedInstance().setDismissDelay(60000);
    	
    }
    
    public void mouseExited(MouseEvent me)
    {
    	ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
    	afterPressing = false;
    }
}