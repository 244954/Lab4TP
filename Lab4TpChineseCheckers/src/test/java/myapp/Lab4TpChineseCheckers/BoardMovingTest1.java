package myapp.Lab4TpChineseCheckers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class BoardMovingTest1 {

	Board b;
	GameBuilder gb;
	Game g;
	
	@Test
	public void firstTest() // was the board set up properly
	{
		b=new Board121w2Players();
		assertEquals(b.getSquare(0, 0),-1);
	}
	@Test
	public void secondTest() // was the board set up properly
	{
		b=new Board121w2Players();
		assertEquals(b.getSquare(6, 2),1);
	}
	@Test
	public void thirdTest() // was the board set up properly
	{
		b=new Board121w2Players();
		assertEquals(b.getSquare(10, 6),0);
	}
	@Test
	public void fourthTest() // was the pawn moved properly
	{
		gb=new StandardGameBuilder2Players();
		g=gb.build();
		g.movePawn(g.getPawn(7, 4), 7, 6);
		assertEquals(g.getSquare(7, 6),1);
	}
	@Test
	public void rulesfirstTest() // do the rules work properly 
	{
		gb=new StandardGameBuilder2Players();
		g=gb.build();
		g.movePawn(g.getPawn(7, 4), 7, 6);
		
		assertEquals(g.canmove(g.getPawn(7, 6), 7, 4),true);
	}
	@Test
	public void rulessecondTest() // do the rules work properly 
	{
		gb=new StandardGameBuilder2Players();
		g=gb.build();
		g.movePawn(g.getPawn(7, 4), 7, 6);
		
		assertEquals(g.canmove(g.getPawn(7, 6), 6, 7),false);
	}
	@Test
	public void rulesdestinationTest() // is a pawn locked at its destination
	{
		gb=new StandardGameBuilder2Players();
		g=gb.build();
		g.movePawn(g.getPawn(7, 4), 7, 6);
		g.movePawn(g.getPawn(12, 15), 12, 14);
		g.movePawn(g.getPawn(7, 6), 12, 15); // illegal move, but that's not checked here
		
		assertEquals(g.canmove(g.getPawn(12, 15), 11, 14),false); 
	}
	@Test
	public void haswonTest() // obviously not
	{
		gb=new StandardGameBuilder2Players();
		g=gb.build();
		g.movePawn(g.getPawn(7, 4), 7, 6);
		g.movePawn(g.getPawn(12, 15), 12, 14);
		
		assertEquals(g.haswon(1),false); 
	}
	@Test
	public void haswonTest2() // maybe
	{
		gb=new StandardGameBuilder2Players();
		g=gb.build();
		g.movePawn(g.getPawn(6, 2), 14, 18);
		g.movePawn(g.getPawn(6, 3), 14, 17);
		g.movePawn(g.getPawn(6, 4), 14, 16);
		g.movePawn(g.getPawn(6, 5), 14, 15);
		
		g.movePawn(g.getPawn(7, 3), 13, 17);
		g.movePawn(g.getPawn(7, 4), 13, 16);
		g.movePawn(g.getPawn(7, 5), 13, 15);
		
		g.movePawn(g.getPawn(8, 4), 12, 16);
		g.movePawn(g.getPawn(8, 5), 12, 15);
		
		g.movePawn(g.getPawn(9, 5), 11, 15);
		
		assertEquals(g.haswon(1),true); 
	}
}
