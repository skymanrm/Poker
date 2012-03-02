package handranking;

import java.util.List;

import card.Card;

public class HighCard extends PairedHandRanking {

	public HighCard(List<Card> cards) {
		super(cards);
	}

	@Override
	protected int[] getRatio() {
		return new int[]{1,1,1,1,1};
	}

	@Override
	public int getRankIndex() {
		return 0;
	}

	@Override
	public String getFormattedName(List<Card> playingCards) {
		String high = playingCards.get(0).getRank().singularName;
		return high+" High";
	}

}
