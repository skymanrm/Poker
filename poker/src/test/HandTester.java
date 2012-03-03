package test;

import handranking.FormedHand;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import card.Card;
import card.Rank;
import card.Suit;

public class HandTester {

	public static void showRandomHands(int numberOfCards, int times){
		for(int i = 0; i<times; i++){
			HandTester.showHandWithList(getRandom(numberOfCards));
		}
	}
	public static void showHandWithList(List<Card> cards){
		long start = System.nanoTime();
		FormedHand fh = new FormedHand(cards);
		long end = System.nanoTime();
		System.out.println("Time: "+(end-start));
		System.out.println(fh.toString());
	}
	public static List<Card> getRandom(int number){
		Stack<Card> cards = Card.newDeck();
		List<Card> list = new ArrayList<Card>();
		for(int i = 0; i <number;i++){
			list.add(cards.pop());
		}
		return list;
	}
	/**
	 * as,ks,qs,js,10s
	 * @param s
	 * @return
	 */
	public static List<Card> formatCards(String s){
		List<Card> cards = new ArrayList<Card>();
		String[] cardStrings = s.split(",");
		Rank foundRank = null;
		Suit foundSuit = null;
		for(String str: cardStrings){
			for(Suit suit: Suit.values()){
				if(str.contains(suit.shortName)){
					foundSuit = suit;
					break;
				}
			}
			for(Rank rank: Rank.values()){
				if(str.contains(rank.shortName)){
					foundRank = rank;
					break;
				}
			}
			cards.add(new Card(foundRank,foundSuit));
		}
		return cards;
	}
}
