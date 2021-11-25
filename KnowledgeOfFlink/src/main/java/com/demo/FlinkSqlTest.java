package com.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jieguangzhi
 * @date 2021-11-11
 */
public class FlinkSqlTest {

    private static Pattern pattern = Pattern.compile("\\{\\{ (\\{|[a-z]|[A-Z]|[0-9]|\\}|[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\\\])+ }}");

    private static Pattern sqlPattern = Pattern.compile("(?ms)('(?:''|[^'])*')|--.*?$|/\\*.*?\\*/|#.*?$|<!--.*-->|\r\n|\r|\n");

    public static void main(String[] args) {
        String sql = "# 我是注释*/\n<!--中国人-->\nSELECT\n *\r FROM\r\n test -- 可了咯";

        final String matcher = sqlPattern.matcher(sql).replaceAll("$1");
//        final Matcher matcher1 = sqlPattern.matcher(sql);
//        final String[] split = sql.split(sqlPattern.toString());
//        for (String s : split) {
//            System.out.println(s);
//        }
          System.out.println(matcher);

        System.out.println("' '${datasource_url}', 'properties.group.id' = 'stp_sql_', '"
                .replaceAll("'properties.group.id'(\\s*)=(\\s*)('{1})(.*?)('{1})", "properties.group.id' = 'stp_sql_\\${env}'"));
        System.out.println("' '${datasource_url}', 'properties.group.id' = 'stp_sql_', '"
                .replaceAll("'properties.group.id'(\\s*)=(\\s*)('{1})(.*?)('{1})", "properties.group.id' = 'stp_sql_${env}'"));
    }
}
