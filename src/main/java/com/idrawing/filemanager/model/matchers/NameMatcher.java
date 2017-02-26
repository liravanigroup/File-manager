package com.idrawing.filemanager.model.matchers;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.io.Files.getNameWithoutExtension;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

/**
 * Created by Admin on 25.02.2017.
 */
public class NameMatcher extends MatchChain {
    public NameMatcher(MatchChain nextMatcher) {
        super(nextMatcher, nextMatcher.getCriteria());
    }

    @Override
    public boolean match(Path file, BasicFileAttributes attrs) {
        return isNullOrEmpty(criteria.getFileName()) ? nextMatcher.match(file, attrs) : isMatch(criteria.getFileName(), file) && nextMatcher.match(file, attrs);
    }

    private boolean isMatch(String fileName, Path file) {
        return containsIgnoreCase(getNameWithoutExtension(file.toAbsolutePath().toString()), fileName);
    }
}
