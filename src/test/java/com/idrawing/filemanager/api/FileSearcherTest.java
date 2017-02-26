package com.idrawing.filemanager.api;

import com.google.common.collect.Iterables;
import com.idrawing.filemanager.domain.FileCriteria;
import com.idrawing.filemanager.domain.LocalFile;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by White Stream on 26.02.2017.
 */
public class FileSearcherTest {

    private static final Path TEST_PATH = Paths.get("src/test/resources/fixture/");
    private FileSearcher fileManager;

    @Before
    public void init() {
        fileManager = new LocalFileManager();
    }

    @Test
    public void shouldFindFilesByExtensionInPathSet() throws Exception {
        //given
        FileCriteria criteria = FileCriteria.builder().path(TEST_PATH).extension("testix").build();

        //when
        Collection<LocalFile> result = fileManager.findByCriteria(criteria);

        //then
        assertEquals(1, result.size());
    }

    @Test
    public void shouldSearchFilesByCriteria() throws IOException {
        //given
        FileCriteria criteria = FileCriteria.builder().path(TEST_PATH).extension("cdw").build();

        //when
        Collection<LocalFile> result = fileManager.findByCriteria(criteria);

        //then
        assertEquals(3, result.size());
    }

    @Test
    public void shouldFindFilesBetweenTwoDates() throws Exception {
        //given
        FileCriteria criteria = FileCriteria.builder().path(TEST_PATH).extension("testix").creationDateFrom(LocalDate.of(2016, 6, 30)).build();

        //when
        Iterable<LocalFile> result = fileManager.findByCriteria(criteria);

        //then
        assertTrue(Iterables.size(result) == 1);
    }

    @Test
    public void shouldGetAllFilesByExtension() throws Exception {
        //given
        FileCriteria criteria = FileCriteria.builder().path(TEST_PATH).extension("test").build();

        //when
        Collection<LocalFile> result = fileManager.findByCriteria(criteria);

        //then
        assertEquals(1, result.size());
    }

    @Test
    public void shouldGetAllFilesByManyExtension() throws Exception {
        //given
        FileCriteria criteria = FileCriteria.builder().path(TEST_PATH).extensions(Arrays.asList("test", "tester", "testix")).build();

        //when
        Collection<LocalFile> result = fileManager.findByCriteria(criteria);

        //then
        assertTrue(result.size() == 3);
    }


}