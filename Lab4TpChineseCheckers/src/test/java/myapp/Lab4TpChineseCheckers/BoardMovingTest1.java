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
}
