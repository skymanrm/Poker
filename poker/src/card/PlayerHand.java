package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerHand {
	private HandRanking handRanking;
	private String formattedName;
	private List<Card> bestFiveCards;
	
	//TODO need to add support for low and ace-low hands
	public void getBestHand(List<Card> holeCards, List<Card> communityCards){
		//Defensive Copies
		List<Card> cards = new ArrayList<Card>(holeCards);
		cards.addAll(new ArrayList<Card>(communityCards));
		
		Collections.sort(cards, Card.highComparator);
		HandRanking[] handrankings = HandRanking.getHighOrderedHandRankings();
		for(HandRanking handRanking: handrankings){
			bestFiveCards = handRanking.getBestFiveCards(cards);
			if(!bestFiveCards.isEmpty()){
				formattedName = handRanking.getFormattedName(bestFiveCards);
				this.handRanking = handRanking;
				break;
			}
		}
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
}
