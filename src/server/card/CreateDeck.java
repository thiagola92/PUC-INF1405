package server.card;

import java.util.ArrayList;

import server.card.interfaces.Card;
import server.card.interfaces.FunctionEquipment;
import server.card.interfaces.FunctionSpecial;
import server.card.interfaces.FunctionWeapon;

/**
 * You probably came from Board.
 * <br>This class will make:
 * <li>Create all cards.</li>
 * <li>Putting them on one ArrayList</li>
 * <br>If you want to add a new card to the game, you should make this here.
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
public class CreateDeck {
	
	private ArrayList<Card> deck;
	
	public CreateDeck(ArrayList<Card> deck) {
		this.deck = deck;
		
		// Equipments
		
		equipment(5, "Equip1", 1, 0, 0, 0, (card, player, board) -> {
			player.equipCard(card);
		});
		
		equipment(5, "Equip2", 0, 1, 0, 0, (card, player, board) -> {
			player.equipCard(card);
		});
		
		equipment(5, "Equip3", 0, 0, 1, 0, (card, player, board) -> {
			player.equipCard(card);
		});
		
		equipment(5, "Equip4", 0, 0, 0, 1, (card, player, board) -> {
			player.equipCard(card);
		});
		
		// Weapons
		
		weapon(5, "Weapon1", 4, 1, (card, player, board) -> {
			player.attackPlayer(card);
		});
		
		weapon(5, "Weapon2", 3, 2, (card, player, board) -> {
			player.attackPlayer(card);
		});
		
		weapon(5, "Weapon3", 2, 3, (card, player, board) -> {
			player.attackPlayer(card);
		});
		
		weapon(5, "Weapon4", 1, 4, (card, player, board) -> {
			player.attackPlayer(card);
		});
		
		// Events
		
		event(5, "Buy card", (card, player, board) -> {
			player.receiveCards(board.pickFromDeck(2));
			player.discardCard(card);
		});
		
		event(5, "Block", (card, player, board) -> {
		});
		
		event(5, "Block", (card, player, board) -> {
		});
		
		event(5, "Block", (card, player, board) -> {
		});
		
		System.out.println(">>Created all cards in the game");
	}
	
	private void equipment(int quantity, String name, int damage, int attacks, int distance, int range, FunctionEquipment function) {
		for(int i=0; i < quantity; ++i)
			deck.add(new Equipment(name, damage, attacks, distance, range, function));
		
		System.out.format(">>%d cards of '%s' were add to the game\n", quantity, name);
	}

	/**
	 * A function that helps to create X equals weapons and put on the array allCards.
	 * @param quantity			How many of this card
	 * @param name				Name of the card
	 * @param description		Description of the card
	 * @param damage			Damage this weapon causes if not blocked.
	 * @param range				Enemy must be this close to you for you hit.
	 * @param function			What the card is suppose to do when used
	 */
	private void weapon(int quantity, String name, int damage, int range, FunctionWeapon function) {
		for(int i=0; i < quantity; ++i)
			deck.add(new Weapon(name, damage, range, function));
		
		System.out.format(">>%d cards of '%s' were add to the game\n", quantity, name);
	}

	/**
	 * A function that helps to create X equals events and put on the array allCards.
	 * @param quantity			How many of this card
	 * @param name				Name of the card
	 * @param description		Description of the card
	 * @param function			What the card is suppose to do when used
	 */
	private void event(int quantity, String name, FunctionSpecial function) {
		for(int i=0; i < quantity; ++i)
			deck.add(new Special(name, function));
		System.out.format(">>%d cards of '%s' were add to the game\n", quantity, name);
	}

}
