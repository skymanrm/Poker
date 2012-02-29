package handranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import card.Card;

public class FormedHand {

	private List<Card> cards;
	private List<Card> playingCards;
	private HandRankingType handRanking;
	
	public FormedHand(List<Card> cards){
		this.cards = cards;
		Collections.sort(this.cards);
		formBestHand();
	}

	private void formBestHand() {
		//List<Card> thec = new ArrayList<Card>();
	}
	
	
}
