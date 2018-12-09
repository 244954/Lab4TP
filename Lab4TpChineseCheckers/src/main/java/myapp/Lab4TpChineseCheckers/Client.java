package myapp.Lab4TpChineseCheckers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	private static int PORT = 8901;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    
    String message;
    Board board;
    int noplayer;
	
	public Client(String serverAddress) throws Exception {
		// Setup networking
        socket = new Socket(serverAddress, PORT);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
	}
	
	public void play()
	{
		String response;
		try {
            response = in.readLine();
            if (response.startsWith("WELCOME")) {;
                noplayer = Integer.parseInt(response.substring(8,9));
                System.out.println("Poloczono "+noplayer);
            }
            response=in.readLine();
            if (response.startsWith("GAMEMODE")) {
            	switch (response.substring(9,10))
                {
                case "1":
                {
                	board=new Board121w2Players();
                	System.out.println("Gamemode 1");
                }
                }
            }
            while (true) {
                response = in.readLine();
                if (response.startsWith("VALIDMOVE")) {
                    
                } else if (response.startsWith("OTHER_MOVED")) {
                    
                } else if (response.startsWith("VICTORY")) {
                    message="You win";
                    break;
                } else if (response.startsWith("MESSAGE")) {
                    message=response.substring(8);
                    System.out.println(message);
                    if (message.equals("Your move"))
                    {
                    	System.out.println("Wyslano");
                    	out.println("END");
                    }
                }
            }
            out.println("QUIT");
        }
		catch (Exception e)
		{
			
		}
        finally {
            try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		
	}
	public static void main(String[] args) throws Exception {
        while (true) {
            String serverAddress = (args.length == 0) ? "localhost" : args[1];
            Client client = new Client(serverAddress);
            client.play();
        }
    }

}
