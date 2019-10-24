import java.util.ArrayList;

public class Driver {
	public static void main(String[] args) {

		// INTRO
		System.out.println("");
		System.out.println("Welcome to the game of UNO!");
		System.out.println("");
		System.out.println("Shuffling cards...");
		System.out.println("Dealing cards to each player...");
		System.out.println("");

		// CREATES AND SHUFFLES DECK
		Deck d = new Deck();
		d.shuffle();

		// CREATE DISCARD PILE
		DiscardPile discard = new DiscardPile();

		// CREATE PLAYERS AND DECKS
		PlayerHand p1 = new PlayerHand();
		p1.setName("Holland");
		for (int i = 0; i < 7; i++) {
			p1.pickUpCard(d.deal());
		}
		
		PlayerHand p2 = new PlayerHand();
		p2.setName("Josie");
		for (int i = 0; i < 7; i++) {
			p2.pickUpCard(d.deal());
		}
		
		PlayerHand p3 = new PlayerHand();
		p3.setName("Morgan");
		for (int i = 0; i < 7; i++) {
			p3.pickUpCard(d.deal());
		}

		PlayerHand p4 = new PlayerHand();
		p4.setName("Halle");
		for (int i = 0; i < 7; i++) {
			p4.pickUpCard(d.deal());
		}

		// DISPLAYS CONTENT OF EACH PLAYERS HAND
		System.out.println(p1.getName() + "'s hand: " + p1);
		System.out.println(p2.getName() + "'s hand: " + p2);
		System.out.println(p3.getName() + "'s hand: " + p3);
		System.out.println(p4.getName() + "'s hand: " + p4);
		System.out.println("");

		// CREATE ARRAY LIST TO STORE PLAYERS
		ArrayList<PlayerHand> players = new ArrayList<PlayerHand>();

		players.add(p1);
		players.add(p2);
		players.add(p3);
		players.add(p4);

		// PUT TOP CARD IN DISCARD PILE
		discard.addCard(d.deal());

		boolean winner = false;
		boolean forward = true;
		int nextPlayer = -1;
		
		// CHECK FOR ACTION IN TOP CARD
		while (discard.getTopCard().getValue().equals("Wild") || 
				discard.getTopCard().getValue().equals("Wild Draw+4")) {
			discard.addCard(d.deal());
		}
		if (discard.getTopCard().getValue().equals("DRAW+2")) {
			System.out.println("Top card: " + discard.getTopCard());
			p1.pickUpCard(d.deal());
			p1.pickUpCard(d.deal());
			System.out.println(p1.getName() + " had to pick up 2 cards from the top card dealt, and doesn't get to play.");
			nextPlayer++;
			System.out.println("");
		}
		else if (discard.getTopCard().getValue().equals("SKIP")) {
			System.out.println("Top card: " + discard.getTopCard());
			System.out.println(p1.getName() + " has been skipped from the top card dealt.");
			nextPlayer++;
			System.out.println("");
		}
		else if (discard.getTopCard().getValue().equals("REVERSE")) {
			System.out.println("Top card: " + discard.getTopCard());
			System.out.println("Since the top card is a reverse, it is " + p4.getName() + "'s turn.");
			nextPlayer++;
			forward = false;
			System.out.println("");
		}

		// ENTER WHILE LOOP OF GAME AND DETERMINE IF THERE IS A WINNER
		while (!winner) {

			// DETERMINE WHO'S TURN AND FORWARD OR REVERSE ROTATION
			if (forward == true) {
				nextPlayer++;
				nextPlayer %= 4;
			} else {
				nextPlayer--;
				if (nextPlayer == -1) {
					nextPlayer = 3;
				}
			}

			// SHOW TOP CARD
			System.out.println("Top card: " + discard.getTopCard());

			// SHOW PLAYERS HAND
			System.out.println(players.get(nextPlayer).getName() + ": " + players.get(nextPlayer));

			// CHECK FOR DECK SUPPLY
			if (d.isEmpty()) {
				d.restockDeck(discard);
			}

			// TEST FOR MATCH TO DISCARD
			Card c = players.get(nextPlayer).playCard(discard);
			if (c == null) {
				players.get(nextPlayer).drawCard(d);
				System.out.println("No match. " + players.get(nextPlayer).getName() + " picked up 1 card.");
				System.out.println("");
			}

			// DEAL WITH ACTION CARDS
			else {
				// PLAYER PLAYS A WILD
				if (c.getValue().equals("Wild")) {
					c.changeColor();
					System.out.print(players.get(nextPlayer).getName() + " played a " + discard);
					System.out.println(players.get(nextPlayer).getName() + " changed the color to " + c.getColor()+".");
					System.out.println("");
					
					// CHECK FOR WINNER
					winner = players.get(nextPlayer).isOutOfCards();
					if (winner)
						break;
				} 
				// PLAYER PLAYS A WILD DRAW+4
				else if (c.getValue().equals("Wild Draw+4")) {
					c.changeColor();
					System.out.print(players.get(nextPlayer).getName() + " played a " + discard);

					// CHECK FOR WINNER
					winner = players.get(nextPlayer).isOutOfCards();
					if (winner)
						break;
					
					// DETERMINE WHO IS PICKING UP CARDS
					if (forward == true) {
						nextPlayer++;
						nextPlayer %= 4;
					} else {
						nextPlayer--;
						if (nextPlayer == -1) {
							nextPlayer = 3;
						}
					}
					players.get(nextPlayer).drawCard(d);
					players.get(nextPlayer).drawCard(d);
					players.get(nextPlayer).drawCard(d);
					players.get(nextPlayer).drawCard(d);
					System.out.print(players.get(nextPlayer).getName() + " picked up 4 cards and doesn't get to play.");
					System.out.println("\n");
				} 
				// PLAYER PLAYS A DRAW+2
				else if (c.getValue().equals("DRAW+2")) {
					System.out.print(players.get(nextPlayer).getName() + " played: " + discard);

					// CHECK FOR WINNER
					winner = players.get(nextPlayer).isOutOfCards();
					if (winner)
						break;
					
					// DETERMINE WHO IS PICKING UP CARDS
					if (forward == true) {
						nextPlayer++;
						nextPlayer %= 4;
					} else {
						nextPlayer--;
						if (nextPlayer == -1) {
							nextPlayer = 3;
						}
					}
					players.get(nextPlayer).drawCard(d);
					players.get(nextPlayer).drawCard(d);
					System.out.print(players.get(nextPlayer).getName() + " picked up 2 cards and doesn't get to play.");
					System.out.println("\n");
				}
				// PLAYER PLAYS A SKIP
				else if (c.getValue().equals("SKIP")) {
					System.out.print(players.get(nextPlayer).getName() + " played: " + discard);

					// CHECK FOR WINNER
					winner = players.get(nextPlayer).isOutOfCards();
					if (winner)
						break;
					
					// DETERMINE WHO IS BEING SKIPPED
					if (forward == true) {
						nextPlayer++;
						nextPlayer %= 4;
					} else {
						nextPlayer--;
						if (nextPlayer == -1) {
							nextPlayer = 3;
						}
					}
					System.out.println(players.get(nextPlayer).getName() + " has been skipped!");
					System.out.println("");
				}
				// PLAYER PLAYS A REVERSE
				else if (c.getValue().equals("REVERSE")) {
					System.out.print(players.get(nextPlayer).getName() + " played: " + discard);
					
					// CHECK FOR WINNER
					winner = players.get(nextPlayer).isOutOfCards();
					if (winner)
						break;
				
					// DETERMINE NEW ORDER
					if (forward == true) {
						forward = false;
					} else {
						forward = true;
					}

					System.out.println("The order has now been reversed!");
					System.out.println("");
				}
				// PLAYER PLAYS A NON-ACTION CARD
				else {
					System.out.print(players.get(nextPlayer).getName() + " played: " + discard);
					System.out.println("");
				}
			}

			// CHECK FOR UNO
			if (players.get(nextPlayer).hasUno() == true) {
				System.out.println(players.get(nextPlayer).getName() + " has UNO!");
				System.out.println("");
			}

			// CHECK FOR WINNER
			winner = players.get(nextPlayer).isOutOfCards();
		}
		// OUTPUT WINNER
		if (winner)
			System.out.println(players.get(nextPlayer).getName() + " has won!");
	}

}

