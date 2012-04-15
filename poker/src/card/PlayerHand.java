package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerHand implements Comparable<PlayerHand>{
	private HandRanking handRanking;
	private String formattedName;
	private List<Card> bestFiveCards;
	
	//TODO need to add support for low and ace-low hands
	public PlayerHand(List<Card> holeCards, List<Card> communityCards){
		//Defensive Copies
		List<Card> cards = new ArrayList<Card>(holeCards);
		cards.addAll(new ArrayList<Card>(communityCards));
		
		Collections.sort(cards, Card.highComparator);
		
		for(HandRanking handRanking: HandRanking.values()){
			bestFiveCards = handRanking.getBestFiveCards(cards);
			if(!bestFiveCards.isEmpty()){
				formattedName = handRanking.getFormattedName(bestFiveCards);
				this.handRanking = handRanking;
				break;
			}
		}
	}

	public static PlayerHand getPlayerHandWithDealtCards(List<DealtCard> holeCards, List<DealtCard> communityCards){
		List<Card> hCards = new ArrayList<Card>();
		for(DealtCard dealtCard: holeCards){
			hCards.add(dealtCard.getCard());
		}
		List<Card> cCards = new ArrayList<Card>();
		for(DealtCard dealtCard: communityCards){
			cCards.add(dealtCard.getCard());
		}
		return new PlayerHand(hCards,cCards);
	}
	
	public HandRanking getHandRanking() {
		return handRanking;
	}

	public String getFormattedName() {
		return formattedName;
	}

	public List<Card> getBestFiveCards() {
		return new ArrayList<Card>(bestFiveCards);
	}

	@Override
	public int compareTo(PlayerHand arg0) {
		int compare = handRanking.compareTo(arg0.getHandRanking());
		if(compare==0){
			for(int i =0;i<5;i++){
				Rank selfRank = bestFiveCards.get(i).getRank();
				Rank otherRank = arg0.getBestFiveCards().get(i).getRank();
				compare = selfRank.compareTo(otherRank);
				if(compare!=0)
					break;
			}
		}
		return compare;
	}
}
