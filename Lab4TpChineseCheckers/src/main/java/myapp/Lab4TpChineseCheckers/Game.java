package myapp.Lab4TpChineseCheckers;

public class Game {

	int noPlayers;
	Board board;
	Rules rules;
	Pawn[] pawn;
	
	public Game() {
		// TODO Auto-generated constructor stub
	}
	
	public void setnoPlayers(int n)
	{
		this.noPlayers=n;
	}
	public void setBoard(Board b)
	{
		this.board=b;
	}
	public void setRules(Rules r)
	{
		this.rules=r;
		r.fetchBoard(board);
	}
	public void setPawns(Pawn[] p)
	{
		this.pawn=p;
	}
	
	public void movePawn(Pawn p,int x,int y)
	{
		;
	}
	
	public int[][] possibleMoves(Pawn p)
	{
		return null;
	}

}
