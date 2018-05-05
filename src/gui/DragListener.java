package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

public class DragListener extends MouseInputAdapter implements ActionListener
{
    Point location;
    MouseEvent pressed;
    boolean afterPressing = false;
    JMenuItem in0, in1, out0;
    JPopupMenu menu;
    Graphics2D graphics;
    Point helper; // for getLocation()
    Point2D start;
    Point2D end;
    Component current;
    boolean connecting = false;
 
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
    		helper = component.getLocation(helper);
    		end = new Point2D.Double(helper.x, helper.y);
    		//end.setLocation(helper.x, helper.y);
    		
    		graphics.draw(new Line2D.Double(start, end));
    		current.paint(graphics);
    		
    		connecting = false;
    		
    	}
    	
    	
    	if (me.getButton() == MouseEvent.BUTTON3)
    	{
    		connecting = false;
    		Component component = me.getComponent();
    		menu = new JPopupMenu("Connection");
    		in0 = new JMenuItem("Connect inputPort 0");
    		in1 = new JMenuItem("Connect inputPort 1");
    		out0 = new JMenuItem("Connect outputPort 1");
    		
    		in0.addActionListener(this);
    		in1.addActionListener(this);
    		out0.addActionListener(this);
    		
    		menu.add(in0);
    		menu.add(in1);
    		menu.add(out0);
    		
    		current = me.getComponent();
    		
    		
            menu.show(component, component.getWidth()/2, component.getHeight()/2);
    		
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
		
	    helper = current.getLocation(helper);
		start = new Point2D.Double(helper.x, helper.y);
//	    start.setLocation(helper.x, helper.y);
	  
		if (me.getSource().equals(in0))
		{
		    
			System.out.println("in0\n");
		}
		
		else if (me.getSource().equals(in1))
		{	
			System.out.println("in1\n");
		}
		
		else if (me.getSource().equals(out0))
		{	
			System.out.println("out0\n");
		}
		// TODO Auto-generated method stub
		
		connecting = true;
	}
}