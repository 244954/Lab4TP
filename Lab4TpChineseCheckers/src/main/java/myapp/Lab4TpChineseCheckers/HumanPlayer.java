package myapp.Lab4TpChineseCheckers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HumanPlayer extends Player {

	int noPlayer;
	List<Player> opponents;
	Socket socket;
    BufferedReader input;
    PrintWriter output;
    Game game;
    int pawnx,pawny;
    
    
    public HumanPlayer(Socket socket, int noPlayer, Game game,int gamemode) {
        this.socket = socket;
        this.noPlayer = noPlayer;
        this.game=game;
        this.current=false;
        this.pawnLocked=false;
        try {
            input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("WELCOME " + this.noPlayer);
            output.println("GAMEMODE " + gamemode);
            output.println("MESSAGE Waiting for opponents to connect");
        } catch (IOException e) {
            System.out.println("Player died: " + e);
        }
    }
    public int getnoPlayer()
    {
    	return this.noPlayer;
    }
    public synchronized void setOpponents(List<Player> p)
    {
    	opponents=new ArrayList<Player>(p);
    	opponents.remove(this); // usun siebie z przeciwnikow
    }
    public void setCurrent(boolean b)
    {
    	this.current=b;
    }
    
    public void otherPlayerMoved(int x,int y,int nx,int ny) {
    	String sx,sy,snx,sny;
    	if (x<10)
    		sx="0"+Integer.toString(x);
    	else
    		sx=Integer.toString(x);
    	if (y<10)
    		sy="0"+Integer.toString(y);
    	else
    		sy=Integer.toString(y);
    	if (nx<10)
    		snx="0"+Integer.toString(nx);
    	else
    		snx=Integer.toString(nx);
    	if (nx<10)
    		sny="0"+Integer.toString(ny);
    	else
    		sny=Integer.toString(ny);
        output.println("OTHER_MOVED " + sx +" "+ sy +" "+ snx +" "+ sny);
    }
    
    public void run()
    {
    	try
    	{
    		System.out.println("Player started");
	    	output.println("MESSAGE All players connected");
	    	
	    	if (this.current==true)
	    	{
	    		output.println("MESSAGE Your move");
	    	}
	    	
	    	while (true)
	    	{
	    		String command;
	    		if(input.ready() && (command = input.readLine())!=null)
	    		{
		    		System.out.println(command);
		    		if (command.startsWith("PAWN") && this.current==true && this.pawnLocked==false)
		    		{
		    			pawnx=Integer.parseInt(command.substring(5, 7));
		    			pawny=Integer.parseInt(command.substring(8, 10));
		    			List<Move> m=this.game.possibleMoves(this.game.getPawn(pawnx, pawny));
		    			for (Move move : m)
		    			{
		    				String x,y;
		    				if (move.x<10)
		    					x="0"+Integer.toString(move.x);
		    				else
		    					x=Integer.toString(move.x);
		    				if (move.y<10)
		    					y="0"+Integer.toString(move.y);
		    				else
		    					y=Integer.toString(move.y);
		    				output.println("VALIDMOVE " + x + " " + y);
		    			}
		    		}
		    		else if (command.startsWith("MOVE") && this.current==true)
		    		{
		    			int xx=Integer.parseInt(command.substring(5, 7));
		    			int yy=Integer.parseInt(command.substring(8, 10));
		    			this.game.movePawn(this.game.getPawn(pawnx, pawny),xx , yy);
		    			System.out.println("Moved");
		    			if (game.haswon(this.noPlayer)==true)
		    			{
		    				output.println("VICTORY");
		    			}
		    			for (Player p: opponents)
		    			{
		    				p.otherPlayerMoved(pawnx, pawny, xx, yy);
		    			}
		    			pawnx=xx;
		    			pawny=yy;
		    			List<Move> m=this.game.possibleMoves(this.game.getPawn(pawnx, pawny));
		    			for (Move move : m)
		    			{
		    				String x,y;
		    				if (move.x<10)
		    					x="0"+Integer.toString(move.x);
		    				else
		    					x=Integer.toString(move.x);
		    				if (move.y<10)
		    					y="0"+Integer.toString(move.y);
		    				else
		    					y=Integer.toString(move.y);
		    				output.println("VALIDMOVE " + x + " " + y);
		    			}
		    			this.pawnLocked=true;
		    		}
		    		else if (command.startsWith("END") && this.current==true)
		    		{
		    			int no=( (this.noPlayer+1>this.game.getnoPlayers()) ? 1 : this.noPlayer+1 );
		    			this.current=false;
		    			for (Player p : opponents)
		    			{
		    				if (p.getnoPlayer()==no)
		    					p.setCurrent(true);
		    			}
		    			this.pawnLocked=false;
		    		}
		    		else if (command.startsWith("QUIT"))
		    		{
		    			return;
		    		}
		    	}
	    	}
    	}
    	catch (IOException e)
    	{
    		
    	}
    	finally
    	{
    		try {socket.close();} catch (IOException e) {}
    	}
    }

}
