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

public class HandTest {

	private Player p1 = new Player("KingScam",100);
	private Player p2 = new Player("LordScam",100);
	private Player p3 = new Player("Wiz",100);
	
	private Hand getPlayersInHand(){
		List<Player> players = new ArrayList<Player>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
		return new Hand(players);
	}
	
	@Test
	public void testNextPlayer() {
		Hand hand = getPlayersInHand();
		Round round = hand.getActiveRound();
		assertEquals("Results",p1,round.getActivePlayer());
		assertEquals("Results",p2,round.getNextPlayer());
		assertEquals("Results",p3,round.getNextPlayer());
		assertEquals("Results",p1,round.getNextPlayer());
	}

	@Test
	public void testFoldingPlayer(){
		Hand hand = getPlayersInHand();
		Round round = hand.getActiveRound();
		BetAction betAction = new BetAction(p1, System.currentTimeMillis(), BetType.FOLD, 0);
		round.evaluateAction(betAction);
		assertEquals("Results",Boolean.FALSE, round.getPlayers().contains(p1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWrongPersonBetting(){
		Hand hand = getPlayersInHand();
		Round round = hand.getActiveRound();
		BetAction betAction = new BetAction(p2, System.currentTimeMillis(), BetType.FOLD, 0);
		round.evaluateAction(betAction);
	}
	
	@Test
	public void testAmountToCall(){
		Hand hand = getPlayersInHand();
		Round round = hand.getActiveRound();
		BetAction betAction = new BetAction(p1, System.currentTimeMillis(), BetType.BET, 10);
		round.evaluateAction(betAction);
		assertEquals("Results",10,round.getAmountToCall());
		BetAction betAction2 = new BetAction(p2, System.currentTimeMillis(),BetType.RAISE,10);
		round.evaluateAction(betAction2);
		assertEquals("Results",20,round.getAmountToCall());
	}
	
	@Test
	public void testResettingTurnAfterBets(){
		Hand hand = getPlayersInHand();
		Round round = hand.getActiveRound();
		BetAction betAction = new BetAction(p1, System.currentTimeMillis(), BetType.BET, 10);
		round.evaluateAction(betAction);
		BetAction betAction2 = new BetAction(p2, System.currentTimeMillis(),BetType.RAISE,10);
		round.evaluateAction(betAction2);
		BetAction betAction3 = new BetAction(p3, System.currentTimeMillis(),BetType.CALL,20);
		round.evaluateAction(betAction3);
		assertEquals("Results",p1.hasActed(),false);
		assertEquals("Results",p2.hasActed(),true);
		assertEquals("Results",p3.hasActed(),true);
	}
}
