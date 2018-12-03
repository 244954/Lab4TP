package myapp.Lab4TpChineseCheckers;

import java.util.ArrayList;
import java.util.List;

public class StandardGameBuilder2Players implements GameBuilder {

	Game gametmp;
	
	public StandardGameBuilder2Players() {
		gametmp=new Game();
		this.setnoPlayers();
		this.setBoard();
		this.setRules();
		this.setPawns();
	}

	public Game build() {
		// TODO Auto-generated method stub
		return gametmp;
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
		for (int i=2;i<=5;i++) // rog 1
			for (int j=6;j<=5-1+i;j++)
				p.add(new Pawn(1,j,i));
		// pionki gracza 2
		for (int i=15;i<=18;i++) // rog 4
			for (int j=10+i-14;j<=14;j++)
				p.add(new Pawn(2,j,i));
		
		gametmp.setPawns(p);
		return this;
	}

}
