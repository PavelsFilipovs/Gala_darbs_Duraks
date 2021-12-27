package Game_Duraks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class User implements Player {
	Scanner scanner = new Scanner(System.in);
	ConsoleText consoleText = new ConsoleText();
	
	private boolean secondCircleOfCardGive = false; /// paskatîties bot kâ biju darîjis! mçìinât pielâgot
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
		if (secondCircleOfCardGive == false) {
			userInterface();
			consoleText.userInsertOneOfNumbers();
			int cardNumber = scanner.nextInt() - 1;
			attackWithThisCard = arrUserCards.get(cardNumber);
			arrUserCards.remove(attackWithThisCard);
		} else {
			arrUserCards.remove(attackWithThisCard);
			secondCircleOfCardGive = false;
		}
		return attackWithThisCard;	/////// vai tieðâm user input nevajag mainigo gameStructure(). NEVAJAG padomâ vai paskaties botu
	}

	public Card giveCard_ToDefenceFrom(Card attackCard) {
		arrUserCards.remove(defendWithThisCard);
		return defendWithThisCard;	////////////////////////// user veidu izveidot nevis bota
	}
	
	public int info_LowestTrump(int trumpIndexSome) {
		int trumpIndex = trumpIndexSome + 10;
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
		int userPush;
		userInterface();
		System.out.print("Pie Jums, " + name + ", nâk ar " + cardToAttack.getName() + " ar ko kausiet vai òemsiet mâjâs?");
		boolean userThinkFight = false;
		
		boolean answer = false;
		ArrayList<Card> arrLocalCardDefend = new ArrayList<Card>();
		ArrayList<Card> arrTrumpLocalCard = new ArrayList<Card>();
		
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
			for (int i = 0; i < arrUserCards.size(); i++) {
				if (trumpIndex == arrUserCards.get(i).getSuitIndex()) {
					arrTrumpLocalCard.add(arrUserCards.get(i));
				}
			}
			if (arrTrumpLocalCard.isEmpty()) {
				answer = false;
			} else {
				answer = true;
			}
			
		} else {
			answer = true;
		}
		
		ArrayList<Card> arrTempCheckUserAnswer = new ArrayList<Card>();
		arrTempCheckUserAnswer.addAll(arrLocalCardDefend);
		arrTempCheckUserAnswer.addAll(arrTrumpLocalCard);
		boolean userGive_NotCorrectAnswer = true;
		
		while (userGive_NotCorrectAnswer) {
			userPush = scanner.nextInt() - 1;
			if (userPush <= arrUserCards.size()) {
				userThinkFight = true;
				if (userThinkFight == answer) {
					for (int i = 0; i < arrTempCheckUserAnswer.size(); i++) {
						if (arrUserCards.get(userPush) == arrTempCheckUserAnswer.get(i)) {
							answer = true;
							defendWithThisCard = arrUserCards.get(userPush);
							consoleText.userDefend(name, cardToAttack.getName(), defendWithThisCard.getName());
							break;
						}
					}
					break;
				} else {
					answer = false;
					break;
				}
				
			} 
			System.out.println("Jums, " + name + ", nav iepçjams nokaut " + cardToAttack.getName() + " ar " 
					+ arrUserCards.get(userPush).getName() + ",izvçlçties citu kârti vai òemiet mâjâs!");
			userPush = scanner.nextInt();
		}
		return answer; // answer parveidot OBLIGÂTI uz true		///////////////////////////////////////////     user veidu izveidot nevis bota
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
		
		
		Set<String> setOfTypeWhatCanGive = new HashSet<String>();
		if (!arrCardsWhatCanGive.isEmpty()) {
			secondCircleOfCardGive = true;
			for (int i = 0; i < arrCardsWhatCanGive.size(); i++) {
				setOfTypeWhatCanGive.add(arrCardsWhatCanGive.get(i).getType());
			}
			consoleText.typeWhatCanGive(setOfTypeWhatCanGive);
			userInterface();
			consoleText.userInsertOneOfNumbers();
			int userPushNumber = scanner.nextInt() - 1;
			System.out.println();
			boolean getIncorrectAnswer = true;
			while (getIncorrectAnswer) {
				if (userPushNumber <= arrUserCards.size()) {
					for (int i = 0; i < arrUserCards.size(); i++) {
						if (arrUserCards.get(userPushNumber) == arrCardsWhatCanGive.get(i)) {
							attackWithThisCard = arrUserCards.get(userPushNumber);
							secondCircleOfCardGive = true;
							getIncorrectAnswer = false;
						}
					}
					if (secondCircleOfCardGive == false) {
						String useCardSuit = arrUserCards.get(userPushNumber).getSuit();
						String useCardType = arrUserCards.get(userPushNumber).getType();
						System.out.println("Lûdzu izvçlieties citu kârti, jo ar " + useCardSuit + "_" + useCardType + "nevar iet! ");
						userPushNumber = scanner.nextInt();
						System.out.println();
					}
					
					 
				} else {
					secondCircleOfCardGive = false;
					getIncorrectAnswer = false;
				}
			}
			
		} else {
			System.out.println("Jums nav neviena kârts ko varat pielikt, tâpçc dodu gajienu nâkamajam");
			secondCircleOfCardGive = false;
		}
		return secondCircleOfCardGive; 	
	}

	
	public boolean endTheTurn() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void userInterface() {
		int numberOfIfStatment = 0;
		// System.out.println("Trumpis ir: " + cardsDeck.sendTrumpName(trump));
		consoleText.tellTrump(trumpIndex);
		System.out.println(getName() + " Jûsu kârtis:");
		for (int i = 0; i < howMany_CardsHave(); i++) {
			Card card = cardInfo(i);
			System.out.println("  " + (i + 1) + ") " + card.getSuit() + "_" + card.getType());
			numberOfIfStatment = i;
		}
		System.out.println("  " + (numberOfIfStatment + 2) + ") " + "Atkarîgi no situâcijas - òemt mâjâs vai pabeigt gâjienu");
		System.out.println();
		/*
		 * if (attackOrDefend == true) { System.out.println("  " + (numberOfIfStatment +
		 * 1) + ") " + "Pateikt, ka ir kauts! (nav ko pielikt)"); } else if
		 * (attackOrDefend == false) { System.out.println("  " + (numberOfIfStatment +
		 * 1) + ") " + "Pateikt, ka jûs òemat mâjâs kârtis!"); }
		 */
		//////////////////////////////////////////////////////
	}

	public void setTrumpIndex(int trumpIndex) {
		this.trumpIndex = trumpIndex;
	}
	
	

}
