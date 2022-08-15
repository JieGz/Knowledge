package com.demo;

import org.apache.commons.io.comparator.NameFileComparator;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author jieguangzhi
 * @date 2022-07-12
 */
public class FileChangeObserver implements Serializable {

    private static final long serialVersionUID = 7294194878163915125L;

    public static final File[] EMPTY_FILE_ARRAY = {};

    private final List<FileChangeListener> listeners = new CopyOnWriteArrayList<>();

    private final FileWrapper rootFileWrapper;

    private final Comparator<File> comparator = NameFileComparator.NAME_COMPARATOR;


    public FileChangeObserver(final String directoryName) {
        this(new File(directoryName));
    }

    public FileChangeObserver(final File directory) {
        this(new FileWrapper(directory));
    }

    protected FileChangeObserver(FileWrapper rootFileWrapper) {
        this.rootFileWrapper = rootFileWrapper;
    }

    public File getDirectory() {
        return rootFileWrapper.getFile();
    }

    public Iterable<FileChangeListener> getListeners() {
        return listeners;
    }

    public void addListener(final FileChangeListener listener) {
        if (Objects.nonNull(listener)) {
            listeners.add(listener);
        }
    }

    public void removeListener(final FileChangeListener listener) {
        if (Objects.nonNull(listener)) {
            listeners.remove(listener);
        }
    }

    public void initialize() {
        final File rootFile = rootFileWrapper.getFile();
        rootFileWrapper.refresh(rootFile);
        final FileWrapper[] children = doListFiles(rootFile, rootFileWrapper);
        rootFileWrapper.setChildren(children);
    }

    public void destroy() {

    }

    public void checkAndNotify() {
        for (FileChangeListener listener : listeners) {
            listener.onStart(this);
        }

        // 目录和文件的事件
        final File rootFile = rootFileWrapper.getFile();
        if (rootFile.exists()) {
            checkAndNotify(rootFileWrapper, rootFileWrapper.getChildren(), listFiles(rootFile));
        } else if (rootFileWrapper.isExists()) {
            checkAndNotify(rootFileWrapper, rootFileWrapper.getChildren(), EMPTY_FILE_ARRAY);
        } else {
            // Didn't exist and still doesn't
        }

        for (FileChangeListener listener : listeners) {
            listener.onStop(this);
        }
    }

    /**
     * Compares two file lists for files which have been created, modified or deleted.
     *
     * @param parent   The parent entry
     * @param previous The original list of files
     * @param files    The current list of files
     */
    private void checkAndNotify(final FileWrapper parent, final FileWrapper[] previous, final File[] files) {
        int c = 0;
        final FileWrapper[] current = files.length > 0 ? new FileWrapper[files.length] : FileWrapper.EMPTY_FILE_WRAPPER_ARRAY;
        for (final FileWrapper wrapper : previous) {
            while (c < files.length && comparator.compare(wrapper.getFile(), files[c]) > 0) {
                current[c] = createFileWrapper(parent, files[c]);
                doCreate(current[c]);
                c++;
            }
            if (c < files.length && comparator.compare(wrapper.getFile(), files[c]) == 0) {
                doChange(wrapper, files[c]);
                checkAndNotify(wrapper, wrapper.getChildren(), listFiles(files[c]));
                current[c] = wrapper;
                c++;
            } else {
                checkAndNotify(wrapper, wrapper.getChildren(), EMPTY_FILE_ARRAY);
                doDelete(wrapper);
            }
        }
        for (; c < files.length; c++) {
            current[c] = createFileWrapper(parent, files[c]);
            doCreate(current[c]);
        }
        parent.setChildren(current);
    }

    private FileWrapper createFileWrapper(final FileWrapper parent, final File file) {
        final FileWrapper fileWrapper = parent.newChildInstance(file);
        fileWrapper.refresh(file);
        final FileWrapper[] children = doListFiles(file, fileWrapper);
        fileWrapper.setChildren(children);
        return fileWrapper;
    }


    private FileWrapper[] doListFiles(final File file, final FileWrapper wrapper) {
        final File[] files = listFiles(file);
        final FileWrapper[] children = files.length > 0 ? new FileWrapper[files.length] : FileWrapper.EMPTY_FILE_WRAPPER_ARRAY;
        for (int i = 0; i < files.length; i++) {
            children[i] = createFileWrapper(wrapper, files[i]);
        }
        return children;
    }

    private File[] listFiles(final File file) {
        File[] children = null;
        if (file.isDirectory()) {
            children = file.listFiles();
        }
        if (Objects.isNull(children)) {
            children = EMPTY_FILE_ARRAY;
        }
        if (children.length > 1) {
            Arrays.sort(children, comparator);
        }
        return children;
    }

    private void doCreate(final FileWrapper wrapper) {
        for (FileChangeListener listener : listeners) {
            if (wrapper.isDirectory()) {
                listener.onDirectoryCreate(wrapper.getFile());
            } else {
                listener.onFileCreate(wrapper.getFile());
            }
        }
        final FileWrapper[] children = wrapper.getChildren();
        for (FileWrapper child : children) {
            doCreate(child);
        }
    }

    private void doChange(final FileWrapper wrapper, final File file) {
        if (wrapper.refresh(file)) {
            for (FileChangeListener listener : listeners) {
                if (wrapper.isDirectory()) {
                    listener.onDirectoryChange(file);
                } else {
                    listener.onFileChange(file);
                }
            }
        }
    }

    private void doDelete(final FileWrapper wrapper) {
        for (FileChangeListener listener : listeners) {
            if (wrapper.isDirectory()) {
                listener.onDirectoryDelete(wrapper.getFile());
            } else {
                listener.onFileDelete(wrapper.getFile());
            }
        }
    }

}
