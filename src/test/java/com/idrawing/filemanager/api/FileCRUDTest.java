package com.idrawing.filemanager.api;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by White Stream on 26.02.2017.
 */
public class FileCRUDTest {

    private static final String TEST_PATH = "src/test/resources/fixture/";
    private FileCRUD fileManager;

    @Before
    public void init() {
        fileManager = new LocalFileManager();
    }

    @Test
    public void shouldCreateFile() throws Exception {
        //given
        Path file = Paths.get(TEST_PATH + "createtest/test.cdw");

        //when
        Path result = fileManager.create(file);

        //then
        assertTrue(Files.exists(result));

        //cleaning
        fileManager.delete(result);
    }

    @Test
    public void shouldCopyFileToOtherDirectory() throws Exception {
        //given
        Path from = Paths.get(TEST_PATH + "copytest/from/test5.cdw");
        Path to = Paths.get(TEST_PATH + "copytest/to/test5.cdw");

        //when
        Path result = fileManager.copy(from, to);

        //then
        assertTrue(Files.exists(to));
        assertEquals(to, result);

        //cleaning
        Files.delete(to);
    }

    @Test
    public void shouldDeleteFile() throws Exception {
        //given
        Path from = Paths.get(TEST_PATH + "copytest/from/test5.cdw");
        Path to = Paths.get(TEST_PATH + "deletetest/test5.cdw");
        Path result = fileManager.copy(from, to);

        //when
        assertTrue(Files.exists(to));
        fileManager.delete(result);

        //then
        assertFalse(Files.exists(to));
    }

    @Test
    public void shouldRenameFile() throws Exception {
        //given
        Path file = Paths.get(TEST_PATH + "renametest/text1.txt");
        Path target = Paths.get(TEST_PATH + "renametest/text2.txt");
        fileManager.create(file);
        assertTrue(Files.exists(file));

        //when
        boolean isRenamed = fileManager.rename(file, target);

        //then
        assertTrue(isRenamed);
        assertFalse(Files.exists(file));
        assertTrue(Files.exists(target));

        //cleaning
        fileManager.delete(target);
    }

    @Test
    public void shouldMoveFileToOtherPath() throws Exception {
        //given
        Path file = Paths.get(TEST_PATH  + "movetest/from/text1.txt");
        Path from = Paths.get(TEST_PATH + "movetest/from/text1.txt");
        Path to = Paths.get(TEST_PATH  + "movetest/to/text1.txt");
        fileManager.create(file);
        assertTrue(Files.exists(file));

        //when
        Path result = fileManager.move(from, to);

        //then
        assertFalse(Files.exists(file));
        assertTrue(Files.exists(result));

        //cleaning
        fileManager.delete(result);
    }

}