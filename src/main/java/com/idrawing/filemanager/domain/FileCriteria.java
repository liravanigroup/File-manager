package com.idrawing.filemanager.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Collection;

/**
 * Created by Admin on 21.02.2017.
 */
@Getter
@Setter
@Builder(builderClassName = "CriteriaBuilder", buildMethodName = "build", toBuilder = true)
public class FileCriteria {
    @Singular
    private Collection<Path> paths;
    private String fileName, partOfPath, regExp;
    @Singular
    private Collection<String> extensions;
    private Long fileSizeMin, fileSizeMax;
    private LocalDate creationDateFrom, creationDateTo, lastModifiedDateFrom, lastModifiedDateTo, lastAccessDateFrom, lastAccessDateTo;
}
