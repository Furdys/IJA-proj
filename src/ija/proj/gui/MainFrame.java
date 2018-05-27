package ija.proj.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.junit.Assert;

import ija.proj.block.*;
import ija.proj.scheme.Scheme;
import ija.proj.scheme.SchemeFile;
import javafx.scene.shape.Line;



public class MainFrame extends JFrame implements MenuListener, ActionListener, KeyListener, Serializable
{
	
	private JMenuBar menuBar;
	private JMenu menuCreate, menuRun, menuCreateLogic, menuCreateArithmetic, menuCreateComplex, menuFile;
	private JMenuItem itemArithmeticAdd, itemArithmeticSub, itemArithmeticMul, itemArithmeticDiv;
	private JMenuItem itemComplexAdd, itemComplexSub, itemComplexMul, itemComplexDiv;
	private JMenuItem itemLogicAnd, itemLogicOr, itemLogicNot;
	private JMenuItem itemRun, itemStep;
	private JMenuItem itemSave, itemLoad;
	private JMenuItem itemResetValues, itemResetScheme;
	private JFileChooser fc;
	

	private static BufferedImage img;
	private static Container c;
	private static Scheme scheme;
	private static ArrayList<BlockComponent> blockComponents;
	private static DragListener drag;
	private static ArrayList<Point> locations;
	//private static ArrayList<Connection> connections;
	
