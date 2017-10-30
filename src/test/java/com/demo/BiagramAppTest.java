package com.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.*;

public class BiagramAppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private BiagramApp biagramApp;
    @Mock
    private FileReader fileReader;
    @Mock
    private BiagramCounter biagramCounter;
    @Mock
    private MapPrinter mapPrinter;

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        MockitoAnnotations.initMocks(this);
        biagramApp = new BiagramApp(fileReader,biagramCounter,mapPrinter);
    }

    @Test
    public void run() throws Exception {
        final String path = "src/test/resources/demo.txt";
        when(fileReader.getFileContents(path)).thenReturn(getWordsList());
        when(biagramCounter.getBiagramCountsMap(anyList())).thenReturn(getBiagramMap());
        biagramApp.run(path);
        verify(fileReader, times(1)).getFileContents(path);
        verify(biagramCounter, times(1)).getBiagramCountsMap(anyList());
        verify(mapPrinter, times(1)).printMap(anyMap());
    }

    @Test
    public void runWithEmptyFileContent() throws Exception {
        final String path = "src/test/resources/demo.txt";
        when(fileReader.getFileContents(path)).thenReturn(Optional.empty());
        biagramApp.run(path);
        verify(fileReader, times(1)).getFileContents(path);
        verify(biagramCounter, times(0)).getBiagramCountsMap(anyList());
        verify(mapPrinter, times(0)).printMap(anyMap());
        assertEquals("The File is empty. Cannot execute program\n",outContent.toString());
    }

    @Test
    public void runWithEmptyBiagrams() throws Exception {
        final String path = "src/test/resources/demo.txt";
        when(fileReader.getFileContents(path)).thenReturn(getWordsList());
        when(biagramCounter.getBiagramCountsMap(anyList())).thenReturn(Optional.empty());
        biagramApp.run(path);
        verify(fileReader, times(1)).getFileContents(path);
        verify(biagramCounter, times(1)).getBiagramCountsMap(anyList());
        verify(mapPrinter, times(0)).printMap(anyMap());
    }

    @Test
    public void runWithBiagramAppException() throws Exception {
        final String path = "src/test/resources/demo.txt";
        doThrow(BiagramAppException.class).when(fileReader).getFileContents(path);
        biagramApp.run(path);
        verify(fileReader, times(1)).getFileContents(path);
        verify(biagramCounter, times(0)).getBiagramCountsMap(anyList());
        verify(mapPrinter, times(0)).printMap(anyMap());
        assertTrue(outContent.toString().contains("Exception occurred reading from File"));
    }

    @Test
    public void runWithRunTimeException() throws Exception {
        final String path = "src/test/resources/demo.txt";
        when(fileReader.getFileContents(path)).thenReturn(getWordsList());
        doThrow(RuntimeException.class).when(biagramCounter).getBiagramCountsMap(anyList());
        biagramApp.run(path);
        verify(fileReader, times(1)).getFileContents(path);
        verify(biagramCounter, times(1)).getBiagramCountsMap(anyList());
        verify(mapPrinter, times(0)).printMap(anyMap());
        assertTrue(outContent.toString().contains("Runtime exception occurred executing the program"));

    }


    private Optional<List<String>> getWordsList(){
        List<String> stringList = Arrays.asList("The", "quick","brown","fox","and","the","quick","blue","hare");
        return Optional.of(stringList);
    }

    private Optional<Map<String,Integer>> getBiagramMap(){
        final HashMap<String, Integer> biagramMap = new HashMap<>();
        biagramMap.put("the quick",2);
        biagramMap.put("fox and", 1);
        return Optional.of(biagramMap);
    }

}