package DesignSystems.ObjectOrientedDesignCentric.Games.CardGames.Poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Hand {
	ArrayList<Card> cards;//5 Cards
	HashMap<Integer, Integer> rankMap;
	HandType handtype;
	int pairRank = 0;

	public Hand() {
		cards = new ArrayList<Card>();
	}

	public ArrayList<Card> getCards() {
		return this.cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public int getpairRank() {
		return this.pairRank;
	}


	public HandType getHandType() {
		if (cardsFromSameSuit()) {
			if (isRoyalFlush()) {
				this.handtype = HandType.RoyalFlush;
				return HandType.RoyalFlush;//case 10
			}

			if (cardsInSequence()) {
				this.handtype = HandType.StraightFlush;
				return HandType.StraightFlush;//case9
			} else {
				this.handtype = HandType.Flush;
				return HandType.Flush;//case6
			}
		}

		if (hasSameCard(4)) {
			this.handtype = HandType.FourOfAKind;
			return HandType.FourOfAKind;//case 8
		}

		if (hasSameCard(3) && hasPairs(1)) {
			this.handtype = HandType.FullHouse;
			return HandType.FullHouse;//case 7
		}

		if (cardsInSequence()) {
			this.handtype = HandType.Straight;
			return HandType.Straight;//case 5
		}

		if (hasSameCard(3) && !hasPairs(1)) {
			this.handtype = HandType.ThreeOfAKind;
			return HandType.ThreeOfAKind;//case 4
		}

		if (hasPairs(2)) {
			this.handtype = HandType.TwoPairs;
			return HandType.TwoPairs;//case 3
		}
		if (hasPairs(1)) {
			this.handtype = HandType.OnePair;
			return HandType.OnePair;//case 2
		}

		this.handtype = HandType.HighCard;
		return HandType.HighCard;//case 1
	}

	public boolean hasPairs(int numberOfPairs) {
		int pairs = 0;
		if (this.rankMap == null) {
			populateHashMap();
		}

		for (Map.Entry<Integer, Integer> entry : rankMap.entrySet()) {
			int currentPairRank;
			if (entry.getValue() >= 2) {
				currentPairRank = (int) entry.getKey();
				if (currentPairRank > this.pairRank) {
					this.pairRank = currentPairRank;
				}
				//if four cards of same value-it should return 2 pairs and not just 1 pair
				pairs = pairs + (((int) entry.getValue()) / 2);
			}
		}

		if (pairs == numberOfPairs) {
			return true;
		} else {
			return false;
		}
	}

	public boolean hasSameCard(int numberOfCards) {
		if (this.rankMap == null) {
			populateHashMap();
		}

		for (Map.Entry<Integer, Integer> entry : rankMap.entrySet()) {
			if (entry.getValue() == numberOfCards) {
				return true;
			}
		}
		return false;

	}

	public boolean isRoyalFlush() {
		int royalCard = 10;//(from 10 to 14)

		for (Card currentCard : cards) {
			if (currentCard.getRank() < royalCard) {
				return false;
			}
		}
		return true;

	}

	public boolean cardsInSequence() {
		//cards sorted
		sortCard();

		int idealDifference = 1;
		Card card = null;

		for (Card currentCard : cards) {
			if (card != null) {
				int diff = currentCard.getRank() - card.getRank();
				if (diff != idealDifference) {
					return false;
				}
			}
			//save previous card
			card = currentCard;
		}
		return true;
	}

	public boolean cardsFromSameSuit() {
		Card card = null;

		for (Card currentCard : cards) {
			if (card != null && !(card.getSuit() == currentCard.getSuit())) {
				return false;
			} else {
				//set first card
				card = currentCard;
			}
		}
		return true;
	}

	public int compareTo(Hand hand) {
		if (this.getHandType().ordinal() > hand.getHandType().ordinal()) {
			return 1;
		} else if (this.getHandType().ordinal() < hand.getHandType().ordinal()) {
			return -1;
		}
		return 0;
	}

	public void populateHashMap() {
		this.rankMap = new HashMap<Integer, Integer>();
		for (Card currentCard : cards) {
			int rank = currentCard.getRank();
			if (this.rankMap.containsKey(rank)) {
				int value = this.rankMap.get(rank);
				this.rankMap.put(rank, value + 1);
			} else {
				this.rankMap.put(rank, 1);
			}
		}
	}

	public void sortCard() {
		Collections.sort(this.cards);
	}
}
