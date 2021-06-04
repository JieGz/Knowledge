package com.demo.thread.lock;

import lombok.Data;

/**
 * @author jieguangzhi
 * @date 2021-06-04
 */
@Data
public class User {

    private String lock;

    private String username = "luke";

    private boolean hasLock;
}
