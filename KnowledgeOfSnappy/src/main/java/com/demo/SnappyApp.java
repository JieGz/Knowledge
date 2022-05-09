package com.demo;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import org.xerial.snappy.Snappy;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

/**
 * @author jieguangzhi
 * @date 2022-02-22
 */
public class SnappyApp {

    public static void main(String[] args) throws IOException {
        // String input = "Hello snappy-java! Snappy-java is a JNI-based wrapper of "
        //         + "Snappy, a fast compresser/decompresser.";
        // byte[] compressed = Snappy.compress(input.getBytes(StandardCharsets.UTF_8));
        String input = "virwTFsiYWN0PXdlYnNka3Byb3RvY29sJnRpbWU9MTY0NTUyNDAwMiZkb21haW49bS5jaHVwZW9zLmNvbSZ1cmw9aHR0cHMlM0ElMkYlMkZtLiAA9NEBJTJGY29sbGVjdGlvbnMlMkY4ODglM0ZmYmNsaWQlM0RJd0FSMzBfUkM4VjNYRmFTQkJaR0FkeWotT2tzbC16Z2xHWkxjNXBEbGtzOHdORklaaF9CVU1nejBYX0djX2FlbV9BWmtxWXJCbmhRX0tDOGFDdlJDUnN1b0ZOZUFWaW01djRxcHRrZFJycHVpWnd2S1Y2S3FWZ2VPakdzV2FxQWNsMHdpbDB5U3ZUbkRpODF2THU4M1lxS2ltTml4Zl85VGs1ODc2dFh1QkNCeHNTWlF6a1IyWGpscEJkR3NOSW9RVVlqYyZ1YT1Nb3ppbGxhJTJGNS4wJTIwKGlQaG9uZSUzQiUyMENQVSUyMGlQaG9uZSUyME9TJTIwMTVfM18xJTIwbGlrZSUyME1hYyUyME9TJTIwWCklMjBBcHBsZVdlYktpdCUyRjYwNS4xLjE1JTIwKEtIVE1MJTJDJTIwbGlrZSUyMEdlY2tvKSUyME1vYmlsZSUyRjE5RDUyJTIwJTVCRkJBTiUyRkZCSU9TJTNCRkJEViUyRmlQaG9uZTExJTJDOCUzQkZCTUQlMkZpUGhvbmUlM0JGQlNOJTJGaU9TJTNCRkJTViUyRjE1LjMuMQUtFFNTJTJGMgULFElEJTJGcAFNBQ8kTEMlMkZqYV9KUAUPME9QJTJGNSU1RCZyZWZFKV0oHGZhY2Vib29rQUngJTJGJmRhdGFfaWQ9ZjBhZTg1YzYtNzc2Yy00ODQyLWE0NTctMTZlMGM0MzE2NWY2JnJlcXVlc3RfNqYC8JAxMDc0JnByb2RpZD1zaG9wbGluZSZldmVudGlkPTYwMDA2MjYwJnNlc3Npb25fY3JlYXRlX3R5cGU9MTAxJmxhc3Rfc2Vzc2lvbl9pZD0mbW9yZWluZm89JTdCJTIybGFuZ3VhZ2UlMjIlM0ElMjJqYSUyMiUyQyUyMmlzX21vYmlsZSUyMiUzQSUyMjEwMSUyDR4gdGhlbWVfaWQlETpcNjFkYzJjN2JjZWVjYzIwNTRiZTM5MzUwASEh2zAyc3RvcmVfcmVnaW9uARUFcARzZwELHSAB/gB6QWgRWEBBc2lhJTI1MkZTaGFuZ2hhaQEaBTEMdXNlcmowABBUb2t5bxUtCbAIbmFtGc8MQWxvbhl/CSEMdmVycy6gAAAxQaUAOBVGGGlzX2FkbWkZwBnfAHA9DIlkqDM5NDU5NTFfN2UwNTM3NWJiMmM1NGNmMTk2ZDFiNzIyMzAwMDc1ZTUlMjIN0QRwZCG+CG1vZBmlGWEhGhhfb2Zmc2V0AZAlPwg0OTIBDCUPRrsACDYuNhUeJGh0dHBfY29va2kdXIhmYWU1MDRhLWU5NWYtNGE3MC1hNDkxLTkzYjc3NTc2MDRhNxVBHHVybF9wYXRoAREFfCWVMgwFADWpDhHPHG1lcmNoYW50LiYCDDIwMDBF8QA3FSclxz09FRkFsxBzZWFyYzJ1ALVtBDI1/m8F/m8F/m8FrW8V8gh0aXSB7W1vJCUyNUU0JTI1QkERBQRFNgEKFDAlMjU5NwEeADUBCgEFADgBGQkPFDMlMjU4MSGdBDAtBQYETWUJBwhhbGwVaZkTARMlz5AyMGIzNGJjYy0zYjg2LTQxMzAtYTNiNC01ZjUxMmUzZGZkM2IlMdsRQAhjcmVBskGbFUlV/QQ2MV2eETIAcnk8ADZ5WIkcXQUsMTY0MTgxOTI4NTUzeSHh0ARpdB1rADBZLhhsb2FkaW5nRowAAZllIaU2DbMYZF9hdF9tcwEcBf4JtQQ0MKFweXMkc2NyZWVuX3dpZF34CDQxNAE3BVMNIRBoZWlnaHmWBDg5eXgJdQBuLngEBDExGWKh2gR1YzIjA2AxNjA1MjY0MDQ0NzAyNjY4MTQzNjQ4Mjg2GewRNjJXAARMVkETAEZBIgBDQQQAOAEPRQ4BBQRBQhUPQVkAQUFZGR4AQhkPBR4BMhkeOksAAEIFHhlpADlFYDBQT0NIRVRURU0lMjVDASIQODlUSVMVdQUSATQZVwlIBRkFBQFIGQ8AOUHnGQ8FIwkPAaJlBQAyASMYMjBNNDQ4NzlyMR0McHJpY10rCDQ3OD13CG9zaQ7BCSHJRQAENjMBCynUDHRhdHVdGl1kDGlmcmE29gZ8MjhhNzVlMjgzMDcyYzJiYTk5MTE1ZjQ0ZTY2YjlhMjEBVgw3RCIs/o8K/o8K/o8K/o8K/o8K/o8K/o8K/o8K/o8K/o8KMo8KkDEzMTcxMDEyLTQxMjUtNDE4YS1hODRhLTAwNWYwOTZiYzNhMCZijwoANf6PCv6PCv6PCv6PCv6PCv6PCv6PCv6PCv6PCv6PCv6PCv6PCv6PCv6PCv6PCv6PCv6PCv6PCv6PCl6PCgA54a0aAwgOqw7+jwr+jwoajwoSRAgJl/6PCv6PCiQxODc5NjE1NTcz/o8K/o8K/o8K/o8Kao8KDg4LAEISkA0OggsS1goAQg6zCiauCgAwPdTWrgoqggz+rgpKrgoAXQ==";
        byte[] compressed = Base64.getDecoder().decode(input);
        byte[] uncompressed = Snappy.uncompress(compressed);

        String result = new String(uncompressed, StandardCharsets.UTF_8);
        System.out.println(result);
//        final List<String> eventList = JSON.parseArray(result, String.class);
//        final String s = eventList.get(0);
//        System.out.println(s);
//        final List<String> split = Splitter.on("&").splitToList(s);
//        System.out.println(split);
//        for (String value : split) {
//            final List<String> keyValue = Splitter.on("=").splitToList(value);
//            System.out.print(keyValue.get(0) + ":");
//            if (keyValue.size() == 2) {
//                System.out.println(keyValue.get(1)+ " "+ System.currentTimeMillis()/1000);
//            }
//        }

        //System.out.println(URLDecoder.decode(result, "UTF-8"));
    }
}
