package myapp.Lab4TpChineseCheckers;

import java.util.ArrayList;
import java.util.List;

public class StandardGameBuilder2Players implements GameBuilder {

	Game gametmp;
	
	public StandardGameBuilder2Players() {
		gametmp=new Game();
	}

	public Game build() {
		// TODO Auto-generated method stub
		return null;
	}

	public GameBuilder setnoPlayers() {
		gametmp.setnoPlayers(2);
		return this;
	}

	public GameBuilder setBoard() {
		gametmp.setBoard(new Board121w2Players());
		return this;

	}

	public GameBuilder setRules() {
		gametmp.setRules(new OurRules());
		return this;

	}

	public GameBuilder setPawns() {
		List <Pawn> p;
		p=new ArrayList<Pawn>();
		
		// pionki gracza 1
		for (int i=1;i<=4;i++) // rog 1
			for (int j=5;j<=5-1+i;j++)
				p.add(new Pawn(1,j,i));
		// pionki gracza 2
		for (int i=14;i<=17;i++) // rog 4
			for (int j=10+i-14;j<=13;j++)
				p.add(new Pawn(2,j,i));
		
		gametmp.setPawns(p);
		return this;
	}

}
