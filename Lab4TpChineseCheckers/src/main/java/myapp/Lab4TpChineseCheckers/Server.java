package myapp.Lab4TpChineseCheckers;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	

	public static void main(String[] argv) throws Exception {
		
		int lp,lbots,tryb;
		lp=Integer.parseInt(argv[0]);
		lbots=Integer.parseInt(argv[1]);
		if (lp==lbots) // if only bots, don't run
		{
			return;
		}
		List<Player> players;
        ServerSocket listener = new ServerSocket(8901);
        System.out.println("Chinese Checkers Server is Running");
        try {
            while (true) {
            	
            	GameBuilder gb;
            	switch(lp)
            	{
	            	case 2:
	            	{
	            		gb=new StandardGameBuilder2Players();
	            		tryb=1;
	            		break;
	            	}
	            	case 3:
	            	{
	            		gb=new StandardGameBuilder3Players();
	            		tryb=2;
	            		break;
	            	}
	            	case 4:
	            	{
	            		gb=new StandardGameBuilder4Players();
	            		tryb=3;
	            		break;
	            	}
	            	case 6:
	            	{
	            		gb=new StandardGameBuilder6Players();
	            		tryb=4;
	            		break;
	            	}
	            	default:
	            		return; // if wrong parameters
            	}
            	Game g=gb.build();
                players=new ArrayList<>();
                for (int i=1;i<=lp-lbots;i++) // setting human players
                {
                	players.add(new HumanPlayer(listener.accept(),i, g,tryb));
                }
                for (int i=lp-lbots+1;i<=lp;i++) //setting bots
                {
                	players.add(new UpgradedBotPlayer(i,g,tryb));
                }
                for (Player p: players)
                {
                	System.out.println(p.getnoPlayer());
                	p.setOpponents(players);
                	if (p.getnoPlayer()==1)
                	{
                		p.setCurrent(true); // this player starts
                	}
                }
                for (Player p: players)
                {
                	p.start();
                }
            }
        } finally {
            listener.close();
        }
    }

}
