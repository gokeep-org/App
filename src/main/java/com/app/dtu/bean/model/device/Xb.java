package com.app.dtu.bean.model.device;

import com.app.config.Ignore;
import com.app.dtu.util.DtuUtil;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Xb implements Serializable{
    
    @Ignore
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

    public String getXb2() {
        return xb2;
    }

    public void setXb2(String xb2) {
        this.xb2 = xb2;
    }

    public String getXb3() {
        return xb3;
    }

    public void setXb3(String xb3) {
        this.xb3 = xb3;
    }

    public String getXb4() {
        return xb4;
    }

    public void setXb4(String xb4) {
        this.xb4 = xb4;
    }

    public String getXb5() {
        return xb5;
    }

    public void setXb5(String xb5) {
        this.xb5 = xb5;
    }

    public String getXb6() {
        return xb6;
    }

    public void setXb6(String xb6) {
        this.xb6 = xb6;
    }

    public String getXb7() {
        return xb7;
    }

    public void setXb7(String xb7) {
        this.xb7 = xb7;
    }

    public String getXb8() {
        return xb8;
    }

    public void setXb8(String xb8) {
        this.xb8 = xb8;
    }

    public String getXb9() {
        return xb9;
    }

    public void setXb9(String xb9) {
        this.xb9 = xb9;
    }

    public String getXb10() {
        return xb10;
    }

    public void setXb10(String xb10) {
        this.xb10 = xb10;
    }

    public String getXb11() {
        return xb11;
    }

    public void setXb11(String xb11) {
        this.xb11 = xb11;
    }

    public String getXb12() {
        return xb12;
    }

    public void setXb12(String xb12) {
        this.xb12 = xb12;
    }

    public String getXb13() {
        return xb13;
    }

    public void setXb13(String xb13) {
        this.xb13 = xb13;
    }

    public String getXb14() {
        return xb14;
    }

    public void setXb14(String xb14) {
        this.xb14 = xb14;
    }

    public String getXb15() {
        return xb15;
    }

    public void setXb15(String xb15) {
        this.xb15 = xb15;
    }

    public String getXb16() {
        return xb16;
    }

    public void setXb16(String xb16) {
        this.xb16 = xb16;
    }

    public String getXb17() {
        return xb17;
    }

    public void setXb17(String xb17) {
        this.xb17 = xb17;
    }

    public String getXb18() {
        return xb18;
    }

    public void setXb18(String xb18) {
        this.xb18 = xb18;
    }

    public String getXb19() {
        return xb19;
    }

    public void setXb19(String xb19) {
        this.xb19 = xb19;
    }

    public String getXb20() {
        return xb20;
    }

    public void setXb20(String xb20) {
        this.xb20 = xb20;
    }

    public String getXb21() {
        return xb21;
    }

    public void setXb21(String xb21) {
        this.xb21 = xb21;
    }

    public String getXb22() {
        return xb22;
    }

    public void setXb22(String xb22) {
        this.xb22 = xb22;
    }

    public String getXb23() {
        return xb23;
    }

    public void setXb23(String xb23) {
        this.xb23 = xb23;
    }

    public String getXb24() {
        return xb24;
    }

    public void setXb24(String xb24) {
        this.xb24 = xb24;
    }

    public String getXb25() {
        return xb25;
    }

    public void setXb25(String xb25) {
        this.xb25 = xb25;
    }

    public String getXb26() {
        return xb26;
    }

    public void setXb26(String xb26) {
        this.xb26 = xb26;
    }

    public String getXb27() {
        return xb27;
    }

    public void setXb27(String xb27) {
        this.xb27 = xb27;
    }

    public String getXb28() {
        return xb28;
    }

    public void setXb28(String xb28) {
        this.xb28 = xb28;
    }

    public String getXb29() {
        return xb29;
    }

    public void setXb29(String xb29) {
        this.xb29 = xb29;
    }

    public String getXb30() {
        return xb30;
    }

    public void setXb30(String xb30) {
        this.xb30 = xb30;
    }

    public String getXb31() {
        return xb31;
    }

    public void setXb31(String xb31) {
        this.xb31 = xb31;
    }

    public String getXb32() {
        return xb32;
    }

    public void setXb32(String xb32) {
        this.xb32 = xb32;
    }

    public String getXb33() {
        return xb33;
    }

    public void setXb33(String xb33) {
        this.xb33 = xb33;
    }
}
