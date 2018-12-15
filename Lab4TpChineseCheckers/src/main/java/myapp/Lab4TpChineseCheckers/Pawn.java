package myapp.Lab4TpChineseCheckers;

public class Pawn {

	private int noPlayer;
	private int x;
	private int y;
	private boolean didmove;
	private boolean didmoveone;
	private boolean atDestination;
	
	public Pawn(int p,int x,int y) {
		this.noPlayer=p;
		this.x=x;
		this.y=y;
		this.didmove=false;
		this.setDidmoveone(false);
		this.setAtDestination(false);
	}
	public void move(int x,int y)
	{
		if ( (x-this.x<2 && x-this.x>-2) && (y-this.y<2 && y-this.y>-2) ) 
			this.didmoveone=true;
		this.x=x;
		this.y=y;
		this.didmove=true;
	}
	public void doneMoving()
	{
		this.didmove=false;
		this.didmoveone=false;
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
	public int getPlayer() {
		return this.noPlayer;
	}
	public boolean getAtDestination() {
		return atDestination;
	}
	public void setAtDestination(boolean atDestination) {
		this.atDestination = atDestination;
	}
	public boolean getDidmoveone() {
		return didmoveone;
	}
	public void setDidmoveone(boolean didmoveone) {
		this.didmoveone = didmoveone;
	}

}
