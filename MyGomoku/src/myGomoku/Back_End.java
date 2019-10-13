package myGomoku;
/*
 * Author: Han Liao (lhan@iastate.edu or leslieileo@gmail.com)
 * This is the project for creative component in ISU
 * This class include all back end algorithm and board evaluator
 */
import java.util.ArrayList;
import java.util.Collections;

public class Back_End {
	public ArrayList<Moves> five=new ArrayList<Moves>();
	private int inarow=5;
	public int[] scaled =new int[4];
	private int scalednum=2;
	private int scaledis=1;
	private boolean depth0=false;
	private int alpha=-1000000;
	private int beta = 1000000;
	public ArrayList<Moves> bestpath = new ArrayList<Moves>();

	public int minimaxiteration=0;
	public int myaiiteration=0;

	public int[] stoneposition(int mouseX, int mouseY) {
		double close=100;
		double distance=0;
		int[] answer= new int[2];
		int r,c;
		for( c=mouseY-23; c<mouseY+23; c++) {
			for( r=mouseX-23; r<mouseX+23; r++) {
				if( ((r-1-20)%39==0) && ((c-1-20)%39==0) ) {
					distance = Math.sqrt(((r-mouseX)^2) + ((c-mouseY)^2));
					if(distance<close) {
						answer[0]=r;
						answer[1]=c;
						close=distance;
					}
				}
			}
		}
		return answer;
	}

	public  int[] Greedy(char[][] board, int key, ArrayList<Moves> movinglist) {
		int[] result=new int[] {0,0};
		if(key<=225&&key%2==0) {
			int i=scaled[0];
			int j=scaled[1];
			int[] Black;
			int value=-100000;
			Moves candidate;
			Moves blackcand;
			for( i=scaled[0];i<=scaled[2];i++) {
				for( j=scaled[1];j<=scaled[3];j++) {
					if(board[i][j]!='B'&&board[i][j]!='W') {
						candidate = new Moves(i,j);
						movinglist.add(candidate);
						board[i][j]='W';
						Black=MAX(board, key+1, movinglist);
						blackcand=new Moves(Black[0],Black[1]);
						board[Black[0]][Black[1]]='B';
						movinglist.add(blackcand);
						int evaluresultB= evaluator(board, movinglist,1).getBoardEval();
						int evaluresultW= evaluator(board, movinglist,2).getBoardEval();
						if(evaluresultW-evaluresultB>value) {
							value=evaluresultW-evaluresultB;
							result[0]=candidate.getX();
							result[1]=candidate.getY();
							board[Black[0]][Black[1]]='-';
							board[i][j]='-';
							movinglist.remove(movinglist.size()-1);
							movinglist.remove(movinglist.size()-1);
						}
						else {
							board[Black[0]][Black[1]]='-';
							board[i][j]='-';
							movinglist.remove(movinglist.size()-1);
							movinglist.remove(movinglist.size()-1);
						}
					}
				}
			}
		}
		if(key<=225&&key%2!=0) {
			int i=scaled[0];
			int j=scaled[1];
			int[] White;
			int value=-100000;
			Moves candidate;
			Moves whitecand;
			for( i=scaled[0];i<=scaled[2];i++) {
				for( j=scaled[1];j<=scaled[3];j++) {
					if(board[i][j]!='B'&&board[i][j]!='W') {
						candidate = new Moves(i,j);
						movinglist.add(candidate);
						board[i][j]='B';
						White=MAX(board, key+1, movinglist);
						whitecand=new Moves(White[0],White[1]);
						board[White[0]][White[1]]='W';
						movinglist.add(whitecand);
						int evaluresultB= evaluator(board, movinglist,1).getBoardEval();
						int evaluresultW= evaluator(board, movinglist,2).getBoardEval();
						if(evaluresultB-evaluresultW>value) {
							value=evaluresultB-evaluresultW;
							result[0]=candidate.getX();
							result[1]=candidate.getY();
							board[White[0]][White[1]]='-';
							board[i][j]='-';
							movinglist.remove(movinglist.size()-1);
							movinglist.remove(movinglist.size()-1);
						}
						else {
							board[White[0]][White[1]]='-';
							board[i][j]='-';
							movinglist.remove(movinglist.size()-1);
							movinglist.remove(movinglist.size()-1);
						}
					}
				}
			}
		}
		return result;
	}

	public  int[] MAX(char[][] board, int key, ArrayList<Moves> movinglist) {
		int[] result=new int[] {0,0};
		if(key<=225&&key%2==0) {
			int i=scaled[0];
			int j=scaled[1];
			int value=-100000;
			Moves candidate;
			for( i=scaled[0];i<=scaled[2];i++) {
				for( j=scaled[1];j<=scaled[3];j++) {
					if(board[i][j]!='B'&&board[i][j]!='W') {
						candidate = new Moves(i,j);
						movinglist.add(candidate);
						board[i][j]='W';
						int evaluresultB= evaluator(board, movinglist,1).getBoardEval();
						int evaluresultW= evaluator(board, movinglist,2).getBoardEval();
						if(evaluresultW-evaluresultB>value) {
							value=evaluresultW-evaluresultB;
							result[0]=candidate.getX();
							result[1]=candidate.getY();
						}
						board[i][j]='-';
						movinglist.remove(movinglist.size()-1);}
				}
			}
		}
		if(key<=225&&key%2!=0) {
			int i=scaled[0];
			int j=scaled[1];
			int value=-100000;
			Moves candidate;
			for( i=scaled[0];i<=scaled[2];i++) {
				for( j=scaled[1];j<=scaled[3];j++) {
					if(board[i][j]!='B'&&board[i][j]!='W') {
						candidate = new Moves(i,j);
						movinglist.add(candidate);
						board[i][j]='B';
						int evaluresultB= evaluator(board, movinglist,1).getBoardEval();
						int evaluresultW= evaluator(board, movinglist,2).getBoardEval();
						if(evaluresultB-evaluresultW>value) {
							value=evaluresultB-evaluresultW;
							result[0]=candidate.getX();
							result[1]=candidate.getY();
						}
						board[i][j]='-';
						movinglist.remove(movinglist.size()-1);}
				}
			}
		}
		return result;
	}

	public  int[] Minimax(char[][] board, int key, ArrayList<Moves> movinglist,int depth,boolean maxplayer) {
		int[] result=new int[] {0,0};
		minimaxiteration=0;
		if(key%2==0) {
			result= MinimaxWhite(board, key,  movinglist,depth,alpha,beta,maxplayer);
		}
		else {
			result=MinimaxBlack(board, key,  movinglist,depth,alpha,beta,maxplayer);
		}
		return result;
	}

