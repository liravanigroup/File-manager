package com.idrawing.filemanager.model.matchers;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.validator.routines.LongValidator;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by Admin on 25.02.2017.
 */
public class FileSizeMatcher extends MatchChain {
    public FileSizeMatcher(MatchChain nextMatcher) {
        super(nextMatcher, nextMatcher.getCriteria());
    }

    @Override
    public boolean match(Path file, BasicFileAttributes attrs) {
        return isMatch(attrs,
                ObjectUtils.defaultIfNull(criteria.getFileSizeMin(), Long.MIN_VALUE),
                ObjectUtils.defaultIfNull(criteria.getFileSizeMax(), Long.MAX_VALUE))
                && nextMatcher.match(file, attrs);
    }

    private boolean isMatch(BasicFileAttributes attrs, long min, long max) {
        return LongValidator.getInstance().isInRange(attrs.size(), min, max);
    }
}
