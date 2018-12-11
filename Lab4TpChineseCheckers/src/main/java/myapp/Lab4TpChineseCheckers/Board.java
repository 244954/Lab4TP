package myapp.Lab4TpChineseCheckers;

public abstract class Board {

	protected int[][] p;
	protected int noPlayers;
	protected int[][] destination; // [all destinatation squares][3] (player,x,y)
	
	public Board() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract void setUp();
	
	public boolean atDestination(Pawn p,int x,int y) // will pawn be at destination upon moving there
	{
		for (int i=0;i<destination.length;i++)
		{
			if (destination[i][0]==p.getPlayer() && destination[i][1]==x && destination[i][2]==y)
			{
				return true;
			}
		}
		return false;
	}
	
	public void setSquare(int x,int y,int squareid)
	{
		p[x][y]=squareid;
	}
	public int getSquare(int x,int y)
	{
		return p[x][y];
	}
	public int[][] getSquares()
	{
		return p;
	}
	public void setnoPlayers(int noPlayers)
	{
		this.noPlayers=noPlayers;
	}
	public int getnoPlayers()
	{
		return noPlayers;
	}
	public void move(int x,int y,int nx,int ny) // dla GUI
	{
		setSquare(nx,ny,getSquare(x,y));
		setSquare(x,y,0);
	}

}
