package com.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BiagramCounter {

    /**
     * This method has the logic to identify the histogram of Biagrams.
     * The method accepts a list of words and then provides a Map output
     * containing the biagrams as the key and the corresponding counts
     * as the value
     *
     *
     * @param wordsList An arraylist of words
     * @return Map containing the biagrams and the count as key-value pairs
     */
    public Optional<Map<String, Integer>> getBiagramCountsMap(List<String> wordsList){
        Map<String, Integer> biagramMap = new HashMap<>();
        if(wordsList == null ) {
            return Optional.empty();
        }
        if (wordsList.size() <= 1) {
            System.out.println("Cannot construct Biagrams with the given words. Should be atleast 2 words");
            return Optional.empty();
        }
        for(int i = 1; wordsList.size() > i; i++){
            String key = (wordsList.get(i-1)+" "+wordsList.get(i)).toLowerCase();
            if(biagramMap.containsKey(key)){
                biagramMap.put(key, biagramMap.get(key) + 1);
            } else {
                biagramMap.put(key, 1);
            }

        }

        return Optional.of(biagramMap);

    }


}
