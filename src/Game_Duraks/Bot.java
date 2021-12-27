package Game_Duraks;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Bot implements Player {
	private boolean secondCircleOfCardGive = false;
	private String name;
	Random random = new Random();
	private ArrayList<Card> arrUserCards = new ArrayList<Card>();
	private Card defendWithThisCard;
	private Card attackWithThisCard;
	private int trumpIndex;
	Scanner scanner = new Scanner(System.in);
	ConsoleText consoleText = new ConsoleText();
	
	public void takeCard(Card card) {
		arrUserCards.add(card);
	}
	
	public void takeCard(ArrayList<Card> arrCards) {
		consoleText.botTakeHome();
		arrUserCards.addAll(arrCards);
	}
	
	public Card giveCard() {
		int indexOfLowestCard;
		if (secondCircleOfCardGive == false) {
			indexOfLowestCard = info_LowestCard();
			for (int i = 0; i < arrUserCards.size(); i++) {
				if (arrUserCards.get(i).getTypeIndex() == indexOfLowestCard) {
					attackWithThisCard = arrUserCards.get(i);
					arrUserCards.remove(attackWithThisCard);
					break;
				}
			}
		} else {
			arrUserCards.remove(attackWithThisCard);			
			secondCircleOfCardGive = false;
		}
		return attackWithThisCard;
		
	}

	public Card giveCard_ToDefenceFrom(Card attackCard) {
		arrUserCards.remove(defendWithThisCard);
		return defendWithThisCard;	
	}
	
	public int info_LowestTrump(int trumpIndexSome) {
		trumpIndex = trumpIndexSome + 10;             ///////////////// te skatît ja aiziet grizi trumpis
		int lowestTrumpTypeIndex = 0;
		
		for (int i = 0; i < arrUserCards.size(); i++) {
			Card card = arrUserCards.get(i);
			int suitIndex = card.getSuitIndex();
			int typeIndex = card.getTypeIndex();
			
			if (suitIndex == trumpIndex) {
				if (lowestTrumpTypeIndex == 0) {
					lowestTrumpTypeIndex = typeIndex;
				}
				else if (lowestTrumpTypeIndex > 0 && lowestTrumpTypeIndex > typeIndex) {
					lowestTrumpTypeIndex = typeIndex;
				} 
			}
		}
		
		return lowestTrumpTypeIndex;
	}
	
	public int info_LowestCard() {
		int lowestCardTypeIndex = 0;
		for (int i = 0; i < arrUserCards.size(); i++) {
			Card card = arrUserCards.get(i);
			int typeIndex = card.getTypeIndex();
			int suitIndex = card.getSuitIndex();
			if (suitIndex != trumpIndex) {
				if (lowestCardTypeIndex == 0) {
					lowestCardTypeIndex = typeIndex;
				} else if (lowestCardTypeIndex > 0 && lowestCardTypeIndex > typeIndex) {
					lowestCardTypeIndex = typeIndex;
				}
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
			Card lowestDefendingCard = arrLocalCardDefend.get(0);
			for (int i = 0; i < arrLocalCardDefend.size(); i++) {
				if (lowestDefendingCard.getTypeIndex() > arrLocalCardDefend.get(i).getTypeIndex()) {
					lowestDefendingCard = arrLocalCardDefend.get(i);
				}
			}
			defendWithThisCard = lowestDefendingCard;
			answer = true;
		}
		if (answer == true) {
			consoleText.BotDefend(name, cardToAttack.getName(), defendWithThisCard.getName());
		}
		return answer;
	}
	
	public boolean checkCanGiveCardToAttack(ArrayList<Card> arrSlaughteredCards) {
		ArrayList<Card> arrCopyOfUserCards = arrUserCards;
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
		if (arrCardsWhatCanGive.isEmpty()) {
			secondCircleOfCardGive = false;
		} else {
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
		}
		return secondCircleOfCardGive; 
	}

	
	public boolean endTheTurn() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setTrumpIndex(int trumpIndex) {
		this.trumpIndex = trumpIndex;
	}
	

}
