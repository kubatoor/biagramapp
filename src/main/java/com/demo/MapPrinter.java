package com.demo;

import java.util.Map;

public class MapPrinter {

    /**
     * Responsible for printing the input Map
     * to the ouput stream
     *
     * @param inputMap
     */
    public void printMap(Map<String, Integer> inputMap){
        if(inputMap ==null || inputMap.isEmpty()){
            System.out.println("The input map is null or Empty. Cannot print");
            return;
        }

        inputMap.forEach((k,v) -> System.out.println(k+": "+v));
    }

}
