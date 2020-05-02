package com.demo.netty.http.common;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * @author 揭光智2
 * @date 2020/05/02
 */
public class ConnectParse {
    public static String parse(String uri) {
        final String token = "token";
        if (Strings.isNullOrEmpty(uri) || !uri.contains("?")) {
            return null;
        }
        String params = uri.split("\\?")[1];
        HashMap<String, String> map = Maps.newHashMap();
        for (String param : params.split("&")) {
            String[] ps = param.split("=");
            map.put(ps[0], ps[1]);
        }
        return map.get(token);
    }
}
