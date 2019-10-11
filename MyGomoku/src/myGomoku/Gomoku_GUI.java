package myGomoku;
/*
 * Author: Han Liao (lhan@iastate.edu or leslieileo@gmail.com)
 * This is the project for creative component in ISU
 */
import java.awt.Color;
import myGomoku.Back_End;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.SimpleDateFormat;

public class Gomoku_GUI {

	public JFrame frame;
	private JPanel BoardPanel = new JPanel();
	private JLabel number = new JLabel();
	public long starttime,endtime,avgtime,iteration=1;
	private int mouseX,mouseY,mouseX1,mouseY1;
	private int[] AI=new int[2];
	private JLabel animationlabel=new JLabel();
	private JLabel evaluatorlabel=new JLabel();
	private JLabel modelabel=new JLabel();
	public char[][] board= new char[15][15];
	public ArrayList<Moves> movinglist=new ArrayList<Moves>();
	private JLabel num= new JLabel();
	private JLabel num1= new JLabel();
	private JTextArea Analysis = new JTextArea();
	private JTextArea Analysis1 = new JTextArea();
	private int k=1;
	private boolean pvp=true;
	private boolean gameend=false;
	private Back_End backend= new Back_End();
	private ArrayList<Moves> five=new ArrayList<Moves>();
	private boolean readfile=false;
	private boolean evaluator=true;
	private int[] evaluatorrange=new int[4];
	private JButton btnsimulate = new JButton("Simulate");
	private Timer TM=null;
	private Timer TM1=null;
	private ArrayList<Moves> OpeningAry = new ArrayList<Moves>();
	private ArrayList<Moves> Candidate = new ArrayList<Moves>();
	private boolean AT1animation=false;
	private boolean Animation=true;
	private boolean isOpening=false;
	private boolean OpeningFlag=true;
	private boolean AIboolean=false;
	private int Delay=500;
	private BoardState evaluresultB;
	private BoardState evaluresultW;
	private String AIversion="Greedy";
	public static int depth=2;
	public static int Mydepth=6;
	public String simuW="Greedy";
	public String simuB="Minimax";
	public boolean simuApply=false;
	Simulator_Menu SM = new Simulator_Menu();
	//Simulator_Window SW = new Simulator_Window();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gomoku_GUI window = new Gomoku_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Gomoku_GUI() {
		initialize();
	}

	private void initialize() {
		for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				board[i][j]='-';
			}
		}
		TM= new Timer(0, null);
		TM1=new Timer(0,null);
		frame = new JFrame();
		frame.setResizable(false);
		ImageIcon beam=null;
		beam = new ImageIcon(this.getClass().getClassLoader().getResource("GomokuIcon.png"));
		Image icon=beam.getImage();
		frame.setIconImage(icon);
		frame.setBounds(100, 100, 1305, 660);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("My Gomoku_HAN LIAO");
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(255,250,240));
		ImageIcon beam2=null;
		beam2 = new ImageIcon(this.getClass().getClassLoader().getResource("Ocean.jpg"));
		Image icon2=beam2.getImage();
		JLabel background = new JLabel(new ImageIcon(icon2));
		frame.setContentPane(background);

		animationlabel=new JLabel("ON");
		animationlabel.setForeground(Color.BLACK);
		animationlabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
		animationlabel.setBounds(891, 575, 40, 35);
		frame.getContentPane().add(animationlabel);

		evaluatorlabel=new JLabel("ON");
		evaluatorlabel.setForeground(Color.BLACK);
		evaluatorlabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
		evaluatorlabel.setBounds(1032, 575, 40, 35);
		frame.getContentPane().add(evaluatorlabel);
		int damn=575;
		if(pvp) {
			damn=521;
		}
		modelabel=new JLabel("->                   <-");
		modelabel.setForeground(Color.RED);
		modelabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 15));
		modelabel.setBounds(654 , damn, 150, 35);
		frame.getContentPane().add(modelabel);

		BoardPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			public void paintComponent (Graphics g) {
				boolean isAntialiased = true;
				if ( isAntialiased )
					((Graphics2D) g).setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
				super.paintComponent(g);
				ImageIcon beam3=null;
				beam3 = new ImageIcon(this.getClass().getClassLoader().getResource("WOODBKG.png"));
				Image icon3=beam3.getImage();	
				frame.getContentPane().setLayout(null);
				g.drawImage(icon3, 0, 0, null);
				g.setColor(Color.BLACK);
				int r=0,c=0;
				for(int i=0; i<15 ; i++) {
					g.drawLine(20,r+20,548+20,r+20);
					g.drawLine(0+20, r+1+20, 548+20, r+1+20);
					g.drawLine(0+20, r+2+20, 548+20, r+2+20);
					r=r+39;
					g.drawLine(c+20,0+20,c+20,548+20);
					g.drawLine(c+1+20, 0+20, c+1+20, 548+20);
					g.drawLine(c+2+20, 0+20, c+2+20, 548+20);
					c=c+39;
				}
				g.drawLine(19,19,548+21,19);g.drawLine(19,19+549,548+21,19+549);
				g.drawLine(19,18,548+21,18);g.drawLine(19,20+549,548+21,20+549);
				g.drawLine(19,21+549,548+21,21+549);
				g.drawLine(19,19,19,548+21);g.drawLine(19+550,19,19+550,548+21);
				g.drawLine(18,18,18,548+22);g.drawLine(20+550,18,20+550,548+22);
				g.fillOval(112+20, 112+20, 12, 12);
				g.fillOval(424+20, 112+20, 12, 12);
				g.fillOval(112+20, 424+20, 12, 12);
				g.fillOval(424+20, 424+20, 12, 12);
				g.fillOval(268+20, 268+20, 12, 12);

				if(isOpening) {
					if(AIboolean) {
						TM1.start();
						TM1.setRepeats(false);
					}
					for(int i=0;i<OpeningAry.size();i++) {
						int x=OpeningAry.get(i).getX()*39+1-5+20;
						int y=OpeningAry.get(i).getY()*39+1-5+20;
						g.setColor(Color.RED);
						g.fillOval(x, y, 10, 10);	
					}
					OpeningAry.clear();
					isOpening=false;
				}

				for(int i=0;i<movinglist.size();i++) {
					int x=movinglist.get(i).getX()*39+1-15+20;
					int y=movinglist.get(i).getY()*39+1-15+20;
					if(movinglist.get(i).getStone().equals("WHITE")) {
						g.setColor(Color.WHITE);
						g.fillOval(x, y, 30, 30);
					}
					else {
						g.setColor(Color.BLACK);
						g.fillOval(x, y, 30, 30);
					}
				}
				if(five.size()!=0) {
					int maxX=0,maxY=0,minX=14,minY=14;
					int temp;
					boolean inverse =false;
					if(five.get(0).getX()>five.get(1).getX()&& five.get(0).getY()<five.get(1).getY()) {
						inverse=true;;
					}
					if(five.get(0).getX()<five.get(1).getX()&& five.get(0).getY()>five.get(1).getY()) {
						inverse=true;;
					}
					for(int f1=0;f1<five.size();f1++) {
						g.setColor(Color.RED);
						g.fillOval(five.get(f1).getX()*39+1-15+20, five.get(f1).getY()*39+1-15+20, 30, 30);
						maxX=Math.max(maxX,five.get(f1).getX());
						minX=Math.min(minX,five.get(f1).getX());
						maxY=Math.max(maxY,five.get(f1).getY());
						minY=Math.min(minY,five.get(f1).getY());
					}
					if(inverse) {
						temp=maxX;maxX=minX;minX=temp;
					}
					g.setColor(new Color(255,0,0));
					g.drawLine(maxX*39+1+20, maxY*39+1+20, minX*39+1+20, minY*39+1+20);
					g.drawLine(maxX*39+2+20, maxY*39+1+20, minX*39+2+20, minY*39+1+20);
					g.drawLine(maxX*39+20, maxY*39+1+20, minX*39+20, minY*39+1+20);
					g.drawLine(maxX*39-1+20, maxY*39+1+20, minX*39-1+20, minY*39+1+20);
					g.drawLine(maxX*39+3+20, maxY*39+1+20, minX*39+3+20, minY*39+1+20);
					g.drawLine(maxX*39-2+20, maxY*39+1+20, minX*39-2+20, minY*39+1+20);
					g.drawLine(maxX*39+4+20, maxY*39+1+20, minX*39+4+20, minY*39+1+20);
					g.drawLine(maxX*39+1+20, maxY*39+2+20, minX*39+1+20, minY*39+2+20);
					g.drawLine(maxX*39+1+20, maxY*39+20, minX*39+1+20, minY*39+20);
					g.drawLine(maxX*39+1+20, maxY*39+3+20, minX*39+1+20, minY*39+3+20);
					g.drawLine(maxX*39+1+20, maxY*39-1+20, minX*39+1+20, minY*39-1+20);
				}
				if(evaluator) {
					g.setColor(Color.RED);
					evaluatorrange=new int[]{0,0,0,0};
					evaluatorrange=backend.scale(movinglist);
					if(evaluatorrange!=null) {
						g.drawLine(evaluatorrange[0]*39+1+20-10, evaluatorrange[1]*39+1+20-10, evaluatorrange[2]*39+1+20+10, evaluatorrange[1]*39+1+20-10);
						g.drawLine(evaluatorrange[0]*39+1+20-10, evaluatorrange[3]*39+1+20+10, evaluatorrange[2]*39+1+20+10, evaluatorrange[3]*39+1+20+10);
						g.drawLine(evaluatorrange[0]*39+1+20-10, evaluatorrange[1]*39+1+20-10,evaluatorrange[0]*39+1+20-10, evaluatorrange[3]*39+1+20+10);
						g.drawLine( evaluatorrange[2]*39+1+20+10, evaluatorrange[1]*39+1+20-10, evaluatorrange[2]*39+1+20+10, evaluatorrange[3]*39+1+20+10);

						g.drawLine(evaluatorrange[0]*39+1+20-11, evaluatorrange[1]*39+1+20-11, evaluatorrange[2]*39+1+20+11, evaluatorrange[1]*39+1+20-11);
						g.drawLine(evaluatorrange[0]*39+1+20-11, evaluatorrange[3]*39+1+20+11, evaluatorrange[2]*39+1+20+11, evaluatorrange[3]*39+1+20+11);
						g.drawLine(evaluatorrange[0]*39+1+20-11, evaluatorrange[1]*39+1+20-11,evaluatorrange[0]*39+1+20-11, evaluatorrange[3]*39+1+20+11);
						g.drawLine( evaluatorrange[2]*39+1+20+11, evaluatorrange[1]*39+1+20-11, evaluatorrange[2]*39+1+20+11, evaluatorrange[3]*39+1+20+11);

						g.drawLine(evaluatorrange[0]*39+1+20-12, evaluatorrange[1]*39+1+20-12, evaluatorrange[2]*39+1+20+12, evaluatorrange[1]*39+1+20-12);
						g.drawLine(evaluatorrange[0]*39+1+20-12, evaluatorrange[3]*39+1+20+12, evaluatorrange[2]*39+1+20+12, evaluatorrange[3]*39+1+20+12);
						g.drawLine(evaluatorrange[0]*39+1+20-12, evaluatorrange[1]*39+1+20-12,evaluatorrange[0]*39+1+20-12, evaluatorrange[3]*39+1+20+12);
						g.drawLine(evaluatorrange[2]*39+1+20+12, evaluatorrange[1]*39+1+20-12,  evaluatorrange[2]*39+1+20+12, evaluatorrange[3]*39+1+20+12);}

					for(int i=0;i<Candidate.size();i++) {
						int max = Candidate.get(0).getPercentage();
						int x=Candidate.get(i).getX()*39+1-5+20;
						int y=Candidate.get(i).getY()*39+1-5+20;
						if(Candidate.get(i).getPercentage()==max) {
							g.setColor(Color.RED);
							g.fillOval(x, y, 10, 10);}
						else {
							g.setColor(Color.BLUE);
							g.fillOval(x, y, 10, 10);	
						}
					}
					Candidate.clear();
				}
				if(AT1animation) {
					TM.start();
					TM.setRepeats(false);
				}
				labelindex(movinglist);	
			}
		};

		
		ActionListener FkU=new ActionListener() {	
			public void actionPerformed(ActionEvent arg0) {
				AIboolean=true;
				boolean flag=true;
				Analysis1.setText("Current mode:AI V1\nYou can press 'STOP' to terminate.\n\n");
				if(evaluator) {
					Analysis1.append("Evaluator is: ON\n");
				}
				else {Analysis1.append("Evaluator is: OFF\n");}

				if(!simuApply) {
				Simulator_Menu SM = new Simulator_Menu();
				//new Thread(new Simulator_Menu()).start();
				SM.run();
				SM.board=board;
				SM.movinglist=movinglist;
				SM.Black=simuB;
				SM.White=simuW;
				frame.dispose();
				}
				//simuApply=false;
				
//				
//				while (true) {
//					if (SM.frame.isVisible()) {
//						System.out.print("in");
//					}
//					else {
//						System.out.print("out");
//						break;
//					}
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e1) {
//						break;
//					}
//				}

				

				
				//	Analysis1.setText(SM.Black);

				//	Analysis1.append(SM.White);



								Analysis1.append("Processing.");
								String a;
								for( int i=1;i<=k;i++) {
									if(i%3==0) {
										Analysis1.append(".");
										if(i%15==0){
											if(evaluator) {
												a="Current mode:AI V1\nYou can press 'STOP' to terminate.\n\nEvaluator is: ON\nProcessing";}
											else {a= "Current mode:AI V1\nYou can press 'STOP' to terminate.\n\nEvaluator is: OFF\nProcessing";}
											Analysis1.setText(a);
										}
									}
								}
								if(Animation) {
									TM= new Timer(Delay,this);
									TM.setRepeats(false);}
								if(gameend) {
									TM.stop();
									AT1animation=false;
									BoardPanel.repaint();
									Analysis1.setForeground(Color.RED);
									Analysis1.setText("Game is over.\nClick 'New game' button to clear board");
								}
								else if(Animation){
									AT1animation=true;
								}
								else {
									AT1animation=false;
								}
								if(!gameend&&k<5) {
									while(flag==true&&k<5) {
										if(k<5&&OpeningFlag==true) {
											OpeningBook ob= new OpeningBook();	
											OpeningAry=ob.Opeing(movinglist,k);
											Random rand=new Random();
											int	 key=rand.nextInt(OpeningAry.size());
											AI[0]=OpeningAry.get(key).getX();
											AI[1]=OpeningAry.get(key).getY();
											if(!isOpening) {
												TM1= new Timer(Delay,this);
												TM1.setRepeats(false);
												isOpening=true;
												BoardPanel.revalidate();
												BoardPanel.repaint();
												Analysis1.setText("Opening book is processing!!");
												OpeningFlag=false;
											}
										}
										else if(!OpeningFlag) {
											OpeningFlag=true;
											TM1.stop();
											if(k%2==0) {
												board[AI[0]][AI[1]]='W';
												movinglist.add (new Moves( AI[0], AI[1], k, "WHITE")) ;
											}
											else {
												board[AI[0]][AI[1]]='B';
												movinglist.add (new Moves( AI[0], AI[1], k, "BLACK")) ;
											}
											Analysis1.setText("Board evaluator information will be shown there: \n\nEvaluator is on by default.\nAnimation is on by default.\n\nYou can turn it off by pressing 'Evaluator' button\n\n");
											BoardPanel.revalidate();
											BoardPanel.repaint();	
											labelindex(movinglist);	
											k++;
										}
										if(AT1animation) {flag=false;}
										else {flag=true;isOpening=false;}
									}
								}
								if(!gameend&&k>=5) {
									flag=true;
									while(!gameend&&flag==true) {
				
										if(evaluator) {
											evaluatorrange=backend.scale(movinglist);
				
										}
										else {
											backend.scaled=new int[] {0,0,14,14};
										}
										if(five.size()!=5) {
											for(int n=0;n<iteration;n++) {
												starttime = System.nanoTime();
												if(k%2==0) {//white
													if(simuW=="MyAI") {AI=backend.MyAI(board,k,movinglist,Mydepth,true);}
													else if(simuW=="Greedy") {AI=backend.Greedy(board,k,movinglist);}
													else if(simuW=="Minimax") {AI=backend.Minimax(board,k,movinglist,depth,true);}
													Analysis1.append("\n\nBlack side Algo: "+simuB+"\n\n");
													Analysis1.append("White side Algo: "+simuW);
												}


												else {
													if(simuB=="MyAI") {AI=backend.MyAI(board,k,movinglist,Mydepth,true);}
													else if(simuB=="Greedy") {AI=backend.Greedy(board,k,movinglist);}
													else if(simuB=="Minimax") {AI=backend.Minimax(board,k,movinglist,depth,true);}
													Analysis1.append("\n\nBlack side Algo: "+simuB+"\n\n");
													Analysis1.append("White side Algo: "+simuW);

												}
												endtime = System.nanoTime();
												avgtime+=(endtime-starttime);
											}
											avgtime/=iteration;
											if(k%2==0) {
												board[AI[0]][AI[1]]='W';
												movinglist.add (new Moves( AI[0], AI[1], k, "WHITE")) ;
											}
											else if(k<=224){
												board[AI[0]][AI[1]]='B';
												movinglist.add (new Moves( AI[0], AI[1], k, "BLACK")) ;
											}
											else {
												board[AI[0]][AI[1]]='B';
												movinglist.add (new Moves( AI[0], AI[1], k, "BLACK")) ;
											}
											if(k>=5) {
												Candidate= backend.SortedCandidate(movinglist,board);
											}
											five.clear();
											endCheck();
											BoardPanel.revalidate();
											BoardPanel.repaint();
											labelindex(movinglist);
											if(five.size()!=0||k==225) {
												gameend=true;
											} 
										}
										k++;
										if(AT1animation) {flag=false;}
										else {flag=true;}
									}
								}
								if(gameend) {
									//TO DO make stop button is pressed
								}
			}
		};
		btnsimulate.addActionListener(FkU);
		btnsimulate.setBackground(new Color(255,215,0));
		btnsimulate.setBounds(935, 470, 97, 25);
		frame.getContentPane().add(btnsimulate);
		frame.setLocationRelativeTo(null);


		BoardPanel.setBounds(41, 20, 589,589);
		BoardPanel.setBackground(new Color(0,250,154));
		frame.getContentPane().add(BoardPanel);
		BoardPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX1= e.getX();
				mouseY1= e.getY();
				int[] temp=backend.stoneposition(mouseX1, mouseY1);
				Analysis.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
				Analysis.setForeground(Color.BLACK);
				Analysis.setText("\n\n\n\n\n\n      Your posible move could be\n\n"+"                  [ "+(temp[0]-21)/39+" , "+(temp[1]-21)/39+" ]"+"\n\n");
				if(board[(temp[0]-21)/39][(temp[1]-21)/39]!='-'&&!gameend) {
					Analysis.setForeground(Color.RED);
					Analysis.append("      It's an OCCUPIED postion!!");
				}
				else if(!gameend){

					if(AIversion.equals("Greedy")) {
						Analysis.append("     Release to comfile the reqest. \n     Or hold it and move to outside        board to cancel the reqeust.\n\nIt takes time by using Greedy algorithm.");}
					else if(AIversion.equals("Minimax")) {
						Analysis.append("     Release to comfile the reqest. \n     Or hold it and move to outside        board to cancel the reqeust.\n\nIt takes time by using Minimax algorithm.");}

					else {
						Analysis.append("     Release to comfile the reqest. \n     Or hold it and move to outside        board to cancel the reqeust.\n\nIt takes time by using Minimax and New Heuristic algorithm.");}
				}
				else {
					Analysis.setForeground(Color.RED);
					Analysis.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
					Analysis.setText("\n\n\n\n\n\n               Game is over!!\n\n       Press 'new game' button to          restart!!!");
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				AIboolean=false;
				Analysis.setForeground(Color.BLACK);
				Analysis.setFont(new Font("Mongolian Baiti", Font.BOLD, 16));
				int[] closed=new int[2];
				mouseX= e.getX();
				mouseY= e.getY();
				if((mouseX<=598&&mouseX>=0)&&(mouseY<=598&&mouseY>=0)&&(Math.abs(mouseX1-mouseX)<=30||Math.abs(mouseY1-mouseY)<=30)) {
					closed=backend.stoneposition(mouseX, mouseY);
					int x=closed[0];
					int y=closed[1];
					if(x!=0 && y!=0 && board[(x-21)/39][(y-21)/39]=='-'&&!gameend) {
						if(k%2!=0) {
							board[(x-21)/39][(y-21)/39]='B';
							movinglist.add (new Moves( (x-21)/39, (y-21)/39, k, "BLACK")) ;	
							k++;}
						else {
							board[(x-1)/39][(y-1)/39]='W';
							movinglist.add (new Moves( (x-21)/39, (y-21)/39, k, "WHITE")) ;	
							k++;
						}

						endCheck();
						if(!pvp&&!gameend) {
							if(k<5&&OpeningFlag==true) {
								OpeningBook ob= new OpeningBook();	
								OpeningAry=ob.Opeing(movinglist,k);
								Random rand=new Random();
								int	 key=rand.nextInt(OpeningAry.size());
								AI[0]=OpeningAry.get(key).getX();
								AI[1]=OpeningAry.get(key).getY();
								OpeningAry.clear();

								if(k%2==0) {
									board[AI[0]][AI[1]]='W';
									movinglist.add (new Moves( AI[0], AI[1], k, "WHITE")) ;
								}
								else {
									board[AI[0]][AI[1]]='B';
									movinglist.add (new Moves( AI[0], AI[1], k, "BLACK")) ;
								}
								k++;
								isOpening=true;
								if(k<4) {
									isOpening=true;
									OpeningAry=ob.Opeing(movinglist,k);
								}
								else {
									isOpening=false;
								}
								Analysis1.setText("Board evaluator information will be shown there: \n\nEvaluator is on by default.\nAnimation is on by default.\n\nYou can turn it off by pressing 'Evaluator' button\n\n");

								BoardPanel.revalidate();
								BoardPanel.repaint();	
								labelindex(movinglist);	

							}
							else if(!gameend&&k>=5) {
								for(int n=0;n<iteration;n++) {
									starttime = System.nanoTime();
									if(AIversion.equals("Greedy")) {
										AI=backend.Greedy(board,k,movinglist);}
									else if(AIversion.equals("Minimax")) {
										AI=backend.Minimax(board,k,movinglist,depth,true);}
									else {
										AI=backend.MyAI(board,k,movinglist,Mydepth,true);
									}
									endtime = System.nanoTime();
									avgtime+=(endtime-starttime);
								}
								avgtime/=iteration;
								if(k%2==0) {
									board[AI[0]][AI[1]]='W';
									movinglist.add (new Moves( AI[0], AI[1], k, "WHITE")) ;
									k++;}
								else {
									board[AI[0]][AI[1]]='B';
									movinglist.add (new Moves( AI[0], AI[1], k, "BLACK")) ;
									k++;}
							}
						}

						if(evaluator) {
							evaluatorrange=backend.scale(movinglist);
							Analysis1.setForeground(Color.BLACK);
							Analysis1.setFont(new Font("Mongolian Baiti", Font.BOLD, 16));
							Analysis1.setText("Evaluator is ON!!\nEvaluate range is:\n"+evaluatorrange[0]+" < x < "+evaluatorrange[2]+"\n"+evaluatorrange[1]+" < y < "+evaluatorrange[3]+"\n");
							if(k<5) {Analysis1.append("Opening book is processing!!");}
						}
						else {
							Analysis1.setForeground(Color.RED);
							Analysis1.setFont(new Font("Mongolian Baiti", Font.BOLD, 16));
							Analysis1.setText("Evaluator is OFF!!\n\nClick 'Evaluator' button to turn it on!!\n\n");
							if(k<5) {Analysis1.append("Opening book is processing!!");}
						}
						if(k>=5) {
							Candidate= backend.SortedCandidate(movinglist,board);
						}
						five.clear();
						endCheck();
						BoardPanel.revalidate();
						BoardPanel.repaint();
						labelindex(movinglist);
						if(five.size()!=0||k==225) {
							gameend=true;
						} 
					}
					else if(!gameend){
						Analysis.setForeground(Color.RED);
						Analysis.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
						Analysis.setText("\n\n\n\n\n\n       Moving request denied!!\n\n       Try another position!!!");
					}
					else {
						Analysis.setForeground(Color.RED);
						Analysis.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
						Analysis.setText("\n\n\n\n\n\n               Game is over!!\n\n       Press 'new game' button to          restart!!!");
					}
				}
				else {
					Analysis.setForeground(Color.RED);
					Analysis.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
					Analysis.setText("\n\n\n\n\n\n\n\n    The Moving request is cancled!!!");
				}
				evaluresultB= backend.evaluator(board, movinglist,1);
				Analysis1.append("\nBlack side board state and value:");
				Analysis1.append(evaluresultB.ToString());

				evaluresultW= backend.evaluator(board, movinglist,2);
				Analysis1.append("\nWhite side board state and value:");
				Analysis1.append(evaluresultW.ToString());

				for(int i=0;i<Candidate.size();i++) {
					Analysis.append(Candidate.get(i).PercentageToString());
				}

				//				ArrayList<Moves> bestpath=backend.BestPath();
				//				Analysis.append(bestpath.toString());

			}
		});

		int r=15,c=35;
		for (int i=0;i<15; i++) {
			frame.getContentPane().setLayout(null);
			number = new JLabel(String.valueOf(i),SwingConstants.CENTER);
			number.setBounds(20, r+19, 20, 20);
			frame.getContentPane().add(number);
			number.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
			number.setForeground(Color.YELLOW);
			r+=39;
			number = new JLabel(String.valueOf(i),SwingConstants.CENTER);
			number.setBounds(c+19, 0, 20, 20);
			frame.getContentPane().add(number);
			number.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
			number.setForeground(Color.YELLOW);
			c+=39;
		}

		Analysis.setFont(new Font("Mongolian Baiti", Font.BOLD, 16));
		Analysis.setBounds(602, 20, 301, 437);
		Analysis.setLineWrap(true);
		Analysis.setWrapStyleWord(true);
		Analysis.setCaretColor(Analysis.getBackground());
		Analysis.setText("Welcome to MyGomoku!!\n\n"+"Date: "+new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime())+"\n\n");
		if(pvp) {
			Analysis.append("Mode: PvP.\nClick 'PvAI' button to switch mode!!\n\n");}
		else {Analysis.append("Mode: PvAI.\nClick 'PvP' button to switch mode!!\n\n");}
		Analysis.append("Process analysis will be shown there: \n");

		Analysis.setForeground(Color.BLACK);
		Analysis.setBackground(new Color(255,250,240));
		frame.getContentPane().add(Analysis);
		JScrollPane scroll = new JScrollPane(Analysis);
		scroll.setBounds(642, 20, 301, 437);
		frame.getContentPane().add(scroll);

		Analysis1.setFont(new Font("Mongolian Baiti", Font.BOLD, 16));
		Analysis1.setBounds(900, 20, 301, 437);
		Analysis1.setLineWrap(true);
		Analysis1.setCaretColor(Analysis1.getBackground());
		Analysis1.setWrapStyleWord(true);
		Analysis1.setText("Board evaluator information will be shown there: \n\nEvaluator is on by default.\nAnimation is on by default.\n\nYou can turn it off by pressing 'Evaluator' button\n\n");
		Analysis1.setForeground(Color.BLACK);
		Analysis1.setBackground(new Color(255,250,240));
		frame.getContentPane().add(Analysis1);
		JScrollPane scroll1 = new JScrollPane(Analysis1);
		scroll1.setBounds(950, 20, 301, 437);
		frame.getContentPane().add(scroll1);

		JButton btnUndo = new JButton("UNDO");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				five.clear();
				gameend=false;
				if(movinglist.size()!=0) {
					if(!pvp && movinglist.size()!=1) {
						k-=2;
						BoardPanel.removeAll();
						int y=movinglist.get(movinglist.size()-1).getY();
						int x=movinglist.get(movinglist.size()-1).getX();
						int z=movinglist.get(movinglist.size()-2).getX();
						int e=movinglist.get(movinglist.size()-2).getY();

						board[x][y]='-';
						board[z][e]='-';
						movinglist.remove(movinglist.size()-1);
						movinglist.remove(movinglist.size()-1);}
					else {
						k--;
						BoardPanel.removeAll();
						int y=movinglist.get(movinglist.size()-1).getY();
						int x=movinglist.get(movinglist.size()-1).getX();
						board[x][y]='-';
						movinglist.remove(movinglist.size()-1);
					}	

					BoardPanel.revalidate();
					BoardPanel.repaint();
					labelindex(movinglist);
					ToString(board);
				}
				else {
					Analysis.setForeground(Color.RED);
					Analysis.setText("");
					Analysis.setText("Board is clear!!\nYou can start a new game now!!\n");
					if(pvp) {
						Analysis.append("Mode is: PvP");
					}
					else {Analysis.append("Mode is: PvAI");}
					BoardPanel.revalidate();
					BoardPanel.repaint();
				}
				Analysis1.setText("");
				if(Animation&&movinglist.size()!=0) {
					evaluatorrange=backend.scale(movinglist);
					Analysis1.setForeground(Color.BLACK);
					Analysis1.setFont(new Font("Mongolian Baiti", Font.BOLD, 16));
					Analysis1.append("Animatoin is On!!\n");
				}
				else if(!Animation&&movinglist.size()==0) {
					Analysis1.setForeground(Color.BLACK);
					Analysis1.setFont(new Font("Mongolian Baiti", Font.BOLD, 16));
					Analysis1.setText("Animation is OFF!!\n");
				}
				else {
					Analysis1.setForeground(Color.RED);
					Analysis1.setFont(new Font("Mongolian Baiti", Font.BOLD, 16));
					Analysis1.setText("Evaluator is OFF!!\n\nClick 'evaluaotr' button to turn it on!!\n");}

				if(evaluator&&movinglist.size()!=0) {
					evaluatorrange=backend.scale(movinglist);
					Analysis1.setForeground(Color.BLACK);
					Analysis1.setFont(new Font("Mongolian Baiti", Font.BOLD, 16));
					Analysis1.append("Evaluator is On!!\nEvaluate range is:\n"+evaluatorrange[0]+" < x < "+evaluatorrange[2]+"\n"+evaluatorrange[1]+" < y < "+evaluatorrange[3]+"\n");
				}
				else if(evaluator&&movinglist.size()==0) {
					Analysis1.setForeground(Color.BLACK);
					Analysis1.setFont(new Font("Mongolian Baiti", Font.BOLD, 16));
					Analysis1.append("Evaluator is ON!!\n");
				}
				else {
					Analysis1.setForeground(Color.RED);
					Analysis1.setFont(new Font("Mongolian Baiti", Font.BOLD, 16));
					Analysis1.setText("Evaluator is OFF!!\n\nClick 'evaluaotr' button to turn it on!!\n");}
				TM.stop();
				TM1.stop();
				AT1animation=false;
				AIboolean=false;
				if(!pvp) {
					if(k<5) {
						OpeningBook ob = new OpeningBook();
						isOpening=true;
						OpeningAry=ob.Opeing(movinglist,k);

					}
					else {
						isOpening=false;
					}
					TM.stop();
					TM1.stop();
					AT1animation=false;
					Analysis1.setText("Board evaluator information will be shown there: \n\nEvaluator is on by default.\nAnimation is on by default.\n\nYou can turn it off by pressing 'Evaluator' button\n\n");
					BoardPanel.revalidate();
					BoardPanel.repaint();	}
			}
		});
		btnUndo.setBackground(new Color(255,99,71));
		btnUndo.setBounds(794, 470, 97, 25);
		frame.getContentPane().add(btnUndo);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				TM1.stop();
				TM.stop();
				AT1animation=false;
				AIboolean=false;
				Analysis.setForeground(Color.BLACK);
				Analysis.setFont(new Font("Mongolian Baiti", Font.BOLD, 16));
				k=1;
				simuApply=false;
				gameend=false;
				five.clear();
				BoardPanel.removeAll();
				movinglist.removeAll(movinglist);
				num1.removeAll();
				num.removeAll();
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						board[i][j]='-';
					}
				}
				BoardPanel.revalidate();
				BoardPanel.repaint();
				Analysis.setText("New game started !!\n\n");
				if(pvp) {Analysis.append("Mode is: PvP.\nClick 'PVP' button to switch PvAI mode");}
				else {Analysis.append("Mode is: PvAI.\nClick 'PVP' button to switch PvP mode");}
				Analysis1.setText("Board evaluator information will be shown there: \n\n");
				String switch1 = null;
				if(evaluator) {switch1="ON";Analysis1.setForeground(Color.BLACK);}
				else {switch1="OFF";Analysis1.setForeground(Color.RED);}
				Analysis1.append("Evaluator is : "+switch1+"\n");
				if(Animation) {
					Analysis1.append("Animation is : ON");}
				else {Analysis1.append("Animation is : OFF");}
			}
		});
		btnNewGame.setBackground(new Color(0,255,0));
		btnNewGame.setBounds(668, 470, 97, 25);
		frame.getContentPane().add(btnNewGame);

		JButton AIversion1 = new JButton("Greedy AI");
		AIversion1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AIversion="Greedy";
				Analysis.setText("Current AI algorithm:\nGreedy Algorithm");
			}
		});
		AIversion1.setBackground(new Color(255,153,255));
		AIversion1.setBounds(1075, 470, 97, 25);
		frame.getContentPane().add(AIversion1);

		JButton AIversion2 = new JButton("Minimax AI");
		AIversion2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AIversion="Minimax";
				Analysis.setText("Current AI algorithm:\nMinimax Algorithm");
			}
		});
		AIversion2.setBackground(new Color(255,153,255));
		AIversion2.setBounds(1075, 524, 97, 25);
		frame.getContentPane().add(AIversion2);

		JButton AIversion3 = new JButton("My AI");
		AIversion3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AIversion="MyAI";
				Analysis.setText("Current AI algorithm:\nMy Algorithm");
			}
		});
		AIversion3.setBackground(new Color(255,153,255));
		AIversion3.setBounds(1075, 578, 97, 25);
		frame.getContentPane().add(AIversion3);

		JButton btnnext = new JButton("Next Move");
		btnnext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AIboolean=true;
				if(k<5&&OpeningFlag==true) {
					OpeningBook ob= new OpeningBook();	
					OpeningAry=ob.Opeing(movinglist,k);
					Random rand=new Random();
					int	 key=rand.nextInt(OpeningAry.size());
					AI[0]=OpeningAry.get(key).getX();
					AI[1]=OpeningAry.get(key).getY();
					if(!isOpening) {
						TM1= new Timer(Delay,this);
						TM1.setRepeats(false);

						isOpening=true;
						BoardPanel.revalidate();
						BoardPanel.repaint();
						Analysis1.setText("Opening book is processing!!");
						OpeningFlag=false;
					}
				}
				else if(!OpeningFlag) {
					OpeningFlag=true;
					TM1.stop();
					if(k%2==0) {
						board[AI[0]][AI[1]]='W';
						movinglist.add (new Moves( AI[0], AI[1], k, "WHITE")) ;
					}
					else {
						board[AI[0]][AI[1]]='B';
						movinglist.add (new Moves( AI[0], AI[1], k, "BLACK")) ;
					}
					Analysis1.setText("Board evaluator information will be shown there: \n\nEvaluator is on by default.\nAnimation is on by default.\n\nYou can turn it off by pressing 'Evaluator' button\n\n");
					BoardPanel.revalidate();
					BoardPanel.repaint();	
					labelindex(movinglist);	
					k++;
				}
				else if(!gameend&&k>=5) {
					if(evaluator) {
						evaluatorrange=backend.scale(movinglist);
					}
					else {
						backend.scaled=new int[] {0,0,14,14};
					}
					if(five.size()!=5) {
						for(int n=0;n<iteration;n++) {
							starttime = System.nanoTime();
							if(AIversion.equals("Greedy")) {
								AI=backend.Greedy(board,k,movinglist);}
							else if(AIversion.equals("Minimax")) {
								AI=backend.Minimax(board,k,movinglist,depth,true);}
							else {
								AI=backend.MyAI(board,k,movinglist,Mydepth,true);
							}
							endtime = System.nanoTime();
							avgtime+=(endtime-starttime);
						}
						avgtime/=iteration;
						if(k%2==0) {
							board[AI[0]][AI[1]]='W';
							movinglist.add (new Moves( AI[0], AI[1], k, "WHITE")) ;
						}
						//						else if(k<=224){
						//							board[AI1[0]][AI1[1]]='B';
						//							movinglist.add (new Moves( AI1[0], AI1[1], k, "BLACK")) ;
						//						}
						else {
							board[AI[0]][AI[1]]='B';
							movinglist.add (new Moves( AI[0], AI[1], k, "BLACK")) ;
						}
						five.clear();
						endCheck();
						BoardPanel.revalidate();
						BoardPanel.repaint();

						labelindex(movinglist);
						if(five.size()!=0||k==225) {
							gameend=true;
						} 
					}
					k++;
				}
				evaluresultB= backend.evaluator(board, movinglist,1);
				Analysis1.append("\nBlack side board state and value:");
				Analysis1.append(evaluresultB.ToString());

				evaluresultW= backend.evaluator(board, movinglist,2);
				Analysis1.append("\nWhite side board state and value:");
				Analysis1.append(evaluresultW.ToString());
			}

		});
		btnnext.setBackground(new Color(255,215,0));
		btnnext.setBounds(935, 524, 97, 25);
		frame.getContentPane().add(btnnext);
		frame.setLocationRelativeTo(null);

		JButton btnSaveBoard = new JButton("SAVE FILE");
		btnSaveBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TM.stop();
				AT1animation=false;
				Analysis.setForeground(Color.BLACK);
				Analysis.setFont(new Font("Mongolian Baiti", Font.BOLD, 16));
				Analysis.setText("Moves for this game: \n\n");
				Analysis.append(String.valueOf(movinglist.size())+" moves in this game\n");
				for(int i=0;i<movinglist.size();i++) {
					Analysis.append(movinglist.get(i).ToString());
				}
				JFileChooser chooser=new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Please type in the file name");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setApproveButtonText("Save");
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
				chooser.setAcceptAllFileFilterUsed(false);
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {	
					try {
						FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt");
						fw.write("There are "+movinglist.size()+" moves in this game.\r\n");
						fw.write("Date: "+new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime())+"\r\n\r\n");
						for(int i=0;i<movinglist.size();i++) {
							fw.write(movinglist.get(i).ToString()+"\r\n");
						}
						fw.close();
						Analysis.setForeground(Color.RED);
						Analysis.append("\n\nFile has been created successfully. Check '"+chooser.getName(chooser.getSelectedFile())+ ".txt' in "+chooser.getCurrentDirectory());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					System.out.println("No Selection ");
				}
			}
		});
		btnSaveBoard.setBackground(new Color(135,206,250));
		btnSaveBoard.setBounds(1200, 524, 97, 25);
		frame.getContentPane().add(btnSaveBoard);

		JButton btnInput = new JButton("INPUT FILE");
		btnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TM.stop();
				AT1animation=false;
				readfile=true;
				movinglist.clear();
				five.clear();
				BoardPanel.removeAll();
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						board[i][j]='-';
					}
				}
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Browse the folder to process");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {	
					Analysis.setText("getSelectedFile() : "+ chooser.getSelectedFile()+"\n\n");
					File file=chooser.getSelectedFile();
					Scanner scr = null;
					try {
						scr = new Scanner(file);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					k=1;
					scr.nextLine();scr.nextLine();scr.nextLine();
					while(scr.hasNext()) {
						String a=scr.nextLine();
						Analysis.append(a+"\n");
						int index=a.indexOf('[');
						int x=Integer.valueOf(a.substring(index+1, index+3));
						int y=Integer.valueOf(a.substring(index+4, index+6));
						if(k%2!=0) {
							board[x][y]='B';
							movinglist.add (new Moves( x, y, k, "BLACK")) ;	
							k++;}
						else {
							board[x][y]='W';
							movinglist.add (new Moves( x, y, k, "WHITE")) ;	
							k++;
						}
						scr.nextLine();
					}
					endCheck();
					BoardPanel.revalidate();
					BoardPanel.repaint();
					labelindex(movinglist);	
				} else {
					System.out.println("No Selection ");
				}
				readfile=false;
			}
		});
		btnInput.setBackground(new Color(135,206,250));
		btnInput.setBounds(1200, 470, 97, 25);
		frame.getContentPane().add(btnInput);

		JButton btnPvP = new JButton("P v P");
		btnPvP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!pvp) {
					pvp=true;
					Analysis.setText("You swtiched to PvP mode.");
					modelabel.remove(modelabel);
					frame.getContentPane().remove(modelabel);
					frame.getContentPane().repaint();
					modelabel=new JLabel("->                   <-");
					modelabel.setForeground(Color.RED);
					modelabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 15));
					modelabel.setBounds(654 , 521, 150, 35);
					frame.getContentPane().add(modelabel);	
				}
				else {
					Analysis.setText("Already in PvP mode.");
				}
			}
		});
		btnPvP.setBackground(new Color(0,255,0));
		btnPvP.setBounds(668, 524, 97, 25);
		frame.getContentPane().add(btnPvP);

		JButton btnPvA = new JButton("P v AI");
		btnPvA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TM.stop();
				TM1.stop();
				AT1animation=false;
				AIboolean=false;
				if(pvp) {
					pvp=false;

					if(k<5) {
						OpeningBook ob = new OpeningBook();
						isOpening=true;
						OpeningAry=ob.Opeing(movinglist,k);

					}
					else {
						isOpening=false;
					}
					TM.stop();
					TM1.stop();
					AT1animation=false;
					Analysis1.setText("Board evaluator information will be shown there: \n\nEvaluator is on by default.\nAnimation is on by default.\n\nYou can turn it off by pressing 'Evaluator' button\n\n");
					BoardPanel.revalidate();
					BoardPanel.repaint();	

					Analysis.setText("You swtiched to PvAI mode.\nCurrent Algorithm: "+AIversion);
					modelabel.remove(modelabel);
					frame.getContentPane().remove(modelabel);
					frame.getContentPane().repaint();
					modelabel=new JLabel("->                   <-");
					modelabel.setForeground(Color.RED);
					modelabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 15));
					modelabel.setBounds(654 , 575, 150, 35);
					frame.getContentPane().add(modelabel);	
				}
				else {

					if(k<5) {
						OpeningBook ob = new OpeningBook();
						isOpening=true;
						OpeningAry=ob.Opeing(movinglist,k);

					}
					else {
						isOpening=false;
					}


					Analysis1.setText("Board evaluator information will be shown there: \n\nEvaluator is on by default.\nAnimation is on by default.\n\nYou can turn it off by pressing 'Evaluator' button\n\n");
					TM.stop();
					TM1.stop();
					AT1animation=false;
					BoardPanel.revalidate();
					BoardPanel.repaint();
					Analysis.setText("Already in PvAI mode.\nCurrent Algorithm: "+AIversion);
				}
			}
		});
		btnPvA.setBackground(new Color(0,255,0));
		btnPvA.setBounds(668, 578, 97, 25);
		frame.getContentPane().add(btnPvA);

		JButton btnEvaluate = new JButton("Evaluator");
		btnEvaluate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!evaluator) {
					evaluator=true;
					Analysis1.setForeground(Color.BLACK);
					Analysis.setFont(new Font("Mongolian Baiti", Font.BOLD, 16));
					Analysis1.setText("Evaluator is ON!!\n\n");
					evaluatorlabel.remove(evaluatorlabel);
					frame.getContentPane().remove(evaluatorlabel);
					frame.getContentPane().repaint();
					evaluatorlabel=new JLabel("ON");
					evaluatorlabel.setForeground(Color.BLACK);
					evaluatorlabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
					evaluatorlabel.setBounds(1032, 575, 40, 35);
					frame.getContentPane().add(evaluatorlabel);
					if(movinglist.size()!=0) {
						endCheck();
						BoardPanel.revalidate();
						BoardPanel.repaint();
						labelindex(movinglist);	
						evaluatorrange=backend.scale(movinglist);
						Analysis1.append("Evaluate range is:\n"+evaluatorrange[0]+" < x < "+evaluatorrange[2]+"\n"+evaluatorrange[1]+" < y < "+evaluatorrange[3]+"\n");
					}
				}
				else {
					evaluator=false;
					endCheck();
					BoardPanel.revalidate();
					BoardPanel.repaint();
					labelindex(movinglist);	

					evaluatorlabel.remove(evaluatorlabel);
					frame.getContentPane().remove(evaluatorlabel);
					frame.getContentPane().repaint();
					evaluatorlabel=new JLabel("OFF");
					evaluatorlabel.setForeground(Color.RED);
					evaluatorlabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
					evaluatorlabel.setBounds(1032, 575, 40, 35);
					frame.getContentPane().add(evaluatorlabel);
					Analysis1.setForeground(Color.RED);
					Analysis1.setText("Evaluator is OFF!!\n\nClick 'Evaluator' button to reopen!!\n");
				}

			}
		});
		btnEvaluate.setBackground(new Color(255,215,0));
		btnEvaluate.setBounds(935, 578, 97, 25);
		frame.getContentPane().add(btnEvaluate);

		JButton btnstop = new JButton("STOP");
		btnstop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AIboolean=false;
				TM.stop();
				TM1.stop();
				AT1animation=false;
				Analysis1.setForeground(Color.RED);
				Analysis1.setText("The AIV1 mode stopped!!\nClick 'AI V1' to simulate the rest.");

			}
		});
		btnstop.setBackground(new Color(255,99,71));
		btnstop.setBounds(794, 524, 97, 25);
		frame.getContentPane().add(btnstop);

		JButton btnani = new JButton("Animation");
		btnani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Animation) {Animation=false;AT1animation=false;
				Analysis1.setForeground(Color.RED);
				Analysis1.setText("You disabled the animation now!!\n\nClick 'Animation' button to disable it.");
				animationlabel.remove(animationlabel);
				frame.getContentPane().remove(animationlabel);
				frame.getContentPane().repaint();
				animationlabel=new JLabel("OFF");
				animationlabel.setForeground(Color.RED);
				animationlabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
				animationlabel.setBounds(891, 575, 40, 35);
				frame.getContentPane().add(animationlabel);
				}
				else {Animation=true;
				Analysis1.setForeground(Color.BLACK);
				Analysis1.setText("You enabled the animation now!!\n\nClick 'Animation' button to disable it.");
				animationlabel.remove(animationlabel);
				frame.getContentPane().remove(animationlabel);
				frame.getContentPane().repaint();
				animationlabel=new JLabel("ON");
				animationlabel.setForeground(Color.BLACK);
				animationlabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
				animationlabel.setBounds(891, 575, 40, 35);
				frame.getContentPane().add(animationlabel);
				}
			}
		});
		btnani.setBackground(new Color(255,99,71));
		btnani.setBounds(794, 578, 97, 25);
		frame.getContentPane().add(btnani);

		JButton btnHelp = new JButton("HELP");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TM.stop();
				AT1animation=false;
				Analysis.setForeground(Color.BLUE);
				Analysis.setFont(new Font("default", Font.BOLD, 18));

				Analysis.setText("\n\n\n\nIf you have any question and find any bug in this program, please feel free to contact me!\n\nAuthor: Han Liao\n\nEmail: lhan@iastate.edu");
				Analysis1.setForeground(Color.BLUE);
				Analysis1.setFont(new Font("Mongolian Baiti", Font.BOLD, 14));
				Analysis1.setText("This program starts with the PvAI mode, the evalutor and animation is turned on by defaut.\n-----------------------------------------------\n-----------------------------------------------\n");
				Analysis1.append("New Game\nClear all moves and board state. Keep the setting for evaluator, animation and mode.\n-----------------------------------------------\n");
				Analysis1.append("PVP\nSwitch to person versus person mode.If you already in this mode, no other aciton will be performed.\n-----------------------------------------------\n");
				Analysis1.append("PVAI\nSwitch to person versus AI mode.If alredy in this mood,no other aciton will be performed\n-----------------------------------------------\n");
				Analysis1.append("UNDO\nIf you are in the PvP mode, it will undo the previous one move.If you are in the PvAI mode, it will undo the previous two moves.\n-----------------------------------------------\n");
				Analysis1.append("STOP'\nWill stop the AI calculation and show current board state. No other action performed.\n-----------------------------------------------\n");
				Analysis1.append("AI V1\nAI wil control the board in current state until the game end. You can press 'STOP' to stop this processing.\n-----------------------------------------------\n");
				Analysis1.append("AI V2\nAI will generate next move depend on current board state.\n-----------------------------------------------\n");
				Analysis1.append("EVALUATOR\nIt will turn on the evaluator or turn it off.\n-----------------------------------------------\n");
				Analysis1.append("ANIMATION\nIt will turn on the Animation or turn it off.\n-----------------------------------------------\n");
				Analysis1.append("INPUT FILE\nRead the input file(.txt) from outside program. The file formatting has to be following:\n\nThere are 6 moves in this game.\n" + 
						"Date: 2018/12/09 22:59:44\n" + 
						"\n" + 
						"Move 01: [13,08] BLACK\n" + 
						"\n" + 
						"Move 02: [04,07] WHITE\n" + 
						"\n-----------------------------------------------\n");
				Analysis1.append("SAVE FILE\nSave the board state into current direcotry as 'Game.txt'. The formatting like above.\n\n");
			}
		});
		btnHelp.setBackground(new Color(135,206,250));
		btnHelp.setBounds(1200,  578, 97, 25);
		frame.getContentPane().add(btnHelp);
	}

	public void endCheck(){
		char a=backend.boardChecker(board);
		if (a!='-') {
			gameend=true;
			Analysis.setForeground(Color.RED);
			if(a=='W') {
				Analysis.setText("GAME OVER!!!\nWHITE SIDE WIN!!!\n\n");
				Analysis.append("Moves for this game: \n");
				Analysis.append(String.valueOf(movinglist.size())+" moves in this game\n");
				for(int i=0;i<movinglist.size();i++) {
					Analysis.append(movinglist.get(i).ToString());
				}	
				Analysis.append("\n\nGAME OVER!!!\nWHITE SIDE WIN!!!\n\n");
			}
			else {
				Analysis.setText("GAME OVER!!!\nBLACK SIDE WIN!!!\n\n");
				Analysis.append("Moves for this game: \n");
				Analysis.append(String.valueOf(movinglist.size())+" moves in this game\n");
				for(int i=0;i<movinglist.size();i++) {
					Analysis.append(movinglist.get(i).ToString());
				}
				Analysis.append("\n\nGAME OVER!!!\nBLACK SIDE WIN!!!\n\n");
			}
		}
		else if(k==225){
			Analysis.setForeground(Color.RED);
			gameend=true;
			Analysis.setText("It's a draw!!\n");
			Analysis.append("Moves for this game: \n");
			Analysis.append(String.valueOf(movinglist.size())+" moves in this game\n");
			for(int i=0;i<movinglist.size();i++) {
				Analysis.append(movinglist.get(i).ToString());
			}
		}
		else if(!readfile){
			ToString(board);
		}
		for(int f=0;f<backend.five.size();f++) {
			five.add(backend.five.get(f));
		}

	}

	public void labelindex(ArrayList<Moves> movinglist) {
		BoardPanel.setLayout(null);
		BoardPanel.removeAll();
		boolean inside=false;
		int x,y;
		for(int i=0;i<movinglist.size();i++) {
			inside=false;
			x=movinglist.get(i).getX();
			y=movinglist.get(i).getY();

			for(int j=0;j<five.size();j++) {
				if(five.get(j).getX()==x&&five.get(j).getY()==y) {
					inside=true;

				}
			}
			if(movinglist.get(i).getIndex()<10&& movinglist.get(i).getStone().equals("BLACK")&&i!=movinglist.size()-1){
				num1=new JLabel();
				if(inside) {
					num1.setForeground(Color.BLACK);
				}
				else if(!inside){
					num1.setForeground(Color.WHITE);}
				num1.setFont(new Font("default", Font.BOLD, 18));
				num1.setText(String.valueOf(movinglist.get(i).getIndex()));
				num1.setBounds(movinglist.get(i).getX()*39+1-5+20, movinglist.get(i).getY()*39+1-15+20, 30, 30);
				BoardPanel.add(num1);	
			}

			else if(movinglist.get(i).getIndex()>=10&& movinglist.get(i).getStone().equals("BLACK")&&i!=movinglist.size()-1){
				num1=new JLabel();
				if(inside) {num1.setForeground(Color.BLACK);}
				else{num1.setForeground(Color.WHITE);}
				num1.setFont(new Font("default", Font.BOLD, 18));
				num1.setText(String.valueOf(movinglist.get(i).getIndex()));
				num1.setBounds(movinglist.get(i).getX()*39+1-10+20, movinglist.get(i).getY()*39+1-15+20, 30, 30);
				BoardPanel.add(num1);
			}
			else if(movinglist.get(i).getIndex()<10&& movinglist.get(i).getStone().equals("WHITE")&&i!=movinglist.size()-1){
				num=new JLabel();
				if(inside) {num.setForeground(Color.WHITE);}
				else{num.setForeground(Color.BLACK);}
				num.setFont(new Font("default", Font.BOLD, 18));
				num.setText(String.valueOf(movinglist.get(i).getIndex()));
				num.setBounds(movinglist.get(i).getX()*39+1-5+20, movinglist.get(i).getY()*39+1-15+20, 30, 30);
				BoardPanel.add(num);	
			}
			else if(movinglist.get(i).getIndex()>=10 && movinglist.get(i).getStone().equals("WHITE")&&i!=movinglist.size()-1){
				num=new JLabel();
				if(inside) {num.setForeground(Color.WHITE);}
				else{num.setForeground(Color.BLACK);}
				num.setFont(new Font("default", Font.BOLD, 18));
				num.setText(String.valueOf(movinglist.get(i).getIndex()));
				num.setBounds(movinglist.get(i).getX()*39+1-10+20, movinglist.get(i).getY()*39+1-15+20, 30, 30);
				BoardPanel.add(num);
			}
			else if(i==movinglist.size()-1 &&movinglist.get(i).getIndex()>=10 ) {
				num=new JLabel();
				if(inside) {
					if(movinglist.get(i).getStone().equals("WHITE")) {num.setForeground(Color.WHITE);}
					else {num.setForeground(Color.BLACK);}
				}

				else {
					num.setForeground(Color.RED);}
				num.setFont(new Font("default", Font.BOLD, 18));
				num.setText(String.valueOf(movinglist.get(i).getIndex()));
				num.setBounds(movinglist.get(i).getX()*39+1-10+20, movinglist.get(i).getY()*39+1-15+20, 30, 30);
				BoardPanel.add(num);
			}
			else if(i==movinglist.size()-1 &&movinglist.get(i).getIndex()<10 ) {
				num=new JLabel();
				if(inside) {
					if(movinglist.get(i).getStone().equals("WHITE")) {num.setForeground(Color.WHITE);}
					else {num.setForeground(Color.BLACK);}
				}
				else {
					num.setForeground(Color.RED);}
				num.setFont(new Font("default", Font.BOLD, 18));
				num.setText(String.valueOf(movinglist.get(i).getIndex()));
				num.setBounds(movinglist.get(i).getX()*39+1-5+20, movinglist.get(i).getY()*39+1-15+20, 30, 30);
				BoardPanel.add(num);
			}
		}
	}

	public void ToString (char[][] board){
		Highlighter highlighter=Analysis.getHighlighter();
		HighlightPainter painter= new DefaultHighlighter.DefaultHighlightPainter(Color.pink);
		Analysis.setForeground(Color.BLACK);
		Analysis.setText("Time comsuming:\n");
		Analysis.setForeground(Color.BLACK);
		Analysis.append("AI calculation start time(ns):  "+starttime+"\n");
		Analysis.append("AI calculation end time(ns):  "+endtime+"\n");
		Analysis.append("Average time: " + avgtime + " ("+iteration+" iteration)\n\n");
		String a = "Time comsuming:";
		int p0 = a.indexOf("Time comsuming:");
		int p1 = p0 + "Time comsuming:".length();
		try {
			highlighter.addHighlight(p0, p1, painter);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		if(pvp) {Analysis.append("Mode: PvP!\n");}
		else {Analysis.append("Mode: PvAI. Algorithem: "+AIversion+"\n");}
		Analysis.append("Board state:\n");
		for(int c=0;c<15;c++) {
			for(int r=0;r<15;r++) {
				Analysis.append(String.valueOf(board[r][c])+"  ");
				if(r==14) {
					Analysis.append("\n");
				}
			}
		}
	}
}
