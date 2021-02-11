package Objects;
public class Card {
	private static String cardFlipped;
	public Card(String cardFlipped) {
		this.cardFlipped = cardFlipped;
	}
	public String cardFlipped() {
		return cardFlipped;
	}
	public void setCardFlipped(String cardFlipped) {
		this.cardFlipped = cardFlipped;
	}
	public String toString() {
		String card = "";
		card = this.cardFlipped;
		return card;
	}
}
