package Game_Duraks;

import java.util.ArrayList;
import java.util.Random;

public class Bot implements Player {
	private boolean secondCircleOfCardGive = false;
	private String name;
	Random random = new Random();
	private ArrayList<Card> arrUserCards = new ArrayList<Card>();
	private Card defendWithThisCard;
	private Card attackWithThisCard;
	private int trumpIndex;
	
	public void takeCard(Card card) {
		arrUserCards.add(card);
	}
	
	public void takeCard(ArrayList<Card> arrCards) {
		arrUserCards.addAll(arrCards);
	}
	
	public Card giveCard() {
		int indexOfLowestCard;
		if (secondCircleOfCardGive == false) {
			
			indexOfLowestCard = info_LowestCard();
			for (int i = 0; i < arrUserCards.size(); i++) {
				if (arrUserCards.get(i).getTypeIndex() == indexOfLowestCard) {
					attackWithThisCard = arrUserCards.get(i);
					arrUserCards.remove(i);
					break;
				}
			}
		} else {
			for (int i = 0; i < arrUserCards.size(); i++) {
				if (arrUserCards.get(i) == attackWithThisCard) {
					arrUserCards.remove(i);
					break;
				}
			}
			
			secondCircleOfCardGive = false;
		}
		return attackWithThisCard;
		
	}

	public Card giveCard_ToDefenceFrom(Card attackCard) {
		
		return defendWithThisCard;	//////////////////////////
	}
	
	public int info_LowestTrump(int trumpIndex) {
		this.trumpIndex = trumpIndex;
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
		boolean answer = false;
		ArrayList<Card> arrLocalCardDefend = new ArrayList<Card>();
		for (int i = 0; i < arrUserCards.size(); i++) {
			int atackCardSuitIndex = cardToAttack.getSuitIndex();
			int atackCardTypeIndex = cardToAttack.getTypeIndex();
			int defendCardSuitIndex = arrUserCards.get(i).getSuitIndex();
			int defendCardTypeIndex = arrUserCards.get(i).getTypeIndex();
			if (atackCardSuitIndex == defendCardSuitIndex) {
				if (atackCardTypeIndex < defendCardTypeIndex) {
					arrLocalCardDefend.add(arrUserCards.get(i));
				}
			} 
		}
		
		if (arrLocalCardDefend.isEmpty()) {
			ArrayList<Card> arrTrumpLocalCard = new ArrayList<Card>();
			if (cardToAttack.getSuitIndex() == trumpIndex) {
				answer = false;
			} else {
				for (int i = 0; i < arrUserCards.size(); i++) {
					if (trumpIndex == arrUserCards.get(i).getSuitIndex()) {
						arrTrumpLocalCard.add(arrUserCards.get(i));
					}
				}
				if (arrTrumpLocalCard.isEmpty()) {
					answer = false;
				} else {
					Card lowestLocalTrumpCard = arrTrumpLocalCard.get(0);
					for (int i = 0; i < arrTrumpLocalCard.size(); i++) {
						if (lowestLocalTrumpCard.getTypeIndex() > arrTrumpLocalCard.get(i).getTypeIndex()) {
							lowestLocalTrumpCard = arrTrumpLocalCard.get(i);
						}
					}
					answer = true;
					defendWithThisCard = lowestLocalTrumpCard;
				}
			}
		} else {
			// uzrakstît atack card ir zema, bet ne maksimali un 
			// tev ir 1 vai vairâk augstâkas kârtis ar to paðu masti kas uzbrucçjam
			// 
		}
		
		
		return answer;///////////////////////////////////////////
	}
	
	public boolean checkCanGiveCardToAttack(ArrayList<Card> arrSlaughteredCards) {
		ArrayList<Card> arrCopyOfUserCards = new ArrayList<Card>();
		ArrayList<Card> arrCardsWhatCanGive = new ArrayList<Card>();
		for (int i = 0; i < arrSlaughteredCards.size(); i++) {
			for (int j = 0; j < arrCopyOfUserCards.size(); j++) {
				int someUserCardTypeIndex = arrCopyOfUserCards.get(j).getTypeIndex();
				int someIncomeCardTypeIndex = arrSlaughteredCards.get(i).getTypeIndex();
				if (someIncomeCardTypeIndex == someUserCardTypeIndex) {
					arrCardsWhatCanGive.add(arrSlaughteredCards.get(i));
					arrCopyOfUserCards.remove(j);
				}
			}
			
		}
		
		Card lowestCard = arrCardsWhatCanGive.get(0);
		for (int i = 0; i < arrCardsWhatCanGive.size(); i++) {
			for (int j = 0; j < arrCardsWhatCanGive.size(); j++) {
				if (lowestCard.getTypeIndex() > arrCardsWhatCanGive.get(i).getTypeIndex()) {
					lowestCard = arrCardsWhatCanGive.get(i);
				}
				
			}
		}
		attackWithThisCard = lowestCard;
		if (!arrCardsWhatCanGive.isEmpty()) {
			secondCircleOfCardGive = true; 
		}
		return secondCircleOfCardGive; //////////////////////////////////////////
	}

	
	public boolean endTheTurn() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
