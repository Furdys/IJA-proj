/**
 * Main class of the application.
 * @brief Package for Main.
 * @file Main.java
 * @author Peter Havan (xhavan00)
 */

package ija.proj.gui;

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
						JFrame frame = new MainFrame("Scheme");
					//	frame = new MainFrame("Hello World");
						frame.setSize(500, 400);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.setVisible(true);
						frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
						
					}
				});
	}

}

/*! \mainpage BlockEdior
 *
 * \section intro_sec Introduction
 *
 * BlockEditor by All Nighter Bros was developed as school project
 * for subject IPP at FIT VUT Brno.\n It allows user to design their
 * own block scheme and execute it.\n
 * This project is developed in Java and it is using Swing for GUI.
 *
 * \section author_sec Authors (All Nighter Bros)
 *
 * Jiri Furda (xfurda00)\n
 * Peter Havan (xhavan00)
 */