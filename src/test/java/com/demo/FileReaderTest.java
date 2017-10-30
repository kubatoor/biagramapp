package com.demo;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class FileReaderTest {

    private FileReader fileReader;

    @Before
    public void setUp() throws Exception {
        fileReader = new FileReader();
    }

    @Test
    public void getFileContentsWithNullInput() throws Exception {
        final Optional<List<String>> fileContents = fileReader.getFileContents(null);
        assertFalse(fileContents.isPresent());
    }

    @Test
    public void getFileContentsWithEmptyFileContents() throws Exception {
        final String path = "src/test/resources/demoEmpty.txt";
        final Optional<List<String>> fileContents = fileReader.getFileContents(path);
        assertFalse(fileContents.isPresent());
    }

    @Test
    public void getFileContentsWithValidInput() throws Exception {
        final String path = "src/test/resources/demo.txt";
        final Optional<List<String>> fileContents = fileReader.getFileContents(path);
        assertTrue(fileContents.isPresent());
        assertTrue(fileContents.get().size()==19);
    }

    @Test(expected = BiagramAppException.class)
    public void testWithExpectedException() throws Exception {
        final String path = "src/test/resources/demo123.txt";
        final Optional<List<String>> fileContents = fileReader.getFileContents(path);
    }
}