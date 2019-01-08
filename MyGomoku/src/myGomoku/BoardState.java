package myGomoku;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Author: Han Liao(lhan@iastate.edu or leslieileo@gmail.com)
 * This is the project for creative component in ISU
 */

//this is constructor class to save board statement and score
public class BoardState {
	private HashMap<ArrayList<Moves>,Integer> oneIndiv;
	private HashMap<ArrayList<Moves>,Integer> twoInrow;
	private HashMap<ArrayList<Moves>,Integer> threeInrow;

	private HashMap<ArrayList<Moves>,Integer> fourInrow;
	private HashMap<ArrayList<Moves>,Integer> fiveInrow;
	private int boardEval=0;  
	
	public void BoradState(HashMap<ArrayList<Moves>,Integer> oneIndiv, HashMap<ArrayList<Moves>,Integer> twoInrow, HashMap<ArrayList<Moves>,Integer> threeInrow, HashMap<ArrayList<Moves>,Integer> fourInrow, HashMap<ArrayList<Moves>,Integer> fiveInrow, int boardEval) {
		this.oneIndiv=oneIndiv;
		this.twoInrow=twoInrow;
		this.threeInrow=threeInrow;	
		this.fourInrow=fourInrow;
		this.boardEval=boardEval;
		this.fiveInrow=fiveInrow;
	}
	
	public HashMap<ArrayList<Moves>,Integer> oneIndiv() {
		return oneIndiv;
	}
	
	public HashMap<ArrayList<Moves>,Integer> twoInrow() {
		return twoInrow;
	}
	
	public HashMap<ArrayList<Moves>,Integer> threeInrow() {
		return threeInrow;
	}
	
	public HashMap<ArrayList<Moves>,Integer> threeInrowD() {
		return fiveInrow;
	}
	
	public HashMap<ArrayList<Moves>,Integer> fourInrow() {
		return fourInrow;
	}
	
	public int getBoardEval() {
		return boardEval;
	}
	
	public String ToString() {
		return String.valueOf(boardEval);
	}
}
