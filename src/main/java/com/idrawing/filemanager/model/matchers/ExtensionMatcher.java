package com.idrawing.filemanager.model.matchers;

import com.google.common.base.Predicate;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import static com.google.common.io.Files.getFileExtension;

/**
 * Created by Admin on 25.02.2017.
 */
public class ExtensionMatcher extends MatchChain {
    public ExtensionMatcher(MatchChain nextMatcher) {
        super(nextMatcher, nextMatcher.getCriteria());
    }

    @Override
    public boolean match(Path file, BasicFileAttributes attrs) {
        return criteria.getExtensions().isEmpty() ? nextMatcher.match(file, attrs) : criteria.getExtensions().stream().anyMatch((Predicate<String>) ex -> isMatch(ex, file) && nextMatcher.match(file, attrs));
    }

    private boolean isMatch(String extension, Path file) {
        return extension.equalsIgnoreCase(getFileExtension(file.toAbsolutePath().toString()));
    }
}
