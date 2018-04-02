package com.app.dtu.bean.model.device;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.bean.model.ParseToEntityAdapter;
import com.app.dtu.bean.model.RedundancyDeviceData;
import com.app.dtu.config.DtuConfig;

import javax.persistence.*;

/**
 * 电器火灾监监控设备-01
 */
@Entity
@Table(name =  DtuConfig.DTU_TABLE_PRIFIX +"combustible_gas_monitor_device")
public class ElectricalFireMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<ElectricalFireMonitorDevice> {

    public ElectricalFireMonitorDevice(Message message) {
        setMessage(message);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ua;
    private String ub;
    private String uc;
    private String la;
    private String lb;
    private String lc;
    private String st;
    private String pta;
    private String ptb;
    private String ptc;
    private String ptn;

    private String st1;
    private String st2;
    private String st3;
    private String st4;
    private String st5;
    private String st6;
    private String st7;
    private String st8;


    private String pt1;
    private String pt2;
    private String pt3;
    private String pt4;
    private String pt5;
    private String pt6;
    private String pt7;
    private String pt8;

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getUb() {
        return ub;
    }

    public void setUb(String ub) {
        this.ub = ub;
    }

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }

    public String getLa() {
        return la;
    }

    public void setLa(String la) {
        this.la = la;
    }

    public String getLb() {
        return lb;
    }

    public void setLb(String lb) {
        this.lb = lb;
    }

    public String getLc() {
        return lc;
    }

    public void setLc(String lc) {
        this.lc = lc;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getPta() {
        return pta;
    }

    public void setPta(String pta) {
        this.pta = pta;
    }

    public String getPtb() {
        return ptb;
    }

    public void setPtb(String ptb) {
        this.ptb = ptb;
    }

    public String getPtc() {
        return ptc;
    }

    public void setPtc(String ptc) {
        this.ptc = ptc;
    }

    public String getPtn() {
        return ptn;
    }

    public void setPtn(String ptn) {
        this.ptn = ptn;
    }

    public String getSt1() {
        return st1;
    }

    public void setSt1(String st1) {
        this.st1 = st1;
    }

    public String getSt2() {
        return st2;
    }

    public void setSt2(String st2) {
        this.st2 = st2;
    }

    public String getSt3() {
        return st3;
    }

    public void setSt3(String st3) {
        this.st3 = st3;
    }

    public String getSt4() {
        return st4;
    }

    public void setSt4(String st4) {
        this.st4 = st4;
    }

    public String getSt5() {
        return st5;
    }

    public void setSt5(String st5) {
        this.st5 = st5;
    }

    public String getSt6() {
        return st6;
    }

    public void setSt6(String st6) {
        this.st6 = st6;
    }

    public String getSt7() {
        return st7;
    }

    public void setSt7(String st7) {
        this.st7 = st7;
    }

    public String getSt8() {
        return st8;
    }

    public void setSt8(String st8) {
        this.st8 = st8;
    }

    public String getPt1() {
        return pt1;
    }

    public void setPt1(String pt1) {
        this.pt1 = pt1;
    }

    public String getPt2() {
        return pt2;
    }

    public void setPt2(String pt2) {
        this.pt2 = pt2;
    }

    public String getPt3() {
        return pt3;
    }

    public void setPt3(String pt3) {
        this.pt3 = pt3;
    }

    public String getPt4() {
        return pt4;
    }

    public void setPt4(String pt4) {
        this.pt4 = pt4;
    }

    public String getPt5() {
        return pt5;
    }

    public void setPt5(String pt5) {
        this.pt5 = pt5;
    }

    public String getPt6() {
        return pt6;
    }

    public void setPt6(String pt6) {
        this.pt6 = pt6;
    }

    public String getPt7() {
        return pt7;
    }

    public void setPt7(String pt7) {
        this.pt7 = pt7;
    }

    public String getPt8() {
        return pt8;
    }

    public void setPt8(String pt8) {
        this.pt8 = pt8;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public ElectricalFireMonitorDevice buildDevice() {
        return null;
    }

    @Override
    public Message buildMessage() {
        return null;
    }

    @Override
    public ElectricalFireMonitorDevice generateEntity(Message message) {
        return null;
    }
}
