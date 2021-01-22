package com.demo.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author 揭光智
 * @date 2019/05/07
 */
public class Main {
    public static void main(String[] args) throws Exception {
        if (Objects.equals(null, null)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        System.out.println(new BigDecimal("5").subtract(new BigDecimal("1")).toString());

        System.out.println(BigDecimal.valueOf(Double.parseDouble("2.305000")).stripTrailingZeros().toPlainString());
        System.out.println(BigDecimal.valueOf(Double.parseDouble("3.0000")).stripTrailingZeros().toPlainString());

        System.out.println(new BigDecimal(8).divide(new BigDecimal(2), 4, BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(1)).toString());

        User user = User.builder().name("luke").age("18").build();
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(user);
        System.out.println(json);

        User user1 = mapper.readValue(json, User.class);
        System.out.println(user1);
    }

}
