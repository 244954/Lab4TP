package myapp.Lab4TpChineseCheckers;

public abstract class Board {

	protected int[][] p;
	private int noPlayers;
	
	public Board() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract void setUp();
	
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

}
