package com.demo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jieguangzhi
 * @date 2021-05-10
 */
@RestController
public class TestController {

    @PostMapping("/test")
    public String test(@Validated @RequestBody TestRequest request) {
        System.out.println(request.getAggregateEnum().getType());
        return "Hello";
    }

    @GetMapping("/version")
    public String version() {
        System.out.println("version");
        return "version";
    }

    @GetMapping("/json")
    public String json() {
        TestRequest request = new TestRequest();
        request.setName("Luke");
        request.setAggregateEnum(ViewerAggregateEnum.AVG);
        ObjectMapper mapper = new ObjectMapper();
        //mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return mapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
