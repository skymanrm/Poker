package action;

import java.util.List;

import player.HandPlayer;

import round.Round;

import card.Card;


public class DrawAction extends Action {

	private final List<Card> discards;
	
	public DrawAction(Round<DrawAction> round, HandPlayer handPlayer, List<Card> discards) {
		super(round, handPlayer);
		this.discards = discards;
	}

	public List<Card> getDiscards() {
		return discards;
	}
}
