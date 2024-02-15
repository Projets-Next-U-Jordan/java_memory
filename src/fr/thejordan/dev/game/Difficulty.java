package fr.thejordan.dev.game;

public enum Difficulty {

	EASY("Easy 2x3",2,3,3,200L),
	MEDIUM("Medium 3x4",3,4,20,400L),
	HARD("Hard 4x4",4,4,30,600L);
	
	public int col;
	public int row;
	public int maxTries;
	public long maxTime;
	public String name;
	
	public int volume() {
		return col*row;
	}
	
	Difficulty(String name, int col, int row, int maxTries, long maxTime) {
		this.name = name;
		this.col = col;
		this.row = row;
		this.maxTries = maxTries;
		this.maxTime = maxTime;
	}
	
    @Override
    public String toString()
    {
        return name;
    }

    public String getKey()
    {
        return name;
    }

    public Difficulty getValue() {
        return this;
    }
	
}
