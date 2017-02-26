package com.idrawing.filemanager.api;


import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

/**
 * Created by Sergej Povzanyuk on 07.08.2016.
 */
public class LocalFileManagerTest {

    private static final String TEST_PATH = "src/test/java/fixture/";
    private FileManager fileManager;

    @Before
    public void init() {
        fileManager = new LocalFileManager();
    }

    @Test
    public void shouldGetDiscsList() throws Exception {
        //when
        Iterable<Path> result = fileManager.getDiscsList();

        //then
        assertTrue(result.iterator().hasNext());
    }

    @Test
    public void shouldCompareTwoFilesContent() throws IOException {
        //given
        Path file1 = Paths.get(TEST_PATH + "comparetest/drawing1.cdw");
        Path file2 = Paths.get(TEST_PATH + "comparetest/drawing2.cdw");

        //when
        boolean isSameFile = fileManager.isSameFile(file1, file2);

        //then
        assertTrue(isSameFile);
    }
}