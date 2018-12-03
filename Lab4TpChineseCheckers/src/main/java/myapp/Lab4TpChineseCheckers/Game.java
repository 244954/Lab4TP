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
	public Pawn getPawn(int x,int y)
	{
		for (Pawn p : pawn)
		{
			if (p.getX()==x && p.getY()==y)
			{
				return p;
			}
		}
		return null;
	}
	
	public void movePawn(Pawn p,int x,int y)
	{
		board.setSquare(p.getX(), p.getY(), 0);
		board.setSquare(x, y, p.getPlayer());
		p.move(x, y);
	}
	public void movedone()
	{
		for (Pawn pp : this.pawn)
		{
			pp.doneMoving();
		}
	}
	public int getSquare(int x,int y) // just for test
	{
		return this.board.getSquare(x, y);
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
