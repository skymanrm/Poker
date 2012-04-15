package test_table;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import hand.Hand;
import hand.Player;
import hand.Round;

import org.junit.Test;

import action.BetAction;
import action.BetType;

public class HandTest {

	private Player p1 = new Player("KingScam",100);
	private Player p2 = new Player("LordScam",100);
	private Player p3 = new Player("Wiz",100);
	
	private Hand getTestHand(){
		List<Player> players = new ArrayList<Player>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
		return new Hand(players);
	}
	
	@Test
	public void testRoundToRoundFidelity() {
		
		Hand hand = getTestHand();
		hand.startNextRound();
		Round round = hand.getActiveRound();
		round.getNextPlayer();
		BetAction betAction = new BetAction(p1, System.currentTimeMillis(), BetType.BET, 10);
		round.evaluateAction(betAction);
		BetAction betAction2 = new BetAction(p2, System.currentTimeMillis(),BetType.CALL,10);
		round.evaluateAction(betAction2);
		BetAction betAction3 = new BetAction(p3, System.currentTimeMillis(),BetType.FOLD,0);
		round.evaluateAction(betAction3);
		boolean nextPlayerIsNull = (round.getNextPlayer()==null);
		assertEquals("Results",nextPlayerIsNull,true);
		assertEquals("Results",round.getHand().getPotValue(),20);
		assertEquals("Results",p1.getBankroll(),90);
		assertEquals("Results",p2.getBankroll(),90);
		assertEquals("Results",p3.getBankroll(),100);
		assertEquals("Results",hand.getPlayers().contains(p3),false);
		
		hand.startNextRound();
		Round round2 = hand.getActiveRound();
		round2.getNextPlayer();
		assertEquals("Results",round2.isKillable(),false);
		BetAction betAction4 = new BetAction(p1, System.currentTimeMillis(), BetType.BET, 10);
		round2.evaluateAction(betAction4);
		BetAction betAction5 = new BetAction(p2, System.currentTimeMillis(),BetType.FOLD,10);
		round2.evaluateAction(betAction5);
		boolean nextPlayerIsNull2 = (round2.getNextPlayer()==null);
		assertEquals("Results",nextPlayerIsNull2,true);
		assertEquals("Results",round2.isKillable(),true);
	}

}
