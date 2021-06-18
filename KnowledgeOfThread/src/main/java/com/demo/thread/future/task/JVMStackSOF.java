package com.demo.thread.future.task;

import com.google.common.base.Splitter;

import java.util.stream.Collectors;

/**
 * @author jieguangzhi
 * @date 2021-06-03
 */
public class JVMStackSOF {

    public static void main(String[] args) {
        JVMStackSOF stackSOF = new JVMStackSOF();

        try {
           stackSOF.stackLeak();
          } catch (Throwable throwable) {
            System.out.println("栈深度:" + stackSOF.stackLength);
            throwable.printStackTrace();
        }
    }

    public int stackLength = 1;

    public void stackLeak() {
        ++stackLength;
        stackLeak();
    }

}
