package com.app.dtu.bean;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class DataMsg {
    private int type;
    private int len;
    private List<Integer> datas;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public List<Integer> getDatas() {
        return datas;
    }

    // 特殊场景下的time包
    public Long time;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void addData(Integer data) {
        if (CollectionUtils.isEmpty(datas)){
            datas = new ArrayList<>();
        }
        datas.add(data);
    }

    @Override
    public String toString() {
        return "DataMsg{" +
                "type=" + type +
                ", len=" + len +
                ", datas=" + datas +
                '}';
    }
}
