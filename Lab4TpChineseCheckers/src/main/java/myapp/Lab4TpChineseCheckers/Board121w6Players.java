package myapp.Lab4TpChineseCheckers;

public class Board121w6Players extends Board {

	public Board121w6Players() {
		setUp();
	}

	@Override
	public void setUp() {
		p=new int[21][21];
		for (int i=0;i<21;i++) // poza plansza
			for (int j=0;j<21;j++)
				p[j][i]=-1;
		for (int i=6;i<=14;i++) // puste pola
			for (int j=6;j<=14;j++)
				p[j][i]=0;
		for (int i=2;i<=5;i++) // rog 1
			for (int j=6;j<=5-1+i;j++)
				p[j][i]=1;
		for (int i=6;i<=9;i++) // rog 2
			for (int j=10+i-5;j<=14;j++)
				p[j][i]=2;
		for (int i=11;i<=14;i++) // rog 3
			for (int j=15;j<=14-10+i;j++)
				p[j][i]=3;
		for (int i=15;i<=18;i++) // rog 4
			for (int j=10+i-14;j<=14;j++)
				p[j][i]=4;
		for (int i=11;i<=14;i++) // rog 5
			for (int j=6;j<=5-10+i;j++)
				p[j][i]=5;
		for (int i=6;i<=9;i++) // rog 6
			for (int j=1+i-5;j<=5;j++)
				p[j][i]=6;
		
		setnoPlayers(6);

	}

}
