package handranking;

import java.util.List;

import card.Card;
import card.Rank;

public class Flush extends HandRanking {

	public Flush(List<Card> cards) {
		super(cards);
	}

	@Override
	public List<Card> getBestFiveCards() {
		List<Card> suitedCards = getSuitedCards();
		if (suitedCards != null){
			return suitedCards.subList(0, 5);
		}
		return null;
	}

	@Override
	public int getRankIndex() {
		return 5;
	}

	@Override
	public String getFormattedName(List<Card> cards) {
		Rank rank = cards.get(0).getRank();
		return rank.singularName+" High Flush";
	}

}
