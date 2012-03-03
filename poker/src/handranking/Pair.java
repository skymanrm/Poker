package handranking;

import java.util.List;

import card.Card;

public class Pair extends PairedHandRanking {

	public Pair(List<Card> cards) {
		super(cards);
	}

	@Override
	protected int[] getRatio() {
		return new int[]{2,1,1,1};
	}

	@Override
	public HandRankingType getType() {
		return HandRankingType.PAIR;
	}

	@Override
	public String getFormattedName(List<Card> playingCards) {
		String pair = playingCards.get(0).getRank().pluralName;
		return "Pair of "+pair;
	}

}
