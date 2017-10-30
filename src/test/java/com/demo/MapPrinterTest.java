package com.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class MapPrinterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private MapPrinter mapPrinter;

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        mapPrinter = new MapPrinter();
    }

    @After
    public void cleanUpStreams() {
        //System.setOut(null);
    }

    @Test
    public void printMapWithValidInput() throws Exception {
        Map<String, Integer> biagramMap = new HashMap<>();
        biagramMap.put("the quick", 2);
        biagramMap.put("and the", 1);
        mapPrinter.printMap(biagramMap);
        assertEquals("the quick: 2\nand the: 1\n",outContent.toString());
    }

    @Test
    public void printMapWithNullInput() throws Exception {
        mapPrinter.printMap(null);
        assertEquals("The input map is null or Empty. Cannot print\n",outContent.toString());
    }

    @Test
    public void printMapWithEmptyMap() throws Exception {
        mapPrinter.printMap(new HashMap<>());
        assertEquals("The input map is null or Empty. Cannot print\n",outContent.toString());
    }

}