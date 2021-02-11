package Objects;
import java.util.Arrays;
public class Deck {
	private static String[] cards;
	public Deck(String[] cards) {
		Deck.cards = cards;
	}
	public static String[] getCards() {
		return cards;
	}
	public static void setCards(String[] cards) {
		Deck.cards = cards;
	}
	public String toString() {
		return Arrays.toString(Deck.cards);
	}
}