	public  int[] MinimaxWhite(char[][] board, int key, ArrayList<Moves> movinglist,int depth,int alpha, int beta, boolean maxplayer) {
		minimaxiteration++;
		int[] result=new int[] {0,0};
		depth0=false;
		if(depth==0) {
			depth0=true;
			return new int[] {movinglist.get(movinglist.size()-1).getX(),movinglist.get(movinglist.size()-1).getY()};}
		if(maxplayer) {
			int maxEval=-1000000;
			int WY=0;int WX=0;
			for(int i=scaled[0];i<=scaled[2];i++) {
				for(int j=scaled[1];j<=scaled[3];j++) {
					if(board[i][j]!='B'&&board[i][j]!='W') {
						board[i][j]='W';
						key++;
						movinglist.add(new Moves(i,j));
						result=MinimaxWhite( board,  key, movinglist,depth-1,alpha,beta,false);
						if(!depth0) {
							board[result[0]][result[1]]='B';
							key++;
							movinglist.add(new Moves(result[0],result[1]));	}
						int whitevalue=evaluator(board, movinglist,2).getBoardEval();
						int blackvalue=evaluator(board, movinglist,1).getBoardEval();
						int diff = whitevalue-blackvalue;
						if(!depth0) {
							board[result[0]][result[1]]='-';
							key--;
							movinglist.remove(movinglist.size()-1);}
						depth0=false;
						if(maxEval<diff) {
							maxEval=whitevalue-blackvalue;
							WX=movinglist.get(movinglist.size()-1).getX();
							WY=movinglist.get(movinglist.size()-1).getY();
							key--;
							board[WX][WY]='-';
							movinglist.remove(movinglist.size()-1);
						}
						else {
							key--;
							board[movinglist.get(movinglist.size()-1).getX()][movinglist.get(movinglist.size()-1).getY()]='-';
							movinglist.remove(movinglist.size()-1);
						}
						alpha = Math.max(alpha, diff);
						if(beta <= alpha) {
							break;
						}
					}
				}
			}
			depth0=false;
			return new int[] {WX,WY};
		}
		else {
			int minEval=1000000;
			int BY=0;int BX=0;
			for(int i=scaled[0];i<=scaled[2];i++) {
				for(int j=scaled[1];j<=scaled[3];j++) {
					if(board[i][j]!='B'&&board[i][j]!='W') {
						board[i][j]='B';
						key++;
						movinglist.add(new Moves(i,j));
						result=MinimaxWhite( board,  key, movinglist,depth-1,alpha,beta,true);
						if(!depth0) {
							board[result[0]][result[1]]='W';
							key++;
							movinglist.add(new Moves(result[0],result[1]));	}
						int whitevalue=evaluator(board, movinglist,2).getBoardEval();
						int blackvalue=evaluator(board, movinglist,1).getBoardEval();
						int diff = whitevalue-blackvalue;
						if(!depth0) {
							board[result[0]][result[1]]='-';
							key--;
							movinglist.remove(movinglist.size()-1);}
						depth0=false;
						if(minEval>diff) {
							minEval=whitevalue-blackvalue;
							BX=movinglist.get(movinglist.size()-1).getX();
							BY=movinglist.get(movinglist.size()-1).getY();
							key--;
							board[BX][BY]='-';
							movinglist.remove(movinglist.size()-1);
						}
						else {
							key--;
							board[movinglist.get(movinglist.size()-1).getX()][movinglist.get(movinglist.size()-1).getY()]='-';
							movinglist.remove(movinglist.size()-1);
						}
						beta = Math.min(beta, diff);
						if(beta <= alpha) {
							break;
						}
					}
				}
			}
			depth0=false;
			return new int[] {BX,BY};
		}
	}

	public  int[] MinimaxBlack(char[][] board, int key, ArrayList<Moves> movinglist,int depth,int alpha, int beta, boolean maxplayer) {
		minimaxiteration++;
		int[] result=new int[] {0,0};
		depth0=false;
		if(depth==0) {
			depth0=true;
			return new int[] {movinglist.get(movinglist.size()-1).getX(),movinglist.get(movinglist.size()-1).getY()};}
		if(maxplayer) {
			int maxEval=-1000000;
			int BY=0;int BX=0;
			for(int i=scaled[0];i<=scaled[2];i++) {
				for(int j=scaled[1];j<=scaled[3];j++) {
					if(board[i][j]!='B'&&board[i][j]!='W') {
						board[i][j]='B';
						key++;
						movinglist.add(new Moves(i,j));
						result=MinimaxBlack( board,  key, movinglist,depth-1,alpha,beta,false);
						if(!depth0) {
							board[result[0]][result[1]]='W';
							key++;
							movinglist.add(new Moves(result[0],result[1]));}
						int whitevalue=evaluator(board, movinglist,2).getBoardEval();
						int blackvalue=evaluator(board, movinglist,1).getBoardEval();
						int diff = blackvalue-whitevalue;
						if(!depth0) {
							board[result[0]][result[1]]='-';
							key--;
							movinglist.remove(movinglist.size()-1);}
						depth0=false;
						if(maxEval<diff) {
							maxEval=blackvalue-whitevalue;
							BX=movinglist.get(movinglist.size()-1).getX();
							BY=movinglist.get(movinglist.size()-1).getY();
							key--;
							board[BX][BY]='-';
							movinglist.remove(movinglist.size()-1);
						}
						else {
							key--;
							board[movinglist.get(movinglist.size()-1).getX()][movinglist.get(movinglist.size()-1).getY()]='-';
							movinglist.remove(movinglist.size()-1);
						}
						alpha = Math.max(alpha, diff);
						if(beta <= alpha) {
							break;
						}
					}
				}
			}
			depth0=false;
			return new int[] {BX,BY};
		}
		else {
			int minEval=1000000;
			int WY=0;int WX=0;
			for(int i=scaled[0];i<=scaled[2];i++) {
				for(int j=scaled[1];j<=scaled[3];j++) {
					if(board[i][j]!='B'&&board[i][j]!='W') {
						board[i][j]='W';
						key++;
						movinglist.add(new Moves(i,j));
						result=MinimaxBlack( board,  key, movinglist,depth-1,alpha, beta,true);
						if(!depth0) {
							board[result[0]][result[1]]='B';
							key++;
							movinglist.add(new Moves(result[0],result[1]));}
						int whitevalue=evaluator(board, movinglist,2).getBoardEval();
						int blackvalue=evaluator(board, movinglist,1).getBoardEval();
						int diff = blackvalue-whitevalue;
						if(!depth0) {
							board[result[0]][result[1]]='-';
							key--;
							movinglist.remove(movinglist.size()-1);}
						depth0=false;
						if(minEval>diff) {	
							minEval=blackvalue-whitevalue;
							WX=movinglist.get(movinglist.size()-1).getX();
							WY=movinglist.get(movinglist.size()-1).getY();
							key--;
							board[WX][WY]='-';
							movinglist.remove(movinglist.size()-1);
						}
						else {
							key--;
							board[movinglist.get(movinglist.size()-1).getX()][movinglist.get(movinglist.size()-1).getY()]='-';
							movinglist.remove(movinglist.size()-1);
						}
						beta = Math.min(beta, diff);
						if(beta <= alpha) {
							break;
						}
					}
				}
			}
			depth0=false;
			return new int[] {WX,WY};
		}
	}

