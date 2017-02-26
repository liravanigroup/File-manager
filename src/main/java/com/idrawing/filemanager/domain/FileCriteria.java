package com.idrawing.filemanager.domain;

import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Collection;

/**
 * Created by Admin on 21.02.2017.
 */
@Getter
@Setter
public class FileCriteria {
    private Collection<Path> paths;
    private String fileName, partOfPath, regExp;
    private Collection<String> extensions;
    private Long fileSizeMin, fileSizeMax;
    private LocalDate creationDateFrom, creationDateTo, lastModifiedDateFrom, lastModifiedDateTo, lastAccessDateFrom, lastAccessDateTo;
}
