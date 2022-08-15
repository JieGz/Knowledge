package com.demo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Objects;

/**
 * File类包装器.
 *
 * @author jieguangzhi
 * @date 2022-07-04
 */
public class FileWrapper implements Serializable {

    private static final long serialVersionUID = 7294194878163915124L;

    static final FileWrapper[] EMPTY_FILE_WRAPPER_ARRAY = {};

    /** FileWrapper包装的file对象 */
    private final File file;

    /** file对象的名字 */
    private String name;
    /** file对象是否存在 */
    private boolean exists;
    /** file对象是否为目录 */
    private boolean directory;
    /** file对象最后修改的时间 */
    private long lastModified;
    /** file的大小,如果File是一个目录,则为0 */
    private long length;

    /** file对象的父目录 */
    private FileWrapper parent;
    /** 如果file对象是一个目录,则包含目录中所有的文件 */
    private FileWrapper[] children;

    public FileWrapper(File file) {
        this(null, file);
    }

    public FileWrapper(final FileWrapper parent, final File file) {
        if (Objects.isNull(file)) {
            throw new IllegalArgumentException("File is missing");
        }

        this.file = file;
        this.parent = parent;
        this.name = file.getName();
    }

    public boolean refresh(final File file) {
        //缓存file对象原始的属性,用于判断file是否有变化
        final boolean originalExists = exists;
        final long originalLastModified = lastModified;
        final boolean originalDirectory = directory;
        final long originalLength = length;

        name = file.getName();
        exists = Files.exists(file.toPath());
        directory = exists && file.isDirectory();
        try {
            lastModified = exists ? FileUtils.lastModified(file) : 0L;
        } catch (IOException e) {
            lastModified = 0L;
        }
        length = exists && !directory ? file.length() : 0L;
        return exists != originalExists || lastModified != originalLastModified || directory != originalDirectory
                || length != originalLength;
    }

    public FileWrapper newChildInstance(final File file) {
        return new FileWrapper(this, file);
    }

    public File getFile() {
        return file;
    }

    public FileWrapper getParent() {
        return parent;
    }

    public FileWrapper[] getChildren() {
        return Objects.isNull(children) ? EMPTY_FILE_WRAPPER_ARRAY : children;
    }

    public void setChildren(FileWrapper[] children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public boolean isDirectory() {
        return directory;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }
}
