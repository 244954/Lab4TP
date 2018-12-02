package myapp.Lab4TpChineseCheckers;

public interface GameBuilder {

	Game build();
	GameBuilder setnoPlayers();
	GameBuilder setBoard();
	GameBuilder setRules();
	GameBuilder setPawns();
	
}
