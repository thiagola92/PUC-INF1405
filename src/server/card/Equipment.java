package server.card;

import server.player.Player;

/**
 * You came here from Player.
 * This a unique type of card called equipment.
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
public class Equipment implements Card {

	private String name;
	private String description;
	private int damage;
	private int attacks;
	private int distance;
	private int range;
	private FunctionEquipment function;

	/**
	 * An card from type Equipment.
	 * @param name				Name of the card
	 * @param description		Description of the card
	 * @param damage			How much increase the damage of the player
	 * @param attacks			How many times more the player can attack
	 * @param distance			How much increase the distance from this player to others
	 * @param range				How much increase the range from the attacks of this player
	 * @param function			What the card is suppose to do when used
	 */
	public Equipment(String name, String description,
			int damage, int attacks, int distance, int range,
			FunctionEquipment function) {
		
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
		function.useCard(this, player);
	}

}
