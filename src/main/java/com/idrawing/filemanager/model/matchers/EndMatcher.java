package com.idrawing.filemanager.model.matchers;

import com.idrawing.filemanager.domain.FileCriteria;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by Admin on 25.02.2017.
 */
public class EndMatcher extends MatchChain {

    public EndMatcher(FileCriteria criteria) {
        super(null, criteria);
    }

    @Override
    public boolean match(Path file, BasicFileAttributes attrs) {
        return true;
    }
}
