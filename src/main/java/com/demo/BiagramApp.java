package com.demo;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BiagramApp {
    private FileReader fileReader;
    private BiagramCounter biagramCounter;
    private MapPrinter mapPrinter;

    public BiagramApp(){
        fileReader = new FileReader();
        biagramCounter = new BiagramCounter();
        mapPrinter = new MapPrinter();
    }

    public BiagramApp (FileReader fileReader, BiagramCounter biagramCounter, MapPrinter mapPrinter){
        this.fileReader = fileReader;
        this.biagramCounter = biagramCounter;
        this.mapPrinter = mapPrinter;
    }

    /**
     * This is the main method of application which accepts file path
     * as the argument and it performs the following tasks
     * (1) Reads the contents of the file identified by the file path
     * (2) Executes the Biagram Histogram algorithm
     * (3) Prints the Map output
     *
     * The method itself concenrs itself with the what rather then how
     * and delegates the how part to the correponding objects to perform
     * above 3 tasks.
     *
     * @param filePath
     */

    public void run(final String filePath) {
        try{
            final Optional<List<String>> wordsList = fileReader.getFileContents(filePath);
            if(wordsList.isPresent()){
                final Optional<Map<String, Integer>> biagramCountsMap = biagramCounter.getBiagramCountsMap(wordsList.get());
                if(biagramCountsMap.isPresent()){
                    System.out.println("************************************Printing Biagram Histagrams*************************************");
                    mapPrinter.printMap(biagramCountsMap.get());
                }

            } else {
                System.out.println("The File is empty. Cannot execute program");
            }

        } catch (BiagramAppException bae){
            System.out.println("Exception occurred reading from File "+bae);
        } catch (RuntimeException ex){
            System.out.println("Runtime exception occurred executing the program "+ex);
        }

    }
}
