package round;

import java.util.List;

import player.HandPlayer;
import table.Hand;
import table.Pot;


import card.Visibility;

public class DealingRound extends BettingRound {

	private final List<Visibility> visibilities;
	
	public DealingRound(int startingPosition,List<HandPlayer> handPlayers,Pot pot, List<Visibility> visibilities, boolean blinds) {
		super(startingPosition, handPlayers,pot, blinds);
		this.visibilities = visibilities;
		
		addCards();
	}

	private void addCards(){
		Hand hand = getHand();
		for(Visibility v: visibilities){
			if(v == Visibility.PRIVATE || v == Visibility.PUBLIC){
				for(HandPlayer player: handPlayers){
					hand.addCardToPlayer(v, player);
				}
			}
			else{
				hand.addCommunityCard();
			}
		}
	}
	
	public List<Visibility> getVisibilities() {
		return visibilities;
	}
}
