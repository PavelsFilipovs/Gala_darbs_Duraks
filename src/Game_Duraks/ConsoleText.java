package Game_Duraks;

import java.util.Set;

public class ConsoleText {
	CardsDeck cardsDeck = new CardsDeck();
	private String trumpName;
	public void rules() {
		System.out.println("'Duraks' ir k�r�u sp�le kuru var sp�l�t no 2 sp�l�t�ji (1 cilv�ks un 1 bots).");
		System.out.println( "Noteikumi:\n\t* Katram sp�l�t�jam tiek izdal�tas 6 k�rtis un defin�ti trumpji,\n" +
							"\t* Pirmais izdara g�jienu sp�l�tais, kuram ir maz�kais trumpis. \n" +
							"\t* Ja nevienam nav trumpis tad sp�li uzs�k sp�t�js ar maz�ko k�rti,\n" +
							"\t* K�v�js (sp�l�t�js, pie kura tiek veikts g�jiens) var k�rtis kaut ar konkr�t� masta sp�c�g�ku k�rti vai ar� k�rti no trumpjiem. \n" + 
							"\t* Ja k�v�js nevar kaut k�rti(s), vi�� uz�em visas g�jiena laik� izlikt�s k�rtis.\n" + 
							"\t* Zaud� tas sp�l�t�js, kuram rok�s paliek p�d�j�s k�rtis vai p�d�jais veic atkau�anos.");
		System.out.println("Veiksmi sp�l�! Uzpiediet 'Enter' lai turpin�tu!");
		
	}
	
	public void userName() {
		System.out.print("K�ds b�s J�su v�rds: ");
	}
	
	public void botName(int number) {
		System.out.print("K�ds b�s Bot_Nr" + number + " v�rds: ");
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
		System.out.println("S�l�t�js " + botName + " atsitaties no " + attackCardName  + " ar " + defendCardName);
	}
	
	public void typeWhatCanGive(Set<String> setOfTypeWhatCanGive) {
		System.out.print("J�s varat iet ar tada veida k�rt�m - ");
		for (String oneOfType : setOfTypeWhatCanGive) {
		    System.out.print(oneOfType + ", ");
		}
		System.out.println();
	}
	
	public void userDefend(String userName, String attackCardName, String defendCardName) {
		System.out.println("J�s " + userName + " atsitaties no " + attackCardName  + " ar " + defendCardName);
	}
	
	public void userTakeHome() {
		System.out.println("J�s pa��m�t maj�s, uzc�l�t visas k�rtis �in� g�jien� izlikt�s uz galda!");
	}
	
	public void botTakeHome() {
		System.out.println("Pretinieks pa��ma m�j�s");
	}
	
	public void userInsertOneOfNumbers() {
		System.out.print("Ievadiet vienu no cipariem lai turpin�tu: ");
	}
	
	public void takeHome() {
		
	}
}