	public MainFrame(String title)
	{
		super(title);
		
		
		setContentPane(new MainPanel());
		System.setProperty("sun.io.serialization.extendedDebugInfo", "true");
		//setLayout(new BorderLayout());
		//setLayout(new GridBagLayout());
		//setLayout(new FlowLayout());
		setLayout(null);
		this.c = getContentPane();
		JFrame MainFrameRef = this;
		fc = new JFileChooser();
			
		/*this.scheme = new Scheme("tmp"); //TODO what names?
		this.blockComponents = new ArrayList<BlockComponent>();
		this.drag = new DragListener();
		MainFrame.locations = new ArrayList<Point>();*/
		
		this.setUp();
		
		this.menuBar = new JMenuBar();
		this.menuCreate = new JMenu("Create");
		this.menuRun = new JMenu("Scheme");
		this.menuFile = new JMenu("File");	
		
		this.itemRun = new JMenuItem("Run");
		
		this.itemRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Before run");
				scheme.run();
				System.out.println("After run");
				for (BlockComponent blockComponent : MainFrame.blockComponents)
				{
					((JComponent) blockComponent.getComponent()).setToolTipText(blockComponent.getBlock().printPorts());
				}
			}
		});
		menuRun.add(itemRun);	
		
		
		this.itemStep = new JMenuItem("Step");
		this.itemStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Before run");
				scheme.runStep();
				System.out.println("After run");
				for (BlockComponent blockComponent : MainFrame.blockComponents)
				{
					((JComponent) blockComponent.getComponent()).setToolTipText(blockComponent.getBlock().printPorts());
				}
			}
		});
		
		menuRun.add(itemStep);
		
		this.itemResetScheme = new JMenuItem("Reset Scheme");
		this.itemResetScheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		
		menuRun.add(itemResetScheme);	
		
		this.itemResetValues = new JMenuItem("Reset Values");
		this.itemResetValues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValues();
			}
		});
		
		menuRun.add(itemResetValues);
		
		
		
		this.itemSave = new JMenuItem("Save");
		this.itemSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Before Save");
	            int returnVal = fc.showSaveDialog(MainFrame.this);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                MainFrame.preSave();
	                MainFrameFile.save(MainFrame.scheme, file.getAbsolutePath());
	                System.out.println("Saving: " + file.getName() + " with size of " + MainFrame.scheme.getBlocks().size() + ".");
	            } else {
	            	System.out.println("Save command cancelled by user.");
	            }
				System.out.println("After Save");
			/*	for (Block block : MainFrame.getScheme().getBlocks())
				{
					System.out.println(block.getInputPort(0).getValue("float"));
				}*/
			}
		});
		
		menuFile.add(itemSave);
		
		this.itemLoad = new JMenuItem("Load");
		this.itemLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Before Load");
				int returnVal = fc.showOpenDialog(MainFrame.this);

	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                //MainFrame.scheme = MainFrameFile.open(file.getAbsolutePath());
	                MainFrame.reset();
	                System.out.println("Opening: " + file.getName() + " with size of " + MainFrameFile.open(file.getAbsolutePath()).getBlocks().size() + "....");
	                MainFrame.setScheme(MainFrameFile.open(file.getAbsolutePath()));
	                MainFrame.loadPaint();
	                System.out.println("Opening: " + file.getName() + " with size of " + MainFrame.scheme.getBlocks().size() + ".");
	            } else {
	            	System.out.println("Open command cancelled by user.");
	            }
				System.out.println("After Load");
				/*for (Block block : MainFrame.getScheme().getBlocks())
				{
					System.out.println(block.getInputPort(0).getValue("float"));
				}*/
			}
		});
		
		menuFile.add(itemLoad);
		
		this.menuBar.add(menuFile);
		this.menuBar.add(this.menuRun);
		this.menuBar.add(this.menuCreate);
		
		
		this.menuCreateArithmetic = new JMenu("Create arithmetic block");
		this.menuCreateLogic = new JMenu("Create logic block");
		this.menuCreateComplex = new JMenu("Create complex block");
		
		
		this.menuCreate.add(menuCreateArithmetic);
		this.menuCreate.add(menuCreateLogic);
		this.menuCreate.add(menuCreateComplex);
		
		this.itemArithmeticAdd = new JMenuItem("Create Add block");
		this.itemArithmeticSub = new JMenuItem("Create Sub block");
		this.itemArithmeticMul = new JMenuItem("Create Mul block");
		this.itemArithmeticDiv = new JMenuItem("Create Div block");
		
		this.itemComplexAdd = new JMenuItem("Create Add block");
		this.itemComplexSub = new JMenuItem("Create Sub block");
		this.itemComplexMul = new JMenuItem("Create Mul block");
		this.itemComplexDiv = new JMenuItem("Create Div block");
		
		this.itemLogicAnd = new JMenuItem("Create And block");
		this.itemLogicNot = new JMenuItem("Create Not block");
		this.itemLogicOr = new JMenuItem("Create Or block");

		
		this.itemArithmeticAdd.addActionListener(this);
		this.itemArithmeticSub.addActionListener(this);
		this.itemArithmeticMul.addActionListener(this);
		this.itemArithmeticDiv.addActionListener(this);
		
		this.itemComplexAdd.addActionListener(this);
		this.itemComplexSub.addActionListener(this);
		this.itemComplexMul.addActionListener(this);
		this.itemComplexDiv.addActionListener(this);
		
		this.itemLogicAnd.addActionListener(this);
		this.itemLogicNot.addActionListener(this);
		this.itemLogicOr.addActionListener(this);

		
		this.menuCreateArithmetic.add(this.itemArithmeticAdd);
		this.menuCreateArithmetic.add(this.itemArithmeticSub);
		this.menuCreateArithmetic.add(this.itemArithmeticMul);
		this.menuCreateArithmetic.add(this.itemArithmeticDiv);
		
		this.menuCreateComplex.add(this.itemComplexAdd);
		this.menuCreateComplex.add(this.itemComplexSub);
		this.menuCreateComplex.add(this.itemComplexMul);
		this.menuCreateComplex.add(this.itemComplexDiv);
		
		this.menuCreateLogic.add(this.itemLogicAnd);
		this.menuCreateLogic.add(this.itemLogicNot);
		this.menuCreateLogic.add(this.itemLogicOr);
		
		setJMenuBar(this.menuBar);
		
		//Container c = getContentPane();


		
		
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
				
		String imageName = "";
		Block block = null;
		if (e.getSource().equals(this.itemArithmeticAdd))
		{	
			imageName = "blockArithmeticAdd.png";
			block = new BlockAdd();
			System.out.println("add\n");
		}
		
		else if (e.getSource().equals(this.itemArithmeticSub))
		{
			imageName = "blockArithmeticSub.png";
			block = new BlockSub();
			System.out.println("sub\n");
			
		}
		
		else if (e.getSource().equals(this.itemArithmeticMul))
		{
			imageName = "blockArithmeticMul.png";
			block = new BlockMul();
			System.out.println("mul\n");
		}
		
		else if (e.getSource().equals(this.itemArithmeticDiv))
		{
			imageName = "blockArithmeticDiv.png";
			block = new BlockDiv();
			System.out.println("div\n");
		}
		
		else if (e.getSource().equals(this.itemLogicAnd))
		{
			imageName = "blockLogicAnd.png";
			block = new BlockAnd();
			System.out.println("and\n");
		}
		
		else if (e.getSource().equals(this.itemLogicNot))
		{
			imageName = "blockLogicNot.png";
			block = new BlockXor();
			System.out.println("not\n");
		}
		
		else if (e.getSource().equals(this.itemLogicOr))
		{
			imageName = "blockLogicOr.png";
			block = new BlockOr();
			System.out.println("or\n");
		}
		
		else if (e.getSource().equals(this.itemComplexAdd))
		{	
			imageName = "blockCxAdd.png";
			block = new BlockCxAdd();
			System.out.println("add\n");
		}
		
		else if (e.getSource().equals(this.itemComplexSub))
		{
			imageName = "blockCxSub.png";
			block = new BlockCxSub();
			System.out.println("sub\n");
			
		}
		
		else if (e.getSource().equals(this.itemComplexMul))
		{
			imageName = "blockCxMul.png";
			block = new BlockCxMul();
			System.out.println("mul\n");
		}
		
		else if (e.getSource().equals(this.itemComplexDiv))
		{
			imageName = "blockCxDiv.png";
			block = new BlockCxDiv();
			System.out.println("div\n");
		}
		
		block.setPictureName(imageName);

		this.img = null;
		try
		{
			this.img = ImageIO.read(getClass().getResource("/ija/proj/gui/img/blocks/" + imageName));
		} catch (IOException f)
		{
			// TODO Auto-generated catch block
			f.printStackTrace();
			System.out.println("Couldnt load image");
			System.exit(1);
		}
		
		
		ImageIcon blok = new ImageIcon(img);
		JLabel lbl = new JLabel();
		lbl.setIcon(blok);
		
		JPanel blockPanel = new JPanel();
		blockPanel.setSize(128,135);
		blockPanel.setLocation(20, 20);
		block.setxLocation(blockPanel.getX());
		block.setyLocation(blockPanel.getY());

		MainFrame.blockComponents.add(new BlockComponent(block, blockPanel));
		
		blockPanel.addMouseListener(drag);
		blockPanel.addMouseMotionListener(drag);

		blockPanel.add(lbl);
		
		this.c.add(blockPanel);
		
		((JComponent) blockPanel).setToolTipText(block.printPorts());
		
		this.c.revalidate();
		
		this.scheme.addBlock(block);

		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuSelected(MenuEvent e)
	{


		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuDeselected(MenuEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuCanceled(MenuEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	public static ArrayList<BlockComponent> getBlockComponents()
	{
		return blockComponents;
	}
	
	public static double fillValues(Block block, Port port, String name)
	{
		Component component = null;
		for (BlockComponent blockComponent : MainFrame.getBlockComponents())
		{
			if (blockComponent.getBlock().equals(block))
			{
				component = blockComponent.getComponent();
				break;
			}
		}
		
		MainFrame.savePosition();
		Border border = BorderFactory.createLineBorder(Color.RED,10);
		((JComponent) component).setBorder(border);
		MainFrame.fixPosition();
		
		
		double value = 0.0;
			if (name == "float" || name == "real" || name == "imaginary")
			{
				String s = (String)JOptionPane.showInputDialog(
				                    component.getParent(),
				                    "Set inputPort [" + name + "]",
				                    null, JOptionPane.PLAIN_MESSAGE,
				                    null, null, null);
				value = Double.parseDouble(s);
				//block.getInputPort(0).setValue("float", value);
				port.setValue("float", value);
			}
			
			else if (name == "bool")
			{
				Object[] possibilities = {"False", "True"};
				String s = (String)JOptionPane.showInputDialog(
									component.getParent(),
									"Set inputPort [" + name + "]",
				                    "Choose:",
				                    JOptionPane.PLAIN_MESSAGE,
				                    null,
				                    possibilities,
				                    "True");
				if (s == "False")
				{
					value = 0.0;			
				}
				
				else if (s == "True")
				{
					value = 1.0;			
				}
				
			}
		
		
	/*	String s = (String)JOptionPane.showInputDialog(
                component.getParent(),
                "Set inputPort",
                null, JOptionPane.PLAIN_MESSAGE,
                null, null, null);*/
		

	//	double value = Double.parseDouble(s);
		((JComponent) component).setBorder(BorderFactory.createEmptyBorder());
		MainFrame.fixPosition();
		return value;
				


	}
	
	public static ArrayList<Point> getLocations()
	{
		return locations;
	}

	
	public static void savePosition()
	{
		for (BlockComponent blockComponent : MainFrame.getBlockComponents())
		{
			locations.add(new Point(blockComponent.getComponent().getX(), blockComponent.getComponent().getY()));
		}
	}
	
	public static void fixPosition()
	{
		
		Iterator<BlockComponent> blockComponent = MainFrame.getBlockComponents().iterator();
		Iterator<Point> point = MainFrame.getLocations().iterator();

		while (blockComponent.hasNext() && point.hasNext()) 
		{
		    blockComponent.next().getComponent().setLocation(point.next());
		}
		
		locations.clear();
	}
	
	public static void setUp()
	{
		MainFrame.scheme = new Scheme("tmp"); //TODO what names?
		MainFrame.blockComponents = new ArrayList<BlockComponent>();
		MainFrame.drag = new DragListener();
		MainFrame.locations = new ArrayList<Point>();
		
	}
	
	public static void reset()
	{
    	//getContentPane().removeAll();
    	//getContentPane().repaint();
		c.removeAll();
		c.repaint();
		setUp();
	}
	
	public void resetValues()
	{
		this.scheme.resetValues();	
		for (BlockComponent blockComponent : MainFrame.getBlockComponents())
		{
			((JComponent) blockComponent.getComponent()).setToolTipText(blockComponent.getBlock().printPorts());
		}
	}
	
	public static void preSave()
	{
		for (BlockComponent blockComponent : MainFrame.blockComponents)
		{
			blockComponent.getBlock().setxLocation(blockComponent.getComponent().getX());
			blockComponent.getBlock().setyLocation(blockComponent.getComponent().getY());
		}
	}
	
	public static void loadPaint()
	{
		int count = 0;
		for (Block block : MainFrame.scheme.getBlocks())
		{
			String imageName = block.getPictureName();
			
			MainFrame.img = null;
			try
			{
				MainFrame.img = ImageIO.read(MainFrame.class.getResource("/ija/proj/gui/img/blocks/" + imageName));
			} catch (IOException f)
			{
				// TODO Auto-generated catch block
				f.printStackTrace();
				System.out.println("Couldnt load image");
				System.exit(1);
			}
			
			
			ImageIcon blok = new ImageIcon(img);
			JLabel lbl = new JLabel();
			lbl.setIcon(blok);
			
			JPanel blockPanel = new JPanel();
			blockPanel.setSize(128,135);
			blockPanel.setLocation(block.getxLocation(), block.getyLocation());
			
			//MainFrame.blockComponents.add(new BlockComponent(block, blockPanel));
			
			BlockComponent blockComponent = new BlockComponent(block, blockPanel);
			if (block.getOutputPort(0) != null)
			{
				blockComponent.setLine(new Line());
			}
			MainFrame.blockComponents.add(blockComponent);
			
			blockPanel.addMouseListener(drag);
			blockPanel.addMouseMotionListener(drag);

			blockPanel.add(lbl);
			
			MainFrame.c.add(blockPanel);
			
			((JComponent) blockPanel).setToolTipText(block.printPorts());
			
			//MainFrame.c.revalidate();
			
			
		}
		
		
		MainFrame.c.revalidate();
		
	}

	public static Scheme getScheme()
	{
		return scheme;
	}

	public static void setScheme(Scheme scheme)
	{
		MainFrame.scheme = scheme;
	}
	
}
