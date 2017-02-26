package com.idrawing.filemanager.model.matchers;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import static com.google.common.base.Strings.isNullOrEmpty;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

/**
 * Created by Admin on 25.02.2017.
 */
public class PathMatcher extends MatchChain {
    public PathMatcher(MatchChain nextMatcher) {
        super(nextMatcher, nextMatcher.getCriteria());
    }

    @Override
    public boolean match(Path file, BasicFileAttributes attrs) {
        return isNullOrEmpty(criteria.getPartOfPath()) ? nextMatcher.match(file, attrs) : isMatch(criteria.getPartOfPath(), file) && nextMatcher.match(file, attrs);
    }

    private boolean isMatch(String filePath, Path file) {
        return containsIgnoreCase(file.toAbsolutePath().toString(), filePath);
    }
}
