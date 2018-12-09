package myapp.Lab4TpChineseCheckers;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	

	public static void main(String[] args) throws Exception {
		
		List<Player> players;
		
        ServerSocket listener = new ServerSocket(8901);
        System.out.println("Chinese Checkers Server is Running");
        try {
            while (true) {
            	GameBuilder gb=new StandardGameBuilder2Players();
            	Game g=gb.build();
                players=new ArrayList<Player>();
                players.add(new HumanPlayer(listener.accept(),1, g,1));
                players.add(new HumanPlayer(listener.accept(),2, g,1));
                for (Player p: players)
                {
                	System.out.println(p.getnoPlayer());
                }
                for (Player p: players)
                {
                	System.out.println(p.getnoPlayer());
                	p.setOpponents(players);
                	if (p.getnoPlayer()==1)
                	{
                		p.setCurrent(true); // ten gracz zaczyna
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
