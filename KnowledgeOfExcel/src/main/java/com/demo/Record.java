package com.demo;

import lombok.*;

/**
 * Excel表格转JavaBean,需要注意的是,每个类型必须要按下面指定的位置放
 * 这是因为使用了EasyExcel框架的原因,还有一种方案是将表头的名字和对应的属性关联起来.
 *
 * @author 揭光智
 * @date 2018/12/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class Record {

    /**
     * 手机编号
     */
    private String id;

    /**
     * 网易邮箱1
     */
    private String ownerName;

    /**
     * 网易邮箱1密码
     */
    private String ownerId;


    /**
     * 网易邮箱1
     */
    private String twListSource;

    /**
     * 网易邮箱1密码
     */
    private String twListSubSource = "";

}
