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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

public class MainFrame extends JFrame implements MenuListener, ActionListener, KeyListener
{
	
	JMenuBar menuBar;
	JMenu menuCreate, menuRun, menuCreateLogic, menuCreateArithmetic, menuCreateComplex;
	JMenuItem itemArithmeticAdd, itemArithmeticSub, itemArithmeticMul, itemArithmeticDiv;
	JMenuItem itemComplexAdd, itemComplexSub, itemComplexMul, itemComplexDiv;
	JMenuItem itemLogicAnd, itemLogicOr, itemLogicNot;
	//private ImageIcon image1;
	//private JLabel label1;
	BufferedImage img;
/*	ImageIcon blok;
	JLabel lbl;
	DragListener drag;*/
	Container c;
	
	public MainFrame(String title)
	{
		super(title);
		
		//setLayout(new BorderLayout());
		//setLayout(new GridBagLayout());
		setLayout(new FlowLayout());
		
		final JTextArea textArea = new JTextArea();
		JButton button = new JButton("Click me!");
		
	
		
		menuBar = new JMenuBar();
		menuCreate = new JMenu("Create");
		menuRun = new JMenu("Run");
		menuBar.add(menuCreate);
		menuBar.add(menuRun);
		
		menuCreateLogic = new JMenu("Create logic block");
		menuCreateArithmetic = new JMenu("Create arithmetic block");
		
		menuCreate.add(menuCreateLogic);
		menuCreate.add(menuCreateArithmetic);
		
		itemArithmeticAdd = new JMenuItem("Create Add block");
		itemArithmeticSub = new JMenuItem("Create Sub block");
		itemArithmeticMul = new JMenuItem("Create Mul block");
		itemArithmeticDiv = new JMenuItem("Create Div block");
		
		itemLogicAnd = new JMenuItem("Create And block");
		itemLogicNot = new JMenuItem("Create Not block");
		itemLogicOr = new JMenuItem("Create Or block");

		
		itemArithmeticAdd.addActionListener(this);
		itemArithmeticSub.addActionListener(this);
		itemArithmeticMul.addActionListener(this);
		itemArithmeticDiv.addActionListener(this);
		
		itemLogicAnd.addActionListener(this);
		itemLogicNot.addActionListener(this);
		itemLogicOr.addActionListener(this);

		
		menuCreateArithmetic.add(itemArithmeticAdd);
		menuCreateArithmetic.add(itemArithmeticSub);
		menuCreateArithmetic.add(itemArithmeticMul);
		menuCreateArithmetic.add(itemArithmeticDiv);
		
		menuCreateLogic.add(itemLogicAnd);
		menuCreateLogic.add(itemLogicNot);
		menuCreateLogic.add(itemLogicOr);
		
		setJMenuBar(menuBar);
		
		//Container c = getContentPane();
		c = getContentPane();
		
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
		if (e.getSource().equals(itemArithmeticAdd))
		{	
			imageName = "blockArithmeticAdd.png";
			System.out.println("add\n");
		}
		
		else if (e.getSource().equals(itemArithmeticSub))
		{
			imageName = "blockArithmeticSub.png";
			System.out.println("sub\n");
		}
		
		else if (e.getSource().equals(itemArithmeticMul))
		{
			imageName = "blockArithmeticMul.png";
			System.out.println("mul\n");
		}
		
		else if (e.getSource().equals(itemArithmeticDiv))
		{
			imageName = "blockArithmeticDiv.png";
			System.out.println("div\n");
		}
		
		else if (e.getSource().equals(itemLogicAnd))
		{
			imageName = "blockLogicAnd.png";
			System.out.println("and\n");
		}
		
		else if (e.getSource().equals(itemLogicNot))
		{
			imageName = "blockLogicNot.png";
			System.out.println("not\n");
		}
		
		else if (e.getSource().equals(itemLogicOr))
		{
			imageName = "blockLogicOr.png";
			System.out.println("or\n");
		}
		
		
		img = null;
		try
		{
			img = ImageIO.read(getClass().getResource("/gui/img/blocks/" + imageName));
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
		
		
		DragListener drag = new DragListener();
		
		blockPanel.addMouseListener(drag);
		blockPanel.addMouseMotionListener(drag);
		
		blockPanel.add(lbl);
	//	Container c = getContentPane();
		c.add(blockPanel);
		

		
		
/*		DragListener drag = new DragListener();
		lbl.addMouseListener( drag );
		lbl.addMouseMotionListener( drag );*/
		
	
		
		
		
	//	Container c = getContentPane();
		
		//c.add(lbl, BorderLayout.CENTER);
		
		c.revalidate();

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

}
