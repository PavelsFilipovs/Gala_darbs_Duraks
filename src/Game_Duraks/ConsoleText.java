package Game_Duraks;

public class ConsoleText {
	private String trump;
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
	
	public void userName() {
		System.out.print("K�ds b�s J�su v�rds: ");
	}
	
	public void botName(int number) {
		System.out.print("K�ds b�s Bot_Nr" + number + " v�rds: ");
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
