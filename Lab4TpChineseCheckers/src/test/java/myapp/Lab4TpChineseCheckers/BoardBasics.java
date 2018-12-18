package myapp.Lab4TpChineseCheckers;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardBasics {

	Board b;
	
	@Test
	public void test1() {
		b=new Board121w2Players();
		assertEquals(2,b.getSquare(12, 16));
	}
	@Test
	public void test2() {
		b=new Board121w2Players();
		assertEquals(-1,b.getSquare(12, 17));
	}
	@Test
	public void test3() {
		b=new Board121w3Players();
		assertEquals(2,b.getSquare(15, 11));
	}
	@Test
	public void test4() {
		b=new Board121w3Players();
		assertEquals(0,b.getSquare(6, 6));
	}
	@Test
	public void test5() {
		b=new Board121w4Players();
		assertEquals(4,b.getSquare(2, 6));
	}
	@Test
	public void test6() {
		b=new Board121w6Players();
		assertEquals(6,b.getSquare(2, 6));
	}
	@Test
	public void test7() {
		b=new Board121w6Players();
		assertEquals(18,b.destination(6).x);
	}

}
