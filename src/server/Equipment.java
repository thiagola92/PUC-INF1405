package server;

public class Equipment implements Card {

	private String name;
	private String description;
	private int damage;
	private int attacks;
	private int distance;
	private int range;
	private Function function;

	public Equipment(String name, String description,
			int damage, int attacks, int distance, int range,
			Function function) {
		
		this.name = name;
		this.description = description;
		this.damage = damage;
		this.attacks = attacks;
		this.distance = distance;
		this.range = range;
		this.function = function;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getDamage() {
		return damage;
	}

	public int getAttacks() {
		return attacks;
	}

	public int getDistance() {
		return distance;
	}

	public int getRange() {
		return range;
	}

	@Override
	public void useCard(Player player) {
		function.useCard(player);
	}

}
