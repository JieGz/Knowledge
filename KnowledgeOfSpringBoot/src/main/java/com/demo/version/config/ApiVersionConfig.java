package com.demo.version.config;

import com.demo.version.mapping.ApiVersionHandlerMapping;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Api版本配置类
 *
 * @author jieguangzhi
 * @date 2021-06-18
 */
@Configuration
public class ApiVersionConfig implements WebMvcRegistrations {

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new ApiVersionHandlerMapping();
    }
}
