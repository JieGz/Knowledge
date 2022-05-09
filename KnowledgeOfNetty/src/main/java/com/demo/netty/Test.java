package com.demo.netty;

import io.netty.util.internal.StringUtil;

/**
 * @author jieguangzhi
 * @date 2022-03-25
 */
public class Test {

    public static void main(String[] args) {
        String smkdata = "00000004347d2662ab9501ac87a5f9b6fbc81dc6";
        final String substring = smkdata.substring(0, 8);
        System.out.println(substring);
        final Integer value = Integer.valueOf(substring);
        System.out.println(value);
        final String password = smkdata.substring(8);
        System.out.println(password);
        final byte[] decodeHexDump = StringUtil.decodeHexDump(password);

    }
}