	public  int[] MyAI(char[][] board, int key, ArrayList<Moves> movinglist,int depth,boolean maxplayer) {
		int[] result=new int[] {1,1};
		boolean whitemoves = false;
		if(key%2==0) {whitemoves=true;}
		else {whitemoves=false;}
		myaiiteration=0;
		bestpath=new ArrayList<Moves>();
		result= Myalgo(board, key,  movinglist,depth,alpha,beta,maxplayer,whitemoves);
		return result;
	}

	public  int[] Myalgo(char[][] board, int key, ArrayList<Moves> movinglist,int depth,int alpha, int beta, boolean maxplayer, boolean whitemoves) {
		myaiiteration++;
		int[] result=new int[] {0,0};
		depth0=false;
		if(depth==0) {
			depth0=true;
			return new int[] {movinglist.get(movinglist.size()-1).getX(),movinglist.get(movinglist.size()-1).getY()};}
		if(maxplayer) {
			int maxEval=-1000000;
			int WY=0;int WX=0;
			ArrayList<Moves> candidate = new ArrayList<Moves>();
			candidate = SortedCandidate(movinglist,board);
			for(int i=0;i<candidate.size();i++) {
				if(whitemoves) {
					board[candidate.get(i).getX()][candidate.get(i).getY()]='W';}
				else {
					board[candidate.get(i).getX()][candidate.get(i).getY()]='B';
				}
				key++;
				movinglist.add(new Moves(candidate.get(i).getX(),candidate.get(i).getY()));
				//bestpath.add(new Moves(candidate.get(i).getX(),candidate.get(i).getY()));
				result=Myalgo( board,  key, movinglist,depth-1,alpha,beta,false,whitemoves);
				if(!depth0) {
					if(whitemoves) {
						board[result[0]][result[1]]='B';}
					else {board[result[0]][result[1]]='W';}
					key++;
					movinglist.add(new Moves(result[0],result[1]));	
					//bestpath.add(new Moves(result[0],result[1]));
				}
				int whitevalue=evaluator(board, movinglist,2).getBoardEval();
				int blackvalue=evaluator(board, movinglist,1).getBoardEval();
				int diff;
				if(whitemoves) {
					diff = whitevalue-blackvalue;}
				else {diff =blackvalue- whitevalue;}
				if(!depth0) {
					board[result[0]][result[1]]='-';
					key--;
					movinglist.remove(movinglist.size()-1);
					//bestpath.remove(bestpath.size()-1);
				}
				depth0=false;
				if(maxEval<diff) {
					if(whitemoves) {
						maxEval=whitevalue-blackvalue;}
					else {maxEval=blackvalue-whitevalue;}
					WX=movinglist.get(movinglist.size()-1).getX();
					WY=movinglist.get(movinglist.size()-1).getY();
					key--;
					board[WX][WY]='-';
					movinglist.remove(movinglist.size()-1);
					//bestpath.remove(bestpath.size()-1);
				}
				else {
					key--;
					board[movinglist.get(movinglist.size()-1).getX()][movinglist.get(movinglist.size()-1).getY()]='-';
					movinglist.remove(movinglist.size()-1);
					//bestpath.remove(bestpath.size()-1);
				}
				alpha = Math.max(alpha, diff);
				if(beta <= alpha) {
					break;
				}
			}
			depth0=false;
			return new int[] {WX,WY};
		}
		else {
			int minEval=1000000;
			int BY=0;int BX=0;
			ArrayList<Moves> candidate = new ArrayList<Moves>();
			candidate = SortedCandidate(movinglist,board);
			for(int i=0;i<candidate.size();i++) {
				if(whitemoves) {
					board[candidate.get(i).getX()][candidate.get(i).getY()]='B';}
				else {
					board[candidate.get(i).getX()][candidate.get(i).getY()]='W';
				}
				key++;
				movinglist.add(new Moves(candidate.get(i).getX(),candidate.get(i).getY()));
				//bestpath.add(new Moves(candidate.get(i).getX(),candidate.get(i).getY()));
				result=Myalgo( board,  key, movinglist,depth-1,alpha,beta,true,whitemoves);
				if(!depth0) {
					if(whitemoves) {
						board[result[0]][result[1]]='W';}
					else {board[result[0]][result[1]]='B';}
					key++;
					movinglist.add(new Moves(result[0],result[1]));	
					//bestpath.add(new Moves(result[0],result[1]));
				}
				int whitevalue=evaluator(board, movinglist,2).getBoardEval();
				int blackvalue=evaluator(board, movinglist,1).getBoardEval();
				int diff;
				if(whitemoves) {
					diff = whitevalue-blackvalue;}
				else {diff =blackvalue- whitevalue;}
				if(!depth0) {
					board[result[0]][result[1]]='-';
					key--;
					movinglist.remove(movinglist.size()-1);
					//bestpath.remove(bestpath.size()-1);
				}
				depth0=false;
				if(minEval>diff) {
					if(whitemoves) {
						minEval=whitevalue-blackvalue;}
					else {minEval=blackvalue-whitevalue;}
					BX=movinglist.get(movinglist.size()-1).getX();
					BY=movinglist.get(movinglist.size()-1).getY();
					key--;
					board[BX][BY]='-';
					movinglist.remove(movinglist.size()-1);
					//bestpath.remove(bestpath.size()-1);
				}
				else {
					key--;
					board[movinglist.get(movinglist.size()-1).getX()][movinglist.get(movinglist.size()-1).getY()]='-';
					movinglist.remove(movinglist.size()-1);
					//bestpath.remove(bestpath.size()-1);
				}
				beta = Math.min(beta, diff);
				if(beta <= alpha) {
					break;
				}
			}
			depth0=false;
			return new int[] {BX,BY};
		}
	}

