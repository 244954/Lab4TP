package myapp.Lab4TpChineseCheckers;

import static org.junit.Assert.*;


import org.junit.Test;


public class BoardMovingTest1 {

	Board b;
	
	@Test
	public void firstTest()
	{
		b=new Board121w2Players();
		assertEquals(b.getSquare(0, 0),-1);
	}
	@Test
	public void secondTest()
	{
		b=new Board121w2Players();
		assertEquals(b.getSquare(6, 2),1);
	}
	@Test
	public void thirdTest()
	{
		b=new Board121w2Players();
		assertEquals(b.getSquare(10, 6),0);
	}
}
