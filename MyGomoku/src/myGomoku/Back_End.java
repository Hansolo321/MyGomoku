package myGomoku;

import java.util.ArrayList;
import java.util.Random;

public class Back_End {
	public ArrayList<Moves> five=new ArrayList<Moves>();
	private int inarow=5;
	public int[] scaled =new int[4];
	private int scalednum=1;


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

	public  int[] AI1(char[][] board, int key) {
		int[] result=new int[] {0,0};
		if(key<=225&&key!=2) {
			Random r = new Random();	
			int rand1 = r.nextInt(scaled[2]+1-scaled[0]) + scaled[0];
			int rand2 = r.nextInt(scaled[3]+1-scaled[1]) + scaled[1];
			while(board[rand1][rand2]!='-') {
				rand1 = r.nextInt(scaled[2]+1-scaled[0]) + scaled[0];
				rand2 = r.nextInt(scaled[3]+1-scaled[1]) + scaled[1];
			}
			result[0]=rand1;
			result[1]=rand2;
			return result;
		}
		else {
			for(int i=0;i<14;i++) {
				for(int j=0;j<14;j++) {
					if(board[i][j]!='-') {
						result[0]=i+1;
						result[1]=j+2;
					return result;
								
					}
				}
			}
		}
		return result;
	}

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
}
