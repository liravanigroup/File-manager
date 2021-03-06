package com.idrawing.filemanager.api;

import com.idrawing.filemanager.domain.FileCriteria;
import com.idrawing.filemanager.domain.LocalFile;
import com.idrawing.filemanager.model.visitors.CleanDirVisitor;
import com.idrawing.filemanager.model.visitors.CopyDirVisitor;
import com.idrawing.filemanager.model.visitors.CriteriaSearchVisitor;
import com.idrawing.filemanager.model.visitors.DeleteVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Sergej Povzanyuk on 07.08.2016.
 */
public class LocalFileManager implements FileManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalFileManager.class);

    @Override
    public Iterable<Path> getDiscsList() {
        return FileSystems.getDefault().getRootDirectories();
    }

    @Override
    public Path copy(Path from, Path to) throws IOException {
        return Files.copy(from, to);
    }

    @Override
    public boolean rename(Path oldPath, Path newPath) throws IOException {
        return oldPath.toFile().renameTo(newPath.toFile());
    }

    @Override
    public boolean isSameFile(Path path1, Path path2) throws IOException {
        return Arrays.equals(Files.readAllBytes(path1), Files.readAllBytes(path2));
    }

    @Override
    public void delete(Path path) throws IOException {
        Files.delete(path);
    }

    @Override
    public Path create(Path path) throws IOException {
        return Files.createFile(path);
    }

    @Override
    public Path createDirectory(Path directory) throws IOException {
        return Files.createDirectories(directory);
    }

    @Override
    public Path deleteDirectory(Path directory) throws IOException {
        return Files.walkFileTree(directory, new DeleteVisitor());
    }

    @Override
    public Path move(Path from, Path to) throws IOException {
        return Files.move(from, to);
    }

    @Override
    public void moveDirectory(Path from, Path to) throws IOException {
        Files.walkFileTree(from, new CopyDirVisitor(from, to));
        Files.walkFileTree(from, new CleanDirVisitor(from));
    }

    @Override
    public Path cleanDirectory(Path directory) throws IOException {
        return Files.walkFileTree(directory, new CleanDirVisitor(directory));
    }

    @Override
    public Collection<LocalFile> findByCriteria(FileCriteria criteria) throws IOException {
        Collection<LocalFile> result = new ArrayList<>();
        CriteriaSearchVisitor csv = new CriteriaSearchVisitor(criteria, result);
        criteria.getPaths().parallelStream().forEach(path -> {try {Files.walkFileTree(path, csv);} catch (IOException ignored) {}});
        LOGGER.info(String.valueOf(result.size()));
        return result;
    }
}