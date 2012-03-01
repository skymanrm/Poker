package handranking;

import java.util.List;

import card.Card;
import card.Rank;

public class StraightFlush extends HandRanking{

	public StraightFlush(List<Card> cards) {
		super(cards);
	}

	@Override
	public int getRankIndex() {
		return 8;
	}

	@Override
	public String getFormattedName(List<Card> playingCards) {
		if(playingCards.get(0).getRank() == Rank.ACE){
			return "Royal Flush";
		}
		return singularRankNames[0]+" High Straight Flush";
	}

	@Override
	public List<Card> getBestFiveCards() {
		List<Card> suitedCards = getSuitedCards();
		if (suitedCards != null){
			List<Card> straightCards = getStraightCards(suitedCards);
			if (straightCards != null){
				return straightCards;
			}
		}
		return null;
	}
}
