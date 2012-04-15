package card;

public class DealtCard {
	
	private final Card card;
	private final CardVisibility visibility;
	
	public DealtCard(Card card, CardVisibility visibility){
		this.card = card;
		this.visibility = visibility;
	}

	public Card getCard() {
		return card;
	}

	public CardVisibility getVisibility() {
		return visibility;
	}

	@Override
	public String toString() {
		return card.toString();
	}
}
