package handranking;

import java.util.ArrayList;
import java.util.List;

import card.Card;
import card.Rank;
import card.Suit;

public abstract class HandRanking{

	protected final List<Card> cards;

	public HandRanking(List<Card> cards) {
		this.cards = cards;
	}

	/**
	 * @return if possible returns the best combination of cards for the HandRanking, otherwise returns null
	 */
	public abstract List<Card> getBestFiveCards();
	/**
	 * @return all the cards in a players hand
	 */
	public List<Card> getCards() {
		return cards;
	}
	
	/**
	 * @return the index of the HandRanking
	 */
	public abstract int getRankIndex();
	
	/**
	 * @return HandRanking's name and its value (ex. Ten High Straight)
	 */
	public abstract String getFormattedName(List<Card> playingCards);
	
	protected List<Card> getSuitedCards(){
		ArrayList<Card> suitedCards = new ArrayList<Card>();
		for(Suit suit: Suit.values()){
			for(Card card: cards){
				if (card.hasSameSuit(suit)){
					suitedCards.add(card);
				}
			}
			if (suitedCards.size() >= 5){
				return suitedCards;
			}
			suitedCards.clear();
		}
		return null;
	}
	
	protected List<Card> getStraightCards(List<Card> cards){
		List<Card> straightCards = new ArrayList<Card>();
		straightCards.add(cards.get(0));
		
		for(short i = 1; i < cards.size(); i++){
			Card current = cards.get(i);
			Card last = getLast(straightCards);
			int difference = getDifference(current, last);
			
			if(difference == 0){
				continue;
			}
			else if(difference == 1){
				straightCards.add(current);
				if(straightCards.size()==5){
					return straightCards;
				}
			}
			else{
				straightCards.clear();
				straightCards.add(current);
			}
		}
		return getLowStraight(cards.get(0), straightCards);
	}
	
	private List<Card> getLowStraight(Card firstCard, List<Card> straightCards){
		boolean hasAce = firstCard.getRank() == Rank.ACE;
		boolean conntectedTwo = !straightCards.isEmpty() && getLast(straightCards).getRank() == Rank.TWO;
		if (straightCards.size() == 4 && hasAce && conntectedTwo){
			straightCards.add(firstCard);
			return straightCards;
		}
		return null;
	}
	
	
	protected int getDifference(Card card1, Card card2){
		return (card1.getRank().rankIndex - card2.getRank().rankIndex);
	}
	
	protected Card getLast(List<Card> cards){
		return cards.get(cards.size() - 1);
	}
}
