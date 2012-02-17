package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import card.HandPlayer.HandStatus;

public class BettingRound extends Round<BetAction> {
	
	protected final List<Integer> raises;
	protected int totalToCall;
	
	public BettingRound(int startingPosition, List<HandPlayer> handPlayers) {
		super(startingPosition, handPlayers);
		raises = new ArrayList<Integer>();
		totalToCall = 0;
	}

	/*
	 * Need to revise how I am doing totalAmountToCall w/ 
	 * players contributions vs amount they need to call
	 */
	@Override
	public void evaluateAction(BetAction action) {
		HandPlayer player = action.getHandPlayer();
		BetActionType actionType = action.getBetActionType();
		
		if(actionType == BetActionType.FOLD){
			this.evaluateFold(player);
		}
		else if(actionType == BetActionType.MATCH){
			this.evaluateMatch(player);
		}
		else{
			this.evaluateRaise(action.getAmount(), player);
		}
		
		player.setActed(true);
		this.setComplete(this.allPlayersHaveActed());
	}

	private void evaluateFold(HandPlayer player){
		player.setHandStatus(HandStatus.FOLDED);
	}
	
	private void evaluateMatch(HandPlayer player){
		int amount = totalToCall - player.getAmountCommitedToRound();
		if(amount>=player.getTableBankroll()){
			int allInAmount = player.getTableBankroll() - player.getAmountCommitedToRound();
			player.setHandStatus(HandStatus.ALL_IN);
			player.addToPot(allInAmount);
		}
		player.addToPot(amount);
	}
	
	private void evaluateRaise(int amount, HandPlayer player){
		if(totalToCall != 0){
			
		}
		player.addToPot(amount);
	}

	private boolean allPlayersHaveActed(){
		for(HandPlayer player : getHandPlayers()){
			if(!player.hasActed()){
				return false;
			}
		}
		return true;
	}
	
	private void resetActedForPlayers(HandPlayer raisePlayer) {
		for(HandPlayer player : getHandPlayers()){
			if(player!=raisePlayer && player.getHandStatus()==HandStatus.PLAYING){
				player.setActed(false);
			}
		}
	}
	
	public int getTotalToCall() {
		return totalToCall;
	}

	public void setTotalToCall(int totalToCall) {
		this.totalToCall = totalToCall;
	}

	public List<Integer> getRaises() {
		return raises;
	}
}
