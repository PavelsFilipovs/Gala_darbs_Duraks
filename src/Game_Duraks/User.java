package Game_Duraks;

import java.util.ArrayList;
import java.util.Random;

public class User implements Player {
	private boolean secondCircleOfCardGive = false; /// paskat�ties bot k� biju dar�jis! m��in�t piel�got
	private String name;
	Random random = new Random();
	private ArrayList<Card> arrUserCards = new ArrayList<Card>();
	private Card defendWithThisCard;
	private Card attackWithThisCard;
	
	public void takeCard(Card card) {
		arrUserCards.add(card);
	}
	
	public void takeCard(ArrayList<Card> arrCards) {
		arrUserCards.addAll(arrCards);
	}
	
	public Card giveCard() {
		
		return new Card();	/////// vai tie��m user input nevajag mainigo gameStructure(). NEVAJAG padom� vai paskaties botu
	}

	public Card giveCard_ToDefenceFrom(Card attackCard) {
		
		return defendWithThisCard;	////////////////////////// user veidu izveidot nevis bota
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

	public boolean checkCanDefendWithCardFrom(Card cardToAttack) {
		
		return true;///////////////////////////////////////////     user veidu izveidot nevis bota
	}
	
	public boolean checkCanGiveCardToAttack(ArrayList<Card> arrSlaughteredCards) {
		
		
		return false; 		////////////////////////////////////////// user veidu izveidot nevis bota
	}

	
	public boolean endTheTurn() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
