package com.demo;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class BiagramCounterTest {

    private BiagramCounter biagramCounter;

    @Before
    public void setUp() throws Exception {
        biagramCounter = new BiagramCounter();
    }

    @Test
    public void getBiagramCountsMapWithNullInput() throws Exception {
        final Optional<Map<String, Integer>> biagramCountsMap = biagramCounter.getBiagramCountsMap(null);
        assertFalse(biagramCountsMap.isPresent());
    }

    @Test
    public void getBiagramCountsMapWithValidInput() throws Exception {
        List<String> stringList = Arrays.asList("The", "quick","brown","fox","and","the","quick","blue","hare");
        final Optional<Map<String, Integer>> biagramCountsMap = biagramCounter.getBiagramCountsMap(stringList);
        assertFalse(biagramCountsMap.get().isEmpty());
        assertTrue(biagramCountsMap.get().size()==7);
        assertTrue(biagramCountsMap.get().get("the quick")==2);
        assertTrue(biagramCountsMap.get().get("quick brown")==1);
    }

    @Test
    public void getBiagramCountsMapWithSingleWord() throws Exception {
        List<String> stringList = Collections.singletonList("quick");
        final Optional<Map<String, Integer>> biagramCountsMap = biagramCounter.getBiagramCountsMap(stringList);
        assertFalse(biagramCountsMap.isPresent());
    }

    @Test
    public void getBiagramCountsMapWithEmptyList() throws Exception {
        List<String> stringList = new ArrayList<>();
        System.out.println("Value of biagram counter is "+biagramCounter);
        final Optional<Map<String, Integer>> biagramCountsMap = biagramCounter.getBiagramCountsMap(stringList);
        assertFalse(biagramCountsMap.isPresent());
    }
}