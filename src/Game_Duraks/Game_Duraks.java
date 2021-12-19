package Game_Duraks;

import java.util.Random;
import java.util.Scanner;


public class Game_Duraks {
	private int numberOfPlayers = 2, cardsOnHands = 6;
	private int trump;
	private  User[] usersArr = new User[numberOfPlayers];
	
	Scanner scanner = new Scanner(System.in);
	CardsDeck cardsDeck = new CardsDeck();
	
	public void rules() {
		System.out.println("'Duraks' ir kârðu spçle kuru var spçlçt no 2 spçlçtâji (1 cilvçks un 1 bots).");
		System.out.println( "Noteikumi:\n\t* Katram spçlçtâjam tiek izdalîtas 6 kârtis un definçti trumpji,\n" +
							"\t* Pirmais izdara gâjienu spçlçtais, kuram ir mazâkais trumpis. \n" +
							"\t* Ja nevienam nav trumpis tad spçli uzsâk spçtâjs ar mazâko kârti,\n" +
							"\t* Kâvçjs (spçlçtâjs, pie kura tiek veikts gâjiens) var kârtis kaut ar konkrçtâ masta spçcîgâku kârti vai arî kârti no trumpjiem. \n" + 
							"\t* Ja kâvçjs nevar kaut kârti(s), viòð uzòem visas gâjiena laikâ izliktâs kârtis.\n" + 
							"\t* Zaudç tas spçlçtâjs, kuram rokâs paliek pçdçjâs kârtis vai pçdçjais veic atkauðanos.");
		System.out.println("Veiksmi spçlç! Uzpiediet kaut ko lai turpinâtu!");
	}
	
	public void giveToPlayersNames() {
		scanner.nextLine();
		for (int i = 0; i < usersArr.length; i++) {
			if (i == 0) {
				User user = new User();
				usersArr[i] = user;
				System.out.print("Kâds bûs Jûsu vârds: ");
				usersArr[i].setName(scanner.nextLine());
				//System.out.println(usersArr[i].getName());
			} else {
				Bot bot = new Bot();
				usersArr[i] = bot;
				System.out.print("Kâds bûs Bot_Nr" + i + " vârds: ");
				usersArr[i].setName(scanner.nextLine());
				//System.out.println(usersArr[i].getName());
			}
		}
	}
	
	public void dealCardsToPlayers() {
		for (int i = 0; i < cardsOnHands; i++) {
			for (int j = 0; j < usersArr.length; j++) {
				Card card = cardsDeck.giveCard();
				usersArr[j].takeCard(card);
			}
		}
		trump = cardsDeck.makeTrump();
	}
	
	public int whoGoesFirst() {
		int[] lowestUserTrump = new int[numberOfPlayers];
		int[] lowestUserCard = new int[numberOfPlayers];
		int playerIndex_GoFirst;
		for (int i = 0; i < usersArr.length; i++) {
			lowestUserTrump[i] = usersArr[i].info_LowestTrump(trump);
			lowestUserCard[i] = usersArr[i].info_LowestCard();
		}
		
		if (lowestUserTrump[0] == 0) {			// ja nav trumpis pirmajam
			playerIndex_GoFirst = 1;
		} else if (lowestUserTrump[1] == 0) { 	// ja nav trumpis otrajam
			playerIndex_GoFirst = 0;
		} else {		
			 if (lowestUserTrump[0] < lowestUserTrump[1]) {
				playerIndex_GoFirst = 0;
			} else  {
				playerIndex_GoFirst = 1;
			} 
			 if (playerIndex_GoFirst == 0) {
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
	
	public void userInterface() {
		System.out.println("Trumpis ir: " + cardsDeck.sendTrumpName(trump));
		System.out.println(usersArr[0].getName() + " Jûsu kârtis:");
		//     usersArr[0].sortCards();
		for (int i = 0; i < usersArr[0].howMany_CardsHave(); i++) {
			Card card = usersArr[0].cardInfo(i);
			System.out.println((i + 1) + ") " + card.getSuit() + "_" + card.getType());
		}
		System.out.print("Uzpiediet vienu no cipariem lai turpinâtu: ");
		scanner.nextLine();//////////////////////////////////////////////////////
	}
	
	
	
}
