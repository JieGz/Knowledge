package com.demo;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;

/**
 * @author jieguangzhi
 * @date 2022-08-15
 */
@Slf4j
public class DateFilter implements FileFilter {

    @Override
    public boolean accept(File file) {
        log.info("file path: {}, file name:{}", file.getPath(), file.getName());
        return true;
    }
}
