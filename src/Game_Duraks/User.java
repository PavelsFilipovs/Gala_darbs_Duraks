package Game_Duraks;

import java.util.ArrayList;

public class User {
	private String name;
	
	private ArrayList<Card> arrUserCards = new ArrayList<Card>();
	
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
		
		return 0; 	/////////////////////////////////
	}
	
	public int info_LowestTrump(int trumpIndex) {
		
		return 0; 	////////////////////////////////
	}
	
	public int info_lowestCard() {
		
		return 0; 	///////////////////////////////
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

}
