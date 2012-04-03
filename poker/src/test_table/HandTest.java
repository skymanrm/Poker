package test_table;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import hand.Hand;
import hand.Player;

import org.junit.Test;

public class HandTest {

	private Player p1 = new Player("KingScam",100);
	private Player p2 = new Player("LordScam",100);
	private Player p3 = new Player("Wiz",100);
	
	@Test
	public void testNextPlayer() {
		List<Player> players = new ArrayList<Player>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
		Hand hand = new Hand(players);
		assertEquals("Results",p1,hand.getNextPlayer());
		assertEquals("Results",p2,hand.getNextPlayer());
		assertEquals("Results",p3,hand.getNextPlayer());
		assertEquals("Results",p1,hand.getNextPlayer());
	}

}
