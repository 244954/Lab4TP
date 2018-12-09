package myapp.Lab4TpChineseCheckers;

import java.net.ServerSocket;

public class Server {
	

	public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(8901);
        System.out.println("Chinese Checkers Server is Running");
        try {
            while (true) {
            	GameBuilder gb=new StandardGameBuilder2Players();
            	Game g=gb.build();
                
            }
        } finally {
            listener.close();
        }
    }

}
