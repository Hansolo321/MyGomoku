package myGomoku;
/*
 * Author: Han Liao (lhan@iastate.edu or leslieileo@gmail.com)
 * This is the project for creative component in ISU
 * This class is Opening book for the first 4 moves
 */
import java.util.ArrayList;

public class OpeningBook {
	private ArrayList<Moves> ary = new ArrayList<Moves>();
	public ArrayList<Moves> Opeing(ArrayList<Moves> movinglist, int k) {
		ary.clear();
		if(k==1) {
			ary.add(new Moves(7,7));
			return ary;
		}
		else if(k==2) {
			double dis1=Math.sqrt(1);
			double dis2=Math.sqrt(2);
			for(int i=0;i<15;i++) {
				for(int j=0;j<15;j++) {
					double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
					if(dist1==dis1||dist1==dis2) {
						ary.add(new Moves(i,j));
					}
				}
			}
			return ary;
		}
		else if(k==3) {
			double distance=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-movinglist.get(1).getX()),2)+Math.pow(Math.abs(movinglist.get(0).getY()-movinglist.get(1).getY()),2));
			double dis1=Math.sqrt(1);
			double dis2=Math.sqrt(2);
			double dis3=Math.sqrt(5);
			double dis4=Math.sqrt(8);
			double dis5=Math.sqrt(10);
			double dis6=Math.sqrt(4);
			double dis7=Math.sqrt(13);
			if(distance==dis1){
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						if((dist1==dis4||dist1==dis5) && dist2==dis3) {
							ary.add(new Moves(i,j));
						}
						if(dist1==dis3&&dist2==dis2) {
							ary.add(new Moves(i,j));
							ary.add(new Moves(i,j));
							ary.add(new Moves(i,j));
						}
						if(dist1==dis2&&dist2==dis1) {
							ary.add(new Moves(i,j));
							ary.add(new Moves(i,j));
							ary.add(new Moves(i,j));
						}
						if(dist1==dis1&&dist2==dis2) {
							ary.add(new Moves(i,j));
							ary.add(new Moves(i,j));
							ary.add(new Moves(i,j));
						}
						if(dist1==dis2&&dist2==dis3) {
							ary.add(new Moves(i,j));
							ary.add(new Moves(i,j));
							ary.add(new Moves(i,j));
						}
					}
				}
			}
			else if(distance==dis2) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						if((dist1==dis1 && dist2==dis1)||(dist1==dis1 && dist2==dis3)) {
							ary.add(new Moves(i,j));
							ary.add(new Moves(i,j));
						}
						if(dist1==dis2&&dist2==dis6) {
							ary.add(new Moves(i,j));
							ary.add(new Moves(i,j));
						}
						if(dist1==dis6&&dist2==dis2) {
							ary.add(new Moves(i,j));
							ary.add(new Moves(i,j));
						}
						if(dist1==dis3&&dist2==dis1) {
							ary.add(new Moves(i,j));
							ary.add(new Moves(i,j));
						}
						if(dist1==dis7&&dist2==dis3) {
							ary.add(new Moves(i,j));
						}
						if(dist1==dis3&&dist2==dis3) {
							ary.add(new Moves(i,j));
							ary.add(new Moves(i,j));
						}
					}
				}
			}
			else {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						if(dist1==dis1||dist1==dis2) {
							ary.add(new Moves(i,j));
						}
					}
				}
			}
			return ary;
		}
		else if(k==4) {
			double distance=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-movinglist.get(1).getX()),2)+Math.pow(Math.abs(movinglist.get(0).getY()-movinglist.get(1).getY()),2));
			double distance1=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-movinglist.get(2).getX()),2)+Math.pow(Math.abs(movinglist.get(1).getY()-movinglist.get(2).getY()),2));
			double distance2=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-movinglist.get(2).getX()),2)+Math.pow(Math.abs(movinglist.get(0).getY()-movinglist.get(2).getY()),2));
			double dis1=Math.sqrt(1);
			double dis2=Math.sqrt(2);
			double dis3=Math.sqrt(4);
			double dis4=Math.sqrt(5);
			double dis5=Math.sqrt(8);
			double dis6=Math.sqrt(10);
			double dis7=Math.sqrt(13);
			double dis8=Math.sqrt(16);
			double dis9=Math.sqrt(9);
			double dis10=Math.sqrt(18);
			if(distance==dis1&&distance1==dis4&&distance2==dis2) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));
						if(dist1==dis2&&dist2==dis1&&dist3==dis5) {
							ary.add(new Moves(i,j));
						}
					}
				}
			}
			else if(distance==dis1&&distance1==dis2&&distance2==dis1) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));
						if(dist1==dis2&&dist2==dis4&&dist3==dis1) {
							ary.add(new Moves(i,j));
						}
					}
				}
			}
			else if(distance==dis1&&distance1==dis1&&distance2==dis2) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));
						if((dist1==dis2&&dist2==dis4&&dist3==dis5)||(dist1==dis5&&dist2==dis4&&dist3==dis2)) {
							ary.add(new Moves(i,j));
						}
					}
				}
			}
			else if(distance==dis1&&distance1==dis2&&distance2==dis4) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));
						if(dist1==dis2&&dist2==dis1&&dist3==dis1) {
							ary.add(new Moves(i,j));
						}
					}
				}
			}
			else if(distance==dis1&&distance1==dis4&&distance2==dis5) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));
						if(dist1==dis2&&dist2==dis1&&dist3==dis2) {
							ary.add(new Moves(i,j));
						}
					}
				}
			}
			else if(distance==dis1&&distance1==dis4&&distance2==dis6) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));
						if(dist1==dis2&&dist2==dis1&&dist3==dis3) {
							ary.add(new Moves(i,j));
						}
					}
				}
			}
			else if(distance==dis2&&distance1==dis4&&distance2==dis1) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));
						if(dist1==dis3&&dist2==dis6&&dist3==dis1) {
							ary.add(new Moves(i,j));
						}
					}
				}
			}
			else if(distance==dis2&&distance1==dis3&&distance2==dis2) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));
						if(dist1==dis2&&dist2==dis3&&dist3==dis5) {
							ary.add(new Moves(i,j));
						}
					}
				}
			}
			else if(distance==dis2&&distance1==dis1&&distance2==dis1) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));
						if(dist1==dis1&&dist2==dis4&&dist3==dis2) {
							ary.add(new Moves(i,j));
						}
					}
				}
			}
			else if(distance==dis2&&distance1==dis2&&distance2==dis3) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));
						if(dist1==dis1&&dist2==dis1&&dist3==dis1) {
							ary.add(new Moves(i,j));
						}
					}
				}
			}
			else if(distance==dis2&&distance1==dis4&&distance2==dis7) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));
						if(dist1==dis1&&dist2==dis1&&dist3==dis5) {
							ary.add(new Moves(i,j));
						}
					}
				}
			}
			else if(distance==dis2&&distance1==dis4&&distance2==dis4) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));
						if(dist1==dis3&&dist2==dis2&&dist3==dis1) {
							ary.add(new Moves(i,j));
						}
					}
				}
			}
			else if(distance==dis2&&distance1==dis5&&distance2==dis2) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));
						if(dist1==dis1&&dist2==dis1&&dist3==dis4) {
							ary.add(new Moves(i,j));
						}
					}
				}
			}
			else if(distance==dis2&&distance1==dis2&&distance2==dis5) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));
						if(dist1==dis1&&dist2==dis1&&dist3==dis4) {
							ary.add(new Moves(i,j));
						}
						if(dist1==dis3&&dist2==dis2&&dist3==dis3) {
							ary.add(new Moves(i,j));
						}
						if(dist1==dis4&&dist2==dis1&&dist3==dis1) {
							ary.add(new Moves(i,j));
						}
					}
				}
			}
			else if(distance==dis2&&distance1==dis1&&distance2==dis4) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));

						if(dist1==dis3&&dist2==dis2&&dist3==dis1) {
							ary.add(new Moves(i,j));
						}
					}
				}
			}
			else if(distance==dis2&&distance1==dis3&&distance2==dis6) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));
						if(dist1==dis2&&dist2==dis3&&dist3==dis8) {
							ary.add(new Moves(i,j));
						}
						if(dist1==dis1&&dist2==dis1&&dist3==dis4) {
							ary.add(new Moves(i,j));
							ary.add(new Moves(i,j));
							ary.add(new Moves(i,j));
						}
					}
				}
			}
			else if(distance==dis2&&distance1==dis4&&distance2==dis9) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));
						if(dist1==dis2&&dist2==dis3&&dist3==dis4) {
							ary.add(new Moves(i,j));
						}
					}
				}
			}
			else if(distance==dis2&&distance1==dis5&&distance2==dis6) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));
						if(dist1==dis1&&dist2==dis1&&dist3==dis7) {
							ary.add(new Moves(i,j));
						}
						if(dist1==dis1&&dist2==dis1&&dist3==dis4) {
							ary.add(new Moves(i,j));
						}
					}
				}
			}
			else if(distance==dis2&&distance1==dis6&&distance2==dis3) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));
						if(dist1==dis1&&dist2==dis1&&dist3==dis9) {
							ary.add(new Moves(i,j));
						}

					}
				}
			}
			else if(distance==dis2&&distance1==dis9&&distance2==dis4) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));
						if(dist1==dis1&&dist2==dis4&&dist3==dis5) {
							ary.add(new Moves(i,j));
						}

					}
				}
			}
			else if(distance==dis2&&distance1==dis6&&distance2==dis5) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));
						if(dist1==dis2&&dist2==dis3&&dist3==dis10) {
							ary.add(new Moves(i,j));
						}

					}
				}
			}
			else if(distance==dis2&&distance1==dis7&&distance2==dis4) {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						double dist3=Math.sqrt(Math.pow(Math.abs(movinglist.get(2).getX()-i),2)+Math.pow(Math.abs(movinglist.get(2).getY()-j),2));
						if(dist1==dis1&&dist2==dis1&&dist3==dis5) {
							ary.add(new Moves(i,j));
						}

					}
				}
			}
			else {
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						double dist1=Math.sqrt(Math.pow(Math.abs(movinglist.get(0).getX()-i),2)+Math.pow(Math.abs(movinglist.get(0).getY()-j),2));
						double dist2=Math.sqrt(Math.pow(Math.abs(movinglist.get(1).getX()-i),2)+Math.pow(Math.abs(movinglist.get(1).getY()-j),2));
						if((dist1==dis1||dist1==dis2)&&dist2!=0) {
							ary.add(new Moves(i,j));
						}

					}
				}
			}
			return ary;
		}
		return null;
	}
}
