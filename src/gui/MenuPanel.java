package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MenuPanel extends JPanel
{
	
	JMenuBar menuBar;
	JMenu menuCreate, menuRun, menuCreateLogic, menuCreateArithmetic;
	JMenuItem itemArithmeticAdd, itemArithmeticSub, itemArithmeticMul, itemArithmeticDiv;
	JMenuItem itemLogicAnd, itemLogicOr, itemLogicNot;
	
		public MenuPanel()
		{
			Dimension size = getPreferredSize();
			size.height = 20;
			setPreferredSize(size);
			
	//		setBorder(BorderFactory.createTitledBorder("Personal Details"));
			
			menuBar = new JMenuBar();
			menuCreate = new JMenu("Create block");
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
			
			menuCreateArithmetic.add(itemArithmeticAdd);
			menuCreateArithmetic.add(itemArithmeticSub);
			menuCreateArithmetic.add(itemArithmeticMul);
			menuCreateArithmetic.add(itemArithmeticDiv);
			
			setLayout(new GridBagLayout());
			GridBagConstraints gc = new GridBagConstraints();
			
			gc.anchor = GridBagConstraints.LINE_START;
			
			gc.weightx = 0.5;
			gc.weightx = 0.5;
			gc.gridx = 0;
			gc.gridy = 0;

			
			
				
		/*	JLabel nameLabel = new JLabel("Name: ");
			JLabel occupationLabel = new JLabel("Occupation: ");
			
			final JTextField nameField = new JTextField(10);
			final JTextField occupationField = new JTextField(10);
			
			JButton addBtn = new JButton("Add");
			
			addBtn.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String name = nameField.getText();
					String occupation = occupationField.getText();
					
					String text = name + ": " + occupation +"\n";
					
					System.out.println(text);
				}
			});
			
			setLayout(new GridBagLayout());
			
			GridBagConstraints gc = new GridBagConstraints();
			
			//// First Column ////////////
			gc.anchor = GridBagConstraints.LINE_END;
			
			gc.weightx = 0.5;
			gc.weightx = 0.5;
			gc.gridx = 0;
			gc.gridy = 0;
			
			add(nameLabel, gc);
			
			gc.gridx = 0;
			gc.gridy = 1;
			add(occupationLabel, gc);
			
			//// Second column ///////////
			gc.anchor = GridBagConstraints.LINE_START;
			
			gc.gridx = 1;
			gc.gridy = 0;
			add(nameField, gc);
			
			gc.gridx = 1;
			gc.gridy = 1;
			add(occupationField, gc);
			
			//// Final row //////////////
			gc.weighty = 10;
			gc.anchor = GridBagConstraints.FIRST_LINE_START;
			
			gc.gridx = 1;
			gc.gridy = 2;
			add(addBtn, gc);*/
			
			
			
		}

}
