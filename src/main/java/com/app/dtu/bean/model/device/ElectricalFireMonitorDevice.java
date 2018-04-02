package com.app.dtu.bean.model.device;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.bean.model.ParseToEntityAdapter;
import com.app.dtu.bean.model.RedundancyDeviceData;
import com.app.dtu.config.DtuConfig;
import com.app.dtu.service.ServiceItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

/**
 * 电器火灾监监控设备-01
 */
@Entity
@Table(name =  DtuConfig.DTU_TABLE_PRIFIX +"combustible_gas_monitor_device")
public class ElectricalFireMonitorDevice extends RedundancyDeviceData implements DeviceDataDeal, ParseToEntityAdapter<ElectricalFireMonitorDevice> {
    private static final Logger logger = LoggerFactory.getLogger(ElectricalFireMonitorDevice.class);

    public ElectricalFireMonitorDevice(Message message) {
        setMessage(message);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ua;
    private Integer ub;
    private Integer uc;
    private Integer la;
    private Integer lb;
    private Integer lc;
    private Integer st;
    private Integer pta;
    private Integer ptb;
    private Integer ptc;
    private Integer ptn;

    private Integer st1;
    private Integer st2;
    private Integer st3;
    private Integer st4;
    private Integer st5;
    private Integer st6;
    private Integer st7;
    private Integer st8;


    private Integer pt1;
    private Integer pt2;
    private Integer pt3;
    private Integer pt4;
    private Integer pt5;
    private Integer pt6;
    private Integer pt7;
    private Integer pt8;

    public Integer getUa() {
        return ua;
    }

    public void setUa(Integer ua) {
        this.ua = ua;
    }

    public Integer getUb() {
        return ub;
    }

    public void setUb(Integer ub) {
        this.ub = ub;
    }

    public Integer getUc() {
        return uc;
    }

    public void setUc(Integer uc) {
        this.uc = uc;
    }

    public Integer getLa() {
        return la;
    }

    public void setLa(Integer la) {
        this.la = la;
    }

    public Integer getLb() {
        return lb;
    }

    public void setLb(Integer lb) {
        this.lb = lb;
    }

    public Integer getLc() {
        return lc;
    }

    public void setLc(Integer lc) {
        this.lc = lc;
    }

    public Integer getSt() {
        return st;
    }

    public void setSt(Integer st) {
        this.st = st;
    }

    public Integer getPta() {
        return pta;
    }

    public void setPta(Integer pta) {
        this.pta = pta;
    }

    public Integer getPtb() {
        return ptb;
    }

    public void setPtb(Integer ptb) {
        this.ptb = ptb;
    }

    public Integer getPtc() {
        return ptc;
    }

    public void setPtc(Integer ptc) {
        this.ptc = ptc;
    }

    public Integer getPtn() {
        return ptn;
    }

    public void setPtn(Integer ptn) {
        this.ptn = ptn;
    }

    public Integer getSt1() {
        return st1;
    }

    public void setSt1(Integer st1) {
        this.st1 = st1;
    }

    public Integer getSt2() {
        return st2;
    }

    public void setSt2(Integer st2) {
        this.st2 = st2;
    }

    public Integer getSt3() {
        return st3;
    }

    public void setSt3(Integer st3) {
        this.st3 = st3;
    }

    public Integer getSt4() {
        return st4;
    }

    public void setSt4(Integer st4) {
        this.st4 = st4;
    }

    public Integer getSt5() {
        return st5;
    }

    public void setSt5(Integer st5) {
        this.st5 = st5;
    }

    public Integer getSt6() {
        return st6;
    }

    public void setSt6(Integer st6) {
        this.st6 = st6;
    }

    public Integer getSt7() {
        return st7;
    }

    public void setSt7(Integer st7) {
        this.st7 = st7;
    }

    public Integer getSt8() {
        return st8;
    }

    public void setSt8(Integer st8) {
        this.st8 = st8;
    }

    public Integer getPt1() {
        return pt1;
    }

    public void setPt1(Integer pt1) {
        this.pt1 = pt1;
    }

    public Integer getPt2() {
        return pt2;
    }

    public void setPt2(Integer pt2) {
        this.pt2 = pt2;
    }

    public Integer getPt3() {
        return pt3;
    }

    public void setPt3(Integer pt3) {
        this.pt3 = pt3;
    }

    public Integer getPt4() {
        return pt4;
    }

    public void setPt4(Integer pt4) {
        this.pt4 = pt4;
    }

    public Integer getPt5() {
        return pt5;
    }

    public void setPt5(Integer pt5) {
        this.pt5 = pt5;
    }

    public Integer getPt6() {
        return pt6;
    }

    public void setPt6(Integer pt6) {
        this.pt6 = pt6;
    }

    public Integer getPt7() {
        return pt7;
    }

    public void setPt7(Integer pt7) {
        this.pt7 = pt7;
    }

    public Integer getPt8() {
        return pt8;
    }

    public void setPt8(Integer pt8) {
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
        try{
            ServiceItem.electricalFireMonitorService.save(this.generateEntity(getMessage()));
        }catch (Throwable e){
            logger.error("Execute add data to db or generate entity is error");
        }
        return true;
    }

    @Override
    public ElectricalFireMonitorDevice buildDevice() {
        return this;
    }

    @Override
    public Message buildMessage() {
        return getMessage();
    }

    @Override
    public ElectricalFireMonitorDevice generateEntity(Message message) {
        return null;
    }
}
