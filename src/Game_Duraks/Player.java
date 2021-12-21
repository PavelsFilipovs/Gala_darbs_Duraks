package Game_Duraks;

import java.util.ArrayList;

public interface Player {

	public void takeCard(Card card);
	
	public void takeCard(ArrayList<Card> arrCards);
	
	public Card giveCard(Card card);
	
	public Card giveCard();
	
	public int info_CardsOnHand();
	
	public int info_LowestTrump(int trumpIndex);
	
	public int info_LowestCard();
	
	public String getName();
	
	public void setName(String name);
	
	public int howMany_CardsHave();
	
	public Card cardInfo(int arrIndex);
	
	public boolean cardToAttack(Card cardToAttack);
	
	public boolean endTheTurn();
}
