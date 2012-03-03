package table;

import static org.junit.Assert.assertEquals;
import handranking.FormedHand;

import java.util.List;

import card.Card;
import player.Player;
import test.HandTester;

/*
 * What happens at an 8 seat 7 Card Stud table if everyone stays in until Seventh Street? 
 * Officially in this case Seventh Street is dealt as a community card. 
 */

/*
 * It is possible that the deck of unused cards, the stub, will run out. 
 * This means there are not enough remaining cards to replace every player's discarded ones. 
 * In this case, all of the discarded cards will be reshuffled into a new stub.
 */

public class Deal {
	
	private List<Card> cards;
	private FormedHand fh;
	private FormedHand fh2;
	
	public static void main(String[] args){
		//HandTester.showRandomHands(7, 7);
		//doTableStuff();
		List<Card> cards;
		FormedHand fh;
		FormedHand fh2;
		
		cards = HandTester.formatCards("KS,KC,KH,KD,AH");
		fh = new FormedHand(cards);
	
		cards = HandTester.formatCards("JS,JC,JH,AH,KH,KD,AD");
		fh2 = new FormedHand(cards);
		
		int compare = fh.compareTo(fh2);
		assertEquals("Result", compare, -1);
	}
	
	public static void doTableStuff(){
		Player player1 = new Player("Logan", 100);
		Player player2 = new Player("Morgan", 100);
		Player player3 = new Player("Colbert", 100);
		
		Table table = new Table(3, GameType.HOLDEM, 1, 2);
		table.addPlayer(player1, 100, 0, TableStatus.PLAYING);
		table.addPlayer(player2, 100, 1, TableStatus.PLAYING);
		table.addPlayer(player3, 100, 2, TableStatus.PLAYING);
		
		System.out.println(table.toString());
		Hand hand = table.newHand();
		System.out.println(hand.toString());
	}
}
