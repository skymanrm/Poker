package handranking;

import java.util.ArrayList;
import java.util.List;

import card.Card;

public abstract class PairedHandRanking extends HandRanking {
	
	public PairedHandRanking(List<Card> cards) {
		super(cards);
	}

	protected abstract int[] getRatio();
	
	@Override
	public List<Card> getBestFiveCards() {
		List<Card> usedCards = new ArrayList<Card>();
		for(int pairNumber : getRatio()){
			List<Card> pairedCards = getMatchingRank(cards, pairNumber ,usedCards);
			if (pairedCards == null){
				return null;
			}
			usedCards.addAll(pairedCards);
		}
		return usedCards;
	}

	private List<Card> getMatchingRank(List<Card> cards, int number, List<Card> alreadyUsed){
		List<Card> pairedCards = new ArrayList<Card>();
		if (number == 1){
			for (Card card : cards){
				if (!alreadyUsed.contains(card)){
					pairedCards.add(card);
					return pairedCards;
				}
			}
		}
		else{
			pairedCards.add(cards.get(0));
			for(short i = 1; i < cards.size(); i++){
				Card current = cards.get(i);
				Card last = getLast(pairedCards);
				int difference = getDifference(current, last);
				
				if((difference == 0) && ! alreadyUsed.contains(current)){
					pairedCards.add(current);
					if(pairedCards.size()==number){
						return pairedCards;
					}
				}
				else{
					pairedCards.clear();
					pairedCards.add(current);
				}
			}
		}
		return null;
	}
}
