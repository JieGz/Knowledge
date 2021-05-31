package com.demo;

/**
 * Viewer聚合函数
 *
 * @author jieguangzhi
 * @date 2021-05-10
 */
public enum ViewerAggregateEnum {

    /** Don't aggregate */
    NONE(""),
    /** Return the average value of the argument */
    AVG("AVG"),
    /** Return a count of the number of rows returned */
    COUNT("COUNT"),
    /** Return the count of a number of different values */
    COUNT_DISTINCT("COUNT(DISTINCT"),
    /** Return a concatenated string */
    GROUP_CONCAT("GROUP_CONCAT"),
    /** Return the maximum value */
    MAX("MAX"),
    /** Return the minimum value */
    MIN("MIN"),
    /** Return the population standard deviation */
    STD("STD"),
    /** Return the population standard deviation */
    STDDEV("STDDEV"),
    /** Return the population standard deviation */
    STDDEV_POP("STDDEV_POP"),
    /** Return the sample standard deviation */
    STDDEV_SAMP("STDDEV_SAMP"),
    /** Return the sum */
    SUM("SUM"),
    /** Return the population standard variance */
    VAR_POP("VAR_POP"),
    /** Return the sample variance */
    VAR_SAMP("VAR_SAMP"),
    /** Return the population standard variance */
    VARIANCE("VARIANCE"),
    /** Return bitwise AND */
    BIT_AND("BIT_AND"),
    /** Return bitwise OR */
    BIT_OR("BIT_OR"),
    /** Return bitwise XOR */
    BIT_XOR("BIT_XOR");

    /**
     * 聚合的方法
     */
    private final String type;

    ViewerAggregateEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
