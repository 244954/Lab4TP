package myapp.Lab4TpChineseCheckers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BotPlayer extends Player {

	public BotPlayer(int noPlayer, Game game,int gamemode) {
        this.noPlayer = noPlayer;
        this.game=game;
        this.current=false;
        this.pawnLocked=false;
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
            opponents.remove(this); // delete yourself from opponents
        }

	@Override
	public void setCurrent(boolean b) {
		this.current=b;
	}
	
	public void run()
	{
		List <Pawn> pawns=new ArrayList<Pawn>(); // list of pawns
		List <Move> moves=new ArrayList<Move>(); // possible moves
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
				thisp=pawns.get(new Random().nextInt(pawns.size()));
				moves=this.game.possibleMoves(thisp);
				thism=moves.get(new Random().nextInt(moves.size()));
			
    			for (Player p: opponents)
    			{
    				p.otherPlayerMoved(thisp.getX(),thisp.getY(),thism.x,thism.y);
    			}
				this.game.movePawn(thisp,thism.x ,thism.y);
    			
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
				try 
				{
					Thread.sleep(1000);
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			} 
		}
	}
}
