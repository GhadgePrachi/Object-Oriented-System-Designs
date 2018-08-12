package DesignSystems.ObjectOrientedDesignCentric.Games.CardGames.Poker;

import java.util.Arrays;
public class Card implements Comparable<Card> {

	int rank;
	int suit;

	private static String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
	private static String[] suits = {"H", "S", "C", "D"};

	public Card(char rank, char suit) {
		this.rank = ((int) Arrays.asList(ranks).indexOf(String.valueOf(rank))) + 2;
		this.suit = ((int) Arrays.asList(suits).indexOf(String.valueOf(suit))) + 1;

	}

	public int getRank() {
		return rank;
	}

	public int getSuit() {
		return suit;
	}

	public int compareTo(Card compareCard) {
		//ascending order
		return this.getRank() - compareCard.getRank();
	}
}



