package myGomoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Author: Han Liao (lhan@iastate.edu or leslieileo@gmail.com)
 * This is the project for creative component in ISU
 */

//this is constructor class to save board statement and score
public class BoardState {
	private List<ArrayList<Moves>> FiveInrow;
	private List<ArrayList<Moves>> Livefour;
	private List<ArrayList<Moves>> Deadfour;
	private List<ArrayList<Moves>> Livethree;
	private List<ArrayList<Moves>> Deadthree;
	private List<ArrayList<Moves>> Livetwo;
	private List<ArrayList<Moves>> Deadtwo;
	private char[][] board;
	private int boardEval=0;  	

	public BoardState(char[][] board) {
		this.FiveInrow = new ArrayList<ArrayList<Moves>>();
		this.Livefour = new ArrayList<ArrayList<Moves>>();
		this.Deadfour = new ArrayList<ArrayList<Moves>>();
		this.Livethree = new ArrayList<ArrayList<Moves>>();
		this.Deadthree = new ArrayList<ArrayList<Moves>>();
		this.Livetwo = new ArrayList<ArrayList<Moves>>();
		this.Deadtwo = new ArrayList<ArrayList<Moves>>();
		this.board=new char[15][15];
		this.boardEval=0;
		for(int i=0;i<14;i++) {
			for(int j=0;j<14;j++) {
				this.board[i][j]=board[i][j];
			}
		}
	}
	
	public List<ArrayList<Moves>> FiveInrow() {
		return FiveInrow;
	}
	
	public List<ArrayList<Moves>> Livefour() {
		return Livefour;
	}
	
	public List<ArrayList<Moves>> Deadfour() {
		return Deadfour;
	}
	
	public List<ArrayList<Moves>> Livethree() {
		return Livethree;
	}
	
	public List<ArrayList<Moves>> Deadthree() {
		return Deadthree;
	}
	
	public List<ArrayList<Moves>> Livetwo() {
		return Livetwo;
	}
	
	public List<ArrayList<Moves>> Deadtwo() {
		return Deadtwo;
	}
		
	public int getBoardEval() {
		if(FiveInrow.size()!=0) {boardEval+=10000000;}
		if((Livefour.size()==1)||(Deadfour.size()==2)||(Deadfour.size()==1&&Livethree.size()==1)) {boardEval+=1000000;}
		if(Livethree.size()==2) {boardEval+=5000;}
		if(Deadthree.size()==1&&Livethree.size()==1) {boardEval+=1000;}
		if(Deadfour.size()!=0) {boardEval+=(500*Deadfour.size());}
		if(Livethree.size()!=0) {boardEval+=(400*Livethree.size());}
		if(Livetwo.size()!=0) {boardEval+=(50*Livetwo.size());}
		if(Deadtwo.size()!=0) {boardEval+=(10*Deadtwo.size());}
		return boardEval;
	}
	
	public String ToString() {
		String result="\nBoard Value: "+getBoardEval()+"";
		result+="\nFive In Row: ";
		for(int i=0;i<FiveInrow.size();i++) {
			for(int j=0;j<FiveInrow.get(i).size();j++) {
				result+=FiveInrow.get(i).get(j).toString();
			}
			//result+="\n";
		}
		
		result+="\nLive Four: ";
		for(int i=0;i<Livefour.size();i++) {
			for(int j=0;j<Livefour.get(i).size();j++) {
				result+=Livefour.get(i).get(j).toString();
			}
			//result+="\n";
		}
		
		result+="\nDead Four: ";
		for(int i=0;i<Deadfour.size();i++) {
			for(int j=0;j<Deadfour.get(i).size();j++) {
				result+=Deadfour.get(i).get(j).toString();
			}
			//result+="\n";
		}
		
		result+="\nLive Three: ";
		for(int i=0;i<Livethree.size();i++) {
			for(int j=0;j<Livethree.get(i).size();j++) {
				result+=Livethree.get(i).get(j).toString();
			}
			//result+="\n";
		}
		
		result+="\nDead Three: ";
		for(int i=0;i<Deadthree.size();i++) {
			for(int j=0;j<Deadthree.get(i).size();j++) {
				result+=Deadthree.get(i).get(j).toString();
			}
			//result+="\n";
		}
		
		result+="\nLive Two: ";
		for(int i=0;i<Livetwo.size();i++) {
			for(int j=0;j<Livetwo.get(i).size();j++) {
				result+=Livetwo.get(i).get(j).toString();
			}
			//result+="\n";
		}
		
		result+="\nDead Two: ";
		for(int i=0;i<Deadtwo.size();i++) {
			for(int j=0;j<Deadtwo.get(i).size();j++) {
				result+=Deadtwo.get(i).get(j).toString();
			}
			//result+="\n";
		}
		return result;
	}
}
