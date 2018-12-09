package myapp.Lab4TpChineseCheckers;

import java.util.ArrayList;
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
	public void setPawns(List<Pawn> p)
	{
		this.pawn=p;
	}
	public void setRules(Rules r)
	{
		this.rules=r;
		r.fetchBoard(board);
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
		if (board.atDestination(p, x, y)==true) // did it reach destination?
		{
			p.setAtDestination(true);
		}
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
	
	public List<Move> possibleMoves(Pawn p)
	{
		List<Move> moves;
		moves=new ArrayList<Move>();
		int x=p.getX();
		int y=p.getY();
		
		for (int i=y-2;i<=y+2;i++)
		{
			for (int j=x-2;j<=x+2;j++)
			{
				if (rules.canmove(p, j, i)==true)
				{
					moves.add(new Move(x,y));
				}
			}
		}
		
		return moves;
	}
	public boolean canmove(Pawn p,int x,int y)
	{
		if (rules.canmove(p, x, y)==true)
			return true;
		else
			return false;
	}
	public boolean haswon(int noPlayer)
	{
		if (rules.haswon(noPlayer,pawn)==true)
			return true;
		else
			return false;
	}
	public int getnoPlayers()
	{
		return this.noPlayers;
	}

}
