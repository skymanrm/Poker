package handranking;
import java.util.Collections;
import java.util.List;
import card.Card;

public class FormedHand implements Comparable<FormedHand>{

	private final List<Card> cards;
	private List<Card> playingCards;
	private HandRankingType handRankingType;
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
				handRankingType = hr.getType();
			}
			counter++;
		}while(playingCards == null);
	}

	private HandRanking getHandRanking(int index){
		switch(index){
		case 0: return new StraightFlush(cards);
		case 1: return new Quads(cards);
		case 2: return new FullHouse(cards);
		case 3: return new Flush(cards);
		case 4: return new Straight(cards);
		case 5: return new Trips(cards);
		case 6: return new TwoPair(cards);
		case 7: return new Pair(cards);
		case 8: return new HighCard(cards);
		}
		return null;
	}
	@Override
	public int compareTo(FormedHand o) {
		if(handRankingType.index > o.handRankingType.index){
			return 1;
		}
		else if(handRankingType.index < o.handRankingType.index){
			return -1;
		}
		else{
			return compareSameHandRankingCards(o);
		}
	}
	
	private int compareSameHandRankingCards(FormedHand o){
		for(int i = 0; i<5;i++){
			Card c = playingCards.get(i);
			Card oc = o.playingCards.get(i);
			int compare = c.compareTo(oc);
			if(compare > 0){
				return 1;
			}
			else if(compare < 0){
				return -1;
			}
		}
		return 0;
	}

	public String getDisplayName() {
		return displayName;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Cards: ");
		for(Card card: cards){
			sb.append(card.toString()+",");
		}
		sb.append("\tPlaying: ");
		for(Card card: playingCards){
			sb.append(card.toString()+",");
		}
		sb.append("\t"+displayName);
		return sb.toString();
	}
	
	public HandRankingType getType() {
		return handRankingType;
	}
}
