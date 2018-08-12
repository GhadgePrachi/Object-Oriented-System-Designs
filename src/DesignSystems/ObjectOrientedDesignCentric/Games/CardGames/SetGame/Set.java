package DesignSystems.ObjectOrientedDesignCentric.Games.CardGames.SetGame;

import java.util.ArrayList;

public class Set {
    ArrayList<Card> set ;//3 Cards
    SetType setType;

    public Set() {
        set = new ArrayList<Card>();
    }

    public ArrayList<Card> getCards() {
        return this.set;
    }

    public void setCards(ArrayList<Card> cards) {
        this.set = cards;
    }

    public SetType getSetType() {
        if(setHasAllDifferentConfiguration()){
            return setType.AllDifferent;
        }
        return setType.None;
    }

    public boolean setHasAllDifferentConfiguration(){
        //TODO : XoR with all cards of set and enum values() after redefining color,shading,shapes as enum using values(), ordinal(), valueOf()
        return true;
    }
}
