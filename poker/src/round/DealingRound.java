package round;

import java.util.List;

import player.HandPlayer;


import card.Hand;
import card.Pot;
import card.Visibility;

public class DealingRound extends BettingRound {

	private final List<Visibility> visibilities;
	
	public DealingRound(int startingPosition,List<HandPlayer> handPlayers,Pot pot, List<Visibility> visibilities) {
		super(startingPosition, handPlayers,pot);
		this.visibilities = visibilities;
		this.roundTitle = "Dealing Round";
		Hand hand = handPlayers.get(0).getHand();
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
