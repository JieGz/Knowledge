package com.demo.json.entity;

/**
 * @author 揭光智
 * @date 2019/05/07
 */
public class Entity {
    private String request_type;

    private Integer longtitude;

    private Integer latitude;

    private long requestid;

    private Integer platform;

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }

    public void setLongtitude(Integer longtitude) {
        this.longtitude = longtitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public void setRequestid(long requestid) {
        this.requestid = requestid;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "request_type='" + request_type + '\'' +
                ", longtitude=" + longtitude +
                ", latitude=" + latitude +
                ", requestid=" + requestid +
                ", platform=" + platform +
                '}';
    }
}
