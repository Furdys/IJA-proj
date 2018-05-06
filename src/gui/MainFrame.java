package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import ija.proj.block.*;
import ija.proj.scheme.Scheme;



public class MainFrame extends JFrame implements MenuListener, ActionListener, KeyListener
{
	
	private JMenuBar menuBar;
	private JMenu menuCreate, menuRun, menuCreateLogic, menuCreateArithmetic, menuCreateComplex;
	private JMenuItem itemArithmeticAdd, itemArithmeticSub, itemArithmeticMul, itemArithmeticDiv;
	private JMenuItem itemComplexAdd, itemComplexSub, itemComplexMul, itemComplexDiv;
	private JMenuItem itemLogicAnd, itemLogicOr, itemLogicNot;
	private JMenuItem itemRun;

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
	
	public MainFrame(String title)
	{
		super(title);
		
		//setLayout(new BorderLayout());
		//setLayout(new GridBagLayout());
		setLayout(new FlowLayout());
		
/*		final JTextArea textArea = new JTextArea();
		JButton button = new JButton("Click me!");*/
		
		this.scheme = new Scheme("tmp"); //TODO what names?
		this.blockComponents = new ArrayList<BlockComponent>();
		this.drag = new DragListener();
		
		this.menuBar = new JMenuBar();
		this.menuCreate = new JMenu("Create");
		this.menuRun = new JMenu("File");
		
		this.itemRun = new JMenuItem("run");
		
		this.itemRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Before run");
				scheme.run();
				System.out.println("After run");
				for (BlockComponent blockComponent : MainFrame.blockComponents)
				{
					((JComponent) blockComponent.getComponent()).setToolTipText("<html>"
							+"Input port 0 = " + blockComponent.getBlock().getInputPort(0).getValue("float") 
							+"<br>"
							+"Input port 1 = " + blockComponent.getBlock().getInputPort(1).getValue("float") 
							+"<br>"
							+"Output port 0 = " + blockComponent.getBlock().getOutputPort(0).getValue("float")
							+"<html>");
				}
			}
		});
		menuRun.add(itemRun);
		
		
		this.menuBar.add(this.menuRun);
		this.menuBar.add(this.menuCreate);
		
		
		this.menuCreateArithmetic = new JMenu("Create arithmetic block");
		this.menuCreateLogic = new JMenu("Create logic block");
		
		
		this.menuCreate.add(menuCreateArithmetic);
		this.menuCreate.add(menuCreateLogic);
		
		this.itemArithmeticAdd = new JMenuItem("Create Add block");
		this.itemArithmeticSub = new JMenuItem("Create Sub block");
		this.itemArithmeticMul = new JMenuItem("Create Mul block");
		this.itemArithmeticDiv = new JMenuItem("Create Div block");
		
		this.itemLogicAnd = new JMenuItem("Create And block");
		this.itemLogicNot = new JMenuItem("Create Not block");
		this.itemLogicOr = new JMenuItem("Create Or block");

		
		this.itemArithmeticAdd.addActionListener(this);
		this.itemArithmeticSub.addActionListener(this);
		this.itemArithmeticMul.addActionListener(this);
		this.itemArithmeticDiv.addActionListener(this);
		
		this.itemLogicAnd.addActionListener(this);
		this.itemLogicNot.addActionListener(this);
		this.itemLogicOr.addActionListener(this);

		
		this.menuCreateArithmetic.add(this.itemArithmeticAdd);
		this.menuCreateArithmetic.add(this.itemArithmeticSub);
		this.menuCreateArithmetic.add(this.itemArithmeticMul);
		this.menuCreateArithmetic.add(this.itemArithmeticDiv);
		
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
			System.out.println("and\n");
		}
		
		else if (e.getSource().equals(this.itemLogicNot))
		{
			imageName = "blockLogicNot.png";
			System.out.println("not\n");
		}
		
		else if (e.getSource().equals(this.itemLogicOr))
		{
			imageName = "blockLogicOr.png";
			System.out.println("or\n");
		}
		

		this.img = null;
		try
		{
			this.img = ImageIO.read(getClass().getResource("/gui/img/blocks/" + imageName));
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
		blockPanel.setPreferredSize(new Dimension(256, 256));

	//	DragListener drag = new DragListener();
		
		MainFrame.blockComponents.add(new BlockComponent(block, blockPanel));
		
		blockPanel.addMouseListener(drag);
		blockPanel.addMouseMotionListener(drag);

		
		blockPanel.add(lbl);
		
		this.c.add(blockPanel);
		
		
		
		((JComponent) blockPanel).setToolTipText("<html>"
				+"Input port 0 = " + block.getInputPort(0).getValue("float") 
				+"<br>"
				+"Input port 1 = " + block.getInputPort(1).getValue("float") 
				+"<br>"
				+"Output port 0 = " + block.getOutputPort(0).getValue("float"));
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
	
	

}
