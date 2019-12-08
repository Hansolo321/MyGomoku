package myGomoku;

/*
 * Author: Han Liao (lhan@iastate.edu or leslieileo@gmail.com)
 * This is the project for creative component in ISU
 * This class include the Simulator Menu windows UI
 */

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
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
	public boolean gameend;
	public String reportname;
	public int testnum;

	@Override
	public void run() {
		frame.setVisible(true);	
		frame.setResizable(false);
		frame.setBounds(100, 100, 706, 360);
		frame.getContentPane().setLayout(null);
		ImageIcon beam=null;
		beam = new ImageIcon(this.getClass().getClassLoader().getResource("GomokuIcon.png"));
		Image icon=beam.getImage();
		frame.setIconImage(icon);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Simulator Menu");
		ImageIcon beam2=null;
		beam2 = new ImageIcon(this.getClass().getClassLoader().getResource("menu-background.jpg"));
		//beam2 = new ImageIcon(this.getClass().getClassLoader().getResource("Ocean.jpg"));
		Image icon2=beam2.getImage();
		JLabel background = new JLabel(new ImageIcon(icon2));
		frame.setContentPane(background);

		////////////////////////////
		/*
		 * parameter setting for myAI
		 */

		JLabel deptylable = new JLabel("Depth");
		deptylable.setFont(new Font("Times New Roman", Font.BOLD, 17));
		deptylable.setBounds(455, 75, 50, 20);
		frame.getContentPane().add(deptylable);

		JTextArea depty1 = new JTextArea();
		depty1.setFont(new Font("Mongolian Baiti", Font.BOLD, 17));
		depty1.setBounds(460, 100, 30, 20);
		frame.getContentPane().add(depty1);
		depty1.setText("8");
		depty1.setLineWrap(true);
		depty1.setWrapStyleWord(true);
		frame.setLocationRelativeTo ( null );

		JTextArea depty2 = new JTextArea();
		depty2.setFont(new Font("Mongolian Baiti", Font.BOLD, 17));
		depty2.setBounds(460, 145, 30, 20);
		frame.getContentPane().add(depty2);
		depty2.setText("8");
		depty2.setLineWrap(true);
		depty2.setWrapStyleWord(true);
		frame.setLocationRelativeTo ( null );

		JLabel candidatelable1 = new JLabel("Candidate & Range");
		candidatelable1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		candidatelable1.setBounds(520, 75, 150, 20);
		frame.getContentPane().add(candidatelable1);

		JTextArea range1 = new JTextArea();
		range1.setFont(new Font("Mongolian Baiti", Font.BOLD, 17));
		range1.setBounds(625, 100, 30, 20);
		frame.getContentPane().add(range1);
		range1.setText("1");
		range1.setLineWrap(true);
		range1.setWrapStyleWord(true);
		frame.setLocationRelativeTo ( null );

		JTextArea range2 = new JTextArea();
		range2.setFont(new Font("Mongolian Baiti", Font.BOLD, 17));
		range2.setBounds(625, 145, 30, 20);
		frame.getContentPane().add(range2);
		range2.setText("1");
		range2.setLineWrap(true);
		range2.setWrapStyleWord(true);
		frame.setLocationRelativeTo ( null );

		JCheckBox candidatecheck1 = new JCheckBox("");
		candidatecheck1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!candidatecheck1.isSelected()) {
					range1.setEditable(false);
					range1.setText("");
					frame.remove(range1);
					frame.repaint();
				}
				else {
					range1.setEditable(true);
					range1.setText("1");
					range1.setBounds(625, 100, 30, 20);
					frame.getContentPane().add(range1);
					frame.repaint();
				}
			}
		});
		candidatecheck1.setBounds(540, 100, 20, 20);
		candidatecheck1.setSelected(true);
		frame.getContentPane().add(candidatecheck1);

		JCheckBox candidatecheck2 = new JCheckBox("");
		candidatecheck2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!candidatecheck2.isSelected()) {
					range2.setEditable(false);
					range2.setText("");
					frame.remove(range2);
					frame.repaint();
				}
				else {
					range2.setEditable(true);
					range2.setText("1");
					range2.setBounds(625, 145, 30, 20);
					frame.getContentPane().add(range2);
					frame.repaint();
				}
			}
		});
		candidatecheck2.setBounds(540, 145, 20, 20);
		candidatecheck2.setSelected(true);
		frame.getContentPane().add(candidatecheck2);

		JLabel reportlable1 = new JLabel("Report file name:");
		reportlable1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		reportlable1.setBounds(10, 200, 130, 20);
		frame.getContentPane().add(reportlable1);

		JTextArea reportarea = new JTextArea();
		reportarea.setFont(new Font("Mongolian Baiti", Font.BOLD, 17));
		reportarea.setBounds(150, 200, 200, 20);
		frame.getContentPane().add(reportarea);
		reportarea.setText("Result Report");
		JScrollPane scroll1 = new JScrollPane(reportarea);
		scroll1.setBounds(150, 200, 200, 20);                     
		frame.getContentPane().add(scroll1);
		reportarea.setLineWrap(true);
		reportarea.setWrapStyleWord(true);
		frame.setLocationRelativeTo ( null );

		JLabel testlable = new JLabel("Test Number:");
		testlable.setFont(new Font("Times New Roman", Font.BOLD, 17));
		testlable.setBounds(400, 200, 130, 20);
		frame.getContentPane().add(testlable);

		JTextArea testarea = new JTextArea();
		testarea.setFont(new Font("Mongolian Baiti", Font.BOLD, 17));
		testarea.setBounds(520, 200, 50, 20);
		frame.getContentPane().add(testarea);
		testarea.setText("100");
		testarea.setLineWrap(true);
		testarea.setWrapStyleWord(true);
		frame.setLocationRelativeTo ( null );

		//////////////////////////////////////////////

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
				Scanner scr=new Scanner(reportarea.getText());
				reportname = scr.nextLine()+".txt";
				Scanner scr1=new Scanner(testarea.getText());
				testnum = Integer.valueOf(scr1.nextLine());
				File file =new File(reportname);
				FileWriter fr = null;
				try {
					fr = new FileWriter(file,true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BufferedWriter br = new BufferedWriter(fr);
				try {
					br.write(reportname+"\n");
					br.write("Start Time: "+new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime())+"\n");
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try{
					br.close();
				}
				catch(IOException e1){
					e1.printStackTrace();
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
				gui.gameend=gameend;
				gui.reportname = reportname;
				gui.testnum = testnum;
				gui.frame.setVisible(true);
				if (gameend) {
					gui.gameend=false;
					gui.k=1;
					gui.movinglist.clear();
					for(int i=0;i<15;i++) {
						for(int j=0;j<15;j++) {
							board[i][j]='-';
						}
					}
				}
				gui.btnsimulate.doClick();
			}
		});
		ApllyButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		ApllyButton.setBackground(Color.GREEN);
		ApllyButton.setForeground(Color.RED);
		ApllyButton.setBounds(425, 250, 149, 38);
		frame.getContentPane().add(ApllyButton);

		JButton Cancelbtn = new JButton("Cancel");
		Cancelbtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		Cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				Gomoku_GUI gui = new Gomoku_GUI();
				gui.k=k;
				gui.board=board;
				gui.simuApply=false;
				gui.simuB=Black;
				gui.simuW=White;
				gui.movinglist=movinglist;
				gui.Animation=Animation;
				gui.gameend=gameend;
				gui.AT1animation=AT1animation;
				gui.reportname = reportname;
				gui.testnum = testnum;
				gui.frame.setVisible(true);
			}
		});
		Cancelbtn.setForeground(Color.GREEN);
		Cancelbtn.setBackground(Color.RED);
		Cancelbtn.setBounds(91, 250, 149, 33);
		frame.getContentPane().add(Cancelbtn);

		JPanel sepLine1 = new JPanel() {	   
			private static final long serialVersionUID = 1L;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.RED);
				g.drawLine(0, 0, 320, 0);
				g.drawLine(0, 0, 0, 110);
				g.drawLine(0, 109, 320, 109);
				g.drawLine(319, 0, 319, 109);

				g.drawLine(0, 1, 320, 1);
				g.drawLine(1, 0, 1, 110);
				g.drawLine(0, 108, 320, 108);
				g.drawLine(318, 0, 318, 109);
			}
		};
		sepLine1.setBounds(355, 70, 320, 110);
		frame.getContentPane().add(sepLine1);
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent e) {
		    	Cancelbtn.doClick();
		    }
		});
	}
}
