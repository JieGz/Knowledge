package com.demo.stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author jieguangzhi
 * @date 2021-01-27 21:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(fluent = true, chain = true)
public class ClassMate {
    /** 年级 */
    private String grade;

    /** 班级 */
    private String clazz;

    /** 名字 */
    private String name;

    /** 性别 */
    private String sex;
}

