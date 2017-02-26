package com.idrawing.filemanager.model.matchers;

import org.apache.commons.validator.routines.LongValidator;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by Admin on 25.02.2017.
 */
public class CreationDateMatcher extends MatchChain {
    public CreationDateMatcher(MatchChain nextMatcher) {
        super(nextMatcher, nextMatcher.getCriteria());
    }

    @Override
    public boolean match(Path file, BasicFileAttributes attrs) {
        return isMatch(attrs,
                defaultIfNull(criteria.getCreationDateFrom(), Long.MIN_VALUE),
                defaultIfNull(criteria.getCreationDateTo(), Long.MAX_VALUE))
                && nextMatcher.match(file, attrs);
    }

    private boolean isMatch(BasicFileAttributes attrs, long from, long to) {
        return LongValidator.getInstance().isInRange(attrs.creationTime().toMillis(), from, to);
    }
}

