package myGomoku;
/*
 * Author: Han Liao (lhan@iastate.edu or leslieileo@gmail.com)
 * This is the project for creative component in ISU
 * This class include the Simulator Menu windows UI
 */

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Simulator_Menu implements Runnable{

	public JFrame frame = new JFrame();
	private JLabel Intro = new JLabel("Intro");
	private JRadioButton GreedyB = new JRadioButton();
	private JRadioButton MinimaxB = new JRadioButton();
	private JRadioButton MyAIB = new JRadioButton();
	private JRadioButton GreedyW = new JRadioButton();
	private JRadioButton MinimaxW = new JRadioButton();
	private JRadioButton MyAIW= new JRadioButton();
	private JButton ApllyButton = new JButton();
	public String White="Greedy";
	public String Black="Greedy";
	public int k=1;
	public boolean AT1animation=false;
	public boolean Animation=true;
	public char[][] board;
	public ArrayList<Moves> movinglist=new ArrayList<Moves>();

	@Override
	public void run() {
		frame.setVisible(true);	
		frame.setResizable(false);
		frame.setBounds(100, 100, 550, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		ImageIcon beam=null;
		beam = new ImageIcon(this.getClass().getClassLoader().getResource("GomokuIcon.png"));
		Image icon=beam.getImage();
		frame.setIconImage(icon);
		//frame.pack();
		frame.setLocationRelativeTo(null);


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Simulator Menu");

		frame.getContentPane().setBackground(new Color(255,250,240));
		ImageIcon beam2=null;
		//beam2 = new ImageIcon(this.getClass().getClassLoader().getResource("menu-background.jpg"));
		beam2 = new ImageIcon(this.getClass().getClassLoader().getResource("Ocean.jpg"));
		Image icon2=beam2.getImage();
		JLabel background = new JLabel(new ImageIcon(icon2));
		frame.setContentPane(background);

		Intro=new JLabel("Choose the AI algorithm to be simulated:");
		Intro.setForeground(Color.BLACK);
		Intro.setFont(new Font("Microsoft JhengHei", Font.BOLD, 20));
		Intro.setBounds(12, 13, 500, 50);
		frame.getContentPane().add(Intro);

		JLabel balcklable = new JLabel("BLACK:");
		balcklable.setFont(new Font("Times New Roman", Font.BOLD, 17));
		balcklable.setBounds(12, 90, 81, 35);
		frame.getContentPane().add(balcklable);

		JLabel lblWhite = new JLabel("WHITE:");
		lblWhite.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblWhite.setBounds(12, 138, 81, 35);
		frame.getContentPane().add(lblWhite);

		GreedyB = new JRadioButton("Greedy");
		GreedyB.setForeground(Color.RED);
		GreedyB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(GreedyB.isSelected()) {
					MinimaxB.setSelected(false);
					MyAIB.setSelected(false);
					Black="Greedy";
				}
			}
		});
		GreedyB.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
		GreedyB.setBounds(110, 97, 100, 25);
		GreedyB.setBackground(new Color(0, 191, 255));
		frame.getContentPane().add(GreedyB);

		MinimaxB = new JRadioButton("Minimax");
		MinimaxB.setForeground(Color.BLUE);
		MinimaxB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MinimaxB.isSelected()) {
					GreedyB.setSelected(false);
					MyAIB.setSelected(false);
					Black="Minimax";
				}
			}
		});
		MinimaxB.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
		MinimaxB.setBounds(240, 97, 100, 25);
		MinimaxB.setBackground(new Color(0, 191, 255));
		frame.getContentPane().add(MinimaxB);

		MyAIB = new JRadioButton("MyAI");
		MyAIB.setForeground(new Color(199, 21, 133));
		MyAIB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MyAIB.isSelected()) {
					GreedyB.setSelected(false);
					MinimaxB.setSelected(false);
					Black="MyAI";
				}
			}
		});
		MyAIB.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
		MyAIB.setBounds(370, 97, 81, 25);
		MyAIB.setBackground(new Color(0, 191, 255));
		frame.getContentPane().add(MyAIB);

		GreedyW = new JRadioButton("Greedy");
		GreedyW.setForeground(Color.RED);
		GreedyW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GreedyW.isSelected()) {
					MinimaxW.setSelected(false);
					MyAIW.setSelected(false);
					White="Greedy";
				}
			}
		});
		GreedyW.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
		GreedyW.setBounds(110, 144, 100, 25);
		GreedyW.setBackground(new Color(0, 191, 255));
		frame.getContentPane().add(GreedyW);

		MinimaxW = new JRadioButton("Minimax");
		MinimaxW.setForeground(Color.BLUE);
		MinimaxW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MinimaxW.isSelected()) {
					GreedyW.setSelected(false);
					MyAIW.setSelected(false);
					White="Minimax";
				}
			}
		});
		MinimaxW.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
		MinimaxW.setBounds(240, 144, 101, 25);
		MinimaxW.setBackground(new Color(0, 191, 255));
		frame.getContentPane().add(MinimaxW);

		MyAIW = new JRadioButton("MyAI");
		MyAIW.setForeground(new Color(199, 21, 133));
		MyAIW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MyAIW.isSelected()) {
					GreedyW.setSelected(false);
					MinimaxW.setSelected(false);
					White="MyAI";
				}
			}
		});
		MyAIW.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
		MyAIW.setBounds(370, 144, 81, 25);
		MyAIW.setBackground(new Color(0, 191, 255));
		frame.getContentPane().add(MyAIW);

		ApllyButton = new JButton("Apply&Start");
		ApllyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GreedyB.isSelected()) {
					Black="Greedy";
				}
				else if(MinimaxB.isSelected()) {
					Black="Minimax";
				}
				else if(MyAIB.isSelected()) {
					Black="MyAI";
				}
				if(GreedyW.isSelected()) {
					White="Greedy";
				}
				else if(MinimaxW.isSelected()) {
					White="Minimax";
				}
				else if(MyAIW.isSelected()) {
					White="MyAI";
				}
				frame.setVisible(false);
				Gomoku_GUI gui = new Gomoku_GUI();	
				gui.k=k;
				gui.simuApply=true;
				gui.simuB=Black;
				gui.simuW=White;
				gui.board=board;
				gui.movinglist=movinglist;
				gui.Animation=Animation;
				gui.AT1animation=AT1animation;
				gui.frame.setVisible(true);
				gui.btnsimulate.doClick();
			}
		});
		ApllyButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		ApllyButton.setBackground(Color.GREEN);
		ApllyButton.setForeground(Color.RED);
		ApllyButton.setBounds(317, 202, 149, 38);
		frame.getContentPane().add(ApllyButton);
		
		JButton Cancelbtn = new JButton("Cancel");
		Cancelbtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		Cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				Gomoku_GUI gui = new Gomoku_GUI();
				gui.board=board;
				gui.simuApply=false;
				gui.simuB=Black;
				gui.simuW=White;
				gui.movinglist=movinglist;
				gui.Animation=Animation;
				gui.AT1animation=AT1animation;
				gui.frame.setVisible(true);
				
			}
		});
		Cancelbtn.setForeground(Color.GREEN);
		Cancelbtn.setBackground(Color.RED);
		Cancelbtn.setBounds(91, 202, 149, 33);
		frame.getContentPane().add(Cancelbtn);

	}
}
