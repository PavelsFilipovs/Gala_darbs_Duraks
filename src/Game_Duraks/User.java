package Game_Duraks;

import java.util.ArrayList;
import java.util.Random;

public class User implements Player {
	private String name;
	Random random = new Random();
	private ArrayList<Card> arrUserCards = new ArrayList<Card>();
	private Card defendWithThisCard;
	
	public void takeCard(Card card) {
		arrUserCards.add(card);
	}
	
	public void takeCard(ArrayList<Card> arrCards) {
		arrUserCards.addAll(arrCards);
	}
	
	public Card giveCard(Card card) {
		
		return new Card();	//////////////////////////
	}
	
	public Card giveCard() {
		
		return new Card();	//////////////////////////
	}
	
	public int info_CardsOnHand() {
		
		return arrUserCards.size(); 	
	}
	
	public int info_LowestTrump(int trumpIndex) {
		int lowestTrumpTypeIndex = 0;
		
		for (int i = 0; i < arrUserCards.size(); i++) {
			Card card = arrUserCards.get(i);
			int suitIndex = card.getSuitIndex();
			int typeIndex = card.getTypeIndex();
			
			if (lowestTrumpTypeIndex == 0) {
				if (suitIndex == trumpIndex) {
					lowestTrumpTypeIndex = typeIndex;
				}
			}  
			else if (lowestTrumpTypeIndex > 0 && lowestTrumpTypeIndex > typeIndex) {
				lowestTrumpTypeIndex = typeIndex;
			}
		}
		
		return lowestTrumpTypeIndex;
	}
	
	public int info_LowestCard() {
		int lowestCardTypeIndex = 0;
		for (int i = 0; i < arrUserCards.size(); i++) {
			Card card = arrUserCards.get(i);
			int typeIndex = card.getTypeIndex();
			if (lowestCardTypeIndex == 0) {
				lowestCardTypeIndex = typeIndex;
			} else if (lowestCardTypeIndex > 0 && lowestCardTypeIndex > typeIndex) {
				lowestCardTypeIndex = typeIndex;
			}
		}
		return lowestCardTypeIndex; 	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int howMany_CardsHave() {
		return arrUserCards.size();
	}
	
	public Card cardInfo(int arrIndex) {
		return arrUserCards.get(arrIndex);
	}

	public boolean CardToAttack(Card cardToAttack) {
		
		return true;///////////////////////////////////////////
	}
	
	

}
