package myapp.Lab4TpChineseCheckers;

public class Move {

	public int x;
	public int y;
	
	public Move(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public int distance(Move m) // "distance" between squares
	{	
		int i;
		i=this.x-m.x;
		int x=((i>0) ? i :-i);
		i=this.y-m.y;
		int y=((i>0) ? i :-i);
		return x+y;
	}

}
