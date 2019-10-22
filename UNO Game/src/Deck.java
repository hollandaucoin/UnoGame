import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	private ArrayList<Card> theCards = new ArrayList<Card>();
	// STATIC ARRAY FOR THE COLORS
	private static String[] color = { "blue", "red", "yellow", "green" };

	public Deck() {

		//BUILD DECK FOR EACH COLOR
		for (int i = 0; i < color.length; i++) {
			// Add 0
			Card c = new Card(color[i], "0");
			theCards.add(c);

			//ADD 1-9 NUMBERED CARDS
			for (int j = 1; j <= 9; j++) {
				theCards.add(new Card(color[i], j + ""));
				theCards.add(new Card(color[i], j + ""));
			}

			//ADD SPECIALTY CARDS
			for (int j = 1; j <= 2; j++) {
				theCards.add(new Card(color[i], "SKIP"));
				theCards.add(new Card(color[i], "REVERSE"));
				theCards.add(new Card(color[i], "DRAW+2"));
			}

			//ADD WILD CARDS
			theCards.add(new Card("", "Wild"));
			theCards.add(new Card("", "Wild Draw+4"));
		}

	}
	
	public void restockDeck(DiscardPile dp) {
		Card top = dp.getTopCard();
		dp.shuffle();
		theCards.add(top);
		for (int i = 1; i < dp.size(); i++) {
			theCards.add(dp.get(i));
		}
	}

	public Card deal() {
		Card c = theCards.get(0);
		theCards.remove(0);
		return c;
	}
	
	public void shuffle() {
		Collections.shuffle(theCards);
	}
	
	public boolean isEmpty() {
		if (theCards.size() <= 5) {
			return true;
		}
		
		return false;
	}

	@Override
	public String toString() {
		String result = "";

		for (Card card : theCards) {
			result += card + "\n";
		}

		return result;
	}

}

