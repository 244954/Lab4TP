package myapp.Lab4TpChineseCheckers;

public class OurRules implements Rules {

	Board b;
	
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
						return true;
					else
						return false;
				}
				// skoki
				else
				{
					//prawo
					if (y==ny && x+2==nx)
					{
						if (b.getSquare(x+1,y)>0)
							return true;
						else
							return false;
					}
					//prawo-dol
					if (y+2==ny && x+2==nx)
					{
						if (b.getSquare(x+1,y+1)>0)
							return true;
						else
							return false;
					}
					//dol
					if (y+2==ny && x==nx)
					{
						if (b.getSquare(x,y+1)>0)
							return true;
						else
							return false;
					}
					//lewo
					if (y==ny && x-2==nx)
					{
						if (b.getSquare(x-1,y)>0)
							return true;
						else
							return false;
					}
					//lewo-gora
					if (y-2==ny && x-2==nx)
					{
						if (b.getSquare(x-1,y-1)>0)
							return true;
						else
							return false;
					}
					//gora
					if (y-2==ny && x==nx)
					{
						if (b.getSquare(x,y-1)>0)
							return true;
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

}
