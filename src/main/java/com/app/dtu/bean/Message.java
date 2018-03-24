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

    /**
     * 解析终端编号
     * @return
     */
    public String parseTerminaNo(){
        return this.id.substring(0, 8);
    }

    /**
     * 解析终端类型， 如01， 02
     * @return
     */
    public String parseTerminaType(){
        return this.id.substring(8, 2);
    }

    /**
     * 解析终端型号
     * @return
     */
    public String parseTerminaModelNo(){
        return this.id.substring(6, 4);
    }
    /**
     * 解析终端地址
     * @return
     */
    public String parseTerminaAddress(){
        return this.id.substring(9, 6);
    }


}