	public ArrayList<Moves> SortedCandidate(ArrayList<Moves> movinglist,char[][] board) {
		ArrayList<Moves> result = new ArrayList<Moves>();
		for(int i=0;i<movinglist.size();i++)
		{
			int x=movinglist.get(i).getX();
			int y=movinglist.get(i).getY();
			if(x-1>=0&&y-1>=0) {result.add(new Moves(x-1,y-1,0));}
			if(x>=0&&y-1>=0) {result.add(new Moves(x,y-1,0));}
			if(x+1<=14&&y-1>=0) {result.add(new Moves(x+1,y-1));}
			if(x-1>=0&&y>=0) {result.add(new Moves(x-1,y));}
			if(x+1<=14&&y>=0) {result.add(new Moves(x+1,y));}
			if(x-1>=0&&y+1<=14) {result.add(new Moves(x-1,y+1));}
			if(x>=0&&y+1<=14) {result.add(new Moves(x,y+1));}
			if(x+1<=14&&y+1<=14) {result.add(new Moves(x+1,y+1));}

			if(scaledis==2) {	
				if(x-2>=0&&y-2>=0) {result.add(new Moves(x-2,y-2));}
				if(x>=0&&y-2>=0) {result.add(new Moves(x,y-2));}
				if(x+2<=14&&y-2>=0) {result.add(new Moves(x+2,y-2));}
				if(x-2>=0&&y>=0) {result.add(new Moves(x-2,y));}
				if(x+2<=14&&y>=0) {result.add(new Moves(x+2,y));}
				if(x-2>=0&&y+2<=14) {result.add(new Moves(x-2,y+2));}
				if(x>=0&&y+2<=14) {result.add(new Moves(x,y+2));}
				if(x+2<=14&&y+2<=14) {result.add(new Moves(x+2,y+2));}
			}
		}
		for(int i=0;i<movinglist.size();i++) {
			for(int j=0;j<result.size();j++)
			{
				if(movinglist.get(i).getX()==result.get(j).getX()&&movinglist.get(i).getY()==result.get(j).getY()) {
					result.remove(j);
					j--;
				}
			}
		}
		for(int i=0;i<result.size()-1;i++) {
			for(int j=i+1;j<result.size();j++)
			{
				if(result.get(i).getX()==result.get(j).getX()&&result.get(i).getY()==result.get(j).getY()) {
					result.remove(j);
					j--;
				}
			}
		}
		for(int i=0;i<result.size();i++) {
			int x=result.get(i).getX();
			int y = result.get(i).getY();
			//up-down
			char target1 ='-';
			int count1=0;
			char target2 ='-';
			int count2=0;
			int value=0;
			int maxvalue=0;
			for(int j=Math.max(0, y-1);j>=Math.max(0, y-4);j--) {
				if(board[x][j]=='-') {break;}
				if(j==Math.max(0, y-1)&&board[x][j]!='-') {
					target1=board[x][j];
					count1++;
				}
				else {
					if(board[x][j]==target1) {
						count1++;
					}
					else {
						break;
					}
				}
			}
			for(int j=Math.min(14, y+1);j<=Math.min(14, y+4);j++) {
				if(board[x][j]=='-') {break;}
				if(j==Math.max(0, y+1)&&board[x][j]!='-') {
					target2=board[x][j];
					count2++;
				}
				else {
					if(board[x][j]==target2) {
						count2++;
					}
					else {
						break;
					}
				}
			}
			if(target1==target2&&target1!='-') {
				value=(1+count1+count2)*(count1+count2)/2 ;
				maxvalue=Math.max(maxvalue, value);
			}
			else {
				value=(1+Math.max(count1, count2))*(Math.max(count1, count2))/2 ;
				maxvalue=Math.max(maxvalue, value);
			}

			//left-right
			target1 ='-';
			count1=0;
			target2 ='-';
			count2=0;
			value=0;
			for(int j=Math.max(0, x-1);j>=Math.max(0, x-4);j--) {
				if(board[j][y]=='-') {break;}
				if(j==Math.max(0, x-1)&&board[j][y]!='-') {
					target1=board[j][y];
					count1++;
				}
				else {
					if(board[j][y]==target1) {
						count1++;
					}
					else {
						break;
					}
				}
			}
			for(int j=Math.min(14, x+1);j<=Math.min(14, x+4);j++) {
				if(board[j][y]=='-') {break;}
				if(j==Math.max(0, x+1)&&board[j][y]!='-') {
					target2=board[j][y];
					count2++;
				}	
				else {
					if(board[j][y]==target2) {
						count2++;
					}
					else {break;}
				}
			}
			if(target1==target2&&target1!='-') {
				value=(1+count1+count2)*(count1+count2)/2 ;
			}
			else {
				value=(1+Math.max(count1, count2))*(Math.max(count1, count2))/2 ;			
			}
			maxvalue=Math.max(maxvalue, value);

			//leftup-rightdown
			target1 ='-';
			count1=0;
			target2 ='-';
			count2=0;
			value=0;
			int p=x-1;int q=y-1;
			while(p>=0&&q>=0) {
				if(board[p][q]=='-') {break;}
				if(p==x-1&&q==y-1&&board[p][q]!='-') {
					target1=board[p][q];
					count1++;
					p--;q--;
				}
				else {
					if(board[p][q]==target1) {
						count1++;
						p--;q--;
					}
					else {break;}
				}
			}
			p=x+1; q=y+1;
			while(p<=14&&q<=14) {
				if(board[p][q]=='-') {break;}
				if(p==x+1&&q==y+1&&board[p][q]!='-') {
					target2=board[p][q];
					count2++;
					p++;q++;
				}
				else {
					if(board[p][q]==target2) {
						count2++;
						p++;q++;
					}
					else {break;}
				}
			}
			if(target1==target2&&target1!='-') {
				value=(1+count1+count2)*(count1+count2)/2 ;
			}
			else {
				value=(1+Math.max(count1, count2))*(Math.max(count1, count2))/2 ;			
			}
			maxvalue=Math.max(maxvalue, value);

			//rightup-leftdown
			target1 ='-';
			count1=0;
			target2 ='-';
			count2=0;
			value=0;
			p=x+1; q=y-1;
			while(p<=14&&q>=0) {
				if(board[p][q]=='-') {break;}
				if(p==x+1&&q==y-1&&board[p][q]!='-') {
					target1=board[p][q];
					count1++;
					p++;q--;
				}
				else {
					if(board[p][q]==target1) {
						count1++;
						p++;q--;
					}
					else {break;}
				}
			}
			p=x-1; q=y+1;
			while(p>=0&&q<=14) {
				if(board[p][q]=='-') {break;}
				if(p==x-1&&q==y+1&&board[p][q]!='-') {
					target2=board[p][q];
					count2++;
					p--;q++;
				}
				else {
					if(board[p][q]==target2) {
						count2++;
						p--;q++;
					}
					else {break;}
				}
			}
			if(target1==target2&&target1!='-') {
				value=(1+count1+count2)*(count1+count2)/2 ;
			}
			else {
				value=(1+Math.max(count1, count2))*(Math.max(count1, count2))/2 ;			
			}
			maxvalue=Math.max(maxvalue, value);
			result.get(i).change(maxvalue);
		}
		Collections.sort(result,Moves.percentageComparator);
		return result;
	}
	
//	public ArrayList<Moves> SortedCandidate(ArrayList<Moves> movinglist,char[][] board) {
//		ArrayList<Moves> result = new ArrayList<Moves>();
//		for(int i=0;i<movinglist.size();i++)
//		{
//			int x=movinglist.get(i).getX();
//			int y=movinglist.get(i).getY();
//			if(x-1>=0&&y-1>=0) {result.add(new Moves(x-1,y-1,0));}
//			if(x>=0&&y-1>=0) {result.add(new Moves(x,y-1,0));}
//			if(x+1<=14&&y-1>=0) {result.add(new Moves(x+1,y-1));}
//			if(x-1>=0&&y>=0) {result.add(new Moves(x-1,y));}
//			if(x+1<=14&&y>=0) {result.add(new Moves(x+1,y));}
//			if(x-1>=0&&y+1<=14) {result.add(new Moves(x-1,y+1));}
//			if(x>=0&&y+1<=14) {result.add(new Moves(x,y+1));}
//			if(x+1<=14&&y+1<=14) {result.add(new Moves(x+1,y+1));}
//
//			if(scaledis==2) {	
//				if(x-2>=0&&y-2>=0) {result.add(new Moves(x-2,y-2));}
//				if(x>=0&&y-2>=0) {result.add(new Moves(x,y-2));}
//				if(x+2<=14&&y-2>=0) {result.add(new Moves(x+2,y-2));}
//				if(x-2>=0&&y>=0) {result.add(new Moves(x-2,y));}
//				if(x+2<=14&&y>=0) {result.add(new Moves(x+2,y));}
//				if(x-2>=0&&y+2<=14) {result.add(new Moves(x-2,y+2));}
//				if(x>=0&&y+2<=14) {result.add(new Moves(x,y+2));}
//				if(x+2<=14&&y+2<=14) {result.add(new Moves(x+2,y+2));}
//			}
//		}
//		for(int i=0;i<movinglist.size();i++) {
//			for(int j=0;j<result.size();j++)
//			{
//				if(movinglist.get(i).getX()==result.get(j).getX()&&movinglist.get(i).getY()==result.get(j).getY()) {
//					result.remove(j);
//					j--;
//				}
//			}
//		}
//		for(int i=0;i<result.size()-1;i++) {
//			for(int j=i+1;j<result.size();j++)
//			{
//				if(result.get(i).getX()==result.get(j).getX()&&result.get(i).getY()==result.get(j).getY()) {
//					result.remove(j);
//					j--;
//				}
//			}
//		}
//		for(int i=0;i<result.size();i++) {
//			int value=0;
//			movinglist.add(new Moves(result.get(i).getX(),result.get(i).getY()));
//			board[result.get(i).getX()][result.get(i).getY()]='B';
//			value = Math.max(value, Math.max(evaluator(board,movinglist,1).getBoardEval(),evaluator(board,movinglist,2 ).getBoardEval()));
//			board[result.get(i).getX()][result.get(i).getY()]='W';
//			value = Math.max(value, Math.max(evaluator(board,movinglist,1).getBoardEval(),evaluator(board,movinglist,2 ).getBoardEval()));
//			board[result.get(i).getX()][result.get(i).getY()]='-';
//			movinglist.remove(movinglist.size()-1);
//			result.get(i).change(value);
//		}
//		Collections.sort(result,Moves.percentageComparator);
//		return result;
//	}

