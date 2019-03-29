package myGomoku;
/*
 * Author: Han Liao (lhan@iastate.edu or leslieileo@gmail.com)
 * This is the project for creative component in ISU
 */
public class Moves {
	private int index;
	private int Xposition;
	private int Yposition;
	private String Stone;
	public Moves(int Xposition, int Yposition, int index, String Stone) {
		this.index=index;
		this.Xposition=Xposition;
		this.Yposition=Yposition;
		this.Stone=Stone;
	}
	
	public Moves(int Xposition,int Yposition) {
		this.Xposition=Xposition;
		this.Yposition=Yposition;
	}
	
	public int getX() {
		return Xposition;
	}
	
	public int getY() {
		return Yposition;
	}
	
	public int getIndex() {
		return index;
	}
	
	public String getStone() {
		return Stone;
	}
	public String ToString() {
		return "Move "+String.format("%02d",index)+": "+"["+String.format("%02d",Xposition)+","+String.format("%02d",Yposition)+"] "+Stone +"\n";
	}
	
	public String toString() {
		return "["+String.format("%02d",Xposition)+","+String.format("%02d",Yposition)+"] ";
	}
}
