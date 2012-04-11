package test_table;

import static org.junit.Assert.assertEquals;
import hand.Hand;
import hand.Player;
import hand.Round;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import action.BetAction;
import action.BetType;

public class RoundTest {

	private Player p1 = new Player("KingScam",100);
	private Player p2 = new Player("LordScam",100);
	private Player p3 = new Player("Wiz",100);
	
	private Round getTestRound(){
		List<Player> players = new ArrayList<Player>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
		return new Hand(players).getActiveRound();
	}
	
	@Test
	public void testNextPlayer() {
		Round round = getTestRound();
		assertEquals("Results",p1,round.getNextPlayer());
		assertEquals("Results",p2,round.getNextPlayer());
		assertEquals("Results",p3,round.getNextPlayer());
		assertEquals("Results",p1,round.getNextPlayer());
	}

	@Test
	public void testFoldingPlayer(){
		Round round = getTestRound();
		BetAction betAction = new BetAction(round.getNextPlayer(), System.currentTimeMillis(), BetType.FOLD, 0);
		round.evaluateAction(betAction);
		assertEquals("Results",Boolean.FALSE, round.getPlayers().contains(p1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWrongPersonBetting(){
		Round round = getTestRound();
		round.getNextPlayer();
		BetAction betAction = new BetAction(p2, System.currentTimeMillis(), BetType.FOLD, 0);
		round.evaluateAction(betAction);
	}
	
	@Test
	public void testAmountToCall(){
		Round round = getTestRound();
		round.getNextPlayer();
		BetAction betAction = new BetAction(p1, System.currentTimeMillis(), BetType.BET, 10);
		round.evaluateAction(betAction);
		assertEquals("Results",10,round.getAmountToCall());
		BetAction betAction2 = new BetAction(p2, System.currentTimeMillis(),BetType.RAISE,10);
		round.evaluateAction(betAction2);
		assertEquals("Results",20,round.getAmountToCall());
	}
	
	@Test
	public void testResettingTurnAfterBets(){
		Round round = getTestRound();
		round.getNextPlayer();
		BetAction betAction = new BetAction(p1, System.currentTimeMillis(), BetType.BET, 10);
		round.evaluateAction(betAction);
		BetAction betAction2 = new BetAction(p2, System.currentTimeMillis(),BetType.RAISE,10);
		round.evaluateAction(betAction2);
		BetAction betAction3 = new BetAction(p3, System.currentTimeMillis(),BetType.CALL,20);
		round.evaluateAction(betAction3);
		assertEquals("Results",round.getRoundStateForPlayer(p1).hasActed(),false);
		assertEquals("Results",round.getRoundStateForPlayer(p2).hasActed(),true);
		assertEquals("Results",round.getRoundStateForPlayer(p3).hasActed(),true);
	}
	
	@Test
	public void testKillable(){
		Round round = getTestRound();
		round.getNextPlayer();
		BetAction betAction = new BetAction(p1, System.currentTimeMillis(), BetType.BET, 10);
		round.evaluateAction(betAction);
		BetAction betAction2 = new BetAction(p2, System.currentTimeMillis(),BetType.RAISE,10);
		round.evaluateAction(betAction2);
		BetAction betAction3 = new BetAction(p3, System.currentTimeMillis(),BetType.FOLD,0);
		round.evaluateAction(betAction3);
		BetAction betAction4 = new BetAction(p1, System.currentTimeMillis(), BetType.FOLD, 0);
		round.evaluateAction(betAction4);
		assertEquals("Results",round.isKillable(),true);
	}
	
	@Test
	public void testRoundCompletion(){
		Round round = getTestRound();
		round.getNextPlayer();
		BetAction betAction = new BetAction(p1, System.currentTimeMillis(), BetType.BET, 10);
		round.evaluateAction(betAction);
		BetAction betAction2 = new BetAction(p2, System.currentTimeMillis(),BetType.RAISE,10);
		round.evaluateAction(betAction2);
		BetAction betAction3 = new BetAction(p3, System.currentTimeMillis(),BetType.CALL,20);
		round.evaluateAction(betAction3);
		BetAction betAction4 = new BetAction(p1, System.currentTimeMillis(), BetType.CALL, 10);
		round.evaluateAction(betAction4);
		boolean nextPlayerIsNull = (round.getNextPlayer()==null);
		assertEquals("Results",nextPlayerIsNull,true);
		assertEquals("Results",round.getHand().getPotValue(),60);
		assertEquals("Results",p1.getBankroll(),80);
		assertEquals("Results",p2.getBankroll(),80);
		assertEquals("Results",p3.getBankroll(),80);
	}
}
