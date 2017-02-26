package com.idrawing.filemanager.api;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by White Stream on 26.02.2017.
 */
public class DirectoryCRUDTest {

    private static final String TEST_PATH = "src/test/resources/fixture/";
    private DirectoryCRUD fileManager;
    private FileCRUD fileCRUD;


    @Before
    public void init() {
        fileManager = new LocalFileManager();
        fileCRUD = new LocalFileManager();
    }

    @Test
    public void shouldRenameDirectory() throws Exception {
        //given
        Path newDirectory = Paths.get(TEST_PATH + "renametest/dir/");
        Path target = Paths.get(TEST_PATH + "renametest/dir2/");
        Path result = Paths.get(TEST_PATH + "renametest/dir2/text1.txt");
        Path file = Paths.get(TEST_PATH + "renametest/dir/text1.txt");
        fileManager.createDirectory(newDirectory);
        fileCRUD.create(file);
        assertTrue(Files.exists(file));

        //when
        boolean isRenamed = fileManager.rename(newDirectory, target);

        //then
        assertTrue(isRenamed);
        assertTrue(Files.exists(result));
        assertFalse(Files.exists(file));

        //cleaning
        fileManager.deleteDirectory(target);
    }

    @Test
    public void shouldCleanDirectory() throws Exception {
        //given
        Path dirtyDirectory = Paths.get(TEST_PATH + "cleantest");
        Path newDirectory = Paths.get(TEST_PATH + "cleantest/dir/");
        Path file1 = Paths.get(TEST_PATH + "cleantest/dir/text1.txt");
        Path file2 = Paths.get(TEST_PATH + "cleantest/dir/text2.txt");
        fileManager.createDirectory(newDirectory);
        fileCRUD.create(file1);
        fileCRUD.create(file2);
        assertTrue(Files.exists(file1));
        assertTrue(Files.exists(file2));

        //when
        fileManager.cleanDirectory(dirtyDirectory);

        //then
        assertFalse(Files.exists(newDirectory));
    }

    @Test
    public void shouldMoveDirectoryToOtherPath() throws Exception {
        //given
        Path newDirectory = Paths.get(TEST_PATH + "movetest/from/dir/");
        Path file1 = Paths.get(TEST_PATH + "movetest/from/dir/text1.txt");
        Path file2 = Paths.get(TEST_PATH + "movetest/from/dir/text2.txt");
        Path from = Paths.get(TEST_PATH + "movetest/from/");
        Path to = Paths.get(TEST_PATH + "movetest/to/");
        fileManager.createDirectory(newDirectory);
        fileCRUD.create(file1);
        fileCRUD.create(file2);
        assertTrue(Files.exists(file1));
        assertTrue(Files.exists(file2));

        //when
        fileManager.moveDirectory(from, to);

        //then
        assertFalse(Files.exists(file1));
        assertFalse(Files.exists(file2));
        assertFalse(Files.exists(newDirectory));
        assertTrue(Files.exists(from));
        assertTrue(Files.exists(to));

        //cleaning
        fileManager.cleanDirectory(to);
    }

    @Test
    public void shouldDeleteNotEmptyDirectory() throws Exception {
        //given
        Path directory = Paths.get(TEST_PATH + "directory/");
        Path file1 = Paths.get(TEST_PATH + "directory/text1.txt");
        Path file2 = Paths.get(TEST_PATH + "directory/text2.txt");
        Path result = fileManager.createDirectory(directory);
        Path filepath1 = fileCRUD.create(file1);
        Path filepath2 = fileCRUD.create(file2);
        assertTrue(Files.exists(filepath1));
        assertTrue(Files.exists(filepath2));

        //when
        fileManager.deleteDirectory(result);

        //then
        assertFalse(Files.exists(directory));
    }

    @Test
    public void shouldCreateDirectory() throws Exception {
        //given
        Path directory = Paths.get(TEST_PATH + "directory/");

        //when
        Path result = fileManager.createDirectory(directory);

        //then
        assertTrue(Files.isDirectory(result));

        //cleaning
        fileManager.deleteDirectory(result);
    }


}