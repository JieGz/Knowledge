package com.demo;

import cn.hutool.core.io.IoUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

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

    @GetMapping("/download")
    public ResponseEntity<Object> download() {
        String content = "download\nusername\nluke";
        final InputStreamResource resource = new InputStreamResource(IoUtil.toStream(content, StandardCharsets.UTF_8));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", String.format("attachment;filename=\"%s", "download.txt"));
        headers.add("Cache-Control", "no-cache,no-store,must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(content.length())
                .contentType(MediaType.parseMediaType("application/txt"))
                .body(resource);
    }
}
