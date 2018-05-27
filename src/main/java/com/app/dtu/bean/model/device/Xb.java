package com.app.dtu.bean.model.device;

import com.app.dtu.util.DtuUtil;
import com.google.gson.annotations.Expose;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Xb implements Serializable{
    
    @Expose(serialize = false)
    private List<String> xbs;

    public void addXb(String xb){
        if (xb == null || xb.length() <= 0) {
            return;
        }
        if (CollectionUtils.isEmpty(xbs)){
            xbs = new ArrayList<>();
        }
        xbs.add(xb);
    }

    public Xb() {
    }

    public Xb(Integer xbType) {
        this.xbType = xbType;
    }

    public Xb(Integer xbType, List<String> xbs) {
        this.xbType = xbType;
        this.xbs = xbs;
    }

    private Integer xbType;
    private String xb2;
    private String xb3;
    private String xb4;
    private String xb5;
    private String xb6;
    private String xb7;
    private String xb8;
    private String xb9;
    private String xb10;
    private String xb11;
    private String xb12;
    private String xb13;
    private String xb14;
    private String xb15;
    private String xb16;
    private String xb17;
    private String xb18;
    private String xb19;
    private String xb20;
    private String xb21;
    private String xb22;
    private String xb23;
    private String xb24;
    private String xb25;
    private String xb26;
    private String xb27;
    private String xb28;
    private String xb29;
    private String xb30;
    private String xb31;
    private String xb32;
    private String xb33;

    public String toJson(){
        if(CollectionUtils.isEmpty(xbs)){
            return null;
        }
        this.xbType = xbType;
        xb2 = getValueByList(xbs, 0);
        xb3 = getValueByList(xbs, 1);
        xb4 = getValueByList(xbs, 2);
        xb5 = getValueByList(xbs, 3);
        xb6 = getValueByList(xbs, 4);
        xb7 = getValueByList(xbs, 5);
        xb8 = getValueByList(xbs, 6);
        xb9 = getValueByList(xbs, 7);
        xb10 = getValueByList(xbs, 8);
        xb11 = getValueByList(xbs, 9);
        xb12 = getValueByList(xbs, 10);
        xb13 = getValueByList(xbs, 11);
        xb14 = getValueByList(xbs, 12);
        xb15 = getValueByList(xbs, 13);
        xb16 = getValueByList(xbs, 14);
        xb17 = getValueByList(xbs, 15);
        xb18 = getValueByList(xbs, 16);
        xb19 = getValueByList(xbs, 17);
        xb20 = getValueByList(xbs, 18);
        xb21 = getValueByList(xbs, 19);
        xb22 = getValueByList(xbs, 20);
        xb23 = getValueByList(xbs, 21);
        xb24 = getValueByList(xbs, 22);
        xb25 = getValueByList(xbs, 23);
        xb26 = getValueByList(xbs, 24);
        xb27 = getValueByList(xbs, 25);
        xb28 = getValueByList(xbs, 26);
        xb29 = getValueByList(xbs, 27);
        xb30 = getValueByList(xbs, 28);
        xb31 = getValueByList(xbs, 29);
        xb32 = getValueByList(xbs, 30);
        xb33 = getValueByList(xbs, 31);
        return DtuUtil.toJson(this);
    }

    private String  getValueByList(List<String> values, int i) {
        if (CollectionUtils.isEmpty(values) || values.size() - 1 < i) {
            return null;
        }
        return values.get(i);
    }
    public Integer getXbType() {
        return xbType;
    }

    public void setXbType(Integer xbType) {
        this.xbType = xbType;
    }



    @Override
    public String toString() {
        return "Xb{" +
                "xbType=" + xbType +
                ", xb2='" + xb2 + '\'' +
                ", xb3='" + xb3 + '\'' +
                ", xb4='" + xb4 + '\'' +
                ", xb5='" + xb5 + '\'' +
                ", xb6='" + xb6 + '\'' +
                ", xb7='" + xb7 + '\'' +
                ", xb8='" + xb8 + '\'' +
                ", xb9='" + xb9 + '\'' +
                ", xb10='" + xb10 + '\'' +
                ", xb11='" + xb11 + '\'' +
                ", xb12='" + xb12 + '\'' +
                ", xb13='" + xb13 + '\'' +
                ", xb14='" + xb14 + '\'' +
                ", xb15='" + xb15 + '\'' +
                ", xb16='" + xb16 + '\'' +
                ", xb17='" + xb17 + '\'' +
                ", xb18='" + xb18 + '\'' +
                ", xb19='" + xb19 + '\'' +
                ", xb20='" + xb20 + '\'' +
                ", xb21='" + xb21 + '\'' +
                ", xb22='" + xb22 + '\'' +
                ", xb23='" + xb23 + '\'' +
                ", xb24='" + xb24 + '\'' +
                ", xb25='" + xb25 + '\'' +
                ", xb26='" + xb26 + '\'' +
                ", xb27='" + xb27 + '\'' +
                ", xb28='" + xb28 + '\'' +
                ", xb29='" + xb29 + '\'' +
                ", xb30='" + xb30 + '\'' +
                ", xb31='" + xb31 + '\'' +
                ", xb32='" + xb32 + '\'' +
                '}';
    }
}
