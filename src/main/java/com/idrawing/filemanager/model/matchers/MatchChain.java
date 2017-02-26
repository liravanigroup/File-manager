package com.idrawing.filemanager.model.matchers;

import com.idrawing.filemanager.domain.FileCriteria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.ZoneOffset;

/**
 * Created by Admin on 25.02.2017.
 */
@Getter
@Setter
@AllArgsConstructor
public abstract class MatchChain {
    protected MatchChain nextMatcher;
    protected FileCriteria criteria;

    long defaultIfNull(LocalDate date, Long dateMilliseconds) {
        return date == null ? dateMilliseconds : date.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public abstract boolean match(Path file, BasicFileAttributes attrs);
}
