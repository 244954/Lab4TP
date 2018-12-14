package myapp.Lab4TpChineseCheckers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.scene.shape.Circle;

public class Surface extends JPanel {

	protected ZRectangle [][] zrect;
	protected Board board;
	private int ZrectLen;
	
    private double X,Y;
    private int noplayer;
    private int chpawnx=-1,chpawny=-1,destinx=-1,destiny=-1;
    private boolean pawnlock;
    
    List<Move> posmoves;
    
    private static int PORT = 8901;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    
	public Surface()
	{
		initUI();
	}
	private void initUI()
	{

		/**Tworzę adapter*/
        	MovingAdapter ma = new MovingAdapter();

        	addMouseMotionListener(ma);
        	addMouseListener(ma);
        	/*
        	try
        	{
        
        	// Setup networking
            socket = new Socket("localhost", PORT);
            in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        	} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		*/
        	
        	
            this.noplayer=1; // to ustawia serwer
            this.pawnlock=false;
            posmoves=new ArrayList<Move>();
            
            List<Move> mymoves;
            mymoves=new ArrayList<Move>();
            ZrectLen=21;
			create_game(1);
	}
	
	public void play()
    {
    	String response;
		try {
            response = in.readLine();
            if (response.startsWith("WELCOME")) {;
                this.noplayer = Integer.parseInt(response.substring(8,9));
                System.out.println("Polaczono "+noplayer);
            }
            response=in.readLine();
            if (response.startsWith("GAMEMODE")) {
            	switch (response.substring(9,10))
                {
                case "1":
                {
                	//board = new Board121w2Players();
                	create_game(1);
                	System.out.println("Gamemode 1");
                }
                break;
                case "2":
                {
                	create_game(2);
                	System.out.println("Gamemode 2");
                }
                break;
                case "3":
                {
                	create_game(3);
                	System.out.println("Gamemode 3");
                }
                break;
                case "4":
                {
                	create_game(4);
                	System.out.println("Gamemode 4");
                }
                }
            }
            
            while (true) {
            	if (in.ready() && (response = in.readLine())!=null)
            	{
	                
	                if (response.startsWith("VALIDMOVE")) {
	                    int parsx,parsy;
	                    System.out.println(response);
	                    parsx=Integer.parseInt(response.substring(10,12));
	                    parsy=Integer.parseInt(response.substring(13,15));
	                    posmoves.add(new Move(parsx,parsy));
	                    repaint();
	                	// do posmoves dodajemy new Move(parsx,parsy)
	                	
	                } else if (response.startsWith("OTHER_MOVED")) {
	                	
	                	// move(x,y,nx,ny)
	                    
	                } else if (response.startsWith("VICTORY")) {
	                	 System.out.println("wygrales");
	                    break;
	                } else if (response.startsWith("MESSAGE")) {
	                    System.out.println(response);
	                }
            	}
            }
            
            
            


            
            
            out.println("QUIT");
        }
		catch (IOException e)
    	{
    		
    	}
    	finally
    	{
    		try {socket.close();} catch (IOException e) {}
    	}
    }
	
	public void create_game(int gamemode) //w tej czesci sprawdzamy warunki i jesli sa poprawne, to tworzymy plansze
    {
            switch (gamemode) 
            {
                case 1:
                    board = new Board121w2Players();
                    break;
                case 2:
                    board = new Board121w3Players();
                    break;
                case 3:
                    board = new Board121w4Players();
                    break;
                case 4:
                    board = new Board121w6Players();
                    break;
                default:
                    break;
            }
            if (board == null)
            {
                
            } 
            
            else 
            {
                zrect=new ZRectangle[21][21];
                for (int i=0;i<21;i++)
                {
                	for (int j=0;j<21;j++)
                	{
                		zrect[j][i]=new ZRectangle(j*20,i*20,20,20,250,250,250);
                		if (board.getSquare(j, i)==-1)
                		{
                			zrect[j][i].setColour(255, 255, 255);
                		}
                		else
                		if (board.getSquare(j, i)==0)
                    	{
                    		zrect[j][i].setColour(0, 0, 0);
                    	}
                		else
                		if (board.getSquare(j, i)==1)
                    	{
                    		zrect[j][i].setColour(255, 0, 0);
                    	}
                		else
                		if (board.getSquare(j, i)==2)
                    	{
                    		zrect[j][i].setColour(0, 255, 0);
                    	}
                		else
                		if (board.getSquare(j, i)==3)
                    	{
                    		zrect[j][i].setColour(0, 0, 255);
                    	}
                		else
                		if (board.getSquare(j, i)==4)
                    	{
                    		zrect[j][i].setColour(132, 0, 255);
                    	}
                		else
                		if (board.getSquare(j, i)==5)
                    	{
                    		zrect[j][i].setColour(255, 255, 0);
                    	}
                		else
                		if (board.getSquare(j, i)==6)
                    	{
                    		zrect[j][i].setColour(0, 255, 255);
                    	}
                	}
                } 
                
                repaint();
                
            }
            
    }
	
	void mark (int a,int b)
	{
		if (this.board.getSquare(a, b)==this.noplayer && pawnlock==false)
    	{
    		chpawnx=a;
    		chpawny=b;
    		String sx,sy;
			if (a<10)
				sx="0"+Integer.toString(a);
			else
				sx=Integer.toString(a);
			if (b<10)
				sy="0"+Integer.toString(b);
			else
				sy=Integer.toString(b);
    		//out.println("PAWN " + sx + " " + sy);
    		//wyślij komunikat do serwera PAWN a b DONE
    	}
    	else
    	if (this.board.getSquare(a, b)==0)
        {
    		if (chpawnx!=-1 && chpawny!=-1)
    		{
            	destinx=a;
            	destiny=b;
    		}
        }
    	if (chpawnx!=-1 && chpawny!=-1 && destinx!=-1 && destiny!=-1)
    	{
    		move(chpawnx,chpawny,destinx,destiny);
    	}
	}
	
