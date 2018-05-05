package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class MainFrame extends JFrame implements MenuListener, ActionListener, KeyListener
{
	
	JMenuBar menuBar;
	JMenu menuCreate, menuRun, menuCreateLogic, menuCreateArithmetic;
	JMenuItem itemArithmeticAdd, itemArithmeticSub, itemArithmeticMul, itemArithmeticDiv;
	JMenuItem itemLogicAnd, itemLogicOr, itemLogicNot;
	//private ImageIcon image1;
	//private JLabel label1;
	BufferedImage img;
/*	ImageIcon blok;
	JLabel lbl;
	DragListener drag;*/
	
	public MainFrame(String title)
	{
		super(title);
		
		setLayout(new BorderLayout());
		
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
		
		itemArithmeticAdd.addActionListener(this);
		itemArithmeticSub.addActionListener(this);
		itemArithmeticMul.addActionListener(this);
		itemArithmeticDiv.addActionListener(this);

		
		menuCreateArithmetic.add(itemArithmeticAdd);
		menuCreateArithmetic.add(itemArithmeticSub);
		menuCreateArithmetic.add(itemArithmeticMul);
		menuCreateArithmetic.add(itemArithmeticDiv);
		
		setJMenuBar(menuBar);
		
//		menuPanel = new MenuPanel();
		
		Container c = getContentPane();
		
		img = null;
		try
		{
			img = ImageIO.read(getClass().getResource("/gui/img/blocks/block.png"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Couldnt load image");
			System.exit(1);
		}
//		plocha.setAutoscrolls(true);
		//c.add(plocha, BorderLayout.CENTER);
	
/*		ImageIcon blok = new ImageIcon(img);
		JLabel lbl = new JLabel();
		lbl.setIcon(blok);
		
		DragListener drag = new DragListener();
		lbl.addMouseListener( drag );
		lbl.addMouseMotionListener( drag );
		
		c.add(lbl, BorderLayout.CENTER);*/
		
	/*	blok = new ImageIcon(img);
		lbl = new JLabel();
		drag = new DragListener();
		lbl.addMouseListener( drag );
		lbl.addMouseMotionListener( drag );*/
		
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
	
		ImageIcon blok = new ImageIcon(img);
		JLabel lbl = new JLabel();
		lbl.setIcon(blok);
		
		DragListener drag = new DragListener();
		lbl.addMouseListener( drag );
		lbl.addMouseMotionListener( drag );
		
		Container c = getContentPane();
		
		c.add(lbl, BorderLayout.CENTER);
		
		c.revalidate();

		
		
		if (e.getSource().equals(itemArithmeticAdd))
		{	
			System.out.println("add\n");
		}
		
		else if (e.getSource().equals(itemArithmeticSub))
		{
			System.out.println("sub\n");
		}
		
		else if (e.getSource().equals(itemArithmeticMul))
		{
			System.out.println("mul\n");
		}
		
		else if (e.getSource().equals(itemArithmeticDiv))
		{
			System.out.println("div\n");
		}
		

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
