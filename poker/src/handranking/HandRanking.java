package handranking;

import java.util.ArrayList;
import java.util.List;
import card.Card;
import card.Rank;
import card.Suit;

public abstract class HandRanking{

	protected final List<Card> cards;
	
	protected final String[] singularRankNames = {"Ace","King","Queen","Jack","Ten",
			"Nine","Eight","Seven","Six",
			"Five","Four","Three","Two"};
	
	protected final String[] pluralRankNames = {"Aces","Kings","Queens","Jacks","Tens",
			"Nines","Eights","Sevens","Sixes",
			"Fives","Fours","Threes","Twos"};

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
	 * @return all the cards in a players hand
	 */
	public abstract int getRankIndex();
	
	/**
	 * @return HandRanking's name and its value (ex. Straight 10 high)
	 */
	public abstract String getFormattedName(List<Card> cards);
	
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
			else{
				suitedCards.clear();
			}
		}
		return null;
	}
	/*
	 * for(Iterator<Integer> iter=liszt.iterator(); iter.hasNext();) {
        int num = ((Integer)iter.next()).intValue();
        total += num;
    }
	 */
	protected List<Card> getStraightCards(List<Card> cards){
		List<Card> straightCards = new ArrayList<Card>();
		straightCards.add(cards.get(0));
		
		for(Card current: cards){
			Card lastCard = straightCards.get(straightCards.size()-1);
			int dif = getDifference(lastCard, current);
			
			if(dif == 0){
				continue;
			}
			else if(dif == 1){
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
		return getLowStraightCards(cards.get(0), straightCards);
	}
	
	private List<Card> getLowStraightCards(Card firstCard, List<Card> straightCards){
		boolean hasAce = firstCard.getRank() == Rank.ACE;
		boolean hasConnectedTwo = straightCards.get(straightCards.size()-1).getRank() == Rank.TWO;
		if (straightCards.size() == 4 && hasAce && hasConnectedTwo){
			straightCards.add(firstCard);
			return straightCards;
		}
		return null;
	}
	
	protected int getDifference(Card card1, Card card2){
		return (card1.getRank().rankIndex - card2.getRank().rankIndex);
	}
}
