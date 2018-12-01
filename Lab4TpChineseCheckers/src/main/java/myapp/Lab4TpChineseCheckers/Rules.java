package myapp.Lab4TpChineseCheckers;

public interface Rules {

	
	void fetchBoard(Board b);
	boolean canmove(Pawn p,int nx,int ny);
}
