package com.leetcode.wordladder2;

import java.util.*;

/**
 * Created by titan-developer on 11/22/14.
 */
public class WordLadder23 {

    public static void main(String[] strings) {
        WordLadder23 wordLadder = new WordLadder23();

        String start = "hit";
        String end = "cog";

        HashSet<String> hashSet = new HashSet<String>();
        hashSet.add("hot");
        hashSet.add("dot");
        hashSet.add("dog");
        hashSet.add("lot");
        hashSet.add("log");
        hashSet.add("cog");

        ArrayList<ArrayList<String>> result = wordLadder.findLadders(start, end, hashSet);

        for (List<String> list : result) {
            for (String str : list) {
                System.out.print(str + ", ");
            }
            System.out.println();
        }
    }

    /* Graph of example:
     *                |--- dot --- dog ---|
     * hit --- hot -- |     |       |     |--- cog
     *                |--- lot --- log ---|
     *
     * backward adjacent list:
     * hit => null
     * hot => hit
     * dot => hot
     * lot => hot
     * dog => dot
     * log => lot
     * cog => dot -- log
     */
    public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
        //<String, Queue> contaion all the adjacent words that is discover in its previous level
        HashMap<String, Queue<String>> adjMap = new HashMap<String, Queue<String>>();
        int currLen = 0;
        boolean found = false;
        ArrayList<ArrayList<String>> r = new ArrayList<ArrayList<String>>();//results
        Queue<String> queue = new LinkedList<String>();                     //queue for BFS
        Set<String> unVisited = new HashSet<String>(dict);                  //unvisited words
        Set<String> visitedThisLev = new HashSet<String>();                 //check words visited during same level
        unVisited.add(end);

        queue.offer(start);
        int currLev = 1;
        int nextLev = 0;


        for(String word : unVisited){
            adjMap.put(word, new LinkedList<String>());
        }
        unVisited.remove(start);


        //BFS
        while( !queue.isEmpty() ){
            String currLadder = queue.poll();
            //for all unvisited words that are one character change from current word
            for(String nextLadder : getNextLadder(currLadder, unVisited)){
                if(visitedThisLev.add(nextLadder)) {
                    nextLev ++;
                    queue.offer(nextLadder);
                }
                adjMap.get(nextLadder).offer(currLadder);
                if(nextLadder.equals(end) && !found) { found = true; currLen+=2;}
            }

            if(--currLev == 0){
                if(found) break;
                unVisited.removeAll(visitedThisLev);
                currLev = nextLev;
                nextLev = 0;
                currLen ++;
            }
        }

        if(found){
            LinkedList<String> p =new LinkedList<String>();
            p.addFirst(end);
            getLadders(start, end, p , r, adjMap, currLen);
        }

        return r;
    }

    //get all unvisited words that are one character change from current word
    private ArrayList<String> getNextLadder(String currLadder, Set<String> unVisited){
        ArrayList<String> nextLadders = new ArrayList<String>();
        StringBuffer replace = new StringBuffer(currLadder);
        for(int i = 0; i < currLadder.length(); i++){
            char old = replace.charAt(i);
            for(char ch = 'a'; ch <= 'z'; ch++){
                replace.setCharAt(i, ch);
                String replaced = replace.toString();
                if(ch != currLadder.charAt(i) && unVisited.contains(replaced) ){
                    nextLadders.add(replaced);
                }
            }
            replace.setCharAt(i, old);
        }
        return nextLadders;
    }

    // DFS to get all possible path from start to end
    private void getLadders(String start, String currLadder, LinkedList<String> p, ArrayList<ArrayList<String>> solu,
                            HashMap<String, Queue<String>> adjMap, int len){
        if(currLadder.equals(start)){
            solu.add(new ArrayList<String>(p));
        }
        else if(len > 0) {
            Queue<String> adjs = adjMap.get(currLadder);
            for(String lad : adjs){
                p.addFirst(lad);
                getLadders(start, lad, p, solu, adjMap, len-1);
                p.removeFirst();
            }
        }
    }
}
