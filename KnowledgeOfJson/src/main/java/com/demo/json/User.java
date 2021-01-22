package com.demo.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jieguangzhi
 * @date 2021-01-22 19:53
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String age;
}
