package com.idrawing.filemanager.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Collection;

/**
 * Created by Admin on 21.02.2017.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileCriteria {
    private Collection<Path> paths;
    private String fileName, partOfPath, regExp;
    private Collection<String> extensions;
    private Long fileSizeMin, fileSizeMax;
    private LocalDate creationDateFrom, creationDateTo;
    private LocalDate lastModifiedDateFrom, lastModifiedDateTo;
    private LocalDate lastAccessDateFrom, lastAccessDateTo;
}
