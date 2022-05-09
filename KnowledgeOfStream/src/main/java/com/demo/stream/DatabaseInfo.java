package com.demo.stream;

import lombok.Data;

import java.util.List;

/**
 * @author jieguangzhi
 * @date 2022-01-04
 */
@Data
public class DatabaseInfo {

    private String dbname;

    private List<String> tables;

    private List<Integer> permissions;
}
