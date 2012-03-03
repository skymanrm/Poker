package handranking;

import java.util.List;

import card.Card;

public class Quads extends PairedHandRanking {

	public Quads(List<Card> cards) {
		super(cards);
	}

	@Override
	protected int[] getRatio() {
		return new int[]{4,1};
	}

	@Override
	public HandRankingType getType() {
		return HandRankingType.QUADS;
	}

	@Override
	public String getFormattedName(List<Card> playingCards) {
		String plural = playingCards.get(0).getRank().pluralName;
		return "Four of a Kind "+plural;
	}

}
