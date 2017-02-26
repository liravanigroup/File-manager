package com.idrawing.filemanager.api;

import com.idrawing.filemanager.domain.FileCriteria;
import com.idrawing.filemanager.domain.LocalFile;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Created by Admin on 19.02.2017.
 */
public interface FileSearcher {
   Collection<LocalFile> findByCriteria(FileCriteria fileCriteria) throws IOException;
}