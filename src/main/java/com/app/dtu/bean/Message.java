package com.app.dtu.bean;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private String id;
    private int warnList;
    private int controCmd;
    private int dataLen;
    private List<DataMsg> dataMsgs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWarnList() {
        return warnList;
    }

    public void setWarnList(int warnList) {
        this.warnList = warnList;
    }

    public int getControCmd() {
        return controCmd;
    }

    public void setControCmd(int controCmd) {
        this.controCmd = controCmd;
    }

    public int getDataLen() {
        return dataLen;
    }

    public void setDataLen(int dataLen) {
        this.dataLen = dataLen;
    }

    public List<DataMsg> getDataMsgs() {
        return dataMsgs;
    }

    public void addDataMsgs(DataMsg dataMsg) {
        if (dataMsgs == null){
            dataMsgs = new ArrayList<>();
        }
        dataMsgs.add(dataMsg);
    }
}
