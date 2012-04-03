package test_table;

import static org.junit.Assert.*;
import hand.Player;
import hand.Table;

import org.junit.Test; 

public class TableTest {

	@Test(expected=IllegalArgumentException.class)
	public void testAddingPlayerToOccupiedSeat() throws IllegalArgumentException{
		Table table = new Table(2);
		Player p1 = new Player("KingScam",100);
		Player p2 = new Player("LordScam",100);
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
		Player p1 = new Player("KingScam",100);
		Player p2 = new Player("LordScam",100);
		Player p3 = new Player("CantPlayScam",100);
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
		Player p1 = new Player("KingScam",100);
		Player p2 = new Player("LordScam",100);
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
		
	}
}
