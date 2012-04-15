package card;

import java.util.Scanner;

import action.BetAction;
import action.BetType;

import hand.Hand;
import hand.Player;
import hand.Round;
import hand.Table;

public class HandSimulator {

	private static Player p1 = new Player("KingScam",100);
	private static Player p2 = new Player("LordScam",100);
	private static Player p3 = new Player("Colbert",100);
	
	public static void main(String[] args) {
		Table table = new Table(3);
		table.addPlayer(p1, 0);
		table.addPlayer(p2, 1);
		table.addPlayer(p3,2);
		while(true){
			Hand hand = table.newHand();
			while(hand.startNextRound()==true){
				Round round = hand.getActiveRound();
				round.getNextPlayer();
				for(DealtCard card: hand.getCommunityCards()){
					System.out.print(card.toString());
				}
				System.out.println();
				while(round.getActivePlayer()!=null &&!round.isKillable()){
					System.out.println(round.getActivePlayer().toString());
					for(DealtCard card: hand.getHandStates().get(round.getActivePlayer()).getCards()){
						System.out.print(card.toString());
					}
					BetAction betAction = HandSimulator.getBetAction(round.getActivePlayer());
					System.out.println(betAction.toString());
					round.evaluateAction(betAction);
				}
				System.out.println("New Round");
			}
			hand.payOut();
			System.out.println("Hand Over");
		}
	}
	
	public static BetAction getBetAction(Player activePlayer){
		Scanner sc = new Scanner(System.in);
		System.out.println(" c/r/f");
		String result = sc.nextLine();
		BetType betType;
		int amount = 0;
		if(result.equalsIgnoreCase("c")){
			betType = BetType.CALL;
		}
		else if(result.equalsIgnoreCase("r")){
			betType = BetType.RAISE;
			amount = 2;
		}
		else if(result.equalsIgnoreCase("f")){
			betType = BetType.FOLD;
		}
		else{
			return getBetAction(activePlayer);
		}
		return new BetAction(activePlayer, System.currentTimeMillis(), betType, amount);
	}

}
