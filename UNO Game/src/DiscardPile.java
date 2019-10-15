import java.util.ArrayList;
import java.util.Collections;

// Holland Aucoin
// December 4, 2018
// Milestone 6 - Discard Pile Class
// This is my own work.

public class DiscardPile {

	// CREATE ARRAY LIST FOR DISCARD PILE
	private ArrayList<Card> discardPile = new ArrayList<Card>();
	
	// GET TOP CARD OF THE DISCARD PILE
	public Card getTopCard() {
		Card dp = discardPile.get(0);
		return dp;
	}
	
	// EMPTYS DISCARD AND CREATES 'NEW' PILE
	public void addCard(Card c) {
		ArrayList<Card> newPile = new ArrayList<Card>();
		newPile.add(c);
		
		for(int i = 0; i < discardPile.size(); i++) {
			newPile.add(discardPile.get(i));
		}
		discardPile = newPile;
	}
	
	// SHUFFLES DISCARD
	public void shuffle() {
		Collections.shuffle(discardPile);
	}
	
	@Override
	public String toString() {
		String result = "";

			result += getTopCard() + "\n";

		return result;
	}

	// CHECKS FOR NUMBER IN DISCARD
	public int size() {
		return discardPile.size();
	}

	public Card get(int i) {
		Card theTopCard = getTopCard();
		discardPile.remove(0);
		return theTopCard;
	}

}
