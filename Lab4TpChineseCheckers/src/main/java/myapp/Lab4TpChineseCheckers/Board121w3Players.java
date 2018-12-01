package myapp.Lab4TpChineseCheckers;

public class Board121w3Players extends Board {

	public Board121w3Players() {
		setUp();
	}

	@Override
	public void setUp() {
		for (int i=0;i<19;i++) // poza plansza
			for (int j=0;j<19;j++)
				p[j][i]=-1;
		for (int i=5;i<=13;i++) // puste pola
			for (int j=5;j<=13;j++)
				p[j][i]=0;
		for (int i=1;i<=4;i++) // rog 1
			for (int j=5;j<=5-1+i;j++)
				p[j][i]=1;
		for (int i=10;i<=13;i++) // rog 3
			for (int j=14;j<=14-10+i;j++)
				p[j][i]=2;
		for (int i=14;i<=17;i++) // rog 4
			for (int j=10+i-14;j<=13;j++)
				p[j][i]=0;
		for (int i=10;i<=13;i++) // rog 5
			for (int j=5;j<=5-1+i;j++)
				p[j][i]=3;
		for (int i=5;i<=8;i++) // rog 6
			for (int j=1+i-5;j<=4;j++)
				p[j][i]=0;

	}

}
