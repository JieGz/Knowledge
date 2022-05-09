package com.demo;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Sql注释处理工具类
 *
 * @author jieguangzhi
 * @date 2022-05-07
 */
public class SqlCommentUtil {

    /** sql中换行符占位符. */
    private final static String REAL_TIME_SQL_NEW_LINE = "REAL_TIME_SQL_NEW_LINE";
    private final static String PLACE_HOLDER = REAL_TIME_SQL_NEW_LINE + " " + REAL_TIME_SQL_NEW_LINE + "|" + REAL_TIME_SQL_NEW_LINE;

    public static Pattern SQL_COMMENT = Pattern.compile("--.*\n|--.*\r\n");

    public static String format(String sql) {
        final Matcher m = SQL_COMMENT.matcher(sql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String group = m.group(0);
            group = REAL_TIME_SQL_NEW_LINE + group + REAL_TIME_SQL_NEW_LINE;
            m.appendReplacement(sb, group);
        }
        m.appendTail(sb);
        return sb.toString().replaceAll("\r\n|\r|\n", "")
                .replaceAll(PLACE_HOLDER, "\n").trim();
    }
}
