package com.demo.json.entity;

import java.util.List;

/**
 * state:0=玩家守擂
 * state:1=神石 starlevel=>神石的等级,freshtime=神石孵化时间,需要*1000
 *
 * @author 揭光智
 * @date 2019/06/03
 */
public class LeiTaiInfo {

    private String latitude;
    private String longtitude;
    private List<LeiTaiYaoLing> sprite_list;
    private int state;
    private int winner_fightpower;
    private String winner_name;


    private int starlevel;
    private long freshtime;

    public String getLatitude() {
        return latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public List<LeiTaiYaoLing> getSprite_list() {
        return sprite_list;
    }

    public int getState() {
        return state;
    }

    public int getWinner_fightpower() {
        return winner_fightpower;
    }

    public String getWinner_name() {
        return winner_name;
    }

    @Override
    public String toString() {
        return "LeiTaiInfo{" +
                "latitude='" + latitude + '\'' +
                ", longtitude='" + longtitude + '\'' +
                ", sprite_list=" + sprite_list +
                ", state=" + state +
                ", winner_fightpower=" + winner_fightpower +
                ", winner_name='" + winner_name + '\'' +
                '}';
    }
}
