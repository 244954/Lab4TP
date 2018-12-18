package myapp.Lab4TpChineseCheckers;

import static org.junit.Assert.*;

import org.junit.Test;

public class PawnTest {

	Pawn p;
	
	@Test
	public void test1() {
		p=new Pawn(1,4,4);
		p.move(5, 5);
		assertEquals(p.getDidmove(),true);
	}
	
	@Test
	public void test2() {
		p=new Pawn(1,2,1);
		p.move(3, 4);
		assertEquals(p.getX(),3);
	}

}
