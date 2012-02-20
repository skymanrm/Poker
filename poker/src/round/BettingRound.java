package round;

import java.util.List;
import java.util.Scanner;

import player.HandPlayer;
import player.HandPlayer.HandStatus;
import action.BetAction;
import action.BetActionType;

public class BettingRound extends Round<BetAction> {
	
	protected int standingRaise;
	protected int totalToCall;
	
	public BettingRound(int startingPosition, List<HandPlayer> handPlayers) {
		super(startingPosition, handPlayers);
		totalToCall = 0;
		standingRaise = 0;
		this.roundTitle = "Betting Round";
		this.resetActedForPlayers(null, true);
	}

	@Override
	public void evaluateAction(BetAction action) {
		HandPlayer player = action.getHandPlayer();
		BetActionType actionType = action.getBetActionType();
		//Testing
		System.out.println("Evaluation Action Method");
		System.out.println(player.getTablePlayer().getPlayer().getName()+" "+action.toString());
		
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
		checkIfComplete();
	}

	public BetAction getAction(){
		BetActionType betType = this.getActionTypeFromInput();
		java.util.Date date= new java.util.Date();
		//TODO should ask amount
		BetAction bet = new BetAction(date.getTime(), this, activePlayer, betType, 2);
		return bet;
	}
	
	private BetActionType getActionTypeFromInput(){
		Scanner scanner = new Scanner(System.in);
		System.out.println(activePlayer.getTablePlayer().getPlayer().getName()+"(m/r/f)");
		String response = scanner.nextLine().trim();
		BetActionType betType = null;
		if(response.equals("m")){
			betType = BetActionType.MATCH;
		}
		else if(response.equals("r")){
			betType = BetActionType.RAISE;
		}
		else if(response.equals("f")){
			betType = BetActionType.FOLD;
		}
		else{
			getActionTypeFromInput();
		}
		return betType;
	}
	
	private void checkIfComplete() {
		this.setComplete(this.allPlayersHaveActed());
		if(!complete){
			int counter = 0;
			for(HandPlayer player: handPlayers){
				if(player.getHandStatus() == HandStatus.PLAYING){
					counter++;
				}
			}
			if(counter==1){
				setComplete(true);
				getHand().setEndConditionMet(true);
			}
		}
	}

	private void evaluateFold(HandPlayer player){
		player.setHandStatus(HandStatus.FOLDED);
	}
	
	private void evaluateMatch(HandPlayer player){
		int amount = getAmountToCall(player);
		if(amount>=player.getTableBankroll()){
			amount = player.getTableBankroll() - player.getAmountCommitedToRound();
			player.setHandStatus(HandStatus.ALL_IN);
		}
		player.addToPot(amount);
	}
	
	/**
	 * Raises the pot by the amount in addition to whatever raises exist (read raise "on top")
	 * @param amount: amount to increase required contribution from other players (read raise amount)
	 * @param player: raising player
	 */
	private void evaluateRaise(int amount, HandPlayer player){
		if(amount<this.getMinimumRaiseAmount(player)){
			throw new IllegalArgumentException("Too small of a raise");
		}
		evaluateMatch(player);
		if(amount==player.getTableBankroll()){
			player.setHandStatus(HandStatus.ALL_IN);
		}
		if(amount >= standingRaise * 2){
			standingRaise = amount;
			resetActedForPlayers(player, true);
		}
		else{
			resetActedForPlayers(player, false);
		}
		player.addToPot(amount);
		totalToCall+=amount;
	}

	
	private boolean allPlayersHaveActed(){
		for(HandPlayer player : getHandPlayers()){
			if(!player.hasActed()){
				return false;
			}
		}
		return true;
	}
	
	private void resetActedForPlayers(HandPlayer raisePlayer, boolean canRaise) {
		for(HandPlayer player : getHandPlayers()){
			if(player!=raisePlayer && player.getHandStatus()==HandStatus.PLAYING){
				player.setActed(false);
				player.setCanRaise(canRaise);
			}
		}
	}
	
	public int getTotalToCall() {
		return totalToCall;
	}

	public void setTotalToCall(int totalToCall) {
		this.totalToCall = totalToCall;
	}
	
	public int getAmountToCall(HandPlayer player){
		return totalToCall - player.getAmountCommitedToRound();
	}
	
	public int getMinimumRaiseAmount(HandPlayer player){
		if(standingRaise!=0){
			return standingRaise;
		}
		//TODO not chill for Limit Games needs revising
		return player.getTablePlayer().getTable().getBigLimit();
	}

	@Override
	public String toString() {
		String s = super.toString();
		s+="\nStanding Raise: "+standingRaise;
		s+="\tTotal to Call: "+totalToCall;
		return s;
	}
}
