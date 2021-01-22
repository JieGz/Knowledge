package com.demo.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        json();
    }

    private static void json() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<User> list = Stream.of(User.builder().name("a").age("10").build(),
                    User.builder().name("b").age("11").build(),
                    User.builder().name("c").age("12").build()).collect(Collectors.toList());
            String json = objectMapper.writeValueAsString(list);
            System.out.println(json);
            List<User> users = objectMapper.readValue(json, new TypeReference<List<User>>() {
            });
            System.out.println(users);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
