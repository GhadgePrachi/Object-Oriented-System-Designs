package DesignSystems.ObjectOrientedDesignCentric.Games.FlipGame;

import java.util.*;

public class Leetcode_Q294_FlipGame {
    /**Approach 1 : Brute Force **/
    public boolean canWinNaive(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        HashMap<String, Boolean> winMap = new HashMap<String, Boolean>();
        return helper(s, winMap);
    }

    public boolean helper(String s, HashMap<String, Boolean> winMap) {
        if (winMap.containsKey(s)) {
            return winMap.get(s);
        }
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.startsWith("++", i)) {
                String t = s.substring(0, i) + "--" + s.substring(i + 2);
                if (!helper(t, winMap)) {
                    winMap.put(s, true);
                    return true;
                }
            }
        }
        winMap.put(s, false);
        return false;
    }

        /**Approach 2 :With memoization**/
    public boolean canWin(String s) {
        if(s == null || s.length() < 2) return false;

        Set<String> winSet = new HashSet<String>();
        return canWin(s, winSet);
    }

    public boolean canWin(String s, Set<String> winSet){
        if(winSet.contains(s)) return true;

        for(int i = 0; i < s.length() - 1; i++) {
            if(s.charAt(i) == '+' && s.charAt(i + 1) == '+') {

                String sOpponent = s.substring(0, i) + "--" + s.substring(i + 2);
                if(!canWin(sOpponent, winSet)) {
                    winSet.add(s);
                    return true;
                }
            }
        }
        return false;

    }

}