	public char boardChecker(char[][] board) {
		five.clear();
		for(int x=0;x<15;x++) {
			for(int y=0;y<15;y++) {
				char stone=board[x][y];
				if(stone!='-') {
					int count3=0;
					for(int i=Math.max(Math.min(x, x-4),0);i<=Math.min(Math.max(x, x+4),14);i++) {
						if(board[i][y]==stone) {
							count3++;
							five.add(new Moves(i,y));
							if(count3==inarow) {
								return stone;}
						}
						else {count3=0;five.clear();}
					}
					five.clear();

					int count4=0;
					for(int i=Math.max(Math.min(y, y-4),0);i<=Math.min(Math.max(y, y+4),14);i++) {
						if(board[x][i]==stone) {
							count4++;
							five.add(new Moves(x,i));
							if(count4==inarow) {	
								return stone;}
						}
						else {count4=0;five.clear();}
					}
					five.clear();

					int add=1;
					int count1=0,count2=0;
					boolean end1=false;
					boolean end2=false;
					while(add<5) {
						five.add(new Moves(x,y));
						if(0<=x+add && 14>=x+add && 0<=y+add && 14>=y+add) {
							if(board[x+add][y+add]==stone&&!end1) {
								count1++;
								five.add(new Moves(x+add,y+add));
							}
							else {
								end1=true;
							}
						}
						if(0<=x-add && 14>=x-add && 0<=y-add && 14>=y-add) {
							if(board[x-add][y-add]==stone&&!end2) {
								count2++;
								five.add(new Moves(x-add,y-add));
							}
							else {
								end2=true;
							}
						}
						add++;
						if(count1+count2==inarow-1) {
							return stone;
						}	
					}
					five.clear();
					add=1;
					count1=0;
					count2=0;
					end1=false;
					end2=false;
					while(add<5) {
						five.add(new Moves(x,y));
						if(0<=x+add && 14>=x+add && 0<=y-add && 14>=y-add) {
							if(board[x+add][y-add]==stone&&!end1) {
								count1++;
								five.add(new Moves(x+add,y-add));
							}
							else {
								end1=true;
							}

						}
						if(0<=x-add && 14>=x-add && 0<=y+add && 14>=y+add) {
							if(board[x-add][y+add]==stone&&!end2) {
								count2++;
								five.add(new Moves(x-add,y+add));
							}
							else {
								end2=true;
							}
						}
						add++;
						if(count1+count2==inarow-1) {
							return stone;
						}
					}
					five.clear();
				}
			}
		}
		five.clear();
		return '-';
	}

	public int[] scale(ArrayList<Moves> movinglist) {
		scaled =new int[4];
		int minX=15,minY=15,maxX=0,maxY=0;
		if(movinglist.size()==0) {
			return null;
		}
		for(int i=0;i<movinglist.size();i++) {
			if(movinglist.get(i).getX()>maxX) {
				maxX=movinglist.get(i).getX();
			}
			if(movinglist.get(i).getY()>maxY) {
				maxY=movinglist.get(i).getY();
			}
			if(movinglist.get(i).getX()<minX) {
				minX=movinglist.get(i).getX();
			}
			if(movinglist.get(i).getY()<minY) {
				minY=movinglist.get(i).getY();
			}
		}
		minX-=scalednum;minY-=scalednum;maxX+=scalednum;maxY+=scalednum;
		minX=Math.max(minX, 0);minY=Math.max(minY, 0);
		maxX=Math.min(maxX, 14);maxY=Math.min(maxY, 14);
		scaled[0]=minX;scaled[1]=minY;scaled[2]=maxX;scaled[3]=maxY;
		return scaled;
	}

