package com.demo.json.entity;

/**
 * @author 揭光智
 * @date 2019/06/03
 */
public class LeiTaiYaoLing {

    private int fightpower;
    private int level;
    private int spriteid;

    public int getFightpower() {
        return fightpower;
    }

    public int getLevel() {
        return level;
    }

    public int getSpriteid() {
        return spriteid;
    }


    @Override
    public String toString() {
        return "LeiTaiYaoLing{" +
                "fightpower=" + fightpower +
                ", level=" + level +
                ", spriteid=" + spriteid +
                '}';
    }
}
