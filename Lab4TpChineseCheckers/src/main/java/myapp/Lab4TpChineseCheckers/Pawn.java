package myapp.Lab4TpChineseCheckers;

public class Pawn {

	private int noPlayer;
	private int x;
	private int y;
	private boolean didmove;
	private boolean atDestination;
	
	public Pawn(int p,int x,int y) {
		this.noPlayer=p;
		this.x=x;
		this.y=y;
		this.didmove=false;
		this.atDestination=false;
	}
	public void move(int x,int y)
	{
		this.x=x;
		this.y=y;
		this.didmove=true;
	}
	public void doneMoving()
	{
		this.didmove=false;
	}

	public boolean getDidmove() {
		return didmove;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}

}
