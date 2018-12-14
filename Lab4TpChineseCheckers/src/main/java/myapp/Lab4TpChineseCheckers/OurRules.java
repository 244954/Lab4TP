package myapp.Lab4TpChineseCheckers;

import java.util.List;

public class OurRules implements Rules {

	Board b;
	List <Pawn> pawn;
	
	public OurRules() {
		
	}
	
	public void fetchBoard(Board b)
	{
		this.b=b;
	}

	public boolean canmove(Pawn p, int nx, int ny) {
		int x=p.getX();
		int y=p.getY();
		 // wolne pole
		if (b.getSquare(nx, ny)==0)
		{
			 // jesli poza zasiegiem skoku
			if ((x-nx>2 && x-nx<-2) || (y-ny>2 && y-ny<-2))
				return false;
			else
			{
				// ruch o jeden
				if ( (x-nx<2 && x-nx>-2) && (y-ny<2 && y-ny>-2) ) 
				{
					if (p.getDidmove()==false)
					{
						if (p.getAtDestination()==true && b.atDestination(p, nx, ny)==false) // jesli w celu, a rusza poza
						{
							return false;
						}
						if ( (x-nx==1 && y-ny==-1) || (x-nx==-1 && y-ny==1) ) // lewa dol lub prawa gora
							return false;
						if (b.atDestination(p, nx, ny)==true) // jesli do celu to zaznacz
							p.setAtDestination(true);
						return true;
					}
					else // jak juz przeskakiwal to nie moze o jedno pole
						return false;
				}
				// skoki
				else
				{
					//prawo
					if (y==ny && x+2==nx)
					{
						if (b.getSquare(x+1,y)>0)
						{
							if (p.getAtDestination()==true && b.atDestination(p, nx, ny)==false) // jesli w celu, a rusza poza
							{
								return false;
							}
							if (b.atDestination(p, nx, ny)==true) // jesli do celu to zaznacz
								p.setAtDestination(true);
							return true;
						}
						else
							return false;
					}
					//prawo-dol
					if (y+2==ny && x+2==nx)
					{
						if (b.getSquare(x+1,y+1)>0)
						{
							if (p.getAtDestination()==true && b.atDestination(p, nx, ny)==false) // jesli w celu, a rusza poza
							{
								return false;
							}
							if (b.atDestination(p, nx, ny)==true) // jesli do celu to zaznacz
								p.setAtDestination(true);
							return true;
						}
						else
							return false;
					}
					//dol
					if (y+2==ny && x==nx)
					{
						if (b.getSquare(x,y+1)>0)
						{
							if (p.getAtDestination()==true && b.atDestination(p, nx, ny)==false) // jesli w celu, a rusza poza
							{
								return false;
							}
							if (b.atDestination(p, nx, ny)==true) // jesli do celu to zaznacz
								p.setAtDestination(true);
							return true;
						}
						else
							return false;
					}
					//lewo
					if (y==ny && x-2==nx)
					{
						if (b.getSquare(x-1,y)>0)
						{
							if (p.getAtDestination()==true && b.atDestination(p, nx, ny)==false) // jesli w celu, a rusza poza
							{
								return false;
							}
							if (b.atDestination(p, nx, ny)==true) // jesli do celu to zaznacz
								p.setAtDestination(true);
							return true;
						}
						else
							return false;
					}
					//lewo-gora
					if (y-2==ny && x-2==nx)
					{
						if (b.getSquare(x-1,y-1)>0)
						{
							if (p.getAtDestination()==true && b.atDestination(p, nx, ny)==false) // jesli w celu, a rusza poza
							{
								return false;
							}
							if (b.atDestination(p, nx, ny)==true) // jesli do celu to zaznacz
								p.setAtDestination(true);
							return true;
						}
						else
							return false;
					}
					//gora
					if (y-2==ny && x==nx)
					{
						if (b.getSquare(x,y-1)>0)
						{
							if (p.getAtDestination()==true && b.atDestination(p, nx, ny)==false) // jesli w celu, a rusza poza
							{
								return false;
							}
							if (b.atDestination(p, nx, ny)==true) // jesli do celu to zaznacz
								p.setAtDestination(true);
							return true;
						} 
						else
							return false;
					}
					
					return false;
					
				}
			}
		}
		else
			return false;
	}

	public boolean haswon(int playerID,List <Pawn> p) {
		for (Pawn pawn : p) // doesnt work
		{
			if (pawn.getPlayer()==playerID && pawn.getAtDestination()==false)
			{
				return false;
			}
		}
		return true;
	}

}
