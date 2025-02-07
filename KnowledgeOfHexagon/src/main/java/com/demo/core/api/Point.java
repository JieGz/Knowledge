package com.demo.core.api;

import lombok.Data;

/**
 * Point代表二维空间的一个点，记录了x坐标和y坐标的值。
 *
 * @author jieguangzhi
 * @date 2024-09-30
 */
@Data
public class Point {

    /** x坐标的值 */
    private final double coordinateX;
    /** y坐标的值 */
    private final double coordinateY;

    public Point(final double coordinateX, final double coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    /** 通过x坐标和y坐标创建一个Point实例 */
    public static Point fromPosition(final double coordinateX, final double coordinateY) {
        return new Point(coordinateX, coordinateY);
    }

    /** 计算两个点之间的距离.[勾股定理] */
    public double distanceFrom(final Point point) {
        return Math.sqrt((this.coordinateX - point.coordinateX) * (this.coordinateX - point.coordinateX)
                + (this.coordinateY - point.coordinateY) * (this.coordinateY - point.coordinateY));
    }
}
