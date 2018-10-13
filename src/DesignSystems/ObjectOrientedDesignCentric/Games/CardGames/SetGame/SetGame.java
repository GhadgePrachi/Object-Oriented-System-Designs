package DesignSystems.ObjectOrientedDesignCentric.Games.CardGames.SetGame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SetGame {
    final static String[] shapes = new String[]{"Diamond","Squiggle", "Oval"};
    final static String[] shadings = new String[]{"Striped", "Open", "Solid"};
    final static String[] colors = new String[]{"Green","Purple","Red"};
    final static int maxShapeCount = 3;
    ArrayList<Card> deckOfCards;
    ArrayList<Card> hand ;

    public SetGame(){
        deckOfCards = new ArrayList<>();
        hand = new ArrayList<>();//initial 12 Cards
        initializeDeckOfCards();
    }

    public void initializeDeckOfCards(){
        for(int i=0;i<shapes.length;i++){
            for(int j=0;j<shadings.length;j++){
                for(int k=0;k<colors.length;k++){
                    for(int l=1;i<=maxShapeCount;l++){
                        String shape = shadings[i];
                        String shading = shadings[j];
                        String color = colors[k];

                        Card card = new Card(shape,shading,color,l);
                        deckOfCards.add(card);
                    }
                }
            }
        }
    }

    public void shuffleCards(){
        //REFER CTC 17.02
    }

    public boolean isValidSet(Card[] input){
        int cardCount = input.length;
        Set<Integer> numbers = new HashSet<>();
        Set<String> shapes = new HashSet<>();
        Set<String> colors = new HashSet<>();
        Set<String> shadings = new HashSet<>();

        for(Card card:input){
            String shape = card.shape;
            String shading = card.color;
            String color = card.color;
            int number = card.number;

            shapes.add(shape);
            shadings.add(shading);
            colors.add(color);
            numbers.add(number);
        }

        if((numbers.size()==1  && shadings.size()==1 && shapes.size()==1 && colors.size()==1) ||
           (numbers.size()==cardCount  && shadings.size()==cardCount && shapes.size()==cardCount && colors.size()==cardCount))
            return true;
        return false;

    }
}