	public void move(int x,int y,int nx,int ny)
    {
    	board.move(x, y, nx, ny);
    	if (board.getSquare(x, y)==0)
    	{
    		zrect[x][y].setColour(0, 0, 0);
    	}
		else
		if (board.getSquare(x, y)==1)
    	{
    		zrect[x][y].setColour(255, 0, 0);
    	}
		else
		if (board.getSquare(x, y)==2)
    	{
    		zrect[x][y].setColour(0, 255, 0);
    	}
		else
		if (board.getSquare(x, y)==3)
    	{
    		zrect[x][y].setColour(0, 0, 255);
    	}
		else
		if (board.getSquare(x, y)==4)
    	{
    		zrect[x][y].setColour(132, 0, 255);
    	}
		else
		if (board.getSquare(x, y)==5)
    	{
    		zrect[x][y].setColour(255, 255, 0);
    	}
		else
		if (board.getSquare(x, y)==6)
    	{
    		zrect[x][y].setColour(0, 255, 255);
    	}
    	if (board.getSquare(nx, ny)==0)
    	{
    		zrect[nx][ny].setColour(0, 0, 0);
    	}
		else
		if (board.getSquare(nx, ny)==1)
    	{
    		zrect[nx][ny].setColour(255, 0, 0);
    	}
		else
		if (board.getSquare(nx, ny)==2)
    	{
    		zrect[nx][ny].setColour(0, 255, 0);
    	}
		else
		if (board.getSquare(nx, ny)==3)
    	{
    		zrect[nx][ny].setColour(0, 0, 255);
    	}
		else
		if (board.getSquare(nx, ny)==4)
    	{
    		zrect[nx][ny].setColour(132, 0, 255);
    	}
		else
		if (board.getSquare(nx, ny)==5)
    	{
    		zrect[nx][ny].setColour(255, 255, 0);
    	}
		else
		if (board.getSquare(nx, ny)==6)
    	{
    		zrect[nx][ny].setColour(0, 255, 255);
    	}
    	chpawnx=nx;
    	chpawny=ny;
    	repaint();
    	destinx=destiny=-1;
    	pawnlock=true;
    	removeselect();
    	
    	// wyslij komunikat do serwera MOVE nx ny
    }
    public void endmove()
    {
    	chpawnx=chpawny=-1;
    	destinx=destiny=-1;
    	pawnlock=false;
    	
    	// wyślij komunikat do serwera END
    }
    
    public void selpossmov(List<Move> m)
    {
    	this.posmoves=new ArrayList<Move>(m);
    }
    public void removeselect()
    {
    	this.posmoves.clear();
    	repaint();
    }
	
	class ZRectangle extends Rectangle2D.Float
	{
		/**Przechowuje kolor prostokąta*/
		private int [] kolor;
        
		/** 
		*Konstruktor prostokata
		*@param x Współrzędna x
		*@param y Współrzędna y
		*@param width Szerokość
		*@param height Wysokość
		*@param a Kolor czerwony
		*@param b Kolor zielony
		*@param c Kolor niebieski
		*/
        public ZRectangle(float x, float y, float width, float height,int a,int b,int c)
		{   
            setFrame(x, y, width, height);
			kolor=new int[3];
			setColour(a,b,c);
        }
		/**
		*Wykrywa czy kursor natrafił na prostokąt
		*@param x Współrzędna x
		*@param y Współrzędna y
		*@return True- kursor trafił na prostokąt, False- nie
		*/
        public boolean isHit(float x, float y)
		{
			return getBounds2D().contains(x, y);
        }
		public void setColour(int a,int b,int c)
		{
			this.kolor[0]=a;
			this.kolor[1]=b;
			this.kolor[2]=c;
		}
    }
	private void doDrawing(Graphics g)
	{
        
       		Graphics2D g2d = (Graphics2D) g;
        
       		Font font = new Font("Serif", Font.BOLD, 40);
       		g2d.setFont(font);
        	
		for (int i=0;i<ZrectLen;i++)
		{
			for (int j=0;j<ZrectLen;j++)
			{
				g2d.setPaint(new Color(zrect[j][i].kolor[0],zrect[j][i].kolor[1],zrect[j][i].kolor[2]));
        		g2d.fill(zrect[j][i]); 
			}
		}
    }
	@Override
	public void paintComponent(Graphics g)
	{
       		super.paintComponent(g);
        
       		doDrawing(g);        
 	}
	/**Klasa obsługująca działanie myszy */
	class MovingAdapter extends MouseAdapter implements ActionListener
	{
		/**Współrzędna x*/
	        private int x;
		/**Współrzędna y*/
        	private int y;
		/**Numer zaznaczonego prostokąta*/
		private int zx=-1;
		private int zy=-1;

		@Override
    	public void mousePressed(MouseEvent e)
    	{
			x=e.getX();
			y=e.getY();
			//System.out.println(x+" "+y);
    	}
        
        @Override
    	public void mouseReleased(MouseEvent e)
        {
				for (int a=0; a<21; a++)
	            {
	                for (int b=0; b<21; b++)
	                {
						if (zrect[a][b].isHit(x, y))
						{
							mark(a,b);
						}
					}
				}
        }



		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}
