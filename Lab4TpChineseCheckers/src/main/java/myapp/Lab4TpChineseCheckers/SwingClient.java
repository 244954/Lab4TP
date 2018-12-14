package myapp.Lab4TpChineseCheckers;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class SwingClient extends JFrame{

	/**Konstruktor frame'a */
	public SwingClient()
	{
		initUI();
	}
	/**Przygotowanie programu */
	private void initUI()
	{
		Surface xx=new Surface();
		//Thread thread=new Thread(xx);
		add(xx);
		setTitle("Warcaby");
        	setSize(1020, 800);
        	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	setLocationRelativeTo(null);
		setResizable(false);
		
		//xx.play();
		
		
	}
	/**
	* Main
	* @param args Argumenty (Nieużywane)
	*/
	public static void main(String[] args)
	{        
		SwingClient ex = new SwingClient();	
		ex.setVisible(true);
	}

}
