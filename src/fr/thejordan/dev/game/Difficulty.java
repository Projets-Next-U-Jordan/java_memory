package fr.thejordan.dev.game;

public enum Difficulty {

	EASY("Easy 2x3", 2,3),
	MEDIUM("Medium 3x4",3,4),
	HARD("Hard 4x4",4,4);
	
	public int col;
	public int row;
	public String name;
	
	public int volume() {
		return col*row;
	}
	
	Difficulty(String name, int col, int row) {
		this.name = name;
		this.col = col;
		this.row = row;
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
