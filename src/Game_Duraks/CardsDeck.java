package Game_Duraks;

import java.util.ArrayList;
import java.util.Random;

public class CardsDeck {
	private ArrayList<Card> arrCardsDeck = new ArrayList<Card>();
	private String[] cardSuit = {"Ercena", "K?rava", "P??a", "Krei?a"};
	private String[] cardType = {"6", "7", "8", "9", "10", "Kalps", "D?ma", "Kungs", "D?zis"};
	public int trumpIndex;
	
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
				card.setName(card.getSuit() + "_" + card.getType());
				arrCardsDeck.add(card);
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
	
	public int makeTrump() { 
		
		Random random = new Random(); trumpIndex = random.nextInt(cardSuit.length);
		
		return trumpIndex;
	}
	
	public String sendTrumpName(int trump) {
		return cardSuit[trump];
	}
	
	public int howManyCardsLeft() {
		return arrCardsDeck.size();
	}
}
