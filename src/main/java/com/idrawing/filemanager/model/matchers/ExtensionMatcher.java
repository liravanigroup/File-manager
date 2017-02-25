package com.idrawing.filemanager.model.matchers;

import com.idrawing.filemanager.domain.FileCriteria;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import static com.google.common.io.Files.getFileExtension;

/**
 * Created by Admin on 25.02.2017.
 */
public class ExtensionMatcher extends MatchChain {
    public ExtensionMatcher(MatchChain nextMatcher, FileCriteria criteria) {
        super(nextMatcher, criteria);
    }

    @Override
    public boolean match(Path file, BasicFileAttributes attrs) {
        if (criteria.getExtensions().isEmpty()) {
            return nextMatcher.match(file, attrs);
        } else {
            for (String extension : criteria.getExtensions()) {
                if(isMatch(extension, file) && nextMatcher.match(file, attrs)){
                    return true;
                }
            }
            return false;
        }
    }


    private boolean isMatch(String extension, Path file) {
        return extension.equalsIgnoreCase(getFileExtension(file.toAbsolutePath().toString()));
    }
}
