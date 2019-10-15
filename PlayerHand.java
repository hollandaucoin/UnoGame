import java.util.ArrayList;

// Holland Aucoin
// December 4, 2018
// Milestone 6 - Hand Class
// This is my own work.

public class PlayerHand {

	// CREATE ARRAY LIST FOR PLAYER'S CARDS
	public ArrayList<Card> playerHand = new ArrayList<Card>();
	public String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void pickUpCard(Card c) {
		playerHand.add(c);
	}
	
	// CHECK TO SEE IF THE HAND HAS A WILD
	public int hasWild() {
		for (int i = 0; i < playerHand.size(); i++) {
			Card c = playerHand.get(i);
			if (c.getValue() == "Wild") {
				return i;
			}
		}
		return -1;
	}
	
	//TEST TO SEE IF HAND HAS A MATCH TO CURRENT DISCARD
		public boolean hasMatch(DiscardPile dis) {
			for (int i = 0; i < playerHand.size(); i++) {
				if (playerHand.get(i).isMatch(dis.getTopCard())) {
					return true;	
				}
			}
			return false;
		}
	
	//GIVEN: MATCH EXISTS. PLAY AND REMOVE FROM HAND
		public Card playCard(DiscardPile dis) {
			//LOCATE INDEX OF MATCHING CARD
			int index = 0;
			Card cardToPlay = null;
			for (; index < playerHand.size(); index++) {
				if (playerHand.get(index).isMatch(dis.getTopCard())) {
					//REMOVE AND RETURN CARD AT 'INDEX'
					cardToPlay = playerHand.get(index);
					dis.addCard(cardToPlay);
					playerHand.remove(index);
					break;
				}
			}
			return cardToPlay;
		}
	
	// PLAYER DRAWS CARD FROM DECK INTO HAND
	public void drawCard(Deck d) {
		Card c = d.deal();
		playerHand.add(c);
	}

	// CHECK TO SEE IF PLAYER HAS UNO
	public boolean hasUno() {
		if (playerHand.size() == 1)
			return true;
		else
			return false;
	}

	// CHECK TO SEE IF PLAYER IS OUT OF CARDS
	public boolean isOutOfCards() {
		if (playerHand.size() == 0)
			return true;
		else
			return false;
	}
	
	@Override
	public String toString() {
		return playerHand + "";
	}

}

