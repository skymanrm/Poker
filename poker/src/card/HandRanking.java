package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum HandRanking {
	/** Five cards in a row of the same suit (AS,KS,QS,JS,10S) */
	STRAIGHT_FLUSH(8,new int[]{1,1,1,1,1}){
		@Override
		public List<Card> getBestFiveCards(List<Card> cards) {
			List<Card> suitedCards = getSuitedCards(cards);
			return getStraightCards(suitedCards);
		}

		@Override
		public String getFormattedName(List<Card> playingCards) {
			Rank rank = playingCards.get(0).getRank();
			if(rank == Rank.ACE){
				return "Royal Flush";
			}
			return rank.getSingularName()+" High Straight Flush";
		}
	},
	
	/** Four cards of a matching rank (2S,2H,2C,2D,9D) */
	FOUR_OF_A_KIND(7,new int[]{4,1}) {
		
		@Override
		public String getFormattedName(List<Card> playingCards) {
			String plural = playingCards.get(0).getRank().getPluralName();
			return "Four of a Kind "+plural;
		}
	},
	
	/** Three cards of matching rank as well as two cards of a different matching rank (7S,7H,7C,2D,2H) */
	FULL_HOUSE(6,new int[]{3,2}) {
		@Override
		public String getFormattedName(List<Card> playingCards) {
			String plural3 = playingCards.get(0).getRank().getPluralName();
			String plural2 = playingCards.get(3).getRank().getPluralName();
			return "Full House "+plural3+" full of "+plural2;
		}
	},
	
	/** Five cards of the same suit (KH,JH,7H,4H,3H) */
	FLUSH(5,new int[]{1,1,1,1,1}) {
		@Override
		public List<Card> getBestFiveCards(List<Card> cards) {
			List<Card> suitedCards = getSuitedCards(cards);
			if (!suitedCards.isEmpty()){
				suitedCards = suitedCards.subList(0, 5);
			}
			return suitedCards;
		}

		@Override
		public String getFormattedName(List<Card> playingCards) {
			Rank rank = playingCards.get(0).getRank();
			return rank.getSingularName()+" High Flush";
		}
	},
	
	/** Five cards in a row (5H,4H,3C,2D,AD) */
	STRAIGHT(4,new int[]{1,1,1,1,1}) {
		@Override
		public List<Card> getBestFiveCards(List<Card> cards) {
			return getStraightCards(cards);
		}

		@Override
		public String getFormattedName(List<Card> playingCards) {
			Rank rank = playingCards.get(0).getRank();
			return rank.getSingularName()+" High Straight";
		}
	},
	
	THREE_OF_A_KIND(3,new int[]{3,1,1}) {
		@Override
		public String getFormattedName(List<Card> playingCards) {
			String plural3 = playingCards.get(0).getRank().getPluralName();
			return "Three of a Kind "+plural3;
		}
	},
	
	TWO_PAIR(2,new int[]{2,2,1}) {
		@Override
		public String getFormattedName(List<Card> playingCards) {
			String firstPair = playingCards.get(0).getRank().getPluralName();
			String secondPair = playingCards.get(2).getRank().getPluralName();
			return "Two Pair "+firstPair+" and "+secondPair;
		}
	},
	
	PAIR(1,new int[]{2,1,1,1}) {
		@Override
		public String getFormattedName(List<Card> playingCards) {
			String pair = playingCards.get(0).getRank().getPluralName();
			return "Pair of "+pair;
		}
	},
	
	HIGH_CARD(0,new int[]{1,1,1,1,1}) {
		@Override
		public String getFormattedName(List<Card> playingCards) {
			String high = playingCards.get(0).getRank().getSingularName();
			return high+" High";
		}
	};
	
	private final int rankIndex;
	private final int[] ratio;
	
	private HandRanking(int rankIndex, int[] ratio){
		this.rankIndex = rankIndex;
		this.ratio = ratio;
	}
	
	public abstract String getFormattedName(List<Card> playingCards);
	
	public List<Card> getBestFiveCards(List<Card> cards) {
		List<Card> unusedCards = new ArrayList<Card>(cards);
		List<Card> usedCards = new ArrayList<Card>();
		ratioLoop:
		for(int pairNumber : ratio){
			for(Rank rank: Rank.values()){
				List<Card> cardsAtRank = getCardsAtRank(unusedCards , rank);
				if(cardsAtRank.size() >= pairNumber){
					cardsAtRank = cardsAtRank.subList(0, pairNumber);
					usedCards.addAll(cardsAtRank);
					unusedCards.removeAll(cardsAtRank);
					continue ratioLoop;
				}
			}
			return Collections.emptyList();
		}
		return usedCards;
	}
	
	private List<Card> getCardsAtRank(List<Card> cards, Rank rank){
		List<Card> cardsAtRank = new ArrayList<Card>();
		for(Card card: cards)
			if(card.getRank() == rank)
				cardsAtRank.add(card);
		return cardsAtRank;
	}

	
	private static List<Card> getSuitedCards(List<Card> cards){
		List<Card> suitedCards = new ArrayList<Card>();
		for(Suit suit: Suit.values()){
			for(Card card: cards){
				if (card.getSuit() == suit){
					suitedCards.add(card);
				}
			}
			if (suitedCards.size() >= 5){
				return suitedCards;
			}
			suitedCards.clear();
		}
		return Collections.emptyList();
	}
	
	private static List<Card> getStraightCards(List<Card> cards){
		//ranks have to be sorted by rankIndex
		List<Card> straightCards = getStraightCardsForRanks(cards,Rank.values());
		if(straightCards.isEmpty()){
			Rank[] ranks = new Rank[]{Rank.FIVE,Rank.FOUR,Rank.THREE,Rank.TWO,Rank.ACE};
			straightCards = getStraightCardsForRanks(cards,ranks);
		}
		return straightCards;
	}
	
	private static List<Card> getStraightCardsForRanks(List<Card> cards, Rank[] ranks){
		List<Card> straightCards = new ArrayList<Card>();
		
		rankLoop: 
		for(Rank rank: ranks){
			for(Card card: cards){
				if(card.getRank() == rank){
					straightCards.add(card);
					if(straightCards.size()==5){
						return straightCards;
					}
					continue rankLoop;
				}
			}
			straightCards.clear();
		}
		return Collections.emptyList();
	}

	public int getRankIndex() {
		return rankIndex;
	}
	
	public static Comparator<HandRanking> getComparator(){
		return new Comparator<HandRanking>(){
			@Override
			public int compare(HandRanking arg0, HandRanking arg1) {
				return arg0.rankIndex-arg1.rankIndex;
			}
		};
	}
}
