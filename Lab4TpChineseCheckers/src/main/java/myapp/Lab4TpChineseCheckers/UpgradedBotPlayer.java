package myapp.Lab4TpChineseCheckers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UpgradedBotPlayer extends Player {
	
	Move destin; // where do i go
	
	public UpgradedBotPlayer(int noPlayer, Game game,int gamemode) {
        this.noPlayer = noPlayer;
        this.game=game;
        this.current=false;
        this.pawnLocked=false;
        destin=game.board.destination(noPlayer); // wow it looks bad
	}

	@Override
	public int getnoPlayer() {
		return this.noPlayer;
	}

	@Override
	public void otherPlayerMoved(int x, int y, int nx, int ny) {
		;
		
	}

	@Override
    public synchronized void setOpponents(List<Player> p)
    {
    	opponents=new ArrayList<Player>(p);
    	opponents.remove(this); // usun siebie z przeciwnikow
    }

	@Override
	public void setCurrent(boolean b) {
		this.current=b;
	}
	
	public void run()
	{
		List <Pawn> pawns=new ArrayList<Pawn>(); // lista swoich pionkow
		List <Move> moves=new ArrayList<Move>(); // mozliwe ruchy
		Pawn thisp; // chosen pawn
		Move thism; // chosen move
		
		while (true)
		{
			if (current)
			{
				System.out.println("My move!");
				pawns.clear();
				for (Pawn p : this.game.getPawns())
				{
					if (p.getPlayer()==this.noPlayer && !this.game.possibleMoves(p).isEmpty()) // has moves
						pawns.add(p);
				}
				thisp=pawns.get(new Random().nextInt(pawns.size())); // choose a pawn to move
				thism=new Move(Integer.MAX_VALUE/2,Integer.MAX_VALUE/2); // somewhere far
				
				/////////////////////////////////////////////// do this recursively and not randomly
				moves=this.game.possibleMoves(thisp);
				for (Move m: moves) // choose the closest to your destination
				{
					if (destin.distance(m)<destin.distance(thism))
					{
						thism=m;
					}
				}
				System.out.println(destin.x+" "+destin.y+" "+thism.x+" "+thism.y);
			
    			for (Player p: opponents)
    			{
    				p.otherPlayerMoved(thisp.getX(),thisp.getY(),thism.x,thism.y);
    			}
				this.game.movePawn(thisp,thism.x ,thism.y);
				//////////////////////////////////////////////
    			
    			int no=( (this.noPlayer+1>this.game.getnoPlayers()) ? 1 : this.noPlayer+1 );
    			this.current=false;
    			for (Player p : opponents)
    			{
    				if (p.getnoPlayer()==no)
    					p.setCurrent(true);
    			}
    			this.game.movedone();
			}
			else
			{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
