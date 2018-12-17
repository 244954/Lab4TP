package myapp.Lab4TpChineseCheckers;

import java.util.ArrayList;
import java.util.List;

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
    	opponents.remove(this); // usun siebie z przeciwnikow
    }

	@Override
	public void setCurrent(boolean b) {
		this.current=b;
	}
	
	public void run()
	{
		boolean hasRun = false;
	}
}
