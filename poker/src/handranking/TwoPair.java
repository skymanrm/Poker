package handranking;

import java.util.List;

import card.Card;

public class TwoPair extends PairedHandRanking {

	public TwoPair(List<Card> cards) {
		super(cards);
	}

	@Override
	protected int[] getRatio() {
		return new int[]{2,2,1};
	}

	@Override
	public HandRankingType getType() {
		return HandRankingType.TWO_PAIR;
	}

	@Override
	public String getFormattedName(List<Card> playingCards) {
		String firstPair = playingCards.get(0).getRank().pluralName;
		String secondPair = playingCards.get(2).getRank().pluralName;
		return "Two Pair "+firstPair+" and "+secondPair;
	}

}
