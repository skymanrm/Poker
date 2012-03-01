package handranking;
import java.util.Collections;
import java.util.List;
import card.Card;

public class FormedHand implements Comparable<FormedHand>{

	private List<Card> cards;
	private List<Card> playingCards;
	private int handRankingIndex;
	private String displayName;
	
	public FormedHand(List<Card> cards){
		this.cards = cards;
		Collections.sort(this.cards);
		Collections.reverse(this.cards);
		formBestHand();
	}

	private void formBestHand() {
		int counter = 0;
		HandRanking hr;
		do{
			hr = getHandRanking(counter);
			playingCards = hr.getBestFiveCards();
			if(playingCards != null){
				displayName = hr.getFormattedName(playingCards);
				handRankingIndex = hr.getRankIndex();
			}
		}while(playingCards == null);
	}

	private HandRanking getHandRanking(int index){
		switch(index){
		case 0: return new StraightFlush(cards);
		}
		return null;
	}
	@Override
	public int compareTo(FormedHand o) {
		if(handRankingIndex < o.handRankingIndex){
			return -1;
		}
		else if(handRankingIndex > o.handRankingIndex){
			return 1;
		}
		else{
			return compareSameHandRankingCards(o);
		}
	}
	
	private int compareSameHandRankingCards(FormedHand o){
		for(int i = 0; i<5;i++){
			Card c = playingCards.get(i);
			Card oc = o.playingCards.get(0);
			int compare = c.compareTo(oc);
			if(compare!=0){
				return compare;
			}
		}
		return 0;
	}

	public String getDisplayName() {
		return displayName;
	}

	@Override
	public String toString() {
		return "FormedHand [cards=" + cards + ", playingCards=" + playingCards
				+ ", handRankingIndex=" + handRankingIndex + ", displayName="
				+ displayName + "]";
	}
	
}
