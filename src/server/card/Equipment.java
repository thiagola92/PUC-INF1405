/**
 * Equipments have the ability to stay in game after use
 * <br>It will attach to one player and have some effect.
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */

package server.card;

import server.player.Player;

public class Equipment implements Card {

	private String name;
	private String description;
	private int damage;
	private int attacks;
	private int distance;
	private int range;
	private FunctionEquipment function;

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
