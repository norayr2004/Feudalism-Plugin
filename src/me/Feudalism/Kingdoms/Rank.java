package me.Feudalism.Kingdoms;

public enum Rank
{
	SERF(0), KNIGHT(1), NOBLE(2), KING(3);
	
	private int ranking;
	
	Rank(int rank)
	{
		this.ranking = rank;
	}
	
	public boolean isTrusted()
	{
		return this == KNIGHT
				|| this == NOBLE
				|| this == KING;
	}
			
	public boolean isRuler()
	{
		return this == NOBLE
				|| this == KING;
	}
	
	public boolean isOwner()
	{
		return this == KING;
	}

	/**
	 * @return the ranking
	 */
	public int getRank()
	{
		return ranking;
	}
	
	public void rankUp()
	{
		if (this.ranking >= 3)
			return;
		this.ranking = this.getRank() + 1;
		return;
	}
	
	public void rankDown()
	{
		if (this.ranking < 0)
		{
			//TODO SET WANTED
			return;
		}
		this.ranking = this.getRank() - 1;
		return;
	}
}
