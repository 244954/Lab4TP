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
        destin=game.board.destination(noPlayer);
	}

	@Override
	public int getnoPlayer() {
		return this.noPlayer;
	}

	@Override
	public void otherPlayerMoved(int x, int y, int nx, int ny) {
				
	}

	@Override
        public synchronized void setOpponents(List<Player> p)
        {
            opponents=new ArrayList<>(p);
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
				if(!hasWon())
				{
					pawns.clear();
					checkIfHasMoves(pawns);
					thisp=pawns.get(new Random().nextInt(pawns.size())); // choose a pawn to move
					thism=new Move(Integer.MAX_VALUE/2,Integer.MAX_VALUE/2); // go somewhere far
					
					// do this recursively and not randomly
					moves=this.game.possibleMoves(thisp);
					for (Move m: moves) // choose the closest to your destination
					{
						if (destin.distance(m)<destin.distance(thism))
						{
							thism=m;
						}
					}
				
	    			for (Player p: opponents)
	    			{
	    				p.otherPlayerMoved(thisp.getX(),thisp.getY(),thism.x,thism.y);
	    			}
					this.game.movePawn(thisp,thism.x ,thism.y);
				}
	    			
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
					e.printStackTrace();
				}
			}
		}
	}

	private void checkIfHasMoves(List<Pawn> pawns) 
	{
		for (Pawn p : this.game.getPawns())
		{
			if (p.getPlayer()==this.noPlayer && !this.game.possibleMoves(p).isEmpty()) // has moves
				pawns.add(p);
		}
	}
	
	private boolean hasWon() 
	{
		return game.haswon(this.noPlayer); 
	}
}
