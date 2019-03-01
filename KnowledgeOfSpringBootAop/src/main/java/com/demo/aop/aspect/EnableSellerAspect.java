package com.demo.aop.aspect;

import com.demo.aop.seller.Seller;
import com.demo.aop.seller.SmartSeller;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * 引介增强：为一个类增加接口或者方法
 * {@link DeclareParents}注解,@Target(ElementType.FIELD).用于修饰字段的
 * value 成员属性：目标类全限定类名[目标类]
 * defaultImpl 成员属性：默认的接口实现[目标类需要新增的接口]
 *
 * @author 揭光智
 * @date 2019/02/28
 */
@Aspect
@Component
public class EnableSellerAspect {

    @DeclareParents(value = "com.demo.aop.waiter.NaughtyWaiter", defaultImpl = SmartSeller.class)
    private Seller seller;
}
