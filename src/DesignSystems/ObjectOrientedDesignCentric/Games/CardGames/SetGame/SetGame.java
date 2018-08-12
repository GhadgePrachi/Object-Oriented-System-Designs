package DesignSystems.ObjectOrientedDesignCentric.Games.CardGames.SetGame;

import java.util.ArrayList;

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

    }

}
