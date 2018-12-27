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
	private HashMap<ArrayList<Moves>,Integer> threeInrowD;
	private HashMap<ArrayList<Moves>,Integer> fourInrow;
	private int boardEval;  
	
	public void BoradState(HashMap<ArrayList<Moves>,Integer> oneIndiv, HashMap<ArrayList<Moves>,Integer> twoInrow, HashMap<ArrayList<Moves>,Integer> threeInrow, HashMap<ArrayList<Moves>,Integer> threeInrowD, HashMap<ArrayList<Moves>,Integer> fourInrow, int boardEval) {
		this.oneIndiv=oneIndiv;
		this.twoInrow=twoInrow;
		this.threeInrow=threeInrow;
		this.threeInrowD=threeInrowD;
		this.fourInrow=fourInrow;
		this.boardEval=boardEval;
	}
	
	public HashMap<ArrayList<Moves>,Integer> oneIndiv() {
		return oneIndiv;
	}
	
	public HashMap<ArrayList<Moves>,Integer> twoInrow() {
		return twoInrow;
	}
	
	public HashMap<ArrayList<Moves>,Integer> threeInrow() {
		return threeInrowD;
	}
	
	public HashMap<ArrayList<Moves>,Integer> fourInrow() {
		return fourInrow;
	}
	
	public int getBoardEval() {
		return boardEval;
	}
}
