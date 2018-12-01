package myapp.Lab4TpChineseCheckers;

public interface GameBuilder {

	Game build();
	void setnoPlayers(int n);
	void setBoard(Board b);
	void setRules(Rules r);
	void setPawns(Pawn[] p);
	
}
