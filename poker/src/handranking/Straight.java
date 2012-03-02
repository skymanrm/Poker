package handranking;

import java.util.List;

import card.Card;
import card.Rank;

public class Straight extends HandRanking {

	public Straight(List<Card> cards) {
		super(cards);
	}

	@Override
	public List<Card> getBestFiveCards() {
		return getStraightCards(cards);
	}

	@Override
	public int getRankIndex() {
		return 4;
	}

	@Override
	public String getFormattedName(List<Card> playingCards) {
		Rank rank = cards.get(0).getRank();
		return rank.singularName+" High Straight";
	}

}
