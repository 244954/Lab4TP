package myapp.Lab4TpChineseCheckers;

import java.util.List;

public abstract class Player extends Thread{

    int noPlayer;
    List<Player> opponents;
    Game game;
    int pawnx,pawny;
    protected volatile boolean current;
    protected boolean pawnLocked;
	
    abstract public int getnoPlayer();
    abstract public void otherPlayerMoved(int x,int y,int nx,int ny);
    abstract public void setOpponents(List<Player> p);
    abstract public void setCurrent(boolean b);
}
