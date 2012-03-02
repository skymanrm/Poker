package handranking;

import java.util.List;

import card.Card;

public class FullHouse extends PairedHandRanking {

	public FullHouse(List<Card> cards) {
		super(cards);
	}

	@Override
	protected int[] getRatio() {
		return new int[]{3,2};
	}

	@Override
	public int getRankIndex() {
		return 6;
	}

	@Override
	public String getFormattedName(List<Card> playingCards) {
		String plural3 = playingCards.get(0).getRank().pluralName;
		String plural2 = playingCards.get(3).getRank().pluralName;
		return "Full House "+plural3+" full of "+plural2;
	}

}
