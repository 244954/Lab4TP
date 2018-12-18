package myapp.Lab4TpChineseCheckers;

import static org.junit.Assert.*;

import org.junit.Test;

public class MoveTest {

	Move m,mm;
	@Test
	public void test1() {
		m=new Move(1,1);
		mm=new Move(2,2);
		assertEquals(2,m.distance(mm));
	}
	
	@Test
	public void test2() {
		m=new Move(1,8);
		assertEquals(8,m.y);
	}

}
