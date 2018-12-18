package myapp.Lab4TpChineseCheckers;

public class Board121w3Players extends Board {

	public Board121w3Players() 
        {
		setUp();
	}

	@Override
	public void setUp() 
        {
		setnoPlayers(3);
		
		destination=new int[30][3];
		int decount=0;
		p=new int[21][21];
		
		for (int i=0;i<21;i++) // outside the board
			for (int j=0;j<21;j++)
				p[j][i]=-1;
		for (int i=6;i<=14;i++) // empty spaces
			for (int j=6;j<=14;j++)
				p[j][i]=0;
		for (int i=2;i<=5;i++) // corner 1
			for (int j=6;j<=5-1+i;j++)
				p[j][i]=1;
		for (int i=6;i<=9;i++) // corner 2
			for (int j=10+i-5;j<=14;j++)
			{
				destination[decount][0]=3;
				destination[decount][1]=j;
				destination[decount][2]=i;
				decount++;
			}
		for (int i=11;i<=14;i++) // corner 3
			for (int j=15;j<=14-10+i;j++)
				p[j][i]=2;
		for (int i=15;i<=18;i++) // corner 4
			for (int j=10+i-14;j<=14;j++)
			{
				p[j][i]=0;
				destination[decount][0]=1;
				destination[decount][1]=j;
				destination[decount][2]=i;
				decount++;
			}
		for (int i=11;i<=14;i++) // corner 5
			for (int j=6;j<=5-10+i;j++)
				p[j][i]=3;
		for (int i=6;i<=9;i++) // corner 6
			for (int j=1+i-5;j<=5;j++)
			{
				p[j][i]=0;
				destination[decount][0]=2;
				destination[decount][1]=j;
				destination[decount][2]=i;
				decount++;
			}
		
		setnoPlayers(3);

	}
	@Override
	public Move destination(int noplayer) 
        {
		switch (noplayer)
		{
                    case 1:
                    {
                            return new Move(14,18);
                    }
                    case 2:
                    {
                            return new Move(2,6);
                    }
                    case 3:
                    {
                            return new Move(14,6);
                    }
                    default:
                    {
                            return null;
                    }
		}
	}

}
