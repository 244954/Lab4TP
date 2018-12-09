package myapp.Lab4TpChineseCheckers;

import java.util.List;

public interface Rules {

	
	void fetchBoard(Board b);
	boolean canmove(Pawn p,int nx,int ny);
	boolean haswon(int playerID,List <Pawn> p);
}
