package gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main  
{

	
	public static void main(String[] args) 
	{
		
		SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						JFrame frame = new MainFrame("Hello World");
						frame.setSize(500, 400);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.setVisible(true);
						frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
						
					}
				});
	}
	
}
