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

import ija.proj.block.*;
import ija.proj.scheme.Scheme;



public class MainFrame extends JFrame implements MenuListener, ActionListener, KeyListener, Serializable
{
	
	private JMenuBar menuBar;
	private JMenu menuCreate, menuRun, menuCreateLogic, menuCreateArithmetic, menuCreateComplex;
	private JMenuItem itemArithmeticAdd, itemArithmeticSub, itemArithmeticMul, itemArithmeticDiv;
	private JMenuItem itemComplexAdd, itemComplexSub, itemComplexMul, itemComplexDiv;
	private JMenuItem itemLogicAnd, itemLogicOr, itemLogicNot;
	private JMenuItem itemRun, itemStep;

	//private ImageIcon image1;
	//private JLabel label1;
	private BufferedImage img;
/*	ImageIcon blok;
	JLabel lbl;
	DragListener drag;*/
	private Container c;
	private Scheme scheme;
	private static ArrayList<BlockComponent> blockComponents;
	private DragListener drag;
	private static ArrayList<Point> locations;
	
	public MainFrame(String title)
	{
		super(title);
		
		//setLayout(new BorderLayout());
		//setLayout(new GridBagLayout());
		//setLayout(new FlowLayout());
		setLayout(null);
		
/*		final JTextArea textArea = new JTextArea();
		JButton button = new JButton("Click me!");*/
		
		this.scheme = new Scheme("tmp"); //TODO what names?
		this.blockComponents = new ArrayList<BlockComponent>();
		this.drag = new DragListener();
		MainFrame.locations = new ArrayList<Point>();
		
		this.menuBar = new JMenuBar();
		this.menuCreate = new JMenu("Create");
		this.menuRun = new JMenu("File");
		
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
		this.c = getContentPane();
		
		
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
		//blockPanel.setPreferredSize(new Dimension(256, 270));
		blockPanel.setSize(256,270);
		blockPanel.setLocation(20,20);

	//	DragListener drag = new DragListener();
		
		MainFrame.blockComponents.add(new BlockComponent(block, blockPanel));
		
		blockPanel.addMouseListener(drag);
		blockPanel.addMouseMotionListener(drag);

		
		blockPanel.add(lbl);
		
		this.c.add(blockPanel);
		
		
		
	/*	((JComponent) blockPanel).setToolTipText("<html>"
				+"Input port 0 = " + block.getInputPort(0).getValue("float") 
				+"<br>"
				+"Input port 1 = " + block.getInputPort(1).getValue("float") 
				+"<br>"
				+"Output port 0 = " + block.getOutputPort(0).getValue("float"));*/
		((JComponent) blockPanel).setToolTipText(block.printPorts());
		
		this.c.revalidate();
		
		this.scheme.addBlock(block);

		//this.blockComponents.add(new BlockComponent(block, blockPanel));
		
		


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
		((JComponent) component).setBorder(border);;
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
				block.getInputPort(0).setValue("float", value);
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
	

}
