package Game_Duraks;

public class ConsoleText {
	private String trump;
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
	
	public void userName() {
		System.out.print("Kâds bûs Jûsu vârds: ");
	}
	
	public void botName(int number) {
		System.out.print("Kâds bûs Bot_Nr" + number + " vârds: ");
	}
	
	public void tellTrump(String trump) {
		this.trump = trump;
		System.out.println("Trumpis ir: " + this.trump);
	}
	
	public void userGo() {
		
	}
	
	public void BotAttack() {
		
	}
	
	public void BotDefend() {
		
	}
	
	public void userAttack() {
		
	}
	
	public void userDefend() {
		
	}
}
