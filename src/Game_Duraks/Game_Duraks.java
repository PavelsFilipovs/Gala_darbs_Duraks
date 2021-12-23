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
	
	public void rules() {
		System.out.println("'Duraks' ir k�r�u sp�le kuru var sp�l�t no 2 sp�l�t�ji (1 cilv�ks un 1 bots).");
		System.out.println( "Noteikumi:\n\t* Katram sp�l�t�jam tiek izdal�tas 6 k�rtis un defin�ti trumpji,\n" +
							"\t* Pirmais izdara g�jienu sp�l�tais, kuram ir maz�kais trumpis. \n" +
							"\t* Ja nevienam nav trumpis tad sp�li uzs�k sp�t�js ar maz�ko k�rti,\n" +
							"\t* K�v�js (sp�l�t�js, pie kura tiek veikts g�jiens) var k�rtis kaut ar konkr�t� masta sp�c�g�ku k�rti vai ar� k�rti no trumpjiem. \n" + 
							"\t* Ja k�v�js nevar kaut k�rti(s), vi�� uz�em visas g�jiena laik� izlikt�s k�rtis.\n" + 
							"\t* Zaud� tas sp�l�t�js, kuram rok�s paliek p�d�j�s k�rtis vai p�d�jais veic atkau�anos.");
		System.out.println("Veiksmi sp�l�! Uzpiediet kaut ko lai turpin�tu!");
	}
	
	public void giveToPlayersNames() {
		scanner.nextLine();
		for (int i = 0; i < arrUsers.length; i++) {
			if (i == 0) {
				User user = new User();
				arrUsers[i] = user;
				System.out.print("K�ds b�s J�su v�rds: ");
				arrUsers[i].setName(scanner.nextLine());
				//System.out.println(usersArr[i].getName());
			} else {
				Bot bot = new Bot();
				arrUsers[i] = bot;
				System.out.print("K�ds b�s Bot_Nr" + i + " v�rds: ");
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
		trump = cardsDeck.makeTrump();
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
					break;						//////////// vien�gi vadz�tu izvad�t uzvar�t�ju ar�!!!!
				}
				if (arrUsers[firstUserIndex].checkCanGiveCardToAttack(arrSlaughteredCards)) {
					attackCard = arrUsers[firstUserIndex].giveCard(); /////// vai tie��m user input nevajag mainigo
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
						break;						//////////// vien�gi vadz�tu izvad�t uzvar�t�ju ar�!!!!
					}
					dealMisingCardsToPlayers();
					attackCard = arrUsers[firstUserIndex].giveCard();
				}
			} else {
				arrUsers[secondUserIndex].takeCard(attackCard);
				arrUsers[secondUserIndex].takeCard(arrSlaughteredCards);
				if (cardsDeck.howManyCardsLeft() <= 0 && somePlayerDontHaveCards()) {
					break;						//////////// vien�gi vadz�tu izvad�t uzvar�t�ju ar�!!!!
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
						///// varb�t atg�din�t, ka kartis beidz�s!?!?!?!?!?
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
		System.out.println("Trumpis ir: " + cardsDeck.sendTrumpName(trump));
		System.out.println(arrUsers[0].getName() + " J�su k�rtis:");
		//     usersArr[0].sortCards();
		for (int i = 0; i < arrUsers[0].howMany_CardsHave(); i++) {
			Card card = arrUsers[0].cardInfo(i);
			System.out.println((i + 1) + ") " + card.getSuit() + "_" + card.getType());
		}
		System.out.print("Ievadiet vienu no cipariem lai turpin�tu: ");
		scanner.nextLine();//////////////////////////////////////////////////////
	}
	
	
	
}
