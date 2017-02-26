package com.idrawing.filemanager.api;


import java.io.IOException;
import java.nio.file.Path;

public interface FileManager extends FileCRUD, DirectoryCRUD, FileSearcher{
    Iterable<Path> getDiscsList();

    boolean isSameFile(Path path1, Path path2) throws IOException;
}
