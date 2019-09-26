package myGomoku;

import java.util.Comparator;

/*
 * Author: Han Liao (lhan@iastate.edu or leslieileo@gmail.com)
 * This is the project for creative component in ISU
 */
public class Moves {
	private int index;
	private int Xposition;
	private int Yposition;
	private int Percentage;
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

	public Moves(int Xposition,int Yposition,int Percentage) {
		this.Xposition=Xposition;
		this.Yposition=Yposition;
		this.Percentage= Percentage;
	}

	public int getPercentage() {
		return Percentage;
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

	public void change(int maxvalue) {
		Percentage =  maxvalue;
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
	
	public String PercentageToString() {
		return "["+String.format("%02d",Xposition)+","+String.format("%02d",Yposition)+"|"+Percentage+"] ";
	}

	public static Comparator<Moves> percentageComparator = new Comparator<Moves>() {
		public int compare(Moves m1, Moves m2) {
			int move1 = m1.getPercentage();
			int move2 = m2.getPercentage();
			return move2-move1;
			}};
}
