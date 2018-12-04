package myapp.Lab4TpChineseCheckers;

import java.util.ArrayList;
import java.util.List;

public class StandardGameBuilder4Players implements GameBuilder {

	Game gametmp;
	
	public StandardGameBuilder4Players() {
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
		gametmp.setnoPlayers(4);
		return this;
	}

	public GameBuilder setBoard() {
		gametmp.setBoard(new Board121w4Players());
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
		for (int i=11;i<=14;i++) // rog 3
			for (int j=15;j<=14-10+i;j++)
				p.add(new Pawn(2,j,i));
		// pionki gracza 3
		for (int i=15;i<=18;i++) // rog 4
			for (int j=10+i-14;j<=14;j++)
			{
				p.add(new Pawn(3,j,i));
			}
		// pionki gracza 4
		for (int i=6;i<=9;i++) // rog 6
			for (int j=1+i-5;j<=5;j++)
			{
				p.add(new Pawn(4,j,i));
			}
		
		gametmp.setPawns(p);
		return this;
	}

}
