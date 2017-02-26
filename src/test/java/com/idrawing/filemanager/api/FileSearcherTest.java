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

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by White Stream on 26.02.2017.
 */
public class FileSearcherTest {

    private static final Path TEST_PATH = Paths.get("src/test/resources/fixture/");
    private FileSearcher fileManager;
    private FileCriteria criteria;

    @Before
    public void init() {
        fileManager = new LocalFileManager();
        criteria = new FileCriteria();
    }

    @Test
    public void shouldFindFilesByExtensionInPathSet() throws Exception {
        //given
        criteria.setPaths(singletonList(TEST_PATH));
        criteria.setExtensions(singletonList("testix"));

        //when
        Collection<LocalFile> result = fileManager.findByCriteria(criteria);

        //then
        assertEquals(1, result.size());
    }

    @Test
    public void shouldSearchFilesByCriteria() throws IOException {
        //given
        criteria.setPaths(singletonList(TEST_PATH));
        criteria.setExtensions(singletonList("cdw"));

        //when
        Collection<LocalFile> result = fileManager.findByCriteria(criteria);

        //then
        assertEquals(3, result.size());
    }

    @Test
    public void shouldFindFilesBetweenTwoDates() throws Exception {
        //given
        criteria.setPaths(singletonList(TEST_PATH));
        criteria.setExtensions(singletonList("testix"));
        criteria.setCreationDateFrom(LocalDate.of(2016, 6, 30));
        criteria.setCreationDateTo(LocalDate.now());

        //when
        Iterable<LocalFile> result = fileManager.findByCriteria(criteria);

        //then
        assertTrue(Iterables.size(result) == 1);
    }

    @Test
    public void shouldGetAllFilesByExtension() throws Exception {
        //given
        criteria.setPaths(singletonList(TEST_PATH));
        criteria.setExtensions(singletonList("test"));

        //when
        Collection<LocalFile> result = fileManager.findByCriteria(criteria);

        //then
        assertEquals(1, result.size());
    }

    @Test
    public void shouldGetAllFilesByManyExtension() throws Exception {
        //given
        criteria.setPaths(singletonList(TEST_PATH));
        criteria.setExtensions(Arrays.asList("test", "tester", "testix"));

        //when
        Collection<LocalFile> result = fileManager.findByCriteria(criteria);

        //then
        assertTrue(result.size() == 3);
    }


}