package table;

import player.Player;

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
	
	public static void main(String[] args){
		HandTester.showHandWithList(HandTester.getRandom(5));
		
		//doTableStuff();
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
