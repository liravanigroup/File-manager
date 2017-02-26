package com.idrawing.filemanager.model.matchers;

import org.apache.commons.validator.routines.RegexValidator;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Created by Admin on 25.02.2017.
 */
public class PathRegExpMatcher extends MatchChain {
    public PathRegExpMatcher(MatchChain nextMatcher) {
        super(nextMatcher, nextMatcher.getCriteria());
    }

    @Override
    public boolean match(Path file, BasicFileAttributes attrs) {
        return isNullOrEmpty(criteria.getRegExp()) ? nextMatcher.match(file, attrs) : isMatch(criteria.getRegExp(), file) && nextMatcher.match(file, attrs);
    }

    private boolean isMatch(String regExp, Path file) {
        return new RegexValidator(regExp, false).isValid(file.toAbsolutePath().toString());
    }
}
