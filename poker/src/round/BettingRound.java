package round;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import player.HandPlayer;
import table.HandStatus;
import table.Pot;
import action.BetAction;
import action.BetActionType;

public class BettingRound extends Round<BetAction> {

	protected int standingRaise;
	protected int totalToCall;
	
	protected final List<HandPlayer> allInPlayers;
	protected final Pot pot;
	
	//TODO manually keep track of # players instead of counting every time
	
	public BettingRound(int startingPosition, List<HandPlayer> handPlayers, Pot pot, boolean blinds) {
		super(startingPosition, handPlayers);
		totalToCall = 0;
		standingRaise = 0;
		this.allInPlayers = new ArrayList<HandPlayer>();
		this.pot = pot;
		if(blinds){
			putInBlinds();
		}
	}

	private void putInBlinds() {
		List<HandPlayer> handPlayers = getHandPlayers();
		int small = getSmallLimit();
		int big = getBigLimit();
		if(handPlayers.size() == 2){
			handPlayers.get(0).addToPot(small);
			handPlayers.get(1).addToPot(big);
			totalToCall+=big;
			standingRaise = big;
		}
		else{
			handPlayers.get(1).addToPot(small);
			handPlayers.get(2).addToPot(big);
			totalToCall+=big;
			standingRaise = big;
		}
	}

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
		checkIfComplete();
		setNextPlayer();
	}

	public BetAction getAction(){
		Scanner scanner = new Scanner(System.in);
		System.out.println(activePlayer.getName()+"(m/r/f)");
		String response = scanner.nextLine().trim();
		
		int amount  = 0;
		BetActionType betType = null;
		if(response.equals("m")){
			betType = BetActionType.MATCH;
		}
		else if(response.equals("r")){
			betType = BetActionType.RAISE;
			System.out.println(activePlayer.getName()+" Amount:");
			amount = scanner.nextInt();
		}
		else if(response.equals("f")){
			betType = BetActionType.FOLD;
		}
		else{
			getAction();
		}
		return new BetAction(this, activePlayer, betType, amount);
	}
	
	private void checkIfComplete() {
		this.setComplete(this.allPlayersHaveActed());
		int counter = findHowManyPlayersPlaying();
		if(counter==1){
			setComplete(true);
			getHand().setEndConditionMet(true);
			finishRound();
		}
		else if(complete){
			finishRound();
		}
	}

	private int findHowManyPlayersPlaying(){
		int counter = 0;
		for(HandPlayer player: handPlayers){
			if(player.getHandStatus() == HandStatus.PLAYING){
				counter++;
			}
		}
		return counter;
	}
	
	private void evaluateFold(HandPlayer player){
		player.setHandStatus(HandStatus.FOLDED);
		currentPosition--;
		pot.removePlayerFromPot(player);
	}
	
	private void evaluateMatch(HandPlayer player){
		int amount = getAmountToCall(player);
		if(playerIsAllIn(player,amount)){
			amount = player.getTableBankroll() - player.getAmountCommittedToRound();
			putPlayerAllIn(player);
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
		if(playerIsAllIn(player,amount)){
			putPlayerAllIn(player);
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
	
	private boolean playerIsAllIn(HandPlayer player, int amount){
		return amount>=player.getTableBankroll();
	}
	private void putPlayerAllIn(HandPlayer player){
		player.setHandStatus(HandStatus.ALL_IN);
		//Sorts All In Players Lowest Committed to Highest (for correct side pots)
		boolean committedMore;
		boolean found = false;
		for(HandPlayer allInPlayer: allInPlayers){
			committedMore =  allInPlayer.getAmountCommittedToRound()>player.getAmountCommittedToRound();
			if(committedMore){
				int index = allInPlayers.indexOf(allInPlayer);
				allInPlayers.add(index,player);
				found = true;
				break;
			}
		}
		if(!found){
			allInPlayers.add(player);
		}
	}

	private boolean allPlayersHaveActed(){
		for(HandPlayer player : getHandPlayers()){
			if(!player.hasActed()){
				return false;
			}
		}
		return true;
	}
	
	private void finishRound(){
		for(HandPlayer player: allInPlayers){
			pot.addSidePot(player, handPlayers);
		}
		for(HandPlayer player: handPlayers){
			player.resetForNewRound();
		}
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
		int amount = totalToCall - player.getAmountCommittedToRound();
		if(amount<0){
			throw new IllegalArgumentException("Amount to call is getting set incorrectly");
		}
		return amount;
	}
	
	public int getMinimumRaiseAmount(HandPlayer player){
		if(standingRaise!=0){
			return standingRaise;
		}
		//TODO not chill for Limit Games needs revising
		return getBigLimit();
	}
	
	public Pot getPot() {
		return pot;
	}

	
	public int getStandingRaise() {
		return standingRaise;
	}
	
	private int getBigLimit(){
		return getHandPlayers().get(0).getTablePlayer().getTable().getBigLimit();
	}
	
	private int getSmallLimit(){
		return getHandPlayers().get(0).getTablePlayer().getTable().getSmallLimit();
	}
	
	@Override
	public String toString() {
		String s = super.toString();
		s+="\nStanding Raise: "+standingRaise;
		s+="\tTotal to Call: "+totalToCall;
		return s;
	}
}
