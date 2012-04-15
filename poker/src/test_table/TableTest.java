package test_table;

import static org.junit.Assert.*;

import hand.Hand;
import hand.Player;
import hand.Round;
import hand.Table;

import org.junit.Test; 

import action.BetAction;
import action.BetType;

public class TableTest {

	private Player p1 = new Player("KingScam",100);
	private Player p2 = new Player("LordScam",100);
	private Player p3 = new Player("CantPlayScam",100);
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddingPlayerToOccupiedSeat() throws IllegalArgumentException{
		Table table = new Table(2);
		table.addPlayer(p1, 0);
		table.addPlayer(p2, 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRemovingPlayerFromUnoccupiedSeat() throws IllegalArgumentException{
		Table table = new Table(2);
		table.removePlayer(0);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testAddingTooManyPlayers() throws IllegalArgumentException{
		Table table = new Table(2);
		table.addPlayer(p1, 0);
		table.addPlayer(p2, 1);
		table.addPlayer(p3, 2);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemovingFromBadIndex() throws IllegalArgumentException{
		Table table = new Table(2);
		table.removePlayer(-1);
	}
	
	@Test
	public void testMovingButton(){
		Table table = new Table(2);
		table.addPlayer(p1, 0);
		table.addPlayer(p2, 1);
		table.newHand();
		assertEquals("Result",table.getButtonSeat(),table.getSeat(0));
		table.newHand();
		assertEquals("Result",table.getButtonSeat(),table.getSeat(1));
		table.newHand();
		assertEquals("Result",table.getButtonSeat(),table.getSeat(0));
	}
	
	@Test
	public void testPlayersPosition(){
		Table table = new Table(2);
		table.addPlayer(p1, 0);
		table.addPlayer(p2, 1);
		Hand hand = table.newHand();
		assertEquals("Results",p1,hand.getPlayer(0));
		hand = table.newHand();
		assertEquals("Results",p2,hand.getPlayer(0));
		hand = table.newHand();
		assertEquals("Results",p1,hand.getPlayer(0));
	}
	
	@Test
	public void testHandToHandFidelity(){
		Table table = new Table(3);
		table.addPlayer(p1, 0);
		table.addPlayer(p2, 1);
		table.addPlayer(p3,2);
		Hand hand = table.newHand();
		//Round 1
		hand.startNextRound();
		Round round = hand.getActiveRound();
		round.getNextPlayer();
		BetAction betAction = new BetAction(p1, System.currentTimeMillis(), BetType.BET, 10);
		round.evaluateAction(betAction);
		betAction = new BetAction(p2, System.currentTimeMillis(),BetType.CALL,10);
		round.evaluateAction(betAction);
		betAction = new BetAction(p3, System.currentTimeMillis(),BetType.FOLD,0);
		round.evaluateAction(betAction);
		//Round 1 Tests
		boolean nextPlayerIsNull = (round.getNextPlayer()==null);
		assertEquals("Results",nextPlayerIsNull,true);
		assertEquals("Results",round.getHand().getPotValue(),20);
		assertEquals("Results",p1.getBankroll(),90);
		assertEquals("Results",p2.getBankroll(),90);
		assertEquals("Results",p3.getBankroll(),100);
		assertEquals("Results",hand.getPlayers().contains(p3),false);
		//Round 2
		hand.startNextRound();
		Round round2 = hand.getActiveRound();
		round2.getNextPlayer();
		assertEquals("Results",round2.isKillable(),false);
		betAction = new BetAction(p1, System.currentTimeMillis(), BetType.BET, 10);
		round2.evaluateAction(betAction);
		betAction = new BetAction(p2, System.currentTimeMillis(),BetType.FOLD,0);
		round2.evaluateAction(betAction);
		//Round 2 Tests
		boolean nextPlayerIsNull2 = (round2.getNextPlayer()==null);
		assertEquals("Results",nextPlayerIsNull2,true);
		assertEquals("Results",round2.isKillable(),true);
		//Pay Out Tests
		hand.payOut();
		hand = table.newHand();
		hand.startNextRound();
		round = hand.getActiveRound();
		round.getNextPlayer();
		assertEquals("Results",round.getActivePlayer(),p2);
		assertEquals("Results",p1.getBankroll(),110);
		assertEquals("Results",p2.getBankroll(),90);
		assertEquals("Results",p3.getBankroll(),100);
	}
}
