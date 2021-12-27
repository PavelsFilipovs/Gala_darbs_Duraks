package Game_Duraks;

import java.util.Set;

public class ConsoleText {
	CardsDeck cardsDeck = new CardsDeck();
	private String trumpName;
	public void rules() {
		System.out.println("'Duraks' ir kârðu spçle kuru var spçlçt no 2 spçlçtâji (1 cilvçks un 1 bots).");
		System.out.println( "Noteikumi:\n\t* Katram spçlçtâjam tiek izdalîtas 6 kârtis un definçti trumpji,\n" +
							"\t* Pirmais izdara gâjienu spçlçtais, kuram ir mazâkais trumpis. \n" +
							"\t* Ja nevienam nav trumpis tad spçli uzsâk spçtâjs ar mazâko kârti,\n" +
							"\t* Kâvçjs (spçlçtâjs, pie kura tiek veikts gâjiens) var kârtis kaut ar konkrçtâ masta spçcîgâku kârti vai arî kârti no trumpjiem. \n" + 
							"\t* Ja kâvçjs nevar kaut kârti(s), viòð uzòem visas gâjiena laikâ izliktâs kârtis.\n" + 
							"\t* Zaudç tas spçlçtâjs, kuram rokâs paliek pçdçjâs kârtis vai pçdçjais veic atkauðanos.");
		System.out.println("Veiksmi spçlç! Uzpiediet 'Enter' lai turpinâtu!");
		
	}
	
	public void userName() {
		System.out.print("Kâds bûs Jûsu vârds: ");
	}
	
	public void botName(int number) {
		System.out.print("Kâds bûs Bot_Nr" + number + " vârds: ");
	}
	
	public void tellTrump(int trumpIndex) {
		trumpName = cardsDeck.sendTrumpName(trumpIndex);
		System.out.println("Trumpis ir: " + trumpName);
	}
	
	public void userGo() {
		
	}
	
	public void BotAttack() {
		
	}
	
	public void BotDefend(String botName, String attackCardName, String defendCardName) {
		System.out.println("Sçlçtâjs " + botName + " atsitaties no " + attackCardName  + " ar " + defendCardName);
	}
	
	public void typeWhatCanGive(Set<String> setOfTypeWhatCanGive) {
		System.out.print("Jûs varat iet ar tada veida kârtîm - ");
		for (String oneOfType : setOfTypeWhatCanGive) {
		    System.out.print(oneOfType + ", ");
		}
		System.out.println();
	}
	
	public void userDefend(String userName, String attackCardName, String defendCardName) {
		System.out.println("Jûs " + userName + " atsitaties no " + attackCardName  + " ar " + defendCardName);
	}
	
	public void userTakeHome() {
		System.out.println("Jûs paòçmât majâs, uzcçlât visas kârtis ðinî gâjienâ izliktâs uz galda!");
	}
	
	public void botTakeHome() {
		System.out.println("Pretinieks paòçma mâjâs");
	}
	
	public void userInsertOneOfNumbers() {
		System.out.print("Ievadiet vienu no cipariem lai turpinâtu: ");
	}
	
	public void takeHome() {
		
	}
}
