package Game_Duraks;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class Game_Duraks {
	private int numberOfPlayers = 2, cardsOnHands = 6, playerIndex_GoFirst;
	private int trump;
	private Card attackCard, defendCard;
	
	private  Player[] arrUsers = new Player[numberOfPlayers];
	private ArrayList<Card> arrSlaughteredCards = new ArrayList<Card>();
	
	Scanner scanner = new Scanner(System.in);
	CardsDeck cardsDeck = new CardsDeck();
	ConsoleText consoleText = new ConsoleText();
	
	public void giveToPlayersNames() {
		consoleText.rules();
		scanner.nextLine();
		for (int i = 0; i < arrUsers.length; i++) {
			if (i == 0) {
				User user = new User();
				arrUsers[i] = user;
				// System.out.print("Kâds bûs Jûsu vârds: ");
				consoleText.userName();
				arrUsers[i].setName(scanner.nextLine());
				//System.out.println(usersArr[i].getName());
			} else {
				Bot bot = new Bot();
				arrUsers[i] = bot;
				// System.out.print("Kâds bûs Bot_Nr" + i + " vârds: ");
				consoleText.botName(i);
				arrUsers[i].setName(scanner.nextLine());
				//System.out.println(usersArr[i].getName());
			}
		}
		
	}
	
	public void dealCardsToPlayers() {
		for (int i = 0; i < cardsOnHands; i++) {
			for (int j = 0; j < arrUsers.length; j++) {
				Card card = cardsDeck.giveCard();
				arrUsers[j].takeCard(card);
			}
		}
		System.out.println();
		trump = cardsDeck.makeTrump();
		String trumpName = cardsDeck.sendTrumpName(trump);
		consoleText.tellTrump(trumpName);
	}
	
	public int whoGoesFirst() {
		boolean flag = false;
		int[] lowestUserTrump = new int[numberOfPlayers];
		int[] lowestUserCard = new int[numberOfPlayers];
		
		for (int i = 0; i < arrUsers.length; i++) {
			lowestUserTrump[i] = arrUsers[i].info_LowestTrump(trump);
			lowestUserCard[i] = arrUsers[i].info_LowestCard();
		}
		
		if (lowestUserTrump[0] == 0) {			// ja nav trumpis pirmajam
			playerIndex_GoFirst = 1;
		} else if (lowestUserTrump[1] == 0) { 	// ja nav trumpis otrajam
			playerIndex_GoFirst = 0;
		} else {		
			if (lowestUserTrump[0] < lowestUserTrump[1]) {
				playerIndex_GoFirst = 0;
				flag = true;
			} else  {
				playerIndex_GoFirst = 1;
				flag = true;
			} 
			if (flag == false) {
				if (lowestUserCard[0] == lowestUserCard[1]) {
					Random random = new Random();
					playerIndex_GoFirst = random.nextInt(numberOfPlayers);
				} else if (lowestUserCard[0] < lowestUserCard[1]) {
					playerIndex_GoFirst = 0;
				} else {
					playerIndex_GoFirst = 1;
				}				
			}
		}
		return playerIndex_GoFirst;
	}
	
	public void gameStructure() {
		boolean haveCardsInDeckAndBothPlayers = true;
		int firstUserIndex = playerIndex_GoFirst;
		int secondUserIndex;
		Card attackCard, defendCard;
		
		if (firstUserIndex == 0) {
			secondUserIndex = 1;
		} else {
			secondUserIndex = 0;
		}
		
		attackCard = arrUsers[firstUserIndex].giveCard();
		
		while (haveCardsInDeckAndBothPlayers) {
			if (arrUsers[secondUserIndex].checkCanDefendWithCardFrom(attackCard)) {
				defendCard = arrUsers[secondUserIndex].giveCard_ToDefenceFrom(attackCard);
				arrSlaughteredCards.add(attackCard);
				arrSlaughteredCards.add(defendCard);
				if (cardsDeck.howManyCardsLeft() <= 0 && somePlayerDontHaveCards()) {
					break;						//////////// vienîgi vadzçtu izvadît uzvarçtâju arî!!!!
				}
				if (arrUsers[firstUserIndex].checkCanGiveCardToAttack(arrSlaughteredCards)) {
					attackCard = arrUsers[firstUserIndex].giveCard(); /////// vai tieðâm user input nevajag mainigo
				} else {
					arrSlaughteredCards.clear();
					if (firstUserIndex == 0) {
						secondUserIndex = 0;
						firstUserIndex = 1;
					} else {
						secondUserIndex = 1;
						firstUserIndex = 0;
					}
					if (cardsDeck.howManyCardsLeft() <= 0 && somePlayerDontHaveCards()) {
						break;						//////////// vienîgi vadzçtu izvadît uzvarçtâju arî!!!!
					}
					dealMisingCardsToPlayers();
					attackCard = arrUsers[firstUserIndex].giveCard();
				}
			} else {
				arrUsers[secondUserIndex].takeCard(attackCard);
				arrUsers[secondUserIndex].takeCard(arrSlaughteredCards);
				if (cardsDeck.howManyCardsLeft() <= 0 && somePlayerDontHaveCards()) {
					break;						//////////// vienîgi vadzçtu izvadît uzvarçtâju arî!!!!
				}
				dealMisingCardsToPlayers();
				attackCard = arrUsers[firstUserIndex].giveCard();
			}
		}
		
	}
	
	private void dealMisingCardsToPlayers() {
		for (int i = 0; i < arrUsers.length; i++) {
			if (arrUsers[i].howMany_CardsHave() < cardsOnHands) {
				for (int j = 0; j < (cardsOnHands - arrUsers[i].howMany_CardsHave()); j++) {
					if (cardsDeck.howManyCardsLeft() > 0) {				
						arrUsers[i].takeCard(cardsDeck.giveCard());		
						///// varbût atgâdinât, ka kartis beidzâs!?!?!?!?!?
					}
				}
			}
		}
	}
	
	private boolean somePlayerDontHaveCards() {
		boolean aswer = false;
		if (arrUsers[0].howMany_CardsHave() <= 0 || arrUsers[0].howMany_CardsHave() <= 0) {
			aswer = true;
		}
		return aswer;
	}
	
	public void userInterface() {
		int numberOfIfStatment = 0;
		// System.out.println("Trumpis ir: " + cardsDeck.sendTrumpName(trump));
		System.out.println(arrUsers[0].getName() + " Jûsu kârtis:");
		for (int i = 0; i < arrUsers[0].howMany_CardsHave(); i++) {
			Card card = arrUsers[0].cardInfo(i);
			System.out.println("  " + (i + 1) + ") " + card.getSuit() + "_" + card.getType());
			numberOfIfStatment = i;
		}
		System.out.println("  " + (numberOfIfStatment + 2) + ") " + "Atkarîgi no situâcijas - òemt mâjâs vai pabeigt gâjienu");
		/*
		 * if (attackOrDefend == true) { System.out.println("  " + (numberOfIfStatment +
		 * 1) + ") " + "Pateikt, ka ir kauts! (nav ko pielikt)"); } else if
		 * (attackOrDefend == false) { System.out.println("  " + (numberOfIfStatment +
		 * 1) + ") " + "Pateikt, ka jûs òemat mâjâs kârtis!"); }
		 */
		//////////////////////////////////////////////////////
	}
	
	
	
}
