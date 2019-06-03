package com.demo.json.entity;

import java.util.List;

/**
 * @author 揭光智
 * @date 2019/06/03
 */
public class LeiTai {
    private List<LeiTaiInfo> dojo_list;
    private int end;
    private int packageNO;
    private int requestid;
    private int retcode;
    private String retmsg;

    public List<LeiTaiInfo> getDojo_list() {
        return dojo_list;
    }

    public int getEnd() {
        return end;
    }

    public int getPackageNO() {
        return packageNO;
    }

    public int getRequestid() {
        return requestid;
    }

    public int getRetcode() {
        return retcode;
    }

    public String getRetmsg() {
        return retmsg;
    }


    @Override
    public String toString() {
        return "LeiTai{" +
                "dojo_list=" + dojo_list +
                ", end=" + end +
                ", packageNO=" + packageNO +
                ", requestid=" + requestid +
                ", retcode=" + retcode +
                ", retmsg='" + retmsg + '\'' +
                '}';
    }
}
