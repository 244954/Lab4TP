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

        @Override
	public Game build() {
		return gametmp;
	}

        @Override
	public GameBuilder setnoPlayers() {
		gametmp.setnoPlayers(4);
		return this;
	}

        @Override
	public GameBuilder setBoard() {
		gametmp.setBoard(new Board121w4Players());
		return this;

	}

        @Override
	public GameBuilder setRules() {
		gametmp.setRules(new OurRules());
		return this;

	}

        @Override
	public GameBuilder setPawns() {
		List <Pawn> p;
		p=new ArrayList<>();
		
		// player 1 pawns
		for (int i=2;i<=5;i++) // corner 1
			for (int j=6;j<=5-1+i;j++)
				p.add(new Pawn(1,j,i));
		// player 2 pawns
		for (int i=11;i<=14;i++) // corner 3
			for (int j=15;j<=14-10+i;j++)
				p.add(new Pawn(2,j,i));
		// player 3 pawns
		for (int i=15;i<=18;i++) // corner 4
			for (int j=10+i-14;j<=14;j++)
			{
				p.add(new Pawn(3,j,i));
			}
		// player 4 pawns
		for (int i=6;i<=9;i++) // corner 6
			for (int j=1+i-5;j<=5;j++)
			{
				p.add(new Pawn(4,j,i));
			}
		
		gametmp.setPawns(p);
		return this;
	}

}
