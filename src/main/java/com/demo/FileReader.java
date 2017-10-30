package com.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class FileReader {
    private static final Optional<List<String>> EMPTY_OPTIONAL = Optional.empty();
    /**
     * Reads the contents of the given file and returns a list of words contained
     * in the file. It splits the lines contained in the file with white space.
     *
     * @param filePath
     * @return List containing the words in the file
     */

    public Optional<List<String>> getFileContents(final String filePath) {
        List<String> wordsList;

        if(filePath==null){
            System.out.println("The filePath is null. Cannot execute further");
            return Optional.empty();
        }

        try(Stream<String> stream = Files.lines(Paths.get(filePath))) {
            wordsList = stream.map(str-> str.split(" ")).flatMap(Arrays::stream).collect(toList());
            if (wordsList.isEmpty()){
                System.out.println("The file is empty");
                return EMPTY_OPTIONAL;

            }
        } catch (NoSuchFileException fnfe){
            System.out.println("File identified by the path " + filePath + " cannot be found. Please provide valid file path");
            throw new BiagramAppException("File cannot be found", fnfe);

        }
        catch (IOException e) {
            System.out.println("IOException occurred reading the File:"+filePath+" "+e);
            throw new BiagramAppException("IOException occurred reading the file", e);
        }
        return Optional.of(wordsList);
    }
}
