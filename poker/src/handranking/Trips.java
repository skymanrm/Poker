package handranking;

import java.util.List;

import card.Card;

public class Trips extends PairedHandRanking {

	public Trips(List<Card> cards) {
		super(cards);
	}

	@Override
	protected int[] getRatio() {
		return new int[]{3,1,1};
	}

	@Override
	public int getRankIndex() {
		return 3;
	}

	@Override
	public String getFormattedName(List<Card> playingCards) {
		String plural3 = playingCards.get(0).getRank().pluralName;
		return "Three of a Kind "+plural3;
	}

}
