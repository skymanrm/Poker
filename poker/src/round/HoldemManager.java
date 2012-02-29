package round;

import java.util.ArrayList;
import java.util.List;

import player.HandPlayer;
import table.Hand;

import card.Visibility;

public class HoldemManager implements RoundManager {

	private Hand hand;
	
	@Override
	public Round<?> getRoundForIndex(Hand hand, int index) {
		this.hand = hand;
		Round<?> round = null;
		switch(index){
		case 0: round = this.getHoleCardRound(); break;
		case 1: round = this.getFlopRound(); break;
		case 2: round = this.getTurnOrRiverRound(); break;
		case 3: round = this.getTurnOrRiverRound(); break;
		}
		return round;
	}
	
	public DealingRound getHoleCardRound(){
		List<Visibility> visibilities = new ArrayList<Visibility>();
		visibilities.add(Visibility.PRIVATE);
		visibilities.add(Visibility.PRIVATE);
		List<HandPlayer> players = hand.getPlayersInHand();
		int startingPosition;
		if(players.size() > 3){
			startingPosition = 3;
		}
		else{
			startingPosition = 0;
		}
		return new DealingRound(startingPosition,players,hand.getPot(),visibilities);
	}
	
	public DealingRound getFlopRound(){
		List<Visibility> visibilities = new ArrayList<Visibility>();
		visibilities.add(Visibility.COMMUNITY);
		visibilities.add(Visibility.COMMUNITY);
		visibilities.add(Visibility.COMMUNITY);
		List<HandPlayer> players = hand.getPlayersInHand();
		int startingPosition = 1;
		return new DealingRound(startingPosition,players,hand.getPot(),visibilities);
	}
	
	public DealingRound getTurnOrRiverRound(){
		List<Visibility> visibilities = new ArrayList<Visibility>();
		visibilities.add(Visibility.COMMUNITY);
		List<HandPlayer> players = hand.getPlayersInHand();
		int startingPosition = 1;
		return new DealingRound(startingPosition,players,hand.getPot(),visibilities);
	}
}
