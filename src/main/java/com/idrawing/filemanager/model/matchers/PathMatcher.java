package com.idrawing.filemanager.model.matchers;

import com.idrawing.filemanager.domain.FileCriteria;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.io.Files.getNameWithoutExtension;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

/**
 * Created by Admin on 25.02.2017.
 */
public class PathMatcher extends MatchChain {
    public PathMatcher(MatchChain nextMatcher, FileCriteria criteria) {
        super(nextMatcher, criteria);
    }

    @Override
    public boolean match(Path file, BasicFileAttributes attrs) {
        if(isNullOrEmpty(criteria.getPartOfPath())){
            return nextMatcher.match(file, attrs);
        }else {
            return isMatch(criteria.getPartOfPath(), file) && nextMatcher.match(file, attrs);
        }
    }

    private boolean isMatch(String filePath, Path file) {
        return containsIgnoreCase(file.toAbsolutePath().toString(), filePath);
    }
}