	public BoardState evaluator(char[][] board, ArrayList<Moves> movinglist, int stone) {
		char target=' ';
		if(stone==1) {target='B';}
		else {target='W';}
		BoardState bs= new BoardState(board);
		ArrayList<Moves> checked = new ArrayList<Moves>();
		ArrayList<Moves> moves=new ArrayList<Moves>();
		if(movinglist.size()<stone) {
			return bs;
		}
		int index=stone-1;
		int count=0;
		boolean block1=false;
		boolean block2=false;
		int cut1=0;
		int cut2=0;
		int cut3=0;//2row
		int cut4=0;//2row
		int dupli=0;
		int dupli2=0;
		int add=1;
		char previous=' ';
		int i,j;
		while(index<=movinglist.size()) {
			moves=new ArrayList<Moves>();
			checked.add(movinglist.get(index));
			moves.add(movinglist.get(index));
			count=1;
			int x=movinglist.get(index).getX();
			int y=movinglist.get(index).getY();
			//Upper
			i=x;
			j=Math.max(0, y-1);
			block1=false;
			block2=false;
			cut1=0;
			cut2=0;
			cut3=0;
			cut4=0;
			dupli=0;
			add=1;
			boolean isFiveinrow=true;
			boolean exist=false;
			while(j>=Math.max(0, y-4)) {
				exist=false;
				for(int a=0;a<checked.size();a++) {
					if(checked.get(a).getX()==i&&checked.get(a).getY()==j) {
						exist=true;
					}
				}
				if(exist) {break;}
				if(board[i][j]==target) {
					if(previous=='-') {add=0;}
					previous=board[i][j];
					count++;
					moves.add(new Moves(i,j));

					if(cut1==1) {
						isFiveinrow=false;
						cut3++;
					}
					if(cut1==2) {
						isFiveinrow=false;
						cut3+=2;
					}
					if(cut1==3) {
						isFiveinrow=false;
						cut3+=3;
					}
				}
				else if(board[i][j]=='-'){
					if(previous=='-') {
						dupli+=add;
					}
					cut1++;
					previous='-';
				}
				else {
					block1=true;
					break;
				}
				j--;
			}
			//Bottom
			previous=' ';
			dupli2=0;
			add=1;
			i=x;
			j=Math.min(14, y+1);
			while(j<=Math.min(14, y+4)) {
				exist=false;
				for(int a=0;a<checked.size();a++) {
					if(checked.get(a).getX()==i&&checked.get(a).getY()==j) {
						exist=true;
					}
				}
				if(exist) {break;}
				if(board[i][j]==target) {
					if(previous=='-') {add=0;}
					previous=board[i][j];
					count++;
					moves.add(new Moves(i,j));
					if(cut2==1) {
						isFiveinrow=false;
						cut4++;
					}
					if(cut2==2) {
						isFiveinrow=false;
						cut4+=2;
					}
					if(cut2==3) {
						isFiveinrow=false;
						cut4+=3;
					}
				}
				else if(board[i][j]=='-'){
					if(previous=='-') {
						dupli2+=add;
					}
					cut2++;
					previous='-';
				}
				else {
					block2=true;
					break;
				}
				j++;
			}

			evalu(count,cut1,cut2,cut3,cut4,dupli,dupli2,block1,block2,isFiveinrow,bs, moves);

			if(index+2>=movinglist.size()) {break;}
			else{index=index+2;}
		}


		//////////////////////////////////
		if(movinglist.size()<2) {
			return bs;
		}
		index=stone-1;
		count=0;
		block1=false;
		block2=false;
		cut1=0;
		cut2=0;
		cut3=0;//2row
		cut4=0;//2row
		dupli=0;
		dupli2=0;
		add=1;
		previous=' ';
		checked.clear();
		checked = new ArrayList<Moves>();

		while(index<=movinglist.size()) {
			moves=new ArrayList<Moves>();
			checked.add(movinglist.get(index));
			moves.add(movinglist.get(index));
			count=1;
			int x=movinglist.get(index).getX();
			int y=movinglist.get(index).getY();
			//Left
			i=Math.max(0, x-1);
			j=Math.max(0, y-1);
			block1=false;
			block2=false;
			cut1=0;
			cut2=0;
			cut3=0;
			cut4=0;
			dupli=0;
			add=1;
			boolean isFiveinrow=true;
			boolean exist=false;
			while(i>=Math.max(0, x-4)&&j>=Math.max(0, y-4)) {
				exist=false;
				for(int a=0;a<checked.size();a++) {
					if(checked.get(a).getX()==i&&checked.get(a).getY()==j) {
						exist=true;
					}
				}
				if(exist) {break;}
				if(board[i][j]==target) {
					if(previous=='-') {add=0;}
					previous=board[i][j];
					count++;
					moves.add(new Moves(i,j));
					if(cut1==1) {
						isFiveinrow=false;
						cut3++;
					}
					if(cut1==2) {
						isFiveinrow=false;
						cut3+=2;
					}
					if(cut1==3) {
						isFiveinrow=false;
						cut3+=3;
					}
				}
				else if(board[i][j]=='-'){
					if(previous=='-') {
						dupli+=add;
					}
					cut1++;
					previous='-';
				}
				else {
					block1=true;
					break;
				}
				i--;
				j--;
			}
			//Right
			previous=' ';
			dupli2=0;
			add=1;
			i=Math.min(14, x+1);
			j=Math.min(14, y+1);
			while(i<=Math.min(14, x+4)&&j<=Math.min(14, y+4)) {
				exist=false;
				for(int a=0;a<checked.size();a++) {
					if(checked.get(a).getX()==i&&checked.get(a).getY()==j) {
						exist=true;
					}
				}
				if(exist) {break;}
				if(board[i][j]==target) {
					if(previous=='-') {add=0;}
					previous=board[i][j];
					count++;
					moves.add(new Moves(i,j));
					if(cut2==1) {
						isFiveinrow=false;
						cut4++;
					}
					if(cut2==2) {
						isFiveinrow=false;
						cut4+=2;
					}
					if(cut2==3) {
						isFiveinrow=false;
						cut4+=3;
					}
				}
				else if(board[i][j]=='-'){
					if(previous=='-') {
						dupli2+=add;
					}
					cut2++;
					previous='-';
				}
				else {
					block2=true;
					break;
				}
				i++;
				j++;
			}
			evalu(count,cut1,cut2,cut3,cut4,dupli,dupli2,block1,block2,isFiveinrow,bs, moves);


			if(index+2>=movinglist.size()) {break;}
			else{index=index+2;}
		}

		//////////////////////////////////
		if(movinglist.size()<2) {
			return bs;
		}
		index=stone-1;
		count=0;
		block1=false;
		block2=false;
		cut1=0;
		cut2=0;
		cut3=0;//2row
		cut4=0;//2row
		dupli=0;
		dupli2=0;
		add=1;
		previous=' ';
		checked.clear();
		checked = new ArrayList<Moves>();

		while(index<=movinglist.size()) {
			moves=new ArrayList<Moves>();
			checked.add(movinglist.get(index));
			moves.add(movinglist.get(index));
			count=1;
			int x=movinglist.get(index).getX();
			int y=movinglist.get(index).getY();
			//UpperLeft
			i=Math.max(0, x-1);
			j=y;
			block1=false;
			block2=false;
			cut1=0;
			cut2=0;
			cut3=0;
			cut4=0;
			dupli=0;
			add=1;
			boolean isFiveinrow=true;
			boolean exist=false;
			while(i>=Math.max(0, x-4)) {
				exist=false;
				for(int a=0;a<checked.size();a++) {
					if(checked.get(a).getX()==i&&checked.get(a).getY()==j) {
						exist=true;
					}
				}
				if(exist) {break;}
				if(board[i][j]==target) {
					if(previous=='-') {add=0;}
					previous=board[i][j];
					count++;
					moves.add(new Moves(i,j));
					if(cut1==1) {
						isFiveinrow=false;
						cut3++;
					}
					if(cut1==2) {
						isFiveinrow=false;
						cut3+=2;
					}
					if(cut1==3) {
						isFiveinrow=false;
						cut3+=3;
					}
				}
				else if(board[i][j]=='-'){
					if(previous=='-') {
						dupli+=add;
					}
					cut1++;
					previous='-';
				}
				else {
					block1=true;
					break;
				}
				i--;
			}
			//BottomRight
			previous=' ';
			dupli2=0;
			add=1;
			i=Math.min(14, x+1);
			j=y;
			while(i<=Math.min(14, x+4)) {
				exist=false;
				for(int a=0;a<checked.size();a++) {
					if(checked.get(a).getX()==i&&checked.get(a).getY()==j) {
						exist=true;
					}
				}
				if(exist) {break;}
				if(board[i][j]==target) {
					if(previous=='-') {add=0;}
					previous=board[i][j];
					count++;
					moves.add(new Moves(i,j));
					if(cut2==1) {
						isFiveinrow=false;
						cut4++;
					}
					if(cut2==2) {
						isFiveinrow=false;
						cut4+=2;
					}
					if(cut2==3) {
						isFiveinrow=false;
						cut4+=3;
					}
				}
				else if(board[i][j]=='-'){
					if(previous=='-') {
						dupli2+=add;
					}
					cut2++;
					previous='-';
				}
				else {
					block2=true;
					break;
				}
				i++;
			}
			evalu(count,cut1,cut2,cut3,cut4,dupli,dupli2,block1,block2,isFiveinrow,bs, moves);

			if(index+2>=movinglist.size()) {break;}
			else{index=index+2;}
		}

		//////////////////////////////////
		if(movinglist.size()<2) {
			return bs;
		}
		index=stone-1;
		count=0;
		block1=false;
		block2=false;
		cut1=0;
		cut2=0;
		cut3=0;//2row
		cut4=0;//2row
		dupli=0;
		dupli2=0;
		add=1;
		previous=' ';
		checked.clear();
		checked = new ArrayList<Moves>();

		while(index<=movinglist.size()) {
			moves=new ArrayList<Moves>();
			checked.add(movinglist.get(index));
			moves.add(movinglist.get(index));
			count=1;
			int x=movinglist.get(index).getX();
			int y=movinglist.get(index).getY();
			//UpperLeft
			i=Math.min(14, x+1);
			j=Math.max(0, y-1);
			block1=false;
			block2=false;
			cut1=0;
			cut2=0;
			cut3=0;
			cut4=0;
			dupli=0;
			add=1;
			boolean isFiveinrow=true;
			boolean exist=false;
			while(i<=Math.min(14, x+4)&&j>=Math.max(0, y-4)) {
				exist=false;
				for(int a=0;a<checked.size();a++) {
					if(checked.get(a).getX()==i&&checked.get(a).getY()==j) {
						exist=true;
					}
				}
				if(exist) {break;}
				if(board[i][j]==target) {
					if(previous=='-') {add=0;}
					previous=board[i][j];
					count++;
					moves.add(new Moves(i,j));
					if(cut1==1) {
						isFiveinrow=false;
						cut3++;
					}
					if(cut1==2) {
						isFiveinrow=false;
						cut3+=2;
					}
					if(cut1==3) {
						isFiveinrow=false;
						cut3+=3;
					}
				}
				else if(board[i][j]=='-'){
					if(previous=='-') {
						dupli+=add;
					}
					cut1++;
					previous='-';
				}
				else {
					block1=true;
					break;
				}
				i++;
				j--;
			}
			//BottomRight
			previous=' ';
			dupli2=0;
			add=1;
			i=Math.max(0, x-1);
			j=Math.min(14, y+1);
			while(i>=Math.max(0, x-4)&&j<=Math.min(14, y+4)) {
				exist=false;
				for(int a=0;a<checked.size();a++) {
					if(checked.get(a).getX()==i&&checked.get(a).getY()==j) {
						exist=true;
					}
				}
				if(exist) {break;}
				if(board[i][j]==target) {
					if(previous=='-') {add=0;}
					previous=board[i][j];
					count++;
					moves.add(new Moves(i,j));
					if(cut2==1) {
						isFiveinrow=false;
						cut4++;
					}
					if(cut2==2) {
						isFiveinrow=false;
						cut4+=2;
					}
					if(cut2==3) {
						isFiveinrow=false;
						cut4+=3;
					}
				}
				else if(board[i][j]=='-'){
					if(previous=='-') {
						dupli2+=add;
					}
					cut2++;
					previous='-';
				}
				else {
					block2=true;
					break;
				}
				i--;
				j++;
			}
			evalu(count,cut1,cut2,cut3,cut4,dupli,dupli2,block1,block2,isFiveinrow,bs, moves);


			if(index+2>=movinglist.size()) {break;}
			else{index=index+2;}
		}

		return bs;
	}

