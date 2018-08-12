package DesignSystems.ObjectOrientedDesignCentric.Games.CardGames.Poker;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class PokerGame {

	public static int[] getPlayerWinCounts(){
		int player1WinCount = 0;
		int player2WinCount = 0;

		/**Step 1 : Create JSON PARSER**/
		JSONParser parser = new JSONParser();
		Object object = null;

		try {
            try {
        /**Step 2 : Parse File using JSON PARSER**/
                object = parser.parse(new FileReader("poker.json"));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        /**Step 3 : Populate data from JSON PARSED OBJECT**/
        JSONArray jsonObject = (JSONArray) object;
			for (int i = 0; i < jsonObject.size(); i++) {
				String[] player1Array = new String[5];
				String[] player2Array = new String[5];
				Hand hand1 = new Hand();
				Hand hand2 = new Hand();
				ArrayList<Card> player1Cards = new ArrayList<Card>();
				ArrayList<Card> player2Cards = new ArrayList<Card>();


				JSONArray handsArray = (JSONArray) jsonObject.get(i);
				JSONArray temp1 = (JSONArray) handsArray.get(0);
                JSONArray temp2 = (JSONArray) handsArray.get(1);
				for (int j = 0; j < temp1.size(); j++) {
					player1Array[j] = temp1.get(j).toString();
				}
				for (int j = 0; j < temp2.size(); j++) {

					player2Array[j] = temp2.get(j).toString();
				}

				for (int k = 0; k < 5; k++) {
					char p1rank = player1Array[k].charAt(0);
					char p1suit = player1Array[k].charAt(1);
					Card player1card = new Card(p1rank, p1suit);
					player1Cards.add(player1card);

					char p2rank = player2Array[k].charAt(0);
					char p2suit = player2Array[k].charAt(1);
					Card player2card = new Card(p2rank, p2suit);
					player2Cards.add(player2card);
				}
				hand1.setCards(player1Cards);
				hand2.setCards(player2Cards);

		/**Step 4 : Determine which player's hand won and update player's win count**/
                if (hand1.compareTo(hand2) == 1) {
					player1WinCount++;
					continue;
				} else if (hand1.compareTo(hand2) == -1) {
					player2WinCount++;
					continue;
				} else {
					//tie
					//1.handle full house,one pair and two pair
					if ((hand1.handtype.ordinal() == 1 ||//one pair
							hand1.handtype.ordinal() == 2 ||//two pair
							hand1.handtype.ordinal() == 6))//full house
					{
						if (hand1.getpairRank() > hand2.getpairRank()) {
							player1WinCount++;
							continue;
						} else if (hand1.getpairRank() < hand2.getpairRank()) {
							player2WinCount++;
							continue;
						}

					}

					//2.handle tie for full house/1pair/2pair and others-pick the highest card and compare
					int max1 = 0;
					int max2 = 0;
					for (Card card : player1Cards) {
						int temp = card.getRank();
						if (temp >= max1)
							max1 = temp;
					}
					for (Card card : player2Cards) {
						int temp = card.getRank();
						if (temp >= max2)
							max2 = temp;
					}
					if (max1 > max2) {
						player1WinCount++;
						continue;
					} else if (max2 > max1) {
						player2WinCount++;
						continue;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new int[]{player1WinCount,player2WinCount};
	}
}
