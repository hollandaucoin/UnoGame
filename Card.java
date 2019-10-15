import java.util.Random;

// Holland Aucoin
// December 4, 2018
// Milestone 6 - Card Class
// This is my own work.

public class Card {

	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_RESET = "\u001B[0m";

	private String value;
	private String color;
	private static Random rnd = new Random();

	public Card(String color, String value) {
		this.color = color;
		this.value = value;
	}

	@Override
	public String toString() {
		if (color.equals("red"))
			return ANSI_RED + value + ANSI_RESET;
		else if (color.equals("green"))
			return ANSI_GREEN + value + ANSI_RESET;
		else if (color.equals("yellow"))
			return ANSI_YELLOW + value + ANSI_RESET;
		else if (color.equals("blue"))
			return ANSI_BLUE + value + ANSI_RESET;
		else
			return value + "";
	}

	public String getColor() {
		return color;
	}

	public String getValue() {
		return value;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String changeColor() {
		String str = "";
		int newColor = rnd.nextInt(4);
		
		switch(newColor) {
		case 0:
			str = "red"; break;
		case 1:
			str = "blue"; break;
		case 2:
			str = "yellow"; break;
		case 3:
			str = "green"; break;
		}
		this.setColor(str);
		return str;
	}

	public boolean isMatch(Card c) {
		if (c.value.equals(this.value))
			return true;
		if (c.color.equals(this.color))
			return true;
		if (this.value.equals("Wild"))
			return true;
		if (this.value.equals("Wild Draw+4"))
			return true;
		else
			return false;
	}
}