	public void evalu(int count,int cut1,int cut2,int cut3,int cut4,int dupli,int dupli2,boolean block1,boolean block2,boolean isFiveinrow,BoardState bs,ArrayList<Moves> moves) {
		//Five in a row
		if(count==5&&isFiveinrow==true) {bs.FiveInrow().add(moves);}
		//Live two
		if(count==2&&cut1>=1&&cut4==0&&cut3==0&&cut2>=1){bs.Livetwo().add(moves);}

		if(count==2&&cut1>=1&&cut4==1&&cut3==0&&cut2>=2){bs.Livetwo().add(moves);}
		if(count==2&&cut1>=2&&cut4==0&&cut3==1&&cut2>=1){bs.Livetwo().add(moves);}

		if(count==2&&cut1>=1&&cut4==2&&cut3==0&&cut2>=3){bs.Livetwo().add(moves);}
		if(count==2&&cut1>=3&&cut4==0&&cut3==2&&cut2>=1){bs.Livetwo().add(moves);}
		//Dead two
		if(count==2&&cut1>=3&&cut2==0&&cut3==0&&cut4==0&&block2==true){bs.Deadtwo().add(moves);}
		if(count==2&&cut1==0&&cut2>=3&&cut3==0&&cut4==0&&block1==true){bs.Deadtwo().add(moves);}

		if(count==2&&cut1>=2&&cut2==1&&cut3==0&&cut4==1&&block2==true){bs.Deadtwo().add(moves);}
		if(count==2&&cut1==0&&cut2>=3&&cut3==0&&cut4==1&&block1==true){bs.Deadtwo().add(moves);}
		if(count==2&&cut1>=3&&cut2==0&&cut3==1&&cut4==0&&block2==true){bs.Deadtwo().add(moves);}
		if(count==2&&cut1==1&&cut2>=2&&cut3==1&&cut4==0&&block1==true){bs.Deadtwo().add(moves);}

		if(count==2&&cut1>=1&&cut2==2&&cut3==0&&cut4==2&&block2==true){bs.Deadtwo().add(moves);}
		if(count==2&&cut1==0&&cut2>=3&&cut3==0&&cut4==2&&block1==true){bs.Deadtwo().add(moves);}
		if(count==2&&cut1>=3&&cut2==0&&cut3==2&&cut4==0&&block2==true){bs.Deadtwo().add(moves);}
		if(count==2&&cut1==2&&cut2>=1&&cut3==2&&cut4==0&&block1==true){bs.Deadtwo().add(moves);}

		if(count==2&&cut1>=1&&cut2==3&&cut3==0&&cut4==3){bs.Deadtwo().add(moves);}
		if(count==2&&cut1==3&&cut2>=1&&cut3==3&&cut4==0){bs.Deadtwo().add(moves);}
		if(count==2&&cut1==0&&cut2==3&&cut3==0&&cut4==3&&block1==true){bs.Deadtwo().add(moves);}
		if(count==2&&cut1==3&&cut2==0&&cut3==3&&cut4==0&&block2==true){bs.Deadtwo().add(moves);}
		//Live three 
		if((count==3&&cut1>=1&&cut2>=1&&cut3==0&&cut4==0)&&!(cut1==1&&cut2==1)){bs.Livethree().add(moves);}	

		//if(count==3&&cut1>=1&&cut2==2&&cut4==2){bs.Livethree().add(moves);}
		if((count==3&&cut1>=2&&cut2>=1&&cut3>=1&&dupli<1)&&!(cut2==2&&cut4==3)&&!(cut3==1&&cut4==1)&&!(cut1==2&&cut3==3)&&!(count==3&&cut1>=2&&cut2>=1&&cut3==1&&cut4>=2&&dupli>=0&&dupli2>=1)){bs.JLivethree().add(moves);}
		//if(count==3&&cut1==2&&cut2>=1&&cut3==1){bs.Livethree().add(moves);}
		//if(count==3&&cut1==2&&cut2>=1&&cut3==2){bs.Livethree().add(moves);}
		if((count==3&&cut1>=1&&cut2>=2&&cut4>=1&&dupli2<1)&&!(cut2==2&&cut4==3)&&!(cut3==1&&cut4==1)&&!(cut1==2&&cut3==3)&&!(count==3&&cut1>=1&&cut2>=2&&cut3>=2&&cut4==1&&dupli>=1&&dupli2>=0)){bs.JLivethree().add(moves);}
		//if(count==3&&cut1>=1&&cut2==2&&cut4==1){bs.Livethree().add(moves);}

		//Dead three
		if(count==3&&cut1>=2&&cut2==0&&cut3==0&&cut4==0&&block2==true){bs.Deadthree().add(moves);}
		if(count==3&&cut1==0&&cut2>=2&&cut3==0&&cut4==0&&block1==true){bs.Deadthree().add(moves);}

		if(count==3&&cut1>=2&&cut2==0&&cut3==1&&cut4==0&&block2==true){bs.Deadthree().add(moves);}
		if(count==3&&cut1>=1&&cut2==1&&cut3==0&&cut4==2&&block2==true){bs.Deadthree().add(moves);}
		if(count==3&&cut1==0&&cut2>=2&&cut3==0&&cut4==1&&block1==true){bs.Deadthree().add(moves);}
		if(count==3&&cut1==1&&cut2>=1&&cut3==2&&cut4==0&&block1==true){bs.Deadthree().add(moves);}

		if(count==3&&cut1==1&&cut2>=1&&cut3==1&&cut4==0&&block1==true){bs.Deadthree().add(moves);}
		if(count==3&&cut1==0&&cut2==2&&cut3==0&&cut4==2&&block1==true){bs.Deadthree().add(moves);}
		if(count==3&&cut1>=1&&cut2==1&&cut3==0&&cut4==1&&block2==true){bs.Deadthree().add(moves);}
		if(count==3&&cut1==2&&cut2==0&&cut3==2&&cut4==0&&block2==true){bs.Deadthree().add(moves);}

		//bug
		if((count==3&&cut1>=2&&cut3==2&&dupli==1)&&!(count==3&&cut1==2&&cut2==0&&cut3==2&&cut4==0&&block2==true)){bs.Deadthree().add(moves);}
		if(count==3&&cut2==2&&cut4==4&&dupli2==1){bs.Deadthree().add(moves);}
		if((count==3&&cut2>=2&&cut4==2&&dupli2==1)&&!(count==3&&cut1==0&&cut2==2&&cut3==0&&cut4==2&&block1==true)){bs.Deadthree().add(moves);}
		if(count==3&&cut1==2&&cut3==4&&dupli==1){bs.Deadthree().add(moves);}

		if(count==3&&cut2==2&&cut4==3){bs.Deadthree().add(moves);}
		if(count==3&&cut3==1&&cut4==1){bs.Deadthree().add(moves);}
		if(count==3&&cut1==2&&cut3==3){bs.Deadthree().add(moves);}

		if(count==3&&cut1==1&&cut2==1&&cut3==0&&cut4==0&&block1==true&&block2==true){bs.Deadthree().add(moves);}
		//Live Four
		if(count==4&&cut1>=1&&cut2>=1&&cut3==0&&cut4==0){bs.Livefour().add(moves);}
		//Dead Four
		if(count==4&&cut1>=1&&cut2==0&&block2==true&&cut3==0&&cut4==0){bs.Deadfour().add(moves);}
		if(count==4&&cut1==0&&cut2>=1&&block1==true&&cut3==0&&cut4==0){bs.Deadfour().add(moves);}

		
		if(count==4&&block1==true&&block2==true){bs.CDeadfour().add(moves);}

		if(count==4&&cut1>=0&&cut2==1&&cut3==0&&cut4==3&&dupli2<1){bs.JDeadfour().add(moves);}
		if(count==4&&cut1>=1&&cut2>=0&&cut3==3&&cut4==0&&dupli<1){bs.JDeadfour().add(moves);}
		if(count==4&&cut1>=0&&cut2>=0&&cut3==1&&cut4==0){bs.JDeadfour().add(moves);}
		if(count==4&&cut1>=0&&cut2>=0&&cut3==0&&cut4==1){bs.JDeadfour().add(moves);}

		if(count==4&&cut1>=0&&cut2>=0&&cut3==2&&cut4==0&&dupli<1){bs.JDeadfour().add(moves);}
		if(count==4&&cut1>=0&&cut2>=0&&cut3==0&&cut4==2&&dupli2<1){bs.JDeadfour().add(moves);}

	}

//	public ArrayList<Moves> BestPath(){
//		bestpath.add(new Moves(1,1));
//		return bestpath;
//	}
}
