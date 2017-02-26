package com.idrawing.filemanager.model.matchers;

import org.apache.commons.validator.routines.LongValidator;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by Admin on 25.02.2017.
 */
public class LastModifiedDateMatcher extends MatchChain {
    public LastModifiedDateMatcher(MatchChain nextMatcher) {
        super(nextMatcher, nextMatcher.getCriteria());
    }

    @Override
    public boolean match(Path file, BasicFileAttributes attrs) {
        return isMatch(attrs,
                defaultIfNull(criteria.getLastModifiedDateFrom(), Long.MIN_VALUE),
                defaultIfNull(criteria.getLastModifiedDateTo(), Long.MAX_VALUE))
                && nextMatcher.match(file, attrs);
    }

    private boolean isMatch(BasicFileAttributes attrs, long from, long to) {
        return LongValidator.getInstance().isInRange(attrs.lastModifiedTime().toMillis(), from, to);
    }
}
