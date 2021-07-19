package com.demo.version.mapping;

import com.demo.version.annotation.ApiVersion;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Api版本映射器
 *
 * @author jieguangzhi
 * @date 2021-06-18
 */
public class ApiVersionHandlerMapping extends RequestMappingHandlerMapping {
    @Override
    protected boolean isHandler(Class<?> beanType) {
        return AnnotatedElementUtils.hasAnnotation(beanType, Controller.class);
    }

    @Override
    protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
        Class<?> controllerClass = method.getDeclaringClass();
        //1. 查找标在类上的ApiVersion注解
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(controllerClass, ApiVersion.class);
        //2. 查找标在方法上的ApiVersion注解
        ApiVersion methodAnnotation = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        //3. 方法上的ApiVersion注解优先级别高于类上的ApiVersion注解
        if (Objects.nonNull(methodAnnotation)) {
            apiVersion = methodAnnotation;
        }
        //4. 默认情况下不使用api版本
        String[] urlPatterns = apiVersion == null ? new String[0] : apiVersion.value();

        PatternsRequestCondition apiPattern = new PatternsRequestCondition(urlPatterns);
        PatternsRequestCondition oldPattern = mapping.getPatternsCondition();
        //5. 在urlPath里拼接上版本号
        PatternsRequestCondition updatedFinalPattern = apiPattern.combine(oldPattern);
        //6. 生成新的RequestMappingInfo
        mapping = new RequestMappingInfo(mapping.getName(), updatedFinalPattern, mapping.getMethodsCondition(),
                mapping.getParamsCondition(), mapping.getHeadersCondition(), mapping.getConsumesCondition(),
                mapping.getProducesCondition(), mapping.getCustomCondition());
        super.registerHandlerMethod(handler, method, mapping);
    }
}
