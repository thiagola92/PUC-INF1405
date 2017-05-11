package server;

public class Equipment extends Card {
	
	private int damage;
	private int attacks;
	private int distance;
	private int range;

	public Equipment(String name, String description,
			int damage, int attacks, int distance, int range) {
		
		super(name, description);
		this.damage = damage;
		this.attacks = attacks;
		this.distance = distance;
		this.range = range;
	}

}
