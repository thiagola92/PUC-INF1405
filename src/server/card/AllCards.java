package server.card;

import java.util.ArrayList;

public class AllCards {
	
	public AllCards(ArrayList<Card> deck) {
		System.out.println(">>Creating all cards in the game");
		
		new Equipment("Arma 1", "descricao arma 1", 1, 0, 0, 0, (card, player) -> {
			//player.equipCard();
		});
	}

}
