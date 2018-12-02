package myapp.Lab4TpChineseCheckers;

import java.util.List;

public class Game {

	int noPlayers;
	Board board;
	Rules rules;
	List <Pawn> pawn;
	
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
	public void setPawns(List<Pawn> p)
	{
		this.pawn=p;
	}
	
	public void movePawn(Pawn p,int x,int y)
	{
		;
	}
	
	public int[][] possibleMoves(Pawn p)
	{
		int [][]moves;
		moves=new int[5*5][2]; // all moves in the radius of 2
		int x=p.getX();
		int y=p.getY();
		
		int n=0;
		
		for (int i=y-2;i<=y+2;i++)
		{
			for (int j=x-2;j<=x+2;j++)
			{
				if (rules.canmove(p, j, i)==true)
				{
					moves[n][0]=j;
					moves[n][1]=i;
					n++;
				}
			}
		}
		
		return moves;
	}

}
