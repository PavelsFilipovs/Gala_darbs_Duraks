package Game_Duraks;

import java.util.ArrayList;

public class CardsDeck {
	private ArrayList<Card> arrCardsDeck = new ArrayList<Card>();
	private String[] cardSuit = {"Ercena", "Kârava", "Pîía", "Kreièa"};
	private String[] cardType = {"Dûzis", "Kungs", "Dâma", "Kalps", "10", "9", "8", "7", "6"};
	
	public CardsDeck() {
		makeDeck();
	}
	
	public void makeDeck() {
		for (int i = 0; i < cardSuit.length; i++) {
			for (int j = 0; j < cardType.length; j++) {
				Card card = new Card();
				card.setSuit(cardSuit[i]);
				card.setSuitIndex(i + 10);
				card.setType(cardType[j]);
				card.setTypeIndex(j + 10);
			}
		}
	}
	
	
	public Card giveCard() {
		int randomCard = random_int(arrCardsDeck.size());
		Card card = arrCardsDeck.get(randomCard);
		arrCardsDeck.remove(randomCard);
		return card;
	}
	
	private int random_int(int Max){
	     return (int)(Math.random()*(Max- 0)) + 0;
	}
	
	public int giveTrump() {
		return random_int(cardSuit.length);
	}
}
