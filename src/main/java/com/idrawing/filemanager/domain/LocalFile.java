package com.idrawing.filemanager.domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.io.Files.getFileExtension;
import static com.google.common.io.Files.getNameWithoutExtension;

/**
 * Created by Sergej Povzanyuk on 07.08.2016.
 */
public class LocalFile {
    private Path path;
    private BasicFileAttributes attributes;

    public LocalFile(Path path) {
        checkArgument(Files.isRegularFile(path), "Argument is not a path");
        this.path = path;
        try {
            this.attributes = Files.readAttributes(path, BasicFileAttributes.class);
        } catch (IOException ignore) {
            this.attributes = new EmptyFileAttributes();
        }
    }

    public String getName() {
        return getNameWithoutExtension(getPathString());
    }

    public Path getPath(){
        return path;
    }

    public String getExtension() {
        return getFileExtension(getPathString().toLowerCase());
    }

    public String getPathString() {
        return path.toAbsolutePath().toString();
    }

    public LocalDateTime getCreationDate() {
        return getLocalDateTime(attributes.creationTime());
    }

    public LocalDateTime getLastModifiedDate() {
        return getLocalDateTime(attributes.lastModifiedTime());
    }

    public LocalDateTime getLastAccessDate() {
        return getLocalDateTime(attributes.lastAccessTime());
    }

    private LocalDateTime getLocalDateTime(FileTime time) {
        return LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault());
    }

    public String getOwner() {
        try {
            return Files.getOwner(path).getName();
        } catch (IOException e) {
            return "Unknown";
        }
    }

    public String getContentType() {
        try {
            return Files.probeContentType(path);
        } catch (IOException e) {
            return "Unknown";
        }
    }

    public long getFileSizeBytes() {
        try {
            return Files.size(path);
        } catch (IOException e) {
            return 0L;
        }
    }

    public File getFile() {
        return path.toFile();
    }

    private class EmptyFileAttributes implements BasicFileAttributes {
        @Override
        public FileTime lastModifiedTime() {
            return FileTime.fromMillis(new Date().getTime());
        }

        @Override
        public FileTime lastAccessTime() {
            return FileTime.fromMillis(new Date().getTime());
        }

        @Override
        public FileTime creationTime() {
            return FileTime.fromMillis(new Date().getTime());
        }

        @Override
        public boolean isRegularFile() {
            return true;
        }

        @Override
        public boolean isDirectory() {
            return false;
        }

        @Override
        public boolean isSymbolicLink() {
            return false;
        }

        @Override
        public boolean isOther() {
            return false;
        }

        @Override
        public long size() {
            return 0L;
        }

        @Override
        public Object fileKey() {
            return new Object();
        }
    }
}
